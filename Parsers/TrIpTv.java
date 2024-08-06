package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetTrIpTv;
import com.swenauk.seyirturk.R;

/* loaded from: classes3.dex */
public class TrIpTv extends Parsers {
    public String parsed;

    public TrIpTv(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isFragman = true;
        this.parsed = "";
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetTrIpTv(this).execute(str);
    }

    public void resumeParse() {
        try {
            try {
                this.streamUrl = this.parsed;
                System.out.println(this.streamUrl);
                if (!this.streamUrl.contains("http") && !this.streamUrl.contains("https")) {
                    this.streamUrl = "https:" + this.streamUrl;
                }
                if (this.streamUrl.contains("youtube.com")) {
                    showBuffer();
                    if (this.streamUrl.contains("/embed/")) {
                        this.streamUrl = this.streamUrl.replace("/embed/", "/watch?v=");
                    }
                    new YoutubeWGetter(this.streamUrl, this.calledContext, this.player);
                }
                if (this.streamUrl.contains("dailymotion.com")) {
                    showBuffer();
                    new DailyMotion(this.streamUrl, this.calledContext, this.player);
                }
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
                }
                this.streamUrls.clear();
            }
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl != null && !this.streamUrl.contains("dailymotion.com") && !this.streamUrl.contains("youtube.com")) {
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
        final String str = this.streamUrl;
        if (this.streamUrl.contains("saban.tiviplayer")) {
            str = "https://sabantv.com";
        } else if (this.streamUrl.contains("cnnturknp")) {
            str = "https://www.cnnturk.com/";
        }
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.TrIpTv.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                defaultHttpDataSource.setRequestProperty("Referer", str);
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.TrIpTv.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                TrIpTv.this.showBuffer();
                TrIpTv trIpTv = TrIpTv.this;
                trIpTv.videoUri = Uri.parse(trIpTv.streamUrls.get(charSequenceArr[r4]));
                TrIpTv.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.TrIpTv.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://www.atv.com.tr/");
                        defaultHttpDataSource.setRequestProperty("Cookie", "__cfduid=ddc346e2c1647e4c8fa26a56c86b1eeb21604082537; _csrf=458dae98d1ba6355b5d25a4e254968a5928f941efb5cfd1fa6e88708486e3a7ba%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22rEjLPhSY1lH7lRHet3GNlAOrbD_hPQNm%22%3B%7D; _b=kube13");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(TrIpTv.this.videoUri));
                TrIpTv.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
