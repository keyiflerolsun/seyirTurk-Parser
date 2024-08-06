package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Dizilla extends Core {
    public Dizilla(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.headers.put("Referer", stripToParse);
        this.headers.put("Accept", "*/*");
        this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Dizilla.1
            @Override // java.lang.Runnable
            public void run() {
                Dizilla.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            String str = Helper.pregMatchAll("Player(.*?)Dublaj", this.pageContent).get(0);
            LinkedList linkedList = new LinkedList();
            linkedList.add("pub+");
            linkedList.add("vidmoly");
            linkedList.add("pub");
            this.streamUrls = Helper.pregMatchFilterList("<a.*?href=\"(.*?)\".*?>.*?<span.*?>\\s*(.*?)\\s*<\\/span>", str, false, true, linkedList);
            if (this.streamUrls.size() > 1) {
                showAlternates();
                return;
            }
            String str2 = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.pageContent).get(0);
            if (!str2.startsWith("https")) {
                str2 = "https:" + str2;
            }
            if (str2.contains("dizilla.org/vmplayer")) {
                str2 = "https://vidmoly.to/embed-" + str2.split("\\?vid=")[1] + ".html";
            }
            this.url = str2;
            oldParserIntegration();
            return;
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            String str3 = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\".*?allowfullscreen.*?</iframe>", this.pageContent).get(0);
            if (!str3.startsWith("https")) {
                str3 = "https:" + str3;
            }
            if (str3.contains("dizilla.org/vmplayer")) {
                str3 = "https://vidmoly.to/embed-" + str3.split("\\?vid=")[1] + ".html";
            }
            this.url = str3;
            oldParserIntegration();
        }
    }
}
