package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.Koreanturk;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetKorenturk extends AsyncTask<String, String, String> {
    Koreanturk afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetKorenturk(Koreanturk koreanturk) {
        this.afaki = koreanturk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            Matcher matcher = Pattern.compile("tab-content icerikler(.*?)text/css").matcher(this.res);
            if (matcher.find()) {
                Matcher matcher2 = Pattern.compile("id=\"(.*?)\".*?(?:iframe.*?src|a.*?href)=\"(.*?)\"").matcher(matcher.group(1));
                while (matcher2.find()) {
                    if (matcher2.group(2).contains("ok.ru") || matcher2.group(2).contains("vidmoly.me") || matcher2.group(2).contains("fembed.com") || matcher2.group(2).contains("videobin.co") || matcher2.group(2).contains("gounlimited.to") || matcher2.group(2).contains("dailymotion")) {
                        if (matcher2.group(2).contains("dailymotion")) {
                            Matcher matcher3 = Pattern.compile("^(.*?)\\?").matcher(matcher2.group(2));
                            if (matcher3.find()) {
                                this.alternates.put(matcher2.group(1), "https:" + matcher3.group(2));
                            }
                        } else if (matcher2.group(2).contains("vidmoly")) {
                            this.alternates.put(matcher2.group(1), "https:" + matcher2.group(2));
                        } else {
                            this.alternates.put(matcher2.group(1), matcher2.group(2));
                        }
                    }
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
            httpURLConnection.setRequestProperty("Referer", "https://filmmodu.org");
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
        super.onPostExecute((GetKorenturk) str);
        this.afaki.showAlternates(this.alternates);
    }
}
