package com.swenauk.mainmenu.GetsPack;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.swenauk.mainmenu.ItemShow;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.MovieProfile;
import com.swenauk.mainmenu.Statics;

/* loaded from: classes3.dex */
public class GetMovies extends AsyncTask<String, String, String> {
    private String email;
    private ItemShow itemShow;
    private MainActivity mainActivity;
    private MovieProfile movieProfile;
    private String server;
    private int u_id;

    public GetMovies(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public GetMovies(ItemShow itemShow) {
        this.itemShow = itemShow;
    }

    public GetMovies(MovieProfile movieProfile) {
        this.movieProfile = movieProfile;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.mainActivity != null) {
            this.server = Statics.SERVER;
            this.u_id = this.mainActivity.id;
            this.email = this.mainActivity.getSharedPreferences("prefName", 0).getString("mail", "empty");
        } else if (this.itemShow != null) {
            this.server = Statics.SERVER;
            this.u_id = this.itemShow.id.intValue();
            this.email = this.itemShow.getSharedPreferences("prefName", 0).getString("mail", "empty");
        } else if (this.movieProfile != null) {
            this.server = Statics.SERVER;
            this.u_id = Statics.getUserID(this.movieProfile);
            this.email = this.movieProfile.getSharedPreferences("prefName", 0).getString("mail", "empty");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't wrap try/catch for region: R(30:107|108|(1:109)|(3:320|321|(6:323|(2:324|(1:326)(1:327))|328|329|114|(23:310|(2:312|313)(22:314|315|126|(3:128|(2:131|129)|132)(1:306)|133|(6:135|136|137|139|140|141)|145|(2:147|(1:149)(1:301))(2:302|303)|150|(4:153|(4:155|(1:157)(2:164|(1:166))|158|(2:160|161)(1:163))(4:167|(1:169)(2:175|(1:177))|170|(2:172|173)(1:174))|162|151)|178|179|(4:182|(4:184|(1:186)(2:193|(1:195))|187|(2:189|190)(1:192))(4:196|(1:198)(2:204|(1:206))|199|(2:201|202)(1:203))|191|180)|207|208|(4:211|(4:213|(1:215)(2:222|(1:224))|216|(2:218|219)(1:221))(4:225|(1:227)(2:233|(1:235))|228|(2:230|231)(1:232))|220|209)|236|237|(4:240|(4:242|(1:244)(2:251|(1:253))|245|(2:247|248)(1:250))(4:254|(1:256)(2:262|(1:264))|257|(2:259|260)(1:261))|249|238)|265|266|(7:268|(4:271|(6:273|(1:275)|276|(1:278)|279|280)(4:282|(1:284)(1:290)|285|(2:287|288)(1:289))|281|269)|291|292|(3:295|296|293)|297|298)(1:300))|125|126|(0)(0)|133|(0)|145|(0)(0)|150|(1:151)|178|179|(1:180)|207|208|(1:209)|236|237|(1:238)|265|266|(0)(0))(26:120|121|122|123|124|125|126|(0)(0)|133|(0)|145|(0)(0)|150|(1:151)|178|179|(1:180)|207|208|(1:209)|236|237|(1:238)|265|266|(0)(0)))(1:331))(2:111|112)|113|114|(2:116|118)|310|(0)(0)|125|126|(0)(0)|133|(0)|145|(0)(0)|150|(1:151)|178|179|(1:180)|207|208|(1:209)|236|237|(1:238)|265|266|(0)(0)) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0d44 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0dbe A[Catch: Exception -> 0x1211, TRY_LEAVE, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0e21 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0e52 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0f21 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0fe0 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:240:0x109f A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x1150 A[Catch: Exception -> 0x1211, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:300:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0e3b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0d87  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0cdc A[Catch: Exception -> 0x1211, TRY_ENTER, TRY_LEAVE, TryCatch #14 {Exception -> 0x1211, blocks: (B:97:0x0b81, B:99:0x0b9c, B:100:0x0bb3, B:102:0x0bbe, B:105:0x0bea, B:107:0x0bf2, B:116:0x0c93, B:118:0x0c99, B:120:0x0ca5, B:126:0x0d3d, B:128:0x0d44, B:129:0x0d6d, B:131:0x0d73, B:133:0x0d89, B:135:0x0dbe, B:145:0x0df7, B:147:0x0e21, B:149:0x0e2d, B:301:0x0e34, B:150:0x0e44, B:151:0x0e4c, B:153:0x0e52, B:155:0x0e60, B:157:0x0e77, B:158:0x0e90, B:160:0x0ea9, B:162:0x0f01, B:164:0x0e7c, B:166:0x0e8c, B:167:0x0eb1, B:169:0x0ecc, B:170:0x0ee5, B:172:0x0efa, B:175:0x0ed1, B:177:0x0ee1, B:179:0x0f09, B:180:0x0f1b, B:182:0x0f21, B:184:0x0f2f, B:186:0x0f46, B:187:0x0f5f, B:189:0x0f74, B:191:0x0fc8, B:193:0x0f4b, B:195:0x0f5b, B:196:0x0f7c, B:198:0x0f93, B:199:0x0fac, B:201:0x0fc1, B:204:0x0f98, B:206:0x0fa8, B:208:0x0fcc, B:209:0x0fda, B:211:0x0fe0, B:213:0x0fee, B:215:0x1005, B:216:0x101e, B:218:0x1033, B:220:0x1087, B:222:0x100a, B:224:0x101a, B:225:0x103b, B:227:0x1052, B:228:0x106b, B:230:0x1080, B:233:0x1057, B:235:0x1067, B:237:0x108b, B:238:0x1099, B:240:0x109f, B:242:0x10ad, B:244:0x10c4, B:245:0x10dd, B:247:0x10f2, B:249:0x1146, B:251:0x10c9, B:253:0x10d9, B:254:0x10fa, B:256:0x1111, B:257:0x112a, B:259:0x113f, B:262:0x1116, B:264:0x1126, B:266:0x114a, B:268:0x1150, B:269:0x115e, B:271:0x1164, B:273:0x1172, B:275:0x1189, B:276:0x118d, B:278:0x11a2, B:281:0x11e5, B:282:0x11ac, B:284:0x11c3, B:285:0x11c9, B:287:0x11de, B:292:0x11e9, B:293:0x11f8, B:295:0x11fe, B:312:0x0cdc, B:317:0x0d10, B:111:0x0c63, B:332:0x0c35, B:336:0x0bd8, B:315:0x0d0a), top: B:96:0x0b81, outer: #7, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0d0a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:345:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0a0e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0a02  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x067d A[Catch: Exception -> 0x06b6, TryCatch #3 {Exception -> 0x06b6, blocks: (B:495:0x04a1, B:497:0x04b1, B:503:0x04ba, B:505:0x04f5, B:455:0x04fe, B:457:0x0510, B:459:0x051c, B:461:0x0522, B:464:0x0535, B:468:0x0547, B:470:0x055c, B:472:0x0568, B:474:0x0579, B:476:0x05a5, B:477:0x0574, B:478:0x05ad, B:480:0x05c2, B:482:0x05ce, B:483:0x05d3, B:485:0x05ff, B:520:0x060e, B:522:0x0636, B:524:0x0646, B:526:0x0656, B:530:0x0673, B:532:0x067d, B:534:0x0689, B:537:0x0691, B:539:0x069d, B:540:0x06a2, B:542:0x066a), top: B:494:0x04a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0691 A[Catch: Exception -> 0x06b6, TryCatch #3 {Exception -> 0x06b6, blocks: (B:495:0x04a1, B:497:0x04b1, B:503:0x04ba, B:505:0x04f5, B:455:0x04fe, B:457:0x0510, B:459:0x051c, B:461:0x0522, B:464:0x0535, B:468:0x0547, B:470:0x055c, B:472:0x0568, B:474:0x0579, B:476:0x05a5, B:477:0x0574, B:478:0x05ad, B:480:0x05c2, B:482:0x05ce, B:483:0x05d3, B:485:0x05ff, B:520:0x060e, B:522:0x0636, B:524:0x0646, B:526:0x0656, B:530:0x0673, B:532:0x067d, B:534:0x0689, B:537:0x0691, B:539:0x069d, B:540:0x06a2, B:542:0x066a), top: B:494:0x04a1 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x06d6  */
    /* JADX WARN: Type inference failed for: r11v41, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r11v46, types: [java.io.OutputStreamWriter] */
    /* JADX WARN: Type inference failed for: r14v120, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r14v121, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r14v123 */
    /* JADX WARN: Type inference failed for: r24v10 */
    /* JADX WARN: Type inference failed for: r24v15 */
    /* JADX WARN: Type inference failed for: r24v16 */
    /* JADX WARN: Type inference failed for: r24v17 */
    /* JADX WARN: Type inference failed for: r24v18 */
    /* JADX WARN: Type inference failed for: r24v2 */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARN: Type inference failed for: r24v7 */
    /* JADX WARN: Type inference failed for: r24v8 */
    /* JADX WARN: Type inference failed for: r24v9 */
    /* JADX WARN: Type inference failed for: r9v118 */
    /* JADX WARN: Type inference failed for: r9v121, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v124 */
    /* JADX WARN: Type inference failed for: r9v125, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v128, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v74, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v75 */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String doInBackground(java.lang.String... r24) {
        /*
            Method dump skipped, instructions count: 4642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetMovies.doInBackground(java.lang.String[]):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c A[Catch: Exception -> 0x010f, TRY_ENTER, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d4 A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00fd A[Catch: Exception -> 0x010e, LOOP:0: B:24:0x00f7->B:27:0x00fd, LOOP_END, TRY_LEAVE, TryCatch #1 {Exception -> 0x010e, blocks: (B:25:0x00f7, B:27:0x00fd), top: B:24:0x00f7 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x010d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00dd A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005d A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x001a A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015 A[Catch: Exception -> 0x010f, TryCatch #0 {Exception -> 0x010f, blocks: (B:3:0x0003, B:7:0x0015, B:8:0x001c, B:11:0x002c, B:12:0x0094, B:14:0x0098, B:16:0x00a3, B:18:0x00a9, B:19:0x00ba, B:21:0x00d4, B:22:0x00e5, B:36:0x00dd, B:37:0x005d, B:40:0x0064, B:41:0x001a, B:42:0x0009, B:45:0x000e), top: B:2:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getUrlContent(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.GetsPack.GetMovies.getUrlContent(java.lang.String):java.lang.String");
    }

    public boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT < 23 || context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetMovies) str);
        MainActivity mainActivity = this.mainActivity;
        if (mainActivity != null) {
            if (!mainActivity.isRandom.booleanValue()) {
                this.mainActivity.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetMovies.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetMovies.this.mainActivity.isPaused = false;
                        GetMovies.this.mainActivity.showGames();
                        GetMovies.this.mainActivity.isMoviesLoaded = true;
                        GetMovies.this.mainActivity.closeAlert();
                    }
                });
            } else {
                this.mainActivity.goToRandom();
            }
        }
        ItemShow itemShow = this.itemShow;
        if (itemShow != null) {
            itemShow.showGames();
            this.itemShow.isMoviesLoaded = true;
            this.itemShow.closeAlert();
        }
        MovieProfile movieProfile = this.movieProfile;
        if (movieProfile != null) {
            if (movieProfile.type == 0) {
                this.movieProfile.showLinks();
                this.movieProfile.isMoviesLoaded = true;
                this.movieProfile.closeAlert();
                return;
            }
            this.movieProfile.parseStreams();
        }
    }
}
