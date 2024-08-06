package com.swenauk.mainmenu.Parsers;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.swenauk.mainmenu.GetsPack.GetKanal7;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class Kanal7 extends Parsers {
    public String parsed;

    public Kanal7(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetKanal7(this).execute(str);
    }

    public void resumeParse() {
        try {
            for (String str : this.parsed.split("\n")) {
                if (str.contains("\"file\":")) {
                    System.out.println(str);
                    Matcher matcher = Pattern.compile("\"file\":\\s*\"(.*?)\"").matcher(str);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        System.out.println(group);
                        this.streamUrl = group.replace(StringUtils.SPACE, "").replace("\"", "");
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
