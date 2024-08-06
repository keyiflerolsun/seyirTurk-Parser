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
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetYjco;
import com.swenauk.seyirturk.R;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class Yjco extends Parsers {
    public String parsed;

    public Yjco(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetYjco(this).execute(str);
    }

    public void resumeParse() {
        try {
            try {
                JSONArray jSONArray = new JSONArray(this.parsed);
                for (int r5 = 0; r5 < jSONArray.length(); r5++) {
                    this.streamUrls.put(jSONArray.getJSONObject(r5).getString(HtmlLabel.TAG_NAME), jSONArray.getJSONObject(r5).getString("file"));
                }
            } catch (Exception unused) {
                showBuffer();
                showAlert();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Yjco.1
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Yjco.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Yjco.this.showBuffer();
                Yjco yjco = Yjco.this;
                yjco.videoUri = Uri.parse(yjco.streamUrls.get(charSequenceArr[r4]));
                Yjco.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Yjco.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(Yjco.this.videoUri));
                Yjco.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
