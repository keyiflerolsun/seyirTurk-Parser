package com.swenauk.mainmenu.Classes;

import java.util.Calendar;

/* loaded from: classes3.dex */
public class FilterClass {
    public float startIMDB = 0.0f;
    public float endIMDB = 10.0f;
    public int startYear = 1900;
    public int endYear = Calendar.getInstance().get(1);
    public int languageType = 0;
    public int localizationType = 0;
    public int orderType = 0;

    public void setAll(float f, float f2, int r3, int r4, int r5, int r6) {
        this.startIMDB = f;
        this.endIMDB = f2;
        this.startYear = r3;
        this.endYear = r4;
        this.languageType = r5;
        this.localizationType = r6;
    }
}
