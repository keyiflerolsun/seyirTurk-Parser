package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.Hdfilmcehennemi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class GetHdfilmcehennemi extends AsyncTask<String, String, String> {
    Hdfilmcehennemi afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetHdfilmcehennemi(Hdfilmcehennemi hdfilmcehennemi) {
        this.afaki = hdfilmcehennemi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0080 A[Catch: Exception -> 0x02ba, LOOP:0: B:14:0x007a->B:16:0x0080, LOOP_END, TryCatch #0 {Exception -> 0x02ba, blocks: (B:2:0x0000, B:4:0x0006, B:7:0x0023, B:8:0x0029, B:10:0x0041, B:12:0x0053, B:13:0x0070, B:14:0x007a, B:16:0x0080, B:19:0x0089, B:21:0x008f, B:24:0x00b5, B:26:0x00d4, B:29:0x00e2, B:31:0x00e8, B:32:0x0252, B:34:0x0262, B:35:0x0269, B:37:0x0271, B:40:0x0279, B:42:0x0281, B:44:0x0289, B:46:0x0291, B:48:0x0299, B:50:0x02a1, B:52:0x02a9, B:54:0x02b1, B:60:0x010a, B:62:0x0110, B:63:0x0121, B:65:0x0129, B:68:0x0133, B:70:0x013b, B:72:0x0141, B:73:0x015e, B:75:0x0164, B:76:0x0175, B:78:0x017b, B:80:0x0181, B:81:0x019e, B:83:0x01a4, B:84:0x01b5, B:86:0x01bb, B:87:0x01d8, B:89:0x01de, B:91:0x01f0, B:93:0x0200, B:95:0x020a, B:96:0x0224, B:98:0x022a, B:99:0x023a, B:101:0x0242, B:102:0x021e, B:109:0x0058, B:111:0x006a), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008f A[Catch: Exception -> 0x02ba, TRY_LEAVE, TryCatch #0 {Exception -> 0x02ba, blocks: (B:2:0x0000, B:4:0x0006, B:7:0x0023, B:8:0x0029, B:10:0x0041, B:12:0x0053, B:13:0x0070, B:14:0x007a, B:16:0x0080, B:19:0x0089, B:21:0x008f, B:24:0x00b5, B:26:0x00d4, B:29:0x00e2, B:31:0x00e8, B:32:0x0252, B:34:0x0262, B:35:0x0269, B:37:0x0271, B:40:0x0279, B:42:0x0281, B:44:0x0289, B:46:0x0291, B:48:0x0299, B:50:0x02a1, B:52:0x02a9, B:54:0x02b1, B:60:0x010a, B:62:0x0110, B:63:0x0121, B:65:0x0129, B:68:0x0133, B:70:0x013b, B:72:0x0141, B:73:0x015e, B:75:0x0164, B:76:0x0175, B:78:0x017b, B:80:0x0181, B:81:0x019e, B:83:0x01a4, B:84:0x01b5, B:86:0x01bb, B:87:0x01d8, B:89:0x01de, B:91:0x01f0, B:93:0x0200, B:95:0x020a, B:96:0x0224, B:98:0x022a, B:99:0x023a, B:101:0x0242, B:102:0x021e, B:109:0x0058, B:111:0x006a), top: B:1:0x0000 }] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r11) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetHdfilmcehennemi.doInBackground(java.lang.String[]):java.lang.String");
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
        super.onPostExecute((GetHdfilmcehennemi) str);
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
