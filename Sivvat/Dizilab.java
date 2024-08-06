package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public class Dizilab extends Core {
    boolean isHugo;
    String toParse;

    public Dizilab(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.isHugo = false;
        this.toParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.lookingFor = "span class=\\\"title\\\">";
        this.reloadFor = 3;
        this.uaType = Core.UAType.Nondroid;
        this.headers.put("Referer", this.toParse);
        this.url = "https://dizilab.com";
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizilab.1
            @Override // java.lang.Runnable
            public void run() {
                Dizilab.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        String str;
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            saveCookieToServer("dz_lb");
            this.parserType = Core.ParserType.GetRequest;
            this.url = this.toParse;
            getUrlContent();
            return;
        }
        String str2 = "";
        if (this.nextCount == 2) {
            if (this.pageContent.contains("mobilembeds")) {
                str = Helper.pregMatchAll("mobilembeds(.*?)Sonra", this.pageContent).get(0);
            } else {
                str = Helper.pregMatchAll("fa fa-angle-down(.*?)Sonra", this.pageContent).get(0);
            }
            if (!Objects.equals(str, "")) {
                this.streamUrls = Helper.pregMatchAll("loadVideo\\(\\'(.*?)\\'.*?<\\/span>(.*?)\\s*<\\/a>", str, false, true);
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 3) {
            for (Map.Entry<String, String> entry : this.streamUrls.entrySet()) {
                if (entry.getValue().equals(this.selectedAlternate)) {
                    str2 = entry.getKey();
                }
            }
            if (str2.toLowerCase().contains("vip")) {
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.lookingFor = "molystream";
                this.lookingForEmbed = "I AM NOT LOOKING";
                this.clickType = 0;
                this.isHugo = true;
            } else {
                this.url = this.root + "/request/php/";
                this.parserType = Core.ParserType.PostRequest;
                try {
                    this.postData = "vid=" + URLEncoder.encode(this.selectedAlternate, "UTF-8") + "&tip=1&type=loadVideo";
                } catch (Exception unused) {
                }
                headersClear();
                this.headers.put("Referer", this.root);
                this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            if (this.isHugo) {
                waitWhileWorking();
                this.lookingFor = "molystream";
                this.clickType = 0;
                this.toClick = Helper.getGetRequestContent(this.headers, Statics.SERVER + "sey/v2/toClickFor.php?type=dlx");
                this.checkMedia = true;
            } else {
                this.url = Helper.pregMatchAll("src=\\\\\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
                this.url = this.root + this.url;
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root));
                System.out.println("Print");
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 5) {
            if (this.isHugo) {
                waitWhileWorking();
                Statics.sheila = this.url;
                oldParserIntegration();
            } else {
                this.url = Helper.pregMatchAll("src=\"(.*?)\"", this.pageContent).get(0);
                if (this.url.contains("ok.ru")) {
                    oldParserIntegration();
                } else {
                    oldParserIntegration();
                }
            }
        }
    }
}
