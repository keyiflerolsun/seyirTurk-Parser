package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Sivvat.Core;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Webteizle extends Core {
    String dataAlt;
    String dataEmbed;

    public Webteizle(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.dataEmbed = "";
        this.dataAlt = "";
        String stripToParse = stripToParse(str, false);
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.GetRequest;
        this.uaType = Core.UAType.Nondroid;
        setUserAgent();
        this.headers.put("Referer", this.root);
        this.url = stripToParse;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Webteizle.1
            @Override // java.lang.Runnable
            public void run() {
                Webteizle.this.start();
            }
        }).start();
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        String str;
        String str2;
        String str3;
        String str4 = "fembed";
        super.next();
        int r12 = 1;
        try {
            if (this.nextCount == 1) {
                this.pageContent = Helper.pregMatchAll("data-id=\"(.*?)\"", this.pageContent).get(0);
                StringBuilder sb = new StringBuilder();
                sb.append("filmid=");
                sb.append(this.pageContent);
                sb.append("&dil=");
                sb.append(this.initialUrl.contains("altyazi") ? "1" : "0");
                String sb2 = sb.toString();
                if (this.initialUrl.contains("sezon")) {
                    Matcher matcher = Pattern.compile("(\\d*)-sezon-(\\d*)-").matcher(this.initialUrl);
                    if (matcher.find()) {
                        sb2 = sb2 + "&s=" + matcher.group(1) + "&b=" + matcher.group(2);
                    }
                }
                String str5 = sb2 + "&bot=0";
                URL url = new URL(this.initialUrl);
                this.dataAlt = "";
                this.dataEmbed = "";
                headersClear();
                this.headers.put("Referer", url.getProtocol() + "://" + Helper.getBaseDomain(url.getHost()));
                this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                this.pageContent = Helper.getGetRequestContent(this.headers, url.getProtocol() + "://" + Helper.getBaseDomain(url.getHost()) + "/js/site.min.js");
                Matcher matcher2 = Pattern.compile("#embed'\\)\\.addClass\\('loading'\\);\\$\\.post\\(\"/(.*?)\"", 32).matcher(this.pageContent);
                if (matcher2.find()) {
                    this.dataEmbed = matcher2.group(1);
                }
                Matcher matcher3 = Pattern.compile("s,b\\)\\{\\$.post\\('/(.*?)'", 32).matcher(this.pageContent);
                if (matcher3.find()) {
                    this.dataAlt = matcher3.group(1);
                }
                this.url = url.getProtocol() + "://" + Helper.getBaseDomain(url.getHost()) + "/" + this.dataAlt;
                this.parserType = Core.ParserType.PostRequest;
                this.postData = str5;
                getUrlContent();
                return;
            }
            if (this.nextCount == 2) {
                JSONArray jSONArray = new JSONObject(this.pageContent).getJSONArray("data");
                int r6 = 0;
                while (r6 < jSONArray.length()) {
                    this.postData = "id=" + jSONArray.getJSONObject(r6).getString(TtmlNode.ATTR_ID);
                    URL url2 = new URL(this.initialUrl);
                    headersClear();
                    this.headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
                    this.headers.put(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                    this.headers.put("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                    this.pageContent = Helper.getUrlContentPost(this.headers, url2.getProtocol() + "://" + Helper.getBaseDomain(url2.getHost()) + "/" + this.dataEmbed, this.postData);
                    Matcher matcher4 = Pattern.compile("<script>(.*?)\\('(.*?)',.*?\\);</script>").matcher(this.pageContent);
                    if (matcher4.find()) {
                        str2 = matcher4.group(r12);
                        str = matcher4.group(2);
                    } else {
                        Matcher matcher5 = Pattern.compile("/player/video.asp\\?v=(.*?)\"").matcher(this.pageContent);
                        if (matcher5.find()) {
                            str2 = "Qiwi";
                            str = matcher5.group(r12);
                        } else {
                            str = "";
                            str2 = str;
                        }
                    }
                    if (str2 != null && !str2.equals("")) {
                        if (str2.equals("uptobox")) {
                            this.streamUrls.put("UP", "https://uptostream.com/iframe/" + str);
                        } else if (str2.equals("sper")) {
                            this.streamUrls.put("Super", "https://supervideo.tv/e/" + str);
                        } else if (str2.equals(str4)) {
                            this.streamUrls.put(str4, "https://www.fembed.net/v/" + str);
                        } else if (str2.equals("vidmoly")) {
                            Map<String, String> map = this.streamUrls;
                            StringBuilder sb3 = new StringBuilder();
                            str3 = str4;
                            sb3.append("https://vidmoly.me/embed-");
                            sb3.append(str);
                            sb3.append(".html");
                            map.put("VidMoly", sb3.toString());
                            r6++;
                            str4 = str3;
                            r12 = 1;
                        } else {
                            str3 = str4;
                            if (str2.equals("mailru")) {
                                String[] split = str.split("/");
                                this.streamUrls.put("mailru", "https://my.mail.ru/" + split[0] + "/" + split[1] + "/video/embed/" + split[2] + "/" + split[3]);
                            } else if (str2.equals("okru")) {
                                this.streamUrls.put("ODK", "https://odnoklassniki.ru/videoembed/" + str);
                            } else if (str2.equals("streamlare")) {
                                this.streamUrls.put("Streamlare", "https://streamlare.com/e/" + str);
                            } else if (str2.equals("streamsb")) {
                                this.streamUrls.put("StreamSB", "https://streamsb.net/e/" + str + ".html");
                            } else if (str2.equals("filemoon")) {
                                this.streamUrls.put("Filemoon", "https://filemoon.sx/e/" + str);
                            }
                            r6++;
                            str4 = str3;
                            r12 = 1;
                        }
                    }
                    str3 = str4;
                    r6++;
                    str4 = str3;
                    r12 = 1;
                }
                showAlternates();
                return;
            }
            if (this.nextCount == 3) {
                this.url = this.selectedAlternate;
                if (this.selectedAlternate.contains("vidmoly")) {
                    this.parserType = Core.ParserType.WebView;
                    this.lookingFor = "window.location";
                    try {
                        URL url3 = new URL(this.initialUrl);
                        this.headers.put("Referer", url3.getProtocol() + "://" + Helper.getBaseDomain(url3.getHost()));
                        getUrlContent();
                    } catch (Exception unused) {
                    }
                }
                oldParserIntegration();
                return;
            }
            if (this.nextCount == 4) {
                waitWhileWorking();
                String str6 = this.pageContent;
            }
        } catch (Exception unused2) {
        }
    }
}
