package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Webteizle;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetWebteizle extends AsyncTask<String, String, String> {
    Webteizle afaki;
    String res;
    String isDub = "0";
    Map<String, String> alternates = new HashMap();

    public GetWebteizle(Webteizle webteizle) {
        this.afaki = webteizle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String str;
        String str2;
        try {
            if (strArr[0].contains("altyazi")) {
                this.isDub = "1";
            }
            this.res = getUrlContent(strArr[0]);
            int r8 = 1;
            if (this.afaki.isAlt) {
                Matcher matcher = Pattern.compile("data-id=\"(.*?)\"", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                String str3 = "filmid=" + matcher.group(1) + "&dil=" + this.isDub;
                int r11 = 2;
                if (strArr[0].contains("sezon")) {
                    Matcher matcher2 = Pattern.compile("(\\d*)-sezon-(\\d*)-").matcher(strArr[0]);
                    if (matcher2.find()) {
                        str3 = str3 + "&s=" + matcher2.group(1) + "&b=" + matcher2.group(2);
                    }
                }
                String str4 = str3 + "&bot=0";
                URL url = new URL(strArr[0]);
                this.res = getUrlContent(url.getProtocol() + "://" + getBaseDomain(url.getHost()) + "/js/site.min.js");
                Matcher matcher3 = Pattern.compile("#embed'\\)\\.addClass\\('loading'\\);\\$\\.post\\(\"/(.*?)\"", 32).matcher(this.res);
                String group = matcher3.find() ? matcher3.group(1) : "";
                Matcher matcher4 = Pattern.compile("s,b\\)\\{\\$.post\\('/(.*?)'", 32).matcher(this.res);
                this.res = getUrlContentPost(url.getProtocol() + "://" + getBaseDomain(url.getHost()) + "/" + (matcher4.find() ? matcher4.group(1) : ""), str4);
                try {
                    JSONArray jSONArray = new JSONObject(this.res).getJSONArray("data");
                    int r9 = 0;
                    while (r9 < jSONArray.length()) {
                        this.res = getUrlContentPost(url.getProtocol() + "://" + getBaseDomain(url.getHost()) + "/" + group, "id=" + jSONArray.getJSONObject(r9).getString(TtmlNode.ATTR_ID));
                        Matcher matcher5 = Pattern.compile("<script>(.*?)\\('(.*?)',.*?\\);</script>").matcher(this.res);
                        if (matcher5.find()) {
                            str2 = matcher5.group(r8);
                            str = matcher5.group(r11);
                        } else {
                            Matcher matcher6 = Pattern.compile("/player/video.asp\\?v=(.*?)\"").matcher(this.res);
                            if (matcher6.find()) {
                                str2 = "Qiwi";
                                str = matcher6.group(r8);
                            } else {
                                str = "";
                                str2 = str;
                            }
                        }
                        if (str2 != null && !str2.equals("")) {
                            if (str2.equals("uptobox")) {
                                this.alternates.put("UP", "https://uptostream.com/iframe/" + str);
                            } else if (str2.equals("sper")) {
                                this.alternates.put("Super", "https://supervideo.tv/e/" + str);
                            } else if (str2.equals("fembed")) {
                                this.alternates.put("fembed", "https://www.fembed.net/v/" + str);
                            } else if (str2.equals("vidmoly")) {
                                this.alternates.put("VidMoly", "https://vidmoly.me/embed-" + str + ".html");
                            } else if (str2.equals("mailru")) {
                                String[] split = str.split("/");
                                this.alternates.put("mailru", "https://my.mail.ru/" + split[0] + "/" + split[1] + "/video/embed/" + split[2] + "/" + split[3]);
                            } else if (str2.equals("okru")) {
                                this.alternates.put("ODK", "https://odnoklassniki.ru/videoembed/" + str);
                            } else if (str2.equals("streamlare")) {
                                this.alternates.put("Streamlare", "https://streamlare.com/e/" + str);
                            } else if (str2.equals("streamsb")) {
                                this.alternates.put("StreamSB", "https://streamsb.net/e/" + str + ".html");
                            } else if (str2.equals("filemoon")) {
                                this.alternates.put("Filemoon", "https://filemoon.sx/e/" + str);
                            }
                        }
                        r9++;
                        r8 = 1;
                        r11 = 2;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (this.alternates.size() != 1) {
                    return null;
                }
                Webteizle webteizle = this.afaki;
                Map<String, String> map = this.alternates;
                webteizle.oneLinkWonder = map.get(map.keySet().toArray()[0]);
                return null;
            }
            this.res = getUrlContent(strArr[0].replace("l=0", "").replace("l=1", ""));
            Matcher matcher7 = Pattern.compile("<p><iframe.*?data-src=(.*?) \\s*.*?></iframe></p>").matcher(this.res);
            if (matcher7.find()) {
                this.afaki.mainUrl = matcher7.group(1);
                return null;
            }
            this.afaki.mainUrl = strArr[0];
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            System.out.println(url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
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
            return e.getMessage();
        }
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            str.contains("www");
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36");
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.getRequestProperties();
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String str3 = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return str3;
                    }
                    str3 = str3 + readLine;
                }
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
        super.onPostExecute((GetWebteizle) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }

    public static String getBaseDomain(String str) {
        int indexOf = str.indexOf(46);
        int lastIndexOf = str.lastIndexOf(46);
        int r3 = 0;
        while (indexOf < lastIndexOf) {
            r3 = indexOf + 1;
            indexOf = str.indexOf(46, r3);
        }
        return r3 > 0 ? str.substring(r3) : str;
    }
}
