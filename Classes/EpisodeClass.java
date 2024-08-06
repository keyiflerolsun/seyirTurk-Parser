package com.swenauk.mainmenu.Classes;

import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class EpisodeClass extends SeriesClass {
    private String ep_id;
    private String episode;
    private boolean isLast;
    private String isLeft;
    private String season;
    private ArrayList<StreamClass> streams;

    public EpisodeClass() {
        this.episode = "";
        this.season = "";
        this.ep_id = "";
        this.isLeft = "";
        this.isLast = false;
        this.streams = new ArrayList<>();
    }

    @Override // com.swenauk.mainmenu.Classes.SeriesClass
    public void setStreams(ArrayList<StreamClass> arrayList) {
        this.streams = arrayList;
    }

    public String getIsLeft() {
        return this.isLeft;
    }

    public String getEp_id() {
        return this.ep_id;
    }

    public String getEpisode() {
        return this.episode;
    }

    public String getSeason() {
        return this.season;
    }

    @Override // com.swenauk.mainmenu.Classes.SeriesClass
    public ArrayList<StreamClass> getStreams() {
        return this.streams;
    }

    public EpisodeClass(JSONObject jSONObject) {
        super(jSONObject);
        try {
            this.episode = jSONObject.getString("Episode");
            this.season = jSONObject.getString("Season");
            this.ep_id = jSONObject.getString("E_ID");
            this.isLeft = jSONObject.getString("isLeft");
            this.isLast = jSONObject.getBoolean("isLast");
            this.streams = new ArrayList<>();
            this.myJson = jSONObject;
        } catch (Exception unused) {
            this.episode = "";
            this.season = "";
            this.ep_id = "";
            this.isLeft = "";
            this.isLast = false;
            this.streams = new ArrayList<>();
        }
    }

    public boolean isLast() {
        return this.isLast;
    }

    public void setLast(boolean z) {
        this.isLast = z;
    }

    @Override // com.swenauk.mainmenu.Classes.SeriesClass, com.swenauk.mainmenu.Classes.PosterClass
    public void Print() {
        System.out.println(this.id + "-> " + this.name);
    }
}
