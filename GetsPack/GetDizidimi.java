package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.Dizidimi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetDizidimi extends AsyncTask<String, String, String> {
    Dizidimi afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetDizidimi(Dizidimi dizidimi) {
        this.afaki = dizidimi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
            if (!this.afaki.isAlt) {
                return null;
            }
            boolean contains = strArr[0].contains("?l=1");
            Matcher matcher = Pattern.compile("class=video-container><p><iframe.*?src=(.*?)\\s", 32).matcher(this.res);
            if (!matcher.find()) {
                return null;
            }
            this.afaki.oneLinkWonder = matcher.group(1) + "?l=" + (contains ? 1 : 0);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            System.out.println(url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + getBaseDomain(url.getHost()));
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
        super.onPostExecute((GetDizidimi) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
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
