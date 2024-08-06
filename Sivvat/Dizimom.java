package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class Dizimom extends Core {
    public Dizimom(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.mainUrlForReferer = stripToParse;
        this.url = stripToParse;
        this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
        this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizimom.1
            @Override // java.lang.Runnable
            public void run() {
                Dizimom.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("<p><iframe.*?src=\"(.*?(?:player|embed|video|ok.ru|suhiaza).*?)\".*?allowfullscreen", this.pageContent);
            this.headers.put("Referer", this.mainUrlForReferer);
            if (pregMatchAll.size() > 0) {
                this.url = pregMatchAll.get(0);
                getUrlContent();
                return;
            }
            return;
        }
        if (this.nextCount == 2) {
            if (this.url.contains("hdmomplayer")) {
                String str = Helper.pregMatchAll("bePlayer\\('(.*?)',\\s*'.*?'\\)", this.pageContent).get(0);
                String str2 = Helper.pregMatchAll("bePlayer\\('.*?',\\s*'(.*?)'\\)", this.pageContent).get(0);
                headersClear();
                this.headers.put("Referer", this.url);
                this.headers.put("Accept", "*/*");
                try {
                    this.url = Statics.getUrlContent("https://rootcheck.tk/parsers/dizimom/hdmomplayer.php?v1=" + URLDecoder.decode(str, "UTF-8") + "&v2=" + URLDecoder.decode(str2, "UTF-8"));
                    this.stepType = Core.StepType.Play;
                    oldParserIntegration();
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            String str3 = this.url;
            String[] split = str3.split("/");
            String str4 = split[split.length - 1];
            if (str4.contains("data=")) {
                String[] split2 = str4.split("=");
                str4 = split2[split2.length - 1];
            }
            if (Helper.containsAny(str3, "videoseyred", "vidmoly", "ok.ru", "suhiaza")) {
                if (str3.startsWith("//")) {
                    str3 = "https:" + str3;
                }
                this.url = str3;
                oldParserIntegration();
                return;
            }
            if (str3.contains("hdmomplayer")) {
                this.headers.put("Referer", this.initialUrl);
                this.url = str3;
                getUrlContent();
                return;
            }
            if (Helper.containsAny(str3, "/v/")) {
                if (str3.startsWith("//")) {
                    str3 = "https:" + str3;
                }
                this.url = str3;
                oldParserIntegration();
                return;
            }
            this.url = str3 + (str3.contains("?") ? "&" : "?") + "do=getVideo";
            this.parserType = Core.ParserType.PostRequest;
            try {
                this.postData = "hash=" + str4 + "&r=" + URLEncoder.encode(this.initialUrl, StringUtil.__UTF8);
            } catch (Exception unused2) {
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            if (this.parserType == Core.ParserType.PostRequest) {
                this.url = Helper.pregMatchAll("file\":\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
                if (this.url.equals("")) {
                    this.url = Helper.pregMatchAll("\"securedLink\":\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
                }
            } else {
                this.url = Helper.pregMatchAll("file\\s*:\\s*\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
            }
            oldParserIntegration();
        }
    }
}
