package com.swenauk.mainmenu.GetsPack;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes3.dex */
public class GetSpecial extends AsyncTask<String, String, String> {
    private String email;
    private MainActivity mainActivity;
    private String server;

    public GetSpecial(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.mainActivity != null) {
            this.server = Statics.SERVER;
            this.email = this.mainActivity.preferences.getString("mail", "empty");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(23:12|13|(3:185|186|(17:188|(1:190)|191|(1:193)(1:196)|194|195|25|26|(1:28)(1:(1:140)(2:141|(1:143)(2:144|(1:146))))|29|(3:31|(2:34|32)|35)(1:138)|36|(7:126|(1:128)|129|(1:131)(1:137)|132|133|134)|40|(6:42|(1:44)(1:122)|45|(8:48|(4:50|51|(4:53|(1:55)(2:76|(1:78))|56|(2:58|(1:60)(2:61|(1:63)(2:64|(1:66)(2:67|(1:69))))))(5:79|(1:81)(2:97|(3:99|83|(2:85|(1:87)(2:88|(1:90)(2:91|(1:93)(2:94|(1:96)))))))|82|83|(0))|70)(4:100|(1:102)|103|(1:105)(2:106|(5:108|72|73|74|75)(2:109|(4:111|73|74|75)(3:112|(2:114|115)(1:116)|75))))|71|72|73|74|75|46)|117|118)(1:123)|119|120))(3:21|(3:147|148|(28:150|151|152|153|154|155|156|(3:157|158|(1:160)(1:161))|162|163|26|(0)(0)|29|(0)(0)|36|(1:38)|124|126|(0)|129|(0)(0)|132|133|134|40|(0)(0)|119|120))|23)|24|25|26|(0)(0)|29|(0)(0)|36|(0)|124|126|(0)|129|(0)(0)|132|133|134|40|(0)(0)|119|120) */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0258 A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0271 A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x028a A[Catch: Exception -> 0x0469, TRY_LEAVE, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x00f4 A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x010d A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0126 A[Catch: Exception -> 0x0469, TRY_LEAVE, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01c9 A[Catch: Exception -> 0x0469, TRY_ENTER, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01f7 A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0246 A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02bb A[Catch: Exception -> 0x0469, TryCatch #3 {Exception -> 0x0469, blocks: (B:4:0x000e, B:7:0x0022, B:9:0x0045, B:10:0x004d, B:12:0x0055, B:15:0x0081, B:17:0x0089, B:19:0x0091, B:21:0x0099, B:28:0x01c9, B:29:0x01f0, B:31:0x01f7, B:32:0x0222, B:34:0x0228, B:36:0x0240, B:38:0x0246, B:40:0x02b5, B:42:0x02bb, B:44:0x02cd, B:46:0x02d9, B:48:0x02df, B:50:0x02eb, B:53:0x0303, B:55:0x031a, B:56:0x0333, B:60:0x0354, B:63:0x0360, B:66:0x036c, B:69:0x0378, B:76:0x031f, B:78:0x032f, B:79:0x0381, B:81:0x039a, B:83:0x03b4, B:87:0x03d3, B:90:0x03de, B:93:0x03e9, B:96:0x03f4, B:97:0x03a0, B:99:0x03b0, B:100:0x03fd, B:102:0x0410, B:105:0x0418, B:108:0x0426, B:111:0x0431, B:114:0x043c, B:122:0x02d2, B:124:0x024c, B:126:0x0252, B:128:0x0258, B:129:0x026b, B:131:0x0271, B:137:0x028a, B:140:0x01d3, B:143:0x01de, B:146:0x01e9, B:166:0x00ee, B:168:0x00f4, B:169:0x0107, B:171:0x010d, B:177:0x0126, B:186:0x0159, B:188:0x015f, B:190:0x0165, B:191:0x0178, B:193:0x017e, B:196:0x0197), top: B:3:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03d0  */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r23) {
        /*
            Method dump skipped, instructions count: 1137
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetSpecial.doInBackground(java.lang.String[]):java.lang.String");
    }

    public boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT < 23 || context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    private String getUrlContent(String str) {
        String str2;
        String str3;
        try {
            MainActivity mainActivity = this.mainActivity;
            if (mainActivity == null) {
                mainActivity = null;
            }
            if (mainActivity != null) {
                str2 = Statics.getASW(mainActivity);
            } else {
                str2 = Statics.ASW;
            }
            if (str.endsWith(".php")) {
                str3 = str + "?u_id=" + String.valueOf(this.mainActivity.id) + "&cmail=" + this.email + "&vidid=" + str2 + "&vn=" + Statics.VersionNumber;
            } else {
                str3 = str + "&u_id=" + String.valueOf(this.mainActivity.id) + "&cmail=" + this.email + "&vidid=" + str2 + "&vn=" + Statics.VersionNumber;
            }
            if (Statics.preferences != null && Statics.preferences.getBoolean("includeYK", false) && !str3.endsWith(".json")) {
                str3 = str3 + "&includeYK=1";
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
            httpURLConnection.setRequestProperty("bety", "jughead");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetSpecial) str);
        MainActivity mainActivity = this.mainActivity;
        if (mainActivity != null) {
            mainActivity.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetSpecial.1
                @Override // java.lang.Runnable
                public void run() {
                    GetSpecial.this.mainActivity.isPaused = false;
                    GetSpecial.this.mainActivity.customShowGames();
                    GetSpecial.this.mainActivity.isMoviesLoaded = true;
                    GetSpecial.this.mainActivity.closeAlert();
                }
            });
        }
    }
}
