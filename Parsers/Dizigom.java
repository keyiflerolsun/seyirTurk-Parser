package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetDizigom;
import com.swenauk.seyirturk.R;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Dizigom extends Parsers {
    public boolean isAlt;
    public String parsed;

    public Dizigom(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        this.isAlt = true;
        new GetDizigom(this).execute(str);
    }

    public void showAlternates(final Map<String, String> map) {
        if (!((Activity) this.calledContext).isDestroyed()) {
            if (map.size() > 1) {
                final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
                builder.setTitle("Kaynak Seçiniz:");
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Dizigom.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r5) {
                        new GetDizigom(Dizigom.this).execute((String) map.get(charSequenceArr[r5]));
                    }
                });
                this.alert = builder.create();
                this.alert.show();
            } else {
                this.isAlt = false;
                new GetDizigom(this).execute(map.get("Dizigom"));
            }
        }
        this.isAlt = false;
    }

    public void resumeParse() {
        String str;
        try {
            str = this.parsed;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (str != null) {
            if (str.contains(HtmlLabel.TAG_NAME)) {
                try {
                    JSONArray jSONArray = new JSONObject(this.parsed).getJSONArray("sources");
                    for (int r2 = 0; r2 < jSONArray.length(); r2++) {
                        this.streamUrls.put(jSONArray.getJSONObject(r2).getString(HtmlLabel.TAG_NAME), jSONArray.getJSONObject(r2).getString("file"));
                    }
                    createAlertWItems("Çözünürlük Seçiniz");
                    return;
                } catch (Exception unused) {
                    showBuffer();
                    showAlert();
                    return;
                }
            }
            String replace = this.parsed.replace("\\", "");
            this.parsed = replace;
            this.streamUrl = replace;
            try {
                prepareVideo();
                return;
            } catch (Exception unused2) {
                showBuffer();
                showAlert();
                return;
            }
            e.printStackTrace();
            return;
        }
        showBuffer();
        showAlert();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4") && !this.streamUrl.contains(".m3u8")) {
            if (this.streamUrl.contains("yandex.ru")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Dizigom.2
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://play.dizigom.tv/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Dizigom.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://play.dizigom.tv/");
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Dizigom.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Dizigom.this.showBuffer();
                System.out.println(Dizigom.this.streamUrls.get(charSequenceArr[r4]));
                Dizigom dizigom = Dizigom.this;
                dizigom.videoUri = Uri.parse(dizigom.streamUrls.get(charSequenceArr[r4]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(Dizigom.this.calledContext, Util.getUserAgent(Dizigom.this.calledContext, "iFrame"));
                Dizigom.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(Dizigom.this.videoUri));
                Dizigom.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
