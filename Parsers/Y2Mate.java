package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetY2Mate;
import com.swenauk.seyirturk.R;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class Y2Mate extends Parsers {
    public int current;
    public ArrayList<String> defs;
    public String parsed;
    public String selected;

    public Y2Mate(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.isFragman = true;
        this.parsed = "";
        this.current = 0;
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetY2Mate(this).execute(str);
    }

    public void secondParse() {
        try {
            this.parsed = this.parsed.replace("\\", "");
            Matcher matcher = Pattern.compile("\\s(\\d*p)\\s", 32).matcher(this.parsed);
            this.defs = new ArrayList<>();
            while (matcher.find()) {
                this.defs.add(matcher.group(1));
            }
            createAlertWItems("Çözünürlük Seçiniz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeParse() {
        try {
            try {
                Pattern compile = Pattern.compile("a\\s*href\\s*=\\s*\"(.*?)\"\\s*rel", 32);
                String replace = this.parsed.replace("\\", "");
                this.parsed = replace;
                Matcher matcher = compile.matcher(replace);
                if (matcher.find()) {
                    this.streamUrl = matcher.group(1);
                    if (this.streamUrl.contains("y2mate")) {
                        this.streamUrl = this.streamUrl.replace("https", "http");
                    }
                }
            } catch (Exception unused) {
                showBuffer();
                showAlert();
            }
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl == null || this.streamUrl.contains("dailymotion.com") || this.streamUrl.contains("youtube.com")) {
                return;
            }
            prepareVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Y2Mate.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(System.getProperty("http.agent"));
                defaultHttpDataSource.setRequestProperty("Referer", "https://youtube.com");
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
        ArrayList<String> arrayList = this.defs;
        CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Y2Mate.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Y2Mate y2Mate = Y2Mate.this;
                y2Mate.selected = y2Mate.defs.get(r4).replace("p", "");
                Y2Mate.this.current = 1;
                new GetY2Mate(Y2Mate.this).execute(new String[0]);
                Y2Mate.this.showBuffer();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
