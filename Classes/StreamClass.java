package com.swenauk.mainmenu.Classes;

/* loaded from: classes3.dex */
public class StreamClass {
    private String backdrop;
    private String hasTrailer;
    private String imdb;
    private Boolean isIpTV;
    private int isTurkish;
    private String link;
    private String provider;
    private String studioName;

    public StreamClass() {
        this.hasTrailer = "0";
        this.studioName = "";
        this.link = "";
        this.provider = "";
        this.isTurkish = -1;
        this.isIpTV = false;
        this.backdrop = "";
        this.imdb = "";
    }

    public void setLink(String str) {
        this.link = str;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setTurkish(int r1) {
        this.isTurkish = r1;
    }

    public void setIsIpTV(Boolean bool) {
        this.isIpTV = bool;
    }

    public void setBackdrop(String str) {
        this.backdrop = str;
    }

    public String getLink() {
        return this.link;
    }

    public String getProvider() {
        return this.provider;
    }

    public int getTurkish() {
        return this.isTurkish;
    }

    public Boolean getIsIpTV() {
        return this.isIpTV;
    }

    public String getBackdrop() {
        return this.backdrop;
    }

    public String getImdb() {
        return this.imdb;
    }

    public String getHasTrailer() {
        return this.hasTrailer;
    }

    public void setHasTrailer(String str) {
        this.hasTrailer = str;
    }

    public void setStudioName(String str) {
        this.studioName = str;
    }

    public String getStudioName() {
        return this.studioName;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(2:2|3)|(4:(11:8|9|10|11|13|14|16|17|18|19|21)|18|19|21)|29|9|10|11|13|14|16|17|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
    
        r5.hasTrailer = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
    
        r5.backdrop = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0069, code lost:
    
        r5.imdb = "";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public StreamClass(org.json.JSONObject r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Link"
            r5.<init>()
            java.lang.String r1 = "0"
            r5.hasTrailer = r1
            java.lang.String r1 = ""
            r5.studioName = r1
            java.io.PrintStream r2 = java.lang.System.out
            r2.println(r6)
            r2 = 0
            java.lang.String r3 = r6.getString(r0)     // Catch: java.lang.Exception -> L6c
            java.lang.String r4 = "https://"
            boolean r3 = r3.contains(r4)     // Catch: java.lang.Exception -> L6c
            if (r3 != 0) goto L44
            java.lang.String r3 = r6.getString(r0)     // Catch: java.lang.Exception -> L6c
            java.lang.String r4 = "http://"
            boolean r3 = r3.contains(r4)     // Catch: java.lang.Exception -> L6c
            if (r3 == 0) goto L2c
            goto L44
        L2c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6c
            r3.<init>()     // Catch: java.lang.Exception -> L6c
            java.lang.String r4 = "https:"
            r3.append(r4)     // Catch: java.lang.Exception -> L6c
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L6c
            r3.append(r0)     // Catch: java.lang.Exception -> L6c
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> L6c
            r5.link = r0     // Catch: java.lang.Exception -> L6c
            goto L4a
        L44:
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L6c
            r5.link = r0     // Catch: java.lang.Exception -> L6c
        L4a:
            java.lang.String r0 = "Provider"
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L6c
            r5.provider = r0     // Catch: java.lang.Exception -> L6c
            java.lang.String r0 = "isTurkish"
            int r0 = r6.getInt(r0)     // Catch: java.lang.Exception -> L6c
            r5.isTurkish = r0     // Catch: java.lang.Exception -> L6c
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Exception -> L6c
            r5.isIpTV = r0     // Catch: java.lang.Exception -> L6c
            java.lang.String r0 = "IMDB"
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L69
            r5.imdb = r0     // Catch: java.lang.Exception -> L69
            goto L7f
        L69:
            r5.imdb = r1     // Catch: java.lang.Exception -> L6c
            goto L7f
        L6c:
            r0 = move-exception
            r5.link = r1
            r5.provider = r1
            r3 = -1
            r5.isTurkish = r3
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r5.isIpTV = r2
            java.io.PrintStream r2 = java.lang.System.out
            r2.println(r0)
        L7f:
            java.lang.String r0 = "Backdrop"
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L88
            r5.backdrop = r0     // Catch: java.lang.Exception -> L88
            goto L8a
        L88:
            r5.backdrop = r1
        L8a:
            java.lang.String r0 = "hasTrailer"
            java.lang.String r0 = r6.getString(r0)     // Catch: java.lang.Exception -> L93
            r5.hasTrailer = r0     // Catch: java.lang.Exception -> L93
            goto L95
        L93:
            r5.hasTrailer = r1
        L95:
            java.lang.String r0 = "StudioName"
            java.lang.String r6 = r6.getString(r0)     // Catch: java.lang.Exception -> L9e
            r5.studioName = r6     // Catch: java.lang.Exception -> L9e
            goto La0
        L9e:
            r5.studioName = r1
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.Classes.StreamClass.<init>(org.json.JSONObject):void");
    }

    public void Print() {
        System.out.println(this.provider + " -> " + this.link + " and isTurkish = " + this.isTurkish);
    }
}
