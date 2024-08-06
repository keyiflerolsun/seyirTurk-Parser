package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlEmbed;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Movies123 extends Core {
    String playableUrl;
    String tvID;

    public Movies123(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        this.playableUrl = "";
        this.tvID = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebViewAutoClick;
        this.uaType = Core.UAType.Nondroid;
        this.lookingFor = "master.m3u8";
        this.lookingForEmbed = HtmlEmbed.TAG_NAME;
        this.toClick = "play-now";
        this.clickType = -1;
        setUserAgent();
        trSubLogin();
        this.url = stripToParse;
        if (!stripToParse.contains("/movie/")) {
            this.initialUrl = this.url;
            this.isTv = true;
            this.s = str2;
            this.e = str3;
            this.isDoubleClick = true;
            this.toClick += "|ep-" + this.e;
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Movies123.1
            @Override // java.lang.Runnable
            public void run() {
                Movies123.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitWhileWorking();
            loadSubtitlesOnline();
            waitWhileWorking();
            oldParserIntegration();
        }
    }
}
