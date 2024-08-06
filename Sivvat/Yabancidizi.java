package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class Yabancidizi extends Core {
    boolean isHugo;

    public Yabancidizi(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.isHugo = false;
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse;
        this.lookingFor = "series-tabs";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Yabancidizi.1
            @Override // java.lang.Runnable
            public void run() {
                Yabancidizi.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.pageContent = Helper.pregMatchAll("series-tabs(.*?)mofycon", this.pageContent).get(0);
            this.streamUrls = Helper.pregMatchAll("data-eid=\"(.*?)\"\\s*data-type=\"(.*?)\"", this.pageContent, false, true);
            String str = this.language == Core.Language.EN ? "2" : "1";
            if (this.streamUrls.containsKey(str)) {
                String str2 = this.streamUrls.get(str);
                try {
                    str2 = URLEncoder.encode(str2, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                this.postData = "lang=" + str + "&episode=" + str2 + "&type=langTab";
                this.parserType = Core.ParserType.PostRequest;
                StringBuilder sb = new StringBuilder();
                sb.append(this.root);
                sb.append("/ajax/service");
                this.url = sb.toString();
                headersClear();
                this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root));
                this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                this.headers.put("Referer", this.root);
                getUrlContent();
                return;
            }
            return;
        }
        String str3 = "";
        if (this.nextCount == 2) {
            saveCookieToServer("ybx");
            this.streamUrls.clear();
            for (Map.Entry<String, String> entry : Helper.pregMatchAll("data-hash=\\\\\"(.*?)\\\\\"\\s*data-link=\\\\\"(.*?)\\\\\"", this.pageContent, true, true).entrySet()) {
                try {
                    this.postData = "hash=" + entry.getKey().replace("\\", "") + "&link=" + URLEncoder.encode(entry.getValue().replace("\\", ""), StringUtil.__UTF8) + "&querytype=alternate&type=videoGet";
                    getPostRequestContent();
                    String replace = Helper.pregMatchAll("\"api_iframe\":\\s*\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
                    if (!replace.equals("")) {
                        this.streamUrls.put(Helper.pregMatchAll("/api/(.*?)/", replace).get(0), replace);
                    }
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 3) {
            for (Map.Entry<String, String> entry2 : this.streamUrls.entrySet()) {
                if (entry2.getValue().equals(this.selectedAlternate)) {
                    str3 = entry2.getKey();
                }
            }
            this.streamUrls.clear();
            if (str3.contains("drive")) {
                this.url = stripToParse(this.initialUrl, true);
                headersClear();
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.lookingFor = "molystream";
                this.lookingForEmbed = "I AM NOT LOOKING";
                this.clickType = 0;
                this.isHugo = true;
            } else {
                this.parserType = Core.ParserType.PostRequest;
                headersClear();
                this.headers.put("Referer", this.root);
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root));
                this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                this.url = this.selectedAlternate;
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            if (!this.isHugo) {
                if (Helper.containsAny(this.selectedAlternate, "/drive", "/moly", "/ruplay", "/saru", "/goo", "/superv")) {
                    this.url = Helper.pregMatchFilter("<iframe(.*?)</iframe", this.pageContent, "display: none", true);
                    this.url = Helper.pregMatchAll("src=['\"](.*?)['\"]", this.url).get(0);
                    this.streamUrl = this.url;
                    oldParserIntegration();
                    return;
                }
                return;
            }
            waitWhileWorking();
            this.lookingFor = "molystream";
            this.clickType = 0;
            this.toClick = Helper.getGetRequestContent(this.headers, Statics.SERVER + "sey/v2/toClickFor.php?type=sfx");
            this.checkMedia = true;
            getUrlContent();
            return;
        }
        if (this.nextCount == 5) {
            waitWhileWorking();
            Statics.sheila = this.url;
            oldParserIntegration();
        }
    }
}
