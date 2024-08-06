package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.GetsPack.GetDiziPlus;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DiziPlus extends Parsers {
    public String adder;
    public String audioUrl;
    public boolean isAuto;
    public boolean isFirst;
    public boolean isTurkish;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String referer;
    public String subLink;
    public String videoUrl;

    public DiziPlus(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isTurkish = str.contains("#l=1");
        this.isFirst = true;
        this.isAuto = false;
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetDiziPlus(this).execute(str);
    }

    public void resumeParse() {
        try {
            this.isFirst = false;
            for (String str : this.parsed.split("\n")) {
                if (str.contains("eval")) {
                    Matcher matcher = Pattern.compile("eval\\((.*)\\)</script><script>").matcher(str);
                    if (matcher.find()) {
                        try {
                            Matcher matcher2 = Pattern.compile("setup\\((.*?)\\);", 32).matcher(JSUnpacker.Unpack("eval(" + matcher.group(1) + ")"));
                            if (matcher2.find()) {
                                try {
                                    JSONObject jSONObject = new JSONObject(matcher2.group(1));
                                    this.streamUrl = jSONObject.getJSONArray("sources").getJSONObject(0).getString("file");
                                    JSONArray jSONArray = jSONObject.getJSONArray("tracks");
                                    for (int r6 = 0; r6 < jSONArray.length(); r6++) {
                                        if (jSONArray.getJSONObject(r6).getString(HtmlLabel.TAG_NAME).equals("Turkce") && !this.isTurkish) {
                                            this.subLink = jSONArray.getJSONObject(r6).getString("file");
                                        }
                                    }
                                    System.out.println("Stopper");
                                } catch (Exception unused) {
                                    showBuffer();
                                    showAlert();
                                }
                            }
                        } catch (Exception unused2) {
                            showBuffer();
                            showAlert();
                        }
                    }
                } else {
                    Matcher matcher3 = Pattern.compile("file:\"(.*?m3u8)\"").matcher(str);
                    if (matcher3.find()) {
                        this.streamUrl = matcher3.group(1);
                    }
                    try {
                        Matcher matcher4 = Pattern.compile("tracks.*?file:\"(.*?)\",label:\"Turkce\"").matcher(str);
                        if (matcher4.find()) {
                            this.subLink = matcher4.group(1);
                        } else {
                            Matcher matcher5 = Pattern.compile("tracks.*?file:\"(.*?)\"").matcher(str);
                            if (matcher5.find()) {
                                this.subLink = matcher5.group(1);
                            }
                        }
                    } catch (Exception unused3) {
                    }
                }
            }
            GetDiziPlus getDiziPlus = new GetDiziPlus(this);
            if (this.streamUrl == null) {
                getDiziPlus.execute(this.parsed);
            } else {
                getDiziPlus.execute(this.streamUrl);
            }
        } catch (Exception unused4) {
            showBuffer();
            showAlert();
        }
    }

    public void secondTimeResume() {
        try {
            if (this.streamUrl != null) {
                this.referer = this.streamUrl;
                if (this.parsed != null) {
                    this.audioUrl = this.streamUrl.replace("master.m3u8", this.parsed);
                    if (!this.isAuto) {
                        this.parsed = this.parsed.replace("index-", "index-v1-");
                        this.streamUrl = this.streamUrl.replace("master.m3u8", this.parsed);
                    }
                }
                prepareVideo();
                return;
            }
            if (this.parsed.contains("m3u8")) {
                this.streamUrl = this.parsed;
                prepareVideo();
            } else {
                showBuffer();
                showAlert();
            }
        } catch (Exception unused) {
            showBuffer();
            showAlert();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        System.out.println(this.streamUrl);
        System.out.println(this.audioUrl);
        System.out.println(this.subLink);
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.DiziPlus.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                defaultHttpDataSource.setRequestProperty("Referer", DiziPlus.this.referer);
                return defaultHttpDataSource;
            }
        }).createMediaSource(MediaItem.fromUri(this.videoUri));
        if (this.audioUrl != null) {
            this.mergedSource = new MergingMediaSource(this.mediaSource, new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.DiziPlus.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                    defaultHttpDataSource.setRequestProperty("Referer", DiziPlus.this.referer);
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(Uri.parse(this.audioUrl))));
        }
        if (this.subLink != null) {
            System.out.println("Sub var");
            SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(this.subLink)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
            MergingMediaSource mergingMediaSource = this.mergedSource;
            if (mergingMediaSource != null) {
                this.mergedSource = new MergingMediaSource(mergingMediaSource, createMediaSource);
            } else {
                this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource);
            }
        }
        try {
            if (this.mergedSource != null) {
                playVideoSub();
            } else {
                playVideo();
            }
        } catch (Exception unused) {
        }
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.DiziPlus.3
            @Override // java.lang.Runnable
            public void run() {
                DiziPlus.this.player.prepare(DiziPlus.this.mergedSource, false, false);
                if (DiziPlus.this.calledContext instanceof VideoView) {
                    ((VideoView) DiziPlus.this.calledContext).setVideoUri(DiziPlus.this.videoUri);
                    try {
                        if (!DiziPlus.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) DiziPlus.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DiziPlus.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.DiziPlus.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        DiziPlus.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.DiziPlus.3.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        DiziPlus.this.player.seekTo(parseInt);
                                        DiziPlus.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                DiziPlus.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            DiziPlus.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        DiziPlus.this.player.setPlayWhenReady(true);
                    }
                }
                DiziPlus.this.player.setPlayWhenReady(true);
            }
        });
    }
}
