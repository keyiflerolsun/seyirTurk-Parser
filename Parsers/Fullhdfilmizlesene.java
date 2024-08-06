package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.swenauk.mainmenu.GetsPack.GetFullhdfilmizlesene;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Sivvat.Trstx;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class Fullhdfilmizlesene extends Parsers {
    public String audioUrl;
    public String comingUrl;
    public boolean isAlt;
    public String lang;
    public String mainUrl;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subtitle;

    public Fullhdfilmizlesene(String str, Context context, ExoPlayer exoPlayer, DefaultTrackSelector defaultTrackSelector, BandwidthMeter bandwidthMeter) {
        super(str, context, exoPlayer);
        this.comingUrl = str;
        this.trackSelector = defaultTrackSelector;
        this.bandwidthMeter = bandwidthMeter;
    }

    public Fullhdfilmizlesene(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
        this.comingUrl = str;
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        try {
            this.isAlt = true;
            Matcher matcher = Pattern.compile("\\?l=(\\d)").matcher(str);
            if (matcher.find()) {
                this.lang = matcher.group(1);
            }
            new GetFullhdfilmizlesene(this).execute(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlternates(final Map<String, String> map) {
        if (!((Activity) this.calledContext).isDestroyed()) {
            if (map.size() > 1) {
                final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
                builder.setTitle("Kaynak Seçiniz:");
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r8) {
                        Fullhdfilmizlesene.this.mainUrl = (String) map.get(charSequenceArr[r8]);
                        try {
                            if (Fullhdfilmizlesene.this.mainUrl.contains("trstx")) {
                                Core.Language language = Core.Language.TR;
                                if (!Fullhdfilmizlesene.this.lang.equals("1")) {
                                    language = Core.Language.EN;
                                }
                                new Trstx(Fullhdfilmizlesene.this.mainUrl, Fullhdfilmizlesene.this.calledContext, Fullhdfilmizlesene.this.player, null, language);
                                return;
                            }
                            Fullhdfilmizlesene.this.isAlt = false;
                            new GetFullhdfilmizlesene(Fullhdfilmizlesene.this).execute(Fullhdfilmizlesene.this.mainUrl);
                        } catch (Exception unused) {
                            Fullhdfilmizlesene.this.showBuffer();
                            Fullhdfilmizlesene.this.showAlert();
                        }
                    }
                });
                this.alert = builder.create();
                this.alert.show();
            } else {
                this.isAlt = false;
                new GetFullhdfilmizlesene(this).execute(this.mainUrl);
            }
        }
        this.isAlt = false;
    }

    public void resumeParse() {
        try {
            if (this.streamUrl == null && this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
                return;
            }
            if (this.streamUrl.contains("vidmoly")) {
                if (!this.streamUrl.contains("http")) {
                    this.streamUrl = "https:" + this.streamUrl;
                }
                new VidMoly(this.streamUrl, this.calledContext, this.player);
                return;
            }
            if (this.streamUrl.contains("trstx")) {
                Core.Language language = Core.Language.TR;
                if (!this.lang.equals("1")) {
                    language = Core.Language.EN;
                }
                new Trstx(this.streamUrl, this.calledContext, this.player, null, language);
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
        this.videoUri = Uri.parse(this.streamUrl);
        this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.2
            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
            public DataSource createDataSource() {
                DefaultHttpDataSource createDataSource = new DefaultHttpDataSource.Factory().setReadTimeoutMs(8000).setConnectTimeoutMs(8000).setAllowCrossProtocolRedirects(true).setUserAgent("iFrame").createDataSource();
                if (Fullhdfilmizlesene.this.streamUrl.contains("vidmoly")) {
                    createDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                } else {
                    createDataSource.setRequestProperty("Origin", "https://rapidvid.net");
                }
                return createDataSource;
            }
        }).setAllowChunklessPreparation(true).createMediaSource(MediaItem.fromUri(this.videoUri));
        if (this.audioUrl != null) {
            this.mergedSource = new MergingMediaSource(this.mediaSource, new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36", 8000, 8000, true, null);
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(Uri.parse(this.audioUrl))));
        }
        if (this.subtitle != null && this.lang.equals("0") && !this.subtitle.equals("")) {
            System.out.println("Sub var");
            SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(this.calledContext, new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36").setConnectTimeoutMs(8000).setConnectTimeoutMs(8000))).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(this.subtitle)).setMimeType(MimeTypes.APPLICATION_SUBRIP).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
            MergingMediaSource mergingMediaSource = this.mergedSource;
            if (mergingMediaSource != null) {
                this.mergedSource = new MergingMediaSource(mergingMediaSource, createMediaSource);
            } else {
                this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource);
            }
        }
        if (this.subtitle != null && this.lang.equals("0") && !this.subtitle.equals("")) {
            playVideoSub();
        } else {
            playVideo();
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r8) {
                Fullhdfilmizlesene.this.showBuffer();
                Fullhdfilmizlesene fullhdfilmizlesene = Fullhdfilmizlesene.this;
                fullhdfilmizlesene.videoUri = Uri.parse(fullhdfilmizlesene.streamUrls.get(charSequenceArr[r8]));
                Fullhdfilmizlesene.this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.4.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(Fullhdfilmizlesene.this.videoUri));
                if (Fullhdfilmizlesene.this.audioUrl != null) {
                    HlsMediaSource createMediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.4.2
                        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                        public DataSource createDataSource() {
                            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                            defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                            return defaultHttpDataSource;
                        }
                    }).createMediaSource(MediaItem.fromUri(Uri.parse(Fullhdfilmizlesene.this.audioUrl)));
                    Fullhdfilmizlesene fullhdfilmizlesene2 = Fullhdfilmizlesene.this;
                    fullhdfilmizlesene2.mergedSource = new MergingMediaSource(fullhdfilmizlesene2.mediaSource, createMediaSource);
                }
                if (Fullhdfilmizlesene.this.subtitle != null) {
                    System.out.println("Sub var");
                    SingleSampleMediaSource createMediaSource2 = new SingleSampleMediaSource.Factory(new DefaultDataSource.Factory(Fullhdfilmizlesene.this.calledContext)).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(Fullhdfilmizlesene.this.subtitle)).setMimeType(MimeTypes.APPLICATION_SUBRIP).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                    if (Fullhdfilmizlesene.this.mergedSource != null) {
                        Fullhdfilmizlesene fullhdfilmizlesene3 = Fullhdfilmizlesene.this;
                        fullhdfilmizlesene3.mergedSource = new MergingMediaSource(fullhdfilmizlesene3.mergedSource, createMediaSource2);
                    } else {
                        Fullhdfilmizlesene fullhdfilmizlesene4 = Fullhdfilmizlesene.this;
                        fullhdfilmizlesene4.mergedSource = new MergingMediaSource(fullhdfilmizlesene4.mediaSource, createMediaSource2);
                    }
                }
                if (Fullhdfilmizlesene.this.subtitle != null) {
                    Fullhdfilmizlesene.this.playVideoSub();
                } else {
                    Fullhdfilmizlesene.this.playVideo();
                }
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Fullhdfilmizlesene.this.player.prepare(Fullhdfilmizlesene.this.mergedSource, false, false);
                } catch (Exception unused) {
                    Fullhdfilmizlesene.this.player.prepare(Fullhdfilmizlesene.this.mediaSource, false, false);
                }
                if (Fullhdfilmizlesene.this.lang.equals("0")) {
                    System.out.println(Fullhdfilmizlesene.this.player.getCurrentTrackSelections());
                    Fullhdfilmizlesene.this.trackSelector.setParameters(Fullhdfilmizlesene.this.trackSelector.getParameters().buildUpon().setPreferredAudioLanguage("English"));
                    if (Fullhdfilmizlesene.this.calledContext instanceof VideoView) {
                        ((VideoView) Fullhdfilmizlesene.this.calledContext).subtitles.put("Türkçe", true);
                    }
                }
                if (Fullhdfilmizlesene.this.calledContext instanceof VideoView) {
                    ((VideoView) Fullhdfilmizlesene.this.calledContext).setVideoUri(Fullhdfilmizlesene.this.videoUri);
                    try {
                        if (!Fullhdfilmizlesene.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) Fullhdfilmizlesene.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Fullhdfilmizlesene.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.5.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene.5.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Fullhdfilmizlesene.this.player.seekTo(parseInt);
                                        Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused2) {
                        Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
                    }
                }
                Fullhdfilmizlesene.this.player.setPlayWhenReady(true);
            }
        });
    }
}
