package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.Izle720p;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetIzle720p extends AsyncTask<String, String, String> {
    Izle720p afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetIzle720p(Izle720p izle720p) {
        this.afaki = izle720p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
            if (this.afaki.isAlt) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Matcher matcher = Pattern.compile("Player.*?</span>(.*?)data-movies", 32).matcher(this.res);
                while (matcher.find()) {
                    matcher = Pattern.compile("a.*?href=\"(.*?)\".*?<span.*?>(.*?)</span>", 32).matcher(matcher.group(1));
                    while (matcher.find()) {
                        arrayList.add(matcher.group(2).replace("data-navigo><span>", "").replace("</span></a>", "").trim());
                        arrayList2.add(matcher.group(1));
                    }
                }
                for (int r4 = 0; r4 < arrayList2.size(); r4++) {
                    this.alternates.put(((String) arrayList.get(r4)).toLowerCase().replace("tek", "").replace("altyazılı", "").toUpperCase(), arrayList2.get(r4));
                }
                if (this.alternates.size() != 1) {
                    return null;
                }
                Izle720p izle720p = this.afaki;
                Map<String, String> map = this.alternates;
                izle720p.oneLinkWonder = map.get(map.keySet().toArray()[0]);
                return null;
            }
            this.res = getUrlContent(strArr[0].replace("l=0", "").replace("l=1", ""));
            Matcher matcher2 = Pattern.compile("<iframe src=\"(.*?)\"").matcher(this.res);
            if (matcher2.find()) {
                this.afaki.mainUrl = matcher2.group(1);
                if (!matcher2.find()) {
                    return null;
                }
                this.afaki.mainUrl = matcher2.group(1);
                return null;
            }
            Matcher matcher3 = Pattern.compile("<script>mailru\\('(.*?)'").matcher(this.res);
            if (!matcher3.find()) {
                return null;
            }
            this.afaki.mainUrl = "https://my.mail.ru/" + matcher3.group(1).replace("_myvideo", "video/embed/_myvideo");
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
                    stringBuffer.append("\n");
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
        super.onPostExecute((GetIzle720p) str);
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
