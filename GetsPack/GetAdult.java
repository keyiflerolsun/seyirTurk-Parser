package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Adult;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* loaded from: classes3.dex */
public class GetAdult extends AsyncTask<String, String, String> {
    Adult afaki;
    String res;
    String token;

    public GetAdult(Adult adult) {
        this.afaki = adult;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0908  */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r19) {
        /*
            Method dump skipped, instructions count: 2442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetAdult.doInBackground(java.lang.String[]):java.lang.String");
    }

    private String getUrlContent(String str) {
        try {
            str.contains("xvideos");
            URL url = new URL(str);
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
            if (str.contains("pornhub") || str.contains("luluvdo")) {
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            }
            if (str.contains("streamtape")) {
                httpURLConnection.setRequestProperty("Referer", "https://streamtape.com/");
            } else if (str.contains("k2s")) {
                httpURLConnection.setRequestProperty("Referer", str);
                httpURLConnection.setRequestProperty("Origin", "https://k2s.cc/");
                httpURLConnection.setRequestProperty("Authorization", "Bearer " + this.token);
            } else if (str.contains("filemoon")) {
                if (Statics.referer.equals("")) {
                    httpURLConnection.setRequestProperty("Referer", "https://webteizle2.com/");
                } else {
                    httpURLConnection.setRequestProperty("Referer", Statics.referer);
                }
            }
            if (this.afaki.parsed != null && this.afaki.parsed != "") {
                httpURLConnection.setRequestProperty("Cookie", this.afaki.parsed);
            }
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            System.out.println(httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() == 301) {
                String headerField = httpURLConnection.getHeaderField("Location");
                System.out.println(headerField);
                return getUrlContent(headerField);
            }
            StringBuffer stringBuffer = new StringBuffer();
            try {
                List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
                this.afaki.parsed = "";
                for (HttpCookie httpCookie : cookies) {
                    if (str.contains("streamtape") || str.contains("k2s")) {
                        StringBuilder sb = new StringBuilder();
                        Adult adult = this.afaki;
                        sb.append(adult.cookie);
                        sb.append(httpCookie);
                        sb.append(";");
                        adult.cookie = sb.toString();
                    }
                    System.out.println("Cookie: " + this.afaki.cookie);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(httpURLConnection.getHeaderField("Location"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                    if (str.contains("streamtape") || str.contains("pornhub") || str.contains("mixdrop") || str.contains("luluvdo") || str.contains("streamhub") || str.contains("youporn") || str.contains("thehun")) {
                        stringBuffer.append("\n");
                    }
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e2) {
            System.out.println(e2);
            return e2.getMessage();
        }
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = (str2.equals("") ? "grant_type=client_credentials&client_id=k2s_web_app&client_secret=pjc8pyZv7vhscexepFNzmu4P" : str2).getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            if (!str2.equals("")) {
                httpURLConnection.setRequestProperty("Accept", MimeType.APPLICATION_JSON);
            } else {
                httpURLConnection.setRequestProperty("Referer", str);
                httpURLConnection.setRequestProperty("Origin", "https://k2s.cc");
            }
            if (str.contains("evoload")) {
                httpURLConnection.setRequestProperty("Referer", "https://evoload.io");
                httpURLConnection.setRequestProperty("Origin", "https://evoload.io");
            } else {
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
                httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setInstanceFollowRedirects(false);
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
        super.onPostExecute((GetAdult) str);
        this.afaki.resumeParse();
    }
}
