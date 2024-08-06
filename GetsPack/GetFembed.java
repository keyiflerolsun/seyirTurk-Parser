package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.Fembed;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.client.RedirectProtocolHandler;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetFembed extends AsyncTask<String, String, String> {
    Fembed afaki;
    String res;

    public GetFembed(Fembed fembed) {
        this.afaki = fembed;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            String replace = getUrlContent(strArr[0]).replace("/v/", "/api/source/");
            Matcher matcher = Pattern.compile("(?:http://|//)(.*?)/").matcher(replace);
            String urlContentPost = getUrlContentPost(replace, "d=" + (matcher.find() ? matcher.group(1) : "") + "&r=");
            this.res = urlContentPost;
            this.res = urlContentPost.replace("\\\\", "");
            Matcher matcher2 = Pattern.compile("\"file\":\"([^\"]+)\",\"label\":\"([^\"]+)\"").matcher(this.res);
            String str = "";
            while (matcher2.find()) {
                this.afaki.streamUrls.put(matcher2.group(2), matcher2.group(1));
                str = matcher2.group(1);
            }
            if (this.afaki.streamUrls.size() != 1) {
                return null;
            }
            this.afaki.streamUrls.clear();
            if (str.contains(RedirectProtocolHandler.NAME)) {
                this.afaki.streamUrl = getRedirectUrl(str.replace("\\", ""));
                return null;
            }
            this.afaki.streamUrl = str;
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
            httpURLConnection.setRequestProperty("Referer", str);
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
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            httpURLConnection.setRequestProperty("Referer", str);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            httpURLConnection.getInputStream();
            return httpURLConnection.getURL().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String getUrlContentPost(String str, String str2) {
        String str3;
        try {
            String str4 = "http://feurl.com";
            if (str2.equals("")) {
                str2 = "Referer=http://feurl.com";
                str3 = "";
            } else {
                str3 = "https://femax20.com";
                str4 = str;
            }
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("User-Agent", "PostmanRuntime/7.22.0");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            httpURLConnection.setRequestProperty("Referer", str4);
            if (!str3.equals("")) {
                httpURLConnection.setRequestProperty("Origin", str3);
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
        super.onPostExecute((GetFembed) str);
        this.afaki.resumeParse();
    }
}
