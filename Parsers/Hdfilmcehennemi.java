package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetHdfilmcehennemi;
import com.swenauk.mainmenu.Sivvat.Videobuzz;
import com.swenauk.seyirturk.R;
import java.util.Map;

/* loaded from: classes3.dex */
public class Hdfilmcehennemi extends Parsers {
    public boolean isAlt;
    public String mainUrl;
    public String parsed;

    public Hdfilmcehennemi(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        this.isAlt = true;
        new GetHdfilmcehennemi(this).execute(str);
    }

    public void showAlternates(final Map<String, String> map) {
        if (!((Activity) this.calledContext).isDestroyed()) {
            if (map.size() > 1) {
                final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
                builder.setTitle("Kaynak Seçiniz:");
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Hdfilmcehennemi.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r4) {
                        Hdfilmcehennemi.this.mainUrl = (String) map.get(charSequenceArr[r4]);
                        Hdfilmcehennemi.this.isAlt = false;
                        new GetHdfilmcehennemi(Hdfilmcehennemi.this).execute(Hdfilmcehennemi.this.mainUrl);
                    }
                });
                this.alert = builder.create();
                this.alert.show();
            } else if (map.size() == 1) {
                this.isAlt = false;
                this.mainUrl = map.get(map.keySet().toArray()[0]);
                resumeParse();
            }
        }
        this.isAlt = false;
    }

    public void resumeParse() {
        if (this.mainUrl.contains("s1cdn.vg")) {
            loadS1cdn();
            return;
        }
        if (this.mainUrl.contains("imdb.com")) {
            loadImdb();
            return;
        }
        if (this.mainUrl.contains("mail.ru")) {
            loadMailRU();
            return;
        }
        if (this.mainUrl.contains("startv")) {
            loadStarTV();
            return;
        }
        if (this.mainUrl.contains("kanald")) {
            loadKanalD();
            return;
        }
        if (this.mainUrl.contains("showtv")) {
            loadShowTV();
            return;
        }
        if (this.mainUrl.contains("atv")) {
            loadATV();
            return;
        }
        if (this.mainUrl.contains("dailymotion")) {
            loadDailyMotion();
            return;
        }
        if (this.mainUrl.contains("streamtape") || this.mainUrl.contains("videobin") || this.mainUrl.contains("gounlimited") || this.mainUrl.contains("pornhub") || this.mainUrl.contains("ashemaletube") || this.mainUrl.contains("xvideos") || this.mainUrl.contains("clipwatching") || this.mainUrl.contains("7dak.com") || this.mainUrl.contains("xnxx.com") || this.mainUrl.contains("unlockxh1.com") || this.mainUrl.contains("xhamster.com")) {
            loadAdult();
            return;
        }
        if (this.mainUrl.contains("chefkoch24")) {
            loadYesilcam();
            return;
        }
        if (this.mainUrl.contains("yabancidizi") || this.mainUrl.contains("puhutv.com") || this.mainUrl.contains("teve2") || this.mainUrl.contains("dizilab")) {
            loadYabanci();
            return;
        }
        if (this.mainUrl.contains("supervideo")) {
            loadSuper();
            return;
        }
        if (this.mainUrl.contains("filmmodu")) {
            loadFilmModu();
            return;
        }
        if (this.mainUrl.contains("uptostream")) {
            loadUpTo();
            return;
        }
        if (this.mainUrl.contains("closeload")) {
            loadCloseLoad();
            return;
        }
        if (this.mainUrl.contains("vidmoly") || this.mainUrl.contains("flmplayer") || this.mainUrl.contains("fasturl")) {
            loadVidMoly();
            return;
        }
        if (this.mainUrl.contains("ok.ru") || this.mainUrl.contains("odnok")) {
            loadOkRu();
            return;
        }
        if (this.mainUrl.contains("youtube")) {
            loadY2Mate();
            return;
        }
        if (this.mainUrl.contains("foxplay")) {
            loadFoxPlay();
            return;
        }
        if (this.mainUrl.contains("feurl.com") || this.mainUrl.contains("fembed.net") || this.mainUrl.contains("vcdn.io") || this.mainUrl.contains("fplay.cf") || this.mainUrl.contains("fembed.com")) {
            loadFembed();
            return;
        }
        if (this.mainUrl.contains("yjco.xyz")) {
            loadYjco();
            return;
        }
        if (this.mainUrl.contains("fileru.net") || this.mainUrl.contains("file.ru")) {
            loadFileRU();
            return;
        }
        if (this.mainUrl.contains(".plus")) {
            loadDiziplus();
            return;
        }
        if (this.mainUrl.contains("tv8.com.tr")) {
            loadTv8();
            return;
        }
        if (this.mainUrl.contains("kanal7")) {
            loadDailyMotion();
            return;
        }
        if (this.mainUrl.contains("k2s")) {
            loadAdult();
            return;
        }
        if (this.mainUrl.contains("umutdeneme")) {
            loadY2Mate();
            return;
        }
        if (this.mainUrl.contains("dizilla") || this.mainUrl.contains("dizipub")) {
            loadDizilla();
            return;
        }
        if (this.mainUrl.contains("dizigom")) {
            loadDizigom();
            return;
        }
        if (this.mainUrl.contains("contentx") || this.mainUrl.contains("filese") || this.mainUrl.contains("playru")) {
            loadContentx();
            return;
        }
        if (this.mainUrl.contains("fullhdfilmizlesene")) {
            loadFullhdfilmizlesene();
            return;
        }
        if (this.mainUrl.contains("koreanturk")) {
            loadKoreanturk();
            return;
        }
        if (this.mainUrl.contains("unutulmazfilmler")) {
            loadUnutulmaz();
        } else if (this.mainUrl.contains("filmakinesi")) {
            loadFilmakinesi();
        } else if (this.mainUrl.contains("vidload.")) {
            new Videobuzz(this.mainUrl, this.calledContext, this.player, null);
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4") && !this.streamUrl.contains(".m3u8")) {
            if (this.streamUrl.contains("yandex.ru")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Hdfilmcehennemi.2
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                        if (Hdfilmcehennemi.this.streamUrl.contains("molyusercontent")) {
                            defaultHttpDataSource.setRequestProperty("Referer", Hdfilmcehennemi.this.videoUri.getScheme() + "://" + Hdfilmcehennemi.this.videoUri.getHost());
                        } else {
                            defaultHttpDataSource.setRequestProperty("Referer", Hdfilmcehennemi.this.videoUri.getScheme() + "://" + Hdfilmcehennemi.this.videoUri.getHost());
                        }
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.Hdfilmcehennemi.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                    String str = Hdfilmcehennemi.this.videoUri.getScheme() + "://" + Hdfilmcehennemi.this.videoUri.getHost();
                    if (Hdfilmcehennemi.this.streamUrl.contains("molystream")) {
                        str = Hdfilmcehennemi.this.videoUri.getScheme() + "://" + Hdfilmcehennemi.this.videoUri.getHost();
                    }
                    defaultHttpDataSource.setRequestProperty("Referer", str);
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Hdfilmcehennemi.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                Hdfilmcehennemi.this.showBuffer();
                System.out.println(Hdfilmcehennemi.this.streamUrls.get(charSequenceArr[r4]));
                Hdfilmcehennemi hdfilmcehennemi = Hdfilmcehennemi.this;
                hdfilmcehennemi.videoUri = Uri.parse(hdfilmcehennemi.streamUrls.get(charSequenceArr[r4]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(Hdfilmcehennemi.this.calledContext, Util.getUserAgent(Hdfilmcehennemi.this.calledContext, "iFrame"));
                Hdfilmcehennemi.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(Hdfilmcehennemi.this.videoUri));
                Hdfilmcehennemi.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    public void loadContentx() {
        new Contentx(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFilmakinesi() {
        new Hdfilmcehennemi(this.mainUrl, this.calledContext, this.player);
    }

    public void loadUnutulmaz() {
        new Unutulmaz(this.mainUrl, this.calledContext, this.player);
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
