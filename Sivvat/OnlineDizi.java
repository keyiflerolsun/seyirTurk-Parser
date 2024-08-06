package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.swenauk.mainmenu.Sivvat.Core;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OnlineDizi extends Core {
    String embed;

    public OnlineDizi(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.embed = "";
        String stripToParse = stripToParse(str, true);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Classic;
        this.url = stripToParse;
        if (stripToParse.contains("/film/")) {
            this.lookingFor = AppMeasurementSdk.ConditionalUserProperty.ACTIVE;
            if (!stripToParse.contains("izle")) {
                this.url += "/turkce-altyazi-izle";
            }
        } else {
            this.lookingFor = "episode-buttons";
        }
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.OnlineDizi.1
            @Override // java.lang.Runnable
            public void run() {
                OnlineDizi.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        String str;
        super.next();
        if (this.nextCount == 1) {
            waitForReloadAndCookie();
            this.pageContent = Helper.pregMatchAll("Alternatif(.*?)episode-buttons", this.pageContent).get(0);
            this.streamUrls = Helper.pregMatchAll("href=\"(.*?)\".*?>(.*?)<", this.pageContent, false, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 2) {
            saveCookieToServer("on_dz");
            this.parserType = Core.ParserType.GetRequest;
            this.url = this.selectedAlternate;
            getUrlContent();
            return;
        }
        if (this.nextCount == 3) {
            String str2 = Helper.pregMatchAll("iframe\\s*src=\"(.*?)\"", this.pageContent).get(0);
            if (!str2.startsWith("http")) {
                str2 = this.root + str2;
            }
            this.url = str2;
            this.headers.put("Referer", this.root);
            getUrlContent();
            return;
        }
        if (this.nextCount == 4) {
            String str3 = Helper.pregMatchAll("ifsrc = \"(.*?)\"", this.pageContent).get(0);
            if (!str3.startsWith("http")) {
                str3 = "https:" + str3;
            }
            this.url = getRedirectUrl(str3);
            if (this.url == null) {
                this.url = "";
            }
            if (this.url.contains("gdplayer")) {
                this.embed = this.url;
                this.headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/108.0.5359.128  Safari/537.36");
                getUrlContent();
            } else if (this.url.contains("fscdn.xyz")) {
                this.embed = this.url;
                String str4 = this.url.split("/")[4];
                String str5 = this.url + "?do=getVideo";
                this.postData = "hash=" + str4 + "&r=" + this.root + "&s=";
                this.url = str5;
                this.headers.put(HttpHeader.CONTENT_TYPE_LC, "application/x-www-form-urlencoded; charset=UTF-8");
                this.headers.put("x-requested-with", "XMLHttpRequest");
                getPostRequestContent();
                try {
                    String string = new JSONObject(this.pageContent).getJSONArray("videoSources").getJSONObject(0).getString("file");
                    if (!string.contains("fcdn")) {
                        this.url = string;
                    } else {
                        this.url = string;
                        getUrlContent();
                    }
                } catch (Exception unused) {
                }
            } else if (this.url.contains("ondembed.xyz")) {
                this.url = this.url.replace("ondembed.xyz", "fembed.com");
            }
            oldParserIntegration();
            return;
        }
        if (this.nextCount == 5) {
            if (this.embed.contains("gdplayer")) {
                this.url = "https:" + Helper.pregMatchAll("(//gdplayer.org/api/.*?)\"", this.pageContent).get(0);
                getUrlContent();
                return;
            }
            Matcher matcher = Pattern.compile("(https:.*?m3u8)").matcher(this.pageContent);
            while (matcher.find()) {
                if (matcher.group(1).contains("240p/playlist")) {
                    str = "240p";
                } else if (matcher.group(1).contains("360p/playlist")) {
                    str = "360p";
                } else if (matcher.group(1).contains("480p/playlist")) {
                    str = "480p";
                } else if (matcher.group(1).contains("720p/playlist")) {
                    str = "720p";
                } else {
                    str = matcher.group(1).contains("1080p/playlist") ? "1080p" : "";
                }
                this.streamUrls.put(str, matcher.group(1));
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 6) {
            if (this.embed.contains("gdplayer")) {
                try {
                    this.url = new JSONObject(this.pageContent).getJSONArray("sources").getJSONObject(0).getString("file");
                } catch (Exception unused2) {
                    this.url = "";
                }
            } else {
                this.url = this.selectedAlternate;
            }
            this.stepType = Core.StepType.Play;
            oldParserIntegration();
        }
    }
}
