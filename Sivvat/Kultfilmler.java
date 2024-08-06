package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Kultfilmler extends Core {
    List<String> match;
    List<String> match2;

    public Kultfilmler(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Kultfilmler.1
            @Override // java.lang.Runnable
            public void run() {
                Kultfilmler.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        int r3 = 0;
        if (this.nextCount == 1) {
            this.pageContent = Helper.pregMatchAll("player-control(.*?)button\\s*report-button\\s*trigger", this.pageContent).get(0);
            for (Map.Entry<String, String> entry : Helper.pregMatchAll("part-name\">(.*?)<.*?\"part-lang\">(.*?)</div", this.pageContent, true, true).entrySet()) {
                String str = r3 == 0 ? this.url : this.url + (r3 + 1) + "/";
                if ((entry.getValue().contains(this.language == Core.Language.TR ? HtmlTableRow.TAG_NAME : "cc") || (this.language != Core.Language.TR && entry.getValue().length() < 2)) && !entry.getKey().contains("KULTPlayer")) {
                    this.streamUrls.put(entry.getKey(), str);
                }
                r3++;
            }
            if (this.streamUrls.size() == 0) {
                this.streamUrls.put("Kaynak 1", this.url);
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            this.url = Helper.pregMatchAll("window\\.atob\\(\"(.*?)\"", this.pageContent).get(0);
            this.url = Helper.decodeBase64(this.url);
            this.url = Helper.pregMatchAll("iframe.*?src=\"(.*?)\"", this.url).get(0);
            if (!this.url.startsWith("http")) {
                this.url = "https:" + this.url;
            }
            if (this.url.contains("yildizkisafilm")) {
                String str2 = this.url.split("/")[this.url.split("/").length - 1];
                String str3 = "https://yildizkisafilm.org/player/index.php?data=" + str2 + "&do=getVideo";
                String str4 = "hash=" + str2 + "&r=https%3A%2F%2Fkultfilmler.com%2F";
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                try {
                    this.url = new JSONObject(Helper.getUrlContentPost(hashMap, str3, str4)).getString("securedLink");
                    oldParserIntegration();
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            oldParserIntegration();
        }
    }
}
