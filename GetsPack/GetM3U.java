package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.ItemShow;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetM3U extends AsyncTask<String, String, String> {
    ItemShow afaki;
    boolean isMy;
    String res;
    String server = Statics.SERVER;

    public GetM3U(ItemShow itemShow, boolean z) {
        this.isMy = false;
        this.afaki = itemShow;
        this.isMy = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(10:2|3|4|(4:9|10|11|(4:13|(9:16|17|18|19|(3:21|(1:26)|30)(1:31)|27|28|29|14)|34|35)(4:37|(10:40|41|42|43|45|(6:47|48|49|51|(1:56)|60)(2:62|63)|57|58|59|38)|66|67))|68|69|(2:71|(1:86)(4:76|77|(2:78|(1:80)(1:81))|82))(1:87)|10|11|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0104, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0105, code lost:
    
        java.lang.System.out.println(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0124 A[Catch: Exception -> 0x0270, TRY_ENTER, TryCatch #0 {Exception -> 0x0270, blocks: (B:3:0x0004, B:6:0x0018, B:9:0x0023, B:10:0x010a, B:13:0x0124, B:14:0x0130, B:16:0x0136, B:37:0x01d9, B:38:0x01e5, B:40:0x01eb, B:89:0x0105, B:69:0x002d, B:71:0x0060, B:74:0x0088, B:85:0x00b6, B:86:0x00bc, B:87:0x00fb), top: B:2:0x0004, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01d9 A[Catch: Exception -> 0x0270, TRY_ENTER, TryCatch #0 {Exception -> 0x0270, blocks: (B:3:0x0004, B:6:0x0018, B:9:0x0023, B:10:0x010a, B:13:0x0124, B:14:0x0130, B:16:0x0136, B:37:0x01d9, B:38:0x01e5, B:40:0x01eb, B:89:0x0105, B:69:0x002d, B:71:0x0060, B:74:0x0088, B:85:0x00b6, B:86:0x00bc, B:87:0x00fb), top: B:2:0x0004, inners: #4 }] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r17) {
        /*
            Method dump skipped, instructions count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetM3U.doInBackground(java.lang.String[]):java.lang.String");
    }

    private String getFileContent(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                System.out.println(readLine);
                sb.append('\n');
            }
            bufferedReader.close();
        } catch (IOException unused) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetM3U.1
                @Override // java.lang.Runnable
                public void run() {
                    GetM3U.this.afaki.ipTvError();
                }
            });
        }
        return sb.toString();
    }

    private String getUrlContent(String str) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.swenauk.mainmenu.GetsPack.GetM3U.2
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            if (str.contains(Statics.SERVER)) {
                httpURLConnection.setRequestProperty("bety", "jughead");
            }
            httpURLConnection.setConnectTimeout(5000);
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
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetM3U.3
                @Override // java.lang.Runnable
                public void run() {
                    GetM3U.this.afaki.ipTvError();
                }
            });
            return e.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetM3U) str);
        this.afaki.showGames();
        this.afaki.isMoviesLoaded = true;
        this.afaki.closeAlert();
    }
}
