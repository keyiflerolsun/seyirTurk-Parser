package com.swenauk.mainmenu.GetsPack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.swenauk.mainmenu.Classes.EpisodeClass;
import com.swenauk.mainmenu.Classes.PosterClass;
import com.swenauk.mainmenu.Classes.StreamClass;
import com.swenauk.mainmenu.ItemShow;
import com.swenauk.mainmenu.MainActivity;
import com.swenauk.mainmenu.MovieProfile;
import com.swenauk.mainmenu.SettingsView;
import com.swenauk.mainmenu.Statics;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UserSystem extends AsyncTask<Integer, String, Integer> {
    private Context context;
    private EpisodeClass episode;
    private ItemShow itemShow;
    private MovieProfile movieProfile;
    private String res;
    private String server;
    private SettingsView settingsView;
    private VideoView videoView;

    public UserSystem(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    public UserSystem(MovieProfile movieProfile) {
        this.movieProfile = movieProfile;
    }

    public UserSystem(VideoView videoView) {
        this.videoView = videoView;
    }

    public UserSystem(ItemShow itemShow) {
        this.itemShow = itemShow;
    }

    public UserSystem(Context context) {
        this.context = context;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (this.settingsView != null) {
            this.server = Statics.SERVER;
            return;
        }
        if (this.movieProfile != null) {
            this.server = Statics.SERVER;
            return;
        }
        if (this.videoView != null) {
            this.server = Statics.SERVER;
        } else if (this.itemShow != null) {
            this.server = Statics.SERVER;
        } else {
            this.server = Statics.SERVER;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Integer num) {
        super.onPostExecute((UserSystem) num);
        SettingsView settingsView = this.settingsView;
        if (settingsView != null) {
            settingsView.showAlert(this.res);
        }
        if (this.movieProfile != null) {
            if (num.intValue() == 2) {
                this.movieProfile.showAlert(this.res);
            } else if (num.intValue() == 3) {
                this.movieProfile.checkFavs(this.res);
            } else if (num.intValue() == 5) {
                this.movieProfile.skipTo(this.res);
            }
        }
        if (this.itemShow != null) {
            if (num.intValue() == 6) {
                ItemShow itemShow = this.itemShow;
                itemShow.completeAlert(this.res, itemShow.favDo.intValue(), true);
            } else if (num.intValue() == 7) {
                ItemShow itemShow2 = this.itemShow;
                itemShow2.completeAlert(this.res, itemShow2.favDo.intValue(), false);
            }
        }
        if (this.videoView == null || num.intValue() != 8) {
            return;
        }
        this.videoView.playNextEpisode(this.episode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Integer doInBackground(Integer... numArr) {
        if (numArr[0].intValue() == 0) {
            this.res = addUser();
        } else if (numArr[0].intValue() == 1) {
            this.res = joinUser(numArr[1].intValue());
        } else if (numArr[0].intValue() == 2) {
            this.res = addFav();
        } else if (numArr[0].intValue() == 3) {
            this.res = checkFav();
        } else if (numArr[0].intValue() == 4) {
            saveCur();
        } else if (numArr[0].intValue() == 5) {
            this.res = getCur();
        } else if (numArr[0].intValue() == 6) {
            this.res = addIpTvFav();
        } else if (numArr[0].intValue() == 7) {
            this.res = addRadioFav();
        } else if (numArr[0].intValue() == 8) {
            saveCur();
            getLatestEpisode();
        } else if (numArr[0].intValue() == 9) {
            this.res = signupUser(numArr[1].intValue());
        } else if (numArr[0].intValue() == 10) {
            this.res = loginUser(numArr[1].intValue());
        }
        return numArr[0];
    }

    private String addUser() {
        String urlContent = getUrlContent(this.server + "sey/back/user.php?type=add&email=" + this.settingsView.email + "&pass=" + this.settingsView.pass);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("Add Res ->");
        sb.append(urlContent);
        printStream.println(sb.toString());
        return urlContent;
    }

    private String signupUser(final int r7) {
        Context context = this.context;
        if (context instanceof MainActivity) {
            String str = ((MainActivity) context).newUserInfo.get(0);
            String str2 = ((MainActivity) this.context).newUserInfo.get(1);
            String str3 = ((MainActivity) this.context).newUserInfo.get(2);
            try {
                str = URLEncoder.encode(str, "UTF-8");
                str2 = URLEncoder.encode(str2, "UTF-8");
                str3 = URLEncoder.encode(str3, "UTF-8");
            } catch (Exception unused) {
            }
            final String urlContent = getUrlContent(this.server + "sey/back/user.php?type=signup&email=" + str + "&pass=" + str2 + "&mail=" + str3 + "&isEncode=1");
            final SharedPreferences sharedPreferences = this.context.getSharedPreferences("prefName", 0);
            try {
                if (Integer.parseInt(urlContent) > 0) {
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            builder.setTitle("Başarıyla üye oldunuz. Girişiniz otomatik yapılsın mı?");
                            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.1.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r5) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        sharedPreferences.edit().putString("user" + r7 + "Mail", ((MainActivity) UserSystem.this.context).newUserInfo.get(2)).apply();
                                        sharedPreferences.edit().putString("user" + r7 + "Email", ((MainActivity) UserSystem.this.context).newUserInfo.get(0)).apply();
                                        sharedPreferences.edit().putInt("user" + r7 + "Id", Integer.parseInt(urlContent)).apply();
                                        sharedPreferences.edit().putString("user" + r7 + "Password", ((MainActivity) UserSystem.this.context).newUserInfo.get(1)).apply();
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                        ((MainActivity) UserSystem.this.context).changeActiveUser(r7);
                                        ((MainActivity) UserSystem.this.context).reloadActivity();
                                    }
                                }
                            });
                            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.1.2
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    ((MainActivity) UserSystem.this.context).canReload = true;
                                    ((MainActivity) UserSystem.this.context).reloadActivity();
                                }
                            });
                            builder.create().show();
                        }
                    });
                } else {
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            if (Integer.parseInt(urlContent) == -1) {
                                builder.setMessage("Bu kullanıcı adı sistemimizde mevcut. Lütfen başka bir kullanıcı adı deneyiniz.");
                            } else if (Integer.parseInt(urlContent) == -2) {
                                builder.setMessage("Bu email adresi sistemimizde mevcut. Lütfen başka bir email adresi deneyiniz.");
                            } else if (Integer.parseInt(urlContent) == -3) {
                                builder.setMessage("Sistemsel bir hata oluştu. Lütfen tekrar deneyin.");
                            } else {
                                builder.setMessage("Üyelik Başarısız");
                            }
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.2.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
            } catch (Exception unused2) {
            }
        }
        return this.res;
    }

    private String loginUser(final int r10) {
        String str;
        Context context = this.context;
        if (context instanceof MainActivity) {
            String str2 = ((MainActivity) context).newUserInfo.get(0);
            String str3 = ((MainActivity) this.context).newUserInfo.get(1);
            try {
                str2 = URLEncoder.encode(str2, "UTF-8");
                str3 = URLEncoder.encode(str3, "UTF-8");
            } catch (Exception unused) {
            }
            final String urlContent = getUrlContent(this.server + "sey/back/user.php?type=signin&email=" + str2 + "&pass=" + str3 + "&isEncode=1");
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("prefName", 0);
            try {
                if (urlContent.contains("|")) {
                    try {
                        str = urlContent.split("\\|")[1];
                    } catch (Exception unused2) {
                        str = "";
                    }
                    int parseInt = Integer.parseInt(urlContent.split("\\|")[0]);
                    if (parseInt > 0 && parseInt != sharedPreferences.getInt("user1Id", -1) && parseInt != sharedPreferences.getInt("user2Id", -1)) {
                        sharedPreferences.edit().putString("user" + r10 + "Mail", str).apply();
                        sharedPreferences.edit().putString("user" + r10 + "Email", ((MainActivity) this.context).newUserInfo.get(0)).apply();
                        sharedPreferences.edit().putString("user" + r10 + "Password", ((MainActivity) this.context).newUserInfo.get(1)).apply();
                        sharedPreferences.edit().putInt("user" + r10 + "Id", parseInt).apply();
                        ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.3
                            @Override // java.lang.Runnable
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                                builder.setTitle("Giriş Başarılı.");
                                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        dialogInterface.dismiss();
                                        if (UserSystem.this.context instanceof MainActivity) {
                                            ((MainActivity) UserSystem.this.context).canReload = true;
                                            ((MainActivity) UserSystem.this.context).changeActiveUser(r10);
                                            ((MainActivity) UserSystem.this.context).reloadActivity();
                                        }
                                    }
                                });
                                builder.create().show();
                            }
                        });
                    } else {
                        ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.4
                            @Override // java.lang.Runnable
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                                builder.setTitle("Bu kullanıcı ile girişiniz bulunmakta.");
                                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.4.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        dialogInterface.dismiss();
                                        if (UserSystem.this.context instanceof MainActivity) {
                                            ((MainActivity) UserSystem.this.context).canReload = true;
                                        }
                                    }
                                });
                                builder.create().show();
                            }
                        });
                    }
                } else {
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.5
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            if (Integer.parseInt(urlContent) == -1) {
                                builder.setMessage("Bu kullanıcı adı ile bir kullanıcı bulunmamaktadır.");
                            } else if (Integer.parseInt(urlContent) == -2) {
                                builder.setMessage("Girdiğiniz şifre yanlış.");
                            } else {
                                builder.setMessage("Giriş Başarısız");
                            }
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.5.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
            } catch (Exception unused3) {
            }
        }
        return this.res;
    }

    private String joinUser(final int r15) {
        if (this.context instanceof MainActivity) {
            final String urlContent = getUrlContent(this.server + "sey/back/user.php?type=add&email=" + ((MainActivity) this.context).newUserInfo.get(0) + "&pass=" + ((MainActivity) this.context).newUserInfo.get(1) + "&mail=" + ((MainActivity) this.context).newUserInfo.get(2));
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("Join Res ->");
            sb.append(urlContent);
            printStream.println(sb.toString());
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("prefName", 0);
            try {
                if (Integer.parseInt(urlContent) > 0 && Integer.parseInt(urlContent) != sharedPreferences.getInt("user1Id", -1) && Integer.parseInt(urlContent) != sharedPreferences.getInt("user2Id", -1)) {
                    sharedPreferences.edit().putString("user" + r15 + "Mail", ((MainActivity) this.context).newUserInfo.get(2)).apply();
                    sharedPreferences.edit().putString("user" + r15 + "Email", ((MainActivity) this.context).newUserInfo.get(0)).apply();
                    sharedPreferences.edit().putInt("user" + r15 + "Id", Integer.parseInt(urlContent)).apply();
                    String urlContent2 = getUrlContent(this.server + "sey/back/hasMail.php?username=" + ((MainActivity) this.context).newUserInfo.get(0));
                    if (!urlContent2.contains("Nomail")) {
                        sharedPreferences.edit().putString("user" + r15 + "Mail", urlContent2).apply();
                    }
                    if (sharedPreferences.getInt(TtmlNode.ATTR_ID, -1) == -1) {
                        sharedPreferences.edit().putString("mail", ((MainActivity) this.context).newUserInfo.get(2)).apply();
                        if (!urlContent2.contains("Nomail")) {
                            sharedPreferences.edit().putString("mail", urlContent2).apply();
                        }
                        sharedPreferences.edit().putString("email", ((MainActivity) this.context).newUserInfo.get(0)).apply();
                        sharedPreferences.edit().putInt(TtmlNode.ATTR_ID, Integer.parseInt(urlContent)).apply();
                        sharedPreferences.edit().putBoolean("hasMail2", !urlContent2.contains("Nomail")).apply();
                    }
                    sharedPreferences.edit().putBoolean("user" + r15 + "hasMail", urlContent2.contains("Nomail") ? false : true).apply();
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.6
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            builder.setTitle("Giriş Yapıldı.");
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.6.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                        ((MainActivity) UserSystem.this.context).changeActiveUser(r15);
                                        ((MainActivity) UserSystem.this.context).reloadActivity();
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    });
                } else if (Integer.parseInt(urlContent) < 0) {
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.7
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            if (Integer.parseInt(urlContent) == -4) {
                                builder.setMessage("Bu Kullanıcı Adı İle Üyelik Bulunmakta, Eğer Kullanıcı Adı Size Aitse Şifreniz Yanlış.");
                            } else if (Integer.parseInt(urlContent) == -1) {
                                builder.setMessage("Üyelik Oluşturulurken Hata Oluştu. Tekrar Deneyiniz.");
                            } else if (Integer.parseInt(urlContent) == -5) {
                                builder.setMessage("Bu Kullanıcı, Daha Önceden İsteğiniz Üzerine Silinmişti.");
                            } else {
                                builder.setMessage("Giriş Başarısız");
                            }
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.7.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    });
                } else {
                    ((MainActivity) this.context).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.8
                        @Override // java.lang.Runnable
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(UserSystem.this.context, R.style.AlertDialog);
                            builder.setTitle("Bu kullanıcı ile girişiniz bulunmakta.");
                            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.GetsPack.UserSystem.8.1
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int r2) {
                                    dialogInterface.dismiss();
                                    if (UserSystem.this.context instanceof MainActivity) {
                                        ((MainActivity) UserSystem.this.context).canReload = true;
                                    }
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
            } catch (Exception unused) {
            }
        }
        return this.res;
    }

    private String addFav() {
        String urlContent = getUrlContent(this.server + "sey/back/user.php?type=fav&u_id=" + this.movieProfile.id + "&f_id=" + this.movieProfile.myMovie.getId() + "&do=" + this.movieProfile.isFav);
        System.out.println(urlContent);
        return urlContent;
    }

    private String addFavLongClick() {
        String urlContent = getUrlContent(this.server + "sey/back/user.php?type=fav&u_id=" + this.movieProfile.id + "&f_id=" + this.movieProfile.myMovie.getId() + "&do=" + this.movieProfile.isFav);
        System.out.println(urlContent);
        return urlContent;
    }

    private String checkFav() {
        return getUrlContent(this.server + "sey/back/user.php?type=check&u_id=" + this.movieProfile.id + "&f_id=" + this.movieProfile.myMovie.getId());
    }

    private String addIpTvFav() {
        String urlContent = getUrlContent(this.server + "sey/back/user.php?type=faviptv&u_id=" + this.itemShow.id + "&link=" + Uri.encode(this.itemShow.favLink) + "&name=" + this.itemShow.favName + "&image=" + this.itemShow.favImage + "&do=" + this.itemShow.favDo + "&channelType=" + this.itemShow.iptvFavType);
        System.out.println(urlContent);
        return urlContent == null ? "-3" : urlContent;
    }

    private String addRadioFav() {
        String urlContent = getUrlContent(this.server + "sey/back/user.php?type=favradio&u_id=" + this.itemShow.id + "&link=" + Uri.encode(this.itemShow.favLink) + "&name=" + this.itemShow.favName + "&image=" + this.itemShow.favImage + "&do=" + this.itemShow.favDo);
        System.out.println(urlContent);
        return urlContent == null ? "-3" : urlContent;
    }

    private String checkIpTvFav() {
        return getUrlContent(this.server + "sey/back/user.php?type=checkiptv&u_id=" + this.movieProfile.id + "&f_id=" + this.movieProfile.myMovie.getId());
    }

    private void saveCur() {
        String str;
        if (this.videoView.u_id != null) {
            if (this.videoView.isTV.booleanValue()) {
                str = "isTv=1&isDone=" + this.videoView.isDone;
            } else {
                str = "";
            }
            getUrlContent(this.server + "sey/back/save.php?type=s&u_id=" + this.videoView.u_id + "&mili=" + this.videoView.mili + "&m_id=" + this.videoView.m_id + "&" + str);
        }
    }

    private void getLatestEpisode() {
        if (this.videoView.u_id != null) {
            try {
                this.episode = new EpisodeClass(new JSONObject(getUrlContent(this.server + "sey/back/episodes.php?id=" + this.videoView.p_id + "&u_id=" + this.videoView.id)).getJSONArray("episodes").getJSONObject(0));
                JSONArray jSONArray = new JSONObject(getUrlContent(this.server + "sey/back/streams.php?id=" + this.videoView.p_id + ("&isTv=1&e=" + this.episode.getEpisode() + "&s=" + this.episode.getSeason() + "&u_id=" + this.videoView.id))).getJSONArray("links");
                for (int r3 = 0; r3 < jSONArray.length(); r3++) {
                    this.episode.getStreams().add(new StreamClass(jSONArray.getJSONObject(r3)));
                }
            } catch (Exception unused) {
            }
        }
    }

    private String getCur() {
        if (this.movieProfile.myMovie.getType() == PosterClass.Type.MOVIE) {
            return getUrlContent(this.server + "sey/back/save.php?type=g&u_id=" + Statics.getUserID(this.movieProfile) + "&m_id=" + this.movieProfile.myMovie.getId());
        }
        if (this.movieProfile.myMovie.getType() != PosterClass.Type.SERIES || !(this.movieProfile.myMovie instanceof EpisodeClass)) {
            return "0";
        }
        return getUrlContent(this.server + "sey/back/save.php?isTv=1&type=g&u_id=" + Statics.getUserID(this.movieProfile) + "&m_id=" + ((EpisodeClass) this.movieProfile.myMovie).getEp_id());
    }

    private String getUrlContent(String str) {
        try {
            if (Statics.preferences.getBoolean("includeYK", false) && str.contains("streams.php")) {
                str = str + "&includeYK=1";
            }
        } catch (Exception unused) {
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
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
}
