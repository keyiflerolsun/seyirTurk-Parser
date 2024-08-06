package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.GetsPack.GetKanalD;

/* loaded from: classes3.dex */
public class KanalD extends Parsers {
    public String parsed;

    public KanalD(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetKanalD(this).execute(str);
    }

    public void resumeParse() {
        try {
            if (this.parsed.contains("dailymotion")) {
                new DailyMotion(this.parsed, this.calledContext, this.player);
            } else {
                this.streamUrl = this.parsed;
                if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                    showBuffer();
                    showAlert();
                }
                if (this.streamUrl != null) {
                    prepareVideo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        System.out.println(this.streamUrl);
        this.mediaSource = new HlsMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }
}
