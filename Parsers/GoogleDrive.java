package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.seyirturk.R;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class GoogleDrive extends Parsers {
    private String audioTrack;

    public GoogleDrive(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        try {
            System.out.println(str);
            String decode = URLDecoder.decode(URLDecoder.decode(str, "UTF-8"), "UTF-8");
            if (decode.contains("fmt_stream_map=")) {
                Matcher matcher = Pattern.compile("fmt_stream_map=(.*)&url_encoded_fmt_stream_map").matcher(decode);
                if (matcher.find()) {
                    System.out.println("Found First");
                    Matcher matcher2 = Pattern.compile("|(.*?),").matcher(matcher.group(1));
                    if (matcher2.find()) {
                        System.out.println("Found Second");
                        this.streamUrl = matcher2.group(1);
                    }
                }
            }
        } catch (Exception unused) {
        }
        try {
            if (this.streamUrls.isEmpty() && this.streamUrl == null) {
                showBuffer();
            } else if (this.streamUrls.isEmpty()) {
                prepareVideo();
                System.out.println(this.streamUrl);
            } else {
                createAlertWItems("Çözünürlük Seçiniz");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.GoogleDrive.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                defaultHttpDataSource.setRequestProperty("Referer", "https://youtube.com/");
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.GoogleDrive.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                GoogleDrive.this.showBuffer();
                GoogleDrive googleDrive = GoogleDrive.this;
                googleDrive.videoUri = Uri.parse(googleDrive.streamUrls.get(charSequenceArr[r6]));
                Uri parse = Uri.parse(GoogleDrive.this.audioTrack);
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(GoogleDrive.this.calledContext, Util.getUserAgent(GoogleDrive.this.calledContext, "iFrame"));
                GoogleDrive.this.mediaSource = new MergingMediaSource(new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(parse)), new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(GoogleDrive.this.videoUri)));
                GoogleDrive.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
