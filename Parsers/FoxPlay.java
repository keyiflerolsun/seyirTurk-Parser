package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.GetsPack.GetFoxPlay;
import com.swenauk.mainmenu.Sivvat.Helper;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class FoxPlay extends Parsers {
    public String parsed;

    public FoxPlay(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetFoxPlay(this).execute(str);
    }

    public void resumeParse() {
        try {
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("source.*?:.*?'(.*?)'", this.parsed);
            int r1 = 0;
            while (true) {
                if (r1 < pregMatchAll.size()) {
                    if (pregMatchAll.get(r1).startsWith("http") && pregMatchAll.get(r1).contains("m3u8")) {
                        this.streamUrl = pregMatchAll.get(r1);
                        break;
                    }
                    r1++;
                } else {
                    break;
                }
            }
            if (this.streamUrl == null && !this.streamUrl.equals("")) {
                showBuffer();
                showAlert();
                return;
            }
            prepareVideo();
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
