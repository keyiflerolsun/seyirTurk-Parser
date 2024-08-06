package com.swenauk.mainmenu.Classes;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SelectionsClass {
    private Map<String, String> selections;

    public SelectionsClass() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.selections = linkedHashMap;
        linkedHashMap.put("İzlemeye Devam Et", "history&id=");
        this.selections.put("Favoriler", "user&p_type=all&id=");
        this.selections.put("Dizi Favoriler", "user&p_type=TV&id=");
        this.selections.put("Film Favoriler", "user&p_type=Movie&id=");
        this.selections.put("IPTV Favoriler", "/back/faviptv.php?id=");
        this.selections.put("Film Türleri", "/kodi/turler.php");
        this.selections.put("Dizi Türleri", "/kodi/turlerdizi.php");
        this.selections.put("Son Çıkanlar(Filmler)", TtmlNode.COMBINE_ALL);
        this.selections.put("En Popüler(Filmler)", "pop");
        this.selections.put("IMDB Top 250(Filmler)", "top");
        this.selections.put("IMDB En Popüler(Filmler)", "fav");
        this.selections.put("Vizyon Filmleri", "vizyon");
        this.selections.put("Sizin İçin Öneriler(Filmler)", "/back/foryou.php");
        this.selections.put("Yeni Bölümü Eklenenler(Diziler)", "all&p_type=TV");
        this.selections.put("Yerli Diziler", "tr_tv&size=200");
        this.selections.put("Yabancı Diziler", "en_tv&size=200");
        this.selections.put("Yerli Filmler", "genre&size=200&genre=Yerli");
        this.selections.put("Türkçe Altyazılı Filmler", "genre&size=200&genre=TRSUB");
        this.selections.put("Türkçe Dublaj Filmler", "genre&size=200&genre=TRDUB");
        this.selections.put("4K Filmler", "genre&size=200&genre=4KK");
        this.selections.put("Aile Filmler", "genre&size=200&genre=Family");
        this.selections.put("Aksiyon Filmleri", "genre&size=200&genre=Action");
        this.selections.put("Almanca Filmler", "genre&size=200&genre=Almanca");
        this.selections.put("Animasyon Filmleri", "genre&size=200&genre=Animation");
        this.selections.put("Belgesel Filmleri", "genre&size=200&genre=Documentary");
        this.selections.put("Bilimkurgu Filmleri", "genre&size=200&genre=Sci-Fi");
        this.selections.put("Biyografi Filmleri", "genre&size=200&genre=Biography");
        this.selections.put("Dram Filmleri", "genre&size=200&genre=Drama");
        this.selections.put("Fantastik Filmleri", "genre&size=200&genre=Fantasy");
        this.selections.put("Gerilim Filmleri", "genre&size=200&genre=Thriller");
        this.selections.put("Gizem Filmleri", "genre&size=200&genre=Mystery");
        this.selections.put("Haber Filmleri", "genre&size=200&genre=News");
        this.selections.put("IMDB Top 250 Filmleri", "genre&size=250&genre=Top250");
        this.selections.put("Kısa Filmler", "genre&size=200&genre=Short");
        this.selections.put("Komedi Filmleri", "genre&size=200&genre=Comedy");
        this.selections.put("Kore Filmleri", "genre&size=200&genre=Korean");
        this.selections.put("Korku Filmleri", "genre&size=200&genre=Horror");
        this.selections.put("Kovboy Filmleri", "genre&size=200&genre=Western");
        this.selections.put("Macera Filmleri", "genre&size=200&genre=Adventure");
        this.selections.put("Müzik Filmleri", "genre&size=200&genre=Music");
        this.selections.put("Müzikal Filmler", "genre&size=200&genre=Musical");
        this.selections.put("Oyun Gösterisi Filmleri", "genre&size=200&genre=Game-Show");
        this.selections.put("Reality Tv Filmleri", "genre&size=200&genre=Reality-TV");
        this.selections.put("Romantik Filmler", "genre&size=200&genre=Romance");
        this.selections.put("Savaş Filmleri", "genre&size=200&genre=War");
        this.selections.put("Spor Filmleri", "genre&size=200&genre=Sport");
        this.selections.put("Suç Filmleri", "genre&size=200&genre=Crime");
        this.selections.put("Suç(Eski) Filmleri", "genre&size=200&genre=Film Noir");
        this.selections.put("Talk Show Filmleri", "genre&size=200&genre=Talk-Show");
        this.selections.put("Tarih Filmleri", "genre&size=200&genre=History");
        this.selections.put("Yeşilçam Filmleri", "genre&size=200&genre=Yesilcam");
        this.selections.put("Türkçe Altyazılı Diziler", "genre&size=200&p_type=TV&genre=TRSUB");
        this.selections.put("Türkçe Dublaj Diziler", "genre&size=200&p_type=TV&genre=TRDUB");
        this.selections.put("4K Diziler", "genre&size=200&p_type=TV&genre=4KK");
        this.selections.put("Aile Dizileri", "genre&size=200&p_type=TV&genre=Family");
        this.selections.put("Aksiyon Dizileri", "genre&size=200&p_type=TV&genre=Action");
        this.selections.put("Almanca Diziler", "genre&size=200&p_type=TV&genre=Almanca");
        this.selections.put("Animasyon Dizileri", "genre&size=200&p_type=TV&genre=Animation");
        this.selections.put("Belgesel Dizileri", "genre&size=200&p_type=TV&genre=Documentary");
        this.selections.put("Bilimkurgu Dizileri", "genre&size=200&p_type=TV&genre=Sci-Fi");
        this.selections.put("Biyografi Dizileri", "genre&size=200&p_type=TV&genre=Biography");
        this.selections.put("Dram Dizileri", "genre&size=200&p_type=TV&genre=Drama");
        this.selections.put("Fantastik Dizileri", "genre&size=200&p_type=TV&genre=Fantasy");
        this.selections.put("Gerilim Dizileri", "genre&size=200&p_type=TV&genre=Thriller");
        this.selections.put("Gizem Dizileri", "genre&size=200&p_type=TV&genre=Mystery");
        this.selections.put("Haber Dizileri", "genre&size=200&p_type=TV&genre=News");
        this.selections.put("Kısa Diziler", "genre&size=200&p_type=TV&genre=Short");
        this.selections.put("Komedi Dizileri", "genre&size=200&p_type=TV&genre=Comedy");
        this.selections.put("Korku Dizileri", "genre&size=200&p_type=TV&genre=Horror");
        this.selections.put("Kore Dizileri", "genre&size=200&p_type=TV&genre=Korean");
        this.selections.put("Kovboy Dizileri", "genre&size=200&p_type=TV&genre=Western");
        this.selections.put("Macera Dizileri", "genre&size=200&p_type=TV&genre=Adventure");
        this.selections.put("Müzik Dizileri", "genre&size=200&p_type=TV&genre=Music");
        this.selections.put("Müzikal Diziler", "genre&size=200&p_type=TV&genre=Musical");
        this.selections.put("Oyun Gösterisi Dizileri", "genre&size=200&p_type=TV&genre=Game-Show");
        this.selections.put("Reality Tv Dizileri", "genre&size=200&p_type=TV&genre=Reality-TV");
        this.selections.put("Romantik Diziler", "genre&size=200&p_type=TV&genre=Romance");
        this.selections.put("Savaş Dizileri", "genre&size=200&p_type=TV&genre=War");
        this.selections.put("Spor Dizileri", "genre&size=200&p_type=TV&genre=Sport");
        this.selections.put("Suç Dizileri", "genre&size=200&p_type=TV&genre=Crime");
        this.selections.put("Suç(Eski) Dizileri", "genre&size=200&p_type=TV&genre=Film Noir");
        this.selections.put("Talk Show Dizileri", "genre&size=200&p_type=TV&genre=Talk-Show");
        this.selections.put("Tarih Dizileri", "genre&size=200&p_type=TV&genre=History");
        this.selections.put("Dizi Stüdyoları", "/kodi/studios_tv.php");
        this.selections.put("Film Stüdyoları", "/kodi/studios.php");
    }

    public Map<String, String> getSelections() {
        return this.selections;
    }
}
