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
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.swenauk.mainmenu.GetsPack.GetDiziroll;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.net.URI;

/* loaded from: classes3.dex */
public class Diziroll extends Parsers {
    public boolean isAlt;
    public String main;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subtitle;

    public Diziroll(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isAlt = true;
        try {
            this.main = new URI(str).getHost();
            System.out.println(this.main);
        } catch (Exception unused) {
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetDiziroll(this).execute(str);
    }

    public void resumeParse() {
        try {
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl != null) {
                if (!this.streamUrl.startsWith("http")) {
                    this.streamUrl = "https:" + this.streamUrl;
                }
                if (!this.streamUrl.contains("contentx") && !this.streamUrl.contains("filese") && !this.streamUrl.contains("playru")) {
                    if (this.streamUrl.contains("vidmoly")) {
                        loadVidMoly();
                    }
                }
                loadContentx();
            }
            if (this.streamUrls.isEmpty()) {
                return;
            }
            createAlertWItems("Çözünürlük Seçiniz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        System.out.println("showVideo");
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                return new DefaultHttpDataSource("iFrame");
            }
        }).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                Diziroll.this.showBuffer();
                Diziroll diziroll = Diziroll.this;
                diziroll.videoUri = Uri.parse(diziroll.streamUrls.get(charSequenceArr[r6]));
                System.out.println(Diziroll.this.videoUri);
                Diziroll.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        return new DefaultHttpDataSource("iFrame");
                    }
                }).createMediaSource(MediaItem.fromUri(Diziroll.this.videoUri));
                if (Diziroll.this.subtitle != null) {
                    System.out.println("Sub var");
                    SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(Diziroll.this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(Diziroll.this.subtitle)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                    Diziroll diziroll2 = Diziroll.this;
                    diziroll2.mergedSource = new MergingMediaSource(diziroll2.mediaSource, createMediaSource);
                }
                if (Diziroll.this.subtitle != null) {
                    Diziroll.this.playVideoSub();
                } else {
                    Diziroll.this.playVideo();
                }
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.3
            @Override // java.lang.Runnable
            public void run() {
                Diziroll.this.player.prepare(Diziroll.this.mergedSource, false, false);
                if (Diziroll.this.calledContext instanceof VideoView) {
                    ((VideoView) Diziroll.this.calledContext).setVideoUri(Diziroll.this.videoUri);
                    try {
                        if (!Diziroll.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) Diziroll.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Diziroll.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Diziroll.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziroll.3.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Diziroll.this.player.seekTo(parseInt);
                                        Diziroll.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Diziroll.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Diziroll.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        Diziroll.this.player.setPlayWhenReady(true);
                    }
                }
                Diziroll.this.player.setPlayWhenReady(true);
            }
        });
    }

    public void loadContentx() {
        new Contentx(this.streamUrl, this.calledContext, this.player, this.main);
    }

    public void loadVidMoly() {
        new VidMoly(this.streamUrl, this.calledContext, this.player);
    }
}
