package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.StarTV;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetStarTV extends AsyncTask<String, String, String> {
    StarTV afaki;
    String res;

    public GetStarTV(StarTV starTV) {
        this.afaki = starTV;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (strArr[0].contains("trtcocuk")) {
            this.res = this.res.replace("\\u002F", "/");
            Matcher matcher = Pattern.compile(strArr[0].split("/")[strArr[0].split("/").length - 1] + ".*?website/(.*?m3u8)", 32).matcher(this.res);
            if (matcher.find()) {
                this.afaki.parsed = "https://cdn-v.pr.trt.com.tr/trtcocuk/website/" + matcher.group(1);
            }
        } else {
            if (!strArr[0].contains("trt1")) {
                Matcher matcher2 = Pattern.compile("\"videoUrl\".*?:.*?\"(.*?)\"", 32).matcher(this.res);
                while (matcher2.find()) {
                    try {
                        String group = matcher2.group(1);
                        this.res = group;
                        if (group.contains("http")) {
                            this.res = getUrlContent(this.res);
                            this.afaki.parsed = new JSONObject(this.res).getJSONObject("data").getJSONObject("flavors").getString("hls");
                        }
                    } catch (Exception unused) {
                        this.afaki.parsed = null;
                    }
                }
            } else {
                Matcher matcher3 = Pattern.compile("src:\\s*'(.*?\\.mp4)", 32).matcher(this.res);
                if (matcher3.find()) {
                    try {
                        this.afaki.parsed = matcher3.group(1);
                        if (!this.afaki.parsed.contains("http")) {
                            this.afaki.parsed = "https:" + this.afaki.parsed;
                        }
                    } catch (Exception unused2) {
                        this.afaki.parsed = null;
                    }
                }
            }
            e.printStackTrace();
        }
        return null;
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
        super.onPostExecute((GetStarTV) str);
        this.afaki.resumeParse();
    }
}
