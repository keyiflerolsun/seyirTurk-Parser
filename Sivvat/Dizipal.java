package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Dizipal extends Core {
    public Dizipal(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizipal.1
            @Override // java.lang.Runnable
            public void run() {
                Dizipal.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.url = Helper.pregMatchAll("data-src=\"(.*?)\"", this.pageContent).get(0);
            getUrlContent();
        } else if (this.nextCount == 2) {
            this.streamUrl = Helper.pregMatchAll("file:\"(.*?)\"", this.pageContent).get(0);
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("\\](http.*?vtt)", this.pageContent);
            for (int r2 = 0; r2 < pregMatchAll.size(); r2++) {
                if (pregMatchAll.get(r2).contains("Turkce") || pregMatchAll.get(r2).contains(HtmlTableRow.TAG_NAME)) {
                    this.subtitle = pregMatchAll.get(r2);
                }
            }
            prepareVideo();
        }
    }
}
