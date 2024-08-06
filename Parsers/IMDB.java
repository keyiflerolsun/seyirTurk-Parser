package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetIMDB;
import com.swenauk.seyirturk.R;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class IMDB extends Parsers {
    public JSONArray parsed;

    public IMDB(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isFragman = true;
        this.parsed = new JSONArray();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetIMDB(this).execute(str);
    }

    public void resumeParse() {
        for (int r5 = 0; r5 < this.parsed.length(); r5++) {
            try {
                try {
                    if (this.parsed.getJSONObject(r5).getString("videoMimeType").toLowerCase().contains("mp4")) {
                        if (this.parsed.getJSONObject(r5).has("displayName")) {
                            this.streamUrls.put(this.parsed.getJSONObject(r5).getJSONObject("displayName").getString("value"), this.parsed.getJSONObject(r5).getString("url"));
                        } else {
                            this.streamUrls.put(this.parsed.getJSONObject(r5).getString("definition"), this.parsed.getJSONObject(r5).getString("videoUrl"));
                        }
                    }
                } catch (Exception unused) {
                    showBuffer();
                    showAlert();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
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
            }
            this.streamUrls.clear();
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
        createAlertWItems("Çözünürlük Seçiniz");
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.IMDB.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                return defaultHttpDataSource;
            }
        }).createMediaSource(MediaItem.fromUri(this.videoUri));
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.IMDB.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                IMDB.this.showBuffer();
                IMDB r3 = IMDB.this;
                r3.videoUri = Uri.parse(r3.streamUrls.get(charSequenceArr[r4]));
                IMDB.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.IMDB.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(IMDB.this.videoUri));
                IMDB.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
