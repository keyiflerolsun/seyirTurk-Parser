package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Videobuzz extends Core {
    String timeStr;

    public Videobuzz(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.timeStr = "";
        stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Classic;
        this.timeStr = String.valueOf(System.currentTimeMillis() * 1000);
        this.headers.put("Referer", this.root);
        this.url = this.root + "/video.js?" + this.timeStr;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Videobuzz.1
            @Override // java.lang.Runnable
            public void run() {
                Videobuzz.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            String str = Helper.pregMatchAll("window,[\"\\'](.*?)[\"\\'],[\"\\'].*?[\"\\']", this.pageContent).get(0);
            String str2 = Helper.pregMatchAll("window,[\"\\'].*?[\"\\'],[\"\\'](.*?)[\"\\']", this.pageContent).get(0);
            headersClear();
            this.headers.put(str, str2);
            this.headers.put("Referer", this.initialUrl);
            String[] split = this.initialUrl.split("iframe/");
            this.url = "https://vidload.one/ajax/" + split[split.length - 1] + "?" + this.timeStr;
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            try {
                JSONObject jSONObject = new JSONObject(this.pageContent);
                this.url = jSONObject.getString("file") + "?" + jSONObject.getString("hash");
            } catch (Exception unused) {
            }
            oldParserIntegration();
        }
    }
}
