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
import com.swenauk.mainmenu.GetsPack.GetDizipubPlus;
import com.swenauk.seyirturk.R;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class DizipubPlus extends Parsers {
    public String cookie;
    public String parsed;

    public DizipubPlus(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.cookie = "";
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetDizipubPlus(this).execute(str);
    }

    public void resumeParse() {
        try {
            System.out.println(this.parsed);
            Matcher matcher = Pattern.compile("(.*?)\\|(.*?),").matcher(this.parsed);
            while (matcher.find()) {
                this.streamUrls.put(matcher.group(1), URLDecoder.decode(matcher.group(2).replace("\\u0026", "&").replace("\\u003d", "="), StringUtil.__UTF8));
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
        this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.DizipubPlus.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
                defaultHttpDataSource.setRequestProperty("Referer", "https://youtube.googleapis.com/");
                defaultHttpDataSource.setRequestProperty("Cookie", DizipubPlus.this.cookie);
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.DizipubPlus.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                DizipubPlus.this.showBuffer();
                System.out.println(DizipubPlus.this.streamUrls.get(charSequenceArr[r4]));
                DizipubPlus dizipubPlus = DizipubPlus.this;
                dizipubPlus.videoUri = Uri.parse(dizipubPlus.streamUrls.get(charSequenceArr[r4]));
                DizipubPlus.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.DizipubPlus.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://youtube.googleapis.com/");
                        System.out.println(DizipubPlus.this.cookie);
                        defaultHttpDataSource.setRequestProperty("Cookie", DizipubPlus.this.cookie);
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(DizipubPlus.this.videoUri));
                DizipubPlus.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
