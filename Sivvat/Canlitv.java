package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;

/* loaded from: classes3.dex */
public class Canlitv extends Core {
    public Canlitv(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Canlitv.1
            @Override // java.lang.Runnable
            public void run() {
                Canlitv.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            this.url = Helper.pregMatchAll("iframe-player.*?src=[\\\"'](.*?)[\\\"']", this.pageContent).get(0);
            if (this.url.contains("urlcik=")) {
                this.url = Helper.pregMatchAll("urlcik=(.*?)&", this.pageContent).get(0);
                this.url = Helper.decodeBase64(this.url);
            }
            getUrlContent();
            return;
        }
        if (this.nextCount == 2) {
            if (this.pageContent.contains("DM.player")) {
                this.url = "https://dailymotion.com/video/" + Helper.pregMatchAll("video\\s*:\\s*'(.*?)'", this.pageContent).get(0);
                oldParserIntegration();
                return;
            }
            if (this.pageContent.contains("youtube.com/embed")) {
                this.url = Helper.pregMatchAll("iframe.*?src=[\\\"'](.*?)(?:\\?|[\\\"'])", this.pageContent).get(0).replace("youtube", "youtubeiptv");
                oldParserIntegration();
                return;
            }
            if (this.pageContent.contains("atob")) {
                this.url = Helper.pregMatchAll("atob\\(\"(.*?)\"", this.pageContent).get(0);
                this.url = Helper.decodeBase64(this.url);
                oldParserIntegration();
                return;
            } else {
                if (Helper.containsAny(this.pageContent, "eval(function", "<script src=\"")) {
                    this.url = Helper.pregMatchAll("script\\s*src=\\\\\"(.*?)\\\\\">", this.pageContent).get(0);
                    if (this.url.equals("")) {
                        this.url = Helper.pregMatchAll("<script\\s*src=\\s*\"(https://play.canlitv.*?)\"", this.pageContent).get(0);
                    }
                    getUrlContent();
                    return;
                }
                return;
            }
        }
        if (this.nextCount == 3) {
            String str = Helper.pregMatchAll("verianahtar\\s*=\\s*\"(.*?)\"", this.pageContent).get(0);
            this.url = ("https:" + Helper.pregMatchAll("yayincomtr4\\s*=\\s*\"(.*?)\"", this.pageContent).get(0)) + str;
            oldParserIntegration();
        }
    }
}
