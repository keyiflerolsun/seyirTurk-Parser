package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.DizipubPlus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetDizipubPlus extends AsyncTask<String, String, String> {
    DizipubPlus afaki;
    String res;
    String video_key;

    public GetDizipubPlus(DizipubPlus dizipubPlus) {
        this.afaki = dizipubPlus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            Boolean bool = false;
            if (!this.res.contains("src=") || bool.booleanValue()) {
                return null;
            }
            Matcher matcher = Pattern.compile("src=\"(.*)\"").matcher(this.res);
            if (!matcher.find()) {
                return null;
            }
            System.out.println("Found 1");
            String group = matcher.group(1);
            Boolean.valueOf(true);
            Matcher matcher2 = Pattern.compile("d/(.*?)/").matcher(group);
            if (!matcher2.find()) {
                return null;
            }
            String str = "https://drive.google.com/file/d/" + matcher2.group(1) + "/view";
            String urlContent = getUrlContent(str);
            System.out.println(str);
            System.out.println(urlContent);
            Matcher matcher3 = Pattern.compile("\"fmt_stream_map\",\"(.*?)\"").matcher(urlContent);
            if (!matcher3.find()) {
                return null;
            }
            this.afaki.parsed = matcher3.group(1);
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("Referer", str);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            httpURLConnection.getContent();
            try {
                for (HttpCookie httpCookie : cookieManager.getCookieStore().getCookies()) {
                    if (str.contains("drive.google")) {
                        StringBuilder sb = new StringBuilder();
                        DizipubPlus dizipubPlus = this.afaki;
                        sb.append(dizipubPlus.cookie);
                        sb.append(httpCookie);
                        sb.append(";");
                        dizipubPlus.cookie = sb.toString();
                    }
                    System.out.println(httpCookie);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
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
        } catch (Exception e2) {
            return e2.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetDizipubPlus) str);
        try {
            this.afaki.resumeParse();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
