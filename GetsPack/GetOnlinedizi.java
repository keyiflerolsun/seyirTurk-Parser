package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Onlinedizi;
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
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetOnlinedizi extends AsyncTask<String, String, String> {
    Onlinedizi afaki;
    Map<String, String> alternates = new HashMap();
    String referer = "";
    String res;

    public GetOnlinedizi(Onlinedizi onlinedizi) {
        this.afaki = onlinedizi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String str;
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
            this.referer = "https://" + new URL(strArr[0]).getHost();
            if (this.afaki.isAlt) {
                Matcher matcher = Pattern.compile("Alternatifler(.*?)episode-buttons", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                this.res = matcher.group(1);
                Matcher matcher2 = Pattern.compile("href=\"(.*?)\".*?>(.*?)<", 32).matcher(this.res);
                while (matcher2.find()) {
                    this.alternates.put(matcher2.group(2), matcher2.group(1));
                }
                return null;
            }
            Matcher matcher3 = Pattern.compile("iframe\\s*src=\"(.*?)\"", 32).matcher(this.res);
            if (!matcher3.find()) {
                return null;
            }
            String group = matcher3.group(1);
            this.res = group;
            if (group.startsWith("//")) {
                this.res = "https:" + this.res;
            }
            this.res = getUrlContent(this.res);
            Matcher matcher4 = Pattern.compile("ifsrc = \"(.*?)\"").matcher(this.res);
            if (matcher4.find()) {
                String group2 = matcher4.group(1);
                this.res = group2;
                if (group2.startsWith("//")) {
                    this.res = "https:" + this.res;
                }
            }
            String redirectUrl = getRedirectUrl(this.res);
            this.res = redirectUrl;
            if (redirectUrl.contains("gdplayer")) {
                this.res = getUrlContent(this.res);
                Matcher matcher5 = Pattern.compile("(//gdplayer.org/api/.*?)\"").matcher(this.res);
                if (!matcher5.find()) {
                    return null;
                }
                this.res = getUrlContent("http:" + matcher5.group(1));
                JSONObject jSONObject = new JSONObject(this.res);
                this.afaki.mainUrl = "http:" + jSONObject.getJSONArray("sources").getJSONObject(0).getString("file");
                return null;
            }
            if (this.res.contains("fcdn.xyz")) {
                String str2 = this.res.split("/")[4];
                String str3 = this.res + "?do=getVideo";
                this.res = str3;
                this.res = getUrlContentPost(str3, "hash=" + str2 + "&r=" + this.referer + "&s=");
                String string = new JSONObject(this.res).getJSONArray("videoSources").getJSONObject(0).getString("file");
                this.res = string;
                if (!string.contains("fcdn")) {
                    this.afaki.mainUrl = this.res;
                    return null;
                }
                this.res = getUrlContent(this.res);
                Matcher matcher6 = Pattern.compile("(https:.*?m3u8)").matcher(this.res);
                while (matcher6.find()) {
                    if (matcher6.group(1).contains("240p/playlist")) {
                        str = "240p";
                    } else if (matcher6.group(1).contains("360p/playlist")) {
                        str = "360p";
                    } else if (matcher6.group(1).contains("480p/playlist")) {
                        str = "480p";
                    } else if (matcher6.group(1).contains("720p/playlist")) {
                        str = "720p";
                    } else {
                        str = matcher6.group(1).contains("1080p/playlist") ? "1080p" : "";
                    }
                    this.alternates.put(str, matcher6.group(1));
                }
                return null;
            }
            if (this.res.contains("ondembed.xyz")) {
                this.afaki.mainUrl = this.res.replace("ondembed.xyz", "fembed.com");
                return null;
            }
            if (this.res.startsWith("//")) {
                this.res = "https:" + this.res;
            }
            this.afaki.mainUrl = this.res;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRedirectUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setRequestProperty("Referer", this.referer);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.connect();
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setRequestProperty("Referer", this.referer);
            httpURLConnection.connect();
            System.out.println("Response Code: " + httpURLConnection.getResponseCode());
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetOnlinedizi) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else if (this.alternates.size() > 0) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, this.referer);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
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
