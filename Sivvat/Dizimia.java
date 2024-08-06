package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Dizimia extends Core {
    public Dizimia(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.headers.put("Referer", stripToParse);
        this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizimia.1
            @Override // java.lang.Runnable
            public void run() {
                Dizimia.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.streamUrls = Helper.pregMatchAll("<a class=\"focus:outline-none\"\\s*href=\"(.*?)\"\\s*title=\"(.*?)\"", this.pageContent, false, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            getUrlContent();
        } else if (this.nextCount == 3) {
            this.url = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.pageContent).get(0);
            if (!this.url.startsWith("http")) {
                this.url = "https:" + this.url;
            }
            oldParserIntegration();
        }
    }
}
