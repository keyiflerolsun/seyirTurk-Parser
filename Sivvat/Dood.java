package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.List;

/* loaded from: classes3.dex */
public class Dood extends Core {
    List<String> match;
    List<String> match2;

    public Dood(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String replace = str.replace(".so", ".yt");
        setRoot(replace);
        String stripToParse = stripToParse(replace, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.lookingFor = "dsplayer";
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dood.1
            @Override // java.lang.Runnable
            public void run() {
                Dood.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitWhileWorking();
            this.match = Helper.pregMatchAll("dsplayer\\.hotkeys[^']+'([^']+).+?function\\s*makePlay.+?return[^?]+[^\"]+", this.pageContent);
            this.match2 = Helper.pregMatchAll("dsplayer\\.hotkeys[^']+'[^']+.+?function\\s*makePlay.+?return[^?]+([^\"]+)", this.pageContent);
            this.url = this.root + this.match.get(0);
            this.headers.put("Referer", this.root);
            this.lookingFor = "https";
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            waitWhileWorking();
            this.url = this.pageContent.replace("<html>", "").replace("<head>", "").replace("<body>", "").replace("</html>", "").replace("</head>", "").replace("</body>", "").replace("\"", "").replace("~", "");
            for (int r1 = 0; r1 < 10; r1++) {
                this.url += Helper.selectAChar("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
            }
            this.url += this.match2 + String.valueOf(System.currentTimeMillis());
            oldParserIntegration();
        }
    }
}
