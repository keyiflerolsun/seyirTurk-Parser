package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.Parsers.Dizigom;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetDizigom extends AsyncTask<String, String, String> {
    Dizigom afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetDizigom(Dizigom dizigom) {
        this.afaki = dizigom;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0]);
            if (this.afaki.isAlt) {
                Matcher matcher = Pattern.compile("\"contentUrl\"\\s*:\\s*\"(.*?)\"", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                this.res = getUrlContent(matcher.group(1)).replace("\\/", "/");
                Matcher matcher2 = Pattern.compile("data-uri=\"(.*?)\"", 32).matcher(this.res);
                ArrayList arrayList = new ArrayList();
                while (matcher2.find()) {
                    if (matcher2.group(1).contains("refresh")) {
                        arrayList.add(matcher.group(1));
                    } else {
                        arrayList.add(matcher2.group(1));
                    }
                }
                Matcher matcher3 = Pattern.compile("data-short=\"(.*?)\"", 32).matcher(this.res);
                ArrayList arrayList2 = new ArrayList();
                while (matcher3.find()) {
                    if (matcher3.group(1).contains("refresh")) {
                        arrayList2.add(matcher.group(1));
                    } else {
                        arrayList2.add(matcher3.group(1));
                    }
                }
                if (arrayList2.size() == arrayList.size()) {
                    for (int r0 = 0; r0 < arrayList2.size(); r0++) {
                        this.alternates.put(arrayList2.get(r0), arrayList.get(r0));
                    }
                }
                if (arrayList2.size() != 0) {
                    return null;
                }
                this.alternates.put("Dizigom", matcher.group(1));
                return null;
            }
            Matcher matcher4 = Pattern.compile("<script type=\".*?text/javascript\">(eval.*?)</script>", 32).matcher(this.res);
            if (matcher4.find()) {
                this.res = JSUnpacker.Unpack(matcher4.group(1));
                Matcher matcher5 = Pattern.compile("\"file\":\"(.*?)\",", 32).matcher(this.res);
                if (!matcher5.find()) {
                    return null;
                }
                String group = matcher5.group(1);
                this.res = group;
                this.afaki.parsed = group;
                return null;
            }
            Matcher matcher6 = Pattern.compile("video/(.*?)$").matcher(strArr[0]);
            if (!matcher6.find()) {
                return null;
            }
            this.res = getUrlContentPost(strArr[0] + "?do=getVideo", "hash=" + matcher6.group(1) + "&r=https://play.dizigom1.com/&s=");
            Matcher matcher7 = Pattern.compile("\"file\":\"(.*?)\",", 32).matcher(this.res);
            if (!matcher7.find()) {
                return null;
            }
            String group2 = matcher7.group(1);
            this.res = group2;
            this.afaki.parsed = group2;
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str.replace("\\", ""));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
            httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + url.getHost());
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

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, "https://dizigom1.com");
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
        super.onPostExecute((GetDizigom) str);
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
