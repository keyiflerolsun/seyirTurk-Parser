package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.Y2Mate;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;
import org.htmlunit.org.apache.http.client.utils.URLEncodedUtils;

/* loaded from: classes3.dex */
public class GetY2Mate extends AsyncTask<String, String, String> {
    Y2Mate afaki;
    String res;
    String you;

    public GetY2Mate(Y2Mate y2Mate) {
        this.afaki = y2Mate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            if (this.afaki.current == 0) {
                String urlContentPost = getUrlContentPost("https://www.y2mate.com/mates/analyzeV2/ajax", strArr[0], 0);
                this.res = urlContentPost;
                this.afaki.parsed = urlContentPost;
                return null;
            }
            Matcher matcher = Pattern.compile("data-id\\s*=\\s*\"(.*?)\"", 32).matcher(this.afaki.parsed);
            if (matcher.find()) {
                this.you = matcher.group(1);
            }
            Matcher matcher2 = Pattern.compile("k__id\\s*=\\s*(.*?);", 32).matcher(this.afaki.parsed);
            if (!matcher2.find()) {
                return null;
            }
            String urlContentPost2 = getUrlContentPost("https://www.y2mate.com/mates/en64/convert", matcher2.group(1).substring(1, matcher2.group(1).length() - 1), 1);
            this.res = urlContentPost2;
            this.afaki.parsed = urlContentPost2;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private String getUrlContentPost(String str, String str2, int r5) {
        String str3;
        try {
            if (r5 == 0) {
                str3 = "url=" + str2 + "&q_auto=0&ajax=1";
            } else {
                str3 = "type=youtube&_id=" + str2 + "&v_id=" + this.you + "&ajax=1&token=&ftype=mp4&fquality=" + this.afaki.selected;
            }
            byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", URLEncodedUtils.CONTENT_TYPE);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
            } finally {
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetY2Mate) str);
        if (this.afaki.current == 0) {
            this.afaki.secondParse();
        } else if (this.afaki.current == 1) {
            this.afaki.resumeParse();
        }
    }
}
