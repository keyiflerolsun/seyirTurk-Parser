package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Classes.IPTvClass;
import com.swenauk.mainmenu.Statics;
import com.swenauk.mainmenu.VideoView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetFavIPTV extends AsyncTask<String, String, String> {
    private String server;
    private VideoView videoView;

    public GetFavIPTV(VideoView videoView) {
        this.videoView = videoView;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.videoView != null) {
            this.server = Statics.SERVER;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            if (this.videoView == null) {
                return null;
            }
            try {
                JSONArray jSONArray = new JSONObject(getUrlContent(this.server + "sey/back/faviptv.php?id=" + this.videoView.id + "&vidid=" + Statics.getASW(this.videoView))).getJSONArray("links");
                this.videoView.allChannels.clear();
                for (int r0 = 0; r0 < jSONArray.length(); r0++) {
                    this.videoView.allChannels.add(new IPTvClass(jSONArray.getJSONObject(r0)));
                }
                return null;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            System.out.println("GetSpecial: " + str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("bety", "jughead");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetFavIPTV) str);
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.comingFromChannels();
        }
    }
}
