package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetYesilcam;
import com.swenauk.seyirturk.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class Yesilcam extends Parsers {
    public Map<String, String> parsed;

    public Yesilcam(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.parsed = new HashMap();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetYesilcam(this).execute(str);
    }

    public void resumeParse() {
        try {
            try {
                this.streamUrls = this.parsed;
            } catch (Exception unused) {
                showBuffer();
                showAlert();
            }
            if (this.streamUrls.size() == 1) {
                if (this.streamUrls.containsKey("Alone")) {
                    this.streamUrl = this.streamUrls.get("Alone");
                } else if (this.streamUrls.containsKey("720p")) {
                    this.streamUrl = this.streamUrls.get("720p");
                } else if (this.streamUrls.containsKey("1080p")) {
                    this.streamUrl = this.streamUrls.get("1080p");
                } else if (this.streamUrls.containsKey("480p")) {
                    this.streamUrl = this.streamUrls.get("480p");
                } else if (this.streamUrls.containsKey("360p")) {
                    this.streamUrl = this.streamUrls.get("360p");
                }
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
        if (this.streamUrl.contains("m3u8")) {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Yesilcam.1
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        } else {
            this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Yesilcam.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Yesilcam.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Yesilcam.this.showBuffer();
                Yesilcam yesilcam = Yesilcam.this;
                yesilcam.videoUri = Uri.parse(yesilcam.streamUrls.get(charSequenceArr[r4]));
                Yesilcam.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Yesilcam.3.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://www.atv.com.tr/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(Yesilcam.this.videoUri));
                Yesilcam.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
