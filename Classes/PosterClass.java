package com.swenauk.mainmenu.Classes;

import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.google.common.net.HttpHeaders;
import java.io.Serializable;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class PosterClass implements Comparable<PosterClass>, Serializable {
    protected boolean canFilter;
    protected boolean hasWatched;
    protected int id;
    protected String image;
    protected String imdbScore;
    protected boolean isTurkish;
    protected Integer langType;
    protected String link;
    protected JSONObject myJson;
    protected String name;
    protected String releaseDate;
    protected String runtime;
    protected String summary;
    protected Type type;

    /* loaded from: classes3.dex */
    public enum Type {
        MOVIE,
        IPTV,
        SERIES,
        MAIN,
        PERSON,
        NULL
    }

    public PosterClass() {
        this.id = 0;
        this.name = "";
        this.image = "";
        this.imdbScore = "";
        this.releaseDate = "";
        this.myJson = new JSONObject();
        this.summary = "";
        this.type = Type.NULL;
        this.link = "";
        this.runtime = "";
        this.langType = null;
        this.hasWatched = false;
        this.canFilter = false;
        this.isTurkish = false;
    }

    public PosterClass(JSONObject jSONObject, Type type) {
        this.myJson = jSONObject;
        try {
            this.id = jSONObject.getInt(SchemaSymbols.ATTVAL_ID);
            this.name = jSONObject.getString(SchemaSymbols.ATTVAL_NAME);
            this.image = jSONObject.getString("Image");
            try {
                this.imdbScore = jSONObject.getString("IMDBScore");
                this.releaseDate = jSONObject.getString("ReleaseDate");
            } catch (Exception unused) {
                this.imdbScore = "0";
                this.releaseDate = "0";
            }
            try {
                this.link = jSONObject.getString(HttpHeaders.LINK);
            } catch (Exception unused2) {
                this.link = "";
            }
            try {
                try {
                    this.runtime = jSONObject.getString("Runtime");
                } catch (Exception unused3) {
                    this.id = 0;
                    this.name = "";
                    this.image = "";
                    this.link = "";
                    this.imdbScore = "";
                    this.releaseDate = "";
                    Type type2 = Type.NULL;
                    this.summary = "";
                    this.runtime = "";
                    this.langType = null;
                    this.isTurkish = false;
                    return;
                }
            } catch (Exception unused4) {
                this.runtime = "";
            }
            try {
                this.langType = Integer.valueOf(jSONObject.getInt("Language"));
                this.summary = jSONObject.getString("Summary");
            } catch (Exception e) {
                this.langType = null;
                System.out.println(e);
            }
            boolean z = true;
            try {
                if (Integer.parseInt(jSONObject.getString("hasWatched")) > 0) {
                    this.hasWatched = true;
                }
            } catch (Exception unused5) {
                this.hasWatched = false;
            }
            this.type = type;
            try {
                this.canFilter = jSONObject.getBoolean("CanFilter");
            } catch (Exception unused6) {
                this.canFilter = false;
            }
            try {
                if (jSONObject.getInt("isTurkish") != 1) {
                    z = false;
                }
                this.isTurkish = z;
            } catch (Exception unused7) {
                this.isTurkish = false;
            }
            if (this.link.contains("pokitv")) {
                if (this.link.startsWith("https://pokitv")) {
                    this.type = Type.IPTV;
                } else {
                    this.type = Type.MAIN;
                }
            }
        } catch (Exception unused8) {
            this.id = 0;
            this.name = jSONObject.getString("title");
            this.image = jSONObject.getString("icon");
            this.link = jSONObject.getString(HtmlLink.TAG_NAME);
            this.imdbScore = "";
            this.releaseDate = "";
            this.type = Type.MAIN;
            this.summary = "";
            this.langType = 0;
            this.runtime = "";
            this.isTurkish = false;
        }
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLangType(Integer num) {
        this.langType = num;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public JSONObject getMyJson() {
        return this.myJson;
    }

    public Type getType() {
        return this.type;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public Integer getLangType() {
        return this.langType;
    }

    public String getImdbScore() {
        return this.imdbScore;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public boolean isHasWatched() {
        return this.hasWatched;
    }

    public void setHasWatched(boolean z) {
        this.hasWatched = z;
    }

    public void Print() {
        System.out.println(this.id + " -> " + this.name + " w/ " + this.type);
    }

    public String getLink() {
        return this.link;
    }

    public boolean isCanFilter() {
        return this.canFilter;
    }

    public void setCanFilter(boolean z) {
        this.canFilter = z;
    }

    public boolean isTurkish() {
        return this.isTurkish;
    }

    public void setTurkish(boolean z) {
        this.isTurkish = z;
    }

    @Override // java.lang.Comparable
    public int compareTo(PosterClass posterClass) {
        return (int) (Double.parseDouble(this.imdbScore) - Double.parseDouble(posterClass.getImdbScore()));
    }
}
