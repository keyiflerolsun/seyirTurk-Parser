package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import android.util.Base64;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.swenauk.mainmenu.Classes.JSUnpacker;
import com.swenauk.mainmenu.Parsers.HdfilmcehennemiSyrtrk;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetHdfilmcehennemiSyrtrk extends AsyncTask<String, String, String> {
    HdfilmcehennemiSyrtrk afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetHdfilmcehennemiSyrtrk(HdfilmcehennemiSyrtrk hdfilmcehennemiSyrtrk) {
        this.afaki = hdfilmcehennemiSyrtrk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", "").replace("hdfilmcehennemisyrtrk", "hdfilmcehennemi"));
            if (this.afaki.isAlt) {
                Matcher matcher = Pattern.compile("nav-slider(.*?)player-container", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                this.res = matcher.group(1);
                Matcher matcher2 = Pattern.compile("a.*?href\\s*=\\s*\"(.*?)\".*?(?:</i>|svg>)(.*?)</a", 32).matcher(this.res);
                while (matcher2.find()) {
                    if (!matcher2.group(2).contains("Fragman") && !matcher2.group(2).contains("Sinema Modu")) {
                        this.alternates.put(matcher2.group(2).trim(), matcher2.group(1));
                    }
                }
                return null;
            }
            Matcher matcher3 = Pattern.compile("<iframe.*?data-src=\"(.*?)\"").matcher(this.res);
            if (!matcher3.find()) {
                return null;
            }
            String group = matcher3.group(1);
            this.res = group;
            if (group.contains("player")) {
                this.res = getUrlContent(this.res);
                Matcher matcher4 = Pattern.compile("eval\\((.*?)\\{\\}\\)\\)").matcher(this.res);
                if (!matcher4.find()) {
                    return null;
                }
                Matcher matcher5 = Pattern.compile("tracks:\\s*(.*?\\]),").matcher(this.res);
                if (matcher5.find()) {
                    this.res = matcher5.group(1);
                    Matcher matcher6 = Pattern.compile("\"file\":\"(.*?)\"").matcher(this.res);
                    while (matcher6.find()) {
                        if (matcher6.group(1).contains("Turkish")) {
                            this.afaki.subtitles.put(HtmlTableRow.TAG_NAME, "https://www.hdfilmcehennemi.life" + matcher6.group(1).replace("\\", ""));
                        } else if (matcher6.group(1).contains("English")) {
                            this.afaki.subtitles.put("en", "https://www.hdfilmcehennemi.life" + matcher6.group(1).replace("\\", ""));
                        }
                    }
                }
                this.afaki.mainUrl = new String(Base64.decode(JSUnpacker.Unpack("eval(" + matcher4.group(1) + "{}))").replace("var file_link=\"", "").replace("\";", ""), 0));
                return null;
            }
            this.afaki.mainUrl = this.res;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            if (str.contains("hdplayersystem.live") || str.contains("hdfilmcehennemi")) {
                httpURLConnection.setRequestProperty("Referer", this.afaki.firstUrl);
            }
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
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
        super.onPostExecute((GetHdfilmcehennemiSyrtrk) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
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
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, "https://protonvideo.to/");
            httpURLConnection.setRequestProperty("Content-Length", "117");
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
