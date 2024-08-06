package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Dizirix extends Core {
    public Dizirix(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizirix.1
            @Override // java.lang.Runnable
            public void run() {
                Dizirix.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            String str = Helper.pregMatchAll("var\\s*bID\\s*=\\s*\"(\\d*)\"", this.pageContent).get(0);
            this.postData = "whichOne=" + (this.language == Core.Language.EN ? "altyazili" : "dublaj") + "&bId=" + str;
            this.parserType = Core.ParserType.PostRequest;
            StringBuilder sb = new StringBuilder();
            sb.append(this.root);
            sb.append("/ajax/request=getLanguage");
            this.url = sb.toString();
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            this.streamUrls = Helper.pregMatchAll("data-code=\\\\\\\"(.*?)\\\\\\\".*?span>(.*?)<", this.pageContent, false, true);
            this.parserType = Core.ParserType.PostRequest;
            this.url = this.root + "/ajax/request=getFrames";
            showAlternates();
            return;
        }
        if (this.nextCount == 3) {
            this.postData = "dataCode=" + this.selectedAlternate;
            this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            this.url = Helper.pregMatchAll("src=\"(.*?)\"", Helper.decodeBase64(this.pageContent)).get(0);
            headersClear();
            if (Helper.containsAny(this.url, "prixy.php", "source=rix2", "source-yg", "proxy.php")) {
                this.headers.put(HttpHeader.REFERER_LC, this.initialUrl);
                this.headers.put("upgrade-insecure-requests", "1");
                this.parserType = Core.ParserType.GetRequest;
                getUrlContent();
                return;
            }
            oldParserIntegration();
            return;
        }
        if (this.nextCount == 5) {
            String str2 = "file:\"(.*?)\"";
            this.url = Helper.pregMatchAll("file:\"(.*?)\"", this.pageContent).get(0);
            if (this.pageContent.contains("Back") && this.url != null && this.url.equals("")) {
                str2 = "Back.*?'(https.*?)'";
            }
            headersClear();
            this.headers.put("Referer", this.url);
            this.headers.put("Origin", this.root);
            this.url = Helper.pregMatchAll(str2, this.pageContent).get(0);
            this.streamUrl = this.url;
            this.subtitle = Helper.pregMatchAll("\"file\":.*?\"(.*?)\"", this.pageContent).get(0);
            prepareVideo();
        }
    }
}
