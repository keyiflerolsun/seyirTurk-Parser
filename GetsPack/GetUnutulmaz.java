package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.Unutulmaz;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class GetUnutulmaz extends AsyncTask<String, String, String> {
    Unutulmaz afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetUnutulmaz(Unutulmaz unutulmaz) {
        this.afaki = unutulmaz;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            if (!this.afaki.isAlt) {
                return null;
            }
            Matcher matcher = Pattern.compile("<iframe.*?src=(.*?)\\s", 32).matcher(this.res);
            if (!matcher.find()) {
                return null;
            }
            String replace = matcher.group(1).replace("\"", "");
            if (!replace.contains("http")) {
                replace = "https:" + replace;
            }
            Matcher matcher2 = Pattern.compile("(?://www|//)(.*?)\\.", 32).matcher(replace);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (matcher2.find()) {
                arrayList.add(matcher2.group(1));
                arrayList2.add(replace);
            }
            Matcher matcher3 = Pattern.compile("<li class=\"part\">\\s*<a href=\"(.*?)\"").matcher(this.res);
            while (matcher3.find()) {
                this.res = getUrlContent(matcher3.group(1));
                Matcher matcher4 = Pattern.compile("<iframe.*?src=(.*?)\\s").matcher(this.res);
                if (matcher4.find()) {
                    String group = matcher4.group(1);
                    if (!group.contains("http")) {
                        group = "https:" + group;
                    }
                    Matcher matcher5 = Pattern.compile("(?://www|//)(.*?)\\.", 32).matcher(group);
                    if (matcher5.find()) {
                        arrayList.add(matcher5.group(1));
                        arrayList2.add(group);
                    }
                }
            }
            if (arrayList2.size() != arrayList.size()) {
                return null;
            }
            for (int r3 = 0; r3 < arrayList.size(); r3++) {
                if (((String) arrayList.get(r3)).equals("filese") || ((String) arrayList.get(r3)).equals("contentx") || ((String) arrayList.get(r3)).equals("playru") || ((String) arrayList.get(r3)).equals("four") || ((String) arrayList.get(r3)).equals("vidmoly")) {
                    this.alternates.put(((String) arrayList.get(r3)).toUpperCase() + StringUtils.SPACE + (r3 + 1), arrayList2.get(r3));
                }
            }
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
        super.onPostExecute((GetUnutulmaz) str);
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
