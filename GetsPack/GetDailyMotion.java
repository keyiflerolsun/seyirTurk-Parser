package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.DailyMotion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetDailyMotion extends AsyncTask<String, String, String> {
    DailyMotion afaki;
    String res;

    public GetDailyMotion(DailyMotion dailyMotion) {
        this.afaki = dailyMotion;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = strArr[0];
            if (strArr[0].contains("kanal7")) {
                this.res = getUrlContent(strArr[0]);
                Matcher matcher = Pattern.compile("<iframe.*?src=\"(https://www.izle7.com/.*?)\"").matcher(this.res);
                if (matcher.find()) {
                    this.res = getUrlContent(matcher.group(1));
                    Matcher matcher2 = Pattern.compile("play_video\\s*=\\s*\"(.*?)\"", 32).matcher(this.res);
                    if (matcher2.find()) {
                        this.res = "https://www.dailymotion.com/embed/video/" + matcher2.group(1);
                    }
                }
            }
            String replace = this.res.replace("embed/", "").replace("video", "player/metadata/video");
            this.res = replace;
            this.res = getUrlContent(replace);
            this.afaki.parsed = new JSONObject(this.res).getJSONObject("qualities").getJSONArray("auto").getJSONObject(0).getString("url");
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
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
        super.onPostExecute((GetDailyMotion) str);
        this.afaki.resumeParse();
    }
}
