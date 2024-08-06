package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Filmekseni extends Core {
    public Filmekseni(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmekseni.1
            @Override // java.lang.Runnable
            public void run() {
                Filmekseni.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.pageContent = Helper.pregMatchAll("tab-pane active(.*?)</nav>", this.pageContent).get(0);
            this.streamUrls = Helper.pregMatchAll("href=\"(.*?)\">\\s*(.*?)\\s*</a>", this.pageContent, false, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            this.url = Helper.pregMatchAll("iframe.*?data-src=\"(.*?)\"", this.pageContent).get(0);
            if (this.url.contains("vidload.one")) {
                String valueOf = String.valueOf(System.currentTimeMillis() * 1000);
                this.url = "https://vidload.one/ajax/" + this.url.split("/")[this.url.split("/").length - 1] + "?" + valueOf;
                HashMap hashMap = new HashMap();
                StringBuilder sb = new StringBuilder();
                sb.append("https://vidload.one/video.js?");
                sb.append(valueOf);
                String getRequestContent = Helper.getGetRequestContent(hashMap, sb.toString());
                this.headers.put(Helper.pregMatchAll("window,'(.*?)','.*?','.*?'", getRequestContent).get(0), Helper.pregMatchAll("window,'.*?','(.*?)','.*?'", getRequestContent).get(0));
                getUrlContent();
                return;
            }
            this.headers.put("Referer", this.initialUrl);
            oldParserIntegration();
            return;
        }
        if (this.nextCount == 4) {
            try {
                JSONObject jSONObject = new JSONObject(this.pageContent);
                this.url = jSONObject.getString("file") + "?" + jSONObject.getString("hash");
                this.url = Helper.getRedirectUrl(this.url);
                headersClear();
                this.headers.put("Referer", "https://vidload.one/");
            } catch (Exception unused) {
            }
            oldParserIntegration();
        }
    }
}
