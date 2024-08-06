package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.MailRU;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetMailRu extends AsyncTask<String, String, String> {
    MailRU afaki;
    String res;
    String video_key = "";

    public GetMailRu(MailRU mailRU) {
        this.afaki = mailRU;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            Matcher matcher = Pattern.compile("\"video\":(.*?),", 32).matcher(this.res);
            while (matcher.find()) {
                String group = matcher.group(1);
                this.res = group;
                String replace = group.replace("\"", "");
                this.res = replace;
                String replace2 = replace.replace("{", "");
                this.res = replace2;
                String replace3 = replace2.replace(":", "");
                this.res = replace3;
                String replace4 = replace3.replace("metadataUrl", "");
                this.res = replace4;
                this.res = replace4.replace(StringUtils.SPACE, "");
                String str = "https://my.mail.ru" + this.res;
                this.res = str;
                this.res = getUrlContent(str);
                this.afaki.video_key = this.video_key;
                this.afaki.parsed = this.res;
            }
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
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            try {
                String headerField = httpURLConnection.getHeaderField("Set-Cookie");
                if (headerField.contains("video_key")) {
                    Matcher matcher = Pattern.compile("video_key=(.*?);", 32).matcher(headerField);
                    while (matcher.find()) {
                        this.video_key = matcher.group(1);
                    }
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
        super.onPostExecute((GetMailRu) str);
        this.afaki.resumeParse();
    }
}
