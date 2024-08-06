package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.TLC;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetTLC extends AsyncTask<String, String, String> {
    TLC afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetTLC(TLC tlc) {
        this.afaki = tlc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
            Pattern.compile("keremiya_part(.*?)wide-button", 32).matcher(this.res);
            if (strArr[0].contains("canli-izle")) {
                Matcher matcher = Pattern.compile("(?:daionUrl|liveUrl)\\s*(?:=|\\:)\\s*(?:\\'|\")(.*?)(?:\\'|\")").matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                this.afaki.streamUrl = matcher.group(1);
                return null;
            }
            if (strArr[0].contains("dmax")) {
                Matcher matcher2 = Pattern.compile("referenceId\\s*:\\s*'(.*?)'").matcher(this.res);
                String redirectUrl = getRedirectUrl("https://dygvideo.dygdigital.com/api/redirect?PublisherId=27&ReferenceId=" + (matcher2.find() ? matcher2.group(1) : "") + "&SecretKey=NtvApiSecret2014*");
                this.res = redirectUrl;
                this.afaki.streamUrl = redirectUrl;
                return null;
            }
            if (!strArr[0].contains("tlctv")) {
                return null;
            }
            Matcher matcher3 = Pattern.compile("referenceId\\s*:\\s*'(.*?)'").matcher(this.res);
            String redirectUrl2 = getRedirectUrl("https://dygvideo.dygdigital.com/api/redirect?PublisherId=20&ReferenceId=" + (matcher3.find() ? matcher3.group(1) : "") + "&SecretKey=NtvApiSecret2014*");
            this.res = redirectUrl2;
            this.afaki.streamUrl = redirectUrl2;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
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

    private String getRedirectUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.connect();
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetTLC) str);
        this.afaki.resumeParse();
    }

    public static String getBaseDomain(String str) {
        int indexOf = str.indexOf(46);
        int lastIndexOf = str.lastIndexOf(46);
        int r3 = 0;
        while (indexOf < lastIndexOf) {
            r3 = indexOf + 1;
            indexOf = str.indexOf(46, r3);
        }
        return r3 > 0 ? str.substring(r3) : str;
    }
}
