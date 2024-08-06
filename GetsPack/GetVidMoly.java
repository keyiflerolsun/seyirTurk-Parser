package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.VidMoly;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetVidMoly extends AsyncTask<String, String, String> {
    VidMoly afaki;
    String res;

    public GetVidMoly(VidMoly vidMoly) {
        this.afaki = vidMoly;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            if (!strArr[0].contains("http")) {
                strArr[0] = "https:" + strArr[0];
            }
            if (strArr[0].contains(".to")) {
                strArr[0] = strArr[0].replace(".to", ".me");
            }
            this.res = getUrlContent(strArr[0]);
            if (strArr[0].contains("fasturl")) {
                Matcher matcher = Pattern.compile("<iframe.*?src=['\"](.*?)['\"]").matcher(this.res);
                if (matcher.find()) {
                    this.res = getUrlContent(matcher.group(1));
                }
            }
            if (this.res.contains("The document has moved")) {
                Matcher matcher2 = Pattern.compile("a href\\s*=\\s*\"(.*?)\">").matcher(this.res);
                if (matcher2.find()) {
                    this.res = getUrlContent(matcher2.group(1));
                }
            }
            String str = strArr[0];
            for (String str2 : this.res.split("\n")) {
                if (str2.contains("window.location")) {
                    try {
                        Matcher matcher3 = Pattern.compile("window.location\\s*=\\s*'(.*?)'").matcher(str2);
                        if (matcher3.find()) {
                            str = str.replace("embed-", matcher3.group(1));
                            this.res = getUrlContent(str);
                        }
                    } catch (Exception unused) {
                    }
                }
                if (this.res.equals(str)) {
                    this.res = getUrlContent(str);
                }
                Matcher matcher4 = Pattern.compile("([^\"]+\\.mp4)").matcher(this.res);
                Matcher matcher5 = Pattern.compile(",label:\"(.*?)\"").matcher(this.res);
                int r8 = 0;
                int r9 = 0;
                while (matcher4.find()) {
                    while (matcher5.find()) {
                        if (r8 == r9) {
                            this.afaki.streamUrls.put(matcher5.group(1), matcher4.group(1));
                            r9++;
                        }
                        r8++;
                    }
                }
                if (this.afaki.streamUrls.size() < 1) {
                    Matcher matcher6 = Pattern.compile("([^\"]+\\.m3u8)").matcher(str2);
                    if (matcher6.find()) {
                        this.afaki.streamUrls.put("m3u8", matcher6.group(1));
                    }
                }
                Matcher matcher7 = Pattern.compile("file\\s*:\\s*\"(/srt.*?)\"").matcher(str2);
                if (matcher7.find()) {
                    this.afaki.isSub = true;
                    this.afaki.subLink = matcher7.group(1);
                }
            }
            this.afaki.parsed = this.res;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            CookieHandler.setDefault(new CookieManager());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("sec-fetch-dest", HtmlInlineFrame.TAG_NAME);
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + url.getHost());
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
        super.onPostExecute((GetVidMoly) str);
        this.afaki.resumeParse();
    }
}
