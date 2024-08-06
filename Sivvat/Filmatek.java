package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Filmatek extends Core {
    int selectedPart;
    LinkedList<String> urls;

    public Filmatek(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.selectedPart = 1;
        this.urls = new LinkedList<>();
        String stripToParse = stripToParse(str, false);
        Statics.language = this.language;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Nondroid;
        setUserAgent();
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmatek.1
            @Override // java.lang.Runnable
            public void run() {
                Filmatek.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        int r2 = 0;
        if (this.nextCount == 1) {
            this.urls = Helper.pregMatchAll("(\\d+)\\.\\s*Kısım", this.pageContent);
            while (r2 < this.urls.size()) {
                Map<String, String> map = this.streamUrls;
                StringBuilder sb = new StringBuilder();
                sb.append("Bölüm ");
                int r3 = r2 + 1;
                sb.append(r3);
                map.put(sb.toString(), this.urls.get(r2));
                r2 = r3;
            }
            showAlternates();
            return;
        }
        try {
            if (this.nextCount == 2) {
                for (int r0 = 0; r0 < this.urls.size(); r0++) {
                    if (this.urls.get(r0).equals(this.selectedAlternate)) {
                        this.selectedPart = r0 + 1;
                    }
                }
                String str = Helper.pregMatchAll("layer_api\":\"(.*?)\",\"play_aj", this.pageContent).get(0);
                this.url = str.replace("\\", "") + Helper.pregMatchAll("data-post='(\\d+)'", this.pageContent).get(0) + "/movie/" + this.selectedPart;
                getUrlContent();
                return;
            }
            if (this.nextCount == 3) {
                this.url = new JSONObject(this.pageContent).getString("embed_url");
                getUrlContent();
            } else if (this.nextCount == 4) {
                this.url = Helper.pregMatchAll("\"file\"\\s*:\\s*\"(.*?)\"", this.pageContent).get(0).replace("\\", "");
                this.stepType = Core.StepType.Play;
                oldParserIntegration();
            }
        } catch (Exception unused) {
        }
    }
}
