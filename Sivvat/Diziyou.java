package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Diziyou extends Core {
    public Diziyou(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.headers.put("Referer", stripToParse);
        this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Diziyou.1
            @Override // java.lang.Runnable
            public void run() {
                Diziyou.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.url = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.pageContent).get(0);
            if (this.language == Core.Language.TR) {
                this.url = this.url.replace(".html", "_tr.html");
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            this.subtitle = Helper.pregMatchAll("<track\\s*src=\"(.*?)\"", this.pageContent).get(0);
            this.url = Helper.pregMatchAll("<source.*?src=\"(.*?)\"", this.pageContent).get(0);
            oldParserIntegration();
        }
    }
}
