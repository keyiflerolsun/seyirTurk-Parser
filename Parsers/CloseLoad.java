package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetCloseLoad;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CloseLoad extends Parsers {
    public Boolean isSub;
    private int lang;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subLink;
    public Map<String, String> subLinks;

    public CloseLoad(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    public CloseLoad(String str, Context context, ExoPlayer exoPlayer, DefaultTrackSelector defaultTrackSelector) {
        super(str, context, exoPlayer);
        this.trackSelector = defaultTrackSelector;
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        GetCloseLoad getCloseLoad = new GetCloseLoad(this);
        this.lang = -1;
        if (str.contains("l=1")) {
            this.lang = 1;
        } else if (str.contains("l=0")) {
            this.lang = 0;
        }
        getCloseLoad.execute(str.replace("?l=1", "").replace("?l=0", ""));
    }

    public void resumeParse() {
        this.subLinks = new HashMap();
        try {
            for (String str : this.parsed.split("\n")) {
                if (str.contains("contentUrl")) {
                    Boolean valueOf = Boolean.valueOf(str.contains("kind=\"captions\""));
                    this.isSub = valueOf;
                    if (valueOf.booleanValue()) {
                        Matcher matcher = Pattern.compile("<track src=\"(.*?)\".*?srclang=\"(.*?)\"").matcher(str);
                        while (matcher.find()) {
                            this.subLinks.put(matcher.group(2), "https://closeload.com" + matcher.group(1));
                        }
                    }
                    if (this.lang == 1) {
                        this.isSub = false;
                    }
                    Matcher matcher2 = Pattern.compile("\"contentUrl\": \"([^\"]+)\"").matcher(str);
                    if (matcher2.find()) {
                        String group = matcher2.group(1);
                        System.out.println(group);
                        this.streamUrl = group.replace(StringUtils.SPACE, "").replace("\"", "");
                    }
                }
            }
            if (this.streamUrl != null) {
                if (this.isSub.booleanValue() && (this.subLink != null || this.subLinks.size() > 0)) {
                    this.videoUri = Uri.parse(this.streamUrl);
                    this.dataSourceFactory = new DefaultDataSourceFactory(this.calledContext, Util.getUserAgent(this.calledContext, "iFrame"));
                    this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.CloseLoad.1
                        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                        public DataSource createDataSource() {
                            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
                            defaultHttpDataSource.setRequestProperty("Referer", "https://closeload.com/");
                            defaultHttpDataSource.setRequestProperty("Origin", "https://closeload.com/");
                            return defaultHttpDataSource;
                        }
                    }).createMediaSource(MediaItem.fromUri(this.videoUri));
                    for (int r0 = 0; r0 < this.subLinks.size(); r0++) {
                        String str2 = (String) this.subLinks.keySet().toArray()[r0];
                        String str3 = this.subLinks.get(str2);
                        this.subLink = str3;
                        SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(str3)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(str2).build(), C.TIME_UNSET);
                        MergingMediaSource mergingMediaSource = this.mergedSource;
                        if (mergingMediaSource == null) {
                            this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource);
                        } else {
                            this.mergedSource = new MergingMediaSource(mergingMediaSource, createMediaSource);
                        }
                    }
                    if (this.mergedSource != null) {
                        playVideoSub();
                        return;
                    } else {
                        prepareVideo();
                        return;
                    }
                }
                prepareVideo();
                return;
            }
            showBuffer();
            showAlert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.CloseLoad.2
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource createDataSource = new DefaultHttpDataSource.Factory().setReadTimeoutMs(8000).setConnectTimeoutMs(8000).setAllowCrossProtocolRedirects(true).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0").createDataSource();
                createDataSource.setRequestProperty("Referer", "https://closeload.com/");
                createDataSource.setRequestProperty("Origin", "https://closeload.com/");
                return createDataSource;
            }
        }).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.CloseLoad.3
            @Override // java.lang.Runnable
            public void run() {
                CloseLoad.this.player.prepare(CloseLoad.this.mergedSource, false, false);
                if (CloseLoad.this.lang == 0) {
                    System.out.println(CloseLoad.this.player.getCurrentTrackSelections());
                    try {
                        CloseLoad.this.trackSelector.setParameters(CloseLoad.this.trackSelector.getParameters().buildUpon().setPreferredAudioLanguage("English"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        CloseLoad.this.trackSelector.setParameters(CloseLoad.this.trackSelector.getParameters().buildUpon().setPreferredAudioLanguage("Turkish"));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (CloseLoad.this.calledContext instanceof VideoView) {
                    ((VideoView) CloseLoad.this.calledContext).setVideoUri(CloseLoad.this.videoUri);
                    try {
                        if (!CloseLoad.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) CloseLoad.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CloseLoad.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.CloseLoad.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        CloseLoad.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.CloseLoad.3.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        CloseLoad.this.player.seekTo(parseInt);
                                        CloseLoad.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                CloseLoad.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            CloseLoad.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        CloseLoad.this.player.setPlayWhenReady(true);
                    }
                }
                CloseLoad.this.player.setPlayWhenReady(true);
            }
        });
    }
}
