package com.swenauk.mainmenu.Classes;

import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MenuItemClass {
    private String dataUrl;
    private String goTo;
    private String title;

    public MenuItemClass(JSONObject jSONObject) {
        try {
            this.title = jSONObject.getString("Title");
            this.goTo = jSONObject.getString("goTo");
            this.dataUrl = jSONObject.getString("DataUrl");
        } catch (Exception unused) {
            this.title = "";
            this.goTo = "";
            this.dataUrl = "";
        }
    }

    public MenuItemClass(String str, String str2) {
        this.title = str;
        this.goTo = "";
        this.dataUrl = str2;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGoTo() {
        return this.goTo;
    }

    public String getDataUrl() {
        return this.dataUrl;
    }

    public void Print() {
        System.out.println(this.title + " -> " + this.dataUrl);
    }
}
