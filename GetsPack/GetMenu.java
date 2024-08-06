package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Classes.MenuItemClass;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetMenu extends AsyncTask<String, String, String> {
    private MainActivity mainActivity;
    private String server;

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.server = Statics.SERVER;
    }

    public GetMenu(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        System.out.println("Get Menu");
        if (this.mainActivity == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONObject(getUrlContent(this.server + "sey/back/menu.php")).getJSONArray("movies");
            this.mainActivity.menuItems.clear();
            for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                this.mainActivity.menuItems.add(new MenuItemClass(jSONArray.getJSONObject(r2)));
            }
            JSONArray jSONArray2 = new JSONObject(getUrlContent(this.server + "sey/back/yearmenu.php")).getJSONArray("movies");
            this.mainActivity.yearItems.clear();
            for (int r22 = 0; r22 < jSONArray2.length(); r22++) {
                this.mainActivity.yearItems.add(new MenuItemClass(jSONArray2.getJSONObject(r22)));
            }
            JSONArray jSONArray3 = new JSONObject(getUrlContent(this.server + "sey/back/ulkeler.php")).getJSONArray("movies");
            this.mainActivity.countryItems.clear();
            for (int r23 = 0; r23 < jSONArray3.length(); r23++) {
                this.mainActivity.countryItems.add(new MenuItemClass(jSONArray3.getJSONObject(r23)));
            }
            JSONArray jSONArray4 = new JSONObject(getUrlContent(this.server + "sey/kodi/studios.php?android=yes")).getJSONArray("movies");
            this.mainActivity.studioItems.clear();
            for (int r24 = 0; r24 < jSONArray4.length(); r24++) {
                this.mainActivity.studioItems.add(new MenuItemClass(jSONArray4.getJSONObject(r24)));
            }
            JSONArray jSONArray5 = new JSONObject(getUrlContent(this.server + "sey/kodi/studios_tv.php?android=yes")).getJSONArray("movies");
            this.mainActivity.studioTVItems.clear();
            for (int r25 = 0; r25 < jSONArray5.length(); r25++) {
                this.mainActivity.studioTVItems.add(new MenuItemClass(jSONArray5.getJSONObject(r25)));
            }
            JSONArray jSONArray6 = new JSONObject(getUrlContent(this.server + "sey/kodi/sites.php?android=yes")).getJSONArray("movies");
            this.mainActivity.sitesItems.clear();
            for (int r26 = 0; r26 < jSONArray6.length(); r26++) {
                this.mainActivity.sitesItems.add(new MenuItemClass(jSONArray6.getJSONObject(r26)));
            }
            JSONArray jSONArray7 = new JSONObject(getUrlContent(this.server + "sey/kodi/sites_tv.php?android=yes")).getJSONArray("movies");
            this.mainActivity.sitesTVItems.clear();
            for (int r1 = 0; r1 < jSONArray7.length(); r1++) {
                this.mainActivity.sitesTVItems.add(new MenuItemClass(jSONArray7.getJSONObject(r1)));
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetMenu) str);
        this.mainActivity.isMenuLoaded = true;
        this.mainActivity.closeAlert();
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
