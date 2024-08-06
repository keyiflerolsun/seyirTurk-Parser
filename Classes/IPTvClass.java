package com.swenauk.mainmenu.Classes;

import com.swenauk.mainmenu.Classes.PosterClass;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class IPTvClass extends PosterClass {
    private String genres;
    private String group;
    private String hash;
    private Boolean isAdult;
    private Boolean isSource;
    private String k_id;
    private ArrayList<StreamClass> streams;

    public IPTvClass() {
        this.hash = "";
        this.k_id = "";
        this.streams = new ArrayList<>();
        this.isSource = false;
    }

    public void setStreams(ArrayList<StreamClass> arrayList) {
        this.streams = arrayList;
    }

    public void addToStream(String str) {
        StreamClass streamClass = new StreamClass();
        streamClass.setLink(str);
        streamClass.setProvider("LinkTV");
        streamClass.setTurkish(1);
        streamClass.setIsIpTV(true);
        this.streams.add(streamClass);
    }

    public Boolean getSource() {
        return this.isSource;
    }

    public void setSource(Boolean bool) {
        this.isSource = bool;
    }

    public ArrayList<StreamClass> getStreams() {
        return this.streams;
    }

    public IPTvClass(JSONObject jSONObject) {
        super(jSONObject, PosterClass.Type.IPTV);
        try {
            if (jSONObject.has("Genres")) {
                this.genres = jSONObject.getString("Genres");
            }
            if (jSONObject.has("Hash")) {
                this.genres = jSONObject.getString("Hash");
            }
            if (jSONObject.has("K_ID")) {
                this.genres = jSONObject.getString("K_ID");
            }
            if (jSONObject.has("Group")) {
                if (jSONObject.getString("Group").equals("")) {
                    this.group = "Gruplandırılmamış";
                } else {
                    this.group = jSONObject.getString("Group");
                }
            } else {
                this.group = null;
            }
            if (jSONObject.has("isAdult")) {
                this.isAdult = true;
            } else {
                this.isAdult = false;
            }
            this.streams = new ArrayList<>();
            this.myJson = jSONObject;
            this.isSource = false;
        } catch (Exception unused) {
            this.genres = "";
            this.streams = new ArrayList<>();
            this.isSource = false;
        }
    }

    public String getGroup() {
        return this.group;
    }

    public String getStream() {
        return this.streams.get(0).getLink();
    }

    public Boolean getAdult() {
        return this.isAdult;
    }

    @Override // com.swenauk.mainmenu.Classes.PosterClass
    public void Print() {
        System.out.println(this.id + "-> " + this.name + " w/" + this.genres);
    }
}
