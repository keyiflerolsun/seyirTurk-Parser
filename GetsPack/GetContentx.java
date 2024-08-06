package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.swenauk.mainmenu.Parsers.Contentx;
import com.swenauk.mainmenu.Sivvat.Helper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetContentx extends AsyncTask<String, String, String> {
    Contentx afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetContentx(Contentx contentx) {
        this.afaki = contentx;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            if (strArr[0].startsWith("//")) {
                strArr[0] = "https:" + strArr[0];
            }
            this.res = getUrlContent(strArr[0]);
            if (this.afaki.isAlt) {
                return null;
            }
            if (!strArr[0].contains("contentx") && !strArr[0].contains("hotlinger") && !strArr[0].contains("playru") && !strArr[0].contains("//four") && !strArr[0].contains("pichive")) {
                if (!strArr[0].contains("filese")) {
                    return null;
                }
                Matcher matcher = Pattern.compile("getJSON\\('(.*?)'", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                matcher.group(1).replace("'", "");
                String urlContent = getUrlContent("https://filese.me" + matcher.group(1).replace("'", ""));
                this.res = urlContent;
                this.afaki.parsed = urlContent;
                return null;
            }
            LinkedHashMap<String, String> pregMatchAll = Helper.pregMatchAll("\"file\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"label\"\\s*:\\s*\"(.*?)\"", this.res, false, true);
            for (Map.Entry<String, String> entry : pregMatchAll.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (pregMatchAll.size() == 1) {
                    this.afaki.subtitle = value;
                } else {
                    String lowerCase = key.toLowerCase();
                    if (lowerCase.contains("rk") || lowerCase.contains("turkish")) {
                        if (value.toLowerCase().contains(HtmlTableRow.TAG_NAME)) {
                            this.afaki.subtitle = value;
                        }
                    }
                }
            }
            Matcher matcher2 = Pattern.compile("window.openPlayer\\((.*?)\\)", 32).matcher(this.res);
            if (!matcher2.find()) {
                return null;
            }
            String replace = matcher2.group(1).split(",")[0].replace("'", "");
            this.res = getUrlContent("https://" + new URL(strArr[0]).getHost() + "/source2.php?v=" + replace);
            JSONArray jSONArray = new JSONObject(this.res).getJSONArray("playlist");
            for (int r1 = 0; r1 < jSONArray.length(); r1++) {
                if (this.afaki.isDublaj && (jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("title").contains("Türkçe Dublaj") || jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("title").contains("Orijinal"))) {
                    this.afaki.parsed = jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("file");
                } else if (!this.afaki.isDublaj && (jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("title").contains("Türkçe Altyazı") || jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("title").contains("Orijinal"))) {
                    this.afaki.parsed = jSONArray.getJSONObject(r1).getJSONArray("sources").getJSONObject(0).getString("file");
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Mobile Safari/537.36");
            httpURLConnection.setRequestProperty("Referer", this.afaki.mainUrlForReferer);
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
        super.onPostExecute((GetContentx) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }
}
