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
import com.swenauk.mainmenu.GetsPack.GetDiziyo;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.net.URI;

/* loaded from: classes3.dex */
public class Diziyo extends Parsers {
    public String main;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String referer;
    public String subtitle;

    public Diziyo(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        try {
            this.main = new URI(str).getHost();
            System.out.println(this.main);
        } catch (Exception unused) {
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetDiziyo(this).execute(str);
    }

    public void resumeParse() {
        try {
            if (this.streamUrls.size() == 1) {
                this.streamUrl = this.streamUrls.get(((CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]))[0]);
                this.streamUrls.clear();
            }
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl != null) {
                prepareVideo();
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
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                defaultHttpDataSource.setRequestProperty("Referer", Diziyo.this.referer);
                defaultHttpDataSource.setRequestProperty("Accept", "*/*");
                return defaultHttpDataSource;
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                Diziyo.this.showBuffer();
                Diziyo diziyo = Diziyo.this;
                diziyo.videoUri = Uri.parse(diziyo.streamUrls.get(charSequenceArr[r6]));
                System.out.println(Diziyo.this.videoUri);
                Diziyo.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        return new DefaultHttpDataSource("iFrame");
                    }
                }).createMediaSource(MediaItem.fromUri(Diziyo.this.videoUri));
                if (Diziyo.this.subtitle != null) {
                    System.out.println("Sub var");
                    SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(Diziyo.this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(Diziyo.this.subtitle)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                    Diziyo diziyo2 = Diziyo.this;
                    diziyo2.mergedSource = new MergingMediaSource(diziyo2.mediaSource, createMediaSource);
                }
                if (Diziyo.this.subtitle != null) {
                    Diziyo.this.playVideoSub();
                } else {
                    Diziyo.this.playVideo();
                }
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.3
            @Override // java.lang.Runnable
            public void run() {
                Diziyo.this.player.prepare(Diziyo.this.mergedSource, false, false);
                if (Diziyo.this.calledContext instanceof VideoView) {
                    ((VideoView) Diziyo.this.calledContext).setVideoUri(Diziyo.this.videoUri);
                    try {
                        if (!Diziyo.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) Diziyo.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Diziyo.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Diziyo.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Diziyo.3.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Diziyo.this.player.seekTo(parseInt);
                                        Diziyo.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Diziyo.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Diziyo.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        Diziyo.this.player.setPlayWhenReady(true);
                    }
                }
                Diziyo.this.player.setPlayWhenReady(true);
            }
        });
    }
}
