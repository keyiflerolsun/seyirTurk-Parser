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
import com.swenauk.mainmenu.GetsPack.GetShowTV;
import com.swenauk.seyirturk.R;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class ShowTV extends Parsers {
    public JSONArray parsed;

    public ShowTV(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.parsed = new JSONArray();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetShowTV(this).execute(str);
    }

    public void resumeParse() {
        for (int r6 = 0; r6 < this.parsed.length(); r6++) {
            try {
                try {
                    System.out.println(this.parsed.length());
                    this.streamUrls.put(this.parsed.getJSONObject(r6).getString("name"), this.parsed.getJSONObject(r6).getString("file"));
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
            } else if (this.streamUrls.containsKey("720")) {
                this.streamUrl = this.streamUrls.get("720");
            } else if (this.streamUrls.containsKey("1080")) {
                this.streamUrl = this.streamUrls.get("1080");
            } else if (this.streamUrls.containsKey("480")) {
                this.streamUrl = this.streamUrls.get("480");
            } else if (this.streamUrls.containsKey("360")) {
                this.streamUrl = this.streamUrls.get("360");
            } else if (this.streamUrls.containsKey("240")) {
                this.streamUrl = this.streamUrls.get("240");
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
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.ShowTV.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                defaultHttpDataSource.setRequestProperty("Referer", "https://www.showtv.com.tr/");
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.ShowTV.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                ShowTV.this.showBuffer();
                ShowTV showTV = ShowTV.this;
                showTV.videoUri = Uri.parse(showTV.streamUrls.get(charSequenceArr[r4]));
                ShowTV.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.ShowTV.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://www.showtv.com.tr/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(ShowTV.this.videoUri));
                ShowTV.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
