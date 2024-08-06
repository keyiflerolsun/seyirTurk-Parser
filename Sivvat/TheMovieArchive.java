package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlEmbed;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class TheMovieArchive extends Core {
    String id;

    public TheMovieArchive(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2) {
        super(str, context, exoPlayer, webView);
        this.id = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebViewAutoClick;
        this.lookingFor = ".m3u8|.mp4";
        this.lookingForEmbed = HtmlEmbed.TAG_NAME;
        this.toClick = "jw-icon jw-icon-display jw-button-color jw-reset|jw-icon jw-icon-display jw-button-color jw-reset";
        this.isDoubleClick = true;
        this.clickType = 0;
        this.url = stripToParse;
        setUserAgent();
        trSubLogin();
        this.id = str2 + "_themoviearchive";
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.TheMovieArchive.1
            @Override // java.lang.Runnable
            public void run() {
                TheMovieArchive.this.start();
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
