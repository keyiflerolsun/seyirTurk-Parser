package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class VKcom extends Core {
    public VKcom(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.VKcom.1
            @Override // java.lang.Runnable
            public void run() {
                VKcom.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        this.pageContent = Helper.pregMatchAll("var\\s*playerParams\\s*=\\s*(\\{.*?\\});", this.pageContent).get(0);
        try {
            this.url = new JSONObject(this.pageContent).getJSONArray("params").getJSONObject(0).getString("hls_ondemand");
            this.streamUrl = this.url;
        } catch (Exception unused) {
        }
        prepareVideo();
    }
}
