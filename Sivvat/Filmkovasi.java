package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;

/* loaded from: classes3.dex */
public class Filmkovasi extends Core {
    public Filmkovasi(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        Statics.language = this.language;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Nondroid;
        setUserAgent();
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmkovasi.1
            @Override // java.lang.Runnable
            public void run() {
                Filmkovasi.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.url = Helper.pregMatchAll("<iframe\\s*src=\"(.*?)\"", this.pageContent).get(0);
            if (this.url.contains("trstx") || this.url.contains("sobreatsesuyp")) {
                oldParserIntegration();
                return;
            } else {
                getUrlContent();
                return;
            }
        }
        if (this.nextCount == 2) {
            this.url = Helper.pregMatchAll("file:\\s*\"(.*?)\"", this.pageContent).get(0);
            this.stepType = Core.StepType.Play;
            oldParserIntegration();
        }
    }
}
