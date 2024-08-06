package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import android.util.Base64;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.Parsers.YabanciDizi;
import com.swenauk.mainmenu.Sivvat.Helper;
import com.swenauk.mainmenu.VideoView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetYabanciDizi extends AsyncTask<String, String, String> {
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    YabanciDizi afaki;
    String line = "";
    String res;
    boolean second;

    public GetYabanciDizi(YabanciDizi yabanciDizi) {
        this.afaki = yabanciDizi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.second = false;
            if (!strArr[0].contains("streamlare")) {
                this.res = getUrlContent(strArr[0]);
            }
            if (!strArr[0].contains("api/drive") && !strArr[0].contains("api/moly") && !strArr[0].contains("api/ruplay") && !strArr[0].contains("api/saru") && !strArr[0].contains("api/goo") && !strArr[0].contains("api/superv")) {
                if (!strArr[0].contains("api/cf") && !strArr[0].contains("api/indi")) {
                    try {
                        if (strArr[0].contains("puhutv.com")) {
                            Helper.pregMatchAll("-([^\"]\\d*)-bolum", this.res).get(0);
                            this.res = Helper.pregMatchAll("movieAssets\".*?\"video_id\":\"(PUHU_.*?)\"", this.res).get(0);
                            this.res = getUrlContent("https://dygvideo.dygdigital.com/api/video_info?akamai=true&PublisherId=29&ReferenceId=" + this.res + "&SecretKey=NtvApiSecret2014");
                            JSONObject jSONObject = new JSONObject(this.res).getJSONObject("data").getJSONObject("flavors");
                            if (jSONObject.has("0")) {
                                this.afaki.parsed = jSONObject.getJSONObject("0").getString("file_url_1");
                                return null;
                            }
                            this.afaki.parsed = jSONObject.getString("hls");
                            return null;
                        }
                        if (strArr[0].contains("teve2")) {
                            Matcher matcher = Pattern.compile("itemprop=\"embedURL\" href=\"(.*?)\"", 32).matcher(this.res);
                            if (!matcher.find()) {
                                return null;
                            }
                            String group = matcher.group(1);
                            this.res = group;
                            String replace = group.replace("video", "action/media");
                            this.res = replace;
                            this.res = getUrlContent(replace);
                            JSONObject jSONObject2 = new JSONObject(this.res).getJSONObject("Media").getJSONObject(HttpHeaders.LINK);
                            String str = jSONObject2.getString("ServiceUrl") + "/" + jSONObject2.getString("SecurePath");
                            this.res = str;
                            this.afaki.parsed = str;
                            return null;
                        }
                        if (strArr[0].contains("dizilab")) {
                            Matcher matcher2 = Pattern.compile("loadVideo\\('(.*?)'\\)").matcher(this.res);
                            if (!matcher2.find()) {
                                return null;
                            }
                            this.res = matcher2.group(1);
                            this.res = getUrlContentPost("https://" + new URL(strArr[0]).getHost() + "/request/php/", this.res);
                            Matcher matcher3 = Pattern.compile("src=\\\\\"(.*?)\\\\\"").matcher(this.res);
                            if (matcher3.find()) {
                                String replace2 = matcher3.group(1).replace("\\", "");
                                this.res = replace2;
                                this.res = getUrlContent(replace2);
                            }
                            Matcher matcher4 = Pattern.compile("file:\"(.*?)\"").matcher(this.res);
                            if (matcher4.find()) {
                                String group2 = matcher4.group(1);
                                this.res = group2;
                                this.afaki.parsed = group2;
                                return null;
                            }
                            JSONArray jSONArray = new JSONObject(this.res).getJSONArray("sources");
                            String str2 = "";
                            for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                                if (jSONArray.getJSONObject(r2).getString(HtmlLabel.TAG_NAME).equals("360p") && !str2.equals("720p")) {
                                    str2 = jSONArray.getJSONObject(r2).getString("file");
                                } else if (jSONArray.getJSONObject(r2).getString(HtmlLabel.TAG_NAME).equals("480p") && !str2.equals("720p")) {
                                    str2 = jSONArray.getJSONObject(r2).getString("file");
                                } else if (jSONArray.getJSONObject(r2).getString(HtmlLabel.TAG_NAME).equals("720p")) {
                                    str2 = jSONArray.getJSONObject(r2).getString("file");
                                }
                                if (str2.equals("")) {
                                    str2 = jSONArray.getJSONObject(r2).getString("file");
                                }
                            }
                            if (str2.equals("")) {
                                return null;
                            }
                            this.afaki.parsed = str2;
                            return null;
                        }
                        if (strArr[0].contains("plus4")) {
                            this.afaki.parsed = "";
                            String str3 = strArr[0];
                            if (str3.startsWith("/player/")) {
                                str3 = "https://sezonlukdizi.vip" + str3;
                            }
                            this.res = getUrlContent(str3);
                            Matcher matcher5 = Pattern.compile("\"file\":\"(.*?)\"").matcher(this.res);
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            while (matcher5.find()) {
                                arrayList.add(matcher5.group(1));
                            }
                            Matcher matcher6 = Pattern.compile("\"label\":\"(.*?)\"").matcher(this.res);
                            while (matcher6.find()) {
                                arrayList2.add(matcher6.group(1));
                            }
                            for (int r7 = 0; r7 < arrayList2.size(); r7++) {
                                this.afaki.streamUrls.put(arrayList2.get(r7), arrayList.get(r7));
                            }
                            return null;
                        }
                        if (strArr[0].contains("upstream")) {
                            Matcher matcher7 = Pattern.compile("text/javascript'>(.*?)</script>").matcher(this.res);
                            if (!matcher7.find()) {
                                return null;
                            }
                            this.res = JSUnpacker.Unpack(matcher7.group(1));
                            Matcher matcher8 = Pattern.compile("file:\"(.*?mp4)\"").matcher(this.res);
                            if (matcher8.find()) {
                                this.afaki.parsed = matcher8.group(1);
                                return null;
                            }
                            Matcher matcher9 = Pattern.compile("file:\"(.*?)\"").matcher(this.res);
                            if (!matcher9.find()) {
                                return null;
                            }
                            this.afaki.parsed = matcher9.group(1);
                            return null;
                        }
                        if (strArr[0].contains("streamsb")) {
                            URL url = new URL(strArr[0]);
                            this.afaki.referer = url.getProtocol() + "://" + url.getHost();
                            this.res = getUrlContent("https://" + url.getHost() + "/sources50/" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + strArr[0].split("/")[4].replace(".html", "") + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8") + "/" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + makeID(12) + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8") + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8"));
                            JSONObject jSONObject3 = new JSONObject(this.res);
                            try {
                                try {
                                    this.afaki.parsed = jSONObject3.getJSONObject("stream_data").getString("file");
                                    return null;
                                } catch (Exception unused) {
                                    this.afaki.parsed = jSONObject3.getJSONObject("stream_data").getString("backup");
                                    return null;
                                }
                            } catch (Exception unused2) {
                                this.afaki.parsed = "";
                                return null;
                            }
                        }
                        if (strArr[0].contains("videoseyred")) {
                            this.afaki.parsed = strArr[0];
                            Matcher matcher10 = Pattern.compile("playlistUrl='(.*?)'").matcher(this.res);
                            if (!matcher10.find()) {
                                return null;
                            }
                            String str4 = "https://videoseyred.in" + matcher10.group(1);
                            this.res = str4;
                            this.res = getUrlContent(str4);
                            System.out.println(this.res);
                            JSONArray jSONArray2 = new JSONArray(this.res);
                            this.afaki.streamUrl = jSONArray2.getJSONObject(0).getJSONArray("sources").getJSONObject(0).getString("file");
                            int r22 = 0;
                            for (int r3 = 0; r22 < jSONArray2.getJSONObject(r3).getJSONArray("tracks").length(); r3 = 0) {
                                if (jSONArray2.getJSONObject(r3).getJSONArray("tracks").getJSONObject(r22).has(HtmlLabel.TAG_NAME) && jSONArray2.getJSONObject(r3).getJSONArray("tracks").getJSONObject(r22).getString(HtmlLabel.TAG_NAME).contains("Türkçe")) {
                                    this.afaki.subtitle = jSONArray2.getJSONObject(0).getJSONArray("tracks").getJSONObject(r22).getString("file");
                                }
                                r22++;
                            }
                            return null;
                        }
                        if (!strArr[0].contains("streamlare")) {
                            return null;
                        }
                        Matcher matcher11 = Pattern.compile("/(?:e|v)/([0-9A-Za-z]+)").matcher(strArr[0]);
                        URL url2 = new URL(strArr[0]);
                        if (!matcher11.find()) {
                            return null;
                        }
                        String str5 = "https://" + url2.getHost() + "/api/video/download/get";
                        this.res = getUrlContentPost("https://" + url2.getHost() + "/api/video/stream/get", "{\"id\": \"" + matcher11.group(1) + "\"}");
                        this.res = getUrlContentPost(str5, "{\"id\": \"" + matcher11.group(1) + "\"}");
                        this.afaki.streamUrl = new JSONObject(this.res).getJSONObject("result").getJSONObject("Original").getString("url");
                        this.afaki.parsed = strArr[0];
                        return null;
                    } catch (Exception unused3) {
                        return null;
                    }
                }
                Matcher matcher12 = Pattern.compile("file:\"(.*?)\",", 32).matcher(this.res);
                while (matcher12.find()) {
                    String group3 = matcher12.group(1);
                    this.res = group3;
                    this.afaki.parsed = group3;
                }
                return null;
            }
            Matcher matcher13 = Pattern.compile("<iframe(.*?)</iframe", 32).matcher(this.res);
            while (matcher13.find()) {
                try {
                    if (!matcher13.group(1).contains("display: none")) {
                        String group4 = matcher13.group(1);
                        this.res = group4;
                        String str6 = Helper.pregMatchAll("src=['\"](.*?)['\"]", group4).get(0);
                        this.res = str6;
                        this.afaki.parsed = str6;
                    }
                } catch (Exception unused4) {
                    this.afaki.parsed = "";
                }
            }
            try {
                if (strArr[0].contains("api/drive")) {
                    this.res = this.res.replace("ydx", "dbx");
                    this.res += "/sheila";
                } else if (strArr[0].contains("api/moly")) {
                    this.afaki.parsed = this.res;
                } else if (strArr[0].contains("api/ruplay")) {
                    System.out.println(strArr[0]);
                    String str7 = strArr[0];
                    Matcher matcher14 = Pattern.compile("v=(.*?)", 32).matcher(this.res);
                    while (matcher14.find()) {
                        String group5 = matcher14.group(1);
                        this.res = group5;
                        this.res = new String(Base64.decode(group5, 0));
                    }
                    String urlContent = getUrlContent(this.res);
                    this.res = urlContent;
                    if (urlContent.contains("data-options")) {
                        Matcher matcher15 = Pattern.compile("data-options=\"(.*)").matcher(this.res);
                        if (matcher15.find()) {
                            try {
                                this.res = new JSONObject(new JSONObject(matcher15.group(1).replace(StringUtils.SPACE, "").replace("\"", "").replace("&quot;", "\"").replace("u0026", "&")).getJSONObject("flashvars").getString("metadata").split(",\"metadataEmbedded")[0] + StringSubstitutor.DEFAULT_VAR_END).getJSONArray("videos").toString();
                            } catch (Exception unused5) {
                            }
                        }
                    }
                } else if (strArr[0].contains("api/saru")) {
                    String str8 = "https://api.saruch.co/videos/" + this.res.split("/")[4] + "/stream";
                    this.res = str8;
                    this.res = getUrlContent(str8);
                    Matcher matcher16 = Pattern.compile("\"file\":\"(.*?)\"", 32).matcher(this.res);
                    String str9 = "";
                    for (int r16 = 0; matcher16.find() && r16 == 0; r16++) {
                        str9 = matcher16.group(1);
                    }
                    Matcher matcher17 = Pattern.compile("\"de\":\"(.*?)\",\"en\":\"(.*?)\"", 32).matcher(this.res);
                    while (matcher17.find()) {
                        str9 = str9 + "?de=" + matcher17.group(1) + "&en=" + matcher17.group(2);
                        this.res = str9;
                        this.res = str9.replace("\\", "");
                    }
                } else if (strArr[0].contains("api/goo")) {
                    this.res = getUrlContent(this.res);
                    Matcher matcher18 = Pattern.compile("type\\|(.*?)'.split", 32).matcher(this.res);
                    while (matcher18.find()) {
                        String group6 = matcher18.group(1);
                        this.res = group6;
                        String[] split = group6.split("\\|");
                        this.res = "https://" + split[1] + ".gounlimited.to/" + split[0] + "/v.mp4";
                        System.out.println(this.res);
                    }
                }
                this.afaki.parsed = this.res;
                return null;
            } catch (Exception unused6) {
                this.afaki.parsed = "";
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(final String str) {
        try {
            this.line = "";
            Thread thread = new Thread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetYabanciDizi.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        URL url = new URL(str);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        if (str.contains("yabancidizi")) {
                            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent").replace("Android", ""));
                            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                            httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE_LC, "application/x-www-form-urlencoded; charset=UTF-8");
                            httpURLConnection.setRequestProperty("Cookie", GetYabanciDizi.this.afaki.cookie);
                            httpURLConnection.setRequestProperty("Referer", "https://" + url.getHost() + "/dizi");
                        } else {
                            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent").replace("Android", ""));
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
                            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
                            if (url.getHost().contains("streamsb")) {
                                httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + url.getHost());
                                httpURLConnection.setRequestProperty("watchsb", "sbstream");
                            } else {
                                httpURLConnection.setRequestProperty("Referer", "https://yabancidizi.pw/dizi/");
                            }
                            if (str.contains("dizilab")) {
                                httpURLConnection.setRequestProperty("Referer", "dizilab.pw");
                            } else if (str.contains("sezonlukdizi")) {
                                httpURLConnection.setRequestProperty("Referer", str);
                            } else if (str.contains("upstream")) {
                                httpURLConnection.setRequestProperty("Referer", "https://sezonlukdizi.vip");
                            } else {
                                httpURLConnection.setRequestProperty("Referer", str);
                            }
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
                                GetYabanciDizi.this.line = stringBuffer.toString();
                                return;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            thread.start();
            for (int r2 = 0; this.line.equals("") && r2 < 100; r2++) {
                Thread.sleep(100L);
                if (VideoView.isDestroyed) {
                    return "";
                }
            }
            if (thread.isAlive()) {
                thread.interrupt();
            }
            return this.line;
        } catch (Exception unused) {
            return getUrlContent(str.replace("https:", "http:"));
        }
    }

    private String makeID(int r6) {
        Random random = new Random();
        String str = "";
        for (int r2 = 0; r2 < r6; r2++) {
            str = str + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62));
        }
        return str;
    }

    public static String bytesToHex(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            int r2 = bArr[r1] & 255;
            int r3 = r1 * 2;
            byte[] bArr3 = HEX_ARRAY;
            bArr2[r3] = bArr3[r2 >>> 4];
            bArr2[r3 + 1] = bArr3[r2 & 15];
        }
        return new String(bArr2, StandardCharsets.UTF_8);
    }

    private String getUrlContentPost(final String str, final String str2) {
        try {
            this.line = "";
            Thread thread = new Thread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetYabanciDizi.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String str3 = "vid=" + str2 + "&tip=0&type=loadVideo";
                        if (str.contains("streamlare")) {
                            str3 = str2;
                        }
                        byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
                        int length = bytes.length;
                        URL url = new URL(str);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setRequestMethod("POST");
                        if (str.contains("yabancidizi")) {
                            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
                            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                            httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE_LC, "application/x-www-form-urlencoded; charset=UTF-8");
                            httpURLConnection.setRequestProperty("Cookie", "udys");
                            httpURLConnection.setRequestProperty("Referer", "https://" + url.getHost() + "/dizi");
                        } else if (str.contains("streamlare")) {
                            httpURLConnection.setRequestProperty("Content-Type", FastJsonJsonView.DEFAULT_CONTENT_TYPE);
                            httpURLConnection.setRequestProperty("Referer", "https://" + url.getHost());
                            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
                        } else {
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
                            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
                            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, str);
                            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
                        }
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            dataOutputStream.write(bytes);
                            dataOutputStream.close();
                            GetYabanciDizi.this.line = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
                        } finally {
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            thread.start();
            for (int r5 = 0; this.line.equals("") && r5 < 100; r5++) {
                Thread.sleep(100L);
                if (VideoView.isDestroyed) {
                    return "";
                }
            }
            if (thread.isAlive()) {
                thread.interrupt();
            }
            return this.line;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetYabanciDizi) str);
        this.afaki.resumeParse();
    }
}
