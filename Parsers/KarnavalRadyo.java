package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetKarnavalRadyo;

/* loaded from: classes3.dex */
public class KarnavalRadyo extends Parsers {
    public String parsed;

    public KarnavalRadyo(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetKarnavalRadyo(this).execute(str);
    }

    public void resumeParse() {
        String str = this.parsed;
        if (str != null) {
            this.streamUrl = str;
        }
        if (this.streamUrl != null || !this.streamUrls.isEmpty()) {
            if (this.streamUrl != null) {
                prepareVideo();
            }
        } else {
            showBuffer();
            showAlert();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.parsed.contains("m3u8")) {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.KarnavalRadyo.1
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame", 8000, 8000, true, null);
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        } else {
            this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.KarnavalRadyo.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame", 8000, 8000, true, null);
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        playVideo();
    }
}
