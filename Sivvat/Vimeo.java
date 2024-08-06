package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Vimeo extends Core {
    public Vimeo(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Vimeo.1
            @Override // java.lang.Runnable
            public void run() {
                Vimeo.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            try {
                this.url = new JSONObject(Helper.pregMatchAll("playerConfig\\s*=\\s*(\\{.*?\\})<", this.pageContent).get(0)).getJSONObject("request").getJSONObject("files").getJSONObject("hls").getJSONObject("cdns").getJSONObject("akfire_interconnect_quic").getString("url");
            } catch (Exception unused) {
            }
            this.stepType = Core.StepType.Play;
            oldParserIntegration();
        }
    }
}
