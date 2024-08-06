package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class Dizibox extends Core {
    boolean isHugo;

    public Dizibox(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.isHugo = false;
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse;
        this.lookingFor = "woca-linkpages";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizibox.1
            @Override // java.lang.Runnable
            public void run() {
                Dizibox.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        String str = "";
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.pageContent = Helper.pregMatchAll("woca-linkpages-dd selectBox(.*?)/select", this.pageContent).get(0);
            this.streamUrls = Helper.pregMatchAll("(?:value=[\"']|href=[\"'])(.*?)[\"'].*?>(.*?)<", this.pageContent, false, true);
            this.streamUrls.remove("King");
            this.streamUrls.remove("Play");
            this.streamUrls.remove("");
            if (this.streamUrls.size() == 0) {
                this.streamUrls.put(Helper.pregMatchAll("selected=.*?>(.*?)</option>", this.pageContent).get(0), this.url);
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            saveCookieToServer("dbx");
            for (Map.Entry<String, String> entry : this.streamUrls.entrySet()) {
                if (entry.getValue().equals(this.selectedAlternate)) {
                    str = entry.getKey();
                }
            }
            this.streamUrls.clear();
            this.url = this.selectedAlternate;
            this.parserType = Core.ParserType.GetRequest;
            if (str.toLowerCase().contains("pro")) {
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.lookingFor = "molystream";
                this.lookingForEmbed = "I AM NOT LOOKING";
                this.clickType = 0;
                this.isHugo = true;
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            if (this.isHugo) {
                waitWhileWorking();
                new HashMap(this.headers).put("bety", "jughead");
                this.lookingFor = "molystream";
                this.clickType = 0;
                this.toClick = Helper.getGetRequestContent(this.headers, Statics.SERVER + "sey/v2/toClickFor.php?type=dbx");
                this.checkMedia = true;
            } else {
                this.parserType = Core.ParserType.GetRequest;
                this.url = Helper.pregMatchAll("frame.*?src=\"(.*?)\".*?webkitallowfullscreen", this.pageContent).get(0);
                this.selectedAlternate = this.url;
                headersClear();
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root));
                this.headers.put("Referer", this.root);
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            if (this.isHugo) {
                waitWhileWorking();
                Statics.sheila = this.url;
                oldParserIntegration();
                return;
            }
            if (Helper.containsAny(this.selectedAlternate, "haydi.php")) {
                this.url = Helper.pregMatchFilter("<iframe(.*?)</iframe", this.pageContent, "display:", true);
                this.url = Helper.pregMatchAll("src=['\"](.*?)['\"]", this.url).get(0);
                this.streamUrl = this.url;
            } else if (Helper.containsAny(this.selectedAlternate, "moly.php")) {
                this.url = Helper.pregMatchFilter("unescape\\(\"(.*?)\"\\)", this.pageContent, "display:", true);
                try {
                    this.url = URLDecoder.decode(this.url, "UTF-8");
                    this.url = Helper.decodeBase64(this.url);
                    this.url = Helper.pregMatchFilter("<iframe(.*?)</iframe", this.url, "display:", true);
                    this.url = Helper.pregMatchAll("src=['\"](.*?)['\"]", this.url).get(0);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                this.streamUrl = this.url;
            }
            oldParserIntegration();
        }
    }
}
