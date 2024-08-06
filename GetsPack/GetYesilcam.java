package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.Yesilcam;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetYesilcam extends AsyncTask<String, String, String> {
    Yesilcam afaki;
    String res;
    Map<String, String> streamUrls = new HashMap();

    public GetYesilcam(Yesilcam yesilcam) {
        this.afaki = yesilcam;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            System.out.println(this.res);
            Matcher matcher = Pattern.compile("source\\s*src=\"(.*?)\"\\s*type='.*?'\\s*label\\s*='(.*?)'", 32).matcher(this.res);
            if (strArr[0].contains("gounlimited.to")) {
                matcher = Pattern.compile("type\\|(.*?)'.split", 32).matcher(this.res);
            }
            while (matcher.find()) {
                try {
                    String group = matcher.group(1);
                    this.streamUrls.put(matcher.group(2), group);
                } catch (Exception unused) {
                    this.afaki.parsed = null;
                }
            }
            this.afaki.parsed = this.streamUrls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Referer", "http://www.atv.com.tr/");
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
            System.out.println(e);
            return e.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetYesilcam) str);
        this.afaki.resumeParse();
    }
}
