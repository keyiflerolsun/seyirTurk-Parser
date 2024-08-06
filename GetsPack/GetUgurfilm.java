package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Ugurfilm;
import com.swenauk.mainmenu.Sivvat.Helper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GetUgurfilm extends AsyncTask<String, String, String> {
    Ugurfilm afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetUgurfilm(Ugurfilm ugurfilm) {
        this.afaki = ugurfilm;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        String str;
        Object obj;
        Object obj2;
        CharSequence charSequence;
        CharSequence charSequence2;
        Matcher matcher;
        String str2;
        Object obj3;
        CharSequence charSequence3;
        String str3;
        CharSequence charSequence4;
        String str4 = "https://";
        try {
            int r7 = 0;
            URL url = new URL(strArr[0]);
            String urlContent = getUrlContent(strArr[0]);
            this.res = urlContent;
            if (!this.afaki.isAlt) {
                return null;
            }
            LinkedList<String> pregMatchAll = Helper.pregMatchAll("<a class=\"partsec\"\\s*href=\"(.*?)\">", this.res);
            if (pregMatchAll.size() > 1 && !this.afaki.hasShownParts) {
                while (r7 < pregMatchAll.size()) {
                    Map<String, String> map = this.alternates;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Part ");
                    int r3 = r7 + 1;
                    sb.append(r3);
                    map.put(sb.toString(), pregMatchAll.get(r7));
                    r7 = r3;
                }
                this.afaki.hasShownParts = true;
                return null;
            }
            this.alternates.clear();
            Matcher matcher2 = Pattern.compile("<iframe.*?src=\"https://" + url.getHost() + "/player(.*?)\"", 32).matcher(this.res);
            String str5 = "/";
            String str6 = "\\/";
            if (matcher2.find()) {
                this.res = "https://" + url.getHost() + "/player" + matcher2.group(1);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                CharSequence charSequence5 = "1X";
                CharSequence charSequence6 = "odnoklassniki";
                Object obj4 = "Youtube";
                CharSequence charSequence7 = "youtube";
                if (this.res.contains(url.getHost() + "/player/play.php")) {
                    String str7 = this.res;
                    Object obj5 = "Odk";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("https://");
                    CharSequence charSequence8 = "ok.ru";
                    sb2.append(url.getHost());
                    sb2.append("/player/play.php?vid=");
                    String replace = str7.replace(sb2.toString(), "");
                    this.res = getUrlContent(this.res);
                    Matcher matcher3 = Pattern.compile("class=\"c-dropdown__item\"\\s*data-dropdown-value=\"(.*?)\" data-order-value=\"(\\d+)\"").matcher(this.res);
                    while (matcher3.find()) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str4);
                        String str8 = str4;
                        sb3.append(url.getHost());
                        sb3.append("/player/ajax_sources.php");
                        String sb4 = sb3.toString();
                        StringBuilder sb5 = new StringBuilder();
                        URL url2 = url;
                        sb5.append("vid=");
                        sb5.append(replace);
                        sb5.append("&alternative=");
                        String str9 = replace;
                        sb5.append(matcher3.group(1));
                        sb5.append("&ord=");
                        sb5.append(matcher3.group(2));
                        Matcher matcher4 = Pattern.compile("\"iframe\":\"(.*?)\"", 32).matcher(getUrlContentPost(sb4, sb5.toString()));
                        if (matcher4.find()) {
                            String replace2 = matcher4.group(1).replace(str6, str5);
                            if (!replace2.contains("http")) {
                                replace2 = "https:" + replace2;
                            }
                            if (replace2.contains("mail")) {
                                arrayList.add("Mailru");
                                arrayList2.add(replace2);
                            } else if (replace2.contains("fembed")) {
                                arrayList.add("Fembed");
                                arrayList2.add(replace2);
                            } else {
                                charSequence2 = charSequence8;
                                if (replace2.contains(charSequence2)) {
                                    obj2 = obj5;
                                    arrayList.add(obj2);
                                    arrayList2.add(replace2);
                                    matcher = matcher3;
                                    charSequence = charSequence6;
                                    charSequence3 = charSequence7;
                                    str2 = str5;
                                    obj3 = obj4;
                                } else {
                                    obj2 = obj5;
                                    charSequence = charSequence6;
                                    if (replace2.contains(charSequence)) {
                                        arrayList.add(obj2);
                                        arrayList2.add(replace2);
                                        matcher = matcher3;
                                        str2 = str5;
                                        obj3 = obj4;
                                        charSequence3 = charSequence7;
                                    } else {
                                        matcher = matcher3;
                                        charSequence3 = charSequence7;
                                        if (replace2.contains(charSequence3)) {
                                            str2 = str5;
                                            obj3 = obj4;
                                            arrayList.add(obj3);
                                            arrayList2.add(replace2);
                                        } else {
                                            str2 = str5;
                                            obj3 = obj4;
                                            str3 = str6;
                                            if (replace2.contains("vidmoly")) {
                                                arrayList.add("Vidmoly");
                                                arrayList2.add(replace2);
                                                charSequence4 = charSequence5;
                                                charSequence8 = charSequence2;
                                                charSequence7 = charSequence3;
                                                charSequence5 = charSequence4;
                                                matcher3 = matcher;
                                                str6 = str3;
                                                str4 = str8;
                                                replace = str9;
                                                obj5 = obj2;
                                                obj4 = obj3;
                                                str5 = str2;
                                                charSequence6 = charSequence;
                                                url = url2;
                                            } else {
                                                charSequence4 = charSequence5;
                                                if (replace2.contains(charSequence4)) {
                                                    arrayList.add(charSequence4);
                                                    arrayList2.add(replace2);
                                                }
                                                charSequence8 = charSequence2;
                                                charSequence7 = charSequence3;
                                                charSequence5 = charSequence4;
                                                matcher3 = matcher;
                                                str6 = str3;
                                                str4 = str8;
                                                replace = str9;
                                                obj5 = obj2;
                                                obj4 = obj3;
                                                str5 = str2;
                                                charSequence6 = charSequence;
                                                url = url2;
                                            }
                                        }
                                    }
                                }
                                str3 = str6;
                                charSequence4 = charSequence5;
                                charSequence8 = charSequence2;
                                charSequence7 = charSequence3;
                                charSequence5 = charSequence4;
                                matcher3 = matcher;
                                str6 = str3;
                                str4 = str8;
                                replace = str9;
                                obj5 = obj2;
                                obj4 = obj3;
                                str5 = str2;
                                charSequence6 = charSequence;
                                url = url2;
                            }
                        }
                        obj2 = obj5;
                        charSequence = charSequence6;
                        charSequence2 = charSequence8;
                        matcher = matcher3;
                        str2 = str5;
                        obj3 = obj4;
                        charSequence3 = charSequence7;
                        str3 = str6;
                        charSequence4 = charSequence5;
                        charSequence8 = charSequence2;
                        charSequence7 = charSequence3;
                        charSequence5 = charSequence4;
                        matcher3 = matcher;
                        str6 = str3;
                        str4 = str8;
                        replace = str9;
                        obj5 = obj2;
                        obj4 = obj3;
                        str5 = str2;
                        charSequence6 = charSequence;
                        url = url2;
                    }
                } else {
                    Object obj6 = obj4;
                    this.res = urlContent;
                    Matcher matcher5 = Pattern.compile("parttab tab-aktif(.*?)</iframe>").matcher(this.res);
                    if (matcher5.find()) {
                        this.res = matcher5.group(1);
                        Matcher matcher6 = Pattern.compile("href=\"(.*?)\"").matcher(this.res);
                        while (matcher6.find()) {
                            Object obj7 = obj6;
                            this.res = getUrlContent(matcher6.group(1));
                            Matcher matcher7 = Pattern.compile("iframe.*?src=(?:'|\")(.*?)(?:'|\")").matcher(this.res);
                            if (matcher7.find()) {
                                String group = matcher7.group(1);
                                if (!group.contains("http")) {
                                    group = "https:" + group;
                                }
                                if (group.contains("mail")) {
                                    arrayList.add("Mailru");
                                    arrayList2.add(group);
                                } else if (group.contains("fembed")) {
                                    arrayList.add("Fembed");
                                    arrayList2.add(group);
                                } else if (group.contains("ok.ru")) {
                                    arrayList.add("Odk");
                                    arrayList2.add(group);
                                } else if (group.contains(charSequence6)) {
                                    arrayList.add("Odk");
                                    arrayList2.add(group);
                                } else if (group.contains(charSequence7)) {
                                    obj = obj7;
                                    arrayList.add(obj);
                                    arrayList2.add(group);
                                    obj6 = obj;
                                }
                            }
                            obj = obj7;
                            obj6 = obj;
                        }
                    } else {
                        Matcher matcher8 = Pattern.compile("iframe.*?src=\"(.*?)\"").matcher(this.res);
                        if (matcher8.find() && matcher8.group(1).contains(charSequence7)) {
                            arrayList.add(obj6);
                            arrayList2.add(matcher8.group(1));
                            this.afaki.mainUrl = matcher8.group(1);
                        }
                    }
                }
                for (int r72 = 0; r72 < arrayList2.size(); r72++) {
                    if (r72 == 0) {
                        this.afaki.mainUrl = (String) arrayList2.get(r72);
                    }
                    this.alternates.put(arrayList.get(r72), arrayList2.get(r72));
                }
                return null;
            }
            try {
                str = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.res).get(1);
            } catch (Exception unused) {
                str = Helper.pregMatchAll("<iframe.*?src=\"(.*?)\"", this.res).get(0);
            }
            String str10 = Helper.pregMatchAll("movie/(.*?)/iframe", this.res).get(0);
            if (!str.contains("trstx") && !str.contains("sobreatsesuyp")) {
                String urlContent2 = getUrlContent(str + "?t=" + str10);
                this.res = urlContent2;
                this.res = Helper.pregMatchAll("\"hls\":\"(.*?)\"", urlContent2).get(0);
                String str11 = "https:" + this.res.replace("\\/", "/");
                this.res = str11;
                this.alternates.put("Altyazılı", str11);
                String urlContent3 = getUrlContent(str);
                this.res = urlContent3;
                this.res = Helper.pregMatchAll("\"hls\":\"(.*?)\"", urlContent3).get(0);
                String str12 = "https:" + this.res.replace("\\/", "/");
                this.res = str12;
                this.alternates.put("Dublaj", str12);
                return null;
            }
            this.afaki.mainUrl = str;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, "https://" + url.getHost());
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
        super.onPostExecute((GetUgurfilm) str);
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
