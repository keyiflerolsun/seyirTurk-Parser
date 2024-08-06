package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Wikiflix extends Core {
    String id;

    public Wikiflix(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2) {
        super(str, context, exoPlayer, webView);
        this.id = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.headers.put("Referer", stripToParse);
        this.id = str2 + "_wikiflix";
        this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Wikiflix.1
            @Override // java.lang.Runnable
            public void run() {
                Wikiflix.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        int r1 = 0;
        if (this.nextCount == 1) {
            this.url.split("#");
            String[] split = this.url.split("\\?")[1].split("&");
            if (split.length > 1) {
                while (r1 < split.length) {
                    this.streamUrls.put("Kaynak - " + r1, split[r1]);
                    r1++;
                }
                showAlternates();
                return;
            }
            this.selectedAlternate = split[0];
            next();
            return;
        }
        if (this.nextCount == 2) {
            if (this.selectedAlternate.length() == 11) {
                this.url = "https://www.youtube.com/watch?v=" + this.selectedAlternate;
                oldParserIntegration();
                return;
            }
            this.url = "https://commons.m.wikimedia.org/wiki/File:" + this.selectedAlternate + "?embedplayer=yes";
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            this.url = Helper.pregMatchAll("(https[^\"]+vp9.webm)", this.pageContent).get(0);
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("/w/[^\"]+vtt", this.pageContent);
            if (pregMatchAll.size() > 0) {
                while (r1 < pregMatchAll.size()) {
                    if (pregMatchAll.get(r1).contains("lang=en")) {
                        translateSubFromUrl("https://commons.m.wikimedia.org" + pregMatchAll.get(r1).replace("amp;", ""), this.id);
                        waitWhileWorking();
                    }
                    r1++;
                }
            }
            this.stepType = Core.StepType.Play;
            oldParserIntegration();
        }
    }
}
