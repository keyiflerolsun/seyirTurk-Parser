package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Izle720P extends Core {
    public Izle720P(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        if (this.url.contains("?lang=")) {
            if (this.url.split("\\?lang")[1].contains("dublaj")) {
                this.language = Core.Language.TR;
            }
            this.url = this.url.split("\\?lang")[0];
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Izle720P.1
            @Override // java.lang.Runnable
            public void run() {
                Izle720P.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\".*?<\\/iframe>", this.pageContent);
            this.pageContent = Helper.pregMatchAll("<span>.*?Player.*?</span>(.*?)data-movies", this.pageContent).get(0);
            for (int r2 = 0; r2 < pregMatchAll.size(); r2++) {
                if (!pregMatchAll.get(r2).toLowerCase().contains("youtube")) {
                    this.streamUrls.put("Alternatif " + r2, pregMatchAll.get(r2));
                }
            }
            Statics.language = this.language;
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            oldParserIntegration();
        }
    }
}
