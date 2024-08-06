package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.gargoylesoftware.htmlunit.html.HtmlEmbed;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.GetsPack.GetDailyMotion;

/* loaded from: classes3.dex */
public class DailyMotion extends Parsers {
    public String parsed;

    public DailyMotion(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        if (!str.contains(HtmlEmbed.TAG_NAME) && !str.contains("kanal7")) {
            str = str.replace("video", "embed/video");
        }
        new GetDailyMotion(this).execute(str);
    }

    public void resumeParse() {
        try {
            this.streamUrl = this.parsed;
            if (this.streamUrl != null) {
                prepareVideo();
            } else {
                showBuffer();
                showAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new HlsMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }
}
