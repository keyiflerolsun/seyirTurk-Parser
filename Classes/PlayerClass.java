package com.swenauk.mainmenu.Classes;

import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Classes.PosterClass;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PlayerClass extends PosterClass {
    public PlayerClass() {
    }

    public PlayerClass(JSONObject jSONObject) {
        super(jSONObject, PosterClass.Type.PERSON);
        try {
            this.name = jSONObject.getString(SchemaSymbols.ATTVAL_NAME);
            this.id = jSONObject.getInt(SchemaSymbols.ATTVAL_ID);
            this.link = jSONObject.getString(HttpHeaders.LINK);
            this.image = jSONObject.getString("Image");
            this.myJson = jSONObject;
        } catch (Exception unused) {
            this.name = "";
            this.id = 0;
            this.link = "";
            this.image = "";
        }
    }

    @Override // com.swenauk.mainmenu.Classes.PosterClass
    public void Print() {
        System.out.println(this.id + "-> " + this.name);
    }
}
