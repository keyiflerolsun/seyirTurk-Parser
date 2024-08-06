package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes3.dex */
public class GetMessage extends AsyncTask<String, String, String> {
    private Integer isUserActive;
    private MainActivity mainActivity;
    private String message;
    private String messageFlag;
    private String server;
    private String u_flag;
    private String u_message;

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.server = Statics.SERVER;
    }

    public GetMessage(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:24:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0154 A[Catch: Exception -> 0x03bd, TryCatch #2 {Exception -> 0x03bd, blocks: (B:9:0x0059, B:87:0x0143, B:25:0x014d, B:27:0x0154, B:28:0x0165, B:31:0x01ec, B:62:0x02de, B:64:0x02e6, B:66:0x02ee, B:67:0x0305, B:69:0x030f, B:71:0x0317, B:73:0x031f, B:76:0x0395, B:78:0x039d, B:84:0x01e8, B:30:0x01d7), top: B:8:0x0059, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01fa A[Catch: Exception -> 0x02de, TryCatch #1 {Exception -> 0x02de, blocks: (B:33:0x01f0, B:35:0x01fa, B:37:0x0249, B:39:0x0257, B:41:0x0277, B:44:0x027a, B:45:0x028b, B:47:0x0291, B:50:0x02a5, B:56:0x02ae, B:58:0x02b4, B:60:0x02c8), top: B:32:0x01f0 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02e6 A[Catch: Exception -> 0x03bd, TryCatch #2 {Exception -> 0x03bd, blocks: (B:9:0x0059, B:87:0x0143, B:25:0x014d, B:27:0x0154, B:28:0x0165, B:31:0x01ec, B:62:0x02de, B:64:0x02e6, B:66:0x02ee, B:67:0x0305, B:69:0x030f, B:71:0x0317, B:73:0x031f, B:76:0x0395, B:78:0x039d, B:84:0x01e8, B:30:0x01d7), top: B:8:0x0059, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x030f A[Catch: Exception -> 0x03bd, TryCatch #2 {Exception -> 0x03bd, blocks: (B:9:0x0059, B:87:0x0143, B:25:0x014d, B:27:0x0154, B:28:0x0165, B:31:0x01ec, B:62:0x02de, B:64:0x02e6, B:66:0x02ee, B:67:0x0305, B:69:0x030f, B:71:0x0317, B:73:0x031f, B:76:0x0395, B:78:0x039d, B:84:0x01e8, B:30:0x01d7), top: B:8:0x0059, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x039d A[Catch: Exception -> 0x03bd, TRY_LEAVE, TryCatch #2 {Exception -> 0x03bd, blocks: (B:9:0x0059, B:87:0x0143, B:25:0x014d, B:27:0x0154, B:28:0x0165, B:31:0x01ec, B:62:0x02de, B:64:0x02e6, B:66:0x02ee, B:67:0x0305, B:69:0x030f, B:71:0x0317, B:73:0x031f, B:76:0x0395, B:78:0x039d, B:84:0x01e8, B:30:0x01d7), top: B:8:0x0059, inners: #5 }] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r24) {
        /*
            Method dump skipped, instructions count: 967
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetMessage.doInBackground(java.lang.String[]):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetMessage) str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (str.contains("tvyayin")) {
                httpURLConnection.setRequestProperty("Referer", "https://www.tvyayinakisi.com/");
                httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
            }
            httpURLConnection.setReadTimeout(2000);
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
