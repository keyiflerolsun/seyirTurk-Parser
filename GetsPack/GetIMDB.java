package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.IMDB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetIMDB extends AsyncTask<String, String, String> {
    IMDB afaki;
    String res;

    public GetIMDB(IMDB r1) {
        this.afaki = r1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            Matcher matcher = Pattern.compile("\"embedUrl\"\\s*:\\s*\"(.*?)\"", 32).matcher(this.res);
            while (matcher.find()) {
                try {
                    String group = matcher.group(1);
                    this.res = group;
                    String replace = group.replace("/video/imdb", "");
                    while (!replace.startsWith("vi")) {
                        replace = replace.substring(1);
                    }
                    String replace2 = replace.replace("\"", "").replace(StringUtils.SPACE, "");
                    if (!this.res.contains("https")) {
                        this.res = "https://imdb.com" + this.res;
                    }
                    String replace3 = this.res.replace("video/imdb", "videoembed");
                    this.res = replace3;
                    String replace4 = replace3.replace("\"", "");
                    this.res = replace4;
                    String replace5 = replace4.replace(StringUtils.SPACE, "");
                    this.res = replace5;
                    this.res = getUrlContent(replace5);
                    Matcher matcher2 = Pattern.compile("IMDbReactInitialState.push(.*?);", 32).matcher(this.res);
                    boolean z = false;
                    while (matcher2.find()) {
                        try {
                            String group2 = matcher2.group(1);
                            this.res = group2;
                            String replace6 = group2.replace("(", "");
                            this.res = replace6;
                            this.res = replace6.replace(")", "");
                            this.afaki.parsed = new JSONObject(this.res).getJSONObject("videos").getJSONObject("videoMetadata").getJSONObject(replace2).getJSONArray("encodings");
                            z = true;
                        } catch (Exception unused) {
                            this.afaki.parsed = null;
                        }
                    }
                    if (!z) {
                        Matcher matcher3 = Pattern.compile("<script id=\"__NEXT_DATA__\" type=\"application/json\">(.*?)</script>", 32).matcher(this.res);
                        while (matcher3.find()) {
                            try {
                                String group3 = matcher3.group(1);
                                this.res = group3;
                                String replace7 = group3.replace("(", "");
                                this.res = replace7;
                                this.res = replace7.replace(")", "");
                                this.afaki.parsed = new JSONObject(this.res).getJSONObject("props").getJSONObject("pageProps").getJSONObject("videoEmbedPlaybackData").getJSONArray("playbackURLs");
                            } catch (Exception unused2) {
                                this.afaki.parsed = null;
                            }
                        }
                    }
                } catch (Exception unused3) {
                    this.afaki.parsed = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUrlContent(String str) {
        try {
            SSLContext.getInstance("SSL").init(null, new TrustManager[]{new X509TrustManager() { // from class: com.swenauk.mainmenu.GetsPack.GetIMDB.1
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
            }}, new SecureRandom());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetIMDB) str);
        this.afaki.resumeParse();
    }
}
