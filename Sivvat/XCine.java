package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class XCine extends Core {
    public XCine(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        trSubLogin();
        if (!str2.equals("0") || !str3.equals("0")) {
            this.s = str2;
            this.e = str3;
            this.isTv = true;
            this.url = this.url.split("html-")[0] + "html";
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.XCine.1
            @Override // java.lang.Runnable
            public void run() {
                XCine.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.streamUrls = Helper.pregMatchAll("<span\\s*data-link=\"(.*?)\"><i></i>\\s*(.*?)\\s*</span", this.pageContent, false, false);
            if (this.streamUrls.isEmpty() || (this.streamUrls.size() == 1 && this.streamUrls.containsKey(""))) {
                this.streamUrls = Helper.pregMatchAll("a\\s*href=\"#\"\\s*id=\".*?1_" + this.e + "\"\\s*data-link=\"(.*?)\">\\s*(.*?)\\s*</a>", this.pageContent, false, false);
            }
            this.streamUrls.remove("Trailer");
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            if (!this.selectedAlternate.startsWith("http")) {
                this.selectedAlternate = "https:" + this.selectedAlternate;
            }
            this.url = this.selectedAlternate;
            loadSubtitlesOnline();
            waitWhileWorking();
            oldParserIntegration();
        }
    }
}
