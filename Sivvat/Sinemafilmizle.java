package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes3.dex */
public class Sinemafilmizle extends Core {
    boolean isHugo;

    public Sinemafilmizle(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.isHugo = false;
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Custom;
        setUserAgent();
        this.url = stripToParse;
        this.lookingFor = "baslik:";
        this.reloadFor = 3;
        this.headers.put("Referer", stripToParse);
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Sinemafilmizle.1
            @Override // java.lang.Runnable
            public void run() {
                Sinemafilmizle.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("dil=\"(.*?)\">.*?</span>", this.pageContent);
            LinkedList<String> pregMatchAll2 = Helper.pregMatchAll("dil=\".*?\">(.*?)</span>", this.pageContent);
            LinkedList<String> pregMatchAll3 = Helper.pregMatchAll("html\\('<iframe.*?src=\"(.*?)\"", this.pageContent);
            String str = this.language == Core.Language.TR ? "trd" : "tra";
            for (int r1 = 0; r1 < pregMatchAll.size(); r1++) {
                if (pregMatchAll.get(r1).equals(str)) {
                    this.streamUrls.put(pregMatchAll2.get(r1), pregMatchAll3.get(r1));
                }
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            String str2 = "";
            for (Map.Entry<String, String> entry : this.streamUrls.entrySet()) {
                if (entry.getValue().equals(this.selectedAlternate)) {
                    str2 = entry.getKey();
                }
            }
            if (str2.toLowerCase().contains("sine")) {
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.lookingFor = "popcornvakti";
                this.lookingForEmbed = "I AM NOT LOOKING";
                this.clickType = 0;
                this.isHugo = true;
                getUrlContent();
                return;
            }
            this.url = this.selectedAlternate;
            if (Helper.containsAny(this.url, "my.mail.ru", "vidmoly")) {
                if (this.url.contains("vidmoly")) {
                    this.url = this.url.split("vid=")[1];
                }
                oldParserIntegration();
                return;
            } else {
                this.parserType = Core.ParserType.GetRequest;
                headersClear();
                this.headers.put("Referer", this.initialUrl);
                getUrlContent();
                return;
            }
        }
        if (this.nextCount == 3) {
            if (this.isHugo) {
                waitWhileWorking();
                this.lookingFor = "popcornvakti";
                this.clickType = 0;
                this.toClick = Helper.getGetRequestContent(this.headers, Statics.SERVER + "sey/v2/toClickFor.php?type=sfx");
                this.checkMedia = true;
                getUrlContent();
                return;
            }
            this.streamUrl = Helper.pregMatchAll("player\"><iframe.*?src=[\"\\'](.*?)[\"\\']", this.pageContent).get(0);
            if (this.streamUrl.contains("odno")) {
                this.url = this.streamUrl;
                oldParserIntegration();
                return;
            } else {
                headersClear();
                this.url = this.streamUrl;
                this.headers.put("Referer", this.initialUrl);
                getUrlContent();
                return;
            }
        }
        if (this.nextCount == 4) {
            if (this.isHugo) {
                waitWhileWorking();
                Statics.sheila = this.url;
                oldParserIntegration();
            } else {
                this.url = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.pageContent).get(0);
                oldParserIntegration();
            }
        }
    }
}
