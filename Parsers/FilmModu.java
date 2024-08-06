package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.swenauk.mainmenu.GetsPack.GetFilmModu;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.net.URI;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FilmModu extends Parsers {
    public String main;
    private MergingMediaSource mergedSource;
    public String parsed;
    private String subtitle;

    public FilmModu(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        try {
            this.main = new URI(str).getHost();
            System.out.println(this.main);
        } catch (Exception unused) {
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetFilmModu(this).execute(str);
    }

    public void resumeParse() {
        try {
            this.parsed = this.parsed.replaceAll("\\<.*?\\>", "");
            try {
                JSONObject jSONObject = new JSONObject(this.parsed);
                if (jSONObject.has("subtitle")) {
                    this.subtitle = jSONObject.getString("subtitle");
                    this.subtitle = "https://" + this.main + this.subtitle;
                    System.out.println(this.subtitle);
                }
                JSONArray jSONArray = jSONObject.getJSONArray("sources");
                for (int r5 = 0; r5 < jSONArray.length(); r5++) {
                    this.streamUrls.put(jSONArray.getJSONObject(r5).getString(HtmlLabel.TAG_NAME), jSONArray.getJSONObject(r5).getString(DomElement.SRC_ATTRIBUTE));
                }
            } catch (Exception e) {
                System.out.println(e);
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
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        System.out.println("showVideo");
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.1
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                return new DefaultHttpDataSource("iFrame");
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r6) {
                FilmModu.this.showBuffer();
                FilmModu filmModu = FilmModu.this;
                filmModu.videoUri = Uri.parse(filmModu.streamUrls.get(charSequenceArr[r6]));
                System.out.println(FilmModu.this.videoUri);
                FilmModu.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.2.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        return new DefaultHttpDataSource("iFrame");
                    }
                }).createMediaSource(MediaItem.fromUri(FilmModu.this.videoUri));
                if (FilmModu.this.subtitle != null) {
                    System.out.println("Sub var");
                    SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(FilmModu.this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(FilmModu.this.subtitle)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                    FilmModu filmModu2 = FilmModu.this;
                    filmModu2.mergedSource = new MergingMediaSource(filmModu2.mediaSource, createMediaSource);
                }
                if (FilmModu.this.subtitle != null) {
                    FilmModu.this.playVideoSub();
                } else {
                    FilmModu.this.playVideo();
                }
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.3
            @Override // java.lang.Runnable
            public void run() {
                FilmModu.this.player.prepare(FilmModu.this.mergedSource, false, false);
                if (FilmModu.this.calledContext instanceof VideoView) {
                    ((VideoView) FilmModu.this.calledContext).setVideoUri(FilmModu.this.videoUri);
                    try {
                        if (!FilmModu.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) FilmModu.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FilmModu.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.3.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        FilmModu.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.FilmModu.3.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        FilmModu.this.player.seekTo(parseInt);
                                        FilmModu.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                FilmModu.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            FilmModu.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        FilmModu.this.player.setPlayWhenReady(true);
                    }
                }
                FilmModu.this.player.setPlayWhenReady(true);
            }
        });
    }
}
