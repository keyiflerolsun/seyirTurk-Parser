package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Yabanci_Dizi extends Core {
    public Yabanci_Dizi(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse.replace("yabanci-dizi", "yabancidizi");
        if (stripToParse.contains("/film/")) {
            this.lookingFor = AppMeasurementSdk.ConditionalUserProperty.ACTIVE;
        } else {
            this.lookingFor = "bolum-menu";
        }
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Yabanci_Dizi.1
            @Override // java.lang.Runnable
            public void run() {
                Yabanci_Dizi.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.parserType = Core.ParserType.GetRequest;
            this.url = this.url.replace("/turkce", "");
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            saveCookieToServer("yb_dz");
            this.url = Helper.pregMatchAll("<iframe.*?allowfullscreen.*?src=\"(.*?)\"", this.pageContent).get(0);
            if (this.url.startsWith("//")) {
                this.url = "https:" + this.url;
            }
            this.streamUrl = this.url;
            oldParserIntegration();
        }
    }
}
