package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.YoutubeWGetter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetYoutube extends AsyncTask<String, String, String> {
    YoutubeWGetter afaki;
    String res;

    public GetYoutube(YoutubeWGetter youtubeWGetter) {
        this.afaki = youtubeWGetter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            int r3 = 0;
            System.out.println(strArr[0]);
            if (strArr[0].startsWith("youtubeiptvs")) {
                int parseInt = strArr[0].contains("#") ? Integer.parseInt(strArr[0].split("#")[1]) : 0;
                this.res = getUrlContent(strArr[0].replace("youtubeiptvs", "https://www.youtube.com"));
                Matcher matcher = Pattern.compile("LIVE.*?\"addedVideoId\":\"(.*?)\",\"").matcher(this.res);
                while (matcher.find()) {
                    if (r3 == parseInt) {
                        this.res = matcher.group(1);
                    }
                    r3++;
                }
                String urlContent = getUrlContent("https://m.youtube.com/watch?v=" + this.res);
                this.res = urlContent;
                this.afaki.parsed = urlContent;
                return null;
            }
            String replace = strArr[0].replace("https://www", "https://m");
            this.res = replace;
            String urlContent2 = getUrlContent(replace);
            this.res = urlContent2;
            this.afaki.parsed = urlContent2;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (str.startsWith("https://www")) {
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");
            } else {
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.101 Mobile Safari/537.36");
            }
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
        super.onPostExecute((GetYoutube) str);
        this.afaki.resumeParse();
    }
}
