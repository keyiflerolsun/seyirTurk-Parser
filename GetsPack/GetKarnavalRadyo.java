package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.KarnavalRadyo;
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
public class GetKarnavalRadyo extends AsyncTask<String, String, String> {
    KarnavalRadyo afaki;
    String res;

    public GetKarnavalRadyo(KarnavalRadyo karnavalRadyo) {
        this.afaki = karnavalRadyo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String group;
        try {
            this.res = getUrlContent(strArr[0]);
            if (strArr[0].contains("canliradyolar.org")) {
                Matcher matcher = Pattern.compile("iframe\\s*src=\"(.*?)\"\\s*name=", 32).matcher(this.res);
                group = matcher.find() ? matcher.group(1) : "";
                this.res = getUrlContent(group);
                Matcher matcher2 = Pattern.compile("\"m4a\"\\s*:\\s*\"(.*?);", 32).matcher(this.res);
                if (matcher2.find()) {
                    group = matcher2.group(1);
                }
                this.afaki.parsed = group;
                System.out.println(group);
                return null;
            }
            if (strArr[0].contains("radyohome.com")) {
                Matcher matcher3 = Pattern.compile("\"hls\",\"url\":\"(.*?)\"", 32).matcher(this.res);
                if (!matcher3.find()) {
                    return null;
                }
                this.afaki.parsed = matcher3.group(1).replace("\\/", "/");
                return null;
            }
            if (strArr[0].contains("onlineradiobox.com")) {
                Matcher matcher4 = Pattern.compile("stream=\"(.*?)\"", 32).matcher(this.res);
                if (!matcher4.find()) {
                    return null;
                }
                this.afaki.parsed = matcher4.group(1);
                return null;
            }
            if (strArr[0].contains("radyodelisi")) {
                Matcher matcher5 = Pattern.compile("source src=\"(.*?)\"", 32).matcher(this.res);
                if (!matcher5.find()) {
                    return null;
                }
                this.afaki.parsed = matcher5.group(1);
                return null;
            }
            Matcher matcher6 = Pattern.compile("<ip>(.*?)</ip>", 32).matcher(this.res);
            String group2 = matcher6.find() ? matcher6.group(1) : "";
            Matcher matcher7 = Pattern.compile("<mount>(.*?)</mount>", 32).matcher(this.res);
            group = matcher7.find() ? matcher7.group(1) : "";
            this.afaki.parsed = "https://" + group2 + "/" + group + ".mp3";
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
        super.onPostExecute((GetKarnavalRadyo) str);
        try {
            this.afaki.resumeParse();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
