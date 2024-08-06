package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import android.os.Build;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.Statics;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes3.dex */
public class LogSystem extends AsyncTask<Integer, String, Integer> {
    private MainActivity mainActivity;
    private String res;

    public LogSystem(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Integer num) {
        super.onPostExecute((LogSystem) num);
        if (num.intValue() == 0) {
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.LogSystem.1
                @Override // java.lang.Runnable
                public void run() {
                    LogSystem.this.mainActivity.noNetwork();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Integer doInBackground(Integer... numArr) {
        String str = Build.MODEL;
        String valueOf = String.valueOf(Build.VERSION.SDK_INT);
        return getUrlContent(Statics.SERVER + "sey/back/logger.php?u_d=" + str + "&u_v=" + valueOf);
    }

    private Integer getUrlContent(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("bety", "jughead");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            this.mainActivity.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.LogSystem.2
                @Override // java.lang.Runnable
                public void run() {
                    LogSystem.this.mainActivity.continueAfterLog();
                }
            });
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
