package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Trstx extends Core {
    public Trstx(String str, Context context, ExoPlayer exoPlayer, WebView webView, Core.Language language) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36", true);
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        this.language = language;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Trstx.1
            @Override // java.lang.Runnable
            public void run() {
                Trstx.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        String str;
        super.next();
        if (this.nextCount == 1) {
            try {
                JSONObject jSONObject = new JSONObject(Helper.pregMatchAll("playerConfigs\\s*=\\s*(.*?);", this.pageContent).get(0));
                this.url = jSONObject.getString("href") + jSONObject.getString("file");
                if (!this.url.startsWith("http")) {
                    this.url = "https://" + this.url;
                }
                getUrlContent();
                return;
            } catch (Exception unused) {
                showBuffer();
                showAlert();
                return;
            }
        }
        if (this.nextCount == 2) {
            if (this.language == Core.Language.None) {
                LinkedHashMap<String, String> pregMatchAll = Helper.pregMatchAll("\"title\":\"(.*?)\".*?file\":\"(.*?)\"", this.pageContent, true, false);
                try {
                    this.streamUrls.put("Türkçe Altyazılı", pregMatchAll.get("Altyaz\\u0131"));
                    this.streamUrls.put("Türkçe Dublaj", pregMatchAll.get("Dublaj"));
                    showAlternates();
                    return;
                } catch (Exception unused2) {
                    return;
                }
            }
            next();
            return;
        }
        if (this.nextCount == 3) {
            LinkedHashMap<String, String> pregMatchAll2 = Helper.pregMatchAll("\"title\":\"(.*?)\".*?file\":\"(.*?)\"", this.pageContent, true, false);
            String str2 = "";
            if (pregMatchAll2.size() > 1) {
                for (int r2 = 0; r2 < pregMatchAll2.size(); r2++) {
                    if (this.language == Core.Language.EN) {
                        str = pregMatchAll2.get("Altyaz\\u0131");
                    } else {
                        str = pregMatchAll2.get("Dublaj");
                    }
                    str2 = str;
                }
            } else {
                try {
                    try {
                        str2 = pregMatchAll2.get("Altyaz\\u0131");
                    } catch (Exception unused3) {
                        str2 = pregMatchAll2.get("Dublaj");
                    }
                } catch (Exception unused4) {
                }
            }
            this.url = this.url.split("playlist")[0] + "playlist/" + str2 + "!!.txt";
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            this.url = this.pageContent;
            this.stepType = Core.StepType.Play;
            oldParserIntegration();
        }
    }
}
