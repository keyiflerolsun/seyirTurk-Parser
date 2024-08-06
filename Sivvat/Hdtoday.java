package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlEmbed;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Hdtoday extends Core {
    String id;
    String playableUrl;
    String tvID;

    public Hdtoday(String str, Context context, ExoPlayer exoPlayer, WebView webView, String str2, String str3, String str4) {
        super(str, context, exoPlayer, webView);
        this.playableUrl = "";
        this.tvID = "";
        this.id = "";
        String stripToParse = stripToParse(str, false);
        this.id = str4 + "_hdtoday";
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebViewAutoClick;
        this.uaType = Core.UAType.Nondroid;
        this.lookingFor = "playlist.m3u8";
        this.lookingForEmbed = HtmlEmbed.TAG_NAME;
        this.toClick = "jw-icon jw-icon-display jw-button-color jw-reset";
        this.clickType = 0;
        setUserAgent();
        trSubLogin();
        this.url = stripToParse;
        if (!stripToParse.contains("watch-movie") && stripToParse.contains("/movie/")) {
            this.url = this.url.replace("/movie/", "/watch-movie/");
        } else {
            this.url = this.url.replace("/tv/", "/watch-tv/").replace("-full-", "-hd-").replace(".se", ".tv");
            String str5 = this.url.split("\\.")[this.url.split("\\.").length - 1];
            this.tvID = str5;
            this.tvID = str5.split("/")[0];
            this.parserType = Core.ParserType.GetRequest;
            this.initialUrl = this.url;
            this.url = "https://hdtoday.se/ajax/episode/servers/" + this.tvID;
            this.isTv = true;
            this.s = str2;
            this.e = str3;
        }
        this.headers.put("Referer", "https://hdtoday.se/");
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Hdtoday.1
            @Override // java.lang.Runnable
            public void run() {
                Hdtoday.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            if (this.tvID.equals("")) {
                waitWhileWorking();
                if (this.lookingForEmbed.contains("http")) {
                    this.parserType = Core.ParserType.GetRequest;
                    this.playableUrl = this.url;
                    this.headers.put("x-requested-with", "XMLHttpRequest");
                    this.url = "https://megacloud.tv/embed-1/ajax/e-1/getSources?id=" + this.lookingForEmbed.split("/")[this.lookingForEmbed.split("/").length - 1].split("\\?")[0];
                    getUrlContent();
                    return;
                }
                oldParserIntegration();
                return;
            }
            try {
                this.url = this.initialUrl.replace(this.tvID, Helper.pregMatchAll("<a data-id=\"(.*?)\"", this.pageContent).get(0)).split("/sezon")[0];
                this.parserType = Core.ParserType.WebViewAutoClick;
                this.nextCount--;
                this.tvID = "";
                getUrlContent();
                return;
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
        if (this.nextCount == 2) {
            this.url = this.playableUrl;
            headersClear();
            try {
                JSONArray jSONArray = new JSONObject(this.pageContent).getJSONArray("tracks");
                for (int r4 = 0; r4 < jSONArray.length(); r4++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(r4);
                    if (jSONObject.has(HtmlLabel.TAG_NAME) && jSONObject.has("file")) {
                        if (jSONObject.getString(HtmlLabel.TAG_NAME).contains("English")) {
                            this.subtitles.put("Eng", jSONObject.getString("file"));
                        } else if (jSONObject.getString(HtmlLabel.TAG_NAME).contains("German")) {
                            this.subtitles.put("Ger", jSONObject.getString("file"));
                        } else if (jSONObject.getString(HtmlLabel.TAG_NAME).contains("Turkish")) {
                            this.subtitles.put("Tur", jSONObject.getString("file"));
                        }
                    }
                }
            } catch (Exception unused) {
            }
            loadSubtitlesOnline();
            waitWhileWorking();
            if (this.subtitles.containsKey("Eng")) {
                translateSubFromUrl(this.subtitles.get("Eng"), this.id);
                waitWhileWorking();
            }
            oldParserIntegration();
        }
    }
}
