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
import com.swenauk.mainmenu.GetsPack.GetTLC;
import com.swenauk.seyirturk.R;
import java.util.Map;

/* loaded from: classes3.dex */
public class TLC extends Parsers {
    public boolean isAlt;
    public String mainUrl;
    public String oneLinkWonder;
    public String parsed;

    public TLC(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        this.isAlt = true;
        new GetTLC(this).execute(str);
    }

    public void showAlternates(final Map<String, String> map) {
        if (!((Activity) this.calledContext).isDestroyed()) {
            if (map.size() > 1) {
                final CharSequence[] charSequenceArr = (CharSequence[]) map.keySet().toArray(new CharSequence[map.keySet().size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
                builder.setTitle("Kaynak Seçiniz:");
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.TLC.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r4) {
                        TLC.this.mainUrl = (String) map.get(charSequenceArr[r4]);
                        TLC.this.isAlt = false;
                        new GetTLC(TLC.this).execute(TLC.this.mainUrl);
                    }
                });
                this.alert = builder.create();
                this.alert.show();
            } else {
                this.isAlt = false;
                if (this.oneLinkWonder.contains("closeload")) {
                    this.mainUrl = this.oneLinkWonder;
                    resumeParse();
                } else {
                    new GetTLC(this).execute(this.oneLinkWonder);
                }
            }
        }
        this.isAlt = false;
    }

    public void resumeParse() {
        try {
            if (this.streamUrl != null) {
                prepareVideo();
            } else {
                showBuffer();
                showAlert();
            }
        } catch (Exception unused) {
            showBuffer();
            showAlert();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (this.streamUrl.contains(".mp4") && !this.streamUrl.contains(".m3u8")) {
            if (this.streamUrl.contains("yandex.ru")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.TLC.2
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                        if (TLC.this.streamUrl.contains("molyusercontent")) {
                            defaultHttpDataSource.setRequestProperty("Referer", TLC.this.videoUri.getScheme() + "://" + TLC.this.videoUri.getHost());
                        } else {
                            defaultHttpDataSource.setRequestProperty("Referer", TLC.this.videoUri.getScheme() + "://" + TLC.this.videoUri.getHost());
                        }
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Parsers.TLC.3
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
                    String str = TLC.this.videoUri.getScheme() + "://" + TLC.this.videoUri.getHost();
                    if (TLC.this.streamUrl.contains("molystream")) {
                        str = TLC.this.videoUri.getScheme() + "://" + TLC.this.videoUri.getHost();
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.TLC.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                TLC.this.showBuffer();
                System.out.println(TLC.this.streamUrls.get(charSequenceArr[r4]));
                TLC tlc = TLC.this;
                tlc.videoUri = Uri.parse(tlc.streamUrls.get(charSequenceArr[r4]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(TLC.this.calledContext, Util.getUserAgent(TLC.this.calledContext, "iFrame"));
                TLC.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(TLC.this.videoUri));
                TLC.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }

    public void loadContentx() {
        new Contentx(this.mainUrl, this.calledContext, this.player);
    }

    public void loadFilmakinesi() {
        new TLC(this.mainUrl, this.calledContext, this.player);
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
