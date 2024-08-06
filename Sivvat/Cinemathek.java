package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Cinemathek extends Core {
    public Cinemathek(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3) {
        super(str, context, exoPlayer, webView);
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.url = stripToParse;
        trSubLogin();
        if (stripToParse.contains("/episoden/")) {
            this.s = str2;
            this.e = str3;
            this.isTv = true;
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Cinemathek.1
            @Override // java.lang.Runnable
            public void run() {
                Cinemathek.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            Matcher matcher = Pattern.compile("data-post=\"(\\d+)\"\\s*data-nume=\"(\\d+)\">.*?title\">(?:Episode|Film) starten!\\s*(.*?)</span>", 32).matcher(this.pageContent);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                if (!this.isTv) {
                    this.streamUrls.put(group3, this.root + "/wp-json/dooplayer/v2/" + group + "/movie/" + group2);
                } else {
                    this.streamUrls.put(group3, this.root + "/wp-json/dooplayer/v2/" + group + "/tv/" + group2);
                }
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            this.streamUrl = this.selectedAlternate;
            this.url = this.streamUrl;
            getUrlContent();
        } else if (this.nextCount == 3) {
            try {
                this.url = new JSONObject(this.pageContent).getString("embed_url");
                this.url = this.url.replace("dooood.com", "dood.la");
                this.streamUrl = this.url;
            } catch (Exception unused) {
            }
            loadSubtitlesOnline();
            waitWhileWorking();
            oldParserIntegration();
        }
    }
}
