package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.swenauk.mainmenu.Parsers.WFilmizle;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetWFilmizle extends AsyncTask<String, String, String> {
    WFilmizle afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetWFilmizle(WFilmizle wFilmizle) {
        this.afaki = wFilmizle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String str;
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
            if (this.afaki.isAlt) {
                Matcher matcher = Pattern.compile("keremiya_part(.*?)wide-button", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                new ArrayList();
                new ArrayList();
                this.alternates.put("WHDPlayer", strArr[0]);
                Matcher matcher2 = Pattern.compile("<a href=\"(.*?)\" class=\"post-page-numbers\"><span>(.*?)</span>", 32).matcher(matcher.group(1));
                while (matcher2.find()) {
                    if (!matcher2.group(2).toLowerCase().equals("fragman")) {
                        this.alternates.put(matcher2.group(2), matcher2.group(1));
                    }
                }
                if (this.alternates.size() != 1) {
                    return null;
                }
                WFilmizle wFilmizle = this.afaki;
                Map<String, String> map = this.alternates;
                wFilmizle.oneLinkWonder = map.get(map.keySet().toArray()[0]);
                return null;
            }
            Matcher matcher3 = Pattern.compile("<iframe loading=\"lazy\".*?src=\"(.*?)\"", 32).matcher(this.res);
            if (matcher3.find()) {
                this.res = matcher3.group(1);
                return null;
            }
            Matcher matcher4 = Pattern.compile("<iframe.*?src=[\"'](.*?)[\"']").matcher(this.res);
            if (!matcher4.find()) {
                return null;
            }
            String group = matcher4.group(1);
            this.res = group;
            if (group.startsWith("//")) {
                this.res = "https:" + this.res;
            }
            if (this.res.contains("/v/")) {
                String[] split = this.res.split("/");
                split[2] = "fembed.com";
                this.res = "";
                for (int r3 = 0; r3 < split.length; r3++) {
                    if (r3 != 0) {
                        this.res += "/";
                    }
                    this.res += split[r3];
                }
            }
            if (this.res.contains("hdplayersystem.live")) {
                if (this.res.contains("/video/")) {
                    str = this.res.replace("https://hdplayersystem.live/video/", "");
                } else {
                    str = this.res.split("data=")[1];
                }
                String str2 = "https://hdplayersystem.live/player/index.php?data=" + str + "&do=getVideo";
                this.res = getUrlContentPost(str2, "{hash:\"" + str + "\", r:\"" + str2 + "\"}", false, str2);
                try {
                    this.afaki.mainUrl = new JSONObject(this.res).getString("videoSource");
                } catch (Exception unused) {
                }
            } else {
                this.afaki.mainUrl = this.res;
            }
            if (!this.res.contains("protonvideo")) {
                return null;
            }
            Matcher matcher5 = Pattern.compile("iframe/(.*?)(?:/|$)").matcher(this.res);
            String group2 = matcher5.find() ? matcher5.group(1) : "";
            this.res = getUrlContentPost("https://api.svh-api.ch/api/v4/player", "idi=" + group2 + "&token=" + getUrlContent("http://bandira.tk?str=" + matcher5.group(1)), true, "https://protonvideo.to/");
            Matcher matcher6 = Pattern.compile("\"file\":\"(.*?)\"").matcher(this.res);
            if (!matcher6.find()) {
                return null;
            }
            this.afaki.mainUrl = matcher6.group(1).replaceAll("\\[\\d*p\\]", "");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.swenauk.mainmenu.GetsPack.GetWFilmizle.1
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
            URL url = new URL(str);
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            if (str.contains("hdplayersystem.live")) {
                httpURLConnection.setRequestProperty("Referer", this.afaki.firstUrl);
            }
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetWFilmizle) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }

    private String getUrlContentPost(String str, String str2, boolean z, String str3) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, str3);
            if (z) {
                httpURLConnection.setRequestProperty("Content-Length", "117");
            } else {
                httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection.setRequestProperty("x-requested-with", "XMLHttpRequest");
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
