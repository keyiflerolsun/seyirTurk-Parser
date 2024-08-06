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
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.seyirturk.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class Youtube extends Parsers {
    private String audioTrack;

    public Youtube(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        for (String str2 : str.split("\n")) {
            if (str2.contains("ytplayer.config =")) {
                Matcher matcher = Pattern.compile("ytplayer.config =(.*)").matcher(str2);
                if (matcher.find()) {
                    try {
                        String str3 = matcher.group(1).split("ytplayer.load")[0].split("\"assets\":")[0];
                        String str4 = ("{\"player_response\":" + (str3.substring(0, str3.length() - 1) + StringSubstitutor.DEFAULT_VAR_END).split("\"player_response\":\"")[1]).split("user_display_image")[0];
                        String str5 = ("{\"streamingData\":" + str4.substring(0, str4.length() - 1).replace("\\", "").split("\"streamingData\":")[1]).split(",\"playerAds\"")[0].replace("codecs=", "codecs=\\").replace("\"\"", "\\\"\"").replace("u0026", "&") + StringSubstitutor.DEFAULT_VAR_END;
                        if (str5.contains(".m3u8")) {
                            this.streamUrl = new JSONObject(str5).getJSONObject("streamingData").getString("hlsManifestUrl");
                        } else {
                            JSONArray jSONArray = new JSONObject(str5).getJSONObject("streamingData").getJSONArray("adaptiveFormats");
                            System.out.println(jSONArray);
                            for (int r7 = 0; r7 < jSONArray.length(); r7++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(r7);
                                if (jSONObject.getString("mimeType").contains(MimeTypes.VIDEO_MP4)) {
                                    if (jSONObject.has("qualityLabel")) {
                                        this.streamUrls.put(jSONObject.getString("qualityLabel"), jSONObject.getString("url"));
                                    }
                                } else if (jSONObject.getString("mimeType").contains(MimeTypes.AUDIO_MP4)) {
                                    this.audioTrack = jSONObject.getString("url");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        if (!e.toString().contains("ArrayIndexOut")) {
                            showBuffer();
                            showAlert();
                        }
                    }
                }
            }
        }
        if (this.streamUrls.isEmpty() && this.streamUrl == null) {
            showBuffer();
        } else if (this.streamUrls.isEmpty()) {
            prepareVideo();
        } else {
            createAlertWItems("Çözünürlük Seçiniz");
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Youtube.1
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Youtube.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                Youtube.this.showBuffer();
                Youtube youtube = Youtube.this;
                youtube.videoUri = Uri.parse(youtube.streamUrls.get(charSequenceArr[r6]));
                System.out.println(Youtube.this.videoUri);
                Uri parse = Uri.parse(Youtube.this.audioTrack);
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(Youtube.this.calledContext, Util.getUserAgent(Youtube.this.calledContext, "iFrame"));
                ProgressiveMediaSource createMediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(Youtube.this.videoUri));
                Youtube.this.mediaSource = new MergingMediaSource(new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(parse)), createMediaSource);
                Youtube.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
