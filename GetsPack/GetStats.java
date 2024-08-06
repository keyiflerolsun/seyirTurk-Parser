package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes3.dex */
public class GetStats extends AsyncTask<String, String, String> {
    private MainActivity mainActivity;
    private String message;
    private Integer messageFlag;
    private String server;

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.server = Statics.SERVER;
    }

    public GetStats(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        System.out.println("Get Message");
        if (this.mainActivity != null) {
            try {
                return getUrlContent(this.server + "sey/back/stats.php");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "İstatistikleri Alırken Hata Oluştu";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetStats) str);
        this.mainActivity.closeAlert();
        this.mainActivity.showStats(str);
    }

    private String getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
