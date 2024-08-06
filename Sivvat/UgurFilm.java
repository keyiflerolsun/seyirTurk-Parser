package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class UgurFilm extends Core {
    public UgurFilm(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.UgurFilm.1
            @Override // java.lang.Runnable
            public void run() {
                UgurFilm.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.root);
            sb.append("player");
            sb.append(Helper.pregMatchAll("<iframe.*?src=\"" + this.root + "player(.*?)\"", this.pageContent).get(0));
            String sb2 = sb.toString();
            if (sb2.contains(this.root + "player/play.php")) {
                sb2.split("=");
                return;
            }
            return;
        }
        int r0 = this.nextCount;
    }
}
