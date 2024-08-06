package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.ATV;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetATV extends AsyncTask<String, String, String> {
    ATV afaki;
    String res;

    public GetATV(ATV atv) {
        this.afaki = atv;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            System.out.println(this.res);
            Matcher matcher = Pattern.compile("url:\\s*\"(https://videojs.tmgrup.com.tr/.*?)\"").matcher(this.res);
            while (matcher.find()) {
                try {
                    this.res = getUrlContent(matcher.group(1));
                    Matcher matcher2 = Pattern.compile("\"VideoUrl\":\"([^\"]+)\".*?\"VideoSmilUrl\":\"([^\"]+)\"", 32).matcher(this.res);
                    while (matcher2.find()) {
                        String group = matcher2.group(1);
                        String str = "https://securevideotoken.tmgrup.com.tr/webtv/secure?url=" + matcher2.group(2) + "&url2=" + group;
                        this.res = str;
                        this.res = getUrlContent(str);
                        this.afaki.parsed = new JSONObject(this.res);
                    }
                } catch (Exception unused) {
                    this.afaki.parsed = null;
                }
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Mobile Safari/537.36");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("Referer", "http://www.atv.com.tr/");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine + "\n");
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetATV) str);
        this.afaki.resumeParse();
    }
}
