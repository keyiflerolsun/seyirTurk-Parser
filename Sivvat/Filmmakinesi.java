package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.net.URL;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Filmmakinesi extends Core {
    public Filmmakinesi(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        Statics.language = this.language;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Nondroid;
        setUserAgent();
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmmakinesi.1
            @Override // java.lang.Runnable
            public void run() {
                Filmmakinesi.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        String str;
        super.next();
        if (this.nextCount == 1) {
            this.url = Helper.pregMatchAll("php.*?(https://closeload.filmmakinesi.*?embed.*?) ", this.pageContent).get(0);
            if (this.url.equals("")) {
                this.url = Helper.pregMatchAll("iframe.*?data-src=\"(.*?)\"", this.pageContent).get(0);
                oldParserIntegration();
                return;
            } else {
                getUrlContent();
                return;
            }
        }
        if (this.nextCount == 2) {
            try {
                str = "https://closeload." + new URL(this.initialUrl).getHost();
            } catch (Exception unused) {
                str = "https://closeload.filmmakinesi.film";
            }
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("<track src=\"(.*?)\"\\s*kind=\"captions\"", this.pageContent);
            int r3 = 0;
            while (true) {
                if (r3 >= pregMatchAll.size()) {
                    break;
                }
                if (pregMatchAll.get(r3).contains(HtmlTableRow.TAG_NAME)) {
                    this.subtitle = str + "/" + pregMatchAll.get(r3);
                    break;
                }
                r3++;
            }
            this.pageContent = Helper.pregMatchAll("(eval.*?)</script>", this.pageContent).get(0);
            this.pageContent = JSUnpacker.Unpack(this.pageContent);
            this.url = Helper.pregMatchAll("(aHR0cH.*?)\"", this.pageContent).get(0);
            this.url = Helper.decodeBase64(this.url);
            headersClear();
            this.headers.put("Referer", str);
            oldParserIntegration();
        }
    }
}
