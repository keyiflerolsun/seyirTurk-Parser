package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Movie4k extends Core {
    public Movie4k(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        trSubLogin();
        if (stripToParse.contains("/serie/")) {
            this.s = str2;
            this.e = str3;
            this.isTv = true;
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Movie4k.1
            @Override // java.lang.Runnable
            public void run() {
                Movie4k.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.streamUrls = Helper.pregMatchAll("class=\"tablinks\"\\s*href=\"#\"\\s*data-link=\"(.*?)\">(.*?)<", this.pageContent, false, false);
            this.streamUrls.remove("Player HD");
            this.streamUrls.remove("Trailer");
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.url = fixUrl(this.selectedAlternate);
            if (this.url.contains("goodstream")) {
                getUrlContent();
                return;
            } else {
                next();
                return;
            }
        }
        if (this.nextCount == 3) {
            if (this.url.contains("goodstream")) {
                this.url = Helper.pregMatchAll("const\\s*source\\s*=\\s*new\\s*URL\\('(.*?)'.*?source\\.pathname\\s*\\+=.*?'(.*?)'", this.pageContent).get(0);
                this.streamUrl = this.url;
                this.stepType = Core.StepType.Play;
            }
            loadSubtitlesOnline();
            waitWhileWorking();
            oldParserIntegration();
        }
    }
}
