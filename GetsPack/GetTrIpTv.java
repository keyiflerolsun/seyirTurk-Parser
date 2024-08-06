package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.TrIpTv;
import com.swenauk.mainmenu.Sivvat.Helper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetTrIpTv extends AsyncTask<String, String, String> {
    TrIpTv afaki;
    String referer = "";
    String res;

    public GetTrIpTv(TrIpTv trIpTv) {
        this.afaki = trIpTv;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String group;
        Matcher matcher;
        try {
            this.res = getUrlContent(strArr[0]);
            Pattern compile = Pattern.compile("ht_stream_m3u8\":\"(.*?)\"", 32);
            Matcher matcher2 = compile.matcher(this.res);
            if (strArr[0].contains("teleontv")) {
                if (strArr[0].endsWith("m3u8")) {
                    this.afaki.parsed = strArr[0];
                } else {
                    matcher2 = Pattern.compile("\\[2160\\](.*?.m3u8)", 32).matcher(this.res);
                }
            } else {
                if (!strArr[0].contains("showtv") && !strArr[0].contains("haberturk") && !strArr[0].contains("bloomberg")) {
                    if (strArr[0].contains("nowtv")) {
                        matcher2 = Pattern.compile("(?:videoSrc|daiUrl)\\s*:\\s*'(.*?)'", 32).matcher(this.res);
                    } else if (strArr[0].contains("dha")) {
                        matcher2 = Pattern.compile("video.src\\s*=\\s*'(.*?)'", 32).matcher(this.res);
                    } else {
                        if (!strArr[0].contains("startv") && (!strArr[0].contains("ntv") || strArr[0].contains("woman") || strArr[0].contains("yaban") || strArr[0].contains("sabantv.com") || strArr[0].contains("milyontv.com"))) {
                            if (!strArr[0].contains("tlctv") && !strArr[0].contains("dmax")) {
                                if (strArr[0].contains("personamedia")) {
                                    matcher2 = Pattern.compile("\"hlsmanifest\"\\s*:\\s*\"(.*?)\"", 32).matcher(this.res);
                                } else if (strArr[0].contains("tv8.com.tr")) {
                                    matcher2 = Pattern.compile("var videoUrl = \"(.*?)\"", 32).matcher(this.res);
                                } else if (strArr[0].contains("kanald.com")) {
                                    Pattern compile2 = Pattern.compile("data-url=\"(.*?)\"", 32);
                                    Matcher matcher3 = compile2.matcher(this.res);
                                    if (matcher3.find()) {
                                        this.afaki.parsed = matcher3.group(1).replace("https://media.duhnet.tv", "");
                                    }
                                    matcher2 = compile2.matcher("test");
                                } else if (strArr[0].contains("teve2")) {
                                    System.out.println(this.res);
                                    matcher2 = !Pattern.compile("file:'(.*?)'").matcher(this.res).find() ? Pattern.compile("contentUrl\"\\s*:\\s*\"(.*?)\"").matcher(this.res) : Pattern.compile("file:'(.*?)'").matcher(this.res);
                                } else if (strArr[0].contains("cnnturk")) {
                                    matcher2 = Pattern.compile("data-id=\"(.*?)\"").matcher(this.res);
                                    if (matcher2.find()) {
                                        this.res = getUrlContent("https://www.cnnturk.com/action/media/" + matcher2.group(1) + "?ad_type=embed-player");
                                        Matcher matcher4 = Pattern.compile("SecurePath\"\\s*:\\s*\"(.*?)\"").matcher(this.res);
                                        if (matcher4.find()) {
                                            String replace = matcher4.group(1).replace("cnnturknp", "cnnturknp/track_4_1000");
                                            this.res = replace;
                                            this.afaki.parsed = replace;
                                        }
                                        matcher2 = Pattern.compile("seyirturkcanım").matcher(this.res);
                                    }
                                } else {
                                    if (!strArr[0].contains("halktv") && !strArr[0].contains("tv100")) {
                                        if (strArr[0].contains("numberone")) {
                                            matcher2 = Pattern.compile("<iframe.*?src=\"(.*?)\"").matcher(this.res);
                                        } else if (strArr[0].contains("brtk")) {
                                            matcher2 = !strArr[0].contains("bayrak") ? Pattern.compile("video_player\"><a href=\"(.*?)\"").matcher(this.res) : Pattern.compile("video_player mobile\"><a href=\"(.*?);").matcher(this.res);
                                        } else if (strArr[0].contains("ucankus")) {
                                            matcher2 = Pattern.compile("<source\\s*src=\"(.*?)\"").matcher(this.res);
                                        } else if (strArr[0].contains("beyaztv")) {
                                            this.res = getUrlContent("https://m.beyaztv.com.tr/canli-yayin/");
                                            matcher2 = Pattern.compile("videoUrl\\s*=\\s*\"(.*?)\"").matcher(this.res);
                                        } else if (strArr[0].contains("tvem")) {
                                            Matcher matcher5 = Pattern.compile("<div class=\"live-area\">\\s*.*?<script src=\"(.*?)\"></script>").matcher(this.res);
                                            if (matcher5.find()) {
                                                this.res = matcher5.group(1);
                                                this.res = getUrlContent("http:" + this.res);
                                                matcher2 = Pattern.compile("yayincomtr4=\"(.*?)\"").matcher(this.res);
                                            }
                                        } else if (strArr[0].contains("ekoturk")) {
                                            matcher2 = Pattern.compile("<iframe.*?src=\"(.*?)\\?").matcher(this.res);
                                        } else if (strArr[0].contains("kralmuzik")) {
                                            Pattern compile3 = Pattern.compile("youtube.init\\('(.*?)'");
                                            Matcher matcher6 = compile3.matcher(this.res);
                                            if (matcher6.find()) {
                                                String str = "https://www.youtube.com/watch?v=" + matcher6.group(1);
                                                this.res = str;
                                                this.afaki.parsed = str;
                                            }
                                            matcher2 = compile3.matcher(this.res);
                                        } else {
                                            if (!strArr[0].contains("ahaber") && !strArr[0].contains("anews") && !strArr[0].contains("apara") && !strArr[0].contains("aspor")) {
                                                if (strArr[0].contains("m.star.com")) {
                                                    matcher2 = Pattern.compile("hls.loadSource\\('(.*?)'").matcher(this.res);
                                                } else if (strArr[0].contains("tv360")) {
                                                    matcher2 = Pattern.compile("source\\s*src=\"(.*?)\"").matcher(this.res);
                                                } else if (strArr[0].contains("dreamturk")) {
                                                    Pattern compile4 = Pattern.compile("data-id=\"(.*?)\"");
                                                    Matcher matcher7 = compile4.matcher(this.res);
                                                    if (matcher7.find()) {
                                                        this.res = getUrlContent("https://www.dreamturk.com.tr/actions/content/media/" + matcher7.group(1));
                                                        try {
                                                            JSONObject jSONObject = new JSONObject(this.res);
                                                            this.res = jSONObject.getJSONObject("Media").getJSONObject(HttpHeaders.LINK).getString("ServiceUrl") + jSONObject.getJSONObject("Media").getJSONObject(HttpHeaders.LINK).getString("SecurePath");
                                                        } catch (Exception e) {
                                                            System.out.println(e);
                                                        }
                                                    }
                                                    matcher2 = compile4.matcher(this.res);
                                                    this.afaki.parsed = this.res;
                                                } else if (strArr[0].contains("ulusal")) {
                                                    try {
                                                        this.res = URLDecoder.decode(this.res, StandardCharsets.UTF_8.toString());
                                                    } catch (Exception e2) {
                                                        System.out.println(e2);
                                                    }
                                                    matcher2 = Pattern.compile("<iframe.*?src=\"(.*?)\"").matcher(this.res);
                                                } else if (strArr[0].contains("kanalb")) {
                                                    matcher2 = Pattern.compile("file\\s*:\\s*\"(.*?)\"").matcher(this.res);
                                                } else if (strArr[0].contains("womantv")) {
                                                    this.res = getUrlContent("https://appie.vidpanel.com/wmtv/video/live");
                                                    try {
                                                        String string = new JSONObject(this.res).getString("video");
                                                        this.res = string;
                                                        this.afaki.parsed = string;
                                                    } catch (Exception e3) {
                                                        System.out.println(e3);
                                                    }
                                                } else if (strArr[0].contains("sportstv")) {
                                                    this.res = getUrlContent("http://avrupaoyunlari.sportstv.com.tr:8188/a2srv-client/anonymous");
                                                    try {
                                                        this.res = getUrlContent("http://avrupaoyunlari.sportstv.com.tr:8188/cms-btv-client/getactivechannelsofchannel/1", new JSONObject(this.res).getString("data"));
                                                        String string2 = new JSONObject(this.res).getJSONArray("data").getJSONObject(0).getString("url");
                                                        this.res = string2;
                                                        this.afaki.parsed = string2;
                                                    } catch (Exception e4) {
                                                        System.out.println(e4);
                                                    }
                                                } else if (strArr[0].contains("tjk")) {
                                                    matcher2 = Pattern.compile("hls\\s*:\\s*'(.*?)'").matcher(this.res);
                                                } else {
                                                    if (!strArr[0].contains("yabantv") && !strArr[0].contains("koytv")) {
                                                        if (strArr[0].contains("volotv.com")) {
                                                            Matcher matcher8 = Pattern.compile("const data\\s*=\\s*(.*?);", 32).matcher(this.res);
                                                            if (matcher8.find()) {
                                                                try {
                                                                    this.res = getUrlContentPost("https://www.volotv.com/Tv/TVShow", new JSONObject(matcher8.group(1).trim()).toString(), false);
                                                                    JSONObject jSONObject2 = new JSONObject(this.res);
                                                                    if (!Pattern.compile("file:'(.*?)'").matcher(jSONObject2.getJSONObject("result").getString("playerBodyEnd")).find()) {
                                                                        matcher = Pattern.compile("contentURL\"\\s*:\\s*(.*?)\"").matcher(jSONObject2.getJSONObject("result").getString("playerBodyEnd"));
                                                                    } else {
                                                                        matcher = Pattern.compile("file:'(.*?)'").matcher(jSONObject2.getJSONObject("result").getString("playerBodyEnd"));
                                                                    }
                                                                    matcher2 = matcher;
                                                                } catch (Exception e5) {
                                                                    System.out.println(e5);
                                                                }
                                                            }
                                                        } else if (strArr[0].contains("chaturbate")) {
                                                            Pattern compile5 = Pattern.compile("hls_source\\\\u0022:\\s*\\\\u0022(.*?)\\\\u0022");
                                                            Matcher matcher9 = compile5.matcher(this.res);
                                                            if (matcher9.find()) {
                                                                String group2 = matcher9.group(1);
                                                                this.res = group2;
                                                                String replace2 = group2.replace("\\u002D", "-");
                                                                this.res = replace2;
                                                                this.afaki.parsed = replace2;
                                                                String replace3 = this.res.replace("playlist.m3u8", "");
                                                                this.res = getUrlContent(this.res);
                                                                Matcher matcher10 = Pattern.compile("RESOLUTION=1280x720(.*?)$").matcher(this.res);
                                                                if (matcher10.find()) {
                                                                    group = matcher10.group(1);
                                                                } else {
                                                                    Matcher matcher11 = Pattern.compile("RESOLUTION=1280x720(.*?m3u8)").matcher(this.res);
                                                                    group = matcher11.find() ? matcher11.group(1) : "";
                                                                }
                                                                if (!group.equals("")) {
                                                                    String str2 = replace3 + group;
                                                                    this.res = str2;
                                                                    this.afaki.parsed = str2;
                                                                }
                                                            }
                                                            matcher2 = compile5.matcher(this.res);
                                                        } else {
                                                            try {
                                                                if (strArr[0].contains("sabantv.com")) {
                                                                    matcher2 = Pattern.compile("playerKey\\s*=\\s*\"(.*?)\"").matcher(this.res);
                                                                    if (matcher2.find()) {
                                                                        String str3 = "https://saban.tiviplayer.com/player/data.json/" + matcher2.group(1);
                                                                        this.res = str3;
                                                                        this.res = getUrlContent(str3);
                                                                        JSONObject jSONObject3 = new JSONObject(this.res);
                                                                        if (jSONObject3.has("playerSourceM3U")) {
                                                                            this.afaki.parsed = jSONObject3.getString("playerSourceM3U");
                                                                        }
                                                                    }
                                                                } else if (strArr[0].contains("milyontv.com")) {
                                                                    Pattern compile6 = Pattern.compile("source\\s*:\\s*'(.*?)'");
                                                                    Matcher matcher12 = compile6.matcher(this.res);
                                                                    if (matcher12.find()) {
                                                                        String group3 = matcher12.group(1);
                                                                        this.res = group3;
                                                                        this.afaki.parsed = group3;
                                                                        System.out.println(this.res);
                                                                    }
                                                                    matcher2 = compile6.matcher(this.res);
                                                                } else {
                                                                    String str4 = "minikago";
                                                                    if (!strArr[0].contains("atv.com.tr") && !strArr[0].contains("vavtv.com.tr") && !strArr[0].contains("minikacocuk") && !strArr[0].contains("minikago") && !strArr[0].contains("a2tv")) {
                                                                        if (strArr[0].contains("tele1")) {
                                                                            matcher2 = Pattern.compile("iframe.*?src=\"(.*?)\"").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = matcher2.group(1);
                                                                            }
                                                                        } else if (strArr[0].contains("ulketv")) {
                                                                            matcher2 = Pattern.compile("xt/html\"\\s*src=\"(.*?)\\?").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = matcher2.group(1);
                                                                            }
                                                                        } else if (strArr[0].contains("tvnet")) {
                                                                            matcher2 = Pattern.compile("live-image\"\\s*href=\"(.*?)\"").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = matcher2.group(1);
                                                                            }
                                                                        } else if (strArr[0].contains("meteor")) {
                                                                            Pattern compile7 = Pattern.compile("src=\"(//.*?)\"");
                                                                            Matcher matcher13 = compile7.matcher(this.res);
                                                                            if (matcher13.find()) {
                                                                                Matcher matcher14 = Pattern.compile("(broad.*?)\\?").matcher(matcher13.group(1));
                                                                                if (matcher14.find()) {
                                                                                    String str5 = matcher13.group(1).split("/")[2];
                                                                                    this.res = getUrlContent("http://" + str5 + "/assets/player/html-5.3/videoonly.js");
                                                                                    Matcher matcher15 = Pattern.compile("\"GET\",\\s*\"(.*?)\"").matcher(this.res);
                                                                                    if (matcher15.find()) {
                                                                                        this.referer = "https://" + str5;
                                                                                        this.res = getUrlContent(matcher15.group(1) + matcher14.group(1));
                                                                                        this.afaki.parsed = new JSONObject(this.res).getJSONArray("streams").getJSONObject(0).getString("url");
                                                                                    }
                                                                                }
                                                                            }
                                                                            matcher2 = compile7.matcher("");
                                                                        } else if (strArr[0].contains("kanal7")) {
                                                                            matcher2 = Pattern.compile("hls:\\s*'(.*?)'").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = matcher2.group(1);
                                                                            }
                                                                        } else if (strArr[0].contains("yirmidort")) {
                                                                            matcher2 = Pattern.compile("source\\s*src=\"(.*?)\"").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = matcher2.group(1);
                                                                            }
                                                                        } else if (strArr[0].contains("istanbuluseyret")) {
                                                                            matcher2 = Pattern.compile("\"dataProvider\":(.*?\\}),").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                this.afaki.parsed = new JSONObject(matcher2.group(1)).getJSONArray("source").getJSONObject(0).getString("url");
                                                                            }
                                                                        } else if (strArr[0].contains("pokitv")) {
                                                                            matcher2 = Pattern.compile("<iframe.*?data-litespeed-src=\"(.*?)\"").matcher(this.res);
                                                                            if (matcher2.find()) {
                                                                                String str6 = matcher2.group(1).split("/")[5];
                                                                                JSONObject jSONObject4 = new JSONObject(getUrlContentPost(matcher2.group(1) + "?do=getVideo", "hash=" + str6 + "&r=" + strArr[0] + "&s=", true));
                                                                                try {
                                                                                    this.afaki.parsed = jSONObject4.getJSONArray("videoSources").getJSONObject(0).getString("file");
                                                                                } catch (Exception unused) {
                                                                                    this.afaki.parsed = Helper.pregMatchAll("source.*?src=\"(.*?)\"", getUrlContent(jSONObject4.getString("videoSrc"))).get(0);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (strArr[0].contains("minikacocuk")) {
                                                                        str4 = "minikago_cocuk";
                                                                    } else if (!strArr[0].contains("minikago")) {
                                                                        if (strArr[0].contains("a2tv")) {
                                                                            this.res = getUrlContent("https://www.atv.com.tr/a2tv/canli-yayin");
                                                                            str4 = "a2tv";
                                                                        } else {
                                                                            str4 = "atv";
                                                                        }
                                                                    }
                                                                    matcher2 = Pattern.compile("\"(https://i.tmgrup.com.tr/videojs/js.*?)\"").matcher(this.res);
                                                                    if (matcher2.find()) {
                                                                        this.res = getUrlContent(matcher2.group(1));
                                                                        Matcher matcher16 = Pattern.compile("\"(https://trkvz.daioncdn.net/" + str4 + "/" + str4 + ".m3u8.*?)\".*?:\\s*\"(.*?)\"").matcher(this.res);
                                                                        if (matcher16.find() && !strArr[0].contains("a2tv")) {
                                                                            String group4 = matcher16.group(2);
                                                                            String group5 = matcher16.group(1);
                                                                            StringBuilder sb = new StringBuilder();
                                                                            sb.append("https://securevideotoken.tmgrup.com.tr/webtv/secure?url=");
                                                                            sb.append(URLEncoder.encode(group5 + group4, "UTF-8"));
                                                                            this.res = getUrlContent(sb.toString());
                                                                            Matcher matcher17 = Pattern.compile(".*?Url\":\"(htt.*?)\"", 2).matcher(this.res);
                                                                            if (matcher17.find()) {
                                                                                this.afaki.parsed = matcher17.group(1);
                                                                            }
                                                                        } else {
                                                                            Matcher matcher18 = Pattern.compile("\"(https://trkvz.daioncdn.net/" + str4 + "/" + str4 + ".m3u8.*?)\";").matcher(this.res);
                                                                            if (matcher18.find()) {
                                                                                this.res = getUrlContent("https://securevideotoken.tmgrup.com.tr/webtv/secure?url=" + URLEncoder.encode(matcher18.group(1), "UTF-8"));
                                                                                Matcher matcher19 = Pattern.compile(".*?Url\":\"(htt.*?)\"", 2).matcher(this.res);
                                                                                if (matcher19.find()) {
                                                                                    this.afaki.parsed = matcher19.group(1);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } catch (Exception unused2) {
                                                            }
                                                        }
                                                    }
                                                    Matcher matcher20 = Pattern.compile("<iframe.*?src=\"(.*?)\"").matcher(this.res);
                                                    if (matcher20.find()) {
                                                        String group6 = matcher20.group(1);
                                                        this.res = group6;
                                                        if (!group6.contains("http")) {
                                                            this.res = "http:" + this.res;
                                                        }
                                                        this.res = getUrlContent(this.res);
                                                        matcher2 = Pattern.compile("file\\s*:\\s*\"(.*?)\"").matcher(this.res);
                                                    }
                                                }
                                            }
                                            String str7 = "https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=";
                                            if (strArr[0].contains("apara")) {
                                                str7 = "https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Faparahd%2Faparahd.m3u8";
                                            } else if (strArr[0].contains("ahaber")) {
                                                str7 = "https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=https%3A%2F%2Ftrkvz-live.ercdn.net%2Fahaberhd%2Fahaberhd.m3u8";
                                            } else if (strArr[0].contains("anews")) {
                                                str7 = "https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Fanewshd%2Fanewshd.m3u8";
                                            } else if (strArr[0].contains("aspor")) {
                                                str7 = "https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Fasporhd%2Fasporhd.m3u8";
                                            }
                                            this.res = getUrlContent(str7);
                                            matcher2 = Pattern.compile("\"Url\":\"(.*?)\"").matcher(this.res);
                                        }
                                    }
                                    matcher2 = Pattern.compile("<iframe.*?src=\"(.*?)\"").matcher(this.res);
                                }
                            }
                            matcher2 = Pattern.compile("(?:daionUrl|liveUrl)\\s*(?:=|\\:)\\s*(?:\\'|\")(.*?)(?:\\'|\")", 32).matcher(this.res);
                        }
                        this.res = getUrlContent("https://dyg-ads-cdn.s3.eu-west-1.amazonaws.com/live-broadcast-player/v1/bundle.js");
                        Matcher matcher21 = Pattern.compile(strArr[0].contains("startv") ? "Startv.*?development\":\"(.*?)\"" : "Ntv.*?development\":\"(.*?)\"").matcher(this.res);
                        if (matcher21.find()) {
                            this.afaki.parsed = matcher21.group(1);
                        }
                    }
                }
                if (strArr[0].contains("showtv")) {
                    compile = Pattern.compile("var\\s*videoUrl\\s*=\\s*\"(.*?)\"", 32);
                } else if (strArr[0].contains("haberturk")) {
                    compile = Pattern.compile("videoUrl\\s*=\\s*\"(.*?)\"", 32);
                } else if (strArr[0].contains("bloomberg")) {
                    compile = Pattern.compile(" var\\s*videoUrl\\s*=\\s*\"(.*?&app.*?)\"", 32);
                }
                matcher2 = compile.matcher(this.res);
            }
            if (!matcher2.find()) {
                return null;
            }
            try {
                String group7 = matcher2.group(1);
                this.res = group7;
                this.afaki.parsed = group7;
                return null;
            } catch (Exception unused3) {
                this.afaki.parsed = null;
                return null;
            }
        } catch (Exception e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.swenauk.mainmenu.GetsPack.GetTrIpTv.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            if (!this.referer.equals("")) {
                httpURLConnection.setRequestProperty("Referer", this.referer);
            } else {
                httpURLConnection.setRequestProperty("Referer", "http://www.atv.com.tr/");
            }
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    private String getUrlContent(String str, String str2) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.swenauk.mainmenu.GetsPack.GetTrIpTv.2
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str3) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str3) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            URL url = new URL(str);
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Authorization", str2);
            httpURLConnection.setRequestProperty("Referer", "http://www.atv.com.tr/");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    private String getUrlContentPost(String str, String str2, boolean z) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            if (z) {
                httpURLConnection.setRequestProperty("x-requested-with", "XMLHttpRequest");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
            } finally {
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetTrIpTv) str);
        this.afaki.resumeParse();
    }
}
