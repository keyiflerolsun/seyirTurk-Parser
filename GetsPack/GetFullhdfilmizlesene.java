package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.html.HtmlBold;
import com.gargoylesoftware.htmlunit.html.HtmlInlineQuotation;
import com.gargoylesoftware.htmlunit.html.HtmlItalic;
import com.gargoylesoftware.htmlunit.html.HtmlS;
import com.gargoylesoftware.htmlunit.html.HtmlUnderlined;
import com.gargoylesoftware.htmlunit.svg.SvgGroup;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes3.dex */
public class GetFullhdfilmizlesene extends AsyncTask<String, String, String> {
    Fullhdfilmizlesene afaki;
    String res;
    Map<String, String> alternates = new HashMap();
    String sec = "";

    public GetFullhdfilmizlesene(Fullhdfilmizlesene fullhdfilmizlesene) {
        this.afaki = fullhdfilmizlesene;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(19:87|(18:132|133|(2:138|139)(1:135)|90|(2:95|91)|97|(2:101|(5:103|(1:105)(1:111)|106|(1:108)(1:110)|109))|112|113|(2:116|114)|117|118|119|(1:121)|122|(1:124)|125|126)|89|90|(3:93|95|91)|131|97|(3:99|101|(0))|112|113|(1:114)|117|118|119|(0)|122|(0)|125|126) */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x030c, code lost:
    
        r3 = r7.replace("\\", "");
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0314, code lost:
    
        if (r3.startsWith("//") != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0316, code lost:
    
        r3 = "http:" + r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x026b, code lost:
    
        if (r3.contains("http") != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x03f7, code lost:
    
        if (r0.contains("http") != false) goto L130;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02b3 A[Catch: Exception -> 0x04e0, TryCatch #0 {Exception -> 0x04e0, blocks: (B:84:0x0212, B:87:0x0232, B:90:0x026f, B:91:0x0280, B:93:0x0286, B:95:0x028c, B:97:0x0295, B:99:0x029b, B:101:0x02a1, B:103:0x02b3, B:105:0x02cb, B:106:0x02d1, B:108:0x02e1, B:109:0x02e7, B:119:0x0325, B:121:0x032d, B:122:0x032f, B:124:0x0339, B:125:0x0348, B:128:0x030c, B:130:0x0316, B:143:0x034e, B:145:0x0360, B:146:0x0375, B:148:0x037b, B:150:0x0381, B:152:0x0399, B:153:0x03a7, B:155:0x03ad, B:157:0x03bd, B:159:0x03fa, B:161:0x0404, B:162:0x0419, B:163:0x042f, B:165:0x0435, B:177:0x0442, B:169:0x0474, B:187:0x0411, B:200:0x04a6, B:202:0x04b8, B:205:0x0365, B:113:0x02eb, B:114:0x02f1, B:116:0x02f7, B:118:0x0307), top: B:83:0x0212, outer: #8, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02f7 A[Catch: Exception -> 0x030c, LOOP:5: B:114:0x02f1->B:116:0x02f7, LOOP_END, TryCatch #4 {Exception -> 0x030c, blocks: (B:113:0x02eb, B:114:0x02f1, B:116:0x02f7, B:118:0x0307), top: B:112:0x02eb, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x032d A[Catch: Exception -> 0x04e0, TryCatch #0 {Exception -> 0x04e0, blocks: (B:84:0x0212, B:87:0x0232, B:90:0x026f, B:91:0x0280, B:93:0x0286, B:95:0x028c, B:97:0x0295, B:99:0x029b, B:101:0x02a1, B:103:0x02b3, B:105:0x02cb, B:106:0x02d1, B:108:0x02e1, B:109:0x02e7, B:119:0x0325, B:121:0x032d, B:122:0x032f, B:124:0x0339, B:125:0x0348, B:128:0x030c, B:130:0x0316, B:143:0x034e, B:145:0x0360, B:146:0x0375, B:148:0x037b, B:150:0x0381, B:152:0x0399, B:153:0x03a7, B:155:0x03ad, B:157:0x03bd, B:159:0x03fa, B:161:0x0404, B:162:0x0419, B:163:0x042f, B:165:0x0435, B:177:0x0442, B:169:0x0474, B:187:0x0411, B:200:0x04a6, B:202:0x04b8, B:205:0x0365, B:113:0x02eb, B:114:0x02f1, B:116:0x02f7, B:118:0x0307), top: B:83:0x0212, outer: #8, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0339 A[Catch: Exception -> 0x04e0, TryCatch #0 {Exception -> 0x04e0, blocks: (B:84:0x0212, B:87:0x0232, B:90:0x026f, B:91:0x0280, B:93:0x0286, B:95:0x028c, B:97:0x0295, B:99:0x029b, B:101:0x02a1, B:103:0x02b3, B:105:0x02cb, B:106:0x02d1, B:108:0x02e1, B:109:0x02e7, B:119:0x0325, B:121:0x032d, B:122:0x032f, B:124:0x0339, B:125:0x0348, B:128:0x030c, B:130:0x0316, B:143:0x034e, B:145:0x0360, B:146:0x0375, B:148:0x037b, B:150:0x0381, B:152:0x0399, B:153:0x03a7, B:155:0x03ad, B:157:0x03bd, B:159:0x03fa, B:161:0x0404, B:162:0x0419, B:163:0x042f, B:165:0x0435, B:177:0x0442, B:169:0x0474, B:187:0x0411, B:200:0x04a6, B:202:0x04b8, B:205:0x0365, B:113:0x02eb, B:114:0x02f1, B:116:0x02f7, B:118:0x0307), top: B:83:0x0212, outer: #8, inners: #4 }] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r17) {
        /*
            Method dump skipped, instructions count: 1261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetFullhdfilmizlesene.doInBackground(java.lang.String[]):java.lang.String");
    }

    private String findRealChar(String str) {
        if (!Character.isAlphabetic(str.charAt(0))) {
            return str;
        }
        if (str.toLowerCase().charAt(0) < 'n' || str.toLowerCase().charAt(0) == 305) {
            return Character.toString((char) (str.charAt(0) + '\r'));
        }
        return Character.toString((char) (str.charAt(0) - '\r'));
    }

    private String myUnpacker(String str, String str2) {
        String[] split = str.split("\\|");
        LinkedList linkedList = new LinkedList(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", HtmlBold.TAG_NAME, "c", "d", "e", "f", SvgGroup.TAG_NAME, "h", HtmlItalic.TAG_NAME, "j", "k", "l", "m", "n", "o", "p", HtmlInlineQuotation.TAG_NAME, "r", HtmlS.TAG_NAME, "t", HtmlUnderlined.TAG_NAME, "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        String[] split2 = str2.split("\\\\");
        String[] strArr = (String[]) Arrays.copyOfRange(split2, 1, split2.length);
        String str3 = "";
        for (int r5 = 0; r5 < strArr.length; r5++) {
            if (!strArr[r5].equals("")) {
                str3 = str3 + split[linkedList.indexOf(strArr[r5])].trim().substring(1);
            }
        }
        return str3;
    }

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty(HttpHeader.REFERER_LC, "https://ugurfilm.com");
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
            httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
            System.out.println(url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            if (this.afaki.comingUrl.contains("fullhdfilm")) {
                httpURLConnection.setRequestProperty("Referer", "https://www.fullhdfilmizlesene.com/");
            } else {
                httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + getBaseDomain(url.getHost()));
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
        super.onPostExecute((GetFullhdfilmizlesene) str);
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
