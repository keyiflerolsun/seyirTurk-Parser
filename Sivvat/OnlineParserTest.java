package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;

/* loaded from: classes3.dex */
public class OnlineParserTest extends Core {
    public OnlineParserTest(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        Statics.language = this.language;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.OnlineParser;
        this.uaType = Core.UAType.Nondroid;
        setUserAgent();
        this.url = stripToParse;
        if (!str2.equals("0") || !str3.equals("0")) {
            this.s = str2;
            this.e = str3;
            this.isTv = true;
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.OnlineParserTest.1
            @Override // java.lang.Runnable
            public void run() {
                OnlineParserTest.this.start();
            }
        }).start();
    }
}
