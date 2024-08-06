package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Upcloud extends Core {
    String v;

    public Upcloud(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        setRoot(str);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.initialUrl = stripToParse;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.headers.put("Referer", "https://dokicloud.one");
        this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Upcloud.1
            @Override // java.lang.Runnable
            public void run() {
                Upcloud.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.v = Helper.pregMatchAll("e4-player\\.min\\.js\\?v=(.*?)\"", this.pageContent).get(0);
            this.parserType = Core.ParserType.WebView;
            this.uaType = Core.UAType.Nondroid;
            headersClear();
            String[] split = this.initialUrl.split("/");
            this.url = "https://dokicloud.one/ajax/embed-4/getSources?id=" + split[split.length - 1].split("\\?")[0];
            this.parserType = Core.ParserType.GetRequest;
            setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
            this.headers.put("Referer", "https://dokicloud.one");
            this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            return;
        }
        int r0 = this.nextCount;
    }
}
