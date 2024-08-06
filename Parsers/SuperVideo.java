package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.GetsPack.GetSuperVideo;
import com.swenauk.mainmenu.Sivvat.Helper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class SuperVideo extends Parsers {
    public boolean isSto;
    public String parsed;

    public SuperVideo(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isSto = false;
    }

    public SuperVideo(String str, Context context, ExoPlayer exoPlayer, boolean z) {
        super(str, context, exoPlayer);
        this.isSto = false;
        this.isSto = z;
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetSuperVideo(this).execute(str);
    }

    public void resumeParse() {
        try {
            Matcher matcher = Pattern.compile("(eval\\(function.*?)</script>", 32).matcher(this.parsed);
            if (matcher.find()) {
                String Unpack = JSUnpacker.Unpack(matcher.group(1).replace("\n", ""));
                this.streamUrl = Helper.pregMatchAll("file:\"(.*?)\"", Unpack).get(0);
                if (this.streamUrl.isEmpty()) {
                    this.streamUrl = Helper.pregMatchAll("src:\"(.*?)\"", Unpack).get(0);
                }
            } else {
                this.streamUrl = Helper.pregMatchAll("\\[\\{file:\"(.*?)\"", this.parsed).get(0);
            }
            if (this.isSto) {
                return;
            }
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
        System.out.println(this.streamUrl);
        this.mediaSource = new HlsMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }
}
