package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Dizipub extends Core {
    public Dizipub(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.url = stripToParse;
        this.lookingFor = "application/ld+json";
        this.reloadFor = 1;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizipub.1
            @Override // java.lang.Runnable
            public void run() {
                Dizipub.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.pageContent = Helper.pregMatchAll("series-alter-active(.*?) </ul>", this.pageContent).get(0);
            this.streamUrls = Helper.pregMatchAll("<(?:span class=|a.*?href=)\"(.*?)\"\\s*title=\"(.*?)\"", this.pageContent, false, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.parserType = Core.ParserType.GetRequest;
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            saveCookieToServer("dpub");
            this.streamUrl = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\".*?allowfullscreen.*?</iframe>", this.pageContent).get(0);
            this.url = this.streamUrl;
            if (!this.streamUrl.startsWith("https")) {
                this.streamUrl = "https:" + this.streamUrl;
            }
            oldParserIntegration();
        }
    }
}
