package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import android.util.Base64;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.GetsPack.GetS1CDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class S1cdn extends Parsers {
    public String parsed;

    public S1cdn(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetS1CDN(this).execute(str);
    }

    public void resumeParse() {
        try {
            for (String str : this.parsed.split("\n")) {
                if (str.contains("var file")) {
                    Matcher matcher = Pattern.compile("file\\s*=\\s*\"(.*)=\";").matcher(str);
                    if (matcher.find()) {
                        String str2 = matcher.group(1) + "=";
                        System.out.println(str2);
                        try {
                            String str3 = new String(Base64.decode(str2.replaceAll("//.*?=", "").substring(2), 0));
                            System.out.println(str3);
                            this.streamUrl = str3;
                        } catch (Exception unused) {
                        }
                    }
                }
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
        this.mediaSource = new HlsMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
        playVideo();
    }
}
