package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Diziyo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetDiziyo extends AsyncTask<String, String, String> {
    Diziyo afaki;
    String res;

    public GetDiziyo(Diziyo diziyo) {
        this.afaki = diziyo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        this.res = getUrlContent(strArr[0]);
        try {
            URL url = new URL(strArr[0]);
            Matcher matcher = Pattern.compile("id=\"player-option-1\"\\s*class=\"(.*?)\"\\s*data-type=\"(.*?)\"\\s*data-post=\"(.*?)\"\\s*data-nume=\"(.*?)\"").matcher(this.res);
            if (matcher.find()) {
                this.res = getUrlContentPost("https://" + url.getHost() + "/wp-admin/admin-ajax.php", "action=doo_player_ajax&post=" + matcher.group(3) + "&nume=" + matcher.group(4) + "&type=" + matcher.group(2));
                Matcher matcher2 = Pattern.compile("src='(.*?)'").matcher(this.res);
                if (matcher2.find()) {
                    String[] split = matcher2.group(1).split("/");
                    this.res = getUrlContentPost(matcher2.group(1) + "?do=getVideo", "hash=" + split[split.length - 1] + "&r=https://" + url.getHost() + "/");
                    this.afaki.referer = matcher2.group(1);
                    this.res = new JSONObject(this.res).getJSONArray("videoSources").getJSONObject(0).getString("file");
                }
            }
            this.afaki.streamUrl = this.res;
            this.res = getUrlContent(this.res);
            System.out.println(this.res);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            if (str2.equals("")) {
                str2 = "";
            }
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty("bety", "jughead");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, str);
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

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            CookieHandler.setDefault(new CookieManager());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            if (!str.contains("sandalye")) {
                httpURLConnection.setRequestProperty("Referer", "https://" + url.getHost());
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
                httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            } else {
                httpURLConnection.setRequestProperty("Referer", this.afaki.referer);
            }
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
        super.onPostExecute((GetDiziyo) str);
        this.afaki.resumeParse();
    }
}
