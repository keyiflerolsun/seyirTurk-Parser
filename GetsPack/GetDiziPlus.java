package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.DiziPlus;
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
public class GetDiziPlus extends AsyncTask<String, String, String> {
    DiziPlus afaki;
    String res;

    public GetDiziPlus(DiziPlus diziPlus) {
        this.afaki = diziPlus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            if (this.afaki.isFirst) {
                Matcher matcher = Pattern.compile("data-hhs=\"(.*?)\"").matcher(this.res);
                if (strArr[0].contains("dizipal")) {
                    matcher = Pattern.compile("data-src=\"(.*?)\"").matcher(this.res);
                }
                if (matcher.find()) {
                    this.afaki.referer = matcher.group(1).replace("&#038;", "&");
                    this.res = matcher.group(1).replace("&#038;", "&");
                }
                if (this.res.contains("flowcdn")) {
                    if (this.res.contains("dizi") && this.res.contains("plus")) {
                        this.res = getUrlContent(this.res);
                        Matcher matcher2 = Pattern.compile("<iframe src=\"(.*?)\"").matcher(this.res);
                        if (matcher2.find()) {
                            this.res = getUrlContent(matcher2.group(1));
                        }
                    } else {
                        this.res = getUrlContent(this.res);
                    }
                    Matcher matcher3 = Pattern.compile("\\(\\{.*?:\\[\\{.*?:\".*?://.*?/.*?/(.*?)/,").matcher(this.res);
                    if (matcher3.find()) {
                        this.afaki.adder = matcher3.group(1);
                    }
                    this.afaki.parsed = this.res;
                } else {
                    this.afaki.isFirst = false;
                    this.res = getUrlContent(this.res);
                    Matcher matcher4 = Pattern.compile("source:\\s*\"(.*?)\",", 32).matcher(this.res);
                    while (matcher4.find()) {
                        this.afaki.parsed = matcher4.group(1);
                    }
                    Matcher matcher5 = Pattern.compile("sources:\\[\\{file:\"(.*?)\"\\}\\]", 32).matcher(this.res);
                    while (matcher5.find()) {
                        this.afaki.parsed = matcher5.group(1);
                    }
                }
            } else {
                this.afaki.parsed = null;
                Matcher matcher6 = Pattern.compile("#EXT-X-MEDIA:.*?LANGUAGE=\"(.*?)\".*?AUTOSELECT=(.*?),.*?URI=\"(.*?)\"").matcher(this.res);
                while (matcher6.find()) {
                    if (this.afaki.isTurkish) {
                        matcher6.group(1);
                        if (matcher6.group(1).equals(HtmlTableRow.TAG_NAME)) {
                            this.afaki.parsed = matcher6.group(3);
                            if (matcher6.group(2).equals("YES")) {
                                this.afaki.isAuto = true;
                            }
                        }
                    } else if (!matcher6.group(1).equals(HtmlTableRow.TAG_NAME)) {
                        this.afaki.parsed = matcher6.group(3);
                        if (matcher6.group(2).equals("YES")) {
                            this.afaki.isAuto = true;
                        }
                    }
                }
                Matcher matcher7 = Pattern.compile(",AUDIO=\"audio0\"(.*?)#EXT-X").matcher(this.res);
                if (matcher7.find()) {
                    this.afaki.videoUrl = matcher7.group(1);
                }
            }
        } catch (Exception unused) {
        }
        return null;
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
            httpURLConnection.setRequestProperty("Referer", str);
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
        super.onPostExecute((GetDiziPlus) str);
        if (this.afaki.isFirst) {
            this.afaki.resumeParse();
        } else {
            this.afaki.secondTimeResume();
        }
    }
}
