package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
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
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetContentx;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Contentx extends Parsers {
    public boolean isAlt;
    public boolean isDublaj;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subtitle;
    public HashMap<String, String> subtitles;

    public Contentx(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.subtitle = "";
        this.subtitles = new HashMap<>();
    }

    public Contentx(String str, Context context, ExoPlayer exoPlayer, String str2) {
        super(str, context, exoPlayer, str2);
        this.subtitle = "";
        this.subtitles = new HashMap<>();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        if (str.contains("?dub")) {
            str = str.replace("?dub", "");
            this.isDublaj = true;
        }
        this.isAlt = false;
        new GetContentx(this).execute(str);
    }

    public void showAlternates(final Map<String, String> map) {
        if (!((Activity) this.calledContext).isDestroyed()) {
            final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
            builder.setTitle("Kaynak Seçiniz:");
            builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Contentx.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int r5) {
                    new GetContentx(Contentx.this).execute((String) map.get(charSequenceArr[r5]));
                }
            });
            this.alert = builder.create();
            this.alert.show();
        }
        this.isAlt = false;
    }

    public void resumeParse() {
        try {
            String str = this.parsed;
            if (str != null) {
                if (str.contains(HtmlLabel.TAG_NAME)) {
                    try {
                        JSONArray jSONArray = new JSONObject(this.parsed).getJSONArray("sources");
                        for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                            this.streamUrls.put(jSONArray.getJSONObject(r2).getString(HtmlLabel.TAG_NAME), jSONArray.getJSONObject(r2).getString("file"));
                        }
                        createAlertWItems("Çözünürlük Seçiniz");
                        return;
                    } catch (Exception unused) {
                        showBuffer();
                        showAlert();
                        return;
                    }
                }
                this.streamUrl = this.parsed;
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
        if (this.streamUrl.contains(".mp4")) {
            if (this.streamUrl.contains("yandex.ru")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Contentx.2
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(System.getProperty("http.agent"));
                        String str = Contentx.this.videoUri.getScheme() + "://" + Contentx.this.videoUri.getHost();
                        if (Contentx.this.streamUrl.contains("molyusercontent")) {
                            defaultHttpDataSource.setRequestProperty("Referer", str);
                        } else {
                            defaultHttpDataSource.setRequestProperty("Referer", str);
                        }
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Contentx.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(System.getProperty("http.agent"));
                    String str = Contentx.this.videoUri.getScheme() + "://" + Contentx.this.videoUri.getHost();
                    if (Contentx.this.streamUrl.contains("molystream")) {
                        str = Contentx.this.videoUri.getScheme() + "://" + Contentx.this.videoUri.getHost();
                    }
                    defaultHttpDataSource.setRequestProperty("Referer", str);
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        if (this.isDublaj && this.mainUrlForReferer.contains("dizilla")) {
            this.subtitles.clear();
            this.subtitle = "";
        }
        if (!this.subtitle.equals("") || this.subtitles.size() > 0) {
            DefaultDataSource.Factory factory = new DefaultDataSource.Factory(this.calledContext, new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true).setUserAgent(System.getProperty("http.agent")).setConnectTimeoutMs(8000).setConnectTimeoutMs(8000));
            if (!this.subtitle.equals("")) {
                String replace = this.subtitle.replace("\\", "");
                this.subtitle = replace;
                SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(factory).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(replace)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                MergingMediaSource mergingMediaSource = this.mergedSource;
                if (mergingMediaSource != null) {
                    this.mergedSource = new MergingMediaSource(mergingMediaSource, createMediaSource);
                } else {
                    this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource);
                }
            } else {
                Iterator<Map.Entry<String, String>> it = this.subtitles.entrySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().getKey();
                    String replace2 = this.subtitles.get(key).replace("\\", "");
                    Uri parse = Uri.parse(replace2);
                    MediaItem.SubtitleConfiguration build = new MediaItem.SubtitleConfiguration.Builder(parse).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(key).build();
                    if (replace2.contains("srt")) {
                        build = new MediaItem.SubtitleConfiguration.Builder(parse).setMimeType(MimeTypes.APPLICATION_SUBRIP).setSelectionFlags(-1).setLanguage(key).build();
                    }
                    SingleSampleMediaSource createMediaSource2 = new SingleSampleMediaSource.Factory(factory).createMediaSource(build, C.TIME_UNSET);
                    MergingMediaSource mergingMediaSource2 = this.mergedSource;
                    if (mergingMediaSource2 != null) {
                        this.mergedSource = new MergingMediaSource(mergingMediaSource2, createMediaSource2);
                    } else {
                        this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource2);
                    }
                }
            }
            playVideoSub();
            return;
        }
        playVideo();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Contentx.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Contentx.this.player.prepare(Contentx.this.mergedSource, false, false);
                } catch (Exception unused) {
                    Contentx.this.player.prepare(Contentx.this.mediaSource, false, false);
                }
                if (Contentx.this.calledContext instanceof VideoView) {
                    ((VideoView) Contentx.this.calledContext).setVideoUri(Contentx.this.videoUri);
                    try {
                        if (!Contentx.this.isFragman.booleanValue()) {
                            final long parseInt = Integer.parseInt(((VideoView) Contentx.this.calledContext).mili);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Contentx.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Contentx.4.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Contentx.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Contentx.4.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Contentx.this.player.seekTo(parseInt);
                                        Contentx.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Contentx.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Contentx.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused2) {
                        Contentx.this.player.setPlayWhenReady(true);
                    }
                }
                Contentx.this.player.setPlayWhenReady(true);
            }
        });
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Contentx.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Contentx.this.showBuffer();
                System.out.println(Contentx.this.streamUrls.get(charSequenceArr[r4]));
                Contentx contentx = Contentx.this;
                contentx.videoUri = Uri.parse(contentx.streamUrls.get(charSequenceArr[r4]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(Contentx.this.calledContext, Util.getUserAgent(Contentx.this.calledContext, "iFrame"));
                Contentx.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(Contentx.this.videoUri));
                Contentx.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
