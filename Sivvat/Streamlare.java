package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import java.net.URLEncoder;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Streamlare extends Core {
    String api_durl;
    String api_surl;

    public Streamlare(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.api_durl = "";
        this.api_surl = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.JavaParse;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Streamlare.1
            @Override // java.lang.Runnable
            public void run() {
                Streamlare.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            String str = Helper.pregMatchAll("/(?:e|v)/([0-9A-Za-z]+)", this.url).get(0);
            this.api_durl = this.root + "/api/video/download/get";
            this.api_surl = this.root + "/api/video/stream/get";
            this.headers.clear();
            this.headers.put("Referer", this.root);
            this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            try {
                this.postData = "id=" + URLEncoder.encode(str, StringUtil.__UTF8);
            } catch (Exception unused) {
            }
            this.url = this.api_surl;
            this.parserType = Core.ParserType.PostRequest;
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            this.url = Helper.pregMatchAll("\"file\":\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
            if (this.url.equals("")) {
                this.url = this.api_durl;
                getUrlContent();
                return;
            } else {
                next();
                return;
            }
        }
        if (this.nextCount == 3) {
            if (this.url.equals(this.api_durl)) {
                try {
                    this.url = new JSONObject(this.pageContent).getJSONObject("result").getJSONObject("Original").getString("url");
                } catch (Exception unused2) {
                }
            }
            if (this.url.contains("?token=")) {
                getUrlContent();
                return;
            } else {
                next();
                return;
            }
        }
        if (this.nextCount == 4) {
            oldParserIntegration();
        }
    }
}
