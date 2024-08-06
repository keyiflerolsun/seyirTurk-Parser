package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Filmon extends Core {
    public Filmon(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.PostRequest;
        this.uaType = Core.UAType.Classic;
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmon.1
            @Override // java.lang.Runnable
            public void run() {
                Filmon.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            String replace = this.url.replace("https://www.filmon.com/", "");
            this.url = "https://www.filmon.com/ajax/getChannelInfo";
            this.postData = "channel_id=" + replace + "&quality:low";
            this.headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            this.headers.put("Cookie", "PHPSESSID=");
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            try {
                this.url = new JSONObject(this.pageContent).getString("serverURL");
            } catch (Exception unused) {
            }
            oldParserIntegration();
        }
    }
}
