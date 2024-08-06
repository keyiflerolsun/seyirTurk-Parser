package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes3.dex */
public class Filmizlesene extends Core {
    public Filmizlesene(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse;
        this.lookingFor = "vidcontainer";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Filmizlesene.1
            @Override // java.lang.Runnable
            public void run() {
                Filmizlesene.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        int r2 = 0;
        int r3 = 1;
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            String str = Helper.pregMatchAll("(?:inepisode|bolumler)(.*?)vidcontainer", this.pageContent).get(0);
            if (str.equals("")) {
                String str2 = Helper.pregMatchAll("iframe\\s*src=\"(.*?)\"", this.pageContent).get(0);
                if (str2.contains("/ok/")) {
                    this.streamUrl = "https://odnoklassniki.ru/videoembed/" + Helper.decodeBase64(str2.split("\\?v=")[1]);
                    oldParserIntegration();
                    return;
                }
                return;
            }
            this.pageContent = str;
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("dil=\"(.*?)\".*?style.*?>.*?<.*?iframe\\s*src=\".*?\"", this.pageContent);
            for (Map.Entry<String, String> entry : Helper.pregMatchAll("dil=\".*?\">(.*?)<.*?iframe\\s*src=\"(.*?)\"", this.pageContent, true, true).entrySet()) {
                if (pregMatchAll.get(r2).contains("trd")) {
                    this.streamUrls.put(entry.getKey() + ",trd", entry.getValue());
                } else if (pregMatchAll.get(r2).contains("tra")) {
                    this.streamUrls.put(entry.getKey() + ",tra", entry.getValue());
                }
                r2++;
            }
            this.streamUrls.remove("opn");
            this.streamUrls.remove("up");
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            saveCookieToServer("flmizl");
            this.streamUrls.clear();
            this.url = this.selectedAlternate;
            if (this.url.contains("mail.ru")) {
                this.url = this.selectedAlternate;
                oldParserIntegration();
                return;
            } else {
                if (this.url.contains("vidmoly")) {
                    this.url = Helper.pregMatchAll("vid=(.*?)$", this.url).get(0);
                    oldParserIntegration();
                    return;
                }
                this.parserType = Core.ParserType.GetRequest;
                headersClear();
                this.headers.put("Cookie", Statics.cookieManager.getCookie(this.root));
                this.headers.put("Referer", this.root);
                getUrlContent();
                return;
            }
        }
        if (this.nextCount == 3) {
            this.pageContent = Helper.pregMatchAll("iframe\\s*src=(?:\\'|\")(.*?)(?:\\'|\")", this.pageContent).get(0);
            if (Helper.containsAny(this.pageContent, "hdplayer", "vidmo")) {
                getUrlContent();
                return;
            } else {
                this.url = this.pageContent;
                oldParserIntegration();
                return;
            }
        }
        if (this.nextCount >= 4 && this.nextCount <= 14) {
            this.pageContent = Helper.pregMatchAll("iframe.*?src\\s*=\\s*\"(.*?)\"", this.pageContent).get(0);
            if (this.pageContent.contains("odnoklass")) {
                this.url = this.pageContent;
                r2 = 1;
            }
            if (this.pageContent.contains("hdplayer")) {
                r3 = r2;
            } else {
                this.url = this.pageContent + "/sheila";
                headersClear();
                this.headers.put("Referer", this.pageContent);
            }
            if (r3 != 0) {
                oldParserIntegration();
                return;
            } else {
                this.url = this.pageContent;
                getUrlContent();
                return;
            }
        }
        this.url = "";
        oldParserIntegration();
    }
}
