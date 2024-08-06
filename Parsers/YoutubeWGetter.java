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
import com.swenauk.mainmenu.GetsPack.GetYoutube;
import com.swenauk.seyirturk.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class YoutubeWGetter extends Parsers {
    private String audioTrack;
    public String parsed;

    public YoutubeWGetter(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        System.out.println(str);
        new GetYoutube(this).execute(str);
    }

    public void resumeParse() {
        for (String str : this.parsed.split("\n")) {
            if (str.contains("ytInitialPlayerResponse =")) {
                Matcher matcher = Pattern.compile("ytInitialPlayerResponse =(.*?)</script>").matcher(str);
                int r7 = 0;
                while (matcher.find()) {
                    if (r7 != 1) {
                        r7++;
                        matcher.group(1);
                        System.out.println("Deneme");
                    } else {
                        try {
                            String str2 = matcher.group(1).split("</script>")[0];
                            String substring = str2.substring(0, str2.length() - 1);
                            if (substring.contains(".m3u8")) {
                                this.streamUrl = new JSONObject(substring).getJSONObject("streamingData").getString("hlsManifestUrl");
                            } else {
                                JSONArray jSONArray = new JSONObject(substring).getJSONObject("streamingData").getJSONArray("adaptiveFormats");
                                System.out.println(jSONArray);
                                for (int r9 = 0; r9 < jSONArray.length(); r9++) {
                                    JSONObject jSONObject = jSONArray.getJSONObject(r9);
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
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.YoutubeWGetter.1
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.YoutubeWGetter.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                YoutubeWGetter.this.showBuffer();
                YoutubeWGetter youtubeWGetter = YoutubeWGetter.this;
                youtubeWGetter.videoUri = Uri.parse(youtubeWGetter.streamUrls.get(charSequenceArr[r6]));
                System.out.println(YoutubeWGetter.this.videoUri);
                Uri parse = Uri.parse(YoutubeWGetter.this.audioTrack);
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(YoutubeWGetter.this.calledContext, Util.getUserAgent(YoutubeWGetter.this.calledContext, "iFrame"));
                ProgressiveMediaSource createMediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(YoutubeWGetter.this.videoUri));
                YoutubeWGetter.this.mediaSource = new MergingMediaSource(new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(parse)), createMediaSource);
                YoutubeWGetter.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
