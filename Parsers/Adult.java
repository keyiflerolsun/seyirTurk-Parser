package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetAdult;
import com.swenauk.mainmenu.Statics;
import com.swenauk.seyirturk.R;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Adult extends Parsers {
    public String comingUrl;
    public String cookie;
    public Boolean isMulti;
    public boolean isSto;
    public String parsed;

    public Adult(String str, Context context, ExoPlayer exoPlayer, boolean z) {
        super(str, context, exoPlayer);
        this.isSto = false;
        this.isFragman = true;
        if (str.contains("filemoon")) {
            this.isFragman = false;
        }
        this.comingUrl = str;
        this.parsed = "";
        this.cookie = "";
        this.isMulti = false;
        this.isSto = z;
        if (str.contains("pornhub") || str.contains("ashemaletube") || str.contains("xvideos") || str.contains("7dak.com") || str.contains("xnxx.com")) {
            this.isMulti = true;
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetAdult(this).execute(str);
    }

    public void resumeParse() {
        try {
            try {
                if (!this.isMulti.booleanValue()) {
                    this.streamUrl = this.parsed;
                    if (!this.streamUrl.contains("http") && !this.streamUrl.contains("https")) {
                        this.streamUrl = "https:" + this.streamUrl;
                    }
                } else {
                    JSONObject jSONObject = new JSONObject(this.parsed);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        this.streamUrls.put(next, jSONObject.getString(next));
                    }
                }
            } catch (Exception unused) {
                if (this.streamUrls.isEmpty()) {
                    showBuffer();
                    showAlert();
                }
            }
            if (this.streamUrls.size() == 1) {
                if (this.streamUrls.containsKey("Alone")) {
                    this.streamUrl = this.streamUrls.get("Alone");
                } else if (this.streamUrls.containsKey("720p")) {
                    this.streamUrl = this.streamUrls.get("720p");
                } else if (this.streamUrls.containsKey("1080p")) {
                    this.streamUrl = this.streamUrls.get("1080p");
                } else if (this.streamUrls.containsKey("480p")) {
                    this.streamUrl = this.streamUrls.get("480p");
                } else {
                    Iterator<String> it = this.streamUrls.keySet().iterator();
                    while (it.hasNext()) {
                        this.streamUrl = this.streamUrls.get(it.next());
                    }
                }
                this.streamUrls.clear();
            }
            if (this.isSto) {
                return;
            }
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl != null) {
                prepareVideo();
            }
            if (this.streamUrls.isEmpty()) {
                return;
            }
            createAlertWItems("Seçiniz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        final String str = this.streamUrl;
        System.out.println(this.streamUrl);
        if (this.comingUrl.contains("clipwatching")) {
            str = "http://streamingporn.xyz";
        } else if (this.comingUrl.contains("streamtape")) {
            str = "https://streamtape.com";
        } else if (this.comingUrl.contains("luluvdo")) {
            str = "https://luluvdo.com/";
        }
        if (this.streamUrl.contains("m3u8")) {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Adult.1
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36", 8000, 8000, true, null);
                    if (!Adult.this.comingUrl.contains("mixdrop")) {
                        if (Statics.referer.equals("")) {
                            defaultHttpDataSource.setRequestProperty("Referer", str);
                        } else {
                            defaultHttpDataSource.setRequestProperty("Referer", Statics.referer);
                            Statics.referer = "";
                        }
                    }
                    defaultHttpDataSource.setRequestProperty("Cookie", Adult.this.cookie);
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        } else {
            this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Adult.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame", 8000, 8000, true, null);
                    if (!Adult.this.comingUrl.contains("mixdrop")) {
                        defaultHttpDataSource.setRequestProperty("Referer", str);
                    }
                    defaultHttpDataSource.setRequestProperty("Cookie", Adult.this.cookie);
                    defaultHttpDataSource.setRequestProperty("User-Agent", "Firefox");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        playVideo();
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Adult.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, final int r5) {
                Adult.this.showBuffer();
                Adult adult = Adult.this;
                adult.videoUri = Uri.parse(adult.streamUrls.get(charSequenceArr[r5]));
                System.out.println(Adult.this.streamUrls.get(charSequenceArr[r5]));
                if (Adult.this.comingUrl.contains("freeomovie") || Adult.this.comingUrl.contains("pandamovie")) {
                    new Adult(Adult.this.streamUrls.get(charSequenceArr[r5]), Adult.this.calledContext, Adult.this.player, Adult.this.isSto);
                    return;
                }
                if (Adult.this.streamUrls.get(charSequenceArr[r5]).contains("m3u8")) {
                    Adult.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Adult.3.1
                        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                        public DataSource createDataSource() {
                            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                            defaultHttpDataSource.setRequestProperty("Referer", Adult.this.streamUrls.get(charSequenceArr[r5]));
                            return defaultHttpDataSource;
                        }
                    }).createMediaSource(MediaItem.fromUri(Adult.this.videoUri));
                } else {
                    Adult.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Adult.3.2
                        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                        public DataSource createDataSource() {
                            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                            defaultHttpDataSource.setRequestProperty("Referer", Adult.this.streamUrls.get(charSequenceArr[r5]));
                            return defaultHttpDataSource;
                        }
                    }).createMediaSource(MediaItem.fromUri(Adult.this.videoUri));
                }
                Adult.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
