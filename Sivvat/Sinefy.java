package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;

/* loaded from: classes3.dex */
public class Sinefy extends Core {
    public Sinefy(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        Statics.language = this.language;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Classic;
        this.url = stripToParse;
        this.lookingFor = "data-querytype";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Sinefy.1
            @Override // java.lang.Runnable
            public void run() {
                Sinefy.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.streamUrls = Helper.pregMatchAll("data-querytype=\".*?\">\\s*<a href=\"(.*?)\"\\s*data-navigo.*?>(.*?)<", this.pageContent, false, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            saveCookieToServer("snfy");
            this.url = this.selectedAlternate;
            this.parserType = Core.ParserType.GetRequest;
            this.headers.put("Accept", "*/*");
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            if (this.selectedAlternate.contains("rapid")) {
                this.url = Helper.pregMatchAll("file:\"(.*?)\"", this.pageContent).get(0);
                this.streamUrl = this.url;
            } else {
                this.url = Helper.pregMatchFilter("iframe.*?src=\"(.*?)\"", this.pageContent, "finema", true);
                if (this.url.startsWith("//")) {
                    this.url = "https:" + this.url;
                }
                this.streamUrl = this.url;
            }
            oldParserIntegration();
        }
    }
}
