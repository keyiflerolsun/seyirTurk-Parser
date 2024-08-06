package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.swenauk.mainmenu.GetsPack.GetKorenturk;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.Map;

/* loaded from: classes3.dex */
public class Koreanturk extends Parsers {
    public Boolean isSub;
    public String mainUrl;
    private MergingMediaSource mergedSource;
    public String parsed;
    public String subLink;

    public void resumeParse() {
    }

    public Koreanturk(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        this.isSub = false;
        new GetKorenturk(this).execute(str);
    }

    public void showAlternates(final Map<String, String> map) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle("Kaynak Seçiniz:");
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Koreanturk.this.mainUrl = (String) map.get(charSequenceArr[r4]);
                if (Koreanturk.this.mainUrl.contains("s1cdn.vg")) {
                    Koreanturk.this.loadS1cdn();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("imdb.com")) {
                    Koreanturk.this.loadImdb();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("mail.ru")) {
                    Koreanturk.this.loadMailRU();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("dailymotion")) {
                    Koreanturk.this.loadDailyMotion();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("streamtape") || Koreanturk.this.mainUrl.contains("videobin") || Koreanturk.this.mainUrl.contains("gounlimited") || Koreanturk.this.mainUrl.contains("pornhub") || Koreanturk.this.mainUrl.contains("ashemaletube") || Koreanturk.this.mainUrl.contains("xvideos") || Koreanturk.this.mainUrl.contains("clipwatching") || Koreanturk.this.mainUrl.contains("7dak.com") || Koreanturk.this.mainUrl.contains("xnxx.com") || Koreanturk.this.mainUrl.contains("unlockxh1.com") || Koreanturk.this.mainUrl.contains("xhamster.com")) {
                    Koreanturk.this.loadAdult();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("chefkoch24")) {
                    Koreanturk.this.loadYesilcam();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("yabancidizi") || Koreanturk.this.mainUrl.contains("puhutv.com") || Koreanturk.this.mainUrl.contains("teve2") || Koreanturk.this.mainUrl.contains("dizilab")) {
                    Koreanturk.this.loadYabanci();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("supervideo")) {
                    Koreanturk.this.loadSuper();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("filmmodu")) {
                    Koreanturk.this.loadFilmModu();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("uptostream")) {
                    Koreanturk.this.loadUpTo();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("closeload")) {
                    Koreanturk.this.loadCloseLoad();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("vidmoly") || Koreanturk.this.mainUrl.contains("flmplayer")) {
                    Koreanturk.this.loadVidMoly();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("ok.ru") || Koreanturk.this.mainUrl.contains("odnok")) {
                    Koreanturk.this.loadOkRu();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("youtube")) {
                    Koreanturk.this.loadY2Mate();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("foxplay")) {
                    Koreanturk.this.loadFoxPlay();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("feurl.com") || Koreanturk.this.mainUrl.contains("fembed.net") || Koreanturk.this.mainUrl.contains("vcdn.io") || Koreanturk.this.mainUrl.contains("fembed")) {
                    Koreanturk.this.loadFembed();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("yjco.xyz")) {
                    Koreanturk.this.loadYjco();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("fileru.net") || Koreanturk.this.mainUrl.contains("file.ru")) {
                    Koreanturk.this.loadFileRU();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains(".plus")) {
                    Koreanturk.this.loadDiziplus();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("tv8.com.tr")) {
                    Koreanturk.this.loadTv8();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("kanal7")) {
                    Koreanturk.this.loadDailyMotion();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("k2s")) {
                    Koreanturk.this.loadAdult();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("umutdeneme")) {
                    Koreanturk.this.loadY2Mate();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("dizilla") || Koreanturk.this.mainUrl.contains("dizipub")) {
                    Koreanturk.this.loadDizilla();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("dizigom")) {
                    Koreanturk.this.loadDizigom();
                    return;
                }
                if (Koreanturk.this.mainUrl.contains("contentx") || Koreanturk.this.mainUrl.contains("filese") || Koreanturk.this.mainUrl.contains("playru")) {
                    Koreanturk.this.loadContentx();
                } else if (Koreanturk.this.mainUrl.contains("fullhdfilmizlesene")) {
                    Koreanturk.this.loadFullhdfilmizlesene();
                } else if (Koreanturk.this.mainUrl.contains("koreanturk")) {
                    Koreanturk.this.loadKoreanturk();
                }
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4")) {
            this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.2
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                    defaultHttpDataSource.setRequestProperty("Referer", "https://contentx.me/");
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        playVideo();
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.4
            @Override // java.lang.Runnable
            public void run() {
                Koreanturk.this.player.prepare(Koreanturk.this.mergedSource, false, false);
                if (Koreanturk.this.calledContext instanceof VideoView) {
                    ((VideoView) Koreanturk.this.calledContext).setVideoUri(Koreanturk.this.videoUri);
                    try {
                        if (!Koreanturk.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) Koreanturk.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Koreanturk.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.4.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Koreanturk.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.4.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Koreanturk.this.player.seekTo(parseInt);
                                        Koreanturk.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Koreanturk.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Koreanturk.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        Koreanturk.this.player.setPlayWhenReady(true);
                    }
                }
                Koreanturk.this.player.setPlayWhenReady(true);
            }
        });
    }

    private void createAlertWItems(String str) {
        if (((Activity) this.calledContext).isDestroyed()) {
            return;
        }
        showBuffer();
        final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Koreanturk.this.showBuffer();
                Koreanturk koreanturk = Koreanturk.this;
                koreanturk.videoUri = Uri.parse(koreanturk.streamUrls.get(charSequenceArr[r4]));
                Koreanturk.this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Koreanturk.5.1
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("iFrame");
                        defaultHttpDataSource.setRequestProperty("Referer", "https://vidmoly.to/");
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(Koreanturk.this.videoUri));
                Koreanturk.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    public void loadContentx() {
        new Contentx(this.mainUrl, this.calledContext, this.player);
    }

    public void loadKoreanturk() {
        new Koreanturk(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFullhdfilmizlesene() {
        new Fullhdfilmizlesene(this.mainUrl, this.calledContext, this.player);
    }

    public void loadDizigom() {
        new Dizigom(this.mainUrl, this.calledContext, this.player);
    }

    public void loadTv8() {
        new Tv8(this.mainUrl, this.calledContext, this.player);
    }

    public void loadY2Mate() {
        new Y2Mate(this.mainUrl, this.calledContext, this.player);
    }

    public void loadDiziplus() {
        new DiziPlus(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFileRU() {
        new FileRU(this.mainUrl, this.calledContext, this.player);
    }

    public void loadS1cdn() {
        new S1cdn(this.mainUrl, this.calledContext, this.player);
    }

    public void loadCanliTvLive() {
        new CanliTvLive(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFoxPlay() {
        new FoxPlay(this.mainUrl, this.calledContext, this.player);
    }

    public void loadYjco() {
        new Yjco(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFembed() {
        new Fembed(this.mainUrl, this.calledContext, this.player);
    }

    public void loadKarnaval() {
        new KarnavalRadyo(this.mainUrl, this.calledContext, this.player);
    }

    public void loadYabanci() {
        new YabanciDizi(this.mainUrl, this.calledContext, this.player);
    }

    public void loadSuper() {
        new SuperVideo(this.mainUrl, this.calledContext, this.player);
    }

    public void loadUpTo() {
        new UptoStream(this.mainUrl, this.calledContext, this.player);
    }

    public void loadOkRu() {
        new OkRu(this.mainUrl, this.calledContext, this.player);
    }

    public void loadYoutube() {
        new YoutubeWGetter(this.mainUrl, this.calledContext, this.player);
    }

    public void loadVidMoly() {
        new VidMoly(this.mainUrl, this.calledContext, this.player);
    }

    public void loadCloseLoad() {
        new CloseLoad(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFilmModu() {
        new FilmModu(this.mainUrl, this.calledContext, this.player);
    }

    public void loadAdult() {
        new Adult(this.mainUrl, this.calledContext, this.player, false);
    }

    public void loadYesilcam() {
        new Yesilcam(this.mainUrl, this.calledContext, this.player);
    }

    public void loadTrIpTV() {
        new TrIpTv(this.mainUrl, this.calledContext, this.player);
    }

    public void loadImdb() {
        new IMDB(this.mainUrl, this.calledContext, this.player);
    }

    public void loadDizilla() {
        new Dizilla(this.mainUrl, this.calledContext, this.player);
    }

    public void loadDailyMotion() {
        new DailyMotion(this.mainUrl, this.calledContext, this.player);
    }

    public void loadDizipubPlus() {
        new DizipubPlus(this.mainUrl, this.calledContext, this.player);
    }

    public void loadMailRU() {
        new MailRU(this.mainUrl, this.calledContext, this.player);
    }

    public void loadATV() {
        new ATV(this.mainUrl, this.calledContext, this.player);
    }

    public void loadShowTV() {
        new ShowTV(this.mainUrl, this.calledContext, this.player);
    }

    public void loadKanalD() {
        new KanalD(this.mainUrl, this.calledContext, this.player);
    }

    public void loadStarTV() {
        new StarTV(this.mainUrl, this.calledContext, this.player);
    }
}
