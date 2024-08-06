package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Statics;
import com.swenauk.mainmenu.VideoView;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/* loaded from: classes3.dex */
public class Jetfilmizle extends Core {
    String referer;

    public Jetfilmizle(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.referer = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.WebView;
        this.uaType = Core.UAType.Nondroid;
        this.url = stripToParse;
        this.referer = stripToParse;
        this.lookingFor = "film_part";
        this.reloadFor = 3;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Jetfilmizle.1
            @Override // java.lang.Runnable
            public void run() {
                Jetfilmizle.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (statusType == Core.StatusType.Error) {
            this.url = "";
            oldParserIntegration();
        }
        if (this.nextCount == 1 && !((VideoView) this.calledContext).isDestroyed()) {
            waitWhileWorking();
            this.pageContent = Helper.pregMatchAll("film_part(.*?)(?:pbgiris|iframe)", this.pageContent).get(0);
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("<span>(.*?)</span>", this.pageContent);
            LinkedList<String> pregMatchAll2 = Helper.pregMatchAll("href=\"(.*?)\"", this.pageContent);
            pregMatchAll2.add(0, this.url);
            ArrayList arrayList = new ArrayList();
            arrayList.add("Vupload");
            arrayList.add("Letsupload");
            arrayList.add("JetPlay");
            arrayList.add("Mail");
            arrayList.add("Aparat");
            arrayList.add("Vidmoly");
            arrayList.add("MixPlay");
            arrayList.add("Jetv.xyz");
            arrayList.add("Platin");
            arrayList.add("Moly");
            arrayList.add("OkRu");
            arrayList.add("Okru");
            arrayList.add("VK");
            arrayList.add("JET");
            arrayList.add("SEG");
            arrayList.add("One");
            arrayList.add("TR-EN");
            arrayList.add("YX");
            arrayList.add("TRP");
            for (int r6 = 0; r6 < pregMatchAll.size(); r6++) {
                if (arrayList.contains(pregMatchAll.get(r6))) {
                    this.streamUrls.put(pregMatchAll.get(r6), pregMatchAll2.get(r6));
                }
            }
            showAlternates();
            return;
        }
        if (this.nextCount == 2 && !((VideoView) this.calledContext).isDestroyed()) {
            this.url = this.selectedAlternate;
            this.lookingFor = "data";
            getUrlContent();
            return;
        }
        if (this.nextCount == 3 && !((VideoView) this.calledContext).isDestroyed()) {
            waitWhileWorking();
            headersClear();
            this.headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
            this.headers.put("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.5,en;q=0.3");
            this.headers.put("Connection", "keep-alive");
            this.headers.put("Referer", this.referer);
            this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            this.url = Helper.pregMatchAll("data-(?:litespeed|)-src='(.*?)'\\s*(?:width|frame|)", this.pageContent).get(0);
            if (this.url.equals("")) {
                this.url = Helper.pregMatchAll("<iframe src=['\"](.*?)['\"].*?allowfullscreen", this.pageContent).get(0);
            }
            if (!this.url.startsWith("http")) {
                this.url = "https:" + this.url;
            }
            if (Helper.containsAny(this.url, "mixdrop", "videobin", "upstream", "vidmoly", "ok.ru", "odnoklassniki", "vk.com", "dood", "trstx")) {
                oldParserIntegration();
                return;
            }
            if (this.url.contains("jetv.xyz/yx")) {
                this.pageContent = Helper.getUrlContentPost(this.headers, "https://jetv.xyz/yx/api.php", "vars=" + Helper.pregMatchAll("id=(.*?)$", this.url));
                this.url = Helper.pregMatchAll("file:.*?\"(.*?)\",", this.pageContent).get(0);
                this.stepType = Core.StepType.Play;
                oldParserIntegration();
                return;
            }
            this.parserType = Core.ParserType.GetRequest;
            getUrlContent();
            return;
        }
        if (this.nextCount == 4 && !((VideoView) this.calledContext).isDestroyed()) {
            if (Helper.containsAny(this.url, "jtfi")) {
                this.url = Helper.pregMatchAll("\"hls\",\"file\":\"(.*?)\"", this.pageContent).get(0).replace("\\/", "/");
                if (this.url.equals("")) {
                    this.url = Helper.pregMatchAll("\"file\":\"(.*?)\"", this.pageContent).get(0).replace("\\/", "/");
                }
                this.referer = this.url;
                getUrlContent();
            }
            if (this.url.contains("letsupload")) {
                this.url = Helper.pregMatchAll("mp4HD:\\s*\"(.*?)\"", this.pageContent).get(0);
                return;
            }
            if (Helper.containsAny(this.url, "aparat.com", "vupload")) {
                this.url = Helper.pregMatchAll("src: \"(.*?)\"", this.pageContent).get(0);
                return;
            }
            if (!Helper.containsAny(this.url, "gp.jetcdn", "jetv.xyz", "yjco.xyz", "jetfilmvid")) {
                if (Helper.containsAny(this.url, "segavid", "oneupload", "ply.jetfilmizle")) {
                    getUrlContent();
                    return;
                }
                return;
            }
            this.pageContent = this.pageContent.replace("\\", "");
            if (this.pageContent.contains("\"label\":")) {
                this.streamUrls = Helper.pregMatchAll("\"label\":\"([^\"]+)\",\"type\":\"[^\"]+\",\"file\":\"([^\"]+)\"", this.pageContent, true, true);
                showAlternates();
                return;
            }
            if (this.pageContent.contains("m3u8")) {
                this.url = Helper.pregMatchAll("\"?file\"? ?: ?\"([^\"]+)\"", this.pageContent).get(0);
                oldParserIntegration();
                return;
            }
            if (this.pageContent.contains("src=")) {
                String str = Helper.pregMatchAll("src=[\"\\'](.*?)[\"\\']", Helper.pregMatchAll("<iframe(.*?)</iframe", this.pageContent).get(0)).get(0);
                if (str.startsWith("//")) {
                    str = "https:" + str;
                }
                if (!str.contains("fjetvid.com")) {
                    this.url = str;
                    oldParserIntegration();
                    return;
                }
                this.postData = "d=fjetvid.com&r=https://jetv.xyz/";
                this.url = str.replace("/v/", "/api/source/");
                headersClear();
                this.headers.put("Referer", this.url);
                this.parserType = Core.ParserType.PostRequest;
                getUrlContent();
                return;
            }
            this.streamUrls = Helper.pregMatchAll("\"?file\"? ?: ?\"([^\"]+)\", ?\"(?:type|label)\": ?\"([^\"]+)\"", this.pageContent, true, true);
            showAlternates();
            return;
        }
        if (this.nextCount == 5 && !((VideoView) this.calledContext).isDestroyed()) {
            if (this.parserType == Core.ParserType.PostRequest) {
                this.streamUrls = Helper.pregMatchAll("\"file\":\"(.*?)\",\"label\":\"(.*?)\"", this.pageContent, false, true);
                showAlternates();
                return;
            }
            if (this.url.contains("jtfi")) {
                this.headers.put("Referer", this.referer);
            } else if (Helper.containsAny(this.url, "segavid", "oneupload")) {
                this.url = Helper.pregMatchAll("file:\"(.*?)\"", this.pageContent).get(0);
            } else if (this.url.contains("ply.jetfilmizle")) {
                try {
                    this.postData = "ct=" + URLEncoder.encode(Helper.pregMatchAll("ct\":\"(.*?)\"", this.pageContent).get(0), "UTF-8") + "&iv=" + URLEncoder.encode(Helper.pregMatchAll("iv\":\"(.*?)\"", this.pageContent).get(0), "UTF-8") + "&s=" + URLEncoder.encode(Helper.pregMatchAll("s\":\"(.*?)\"", this.pageContent).get(0), "UTF-8");
                    StringBuilder sb = new StringBuilder();
                    sb.append(Statics.SERVER);
                    sb.append("sey/v2/jetfilmizle_tren.php");
                    this.url = sb.toString();
                    HashMap hashMap = new HashMap();
                    hashMap.put("Accept", "*/*");
                    hashMap.put("User-Agent", this.headers.get("User-Agent"));
                    String urlContentPost = Helper.getUrlContentPost(hashMap, Statics.SERVER + "sey/v2/jetfilmizle_tren.php?isAndroid=1", this.postData);
                    this.url = Helper.pregMatchAll("\"file\":\"(.*?)\"", urlContentPost).get(0);
                    if (urlContentPost.contains(HtmlLabel.TAG_NAME) && urlContentPost.contains(".srt")) {
                        this.subtitle = Helper.pregMatchAll("(https[^\"]+\\.srt)\",\"label\":\"Turk", urlContentPost).get(0);
                    }
                    headersClear();
                    this.headers.put("Referer", this.initialUrl);
                    oldParserIntegration();
                    return;
                } catch (Exception unused) {
                }
            } else {
                this.url = this.selectedAlternate;
            }
            headersClear();
            this.headers.put("Range", "bytes=0-");
            oldParserIntegration();
            return;
        }
        if (this.nextCount != 6 || ((VideoView) this.calledContext).isDestroyed()) {
            return;
        }
        this.url = this.selectedAlternate;
        headersClear();
        this.headers.put("Range", "bytes=0-");
        oldParserIntegration();
    }
}
