package com.swenauk.mainmenu.Classes;

import android.text.TextUtils;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class ParserConnection {
    public static CookieManager cookieManager;
    public static URLConnection urlConnection;

    public static void StartConnection() throws IOException {
        URL url = new URL("");
        CookieManager cookieManager2 = new CookieManager();
        cookieManager = cookieManager2;
        CookieHandler.setDefault(cookieManager2);
        URLConnection openConnection = url.openConnection();
        urlConnection = openConnection;
        openConnection.setUseCaches(true);
        SaveCookie();
        urlConnection.connect();
    }

    public static void SaveCookie() {
        List<String> list = urlConnection.getHeaderFields().get("Set-Cookie");
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                cookieManager.getCookieStore().add(null, HttpCookie.parse(it.next()).get(0));
            }
        }
    }

    public static URLConnection LoadCookie(URLConnection uRLConnection) {
        if (cookieManager.getCookieStore().getCookies().size() > 0) {
            uRLConnection.setRequestProperty("Cookie", TextUtils.join(";", cookieManager.getCookieStore().getCookies()));
        }
        return uRLConnection;
    }
}
