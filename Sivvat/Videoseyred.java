package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class Videoseyred extends Core {
    public Videoseyred(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Videoseyred.1
            @Override // java.lang.Runnable
            public void run() {
                Videoseyred.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.pageContent = Helper.pregMatchAll("playlistUrl='(.*?)'", this.pageContent).get(0);
            this.url = "https://videoseyred.in" + this.pageContent;
            this.headers.put("Referer", this.initialUrl);
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            try {
                JSONArray jSONArray = new JSONArray(this.pageContent);
                this.url = jSONArray.getJSONObject(0).getJSONArray("sources").getJSONObject(0).getString("file");
                JSONArray jSONArray2 = jSONArray.getJSONObject(0).getJSONArray("tracks");
                for (int r4 = 0; r4 < jSONArray2.length(); r4++) {
                    if (jSONArray2.getJSONObject(r4).has(HtmlLabel.TAG_NAME) && jSONArray2.getJSONObject(r4).getString(HtmlLabel.TAG_NAME).contains("T")) {
                        this.subtitle = jSONArray2.getJSONObject(r4).getString("file");
                    }
                }
            } catch (Exception unused) {
            }
            oldParserIntegration();
        }
    }
}
