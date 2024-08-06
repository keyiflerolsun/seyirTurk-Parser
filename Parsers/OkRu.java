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
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetOkRu;
import com.swenauk.seyirturk.R;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OkRu extends Parsers {
    public String parsed;

    public OkRu(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetOkRu(this).execute(str);
    }

    public void resumeParse() {
        try {
            JSONArray jSONArray = new JSONObject(this.parsed).getJSONArray("videos");
            for (int r1 = 0; r1 < jSONArray.length(); r1++) {
                JSONObject jSONObject = jSONArray.getJSONObject(r1);
                this.streamUrls.put(jSONObject.getString("name"), jSONObject.getString("url"));
            }
            if (!this.streamUrls.isEmpty() && (!this.streamUrls.containsValue("") || this.streamUrls.size() != 1)) {
                createAlertWItems("Çözünürlük Seçiniz");
                return;
            }
            showBuffer();
            showAlert();
        } catch (Exception e) {
            showBuffer();
            showAlert();
            e.printStackTrace();
        }
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.OkRu.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                System.out.println(OkRu.this.streamUrls.get(charSequenceArr[r4]));
                OkRu.this.showBuffer();
                OkRu okRu = OkRu.this;
                okRu.videoUri = Uri.parse(okRu.streamUrls.get(charSequenceArr[r4]));
                new DefaultDataSourceFactory(OkRu.this.calledContext, Util.getUserAgent(OkRu.this.calledContext, "iFrame"));
                OkRu.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.OkRu.1.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://odnoklassniki.ru/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(OkRu.this.videoUri));
                OkRu.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
