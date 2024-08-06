package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetYabanciDizi;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class YabanciDizi extends Parsers {
    public String cookie;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String referer;
    public String subtitle;

    public YabanciDizi(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.referer = "";
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetYabanciDizi(this).execute(str);
    }

    public void resumeParse() {
        try {
            if (this.parsed.contains("moly")) {
                new VidMoly(this.parsed, this.calledContext, this.player);
                return;
            }
            String str = this.parsed;
            if (str != null) {
                if (str.contains("mycdn.me")) {
                    try {
                        JSONArray jSONArray = new JSONArray(this.parsed);
                        for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                            this.streamUrls.put(jSONArray.getJSONObject(r2).getString("name"), jSONArray.getJSONObject(r2).getString("url"));
                        }
                        if (this.streamUrls.isEmpty()) {
                            showBuffer();
                            showAlert();
                            return;
                        } else {
                            createAlertWItems("Çözünürlük Seçiniz");
                            return;
                        }
                    } catch (Exception unused) {
                        this.streamUrls = null;
                        return;
                    }
                }
                if (this.streamUrls.size() != 0) {
                    createAlertWItems("Çözünürlük Seçiniz");
                    return;
                }
                this.streamUrl = this.parsed;
                System.out.println(this.streamUrl);
                prepareVideo();
                return;
            }
            showBuffer();
            showAlert();
        } catch (Exception unused2) {
            showBuffer();
            showAlert();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4") || this.streamUrl.contains("saruch") || this.streamUrl.contains("streamlare")) {
            if (this.streamUrl.contains("yandex.ru")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(YabanciDizi.this.parsed.contains("streamlare") ? "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                        if (!YabanciDizi.this.parsed.contains("streamlare")) {
                            if (YabanciDizi.this.streamUrl.contains("molyusercontent")) {
                                defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to");
                            } else if (YabanciDizi.this.streamUrl.contains("saruch")) {
                                defaultHttpDataSource.setRequestProperty("Referer", "https://yabancidizi.pw");
                            } else if (YabanciDizi.this.streamUrl.contains("dizilabapi")) {
                                defaultHttpDataSource.setRequestProperty("Referer", "https://dizilab.pw");
                                defaultHttpDataSource.setRequestProperty("Origin", "https://dizilab.pw");
                            } else {
                                defaultHttpDataSource.setRequestProperty("Referer", YabanciDizi.this.streamUrl);
                            }
                            if (YabanciDizi.this.streamUrl.contains("upstream")) {
                                defaultHttpDataSource.setRequestProperty("Origin", "https://sezonlukdizi.vip");
                            }
                        }
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            final String str = this.referer;
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    String replace;
                    if (YabanciDizi.this.parsed.contains("streamlare")) {
                        replace = System.getProperty("http.agent");
                    } else {
                        replace = !str.equals("") ? System.getProperty("http.agent").replace("Android", "") : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
                    }
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(replace);
                    String str2 = YabanciDizi.this.streamUrl.contains("molystream") ? YabanciDizi.this.streamUrl : "https://vidmoly.to";
                    if (!YabanciDizi.this.streamUrl.contains("manifest")) {
                        defaultHttpDataSource.setRequestProperty("Referer", str2);
                    }
                    if (!str.equals("")) {
                        defaultHttpDataSource.setRequestProperty("Referer", str);
                        System.getProperty("http.agent").replace("Android", "");
                    }
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        String str2 = this.subtitle;
        if (str2 != null) {
            this.mergedSource = new MergingMediaSource(this.mediaSource, new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(str2)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET));
        }
        if (this.mergedSource != null) {
            playVideoSub();
        } else {
            playVideo();
        }
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r5) {
                YabanciDizi.this.showBuffer();
                System.out.println(YabanciDizi.this.streamUrls.get(charSequenceArr[r5]));
                YabanciDizi yabanciDizi = YabanciDizi.this;
                yabanciDizi.videoUri = Uri.parse(yabanciDizi.streamUrls.get(charSequenceArr[r5]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(YabanciDizi.this.calledContext, Util.getUserAgent(YabanciDizi.this.calledContext, "iFrame"));
                if (YabanciDizi.this.streamUrls.get(charSequenceArr[r5]).contains("testediyorum")) {
                    YabanciDizi.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.3.1
                        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                        public DataSource createDataSource() {
                            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                            defaultHttpDataSource.setRequestProperty("Referer", "https://sezonlukdizi.vip");
                            return defaultHttpDataSource;
                        }
                    }).createMediaSource(MediaItem.fromUri(YabanciDizi.this.videoUri));
                } else {
                    YabanciDizi.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(YabanciDizi.this.videoUri));
                }
                YabanciDizi.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.4
            @Override // java.lang.Runnable
            public void run() {
                YabanciDizi.this.player.prepare(YabanciDizi.this.mergedSource, false, false);
                if (YabanciDizi.this.calledContext instanceof VideoView) {
                    ((VideoView) YabanciDizi.this.calledContext).setVideoUri(YabanciDizi.this.videoUri);
                    try {
                        if (!YabanciDizi.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) YabanciDizi.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(YabanciDizi.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.4.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        YabanciDizi.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.YabanciDizi.4.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        YabanciDizi.this.player.seekTo(parseInt);
                                        YabanciDizi.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                YabanciDizi.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            YabanciDizi.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        YabanciDizi.this.player.setPlayWhenReady(true);
                    }
                }
                YabanciDizi.this.player.setPlayWhenReady(true);
            }
        });
    }
}
