package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class Sto extends Core {
    public Sto(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        trSubLogin();
        if (stripToParse.contains("/serie/")) {
            this.s = str2;
            this.e = str3;
            this.isTv = true;
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Sto.1
            @Override // java.lang.Runnable
            public void run() {
                Sto.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            Matcher matcher = Pattern.compile("data-lang-key=\"(\\d)\"\\s*data-link-id=\"\\d+\"\\s*data-link-target=\"(.*?)\".*?Hoster\\s(.*?)\"", 32).matcher(this.pageContent);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                if (group != null && ((group.equals("2") && this.language == Core.Language.EN) || (group.equals("1") && this.language == Core.Language.GE))) {
                    this.streamUrls.put(group3, this.root + group2);
                }
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.streamUrl = Helper.redirectedUrl(this.selectedAlternate);
            this.url = this.streamUrl;
            if (this.url.contains("vidoza") || this.url.contains("videzz")) {
                getUrlContent();
                return;
            } else {
                next();
                return;
            }
        }
        if (this.nextCount == 3) {
            if (this.url.contains("vidoza") || this.url.contains("videzz")) {
                this.url = Helper.pregMatchAll("source\\s*src=\"(.*?)\"", this.pageContent).get(0);
                this.streamUrl = this.url;
            }
            loadSubtitlesOnline();
            waitWhileWorking();
            oldParserIntegration();
        }
    }
}
