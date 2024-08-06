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
import com.swenauk.mainmenu.GetsPack.GetFileRu;
import com.swenauk.mainmenu.GetsPack.GetFileRuFirst;
import com.swenauk.seyirturk.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FileRU extends Parsers {
    public String parsed;

    public FileRU(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetFileRuFirst(this).execute(str);
    }

    public void secondParse() {
        try {
            Boolean bool = false;
            for (String str : this.parsed.split("\n")) {
                if (str.contains("$.getJSON('") && !bool.booleanValue()) {
                    Matcher matcher = Pattern.compile("getJSON\\('(.*?)'").matcher(str);
                    if (matcher.find()) {
                        new GetFileRu(this).execute("http://www.fileru.net/" + matcher.group(1));
                        bool = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeParse() {
        try {
            JSONArray jSONArray = new JSONObject(this.parsed).getJSONArray("sources");
            for (int r6 = 0; r6 < jSONArray.length(); r6++) {
                this.streamUrls.put(jSONArray.getJSONObject(r6).getString(HtmlLabel.TAG_NAME), jSONArray.getJSONObject(r6).getString("file"));
            }
        } catch (Exception unused) {
        }
        try {
            if (this.streamUrls.size() == 1) {
                if (this.streamUrls.containsKey("Alone")) {
                    this.streamUrl = this.streamUrls.get("Alone");
                } else if (this.streamUrls.containsKey("720p")) {
                    this.streamUrl = this.streamUrls.get("720p");
                } else if (this.streamUrls.containsKey("1080p")) {
                    this.streamUrl = this.streamUrls.get("1080p");
                } else if (this.streamUrls.containsKey("480p")) {
                    this.streamUrl = this.streamUrls.get("480p");
                } else if (this.streamUrls.containsKey("360p")) {
                    this.streamUrl = this.streamUrls.get("360p");
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
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.FileRU.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla");
                defaultHttpDataSource.setRequestProperty("Referer", "https://dizilla.com");
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.FileRU.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                FileRU.this.showBuffer();
                FileRU fileRU = FileRU.this;
                fileRU.videoUri = Uri.parse(fileRU.streamUrls.get(charSequenceArr[r4]).replace("\\", ""));
                FileRU.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.FileRU.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://dizilla.com");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(FileRU.this.videoUri));
                FileRU.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
