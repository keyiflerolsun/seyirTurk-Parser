package com.swenauk.mainmenu.Classes;

import com.swenauk.mainmenu.Classes.PosterClass;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class SeriesClass extends PosterClass {
    public String genres;
    private ArrayList<StreamClass> streams;

    public SeriesClass() {
        this.genres = "";
        this.streams = new ArrayList<>();
    }

    public void setStreams(ArrayList<StreamClass> arrayList) {
        this.streams = arrayList;
    }

    public ArrayList<StreamClass> getStreams() {
        return this.streams;
    }

    public SeriesClass(JSONObject jSONObject) {
        super(jSONObject, PosterClass.Type.SERIES);
        try {
            this.genres = jSONObject.getString("Genres");
            this.streams = new ArrayList<>();
            this.myJson = jSONObject;
        } catch (Exception unused) {
            this.genres = "";
            this.streams = new ArrayList<>();
        }
    }

    @Override // com.swenauk.mainmenu.Classes.PosterClass
    public void Print() {
        System.out.println(this.id + "-> " + this.name + " w/" + this.genres);
    }
}
