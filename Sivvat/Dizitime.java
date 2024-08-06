package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.util.LinkedList;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class Dizitime extends Core {
    boolean isHugo;

    public Dizitime(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.isHugo = false;
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse;
        this.lookingFor = "data-name=";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizitime.1
            @Override // java.lang.Runnable
            public void run() {
                Dizitime.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.streamUrls = Helper.pregMatchAll("data-name=\"(.*?)\"\\s*data-oid=\"(.*?)\"", this.pageContent, true, true);
            showAlternates();
            return;
        }
        String str = "";
        if (this.nextCount == 2) {
            saveCookieToServer("dtx");
            for (Map.Entry<String, String> entry : this.streamUrls.entrySet()) {
                if (entry.getValue().equals(this.selectedAlternate)) {
                    str = entry.getKey();
                }
            }
            if (str.toLowerCase().contains("time")) {
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.lookingFor = "molystream";
                this.lookingForEmbed = "I AM NOT LOOKING";
                this.clickType = 0;
                this.isHugo = true;
            } else {
                this.streamUrls.clear();
                headersClear();
                this.headers.put("Referer", this.root);
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root) + "; dtys=");
                this.url = this.root + "/play/" + this.selectedAlternate;
                this.parserType = Core.ParserType.GetRequest;
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            if (this.isHugo) {
                waitWhileWorking();
                this.lookingFor = "molystream";
                this.clickType = 0;
                this.toClick = Helper.getGetRequestContent(this.headers, Statics.SERVER + "sey/v2/toClickFor.php?type=dtx");
                this.checkMedia = true;
                getUrlContent();
                return;
            }
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("document\\.write\\(atob\\(unescape\\(\"(.*?)\"\\)\\)\\);", this.pageContent);
            String str2 = "";
            for (int r1 = 0; r1 < pregMatchAll.size(); r1++) {
                str2 = str2 + Helper.decodeBase64(pregMatchAll.get(r1));
            }
            this.pageContent = Helper.pregMatchAll("<iframe.*?src=[\",'](.*?)[\",']", str2).get(0);
            this.pageContent = this.pageContent.replace(":", "").replace("&#", "");
            this.pageContent = this.pageContent.trim();
            this.pageContent = this.pageContent.replace(";", StringUtils.SPACE);
            String[] split = this.pageContent.split(StringUtils.SPACE);
            this.pageContent = "";
            try {
                for (String str3 : split) {
                    this.pageContent += ((char) Integer.parseInt(str3));
                }
            } catch (Exception unused) {
                this.url = "";
            }
            if (this.pageContent.contains("getvideo")) {
                this.url = this.pageContent;
                this.parserType = Core.ParserType.GetRequest;
                getUrlContent();
                return;
            } else {
                this.url = this.pageContent;
                oldParserIntegration();
                return;
            }
        }
        if (this.nextCount == 4) {
            if (this.isHugo) {
                waitWhileWorking();
                Statics.sheila = this.url;
                oldParserIntegration();
                return;
            } else {
                this.pageContent = Helper.pregMatchAll("sources\\s*:\\s*\\[(.*?)\\]", this.pageContent).get(0);
                this.streamUrls = Helper.pregMatchAll("file:\"(.*?)\"(?:\\}|,label:\"(.*?)\")", this.pageContent, false, true);
                headersClear();
                showAlternates();
                return;
            }
        }
        if (this.nextCount == 5) {
            this.url = this.selectedAlternate;
            headersClear();
            this.headers.put("Referer", "https://vidmoly.to/");
            oldParserIntegration();
        }
    }
}
