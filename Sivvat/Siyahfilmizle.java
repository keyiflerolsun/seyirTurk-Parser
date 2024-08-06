package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class Siyahfilmizle extends Core {
    public Siyahfilmizle(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Nondroid;
        headersClear();
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Siyahfilmizle.1
            @Override // java.lang.Runnable
            public void run() {
                Siyahfilmizle.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            Matcher matcher = Pattern.compile("<li><span.*?href=\"(.*?)\".*?i class=\"(.*?)\"></i>(.*?)<", 32).matcher(this.pageContent);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                if ((this.language == Core.Language.EN && group2.contains("en")) || (this.language == Core.Language.TR && group2.contains(HtmlTableRow.TAG_NAME))) {
                    this.streamUrls.put(group3, group);
                }
            }
            if (this.streamUrls.size() == 0) {
                this.nextCount = 2;
                next();
                return;
            } else {
                showAlternates();
                return;
            }
        }
        if (this.nextCount == 2) {
            this.url = this.selectedAlternate;
            getUrlContent();
        } else if (this.nextCount == 3) {
            this.url = Helper.pregMatchAll("iframe.*?data-src=[\"\\'](.*?)[\"\\']", this.pageContent).get(0).replace("#038;", "");
            if (!this.url.startsWith("http")) {
                this.url = "https:" + this.url;
            }
            oldParserIntegration();
        }
    }
}
