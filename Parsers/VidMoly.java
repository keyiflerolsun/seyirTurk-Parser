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
import com.swenauk.mainmenu.GetsPack.GetVidMoly;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;

/* loaded from: classes3.dex */
public class VidMoly extends Parsers {
    public Boolean isSub;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subLink;

    public VidMoly(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        this.isSub = false;
        if (str.contains("/sheila")) {
            this.streamUrl = str;
            resumeParse();
        } else {
            new GetVidMoly(this).execute(str.replace("vidmoly.top", "vidmoly.me").replace("vidmoly.to", "vidmoly.me"));
        }
    }

    public void resumeParse() {
        try {
            if (this.streamUrls.size() == 1) {
                this.streamUrl = this.streamUrls.get(this.streamUrls.keySet().toArray()[0]);
                this.streamUrls.clear();
            }
            try {
                if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                    showBuffer();
                    showAlert();
                    return;
                }
                if (this.streamUrl != null) {
                    System.out.println(this.streamUrl);
                    if (this.isSub.booleanValue() && this.subLink != null) {
                        this.videoUri = Uri.parse(this.streamUrl);
                        System.out.println(this.subLink);
                        this.dataSourceFactory = new DefaultDataSourceFactory(this.calledContext, Util.getUserAgent(this.calledContext, "iFrame"));
                        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.1
                            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                            public DataSource createDataSource() {
                                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                                defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.me/");
                                return defaultHttpDataSource;
                            }
                        }).createMediaSource(MediaItem.fromUri(this.videoUri));
                        try {
                            if (this.subLink.startsWith("/")) {
                                this.subLink = "https://www.vidmoly.me" + this.subLink;
                            }
                            this.mergedSource = new MergingMediaSource(this.mediaSource, new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(this.subLink)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET));
                        } catch (Exception unused) {
                        }
                        playVideoSub();
                    } else {
                        prepareVideo();
                    }
                }
                if (this.streamUrls.isEmpty()) {
                    return;
                }
                createAlertWItems("Çözünürlük Seçiniz");
            } catch (Exception unused2) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4")) {
            this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.me/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                    if (VidMoly.this.streamUrl.contains("sheila")) {
                        defaultHttpDataSource.setRequestProperty("Referer", VidMoly.this.streamUrl.replace("/sheila", ""));
                    } else {
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.me/");
                    }
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        playVideo();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (VidMoly.this.calledContext instanceof VideoView) {
                        ((VideoView) VidMoly.this.calledContext).subtitles.put("Türkçe", true);
                    }
                } catch (Exception unused) {
                }
                VidMoly.this.player.prepare(VidMoly.this.mergedSource, false, false);
                if (VidMoly.this.calledContext instanceof VideoView) {
                    ((VideoView) VidMoly.this.calledContext).setVideoUri(VidMoly.this.videoUri);
                    try {
                        if (!VidMoly.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) VidMoly.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(VidMoly.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.4.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        VidMoly.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.4.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        VidMoly.this.player.seekTo(parseInt);
                                        VidMoly.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                VidMoly.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            VidMoly.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused2) {
                        VidMoly.this.player.setPlayWhenReady(true);
                    }
                }
                VidMoly.this.player.setPlayWhenReady(true);
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                VidMoly.this.showBuffer();
                VidMoly vidMoly = VidMoly.this;
                vidMoly.videoUri = Uri.parse(vidMoly.streamUrls.get(charSequenceArr[r4]));
                VidMoly.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.VidMoly.5.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.me/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(VidMoly.this.videoUri));
                VidMoly.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
