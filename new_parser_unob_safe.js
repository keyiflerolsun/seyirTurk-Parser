function parser() {
    if (Core.jsParser.thread.isInterrupted()) {
        return;
    }
    consolelog('--------------------Parser Function Start--------------------');
    if (url(['stream.tvcdn.net'], 1)) {
        url('http://', 'https://');
    }
    consolelog(5, url());
    consolelog(6, g.getLang());
    consolelog(7, sub());
    consolelog(headers());
    try {
        if (headers() == 0) {
            try {
                Core.mainUrlForReferer = url();
            } catch (_0x2f232a) { }
            headers('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36');
            headers('Referer', baseUrl(url()));
            headers('base', 'aHR0cHM6Ly9zZXlpcnR1cmsubmV0L3Jvb3RjaGVjay9hbmRyb2lkLnBocA');
        }
        g.logHeader();
        consolelog('--------------------Parser Function End--------------------');
        if (isPreview() && !url(['https://'], 1)) {
            var _0x20399f = atob(reverseString(fetch(atob(headers('base') + '='))));
            url(_0x20399f + 'sey/back/getImdb.php?id=' + url());
            url(fetch());
            if (url(['|'], 1)) {
                url('https://www.youtube.com/watch?v=' + url().split('|')[1]);
            } else {
                url('https://imdb.com/title/tt' + url());
                headers('Referer', 'https://imdb.com');
            }
        }
        url('?wfilmizle', '');
        url('?atv', '');
        if (url(['atv.com.tr', 'a2tv', 'minikago', 'minikacocuk', 'vavtv.com.tr'], 1) && !url(['.m3u8'], 1)) {
            atv();
        } else {
            if (url(['kanald.com'], 1) && !url(['.m3u8'], 1)) {
                kanald();
            } else {
                if (url(['startv.com.tr', 'ntv.com.tr'], 1) && !url(['.m3u8'], 1) && !url(['womantv'], 1)) {
                    startv();
                } else {
                    if (url(['showtv.com', 'showturk.com', 'showmax.com'], 1) && !url(['.m3u8'], 1)) {
                        showtv();
                    } else {
                        if (url(['nowtv.com'], 1) && !url(['.m3u8'], 1)) {
                            nowtv();
                        } else {
                            if (url(['tv8.com.tr'], 1) && !url(['video-cdn'], 1) && !url(['.m3u8'], 1)) {
                                tv8();
                            } else {
                                if (url(['tlctv', 'dmax'], 1) && !url(['.m3u8'], 1)) {
                                    tlc();
                                } else {
                                    if (url(['teve2'], 1) && !url(['duhnet'], 1) && !url(['.m3u8'], 1)) {
                                        teve2();
                                    } else {
                                        if (url(['tv360'], 1) && !url(['.m3u8'], 1)) {
                                            tv360();
                                        } else {
                                            if (url(['tvem'], 1) && !url(['m3u8'], 1) && !url(['.m3u8'], 1)) {
                                                tvem();
                                            } else {
                                                if (url(['womantv'], 1) && !url(['.m3u8'], 1)) {
                                                    womantv();
                                                } else {
                                                    if (url(['kanal7'], 1) && !url(['.m3u8'], 1)) {
                                                        kanal7();
                                                    } else {
                                                        if (url(['ucankus'], 1) && !url(['.m3u8'], 1)) {
                                                            ucankus();
                                                        } else {
                                                            if (url(['tele1'], 1) && !url(['.m3u8'], 1)) {
                                                                tele1();
                                                            } else {
                                                                if (url(['kanalb'], 1) && !url(['.m3u8'], 1)) {
                                                                    kanalb();
                                                                } else {
                                                                    if (url(['haberturk'], 1) && !url(['.m3u8'], 1)) {
                                                                        haberturk();
                                                                    } else {
                                                                        if (url(['cnnturk'], 1) && !url(['.m3u8'], 1)) {
                                                                            cnnturk();
                                                                        } else {
                                                                            if (url(['ahaber.com', 'anews.com', 'sabah.com.tr', 'aspor.com'], 1) && !url(['.m3u8'], 1)) {
                                                                                ahaber();
                                                                            } else {
                                                                                if (url(['bloomberg'], 1) && !url(['.m3u8'], 1)) {
                                                                                    bloomberg();
                                                                                } else {
                                                                                    if (url(['yirmidort.tv'], 1) && !url(['.m3u8'], 1)) {
                                                                                        yirmidort();
                                                                                    } else {
                                                                                        if (url(['beyaztv.com.tr'], 1) && !url(['.m3u8'], 1)) {
                                                                                            beyaztv();
                                                                                        } else {
                                                                                            if (url(['tvnet.com.tr'], 1) && !url(['.m3u8'], 1)) {
                                                                                                tvnet();
                                                                                            } else {
                                                                                                if (url(['dha.com.tr'], 1) && !url(['.m3u8'], 1)) {
                                                                                                    dha();
                                                                                                } else {
                                                                                                    if (url(['yabantv.com', 'koytv.tv'], 1) && !url(['.m3u8'], 1)) {
                                                                                                        yabantv();
                                                                                                    } else {
                                                                                                        if (url(['tjk.org'], 1) && !url(['.m3u8'], 1)) {
                                                                                                            tjk();
                                                                                                        } else {
                                                                                                            if (url(['dreamturk.com'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                dreamturk();
                                                                                                            } else {
                                                                                                                if (url(['canlitv.digital'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                    canlitvcenter();
                                                                                                                } else {
                                                                                                                    if (url(['istanbuluseyret'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                        istanbuluseyret();
                                                                                                                    } else {
                                                                                                                        if (url(['radyodelisi'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                            radyodelisi();
                                                                                                                        } else {
                                                                                                                            if (url(['pokitv'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                pokitv();
                                                                                                                            } else {
                                                                                                                                if (url(['filmon'], 1) && !url(['m3u8'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                    filmon();
                                                                                                                                } else {
                                                                                                                                    if (url(['cnbce'], 1) && !url(['m3u8'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                        cnbce();
                                                                                                                                    } else {
                                                                                                                                        if (url(['canlitvulusal'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                            canlitvulusal();
                                                                                                                                        } else {
                                                                                                                                            if (url(['canlitv.ws'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                canlitvws();
                                                                                                                                            } else {
                                                                                                                                                if (url(['yoltv.com'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                    yoltv();
                                                                                                                                                } else {
                                                                                                                                                    if (url(['canlitvvolo'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                        canlitvvolo();
                                                                                                                                                    } else {
                                                                                                                                                        if (url(['149.255.152.218', 'aspx'], 1)) {
                                                                                                                                                            myvideoaz();
                                                                                                                                                        } else {
                                                                                                                                                            if (url(['parsatv'], 1)) {
                                                                                                                                                                parsatv();
                                                                                                                                                            } else {
                                                                                                                                                                if (url(['radyohome.com'], 1)) {
                                                                                                                                                                    radyohome();
                                                                                                                                                                } else {
                                                                                                                                                                    if (url(['streamtheworld.com'], 1) && !url(['aac'], 1)) {
                                                                                                                                                                        streamtheworld();
                                                                                                                                                                    } else {
                                                                                                                                                                        if (url(['canliradyolar'], 1) && !url(['mp3'], 1)) {
                                                                                                                                                                            canliradyolar();
                                                                                                                                                                        } else {
                                                                                                                                                                            if (url(['onlineradiobox'], 1) && !url(['mp3'], 1)) {
                                                                                                                                                                                onlineradiobox();
                                                                                                                                                                            } else {
                                                                                                                                                                                if (url(['filmmakinesi'], 1)) {
                                                                                                                                                                                    filmmakinesi();
                                                                                                                                                                                } else {
                                                                                                                                                                                    if (url(['asyafilmizlesene'], 1)) {
                                                                                                                                                                                        asyafilmizlesene();
                                                                                                                                                                                    } else {
                                                                                                                                                                                        if (url(['filmmodu'], 1)) {
                                                                                                                                                                                            filmmodu();
                                                                                                                                                                                        } else {
                                                                                                                                                                                            if (url(['setfilmizle', 'hdfilmcehennemiboncuk45'], 1)) {
                                                                                                                                                                                                setfilmizle();
                                                                                                                                                                                            } else {
                                                                                                                                                                                                if (url(['filmekseni'], 1)) {
                                                                                                                                                                                                    filmekseni();
                                                                                                                                                                                                } else {
                                                                                                                                                                                                    if (url(['sinefil'], 1)) {
                                                                                                                                                                                                        sinefil();
                                                                                                                                                                                                    } else {
                                                                                                                                                                                                        if (url(['kultfilmler'], 1)) {
                                                                                                                                                                                                            kultfilmler();
                                                                                                                                                                                                        } else {
                                                                                                                                                                                                            if (url(['filmkovasi'], 1)) {
                                                                                                                                                                                                                filmkovasi();
                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                if (url(['filmatek'], 1)) {
                                                                                                                                                                                                                    filmatek();
                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                    if (url(['movie4k'], 1)) {
                                                                                                                                                                                                                        movie4k();
                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                        if (url(['/s.to', 'aniworld.to'], 1)) {
                                                                                                                                                                                                                            sto();
                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                            if (url(['siyahfilmizle'], 1)) {
                                                                                                                                                                                                                                siyahfilmizle();
                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                if (url(['sinemafilmizle'], 1)) {
                                                                                                                                                                                                                                    sinemafilmizle();
                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                    if (url(['123movies'], 1)) {
                                                                                                                                                                                                                                        movies123();
                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                        if (url(['wikiflix'], 1)) {
                                                                                                                                                                                                                                            wikiflix();
                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                            if (url(['hdtoday'], 1)) {
                                                                                                                                                                                                                                                hdtoday();
                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                if (url(['wfilmizle'], 1)) {
                                                                                                                                                                                                                                                    wfilmizle();
                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                    if (url(['sinefy'], 1)) {
                                                                                                                                                                                                                                                        sinefy();
                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                        if (url(['fullhdfilmizlesene'], 1)) {
                                                                                                                                                                                                                                                            fullhdfilmizlesene();
                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                            if (url(['4kfilmizle'], 1) && !url(['filmizlemek'], 1)) {
                                                                                                                                                                                                                                                                filmizle4k();
                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                if (url(['filmizlesene'], 1)) {
                                                                                                                                                                                                                                                                    filmizlesene();
                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                    if (url(['jetfilm'], 1)) {
                                                                                                                                                                                                                                                                        jetfilm();
                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                        if (url(['hdfilmizle'], 1)) {
                                                                                                                                                                                                                                                                            hdfilmcehennemi3();
                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                            if (url(['hdfilmcehennemi'], 1)) {
                                                                                                                                                                                                                                                                                if (url(['syrtrk'], 1)) {
                                                                                                                                                                                                                                                                                    hdfilmcehennemi2();
                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                    if (url(['boncuk'], 1)) {
                                                                                                                                                                                                                                                                                        hdfilmcehennemi3();
                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                        hdfilmcehennemi();
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                if (url(['realfilmizle'], 1)) {
                                                                                                                                                                                                                                                                                    realfilmizle();
                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                    if (url(['filmmax'], 1)) {
                                                                                                                                                                                                                                                                                        filmmax();
                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                        if (url(['unutulmazfilmler'], 1)) {
                                                                                                                                                                                                                                                                                            unutulmaz();
                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                            if (url(['sinemadelisi'], 1)) {
                                                                                                                                                                                                                                                                                                sinemadelisi();
                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                if (url(['sinema.cx'], 1) && !url(['/hls'], 1)) {
                                                                                                                                                                                                                                                                                                    sinemacx();
                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                    if (url(['superfilmgeldi', 'hdsinemax'], 1)) {
                                                                                                                                                                                                                                                                                                        superfilmgeldi();
                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                        if (url(['vumoox'], 1)) {
                                                                                                                                                                                                                                                                                                            vumoox();
                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                            if (url(['yesmovies'], 1)) {
                                                                                                                                                                                                                                                                                                                yesmovies();
                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                if (url(['watchomovies'], 1)) {
                                                                                                                                                                                                                                                                                                                    watchomovies();
                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                    if (url(['123chill'], 1)) {
                                                                                                                                                                                                                                                                                                                        chill123();
                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                        if (url(['webteizle'], 1)) {
                                                                                                                                                                                                                                                                                                                            webteizle();
                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                            if (url(['ugurfilm'], 1)) {
                                                                                                                                                                                                                                                                                                                                ugurfilm();
                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                if (url(['xcine'], 1)) {
                                                                                                                                                                                                                                                                                                                                    xcine();
                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                    if (url(['filmcidayi'], 1)) {
                                                                                                                                                                                                                                                                                                                                        filmcidayi();
                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                        if (url(['filmizlemek'], 1)) {
                                                                                                                                                                                                                                                                                                                                            filmizlemek();
                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                            if (url(['fullhdfilm'], 1)) {
                                                                                                                                                                                                                                                                                                                                                fullhdfilm();
                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                if (url(['dizilla'], 1)) {
                                                                                                                                                                                                                                                                                                                                                    dizilla();
                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                    if (url(['dizimom'], 1)) {
                                                                                                                                                                                                                                                                                                                                                        dizimom();
                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                        if (url(['dizimia'], 1)) {
                                                                                                                                                                                                                                                                                                                                                            dizimia();
                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                            if (url(['diziyou'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                diziyou();
                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                if (url(['dizilab'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                    dizilab();
                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                    if (url(['dizitime'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                        dizitime();
                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                        if (url(['dizibox'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                            dizibox();
                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                            if (url(['yabancidizi'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                yabancidizi();
                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                if (url(['dizipub', 'dizist'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                    dizipub();
                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                    if (url(['dizipal'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                        dizipal();
                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                        if (url(['dizirix'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                            dizirix();
                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                            if (url(['onlinedizi'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                onlinedizi();
                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                if (url(['yabanci-dizi'], 1) || url(['roketdizi'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                    yabanci_dizi();
                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['diziyo'], 1) && !url(['diziyou'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                        diziyo();
                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['diziroll'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                            diziroll();
                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['sezonlukdizi'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                sezonlukdizi();
                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['diziwatch'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                    diziwatch();
                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['diziplus'], 1) || url(['dizimag'], 1) || url(['diziberlin'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                        diziplus();
                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['dizifin'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                            dizifin();
                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['dizikral'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                dizikral();
                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['dizifix'], 1) || url(['dizilib'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                    dizifix();
                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['koreanturk'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                        koreanturk();
                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['dizigom'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                            dizigom();
                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['ddizi'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                ddizi();
                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['dizimat'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                    dizimat();
                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['canlidizi'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                        canlidizi();
                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['streamruby'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            streamruby();
                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['filemoon', 'upstream'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                filemoon();
                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['sibnet'], 1) && !url(['m3u8'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    sibnet();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['govids'], 1) && !url(['redirect'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        govids();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['youtube'], 1) && !url(['googlevideo.com/'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            youtube();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['vidmoly'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                vidmoly();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['contentx', 'playru', 'filese', 'hotlinger', '//four', 'pichive'], 1) && !url(['m.php'], 0)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    contentx();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['ok.ru', 'odnok'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        okru();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['vidmoxy', 'vidrame'], 1) && !url(['ok.vidmoxy'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            vidmoxy();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['supervideo', 'dropload'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                supervideo();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['mixdrop'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    mixdrop();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['streamtape'], 1) && !url(['get_video'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        streamtape();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['voe'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            voe();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['vectorx', 'ply.jetv.xyz'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                vectorx();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['videoseyred.in'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    videoseyred();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['mail.ru'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        mailru();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['vimeo'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            vimeo();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['streamoupload'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                streamoupload();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['vk.com'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    vkcom();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['dood.'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        dood();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['vidload.'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            vidload();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['puhutv.com'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                puhutv();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['dailymotion'], 1) && !url(['m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    dailymotion();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['imdb'], 1) && !url(['video.media'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        imdb();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['trstx', 'sobreatsesuyp', 'chaintwistasherictive'], 1) && !url(['.txt'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            trstx();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['vudeo'], 1) && !url(['.mp4'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                vudeo();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['oneupload'], 1) && !url(['.mp4'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    oneupload();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['vidlop'], 1) && !url(['.mp4'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        vidlop();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['streamplayer'], 1) && !url(['.mp4'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            streamplayer();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['chaturbate'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                chaturbate();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['ashemaletube'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    ashemaletube();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['pornhub'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        pornhub();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['xvideos'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            xvideos();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['thehun'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                thehun();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['youporn'], 1) && !url(['/hls/'], 1) && !url(['/mp4/'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    youporn();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['xnxx'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        xnxx();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['xhamster'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            xhamster();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (url(['7dak'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                dak7();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (url(['hdabla'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    hdabla();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (url(['allclassic'], 1) && !url(['.mp4'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        allclassic();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (url(['.aac', 'radyotvonline'], 1) && !url(['streamtheworld'], 1) && !url(['.m3u8'], 1)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            g.setMediaType('mp3');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            var _0x20399f = Core.getUrlContentType() + '';
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            consolelog('Content Type: ' + _0x20399f);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            consolelog('Media Type: ' + g.getMediaType() + '');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (_0x20399f == 'video/mp2t' || _0x20399f == 'video/x-matroska') {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                g.setMediaType('mp4');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } catch (_0x21f6ae) { }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (isPreview()) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            g.setReady(true);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            url(fixUrl(url()));
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            Core.playVideoJS();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } catch (_0x2a1855) {
        var _0x146e09 = _0x2a1855.message;
        consolelog('Error: ', _0x146e09);
        errorEncode = encodeURIComponent(_0x146e09);
        try {
            var _0x5e9a5e = encodeURIComponent('NEW PARSER: ' + Core.mainUrlForReferer + ', ' + g.getLang());
            fetch('http://beytepe.tk/sey/back/v2/parser/notifier.php?error=' + errorEncode + '&details=' + _0x5e9a5e);
        } catch (_0x5ec1df) {
            var _0x5e9a5e = encodeURIComponent('NEW PARSER: ' + url() + ', ' + g.getLang());
            fetch('http://beytepe.tk/sey/back/v2/parser/notifier.php?error=' + errorEncode + '&details=' + _0x5e9a5e);
        }
        if (!isPreview()) {
            if (_0x146e09.includes('Webview')) {
                Core.showOnError("SeÃ§tiÄŸiniz kaynak/alt kaynak cihazÄ±nÄ±z tarafÄ±ndan desteklenmemektedir.");
            } else {
                if (_0x146e09.includes("kapalÄ± olduÄŸundan")) {
                    Core.showOnError(_0x146e09 + '');
                } else {
                    Core.showOnError();
                }
            }
        } else {
            url('empty');
            g.setReady(true);
        }
        return _0x146e09;
    }
}
function ahaber() {
    if (url(['apara'], 0)) {
        url('https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Faparahd%2Faparahd.m3u8');
    } else {
        if (url(['ahaber'], 0)) {
            url('https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=https%3A%2F%2Ftrkvz-live.ercdn.net%2Fahaberhd%2Fahaberhd.m3u8');
        } else {
            if (url(['anews'], 0)) {
                url('https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Fanewshd%2Fanewshd.m3u8');
            } else {
                if (url(['aspor'], 0)) {
                    url('https://securevideotoken.tmgrup.com.tr/webtv/secure?851521&url=http%3A%2F%2Ftrkvz-live.ercdn.net%2Fasporhd%2Fasporhd.m3u8');
                }
            }
        }
    }
    url(fetch().match(/"Url":"(.*?)"/)[1]);
    parser();
}
function allclassic() {
    var _0x4e8df0 = fetch().match(/function\/0\/(.*?)',/)[1];
    var _0x40fdc6 = _0x4e8df0.split('/')[5].substring(0, 32);
    var _0x59ef0a = '76582147925414364366255444386504';
    h = _0x40fdc6.split('');
    _0x59ef0a = _0x59ef0a.split('');
    for (let _0x14168a = h.length - 1; _0x14168a >= 0; _0x14168a--) {
        let _0x2508bc = _0x14168a;
        for (let _0x41e048 = _0x14168a; _0x41e048 < _0x59ef0a.length; _0x41e048++) {
            _0x2508bc += parseInt(_0x59ef0a[_0x41e048]);
        }
        while (_0x2508bc >= h.length) {
            _0x2508bc -= h.length;
        }
        let _0x5c6793 = '';
        for (let _0x5b86db = 0; _0x5b86db < h.length; _0x5b86db++) {
            if (_0x5b86db === _0x14168a) {
                _0x5c6793 += h[_0x2508bc];
            } else {
                if (_0x5b86db === _0x2508bc) {
                    _0x5c6793 += h[_0x14168a];
                } else {
                    _0x5c6793 += h[_0x5b86db];
                }
            }
        }
        h = _0x5c6793.split('');
    }
    h = h.join('');
    url(_0x4e8df0.replace(_0x40fdc6, h));
    parser();
}
function ashemaletube() {
    var _0x57097c = matchAll(fetch(), /"src":"(.*?)","desc":"(.*?)",/g);
    var _0x46a0be = {};
    for (let _0x6d6389 = 0; _0x6d6389 < _0x57097c.length; _0x6d6389++) {
        _0x46a0be[_0x57097c[_0x6d6389][2]] = _0x57097c[_0x6d6389][1];
    }
    var _0x374867 = ['360p', '480p', '720p', '1080p', 'Alone'];
    var _0x56c934 = -1;
    var _0x84bac6 = -1;
    for (let _0x4a4ef6 = 0; _0x4a4ef6 < Object.keys(_0x46a0be).length; _0x4a4ef6++) {
        try {
            var _0x5dc679 = Object.keys(_0x46a0be)[_0x4a4ef6];
            var _0x22e27d = _0x374867.indexOf(_0x5dc679);
            if (_0x56c934 < _0x22e27d) {
                _0x56c934 = _0x22e27d;
                _0x84bac6 = _0x5dc679;
            }
        } catch (_0x1fd4b0) { }
    }
    if (_0x56c934 != -1) {
        url(fixUrl(_0x46a0be[_0x84bac6].replace(/\\/g, '')));
    }
    parser();
}
function asyafilmizlesene() {
    initialUrl = url() + '';
    var _0x36db0b = fetch();
    _0x36db0b = _0x36db0b.match(/href="([^"]+)"\s*class="amy-redirect-watch-online".*?Film\s*izle/)[1];
    _0x36db0b = fetch(_0x36db0b);
    streamUrls = {};
    try {
        var _0x440058 = matchAll(_0x36db0b, /data-source="(.*?)">/g);
        consolelog(_0x440058);
        for (let _0x33ffc2 = 0; _0x33ffc2 < _0x440058.length; _0x33ffc2++) {
            try {
                var _0x14d665 = _0x440058[_0x33ffc2][1];
                var _0x11dafd = _0x14d665.split('//')[1].split('.')[0];
                if (_0x11dafd != 'listeamed' && _0x11dafd != 'csst') {
                    streamUrls[_0x11dafd] = _0x14d665;
                }
            } catch (_0x2ed15f) { }
        }
    } catch (_0x5715ad) {
        error(_0x5715ad.message);
    }
    if (!(initialUrl + '').includes('#') && JSON.stringify(streamUrls) != '{}') {
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    } else {
        url((initialUrl + '').split('#')[1]);
        parser();
    }
}
function atv() {
    if (url(['canli-yayin'], 0)) {
        var _0x1e8bb8 = 'atv';
        if (url(['minikacocuk'], 0)) {
            _0x1e8bb8 = 'minikago_cocuk';
        } else {
            if (url(['minikago'], 0)) {
                _0x1e8bb8 = 'minikago';
            } else {
                if (url(['a2tv'], 0)) {
                    _0x1e8bb8 = 'a2tv';
                    url('https://www.atv.com.tr/a2tv/canli-yayin');
                }
            }
        }
        var _0x49a483 = fetch();
        url(_0x49a483.match(/"(https:\/\/i.tmgrup.com.tr\/videojs\/js.*?)"/)[1]);
        var _0x5379d3 = "\"(https://trkvz.daioncdn.net/" + _0x1e8bb8 + '/' + _0x1e8bb8 + '.m3u8.*?)' + "\"";
        var _0x2d4084 = _0x5379d3 + ".*?:\\s*\"(.*?)\"";
        var _0x310712 = new RegExp(_0x2d4084);
        var _0xed0390 = '';
        var _0x5432d3 = '';
        try {
            _0x49a483 = fetch().match(_0x310712);
            if (_0x1e8bb8 == 'a2tv') {
                _0x49a483 = '';
            }
            _0xed0390 = _0x49a483[2];
            _0x5432d3 = _0x49a483[1];
        } catch (_0x2a413d) {
            _0x49a483 = '';
        }
        if (_0x49a483 == '') {
            _0x5379d3 = "\"(https://trkvz.daioncdn.net/" + _0x1e8bb8 + '/' + _0x1e8bb8 + '.m3u8.*?)' + "\";";
            _0x310712 = new RegExp(_0x5379d3);
            _0x49a483 = fetch().match(_0x310712);
            _0x5432d3 = _0x49a483[1];
            _0xed0390 = '';
        }
        url('https://securevideotoken.tmgrup.com.tr/webtv/secure?url=' + encodeURIComponent(_0x5432d3 + _0xed0390));
        _0x49a483 = fetch();
        url(_0x49a483.match(/.*?Url":"(htt.*?)"/)[1]);
    } else {
        try {
            var _0x49a483 = fetch();
            var _0x2a79c7 = (_0x49a483 + '').match(/videoid="(.*?)" data-vp="tmdvpcontainer" data-websiteid="(.*?)"/);
            _0x2a79c7 = 'https://videojs.tmgrup.com.tr/getvideo/' + _0x2a79c7[2] + '/' + _0x2a79c7[1];
            _0x49a483 = fetch(_0x2a79c7);
            var _0x4e01fb = _0x49a483.match("\"VideoUrl\":\"([^\"]+)\".*?\"VideoSmilUrl\":\"([^\"]+)\"");
            var _0xed0390 = encodeURIComponent(_0x4e01fb[1]);
            var _0x5432d3 = encodeURIComponent(_0x4e01fb[2]);
            url('https://securevideotoken.tmgrup.com.tr/webtv/secure?url=' + _0xed0390 + '&url2=' + _0x5432d3);
            _0x2a79c7 = fetch();
            json = JSON.parse(_0x2a79c7);
            if (json.hasOwnProperty('Url')) {
                url(json.Url);
            } else {
                if (json.hasOwnProperty('Alone')) {
                    url(json.Alone);
                } else {
                    if (json.hasOwnProperty('1080p')) {
                        url(json['1080p']);
                    } else {
                        if (json.hasOwnProperty('720p')) {
                            url(json['720p']);
                        } else {
                            if (json.hasOwnProperty('480p')) {
                                url(json['480p']);
                            }
                        }
                    }
                }
            }
        } catch (_0x13dc82) {
            error(_0x13dc82.message);
        }
    }
    parser();
}
function beyaztv() {
    url(fetch().match(/videoUrl\s*=\s*"(.*?)"/)[1]);
    parser();
}
function bloomberg() {
    url(fetch().match(/ var\s*videoUrl\s*=\s*"(.*?&app.*?)"/)[1]);
    parser();
}
function canlidizi() {
    var _0x223f46 = fetch();
    _0x223f46 = _0x223f46.match(/data-wpfc-original-src="(.*?(?:fireplayer|betaplayer).*?)"/)[1];
    if (_0x223f46.includes('fireplayer')) {
        var _0x29635e = _0x223f46.split('/');
        _0x29635e = _0x29635e[_0x29635e.length - 1];
        var _0x1b6c06 = _0x223f46 + '?do=getVideo';
        var _0x34ca09 = 'hash=' + _0x29635e + '&r=' + baseUrl(url()) + '&s=';
        headers('Content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
        headers('x-requested-with', 'XMLHttpRequest');
        _0x223f46 = fetchPost(_0x34ca09, _0x1b6c06);
        _0x223f46 = JSON.parse(_0x223f46);
        url(_0x223f46.videoSources[0].file);
    } else {
        if (_0x223f46.includes('betaplayer')) {
            var _0x3169b4 = _0x223f46;
            _0x223f46 = fetch(_0x223f46);
            var _0x1b6c06 = _0x223f46.match(/file\s*:\s*"(.*?betaplayer.*?)"/)[1];
            headers('Referer', _0x3169b4);
            headers('Accept', '*/*');
            url(_0x1b6c06);
            try {
                var _0x4d4f4e = _0x223f46.match(/tracks\s*:\s*(\[.*?\]),/)[1];
                _0x4d4f4e = _0x4d4f4e.split('}');
                for (let _0x101881 = 0; _0x101881 < _0x4d4f4e.length; _0x101881++) {
                    if (_0x4d4f4e[_0x101881].includes('tur') && _0x4d4f4e[_0x101881].includes('.vtt') && !_0x4d4f4e[_0x101881].includes('forced')) {
                        sub(baseUrl(url()) + '' + _0x4d4f4e[_0x101881].match(/file":\s*"(.*?)"/)[1]);
                    }
                }
            } catch (_0xc8199a) { }
        }
    }
    parser();
}
function canliradyolar() {
    var _0x55e1d8 = fetch();
    var _0x60b048 = _0x55e1d8.match(/iframe\s*src="(.*?)"\s*name=/)[1];
    _0x55e1d8 = fetch(_0x60b048);
    _0x60b048 = _0x55e1d8.match(/source\s*src="(.*?)"/)[1];
    url(_0x60b048);
    g.setMediaType('mp3');
    parser();
}
function canlitvcenter() {
    var _0x5356ed = fetch();
    _0x5356ed = _0x5356ed.match(/iframe-player.*?src=[\"'](.*?)[\"']/)[1];
    if (_0x5356ed.includes('urlcik=')) {
        try {
            _0x5356ed = _0x5356ed.match(/urlcik=(.*?)&/)[1];
            _0x5356ed = atob(_0x5356ed);
        } catch (_0x264774) { }
    }
    _0x5356ed = fetch(_0x5356ed);
    if (_0x5356ed.includes('DM.player')) {
        url('https://dailymotion.com/video/' + _0x5356ed.match(/video\\s*:\\s*'(.*?)'/)[1]);
    } else {
        if (_0x5356ed.includes('youtube.com/embed')) {
            url(_0x5356ed.match(/iframe.*?src=[\"'](.*?)(?:\?|[\"'])/)[1]);
        } else {
            if (_0x5356ed.includes('atob')) {
                url(atob(_0x5356ed.match(/atob\("(.*?)"/)[1]));
            } else {
                if (_0x5356ed.includes('eval(function') || _0x5356ed.includes("<script src=\"")) {
                    try {
                        url(_0x5356ed.match(/script\s*src=\\"(.*?)\\">/)[1]);
                    } catch (_0x48af45) {
                        url('');
                    }
                    if (url() == '') {
                        url(_0x5356ed.match(/<script\s*src=\s*"(https:\/\/play.cdn-canlitv.*?)"/)[1]);
                    }
                    _0x5356ed = fetch();
                    var _0x52b2b8 = _0x5356ed.match(/verianahtar\s*=\s*"(.*?)"/)[1];
                    var _0x4fc532 = 'https:' + _0x5356ed.match(/yayincomtr4\s*=\s*"(.*?)"/)[1];
                    url(_0x4fc532 + _0x52b2b8);
                }
            }
        }
    }
    parser();
}
function canlitvulusal() {
    url('https://canlitvulusal.com/', 'https://canlitvulusal3.xyz/live/');
    url('tv-show/', '');
    url('-canli-yayin/', '/index.m3u8');
    url('-', '');
    try {
        fetch();
    } catch (_0x275a15) {
        url('https://canlitvulusal.com/', 'https://canlitvulusal3.xyz/live/');
        url('tv-show/', '');
        url('-canli-yayin/', '/index.m3u8');
        url('-', '');
    }
    g.deleteHeader('Referer');
    parser();
}
function canlitvvolo() {
    headers('Referer', url());
    headers('User-Agent', 'Cloudflare');
    var _0xd960db = fetch().match(/const data = (.*?);/)[1].replace(/\s*/g, '').replace('{', '').replace('}', '').split(',');
    var _0x1f25a5 = {};
    for (var _0x5bf5f5 = 0; _0x5bf5f5 < _0xd960db.length; _0x5bf5f5++) {
        var _0x5c9773 = _0xd960db[_0x5bf5f5].split(':');
        _0x1f25a5[_0x5c9773[0]] = _0x5c9773[1].replace(/'/g, '');
    }
    headers('Referer', url());
    headers('X-Requested-With', 'XMLHttpRequest');
    headers('Content-Type', 'application/json');
    url('https://tv.canlitvvolo.com/Tv/TVShow');
    _0xd960db = fetchPost(JSON.stringify(_0x1f25a5));
    var _0x31643b = JSON.parse(_0xd960db);
    try {
        url(_0x31643b.result.playerBodyEnd.match(/file:'(.*?)'/)[1]);
    } catch (_0x5024d5) {
        url(_0x31643b.result.playerBodyEnd.match(/contentURL"\s*:\s*(.*?)"/)[1]);
    }
    parser();
}
function canlitvws() {
    var _0x31093f = fetch().match(/index.php\?id=(\d+)"/)[1];
    var _0x475c25 = fetch('https://www.canlitv.ws/embed/?id=' + _0x31093f).match(/file:\s*"(.*?)"/)[1];
    url(_0x475c25);
    parser();
}
function chaturbate() {
    var _0x2545cb = fetch().match(/hls_source\\u0022:\s*\\u0022(.*?)\\u0022/)[1].replace(/\\u002D/g, '-');
    url(_0x2545cb);
    parser();
}
function chill123() {
    headers('Referer', 'https://123chill.online/');
    data = fetch();
    try {
        var _0x34c46e = (data + '').match(/iframe\s*id="myFrame"\s*src="(.*?)"/)[1];
        data = fetch(_0x34c46e);
        var _0x2d3288 = (data + '').match(/<iframe.*?src="(.*?)"/)[1];
        _0x2d3288 = fixUrl(_0x2d3288);
        var _0xb4f6a = baseUrl(_0x2d3288);
        data = fetch(_0x2d3288);
        _0x2d3288 = _0xb4f6a + (data + '').match(/src:.*?'(.*?)'/)[1];
        var _0xf47fb4 = '';
        while (!_0xf47fb4.startsWith('eqq') && true) {
            data = fetch(_0x2d3288);
            var _0xf47fb4 = (data + '').match(/style="display:none;">\s*(.*?)<\/div>/)[1];
            consolelog(_0xf47fb4);
        }
        if (!_0xf47fb4.startsWith('eqq')) { }
        url(replaceCustomChars(_0xf47fb4));
    } catch (_0x5ed47d) {
        error(_0x5ed47d.message);
    }
    parser();
}
function cnbce() {
    try {
        var _0x59c4e2 = fetchPost('', 'https://www.cnbce.com/api/live-stream/source');
        var _0x1fea04 = JSON.parse(_0x59c4e2);
        url(_0x1fea04.source);
    } catch (_0xc9c4fa) {
        error(_0xc9c4fa.message);
    }
    parser('', 2, '', false);
}
function cnnturk() {
    var _0x3f3c68 = JSON.parse(fetch('https://www.cnnturk.com/action/media/' + fetch().match(/data-id="(.*?)"/)[1] + '?ad_type=embed-player'));
    url(_0x3f3c68.Media.Link.ServiceUrl + '' + _0x3f3c68.Media.Link.SecurePath);
    parser();
}
function contentx() {
    if (!url(['filese'], 0)) {
        url(fixUrl(url()));
        var _0x21cd38 = fetch();
        try {
            var _0x2e43ba = matchAll(_0x21cd38, /"file"\s*:\s*"(.*?)"\s*,\s*"label"\s*:\s*"(.*?)"/g);
            for (let _0x5cdced = 0; _0x5cdced < _0x2e43ba.length; _0x5cdced++) {
                if ((_0x2e43ba[_0x5cdced][2].toLowerCase().includes('rk') || _0x2e43ba[_0x5cdced][2].toLowerCase().includes('turkish')) && !_0x2e43ba[_0x5cdced][1].includes('forced')) {
                    sub(_0x2e43ba[_0x5cdced][1].replace(/\\\//g, '/'));
                }
            }
        } catch (_0x4c443a) {
            error(_0x4c443a.message);
        }
        _0x21cd38 = _0x21cd38.match(/window.openPlayer\((.*?)\)/)[1].split(',')[0].replace(/'/g, '');
        _0x21cd38 = fetch(baseUrl(url()) + '/source2.php?v=' + _0x21cd38);
        var _0x2d0c33 = JSON.parse(_0x21cd38).playlist;
        url('');
        for (var _0x2bf93c = 0; _0x2bf93c < _0x2d0c33.length; _0x2bf93c++) {
            if (g.getLang() == 1 && (_0x2d0c33[_0x2bf93c].sources[0].title.includes("TÃ¼rkÃ§e Dublaj") || _0x2d0c33[_0x2bf93c].sources[0].title.includes('Orijinal'))) {
                url(_0x2d0c33[_0x2bf93c].sources[0].file);
            } else {
                if (g.getLang() == 0 && (_0x2d0c33[_0x2bf93c].sources[0].title.includes("TÃ¼rkÃ§e AltyazÄ±") || _0x2d0c33[_0x2bf93c].sources[0].title.includes('Orijinal'))) {
                    url(_0x2d0c33[_0x2bf93c].sources[0].file);
                }
            }
        }
        if (url() == '') {
            for (var _0x2bf93c = 0; _0x2bf93c < _0x2d0c33.length; _0x2bf93c++) {
                url(_0x2d0c33[_0x2bf93c].sources[0].file);
            }
        }
        headers('Referer', baseUrl(url()));
        parser();
    }
}
function dailymotion() {
    url('embed/', '');
    url('video', 'player/metadata/video');
    var _0x1a40b9 = fetch();
    var _0x4ed7a1 = JSON.parse(_0x1a40b9);
    url(_0x4ed7a1.qualities.auto[0].url);
    g.deleteHeader('Referer');
    parser();
}
function dak7() {
    var _0x56c248 = matchAll(fetch(), /source\s*src="(.*?)".*?size="(.*?)"/g);
    var _0x3aa216 = ['240', '360', '480', '720', '1080', 'Alone'];
    var _0x3bbe2f = -1;
    var _0xfabce1 = -1;
    for (let _0xf8413 = 0; _0xf8413 < _0x56c248.length; _0xf8413++) {
        try {
            var _0xa0ddad = _0x56c248[_0xf8413][2];
            var _0x5a33f3 = _0x3aa216.indexOf(_0xa0ddad);
            consolelog(_0x5a33f3);
            if (_0x3bbe2f < _0x5a33f3) {
                _0x3bbe2f = _0x5a33f3;
                _0xfabce1 = _0xf8413;
            }
        } catch (_0x15fda1) { }
    }
    if (_0x3bbe2f != -1) {
        url(fixUrl(_0x56c248[_0xfabce1][1].replace(/\\/g, '')));
    }
    parser();
}
function ddizi() {
    headers('Accept', '*/*');
    var _0x4f47a4 = fetch();
    var _0x47cc92 = _0x4f47a4.match(/iframe.*?src="(.*?)"/)[1];
    if (!_0x47cc92.startsWith('https')) {
        _0x47cc92 = baseUrl(url()) + '/' + _0x47cc92;
        headers('Referer', _0x47cc92);
        g.deleteHeader('Origin');
        _0x4f47a4 = fetch(_0x47cc92);
        url(_0x4f47a4.match(/file:"(.*?)"/)[1].replace(/\\/g, ''));
    } else {
        url(_0x47cc92);
    }
    parser();
}
function dha() {
    url(fetch().match(/src:\s*'(.*?)'/)[1].replace(/\\\//g, '/'));
    parser();
}
function dizibox() {
    var _0x3d32e2 = url();
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x26519 = '';
    try {
        _0x26519 = fetch();
        _0x26519 = _0x26519.match(/woca-linkpages-dd selectBox(.*?)\/select/);
    } catch (_0x44fa26) {
        _0x26519 = '';
    }
    var _0x9c1fec = {
        _0x137b00: _0x39fafe
    };
    try {
        var _0xdf8e4b = matchAll(_0x26519, /(?:value=["']|href=["'])(.*?)["'].*?>(.*?)</g);
        for (let _0x274338 = 0; _0x274338 < _0xdf8e4b.length; _0x274338++) {
            var _0x39fafe = _0xdf8e4b[_0x274338][1];
            var _0x137b00 = _0xdf8e4b[_0x274338][2];
            if (_0x137b00 != '' && _0x137b00 != 'King' && _0x137b00 != 'Play') {
                ;
            }
        }
    } catch (_0x20d176) {
        error(_0x20d176.message);
    }
    if (!_0x3d32e2.includes('#') && JSON.stringify(_0x9c1fec) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x9c1fec));
    } else {
        url(_0x3d32e2.split('#')[1]);
        var _0x345a0b = _0x3d32e2.split('#')[2];
        if (_0x345a0b.toLowerCase().includes('pro')) {
            if (!isWebView) {
                error('NO Webview');
            } else {
                getWebViewOwnContentJS(_0x3d32e2, 'molystream', 'I AM NOT LOOKING', 'playSheilaBtn', '', 0, false, headers, 1);
            }
        } else {
            _0x26519 = fetch();
            url(_0x26519.match(/frame.*?src="(.*?)".*?webkitallowfullscreen/)[1]);
            _0x26519 = fetch();
            if (url(['haydi.php'], 1)) {
                try {
                    var _0xdf8e4b = matchAll(_0x26519, /<iframe(.*?)<\/iframe/g);
                    for (let _0x1f9256 = 0; _0x1f9256 < _0xdf8e4b.length; _0x1f9256++) {
                        if (!_0xdf8e4b[_0x1f9256][1].includes('display:')) {
                            url(_0xdf8e4b[_0x1f9256][1]);
                            break;
                        }
                    }
                    url(url().match(/src=['"](.*?)['"]/)[1]);
                } catch (_0x25ab26) {
                    error(_0x25ab26.message);
                }
            } else {
                if (url(['moly.php'], 1)) {
                    try {
                        var _0xdf8e4b = matchAll(_0x26519, /unescape\("(.*?)"\)/g);
                        for (let _0x3fad48 = 0; _0x3fad48 < _0xdf8e4b.length; _0x3fad48++) {
                            if (!_0xdf8e4b[_0x3fad48][1].includes('display:')) {
                                url(_0xdf8e4b[_0x3fad48][1]);
                                break;
                            }
                        }
                        url(decodeURIComponent(url()));
                        url(atob(url()));
                        try {
                            var _0xdf8e4b = matchAll(url(), /<iframe(.*?)<\/iframe/g);
                            for (let _0x1ee221 = 0; _0x1ee221 < _0xdf8e4b.length; _0x1ee221++) {
                                if (!_0xdf8e4b[_0x1ee221][1].includes('display:')) {
                                    url(_0xdf8e4b[_0x1ee221][1]);
                                    break;
                                }
                            }
                        } catch (_0x56467b) {
                            error(_0x56467b.message);
                        }
                        url(url().match(/src=['"](.*?)['"]/)[1]);
                    } catch (_0x3fcc84) {
                        error(_0x3fcc84.message);
                    }
                }
            }
            parser();
        }
    }
}
function dizifin() {
    var _0x1a1a0f = fetch();
    headers('Referer', url());
    _0x1a1a0f = _0x1a1a0f.match(/iframe\s*src="(.*?)"/)[1];
    var _0x54782d = baseUrl(_0x1a1a0f);
    headers('Referer', 'https://dizifin.com/');
    _0x1a1a0f = fetch(_0x1a1a0f);
    try {
        var _0x1ceaed = matchAll(_0x1a1a0f, /"file":"(.*?vtt)"/g);
        for (let _0x38265f = 0; _0x38265f < _0x1ceaed.length; _0x38265f++) {
            if (_0x1ceaed[_0x38265f][1].includes('tur') || _0x1ceaed[_0x38265f][1].includes('tr')) { }
            sub(_0x1ceaed[_0x38265f][1].replace(/\\/g, '').replace('..', _0x54782d));
        }
    } catch (_0x2bba00) {
        error(_0x2bba00.message);
    }
    _0x1a1a0f = _0x1a1a0f.match(/"file":\s*"(.*?)"/)[1].replace(/\\x/g, ' ');
    var _0x4f44a6 = _0x1a1a0f.split(' ');
    _0x1a1a0f = '';
    for (var _0x21af89 = 0; _0x21af89 < _0x4f44a6.length; _0x21af89++) {
        var _0x5e359c = parseInt(_0x4f44a6[_0x21af89], 16);
        var _0x1ce7aa = String.fromCharCode(_0x5e359c);
        _0x1a1a0f += _0x1ce7aa;
    }
    _0x1a1a0f = _0x1a1a0f.replace('..', _0x54782d).replace(' ', '').substring(1);
    url(_0x1a1a0f);
    parser();
}
function dizifix() {
    var _0x5e9522 = url();
    var _0x39e15e = fetch();
    var _0x1e97db = {
        _0x26aafa: _0x347c74
    };
    try {
        var _0x28f813 = matchAll(_0x39e15e, /<a\s*data-frame="(.*?)"/g);
        for (let _0x15f78a = 0; _0x15f78a < _0x28f813.length; _0x15f78a++) {
            var _0x347c74 = _0x28f813[_0x15f78a][1];
            var _0x26aafa = 'Kaynak-' + (_0x15f78a + 1);
            ;
        }
    } catch (_0x40189e) {
        error(_0x40189e.message);
    }
    if (!_0x5e9522.includes('#') && JSON.stringify(_0x1e97db) != '{}' && _0x1e97db.length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x1e97db));
    } else {
        if (url(['#'], 1)) {
            url(_0x5e9522.split('#')[1]);
        } else {
            url(_0x1e97db['Kaynak-1']);
        }
        headers('Referer', headers('Referer') + '/');
        if (!url(['dovideo'], 1) && !url(['videoloji'], 1)) {
            parser();
        } else {
            _0x39e15e = fetch();
            var _0x3642f0 = _0x39e15e.match(/"file":\s*"(.*?)"/)[1].replace(/\\x/g, '');
            url(hex2a(_0x3642f0).replace('..', baseUrl(url())));
            try {
                var _0x325b45 = matchAll(_0x39e15e, /"file":"(.*?)"/g);
                sub('');
                for (var _0x24be55 = 0; _0x24be55 < _0x325b45.length; _0x24be55++) {
                    var _0x56c8a1 = _0x325b45[_0x24be55][1];
                    if (_0x56c8a1.includes('tur')) {
                        sub(_0x56c8a1.replace(/\\/g, '').replace('..', baseUrl(url())));
                    }
                }
                if (sub() == '') {
                    var _0x56c8a1 = _0x325b45[_0x325b45.length - 1][1];
                    sub(_0x56c8a1.replace(/\\/g, '').replace('..', baseUrl(url())));
                }
            } catch (_0x2827e2) {
                sub('');
            }
            parser();
        }
    }
}
function dizigom() {
    var _0x24aad0 = fetch().match(/"contentUrl"\s*:\s*"(.*?)"/)[1].replace(/\\/g, '').replace('https://', 'https://play.');
    _0x24aad0 = fetch(_0x24aad0).match(/>(eval\(.*?\))\s*<\/script>/)[1];
    _0x24aad0 = unPack(_0x24aad0).split("\n").join('').match(/sources:(\[.*?\])/)[1];
    var _0x2d35a2 = JSON.parse(_0x24aad0);
    url(_0x2d35a2[0].file);
    parser();
}
function dizikral() {
    headers('x-requested-with', 'XMLHttpRequest');
    headers('content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
    var _0x9ef779 = fetch();
    var _0x36f7ec = _0x9ef779.match(/<iframe\s*src="(.*?)"/)[1];
    _0x9ef779 = fetch(_0x36f7ec);
    _0x9ef779 = _0x9ef779.match(/bePlayer\('\s*(.*?)'\s*,\s*'(.*?)'\)/);
    var _0x207bb6 = _0x9ef779[1];
    var _0x3bd718 = _0x9ef779[2];
    _0x9ef779 = hdmomplayer(_0x207bb6, _0x3bd718);
    sub(_0x9ef779[1]);
    url(_0x9ef779[0]);
    headers('Accept', '*/*');
    headers('Referer', _0x36f7ec);
    parser();
}
function dizilab() {
    var _0x5e47d8 = url();
    var _0x45c423 = fetch();
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    if (_0x45c423.includes('mobilembeds')) {
        _0x45c423 = _0x45c423.match(/mobilembeds(.*?)Sonra/)[1];
    } else {
        _0x45c423 = _0x45c423.match(/fa fa-angle-down(.*?)Sonra/)[1];
    }
    var _0x46a7b7 = {
        _0x51c720: _0x57dc18
    };
    try {
        var _0x7a9f91 = matchAll(_0x45c423, /loadVideo\('(.*?)'.*?"icon-tr"><\/span>(.*?)\s*<\/a>/g);
        for (let _0x46e789 = 0; _0x46e789 < _0x7a9f91.length; _0x46e789++) {
            var _0x57dc18 = _0x7a9f91[_0x46e789][1];
            var _0x51c720 = _0x7a9f91[_0x46e789][2].split(' ').join('').split("\n").join('').split("\t").join('');
            ;
        }
    } catch (_0x2e5bb3) {
        error(_0x2e5bb3.message);
    }
    if (!_0x5e47d8.includes('#') && JSON.stringify(_0x46a7b7) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x46a7b7));
    } else {
        url(_0x5e47d8.split('#')[1]);
        var _0x340638 = _0x5e47d8.split('#')[2];
        if (_0x340638.toLowerCase().includes('vip')) {
            if (!g.isWebView()) {
                error('NO Webview');
            } else {
                getWebViewOwnContentJS(_0x5e47d8, 'molystream', 'I AM NOT LOOKING', 'playSheilaBtn', '', 0, false, g.getHeadersJSON, 1);
            }
        } else {
            url(_0x46a7b7[_0x340638]);
            postData = 'vid=' + encodeURIComponent(url()) + '&mobil=1&tip=1&type=loadVideo';
            url(baseUrl(_0x5e47d8) + '/ajax');
            headers('Referer', _0x5e47d8.split('#')[0]);
            headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            headers('X-Requested-With', 'XMLHttpRequest');
            _0x45c423 = fetchPost(postData + '');
            g.logHeader();
            url(_0x45c423.match(/src=\\"(.*?)"/)[1].replace(/\\/g, ''));
            url('https://dizilab.com', baseUrl(_0x5e47d8));
            g.deleteHeader('X-Requested-With');
            g.deleteHeader('Content-Type');
            url(_0x45c423.match(/src=\\"(.*?)\\"/)[1].split("\\").join(''));
            url('https://dizilab.com', baseUrl(_0x5e47d8));
            if (url(['vplayer', 'oplayer'], 1)) {
                _0x45c423 = fetch();
                _0x45c423 = Core.fetchCFF(url(), g.getHeadersJSON(), "LÃ¼tfen bekleyiniz") + '';
                if (_0x45c423.includes('cff')) {
                    headers('Cookie', _0x45c423.split(';')[0] + ';');
                    _0x45c423 = fetch();
                    if (_0x45c423.includes("LÃ¼tfen bekleyiniz")) {
                        var _0x5583cb = 0;
                        while (_0x5583cb < 10) {
                            _0x45c423 = Core.fetchCFF(url(), g.getHeadersJSON(), "LÃ¼tfen bekleyiniz");
                            if (_0x45c423.includes('cff')) {
                                headers('Cookie', _0x45c423.split(';')[0] + ';');
                                _0x45c423 = fetch();
                            }
                            if (!_0x45c423.includes("LÃ¼tfen bekleyiniz")) {
                                break;
                            }
                            _0x5583cb++;
                        }
                    }
                }
                url(fetch().match(/src="(.*?)"/)[1]);
            }
            g.deleteHeader.Cookie;
            parser();
        }
    }
}
function dizilla() {
    headers('Accept', '*/*');
    var _0xdc348 = url();
    try {
        var _0x199402 = fetch();
        var _0x219ec7 = _0x199402.match(/<iframe.*?src="(.*?)"/)[1];
        if (!_0x219ec7.startsWith('https')) {
            _0x219ec7 = 'https:' + _0x219ec7;
        }
        if (_0x219ec7.includes('dizilla.org/vmplayer')) {
            _0x219ec7 = 'https://vidmoly.to/embed-' + _0x219ec7.split("\\?vid=")[1] + '.html';
        }
        url(_0x219ec7);
        parser();
    } catch (_0x35f8cb) {
        findSource(_0xdc348);
        parser();
    }
}
function dizimat() {
    var _0x3562a2 = fetch();
    var _0x585385 = _0x3562a2.match(/data-frame=["\']?(.*?)["\'\s*]/)[1];
    _0x3562a2 = fetch(_0x585385);
    url(_0x3562a2.match(/file\s*:\s*"(.*?m3u8.*?)"/)[1]);
    try {
        var _0x386774 = _0x3562a2.match(/tracks:\s*(.*?])/)[1];
        _0x386774 = _0x386774.split('}');
        for (let _0x43ed3e = 0; _0x43ed3e < _0x386774.length; _0x43ed3e++) {
            if (_0x386774[_0x43ed3e].includes('Tur') && _0x386774[_0x43ed3e].includes('.vtt')) {
                sub(baseUrl(url()) + '' + _0x386774[_0x43ed3e].match(/file:\s*"(.*?)"/)[1]);
            }
        }
    } catch (_0x4cabb1) { }
    parser();
}
function dizimia() {
    var _0x4baeb6 = url();
    var _0x3a0ae0 = fetch();
    var _0x51a872 = {};
    if (g.getLang() == 0) {
        Core.setLang(1);
    } else {
        Core.setLang(0);
    }
    try {
        var _0x5a2590 = matchAll(_0x3a0ae0, /<a class="focus:outline-none"\s*href="(.*?)"\s*title="(.*?)"/g);
        var _0x4a845e = 1;
        for (let _0x3cbbe0 = 0; _0x3cbbe0 < _0x5a2590.length; _0x3cbbe0++) {
            if (_0x5a2590[_0x3cbbe0][2] != 'Fragman') {
                _0x51a872[_0x5a2590[_0x3cbbe0][2]] = _0x5a2590[_0x3cbbe0][1];
                _0x4a845e++;
            }
        }
    } catch (_0x172ec4) {
        error(_0x172ec4.message);
    }
    if (!_0x4baeb6.includes('#') && JSON.stringify(_0x51a872) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x51a872));
    } else {
        url(_0x4baeb6.split('#')[1]);
        var _0x3a0ae0 = fetch();
        var _0x310ea2 = _0x3a0ae0.match(/<iframe.*?src="(.*?)"/)[1];
        if (!_0x310ea2.startsWith('https')) {
            _0x310ea2 = 'https:' + _0x310ea2;
        }
        url(_0x310ea2);
        parser();
    }
}
function dizimom() {
    var _0x106636 = url();
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Mobile Safari/537.36');
    headers('X-Requested-With', 'XMLHttpRequest');
    headers('sec-ch-ua-mobile', '?1');
    var _0x3ee9f8 = fetch();
    urls = {};
    singleUrl = '';
    try {
        var _0xd2ddf8 = matchAll(_0x3ee9f8, /iframe.*?src="([^\"]+)".*?<\/iframe>/g);
        for (let _0x369136 = 0; _0x369136 < _0xd2ddf8.length; _0x369136++) {
            if (!_0xd2ddf8[_0x369136][1].includes('video_onu')) {
                urls[_0xd2ddf8[_0x369136][1]] = _0xd2ddf8[_0x369136][1];
                singleUrl = _0xd2ddf8[_0x369136][1];
            }
        }
    } catch (_0x5808cd) { }
    if (!_0x106636.includes('#') && urls.length > 1) {
        Core.showAlternatesJS(JSON.stringify(urls));
    } else {
        if (!_0x106636.includes('#')) {
            _0x106636 += '#' + singleUrl;
        }
        var _0x194b3f = urls[_0x106636.split('#')[1]].replace('https://hdplayersystem.live/video/', '');
        if (!_0x194b3f.includes('videoseyred.in') && !_0x194b3f.includes('youtube')) {
            if (_0x194b3f.includes('hdmomplayer')) {
                var _0x3ee9f8 = fetch(_0x194b3f);
                _0x3ee9f8 = _0x3ee9f8.match(/bePlayer\('(.*?)',\s*'(.*?)'\)/);
                headers('Referer', _0x194b3f);
                headers('Accept', '*/*');
                headers('Origin', baseUrl(_0x194b3f));
                var _0x1fd8b3 = _0x3ee9f8[1];
                var _0xe02b02 = _0x3ee9f8[2];
                _0x3ee9f8 = hdmomplayer(_0x1fd8b3, _0xe02b02);
                sub(_0x3ee9f8[1]);
                url(_0x3ee9f8[0]);
            } else {
                if (_0x194b3f.includes('index.php') || _0x194b3f.includes('peacemakerst')) {
                    url(_0x194b3f + '&do=getVideo');
                } else {
                    url('https://hdplayersystem.live/player/index.php?data=' + _0x194b3f + '&do=getVideo');
                }
                var _0x3ee9f8 = fetchPost('hash=' + _0x194b3f + '&r=' + encodeURIComponent(_0x106636) + ')');
                var _0xc0f4bc = JSON.parse(_0x3ee9f8);
                if (_0xc0f4bc.hasOwnProperty('securedLink')) {
                    url(_0xc0f4bc.securedLink.replace(/\\\//g, '/'));
                } else {
                    url(_0xc0f4bc.videoSources[0].file);
                }
            }
        } else {
            _0x194b3f = _0x194b3f.replace('https://videoseyred.in/embed/', '').replace('?hideTitle=1', '');
            var _0x3ee9f8 = fetch('https://videoseyred.in/playlist/' + _0x194b3f + '.json');
            var _0xc0f4bc = JSON.parse(_0x3ee9f8);
            url(_0xc0f4bc[0].sources[0].file);
            var _0x5857fd = _0xc0f4bc[0].tracks;
            for (var _0x2df4f1 = 0; _0x2df4f1 < _0x5857fd.length; _0x2df4f1++) {
                if (_0x5857fd[_0x2df4f1].hasOwnProperty('language')) {
                    if (_0x5857fd[_0x2df4f1].language == 'tur') {
                        sub(_0x5857fd[_0x2df4f1].file);
                    }
                }
            }
            headers('Referer', 'https://videoseyred.in/');
        }
        parser();
    }
}
function dizipal() {
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x439a0c = fetch();
    url(_0x439a0c.match(/data-src="(.*?)"/)[1]);
    _0x439a0c = fetch();
    url(_0x439a0c.match(/file:"(.*?)"/)[1]);
    try {
        var _0x3b6d8d = matchAll(_0x439a0c, /\](http.*?\.vtt)/g);
        for (let _0x5a870e = 0; _0x5a870e < _0x3b6d8d.length; _0x5a870e++) {
            if (_0x3b6d8d[_0x5a870e][1].includes('Turkce') || _0x3b6d8d[_0x5a870e][1].includes('tr') || _0x3b6d8d[_0x5a870e][1].includes('_tur')) {
                sub(_0x3b6d8d[_0x5a870e][1]);
            }
        }
    } catch (_0x5c54d7) {
        sub('');
    }
    parser();
}
function diziplus() {
    var _0x5e0807 = '';
    _0x5e0807 = fetch();
    var _0x435ac6 = _0x5e0807.match(/data-frame="(.*?)"/)[1];
    if (!_0x435ac6.includes('vidmoxy')) {
        _0x5e0807 = fetch(_0x435ac6);
        var _0x4e0530 = _0x5e0807.match(/bePlayer\('(.*?)',\s*'(.*?)'\)/);
        var _0x2d9575 = _0x4e0530[1];
        var _0x39470e = _0x4e0530[2];
        headers('Referer', _0x435ac6);
        _0x5e0807 = hdmomplayer(_0x2d9575, _0x39470e);
        sub(_0x5e0807[1]);
        _0x5e0807 = _0x5e0807[0];
        headers('Accept', '*/*');
    } else {
        _0x5e0807 = _0x435ac6;
    }
    url(_0x5e0807);
    parser();
}
function dizipub() {
    var _0x475040 = url();
    if (g.getLang() == 2) {
        Core.setLang(1);
    }
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x39a4e5 = '';
    try {
        var _0x39a4e5 = fetch();
        _0x39a4e5 = _0x39a4e5.match(/series-alter-active(.*?) <\/ul>/)[1];
        var _0x3328ce = {
            _0x103379: _0x4f9f1a
        };
        try {
            var _0x35cb78 = matchAll(_0x39a4e5, /<(?:span class=|a.*?href=)"(.*?)"\s*title="(.*?)"/g);
            for (let _0x10d689 = 0; _0x10d689 < _0x35cb78.length; _0x10d689++) {
                var _0x4f9f1a = _0x35cb78[_0x10d689][1];
                var _0x103379 = _0x35cb78[_0x10d689][2];
                ;
            }
        } catch (_0x963f33) {
            error(_0x963f33.message);
        }
        if (!_0x475040.includes('#') && JSON.stringify(_0x3328ce) != '{}') {
            Core.showAlternatesJS(JSON.stringify(_0x3328ce));
        } else {
            url(_0x475040.split('#')[1]);
            try {
                _0x39a4e5 = fetch();
                url(_0x39a4e5.match(/<iframe.*?src="(.*?)".*?allowfullscreen.*?<\/iframe>/)[1]);
                url(fixUrl(url()));
            } catch (_0x41bcd3) {
                findSource(_0x475040.split('#')[0]);
            }
            parser();
        }
    } catch (_0x4a4a13) {
        findSource(_0x475040.split('#')[0]);
        parser();
    }
}
function dizirix() {
    var _0x5bb639 = url();
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    if (!_0x5bb639.includes('#')) {
        var _0xa9d4e4 = fetch();
        var _0x177ad1 = 'dublaj';
        if (g.getLang() == 1) {
            _0x177ad1 = 'altyazili';
        }
        var _0x17f5fb = _0xa9d4e4.match(/var\s*bID\s*=\s*"(\d*)"/)[1];
        var _0x2bdd81 = 'whichOne=' + _0x177ad1 + '&bId=' + _0x17f5fb;
        url(baseUrl(url()) + '/ajax/request=getLanguage');
        _0xa9d4e4 = fetchPost(_0x2bdd81);
        var _0x40fc11 = {
            _0x21210b: _0x1448d9
        };
        try {
            var _0x2d79f3 = matchAll(_0xa9d4e4, /data-code=\\\"(.*?)\\\".*?span>(.*?)</g);
            for (let _0x163a06 = 0; _0x163a06 < _0x2d79f3.length; _0x163a06++) {
                var _0x1448d9 = _0x2d79f3[_0x163a06][1];
                var _0x21210b = _0x2d79f3[_0x163a06][2];
                ;
            }
        } catch (_0xaaecac) {
            error(_0xaaecac.message);
        }
        Core.showAlternatesJS(JSON.stringify(_0x40fc11));
    } else {
        url(_0x5bb639.split('#')[1]);
        headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        var _0xa9d4e4 = fetchPost('dataCode=' + url(), baseUrl(_0x5bb639) + '/ajax/request=getFrames');
        _0xa9d4e4 = atob(_0xa9d4e4);
        url(_0xa9d4e4.match(/src="(.*?)"/)[1]);
        g.deleteHeader('Content-Type');
        if (url(['prixy.php', 'source=rix2', 'source-yg', 'proxy.php'], 1)) {
            headers('upgrade-insecure-requests', '1');
            _0xa9d4e4 = fetch();
            url('dummy');
            try {
                url(_0xa9d4e4.match(/file:"(.*?)"/)[1]);
                var _0x2d3a69 = _0xa9d4e4.match(/"file":.*?"(.*?)"/) || [];
                if (_0x2d3a69.length > 0) {
                    sub(_0x2d3a69[1]);
                }
            } catch (_0x37353f) {
                url(_0xa9d4e4.match(/Back.*?'(https.*?)'/)[1]);
            }
            g.deleteHeader('upgrade-insecure-requests');
            headers('Origin', baseUrl(_0x5bb639));
        }
        parser();
    }
}
function diziroll() {
    var _0x5bd9cd = fetch();
    _0x5bd9cd = _0x5bd9cd.match(/focus\:outline-none"\s*href="(.*?)"\s*title="(.*?)"\s*data-navigo/)[1];
    _0x5bd9cd = fetch(_0x5bd9cd);
    try {
        var _0xe98382 = matchAll(_0x5bd9cd, /iframe.*?src="(.*?)"/g);
        for (let _0x85831c = 0; _0x85831c < _0xe98382.length; _0x85831c++) {
            if (!_0xe98382[_0x85831c][1].includes('finema')) {
                url(_0xe98382[_0x85831c][1]);
                break;
            }
        }
    } catch (_0x2899f0) {
        error(_0x2899f0.message);
    }
    if (g.getLang() == 2) {
        Core.setLang(0);
    }
    parser();
}
function dizitime() {
    var _0x560143 = url();
    var _0x5c508a = fetch();
    var _0x35dcab = {
        _0x50b6f9: _0x33c1f4
    };
    try {
        var _0x4f205c = matchAll(_0x5c508a, /data-name="(.*?)"\s*data-oid="(.*?)"/g);
        for (let _0x387c57 = 0; _0x387c57 < _0x4f205c.length; _0x387c57++) {
            var _0x33c1f4 = _0x4f205c[_0x387c57][2];
            var _0x50b6f9 = _0x4f205c[_0x387c57][1];
            if (!_0x35dcab.hasOwnProperty(_0x50b6f9) && !_0x50b6f9.toLowerCase().includes('odnok')) {
                ;
            }
        }
    } catch (_0x1747fe) {
        error(_0x1747fe.message);
    }
    if (!_0x560143.includes('#') && JSON.stringify(_0x35dcab) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x35dcab));
    } else {
        url(_0x560143.split('#')[1]);
        var _0x7e7ffc = _0x560143.split('#')[2];
        if (_0x7e7ffc.toLowerCase().includes('time')) {
            if (!g.isWebView()) {
                error('NO Webview');
            } else {
                getWebViewOwnContentJS(_0x560143, 'molystream.org', 'I AM NOT LOOKING', 'playSheilaBtn', '', 0, false, g.getHeadersJSON(), 1);
            }
        } else {
            url(_0x35dcab[_0x7e7ffc]);
            headers('Referer', baseUrl(_0x560143));
            url(baseUrl(_0x560143) + '/getvideo/' + url() + '_t');
            url(Core.getRedirectUrlJS(url()));
            parser();
        }
    }
}
function diziwatch() {
    var _0xd4df82 = fetch();
    if (!_0xd4df82.includes('<iframe')) {
        _0xd4df82 = _0xd4df82.toLowerCase();
        var _0x2b84cd = _0xd4df82.match(/url\s*:\s*"(http[^"]+php)".*?\'pid\'\s*:\s*(\d+),/);
        _0xd4df82 = fetch(_0x2b84cd[1] + '?action=playlist&pid=' + _0x2b84cd[2]).toLowerCase();
        var _0x3bc640 = JSON.parse(_0xd4df82);
        url(_0x3bc640[0].sources[0].file);
    } else {
        var _0x2b84cd = _0xd4df82.match(/<iframe\s*src="(.*?)".*?allowfullscreen>/)[1];
        _0xd4df82 = fetch(_0x2b84cd);
        url(_0xd4df82.match(/'\/playlist\/(.*?).json';/)[1]);
        _0xd4df82 = fetch('https://videoseyred.in/playlist/' + url() + '.json');
        var _0x122d0f = JSON.parse(_0xd4df82);
        url(_0x122d0f[0].sources[0].file);
        var _0x164975 = _0x122d0f[0].tracks;
        for (var _0x7e237c = 0; _0x7e237c < _0x164975.length; _0x7e237c++) {
            if (_0x164975[_0x7e237c].hasOwnProperty('language')) {
                if (_0x164975[_0x7e237c].language == 'tur') {
                    sub(_0x164975[_0x7e237c].file);
                }
            }
        }
        headers('Referer', 'https://videoseyred.in/');
    }
    parser();
}
function diziyo() {
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x1e0877 = fetch();
    var _0x854e98 = _0x1e0877.match(/id="player-option-1"\s*class=".*?"\s*data-type="(.*?)"\s*data-post=".*?"\s*data-nume=".*?"/)[1];
    var _0x21e395 = _0x1e0877.match(/id="player-option-1"\s*class=".*?"\s*data-type=".*?"\s*data-post="(.*?)"\s*data-nume=".*?"/)[1];
    var _0x2ce50d = _0x1e0877.match(/id="player-option-1"\s*class=".*?"\s*data-type=".*?"\s*data-post=".*?"\s*data-nume="(.*?)"/)[1];
    var _0x2375d0 = 'action=doo_player_ajax&post=' + _0x21e395 + '&nume=' + _0x2ce50d + '&type=' + _0x854e98;
    _0x1e0877 = fetchPost(_0x2375d0, baseUrl(url()) + '/wp-admin/admin-ajax.php');
    _0x1e0877 = _0x1e0877.match(/src='(.*?)'/)[1];
    var _0x15415b = _0x1e0877.split('/');
    var _0x3bfc53 = _0x15415b[_0x15415b.length - 1];
    _0x2375d0 = 'hash=' + _0x3bfc53 + '&r=' + baseUrl(url()) + '/';
    headers('X-Requested-With', 'XMLHttpRequest');
    var _0x41af99 = fetchPost(_0x2375d0, _0x1e0877 + '?do=getVideo');
    headers('Referer', _0x1e0877);
    var _0x46364d = JSON.parse(_0x41af99);
    url(_0x46364d.videoSources[0].file);
    g.deleteHeader('X-Requested-With');
    headers('Accept', '*/*');
    parser();
}
function diziyou() {
    var _0x5ad91e = fetch();
    url(_0x5ad91e.match(/<iframe.*?src="(.*?)"/)[1]);
    if (g.getLang() == 0) {
        url(url('.html', '_tr.html'));
    }
    var _0x5ad91e = fetch();
    sub(_0x5ad91e.match(/<track\s*src="(.*?)"/)[1]);
    url(_0x5ad91e.match(/<source.*?src="(.*?)"/)[1]);
    parser();
}
function dood() {
    url(fixUrl(url()));
    url('.so', '.yt');
    url('.pm', '.yt');
    url('dooood.com', 'dood.la');
    var _0x5b7278 = fetch().match(/dsplayer\.hotkeys[^']+'([^']+).+?function\s*makePlay.+?return[^?]+([^"]+)/);
    var _0x26dc04 = 'https://d0000d.com' + _0x5b7278[1];
    var _0x395455 = '';
    for (var _0xd2913b = 0; _0xd2913b < 10; _0xd2913b++) {
        _0x395455 += 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'.charAt(Math.floor(Math.random() * 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'.length));
    }
    var _0x4cedd7 = new Date().getTime();
    headers('Referer', 'https://d0000d.com');
    url(fetch(_0x26dc04) + _0x395455 + _0x5b7278[2] + _0x4cedd7 + '#.mp4');
    parser();
}
function dreamturk() {
    var _0x22a5a9 = fetch().match(/data-id="(.*?)"/)[1];
    url('https://www.dreamturk.com.tr/actions/content/media/' + _0x22a5a9);
    var _0x2509a4 = JSON.parse(fetch());
    url(_0x2509a4.Media.Link.ServiceUrl + _0x2509a4.Media.Link.SecurePath);
    parser();
}
function filemoon() {
    data = fetch();
    try {
        data = (data + '').match(/text\/javascript.*?\s*(eval.*?)<\/sc/)[1];
    } catch (_0x29221f) {
        try {
            data = (data + '').match(/<iframe\s*src="(.*?)"/)[1];
            headers('Accept-Language', 'tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7');
            headers('sec-fetch-dest', 'iframe');
            data = fetch(data);
            data = (data + '').match(/text\/javascript.*?(eval.*?)<\/script/)[1];
        } catch (_0x4965ff) {
            data = fetch();
            data = (data + '').match(/text\/javascript.*?(eval.*?)<\/script/)[1];
        }
    }
    url(unPack(data));
    try {
        url(url().match(/file:"(.*?m3u8)"/)[1]);
    } catch (_0x1c3221) {
        url(url().match(/file\s*:"(.*?)"/)[1]);
    }
    parser();
}
function filmatek() {
    var _0x2dbe51 = url();
    var _0x22f60c = 1;
    var _0x214173 = fetch();
    var _0x39615b = {};
    try {
        var _0x506c9e = matchAll(_0x214173, /(\d+)\.\s*KÄ±sÄ±m/g);
        var _0x51668f = 1;
        for (let _0x2462db = 0; _0x2462db < _0x506c9e.length; _0x2462db++) {
            if (!_0x506c9e[_0x2462db][1].includes('youtube')) {
                if (_0x2dbe51.includes('#')) {
                    if (_0x506c9e[_0x2462db][1] == _0x2dbe51.split('#')[1]) {
                        _0x22f60c = _0x2462db + 1;
                    }
                }
                _0x39615b["BÃ¶lÃ¼m " + _0x51668f] = _0x506c9e[_0x2462db][1];
                _0x51668f++;
            }
        }
    } catch (_0x5336bd) {
        error(_0x5336bd.message);
    }
    if (!_0x2dbe51.includes('#') && JSON.stringify(_0x39615b) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x39615b));
    } else {
        if (_0x2dbe51.includes('#')) {
            _0x22f60c = _0x2dbe51.split('#')[1];
        }
        var _0x4066bf = _0x214173.match(/layer_api":"(.*?)","play_aj/)[1];
        var _0x257b9b = _0x214173.match(/data-post='(\d+)'/)[1];
        url(_0x4066bf.replace(/\\\//g, '/') + _0x257b9b + '/movie/' + _0x22f60c);
        var _0x214173 = fetch();
        try {
            var _0x338ebc = JSON.parse(_0x214173);
            url(_0x338ebc.embed_url);
            _0x214173 = fetch();
            url(_0x214173.match(/"file"\s*:\s*"(.*?)"/)[1].replace(/\\\//g, '/'));
            parser();
        } catch (_0x1ec262) {
            error(_0x1ec262.message);
        }
    }
}
function filmcidayi() {
    var _0x3b67fd = fetch();
    var _0x1b1ad9 = 'movie';
    if (url(['/bolum'], 1)) {
        _0x1b1ad9 = 'tv';
    }
    var _0x1b7cad = _0x3b67fd.match(/data-post=[\'"](.*?)[\'"]/)[1];
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    _0x3b67fd = fetchPost('action=doo_player_ajax&post=' + _0x1b7cad + '&nume=1&type=' + _0x1b1ad9, baseUrl(url()) + '/wp-admin/admin-ajax.php');
    var _0x1a6e5c = JSON.parse(_0x3b67fd).embed_url;
    _0x3b67fd = fetchPost(_0x3b67fd, _0x1a6e5c);
    url(_0x3b67fd.match(/file:"(.*?)"/)[1]);
    g.deleteHeader('Content-Type');
    headers('Referer', _0x1a6e5c);
    headers('Accept', '*/*');
    headers('Origin', 'https://hotstream.club');
    parser();
}
function filmekseni() {
    var _0xd0d0a = url();
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('X-Requested-With', 'XMLHttpRequest');
    var _0x56f3c9 = fetch();
    try {
        _0x56f3c9 = _0x56f3c9.match('tab-pane active(.*?)</nav>')[1];
    } catch (_0x3db409) {
        error('Telif');
    }
    var _0x1db8e6 = {};
    try {
        var _0x34e330 = matchAll(_0x56f3c9, /href="(.*?)">\s*(.*?)\s*<\/a>/g);
        for (let _0x6123b = 0; _0x6123b < _0x34e330.length; _0x6123b++) {
            _0x1db8e6[_0x34e330[_0x6123b][2]] = _0x34e330[_0x6123b][1];
        }
    } catch (_0x6db13e) { }
    if (!_0xd0d0a.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x1db8e6));
    } else {
        url(_0xd0d0a.split('#')[1]);
        var _0x56f3c9 = fetch();
        url(_0x56f3c9.match(/iframe.*?data-src="(.*?)"/)[1]);
        if (url(['vidload'], 1)) {
            url(fixUrl(url()));
            var _0x32b6d4 = new Date();
            _0x32b6d4 = _0x32b6d4.getTime();
            var _0x2fc7ba = url().split('/')[url().split('/').length - 1];
            var _0x54cb07 = baseUrl(url());
            url(_0x54cb07 + '/ajax/' + _0x2fc7ba + '?' + _0x32b6d4);
            headers('Referer', _0x54cb07);
            var _0x2c141c = fetch(_0x54cb07 + '/video.js?' + _0x32b6d4);
            var _0x225fee = _0x2c141c.match("window,'(.*?)','.*?','.*?'")[1];
            var _0x2521f7 = _0x2c141c.match("window,'.*?','(.*?)','.*?'")[1];
            headers(_0x225fee, _0x2521f7);
            _0x56f3c9 = fetch();
            var _0x26363c = JSON.parse(_0x56f3c9);
            url(_0x26363c.file + '?' + _0x26363c.hash);
            url(Core.getRedirectUrlJS(url()));
        } else {
            headers('Referer', _0xd0d0a);
        }
        parser();
    }
}
function filmizle4k() {
    var _0x2556c9 = fetch(fetch().match(/<p><iframe.*?src="(.*?)".*?/)[1]);
    try {
        var _0x1a483a = JSON.parse(_0x2556c9.match(/jwSetup\.tracks\s*=\s*(\[.*?\])/)[1]);
        for (let _0x37520f = 0; _0x37520f < _0x1a483a.length; _0x37520f++) {
            if (_0x1a483a[_0x37520f].hasOwnProperty('label') && _0x1a483a[_0x37520f].label.includes("Ã¼rkÃ§e")) {
                sub(_0x1a483a[_0x37520f].file);
            }
        }
        sub(sub().replace('.vtt', '.srt'));
    } catch (_0x579e43) { }
    url(hexToString(_0x2556c9.match(/(?:"|)(?:file|hls)(?:"|)\s*:\s*"(.*?)"/)[1]));
    parser();
}
function filmizlemek() {
    error("4kfilmizlemek isimli kaynak site kapalÄ± olduÄŸundan oynatÄ±lamÄ±yor.");
    var _0xf7ad47 = url();
    var _0x11e7fb = fetch();
    var _0x2e21e9 = {};
    try {
        var _0x4c5955 = matchAll(_0x11e7fb, /class="parttab.*?href="(.*?)".*?<\/i>(.*?)</g);
        for (let _0xebacef = 0; _0xebacef < _0x4c5955.length; _0xebacef++) {
            alt = _0x4c5955[_0xebacef][2].trim().replace('HD', '');
            if (!alt.includes('ragman')) {
                _0x2e21e9[alt + _0xebacef] = fixUrl(_0x4c5955[_0xebacef][1]);
            }
        }
    } catch (_0x10ae84) {
        error(_0x10ae84.message);
    }
    if (!_0xf7ad47.includes('#') && JSON.stringify(_0x2e21e9) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x2e21e9));
    } else {
        if (_0xf7ad47.includes('#')) {
            part = _0xf7ad47.split('#')[1];
        }
        consolelog(part);
        _0x11e7fb = fetch(part);
        url(_0x11e7fb.match(/<iframe.*?src="(.*?)"/)[1]);
        parser();
    }
}
function filmizlesene() {
    var _0x2c373e = url();
    headers['User-Agent'] = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36';
    var _0x51ae0a = fetch();
    var _0x3452fc = _0x51ae0a.match(/(?:inepisode|bolumler)(.*?)vidcontainer/)[1];
    if (_0x3452fc == '' && !_0x2c373e.includes('#')) {
        _0x51ae0a = _0x51ae0a.match(/iframe\s*src="(.*?)"/)[1];
        if (_0x51ae0a.includes('/ok/')) {
            var _0x4495d3 = _0x51ae0a.split('?v=')[1];
            _0x4495d3 = atob(_0x4495d3);
            url('https://odnoklassniki.ru/videoembed/' + _0x4495d3);
        }
        parser();
    } else {
        _0x51ae0a = _0x3452fc;
        if (!_0x2c373e.includes('#')) {
            var _0x12d3f6 = {
                _0x37df4f: _0x10dfbd
            };
            try {
                var _0x22dd25 = matchAll(_0x51ae0a, /dil="(.*?)">(.*?)<.*?iframe\s*src="(.*?)"/g);
                for (let _0x270bb1 = 0; _0x270bb1 < _0x22dd25.length; _0x270bb1++) {
                    if (_0x37df4f != 'opn' && _0x37df4f != 'up') {
                        var _0x10dfbd = _0x22dd25[_0x270bb1][3];
                        var _0x37df4f = _0x22dd25[_0x270bb1][2] + ', ' + _0x22dd25[_0x270bb1][1];
                        ;
                    }
                }
            } catch (_0x3d2a6a) {
                error(_0x3d2a6a.message);
            }
            Core.showAlternatesJS(JSON.stringify(_0x12d3f6));
        } else {
            url(_0x2c373e.split('#')[1]);
            if (url(['vidmoly'], 1)) {
                url(url().match(/vid=(.*?)$/)[1]);
            } else {
                if (url(['mail.ru'], 1)) { } else {
                    _0x51ae0a = fetch();
                    _0x51ae0a = _0x51ae0a.match(/iframe\s*src=(?:\'|")(.*?)(?:\'|")/)[1];
                    if (_0x51ae0a.includes('hdplayer') || _0x51ae0a.includes('vidmo')) {
                        var _0x14e860 = fetchResponseHeader(url(), g.getHeadersJSON(), 'set-cookie');
                        headers('Cookie', '');
                        try {
                            _0x14e860 = _0x14e860.match(/(PHPS.*?;)/)[1];
                            headers('Cookie', _0x14e860 + '');
                            g.logHeader();
                        } catch (_0x3f4034) { }
                        _0x14e860 = fetchResponseHeader(_0x51ae0a, g.getHeadersJSON(), 'set-cookie');
                        headers('Cookie', headers('Cookie') + _0x14e860);
                        _0x51ae0a = fetch(_0x51ae0a);
                        g.logHeader();
                        var _0x4e9c88 = false;
                        _0x51ae0a = _0x51ae0a.match(/iframe.*?src\s*=\s*"(.*?)"/)[1];
                        if (_0x51ae0a.includes('odnoklass')) {
                            url(_0x51ae0a);
                            _0x4e9c88 = true;
                        }
                        if (!_0x51ae0a.includes('hdplayer')) {
                            url(_0x51ae0a + '/sheila');
                            g.deleteHeader('Cookie');
                            headers('Referer', _0x51ae0a);
                            _0x4e9c88 = true;
                        }
                    } else {
                        url(_0x51ae0a);
                    }
                }
            }
            parser();
        }
    }
}
function filmkovasi() {
    try {
        var _0x198127 = fetch();
    } catch (_0x45de1d) {
        error('Telif');
    }
    var _0x1ae622 = _0x198127.match(/iframe.*?src=["']?(.*?)[\s>"']/)[1];
    if (!_0x1ae622.includes('trstx') && !_0x1ae622.includes('sobreatsesuyp') && !_0x1ae622.includes('vidrame')) {
        _0x198127 = fetch(_0x1ae622);
        var _0xb77bf0 = baseUrl(_0x1ae622);
        var _0x19c298 = _0x198127.match(/uid":"(.*?)"/)[1];
        var _0xfb3d61 = _0x198127.match(/md5":"(.*?)"/)[1];
        var _0xfbc363 = _0x198127.match(/"id":"(.*?)"/)[1];
        var _0x578ca2 = _0x198127.match(/status":"(.*?)"/)[1];
        _0x1ae622 = _0x198127.match(/file:\s*(.*?m3u8.*?),/)[1];
        _0x1ae622 = _0xb77bf0 + _0x1ae622.replace('`', '').replace('`', '').replace('${video.uid}', _0x19c298).replace('${video.id}', _0xfbc363).replace('${video.status}', _0x578ca2).replace('${video.md5}', _0xfb3d61);
        headers('Referer', 'https://watch.trplayer.site/');
        headers('Accept', '*/*');
    }
    url(_0x1ae622);
    parser();
}
function filmmakinesi() {
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    try {
        var _0x18d9aa = fetch(fetch().match(/php.*?(https:\/\/closeload.filmmakinesi.*?embed.*?) /)[1]);
    } catch (_0x39710b) {
        if (fetch().toLowerCase().includes('telif haklar')) {
            error('Telif');
        }
    }
    var _0x536985 = 'https://closeload.' + baseUrl(url(), true);
    try {
        var _0x23f2e3 = matchAll(_0x18d9aa, /<track src="(.*?)"\s*kind="captions"/g);
        for (let _0x46ea29 = 0; _0x46ea29 < _0x23f2e3.length; _0x46ea29++) {
            if (_0x23f2e3[_0x46ea29][1].includes('tr')) {
                sub(_0x536985 + '/' + _0x23f2e3[_0x46ea29][1]);
                break;
            }
        }
    } catch (_0x3f03e6) { }
    url(atob(unPack(_0x18d9aa.match('(eval.*?)</script>')[1]).match("(aHR0cH.*?)\"")[1]));
    headers('Referer', _0x536985);
    g.setMediaType('mimeType:MPEG-TS');
    parser();
}
function filmmax() {
    var _0x5214fe = fetch();
    var _0x76129e = _0x5214fe.match(/iframe.*?src="(.*?)"/)[1];
    _0x5214fe = fetch(_0x76129e);
    url(_0x5214fe.match(/'\/playlist\/(.*?).json';/)[1]);
    _0x5214fe = fetch('https://videoseyred.in/playlist/' + url() + '.json');
    var _0x7d6540 = JSON.parse(_0x5214fe);
    url(_0x7d6540[0].sources[0].file);
    var _0x21c20b = _0x7d6540[0].tracks;
    for (var _0x19827f = 0; _0x19827f < _0x21c20b.length; _0x19827f++) {
        if (_0x21c20b[_0x19827f].hasOwnProperty('language')) {
            if (_0x21c20b[_0x19827f].language == 'tur') {
                sub(_0x21c20b[_0x19827f].file);
            }
        }
    }
    headers('Referer', 'https://videoseyred.in/');
    parser();
}
function filmmodu() {
    var _0x567234 = JSON.parse(fetch());
    if (_0x567234.hasOwnProperty('subtitle')) {
        sub(baseUrl(url()) + _0x567234.subtitle);
    }
    var _0x4ddd2e = _0x567234.sources;
    _0x4ddd2e.sort(function (_0x534f32, _0x283a09) {
        return _0x534f32.label.localeCompare(_0x283a09.label);
    });
    url(_0x4ddd2e[0].src);
    parser();
}
function filmon() {
    url(url('https://www.filmon.com/', ''));
    var _0x50df0c = 'channel_id=' + url() + '&quality=low';
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('X-Requested-With', 'XMLHttpRequest');
    headers('Cookie', 'PHPSESSID=');
    var _0x1d8b01 = fetchPost(_0x50df0c, 'https://www.filmon.com/ajax/getChannelInfo');
    var _0x3bc221 = JSON.parse(_0x1d8b01);
    url(_0x3bc221.serverURL);
    parser();
}
function fullhdfilm() {
    var _0x542e51 = url();
    var _0x47faf6 = fetch();
    var _0x39bcf9 = {};
    try {
        var _0x2b1670 = matchAll(_0x47faf6, /id='(\d+)'.*?href=\"#\">(.*?)<\/a><\/li>/g);
        for (let _0x55987b = 0; _0x55987b < _0x2b1670.length; _0x55987b++) {
            _0x39bcf9[_0x2b1670[_0x55987b][2]] = fixUrl(_0x2b1670[_0x55987b][1]);
        }
    } catch (_0x364208) {
        error(_0x364208.message);
    }
    if (!_0x542e51.includes('#') && JSON.stringify(_0x39bcf9) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x39bcf9));
    } else {
        if (_0x542e51.includes('#')) {
            part = _0x542e51.split('#')[1].replace('https://', '');
        }
        var _0x18755c = "pdata\\['prt_" + part + "'\\]\\s*=\\s*'(.*?)'";
        var _0x1fed05 = new RegExp(_0x18755c);
        var _0x354fe8 = 'PGlmcmFtZSB' + _0x47faf6.match(_0x1fed05)[1];
        var _0x224e3b = g.base64Decode(_0x354fe8);
        url(_0x224e3b.match(/src=["\\\'](.*?)["\\\']/)[1]);
        parser();
    }
}
function fullhdfilmizlesene() {
    var _0x3223b6 = url();
    var _0x589cf1 = fetch().match(/var\s*scx\s*=\s*(.*?);/)[1];
    var _0x4d6962 = JSON.parse(_0x589cf1);
    var _0x44b129 = 'tr';
    var _0x27b6c9 = {
        _0x5638b0: atob(_0x3af67c)
    };
    var _0x335b73 = {
        _0x5638b0: _0x4d6962[_0x5638b0].sx.t[0].replace(/\\/g, ''),
        _0x5638b0: _0x4d6962[_0x5638b0].sx.t[_0x44b129].replace(/\\/g, '')
    };
    if (g.getLang() == 1) {
        _0x44b129 = 'en';
    }
    for (let _0x10fc61 = 0; _0x10fc61 < Object.keys(_0x4d6962).length; _0x10fc61++) {
        var _0x5638b0 = Object.keys(_0x4d6962)[_0x10fc61];
        try {
            ;
        } catch (_0x12ddf5) {
            ;
        }
    }
    var _0x3af67c = '';
    for (let _0x52c615 = 0; _0x52c615 < Object.keys(_0x335b73).length; _0x52c615++) {
        var _0x5638b0 = Object.keys(_0x335b73)[_0x52c615];
        for (let _0x1e03d2 = 0; _0x1e03d2 < _0x335b73[_0x5638b0].length; _0x1e03d2++) {
            _0x3af67c += findRealChar(_0x335b73[_0x5638b0].substring(_0x1e03d2, _0x1e03d2 + 1));
        }
        ;
    }
    if (!_0x3223b6.includes('#') && Object.keys(_0x27b6c9).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x27b6c9));
    } else {
        url(_0x27b6c9[Object.keys(_0x27b6c9)[0]]);
        _0x589cf1 = fetch();
        try {
            var _0x49d926 = JSON.parse(_0x589cf1.match(/jwSetup\.tracks\s*=\s*(\[.*?\])/)[1]);
            for (let _0x318fc4 = 0; _0x318fc4 < _0x49d926.length; _0x318fc4++) {
                if (_0x49d926[_0x318fc4].hasOwnProperty('label') && _0x49d926[_0x318fc4].label.includes("Ã¼rkÃ§e")) {
                    sub(_0x49d926[_0x318fc4].file);
                }
            }
        } catch (_0x276c8c) { }
        try {
            url(hexToString(_0x589cf1.match(/(?:"|)(?:file|hls)(?:"|)\s*:\s*"(.*?)"/)[1]));
        } catch (_0x14507e) {
            url(_0x27b6c9[Object.keys(_0x27b6c9)[0]]);
        }
        if (url() == '') {
            url(_0x27b6c9[Object.keys(_0x27b6c9)[0]]);
        }
        sub(sub().replace('.vtt', '.srt'));
        parser();
    }
}
function govids() {
    var _0x2f73d3 = url().split('?')[1].split('=');
    var _0x3e45ce = _0x2f73d3[1];
    var _0x2ad009 = _0x2f73d3[2];
    var _0x14cb12 = _0x2f73d3[3];
    var _0x1e24c7 = 'i=' + _0x3e45ce + '=' + _0x2ad009 + '=' + _0x14cb12;
    headers('Referer', url());
    headers('sec-ch-ua-platform', "\"Windows\"");
    headers('sec-ch-ua-mobile', '?0');
    url('/embed?', '/embed/get?');
    var _0x1575d7 = fetchPost(_0x1e24c7);
    try {
        var _0x5d74ce = JSON.parse(_0x1575d7);
        var _0x48f46a = _0x5d74ce.Links[0];
        _0x48f46a = _0x48f46a.split('redirect')[1];
        var _0x17999f = '#.mp4';
        if (url(['setplay'], 1)) {
            _0x17999f = '';
        }
        url(baseUrl(url()) + '/redirect' + _0x48f46a + _0x17999f);
    } catch (_0x41f895) {
        url('');
    }
    g.logHeader();
    parser();
}
function haberturk() {
    url(fetch().match(/videoUrl\s*=\s*"(.*?)"/)[1]);
    parser();
}
function hdabla() {
    var _0x13b46f = fetch();
    var _0x534935 = _0x13b46f.match(/<iframe\s*src="(.*?)"/)[1];
    _0x534935 = fixUrl(_0x534935);
    _0x13b46f = fetch(_0x534935);
    url(_0x13b46f.match(/file\s*:\s*'(.*?)'/)[1]);
    headers('Referer', 'https://wai.moonfast.site/');
    parser();
}
function hdfilmcehennemi() {
    var _0x3ff77b = url();
    var _0x3d6713 = fetch();
    var _0x338c36 = '';
    if (g.getLang() == 0) {
        _0x338c36 = _0x3d6713.match(/videostr(.*?)<\/nav/)[1];
    } else {
        _0x338c36 = _0x3d6713.match(/videosen(.*?)<\/nav/)[1];
    }
    var _0x5a885a = matchAll(_0x338c36, /nav-link\s*px-3\s*py-1.*?"\s*href="(.*?)"/g);
    var _0xd6e0e8 = {};
    for (let _0x1292a8 = 0; _0x1292a8 < _0x5a885a.length; _0x1292a8++) {
        _0xd6e0e8['Kaynak ' + (_0x1292a8 + 1)] = _0x5a885a[_0x1292a8][1];
    }
    if (!_0x3ff77b.includes('#') && Object.keys(_0xd6e0e8).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0xd6e0e8));
    } else {
        _0x3d6713 = fetch();
        try {
            _0x3d6713 = _0x3d6713.match(/<script>var.*?'(.*?)'/)[1].match(/iframe.*?src\s*=\s*(?:"|\')(.*?)(?:"|\')/)[1].toLowerCase();
            _0x3d6713 = fixUrl(_0x3d6713);
            if (_0x3d6713.includes('moly')) {
                if (_0x3d6713.includes('watch.php')) {
                    url('https://vidmoly.to/embed-' + _0x3d6713.split('watch.php')[1] + '.html');
                }
            } else {
                if (_0x3d6713.includes('odnok') || _0x3d6713.includes('ok.ru')) {
                    if (_0x3d6713.includes('watch.php')) {
                        url('https://odnoklassniki.ru/videoembed/' + _0x3d6713.split('watch.php')[1]);
                    }
                } else {
                    if (_0x3d6713.includes('up')) {
                        if (_0x3d6713.includes('watch.php')) {
                            url('https://uptostream.com/iframe/' + _0x3d6713.split('watch.php')[1]);
                        }
                    } else {
                        if (_0x3d6713.includes('fembed')) {
                            if (_0x3d6713.includes('watch.php')) {
                                url('https://www.fembed.net/v/' + _0x3d6713.split('watch.php')[1]);
                            }
                        }
                    }
                }
            }
        } catch (_0x50585f) {
            _0x3d6713 = _0x3d6713.match(/<iframe.*?src\s*=\s*(?:"|\')(.*?)(?:"|\')/)[1];
            _0x3d6713 = fixUrl(_0x3d6713);
            if (_0x3d6713.includes('ashortl')) {
                _0x3d6713 = fetch(_0x3d6713);
                _0x3d6713 = _0x3d6713.match(/<iframe.*?src\\s*=\\s*(?:\"|\\')(.*?)(?:\"|\\')/)[1];
            }
            url(_0x3d6713);
        }
        parser();
    }
}
function hdfilmcehennemi2() {
    url(url('syrtrk', ''));
    var _0x1f39e4 = url();
    headers('User-Agent', 'Cloudflare');
    try {
        var _0xfdf084 = fetch();
    } catch (_0x434eb1) {
        if (g.isWebView()) {
            Core.saveCookie(url(), 'on_dz', 'description');
            var _0xfdf084 = Core.pageContent.split("\n").join('');
        } else {
            error('');
        }
    }
    _0xfdf084 = _0xfdf084.match(/<nav\s*class="video-alternatives">(.*?)player-container/)[1];
    var _0x3113b9 = {
        _0x27c7af: _0x24e4f6
    };
    var _0x870c = '';
    try {
        var _0x1b4034 = matchAll(_0xfdf084, /<div\s*class="alternative-links".*?data-lang="(.*?)">/g);
        var _0x3606f4 = _0xfdf084.split("class=\"alternative-links");
        for (let _0x43d0f6 = 0; _0x43d0f6 < _0x1b4034.length; _0x43d0f6++) {
            _0xfdf084 = _0x3606f4[_0x43d0f6 + 1];
            var _0xaa5733 = matchAll(_0xfdf084, /data-video="(.*?)">(.*?)<\/button>/g);
            for (let _0x4611a5 = 0; _0x4611a5 < _0xaa5733.length; _0x4611a5++) {
                var _0x27c7af = _0xaa5733[_0x4611a5][2].replace(/ /g, '');
                var _0x24e4f6 = _0xaa5733[_0x4611a5][1];
                _0x870c = _0x24e4f6;
                if (_0x1b4034[_0x43d0f6][1] == 'dual') {
                    _0x27c7af = _0x27c7af + " - AltyazÄ± & Dublaj";
                } else {
                    if (_0x1b4034[_0x43d0f6][1] == 'tr') {
                        _0x27c7af = _0x27c7af + ' - Dublaj';
                    } else {
                        _0x27c7af = _0x27c7af + " - AltyazÄ±";
                    }
                }
                ;
            }
        }
    } catch (_0x64a74) {
        error(_0x64a74.message);
    }
    if (!_0x1f39e4.includes('#') && JSON.stringify(_0x3113b9) != '{}' && Object.keys(_0x3113b9).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x3113b9));
    } else {
        if (_0x1f39e4.includes('#')) {
            url(_0x1f39e4.split('#')[1]);
        } else {
            url(_0x870c);
        }
        headers('X-Requested-With', 'fetch');
        _0xfdf084 = fetch(baseUrl(_0x1f39e4) + '/video/' + url() + '/');
        _0xfdf084 = _0xfdf084.match(/<iframe.*?data-src=\\"(.*?)\\"/)[1].replace(/\\\//g, '/');
        var _0x30f3f2 = [];
        if (_0xfdf084.includes('player')) {
            _0xfdf084 = fetch(_0xfdf084);
            url('eval(' + _0xfdf084.match(/eval\((.*?)\{\}\)\)/)[1] + '{}))');
            _0xfdf084 = _0xfdf084.match(/tracks:\s*(.*?\]),/)[1];
            _0x30f3f2 = [];
            try {
                var _0xaa5733 = matchAll(_0xfdf084, /"file":"(.*?)"/g);
                for (let _0x53c3fd = 0; _0x53c3fd < _0xaa5733.length; _0x53c3fd++) {
                    var _0x30268b = '';
                    if (_0xaa5733[_0x53c3fd][1].includes('Turkish')) {
                        _0x30268b = 'tr';
                    } else {
                        if (_0xaa5733[_0x53c3fd][1].includes('English')) {
                            _0x30268b = 'en';
                        }
                    }
                    if (_0x30268b != '') {
                        var _0x5d8ac0 = _0xaa5733[_0x53c3fd][1].replace(/\\\//g, '/') + '/';
                        var _0x4b155a = {
                            'lang': _0x30268b,
                            'url': baseUrl(_0x1f39e4) + _0x5d8ac0
                        };
                        _0x30f3f2.push(_0x4b155a);
                    }
                }
            } catch (_0x3b6f4e) { }
            _0xfdf084 = unPack(url()).replace("var file_link=\"", '').replace("\";", '');
            url(g.base64Decode(_0xfdf084));
            g.deleteHeader('X-Requested-With');
        } else {
            if (_0xfdf084.includes('video/embed')) {
                headers('Referer', _0xfdf084);
                _0xfdf084 = fetch(_0xfdf084);
                _0x30f3f2 = [];
                try {
                    var _0xaa5733 = matchAll(_0xfdf084, /<track\s*src="(.*?\.vtt)".*?label="(.*?)"/g);
                    for (let _0x5a852d = 0; _0x5a852d < _0xaa5733.length; _0x5a852d++) {
                        var _0x30268b = '';
                        if (_0xaa5733[_0x5a852d][2].includes('Turkish')) {
                            _0x30268b = 'tr';
                        }
                        if (_0x30268b != '') {
                            var _0x5d8ac0 = _0xaa5733[_0x5a852d][1].replace(/\\\//g, '/');
                            var _0x4b155a = {
                                'lang': _0x30268b,
                                'url': baseUrl(headers.Referer) + _0x5d8ac0
                            };
                            _0x30f3f2.push(_0x4b155a);
                        }
                    }
                } catch (_0x29dd95) { }
                url('eval(' + _0xfdf084.match(/eval\((.*?)\{\}\)\)/)[1] + '{}))');
                _0xfdf084 = unPack(url()).replace("var file_link=\"", '');
                url(_0xfdf084.match(/"(aHR0c.*?)"/)[1]);
                url(atob(url()));
            }
        }
        sub(JSON.stringify(_0x30f3f2));
        parser();
    }
}
function hdfilmcehennemi3() {
    var _0x32f1df = url().split('?l=');
    url(_0x32f1df[0]);
    var _0x52ff37 = 'en';
    if (g.getLang() == 0 || _0x32f1df[1] == '1') {
        _0x52ff37 = 'tr';
    } else {
        if (g.getLang() == 2 || _0x32f1df[1] == '2') {
            _0x52ff37 = 'dual';
        }
    }
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x3d8829 = matchAll(fetch(), /let\s*parts\s*=\s*(.*?);/g);
    url('');
    if (_0x3d8829.length > 0) {
        var _0x38a680 = JSON.parse(_0x3d8829[0][1]);
        for (var _0x14d76c = 0; _0x14d76c < _0x38a680.length; _0x14d76c++) {
            if (_0x38a680[_0x14d76c].lang == _0x52ff37) {
                url(_0x38a680[_0x14d76c].data.match(/iframe\s*src="(.*?)"/)[1]);
                break;
            }
        }
    } else {
        url(_0x3d8829.match(/<iframe\s*src="(.*?)"/)[1]);
    }
    parser();
}
function hdtoday() {
    if (!g.isWebView()) {
        error('No Webview');
    }
    var _0x314f4d = url().split('#')[0];
    headers('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36');
    var _0x577214 = '';
    if (_0x314f4d.includes('/movie/')) {
        _0x314f4d = _0x314f4d.replace('/movie/', '/watch-movie/');
    } else {
        if (_0x314f4d.includes('/tv/')) {
            _0x314f4d = _0x314f4d.replace('/tv/', '/watch-tv/').replace('-full-', '-hd-').replace('.se', '.tv');
            _0x577214 = _0x314f4d.split('.')[_0x314f4d.split('.').length - 1];
            _0x577214 = _0x577214.split('/')[0];
            var _0x274c86 = fetch(baseUrl(_0x314f4d) + '/ajax/episode/servers/' + _0x577214);
            _0x314f4d = _0x314f4d.replace(_0x577214, _0x274c86.match(/<a data-id="(.*?)"/)[1]).split('/sezon')[0];
        }
    }
    if (!url().includes('#')) {
        getWebViewOwnContentJS(_0x314f4d, 'aW5kZXgubTN1OA==.m3u8', 'embed', 'jw-icon jw-icon-display jw-button-color jw-reset', '', 0, false, g.getHeadersJSON(), 2);
    } else {
        var _0x2a0217 = url().split('#')[2];
        url(url().split('#')[1]);
        if (_0x2a0217.includes('http')) {
            headers('x-requested-with', 'XMLHttpRequest');
            var _0x7f597d = _0x2a0217.split('/')[_0x2a0217.split('/').length - 1];
            _0x7f597d = _0x7f597d.split('?')[1];
            var _0xda76b5 = fetch(_0x2a0217);
            var _0x28b321 = [];
            try {
                var _0x3d38d6 = JSON.parse(_0xda76b5);
                var _0x249b38 = _0x3d38d6.tracks;
                for (var _0x10bd3d = 0; _0x10bd3d < _0x249b38.length; _0x10bd3d++) {
                    var _0x4e3910 = _0x249b38[_0x10bd3d];
                    if (_0x4e3910.hasOwnProperty('label') && _0x4e3910.hasOwnProperty('file')) {
                        if (_0x4e3910.label.includes('English') || _0x4e3910.label.includes('German') || _0x4e3910.label.includes('Turkish')) {
                            var _0x2d1a12 = 'Eng';
                            if (_0x4e3910.label.includes('German')) {
                                _0x2d1a12 = 'Ger';
                            } else {
                                if (_0x4e3910.label.includes('Turkish')) {
                                    _0x2d1a12 = 'Tur';
                                }
                            }
                            var _0x468fc9 = {
                                'lang': _0x2d1a12,
                                'url': _0x4e3910.file
                            };
                            _0x28b321.push(_0x468fc9);
                        }
                    }
                }
            } catch (_0x1481db) { }
            var _0x3f4540 = '';
            var _0x2919e1 = '';
            var _0x1e9ca9 = '';
            for (var _0x10bd3d = 0; _0x10bd3d < _0x28b321.length; _0x10bd3d++) {
                if (_0x28b321[_0x10bd3d].lang == 'Tur') {
                    _0x3f4540 = _0x28b321[_0x10bd3d].url;
                } else {
                    if (_0x28b321[_0x10bd3d].lang == 'Eng') {
                        _0x2919e1 = _0x28b321[_0x10bd3d].url;
                    } else {
                        if (_0x28b321[_0x10bd3d].lang == 'Ger') {
                            _0x1e9ca9 = _0x28b321[_0x10bd3d].url;
                        }
                    }
                }
            }
            var _0x8a416e = [];
            if (_0x3f4540 == '') {
                var _0x46dd68 = '';
                if (_0x2919e1 != '') {
                    _0x46dd68 = _0x2919e1;
                } else {
                    if (_0x1e9ca9 != '') {
                        _0x46dd68 = _0x1e9ca9;
                    }
                }
                if (_0x46dd68 != '') {
                    headers('forHelper', 'c2V5L3RyYW5zbGF0ZS8');
                    headers('forHelper2', 'Q2V2aXJpQUkuVFIucGhwP3VybD0');
                    var _0x2e64bd = 'm_';
                    if (_0x577214 != '') {
                        _0x2e64bd = 't_';
                    }
                    sub(subHelp(_0x46dd68, _0x2e64bd + 'hd'));
                    if (_0x2919e1 != '') {
                        var _0x2a0217 = {
                            lang: 'en',
                            url: _0x2919e1
                        };
                        ;
                        ;
                        _0x8a416e = JSON.parse(sub());
                        _0x8a416e.push(_0x2a0217);
                        sub(JSON.stringify(_0x8a416e));
                    }
                }
            } else {
                sub(_0x3f4540);
            }
            parser();
        }
    }
}
function imdb() {
    url(url() + '/');
    var _0x4d45d6 = fetch().match(/"embedUrl"\s*:\s*"(.*?)"/)[1];
    var _0x2336db = _0x4d45d6.replace('video/imdb', 'videoembed');
    if (!_0x2336db.startsWith('https')) {
        _0x2336db = 'https://imdb.com' + _0x2336db;
    }
    var _0x3a9a9e = fetch(_0x2336db);
    try {
        var _0x44704b = matchAll(_0x3a9a9e, /"videoUrl":"(.*?)"},{"definition":"(.*?)"/);
        if (_0x44704b.length > 0) {
            for (var _0x7a0c14 = 0; _0x7a0c14 < _0x44704b.length; _0x7a0c14++) { }
        } else {
            throw Error();
        }
    } catch (_0x5d0d27) {
        var _0x212825 = JSON.parse(_0x3a9a9e.match(/videoEmbedPlaybackData":(.*?)}],/)[1] + '}]}');
        url(_0x212825.playbackURLs[0].url);
    }
    parser();
}
function istanbuluseyret() {
    var _0x35c877 = fetch();
    _0x35c877 = _0x35c877.match(/"dataProvider":(.*?\}),/)[1];
    var _0x22ac5d = JSON.parse(_0x35c877);
    url(_0x22ac5d.source[0].url);
    parser();
}
function sinefil() {
    var _0x3d4ace = url();
    var _0x49dcf3 = fetch();
    var _0xbf90c8 = {};
    try {
        var _0x34aabd = matchAll(_0x49dcf3, /<iframe.*?src="(.*?)".*?<\/iframe>/g);
        var _0x845f4c = 1;
        for (let _0x252470 = 0; _0x252470 < _0x34aabd.length; _0x252470++) {
            if (!_0x34aabd[_0x252470][1].includes('youtube')) {
                _0xbf90c8['Alternatif ' + _0x845f4c] = _0x34aabd[_0x252470][1];
                _0x845f4c++;
            }
        }
        if (Object.keys(_0xbf90c8).length < 1) {
            throw new Error('Telif');
        }
    } catch (_0x3663fc) {
        if (!_0x49dcf3.includes('allowfullscreen') && _0x49dcf3.toLowerCase().includes('telif nedeniyle')) {
            error('Telif');
        } else {
            error(_0x3663fc.message);
        }
    }
    if (!_0x3d4ace.includes('#') && _0xbf90c8.length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0xbf90c8));
    } else {
        if (_0xbf90c8.length > 1) {
            url(_0x3d4ace.split('#')[1]);
        } else {
            url(_0xbf90c8[Object.keys(_0xbf90c8)[0]]);
        }
        parser();
    }
}
function jetfilm() {
    var _0x7cf8e8 = url();
    headers('User-Agent', 'cloudflare');
    var _0x85bf1b = fetch().match(/film_part(.*?)(?:pbgiris|iframe)/)[1];
    consolelog(_0x85bf1b);
    var _0x5e499c = matchAll(_0x85bf1b, /<span>(.*?)<\/span>/g);
    var _0x23a893 = matchAll(_0x85bf1b, /href="(.*?)"/g);
    var _0x1bc318 = {};
    var _0x6ec6ca = ['vupload', 'letsupload', 'jetplay', 'mail', 'aparat', 'vidmoly', 'mixplay', 'jetv.xyz', 'platin', 'moly', 'okru', 'vk', 'jet', 'seg', 'one', 'tr-en', 'yx', 'trp', 'dood', 'fmoon'];
    _0x23a893.unshift(['', url()]);
    for (let _0x1357bb = 0; _0x1357bb < _0x5e499c.length; _0x1357bb++) {
        if (_0x6ec6ca.indexOf((_0x5e499c[_0x1357bb][1] + '').toLowerCase()) !== -1) {
            _0x1bc318[_0x5e499c[_0x1357bb][1]] = _0x23a893[_0x1357bb][1];
        }
    }
    if (!_0x7cf8e8.includes('#') && Object.keys(_0x1bc318).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x1bc318));
    } else {
        url(url().split('#')[1]);
        _0x85bf1b = fetch();
        try {
            url(_0x85bf1b.match(/<iframe.*?data(?:-litespeed|)-src=["|'](.*?)['|"]\s*(?:width|frame|)/)[1]);
        } catch (_0x2eda2f) {
            try {
                url(_0x85bf1b.match(/<iframe.*?data-src="(.*?)"/)[1]);
            } catch (_0x5e5180) {
                try {
                    url(_0x85bf1b.match(/iframe src='(.*?)'/)[1]);
                } catch (_0xc7335e) {
                    url(_0x85bf1b.match(/class="film_partiframe src='(.*?)'/)[1]);
                }
            }
        }
        if (!url(['mixdrop', 'videobin', 'upstream', 'vidmoly', 'ok.ru', 'odnoklassniki', 'vk.com', 'dood', 'trstx'], 1)) {
            if (url(['jetv.xyz/yx'], 1)) {
                var _0x43ed5e = 'vars=' + url().match(/id=(.*?)$/)[1];
                _0x85bf1b = fetchPost(_0x43ed5e, 'https://jetv.xyz/yx/api.php');
                url(_0x85bf1b.match(/file:.*?"(.*?)",/)[1]);
            } else {
                _0x85bf1b = fetch();
                if (url(['jtfi'], 1)) {
                    try {
                        url(_0x85bf1b.match(/"hls","file":"(.*?)"/)[1].replace(/\\/g, ''));
                    } catch (_0x45a717) {
                        _0x85bf1b = matchAll(_0x85bf1b, /"file":"(.*?)"/g);
                        url(_0x85bf1b[_0x85bf1b.length - 1][1].replace(/\\/g, '') + '#.mp4');
                    }
                    headers('Referer', url());
                    headers('Range', 'bytes=0-');
                } else {
                    if (url(['oneupload', 'segavid'], 1)) {
                        url(fetch().match(/file:"(.*?)"/)[1]);
                    } else {
                        if (url(['jetv.xyz'], 1)) {
                            if (_0x85bf1b.includes('jetESources')) {
                                var _0x454606 = {
                                    iv: '987654jetfilmcom',
                                    s: '987654321jetfilm',
                                    ct: _0x85bf1b.match(/jetESources\s*=\s*"(.*?)";/)[1]
                                };
                                url(getAes('', JSON.stringify(_0x454606)));
                            } else {
                                if (_0x85bf1b.includes('Contents =')) {
                                    url(url());
                                } else {
                                    if (_0x85bf1b.includes("\"label\":")) {
                                        consolelog('label');
                                    } else {
                                        if (_0x85bf1b.includes('m3u8')) {
                                            consolelog('m3u8');
                                        } else {
                                            if (_0x85bf1b.includes('src=')) {
                                                consolelog('src=');
                                            } else {
                                                consolelog('other');
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            _0x85bf1b = _0x85bf1b.match(/"?file"? ?: ?"([^"]+)", ?"(?:type|label)": ?"([^"]+)"/);
                        }
                    }
                }
            }
        }
        consolelog('Test: ' + url());
        url(fixUrl(url()));
        parser();
    }
}
function kanal7() {
    if (url(['canli-izle'], 1)) {
        url(fetch().match(/hls:\s*'(.*?)'/)[1]);
    } else {
        var _0x30bacf = fetch().match(/<iframe.*?src="(https:\/\/www.izle7.com\/.*?)"/)[1];
        _0x30bacf = fetch(_0x30bacf).match(/play_video\s*=\s*"(.*?)"/)[1];
        url('https://www.dailymotion.com/embed/video/' + _0x30bacf);
    }
    parser();
}
function kanalb() {
    url('https://baskentaudiovideo.xyz/LiveApp/streams/' + fetch().match(/\?name=(.*?)"/)[1] + '.m3u8');
    parser(url(), 1, '', '', false);
}
function kanald() {
    if (url(['canli-yayin'], 0)) {
        var _0x27cd7a = fetch();
        url(_0x27cd7a.match(/data-url="(.*?)"/)[1].replace('https://media.duhnet.tv', ''));
        parser();
    } else {
        var _0x27cd7a = fetch();
        _0x27cd7a = _0x27cd7a.match(/\/embed\/(.*?)">/)[1];
        _0x27cd7a = fetch('https://www.kanald.com.tr/actions/media?id=' + _0x27cd7a + '&p=1&pc=1');
        var _0x355fd0 = JSON.parse(_0x27cd7a);
        var _0x32da00 = _0x355fd0.data.media.link.securePath;
        if (_0x355fd0.data.media.link.hasOwnProperty('serviceUrl')) {
            if (!_0x32da00.startsWith('/') && !_0x32da00.startsWith('http')) {
                _0x32da00 = _0x355fd0.data.media.link.serviceUrl + '/' + _0x355fd0.data.media.link.securePath;
            } else {
                _0x32da00 = _0x355fd0.data.media.link.serviceUrl + '' + _0x355fd0.data.media.link.securePath;
            }
        }
        url(_0x32da00);
        parser();
    }
}
function koreanturk() {
    var _0x580cc8 = url();
    var _0x3c8a65 = fetch();
    _0x3c8a65 = matchAll(_0x3c8a65.match(/tab-content icerikler(.*?)text\/css/)[1], /id="(.*?)".*?(?:iframe.*?src|a.*?href)="(.*?)"/g);
    streamUrls = {};
    for (let _0x40131e = 0; _0x40131e < _0x3c8a65.length; _0x40131e++) {
        if (!_0x3c8a65[_0x40131e][1].includes('guard') && !_0x3c8a65[_0x40131e][1].includes('mega')) {
            streamUrls[_0x3c8a65[_0x40131e][1]] = _0x3c8a65[_0x40131e][2];
        }
    }
    if (!_0x580cc8.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    } else {
        url(url().split('#')[1]);
        parser();
    }
}
function kultfilmler() {
    var _0x2a2e03 = url();
    var _0x51773f = fetch();
    _0x51773f = _0x51773f.match(/player-control(.*?)button\s*report-button\s*trigger/)[1];
    var _0x326d54 = {};
    try {
        var _0x3fb73 = matchAll(_0x51773f, /part-name">(.*?)<.*?"part-lang">(.*?)<\/div/g);
        var _0x59354f = 0;
        for (let _0x385cfd = 0; _0x385cfd < _0x3fb73.length; _0x385cfd++) {
            var _0x2030e0 = url();
            if (_0x59354f > 0) {
                _0x2030e0 = url() + (_0x59354f + 1) + '/';
            }
            var _0x374da9 = 'cc';
            if (g.getLang() == 1) {
                _0x374da9 = 'tr';
            }
            if (_0x3fb73[_0x385cfd][2].includes(_0x374da9) || _0x374da9 != 1 && _0x3fb73[_0x385cfd][2].length < 2) {
                if (!_0x3fb73[_0x385cfd][1].includes('KULTPlayer')) {
                    _0x326d54[_0x3fb73[_0x385cfd][1]] = _0x2030e0;
                }
            }
            _0x59354f++;
        }
    } catch (_0x24eb31) {
        error(_0x24eb31.message);
    }
    if (!_0x2a2e03.includes('#') && _0x326d54.length > 0) {
        Core.showAlternatesJS(JSON.stringify(_0x326d54));
    } else {
        if (_0x2a2e03.includes('#')) {
            url(_0x2a2e03.split('#')[1]);
        } else {
            url(_0x2a2e03);
        }
        _0x51773f = fetch();
        url(_0x51773f.match(/new ContentManager\(.*?, "(.*?)", 10\);/)[1]);
        url(atob(url()));
        url(url().match(/iframe.*?src="(.*?)"/)[1]);
        url(fixUrl(url()));
        if (url(['yildizkisafilm'], 0)) {
            var _0x166a02 = url().split('/')[url().split('/').length - 1];
            url('https://yildizkisafilm.org/player/index.php?data=' + _0x166a02 + '&do=getVideo');
            var _0x2227d2 = 'hash=' + _0x166a02 + '&r=';
            headers('X-Requested-With', 'XMLHttpRequest');
            try {
                _0x51773f = fetch();
                sub(_0x51773f.match(/var playerjsSubtitle = "\[TÃ¼rkÃ§e\](.*?\.srt)";/)[1]);
            } catch (_0x26b7c1) {
                sub('');
            }
            g.logHeader();
            var _0x28199a = fetchPost(_0x2227d2);
            try {
                _0x2227d2 = JSON.parse(_0x28199a);
                url(_0x2227d2.securedLink);
                parser();
            } catch (_0x8d5bdb) {
                error(_0x8d5bdb.message);
            }
        } else {
            parser();
        }
    }
}
function mailru() {
    url(fixUrl(url()));
    var _0xd12e47 = matchAll(fetch(), /"video":(.*?),/g);
    var _0x63e6bd = '';
    for (let _0x2a6c80 = 0; _0x2a6c80 < _0xd12e47.length; _0x2a6c80++) {
        _0x63e6bd = 'https://my.mail.ru' + _0xd12e47[_0x2a6c80][1].split("\"").join('').split('{').join('').split(':').join('').split('metadataUrl').join('').split(' ').join('');
    }
    var _0x48b836 = JSON.parse(fetch(_0x63e6bd)).videos;
    var _0x569706 = ['360p', '480p', '720p', '1080p', 'Alone'];
    var _0x439288 = -1;
    var _0x5ab25b = -1;
    for (let _0x35dd4e = 0; _0x35dd4e < _0x48b836.length; _0x35dd4e++) {
        try {
            var _0x476172 = _0x569706.indexOf(_0x48b836[_0x35dd4e].key);
            if (_0x439288 < _0x476172) {
                _0x439288 = _0x476172;
                _0x5ab25b = _0x35dd4e;
            }
        } catch (_0x55904b) { }
    }
    if (_0x439288 != -1) {
        url(fixUrl(_0x48b836[_0x5ab25b].url));
    }
    parser();
}
function mixdrop() {
    url(fixUrl(url()));
    var _0x3ce7e2 = fetch();
    _0x3ce7e2 = unPack(_0x3ce7e2.match(/(eval\(function\(p,a,c,k,e,d.*?)<\/script>/)[1]);
    var _0x551f87 = /MDCore.wurl="(.*?)"/;
    if (url(['luluvdo'], 1)) {
        _0x551f87 = /file:"(.*?)"/;
    } else {
        if (url(['streamhub'], 1)) {
            _0x551f87 = /src:"(.*?)"/;
        }
    }
    url(fixUrl(_0x3ce7e2.match(_0x551f87)[1]));
    parser();
}
function movies123() {
    if (!g.isWebView()) {
        error('No Webview');
    }
    var _0x5746b9 = url().split('#')[0];
    headers['User-Agent'] = 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36';
    var _0x1f2e8c = -1;
    getWebViewOwnContentJS(_0x5746b9, 'site/hls', 'I AM NOT LOOKING', 'play-now', '', _0x1f2e8c, false, g.getHeadersJSON, 0);
}
function movie4k() {
    var _0x1f0b9c = url();
    var _0x451b1d = fetch();
    var _0x19bd3b = {};
    try {
        var _0x300caa = matchAll(_0x451b1d, /class="tablinks"\s*href="#"\s*data-link="(.*?)">(.*?)</g);
        var _0x4cdf3f = 1;
        for (let _0x3c576a = 0; _0x3c576a < _0x300caa.length; _0x3c576a++) {
            if (!_0x300caa[_0x3c576a][2].includes('Player HD') && !_0x300caa[_0x3c576a][2].includes('Trailer') && !_0x300caa[_0x3c576a][2].includes('Server 4K') && !_0x300caa[_0x3c576a][2].includes('Goodstream')) {
                _0x19bd3b[_0x300caa[_0x3c576a][2]] = _0x300caa[_0x3c576a][1];
                _0x4cdf3f++;
            }
        }
    } catch (_0x32221b) {
        error(_0x32221b.message);
    }
    if (!_0x1f0b9c.includes('#') && JSON.stringify(_0x19bd3b) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x19bd3b));
    } else {
        url(_0x1f0b9c.split('#')[1]);
        url(fixUrl(url()));
        if (url(['goodstream'], 1)) { } else {
            parser();
        }
    }
}
function nowtv() {
    if (url(['canli-yayin'], 0)) {
        var _0x7b152f = fetch();
        ;
        url(_0x7b152f.match(/(?:videoSrc|daiUrl)\s*:\s*'(.*?)'/)[1]);
        parser();
    } else {
        var _0x7b152f = fetch();
        url(_0x7b152f.match(/'(https:\/\/nowtv-.*?)'/)[1]);
        parser();
    }
}
function myvideoaz() {
    url(fetch().match(/application\/x-mpegURL"\s*src="(.*?)"/)[1]);
    parser();
}
function okru() {
    url(fixUrl(url()));
    headers('User-Agent', 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0');
    var _0x624b7e = url().match(/https?:\/\/(?:www.)?(?:odnoklassniki|ok).ru\/(?:videoembed\/|dk\?cmd=videoPlayerMetadata&mid=)(\d+)/)[1];
    var _0xc5eb5b = JSON.parse(fetchPost('cmd=videoPlayerMetadata&mid=' + _0x624b7e, 'http://www.ok.ru/dk'));
    _0xc5eb5b = _0xc5eb5b.videos;
    var _0xda31b1 = ['mobile', 'lowest', 'low', 'sd', 'hd', 'full'];
    var _0x34b9d1 = -1;
    var _0x121aad = -1;
    for (let _0x1c4f89 = 0; _0x1c4f89 < _0xc5eb5b.length; _0x1c4f89++) {
        try {
            var _0x8c539 = _0xda31b1.indexOf(_0xc5eb5b[_0x1c4f89].name);
            if (_0x34b9d1 < _0x8c539) {
                _0x34b9d1 = _0x8c539;
                _0x121aad = _0x1c4f89;
            }
        } catch (_0x544146) { }
    }
    if (_0x34b9d1 != -1) {
        url(_0xc5eb5b[_0x121aad].url + '#.mp4');
        headers('Referer', 'https://odnoklassniki.ru/');
    }
    parser();
}
function oneupload() {
    var _0x755307 = fetch();
    url(_0x755307.match(/\[\{file:"(.*?)"/)[1]);
    parser();
}
function onlinedizi() {
    var _0x38bf5b = url();
    var _0x362a3b = 'episode-buttons';
    if (url(['/film/'], 1)) {
        _0x362a3b = 'active';
        if (!url(['izle'], 1)) {
            url(url() + '/turkce-altyazi-izle');
        }
    }
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x528e3c = fetch();
    try {
        _0x528e3c = _0x528e3c.match(/Alternatif(.*?)episode-buttons/)[1];
    } catch (_0x585c1a) { }
    var _0x31fd16 = {
        _0x3959b9: _0x3dfb46
    };
    try {
        var _0x330486 = matchAll(_0x528e3c, /href="(.*?)".*?>(.*?)</g);
        for (let _0x378dee = 0; _0x378dee < _0x330486.length; _0x378dee++) {
            var _0x3dfb46 = _0x330486[_0x378dee][1];
            var _0x3959b9 = _0x330486[_0x378dee][2];
            ;
        }
    } catch (_0x5a79f3) {
        error(_0x5a79f3.message);
    }
    if (Object.keys(_0x31fd16).length == 0 || Object.keys(_0x31fd16).length > 8) {
        error('Onlinedizi 1371');
    }
    if (!_0x38bf5b.includes('#') && JSON.stringify(_0x31fd16) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x31fd16));
    } else {
        url(_0x38bf5b.split('#')[1]);
        _0x528e3c = fetch();
        url(_0x528e3c.match(/iframe\s*src="(.*?)"/)[1]);
        if (!url().startsWith('http')) {
            url(baseUrl(_0x38bf5b) + url());
        }
        _0x528e3c = fetch();
        var _0x216118 = _0x528e3c.match(/ifsrc = "(.*?)"/)[1];
        if (!_0x216118.startsWith('http')) {
            _0x216118 = 'https:' + _0x216118;
        }
        url(Core.getRedirectUrlJS(_0x216118));
        if (url() == '') {
            error('Onlinedizi 1393');
        } else {
            if (url(['gdplayer'], 1)) {
                _0x528e3c = fetch();
                url('https:' + _0x528e3c.match(/(\/\/gdplayer.org\/api\/.*?)"/)[1]);
                _0x528e3c = fetch();
                try {
                    var _0x505280 = JSON.parse(_0x528e3c);
                    url(_0x505280.sources[0].file);
                } catch (_0x3ccb07) {
                    error(_0x3ccb07.message);
                }
            } else {
                if (url(['fscdn.xyz'], 1)) {
                    var _0x158b31 = url().split('/')[4];
                    var _0x52d073 = url() + '?do=getVideo';
                    var _0x18e648 = 'hash=' + _0x158b31 + '&r=' + baseUrl(_0x38bf5b) + '&s=';
                    url(_0x52d073);
                    headers('content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    headers('x-requested-with', 'XMLHttpRequest');
                    _0x528e3c = fetchPost(_0x18e648);
                    try {
                        var _0x505280 = JSON.parse(_0x528e3c);
                        _0x52d073 = _0x505280.videoSources[0].file;
                        url(_0x52d073);
                        if (_0x52d073.includes('fcdn')) {
                            _0x528e3c = fetch();
                            url('');
                            try {
                                var _0x330486 = matchAll(_0x528e3c, /sticon-film"><\/span><b>(.*?)</g);
                                for (let _0x865c0d = 0; _0x865c0d < alts_raw.length; _0x865c0d++) {
                                    if (url() == '') {
                                        url(_0x330486[_0x865c0d][1]);
                                    } else {
                                        if (_0x330486[_0x865c0d][1].includes('1080p/playlist')) {
                                            url(_0x330486[_0x865c0d][1]);
                                        } else {
                                            if (_0x330486[_0x865c0d][1].includes('720p/playlist') && !url.includes('1080p/playlist')) {
                                                url(_0x330486[_0x865c0d][1]);
                                            } else {
                                                if (_0x330486[_0x865c0d][1].includes('480p/playlist') && !url.includes('1080p/playlist') && !url.includes('720p/playlist')) {
                                                    url(_0x330486[_0x865c0d][1]);
                                                } else {
                                                    if (_0x330486[_0x865c0d][1].includes('360p/playlist') && !url.includes('1080p/playlist') && !url.includes('720p/playlist') && !url.includes('480p/playlist')) {
                                                        url(_0x330486[_0x865c0d][1]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (_0x4af6ee) { }
                        }
                        g.deleteHeader('content-type');
                        g.deleteHeader('x-requested-with');
                        headers('Referer', 'https://yandex.ru');
                    } catch (_0xddb21d) {
                        error(_0xddb21d.message);
                    }
                } else {
                    if (url(['ondembed.xyz'], 1)) {
                        url('ondembed.xyz', 'fembed.com');
                    }
                }
            }
        }
        parser();
    }
}
function onlineradiobox() {
    url(fetch().match(/stream="(.*?)"/)[1]);
    g.setMediaType('mp3');
    parser();
}
function parsatv() {
    var _0x584fc2 = fetch();
    try {
        url(_0x584fc2.match(/file:\s*"(.*?)"/)[1]);
    } catch (_0x19f552) {
        url(_0x584fc2.match(/<iframe.*src="(.*embed.*)"/)[1]);
    }
    if (url(['youtube'], 1)) {
        url(url().match(/youtube-nocookie.com\/embed\/(.*?)\?/)[1]);
        url('https://m.youtube.com/watch?v=' + url());
    }
    parser();
}
function pokitv() {
    var _0x44c1cd = url();
    var _0x440aee = fetch();
    url(_0x440aee.match(/<iframe.*?data-litespeed-src="(.*?)"/)[1]);
    var _0xb9b53b = url().split('/')[5];
    headers('X-Requested-With', 'XMLHttpRequest');
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    _0x440aee = fetchPost('hash=' + _0xb9b53b + '&r=' + _0x44c1cd + '&s=', url() + '?do=getVideo');
    var _0x1a89ce = JSON.parse(_0x440aee);
    if (_0x1a89ce.hasOwnProperty('videoSources')) {
        url(_0x1a89ce.videoSources[0].file);
    } else {
        url(_0x1a89ce.videoSrc);
        _0x440aee = fetch();
        url(_0x440aee.match(/source.*?src=\"(.*?)\"/)[1]);
    }
    parser();
}
function pornhub() {
    var _0x3f2d34 = matchAll(fetch(), /var flashvars_.*?=\s*(\{.*?});/g);
    var _0x3c737c = {};
    for (let _0x48f3b4 = 0; _0x48f3b4 < _0x3f2d34.length; _0x48f3b4++) {
        try {
            var _0x342e9c = JSON.parse(_0x3f2d34[_0x48f3b4][1]);
            var _0x502d9a = _0x342e9c.defaultQuality;
            for (let _0x28385e = 0; _0x28385e < _0x502d9a.length; _0x28385e++) {
                var _0x4888ed = _0x342e9c.mediaDefinitions;
                for (let _0x19260a = 0; _0x19260a < _0x4888ed.length; _0x19260a++) {
                    if (_0x502d9a[_0x28385e] == _0x4888ed[_0x19260a].quality) {
                        _0x3c737c[_0x502d9a[_0x28385e]] = _0x4888ed[_0x19260a].videoUrl;
                    }
                }
            }
        } catch (_0x1ba5f9) { }
    }
    var _0x4a2172 = ['240', '360', '480', '720', '1080', 'Alone'];
    var _0x167f56 = -1;
    var _0x2963d9 = -1;
    for (let _0x1c6c8c = 0; _0x1c6c8c < Object.keys(_0x3c737c).length; _0x1c6c8c++) {
        try {
            var _0xcb7edb = Object.keys(_0x3c737c)[_0x1c6c8c];
            var _0x1bb19f = _0x4a2172.indexOf(_0xcb7edb);
            if (_0x167f56 < _0x1bb19f) {
                _0x167f56 = _0x1bb19f;
                _0x2963d9 = _0xcb7edb;
            }
        } catch (_0x3ac4fc) { }
    }
    if (_0x167f56 != -1) {
        url(fixUrl(_0x3c737c[_0x2963d9].replace(/\\/g, '')));
    }
    parser();
}
function puhutv() {
    url(fixUrl(url()));
    var _0x4f043d = fetch().match(/movieAssets".*?"video_id":"(PUHU_.*?)"/)[1];
    url('https://dygvideo.dygdigital.com/api/video_info?akamai=true&PublisherId=29&ReferenceId=' + _0x4f043d + '&SecretKey=NtvApiSecret2014');
    _0x4f043d = fetch();
    var _0x58be68 = JSON.parse(_0x4f043d);
    try {
        url(_0x58be68.data.flavors['0'].file_url_1);
    } catch (_0x332c1c) {
        url(_0x58be68.data.flavors.hls);
    }
    parser();
}
function radyodelisi() {
    var _0xd2fdbb = fetch();
    url(_0xd2fdbb.match(/source src=\"(.*?)\"/)[1]);
    parser();
}
function radyohome() {
    var _0x3fa7ab = matchAll(fetch(), /"hls","url":"(.*?)"/g);
    consolelog(_0x3fa7ab.length, 'Test');
    url(_0x3fa7ab[_0x3fa7ab.length - 1][1].replace(/\\/g, ''));
    parser();
}
function realfilmizle() {
    var _0x47f670 = fetch().toLowerCase();
    var _0x34e15c = _0x47f670.match(/iframe.*?src="(https.*?)"/)[1];
    _0x47f670 = fetch(_0x34e15c);
    url(_0x47f670.match(/{\s*file:"(.*?)"/)[1]);
    headers('Referer', _0x34e15c);
    headers('Accept', '*/*');
    headers('Origin', _0x34e15c);
    parser();
}
function setfilmizle() {
    url('boncuk45', '');
    var _0x3a4872 = url();
    if (url(['#'], 1)) {
        url(url().split('#')[0]);
    }
    var _0xfbc150 = fetch();
    var _0x34b515 = _0xfbc150.match("data.*?-id=\"(.*?)\"")[1];
    var _0x3009d2 = {};
    try {
        var _0x45b3fc = matchAll(_0xfbc150, /sticon-film"><\/span><b>(.*?)</g);
        for (let _0x4b8421 = 0; _0x4b8421 < _0x45b3fc.length; _0x4b8421++) {
            if (_0x45b3fc[_0x4b8421][1] != 'Raca') {
                _0x3009d2[_0x45b3fc[_0x4b8421][1]] = _0x45b3fc[_0x4b8421][1];
            }
        }
    } catch (_0x872e00) { }
    var _0x5c36cf = _0x45b3fc.length == _0x3009d2.length;
    if (!_0x3a4872.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x3009d2));
    } else {
        try {
            var _0x56b48d = _0x3009d2[_0x3a4872.split('#')[1]];
            var _0x419f69 = baseUrl(url()) + '/wp-admin/admin-ajax.php';
            var _0x3375b5 = _0xfbc150.match(/window\.videoAjax.*?nonce:\s*'(.*?)'/)[1];
            var _0x251f20 = 'action=get_video_url&post_id=' + _0x34b515 + '&player_name=' + _0x56b48d + '&nonce=' + _0x3375b5 + '&partKey=';
            if (!_0x5c36cf) {
                if (lang == 1) {
                    _0x251f20 += 'turkcedublaj';
                } else {
                    _0x251f20 += 'turkcealtyazi';
                }
            }
            var _0x39723e = fetchPost(_0x251f20, _0x419f69);
            url(_0x39723e.match(/url":"(.*?)"/)[1].replace(/\\/g, ''));
            if (url(['setplay', 'player'], 1)) {
                headers('Referer', url());
                headers('Accept', '*/*');
                _0xfbc150 = fetch(url());
                var _0x596b37 = _0xfbc150.match(/"videoServer":"(.*?)"/)[1];
                var _0x2e928a = baseUrl(url()).replace(/\\/g, '');
                var _0x4e443a = _0xfbc150.match(/"videoUrl":"(.*?)"/)[1].replace(/\\/g, '');
                url(_0x2e928a + '' + _0x4e443a + '?s=' + _0x596b37);
                consolelog(url());
            }
            parser();
        } catch (_0x20e53a) {
            error(_0x20e53a.message);
        }
    }
}
function showtv() {
    if (url(['canli-yayin', 'canliyayin'], 1)) {
        var _0x2fcb6e = fetch();
        var _0x2c2f6e = /var\s*videoUrl\s*=\s*"(.*?)"/;
        if (url(['turk', 'max'], 1)) {
            _0x2c2f6e = /"ht_stream_m3u8":"(https.*?playlist.m3u8.*?)"/;
        }
        url(_0x2fcb6e.match(_0x2c2f6e)[1]);
        parser();
    } else {
        var _0x2fcb6e = fetch().match(/data-ht='(.*?)'/)[1];
        _0x2fcb6e = _0x2fcb6e.replace("'", "\"");
        var _0x15b453 = JSON.parse(_0x2fcb6e + '');
        try {
            _0x15b453 = _0x15b453.ht_files[1].mp4;
        } catch (_0x2fff2e) {
            _0x15b453 = _0x15b453.ht_files[0].mp4;
        }
        var _0x1aef6f = ['240', '360', '480', '720', '1080', 'Alone'];
        var _0x55056e = -1;
        var _0x2d5f2 = -1;
        for (let _0x414c35 = 0; _0x414c35 < _0x15b453.length; _0x414c35++) {
            try {
                var _0xd0a851 = _0x1aef6f.indexOf(_0x15b453[_0x414c35].name + '');
                if (_0x55056e < _0xd0a851) {
                    _0x55056e = _0xd0a851;
                    _0x2d5f2 = _0x414c35;
                }
            } catch (_0x48e894) { }
        }
        if (_0x55056e != -1) {
            url(fixUrl(_0x15b453[_0x2d5f2].file));
        }
        parser();
    }
}
function sezonlukdizi() {
    var _0x48603c = url();
    var _0x20cd64 = fetch();
    _0x20cd64 = _0x20cd64.match(/<div\s*bid="(\d*)"\s*did=/)[1];
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    headers('X-Requested-With', 'XMLHttpRequest');
    _0x20cd64 = fetchPost('bid=' + _0x20cd64 + '&dil=' + g.getLang(), baseUrl(url()) + '/ajax/dataAlternatif2.asp');
    if (_0x20cd64.includes('eklenmedi')) {
        error('Sezonlukdizi 1544');
    }
    var _0x59368f = JSON.parse(_0x20cd64);
    var _0x2f412c = {
        _0x2e7d9f: _0x5650fd
    };
    for (var _0xf7c64a = 0; _0xf7c64a < _0x59368f.data.length; _0xf7c64a++) {
        if (!_0x59368f.data[_0xf7c64a].baslik.toLowerCase().includes('netu') && !_0x59368f.data[_0xf7c64a].baslik.toLowerCase().includes('upto') && !_0x59368f.data[_0xf7c64a].baslik.toLowerCase().includes('multi') && !_0x59368f.data[_0xf7c64a].baslik.toLowerCase().includes('upstream') && !_0x59368f.data[_0xf7c64a].baslik.toLowerCase().includes('videoseyred')) {
            var _0x5650fd = _0x59368f.data[_0xf7c64a].id + '';
            var _0x2e7d9f = _0x59368f.data[_0xf7c64a].baslik;
            ;
        }
    }
    if (_0x2f412c.length == 0) {
        error('Sezonlukdizi 1560');
    }
    if (!_0x48603c.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x2f412c));
    } else {
        url(_0x48603c.split('#')[1]);
        _0x20cd64 = fetchPost('id=' + url(), baseUrl(_0x48603c) + '/ajax/dataEmbed.asp');
        _0x20cd64 = _0x20cd64.match(/(?:SRC|src)="(.*?)"/)[1];
        if (_0x20cd64.startsWith('//')) {
            _0x20cd64 = 'https:' + _0x20cd64;
        }
        if (_0x20cd64.includes('/player/fembed.asp')) {
            url('https://dutrag.com/v/' + _0x20cd64.split("\\?v=")[1]);
        } else {
            if (_0x20cd64.includes('sbembed')) {
                var _0x4d12f0 = _0x20cd64.split('/')[4].replace('.html', '');
                var _0x344bd3 = Core.makeID(12) + '||' + _0x4d12f0 + '||' + Core.makeID(12) + '||streamsb';
                var _0x348585 = Core.bytesToHex(_0x344bd3);
                var _0x208dd3 = decodeURIComponent(_0x348585);
                _0x344bd3 = Core.makeID(12) + '||' + Core.makeID(12) + '||' + Core.makeID(12) + '||streamsb';
                _0x348585 = Core.bytesToHex(_0x344bd3);
                var _0x120b66 = decodeURIComponent(_0x348585);
                _0x344bd3 = Core.makeID(12) + '||' + _0x120b66 + '||' + Core.makeID(12) + '||streamsb';
                _0x348585 = Core.bytesToHex(_0x344bd3);
                var _0x59679f = decodeURIComponent(_0x348585);
                url(baseUrl(url()) + '/sourcesx38/' + _0x208dd3 + '/' + _0x59679f);
                _0x20cd64 = fetch();
                try {
                    url(JSON.parse(_0x20cd64).stream_data.file);
                } catch (_0x1090e1) {
                    url(JSON.parse(_0x20cd64).stream_data.backup);
                }
            } else {
                if (_0x20cd64.includes('playerjs')) {
                    var _0x4d12f0 = _0x20cd64.split('/')[4].split('&v=')[1];
                    url(hexToString(_0x4d12f0));
                } else {
                    url(_0x20cd64);
                }
            }
        }
        g.deleteHeader('X-Requested-With');
        g.deleteHeader('Content-Length');
        parser();
    }
}
function sibnet() {
    data = fetch();
    headers('Referer', url());
    data = (data + '').match(/src:\s*"(.*?)",/)[1];
    url(baseUrl(url()) + data);
    parser();
}
function sinefy() {
    var _0x23d127 = url();
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x138e70 = fetch();
    var _0x4ad842 = {
        _0x5332ee: _0x109899
    };
    try {
        var _0x18e5c4 = matchAll(_0x138e70, /data-querytype=".*?">\s*<a href="(.*?)"\s*data-navigo.*?>(.*?)</g);
        for (let _0x5c9504 = 0; _0x5c9504 < _0x18e5c4.length; _0x5c9504++) {
            var _0x109899 = _0x18e5c4[_0x5c9504][1];
            var _0x5332ee = _0x18e5c4[_0x5c9504][2];
            ;
        }
    } catch (_0x3c4c63) {
        error(_0x3c4c63.message);
    }
    if (!_0x23d127.includes('#')) {
        if (g.getLang() == 0) {
            Core.setLang(1);
        } else {
            Core.setLang(0);
        }
    }
    if (!_0x23d127.includes('#') && JSON.stringify(_0x4ad842) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x4ad842));
    } else {
        url(_0x23d127.split('#')[1]);
        headers('Accept', '*/*');
        _0x138e70 = fetch();
        if (url(['rapid'], 1)) {
            url(_0x138e70.match(/file:"(.*?)"/)[1]);
        } else {
            try {
                var _0x18e5c4 = matchAll(_0x138e70, /iframe.*?src="(.*?)"/g);
                for (let _0x4515d4 = 0; _0x4515d4 < _0x18e5c4.length; _0x4515d4++) {
                    var _0x109899 = _0x18e5c4[_0x4515d4][1];
                    if (!_0x109899.includes('finema')) {
                        url(_0x109899);
                        break;
                    }
                }
                url(fixUrl(url()));
            } catch (_0x47d0c6) {
                error(_0x47d0c6.message);
            }
        }
        parser();
    }
}
function sinemacx() {
    var _0x432a4f = url();
    url('?l=2', '');
    var _0x353a0b = fetch();
    _0x353a0b = _0x353a0b.match(/iframe.*?data-vsrc="(.*?)"/)[1];
    var _0x373018 = _0x353a0b.split('/')[4];
    url(_0x353a0b);
    url(fixUrl(url()));
    _0x353a0b = fetch();
    var _0x546871 = '';
    try {
        _0x546871 = _0x353a0b.match(/playerjsSubtitle.*?(http.*?vtt)"/)[1];
    } catch (_0x19792c) {
        _0x546871 = '';
    }
    headers('content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('x-requested-with', 'XMLHttpRequest');
    url('video/', 'player/index.php?data=');
    url(url() + '&do=getVideo');
    _0x353a0b = fetchPost('hash=' + _0x373018 + '&r=' + baseUrl(_0x432a4f), url());
    var _0x5a0b2b = JSON.parse(_0x353a0b);
    url(_0x5a0b2b.securedLink);
    g.deleteHeader('content-type');
    g.deleteHeader('x-requested-with');
    parser();
}
function sinemadelisi() {
    error("Sinemadelisi isimli kaynak site kapalÄ± olduÄŸundan oynatÄ±lamÄ±yor.");
    var _0x14db2e = url();
    var _0x54e46f = fetch();
    _0x54e46f = _0x54e46f.match(/inepisode">(.*?)"butonlar">/)[1];
    var _0x36d45d = {
        _0x36c80f: url()
    };
    try {
        var _0x2074af = matchAll(_0x54e46f, /<a\s*href="(.*?)"\s*class="post.*?dil="undefined">(.*?)</g);
        for (let _0x4bf26d = 0; _0x4bf26d < _0x2074af.length; _0x4bf26d++) {
            _0x36d45d[_0x2074af[_0x4bf26d][2] + '-' + _0x4bf26d] = _0x2074af[_0x4bf26d][1];
        }
    } catch (_0x52454b) { }
    if (!_0x14db2e.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x36d45d));
    } else {
        url(_0x14db2e.split('#')[1]);
        url('?l=2', '');
        _0x54e46f = fetch();
        url(_0x54e46f.match(/fitvidscompatible"\s*data-lazy-src="(.*?)"/)[1]);
        url(fixUrl(url()));
        parser();
    }
}
function sinemafilmizle() {
    var _0x968ae9 = url();
    headers('Referer', url());
    var _0x2e0e97 = fetch();
    var _0x2ca03c = [];
    var _0x38247d = [];
    var _0x26eef6 = [];
    var _0x3a0670 = {};
    var _0x1b4c67 = 'tra';
    if (g.getLang() == 0) {
        _0x1b4c67 = 'trd';
    }
    try {
        var _0x363f63 = matchAll(_0x2e0e97, /dil="(.*?)">.*?<\/span>/g);
        for (let _0x275415 = 0; _0x275415 < _0x363f63.length; _0x275415++) {
            _0x2ca03c.push(_0x363f63[_0x275415][1]);
        }
        _0x363f63 = matchAll(_0x2e0e97, /dil=".*?">(.*?)<\/span>/g);
        for (let _0x4811c0 = 0; _0x4811c0 < _0x363f63.length; _0x4811c0++) {
            _0x38247d.push(_0x363f63[_0x4811c0][1]);
        }
        _0x363f63 = matchAll(_0x2e0e97, /html\('.*?<iframe.*?src="(.*?)"/g);
        for (let _0x578ad4 = 0; _0x578ad4 < _0x363f63.length; _0x578ad4++) {
            _0x26eef6.push(_0x363f63[_0x578ad4][1]);
        }
    } catch (_0x2c8d45) {
        error(_0x2c8d45.message);
    }
    for (var _0x34979c = 0; _0x34979c < _0x2ca03c.length; _0x34979c++) {
        if (_0x2ca03c[_0x34979c] == _0x1b4c67) {
            try {
                _0x3a0670[_0x38247d[_0x34979c]] = _0x26eef6[_0x34979c];
            } catch (_0x243fac) { }
        }
    }
    if (!_0x968ae9.includes('#') && JSON.stringify(_0x3a0670) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x3a0670));
    } else {
        url(_0x968ae9.split('#')[1]);
        var _0x459028 = _0x26eef6.indexOf(url());
        var _0x2bc1a7 = '';
        if (_0x459028 != null) {
            _0x2bc1a7 = _0x38247d[_0x459028];
        }
        if (_0x2bc1a7.toLowerCase().includes('sine')) {
            if (!g.isWebView()) {
                error('No Webview');
            } else {
                getWebViewOwnContentJS(_0x968ae9, 'popcornvakti', 'I AM NOT LOOKING', 'playSheilaBtn', '', 0, false, g.getHeadersJSON() + '', 1);
            }
        } else {
            if (url(['my.mail.ru', 'vidmoly'], 1)) {
                if (url(['vidmoly'], 1)) {
                    url(url().split('vid=')[1]);
                }
            } else {
                headers('Referer', _0x968ae9);
                _0x2e0e97 = fetch();
                url(_0x2e0e97.match(/player"><iframe.*?src=["\'](.*?)["\']/)[1]);
                if (!url(['odno'], 1) && !url(['ok.ru'], 1)) {
                    _0x2e0e97 = fetch();
                    url(_0x2e0e97.match(/<iframe.*?src="(.*?)"/)[1]);
                }
            }
            parser();
        }
    }
}
function siyahfilmizle() {
    var _0x2e7c4c = url();
    var _0x1aa2cc = fetch();
    var _0x50735c = {
        _0x207f6c: _0x6fd06a,
        _0x207f6c: _0x6fd06a
    };
    try {
        var _0x42a144 = matchAll(_0x1aa2cc, /<li><span.*?href="(.*?)".*?i class="(.*?)"><\/i>(.*?)</g);
        for (let _0x4bc831 = 0; _0x4bc831 < _0x42a144.length; _0x4bc831++) {
            var _0x387bcc = _0x42a144[_0x4bc831][2];
            var _0x6fd06a = _0x42a144[_0x4bc831][1];
            var _0x207f6c = _0x42a144[_0x4bc831][3];
            if (_0x207f6c == '') {
                _0x207f6c = 'FilmPlatin';
                ;
            }
            if (_0x387bcc != null) {
                if (_0x387bcc.includes('en') && g.getLang() == 1 || _0x387bcc.includes('tr') && g.getLang() == 0) {
                    ;
                }
            }
        }
    } catch (_0x504f5d) {
        error(_0x504f5d.message);
    }
    if (!_0x2e7c4c.includes('#') && JSON.stringify(_0x50735c) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x50735c));
    } else {
        if (_0x2e7c4c.includes('#')) {
            url(_0x2e7c4c.split('#')[1]);
        }
        _0x1aa2cc = fetch();
        url(_0x1aa2cc.match(/iframe.*?src=["\'](.*?)["\']/)[1].replace('#038;', ''));
        url(fixUrl(url()));
        parser();
    }
}
function startv() {
    if (url(['canli-yayin'], 0)) {
        var _0x5b5b02 = fetch('https://dyg-ads-cdn.s3.eu-west-1.amazonaws.com/live-broadcast-player/v1/bundle.js');
        var _0x2ac222 = /Ntv.*?development":"(.*?)"/;
        if (url(['startv'], 0)) {
            _0x2ac222 = /Startv.*?development":"(.*?)"/;
        }
        url(_0x5b5b02.match(_0x2ac222)[1]);
        parser();
    } else {
        var _0x5b5b02 = fetch();
        refId = _0x5b5b02.match(/<meta property="dyg:tags" content="(.*?)"\/>/)[1].split(',')[2];
        var _0x23e43c = JSON.parse(fetch('https://dygvideo.dygdigital.com/api/video_info?akamai=true&PublisherId=1&ReferenceId=StarTv_' + refId + '&SecretKey=NtvApiSecret2014*'));
        url(fixUrl(_0x23e43c.data.flavors['0'].file_url_1));
        parser();
    }
}
function sto() {
    var _0x4e9349 = url();
    var _0x2249ea = fetch();
    var _0x1052b3 = baseUrl(url());
    var _0x144a8e = {
        _0x3a0415: _0x1052b3 + _0x5afd90
    };
    try {
        var _0x495edc = matchAll(_0x2249ea, /data-lang-key="(\d)"\s*data-link-id="\d+"\s*data-link-target="(.*?)".*?Hoster\s(.*?)"/g);
        for (let _0x53f057 = 0; _0x53f057 < _0x495edc.length; _0x53f057++) {
            var _0x1114c8 = _0x495edc[_0x53f057][1];
            var _0x5afd90 = _0x495edc[_0x53f057][2];
            var _0x3a0415 = _0x495edc[_0x53f057][3];
            if (_0x1114c8 != null) {
                if (_0x1114c8 == '2' && g.getLang() == 0 || _0x1114c8 == '1' && g.getLang() == 2) {
                    ;
                }
            }
        }
    } catch (_0x4f17e0) {
        error(_0x4f17e0.message);
    }
    if (!_0x4e9349.includes('#') && JSON.stringify(_0x144a8e) != '{}') {
        Core.showAlternatesJS(JSON.stringify(_0x144a8e));
    } else {
        url(_0x4e9349.split('#')[1]);
        url(Core.getRedirectUrlJS(url()));
        if (url(['vidoza', 'videzz'], 1)) {
            _0x2249ea = fetch();
            url(_0x2249ea.match(/source\s*src="(.*?)"/)[1]);
        }
        parser();
    }
}
function streamplayer() {
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('x-requested-with', 'XMLHttpRequest');
    var _0xddbf1e = fetch();
    url(_0xddbf1e.match(/sources\s*:\s* \[\{file:"(.*?)"/)[1]);
    headers('Referer', 'https://vidmoly.to/');
    parser();
}
function streamruby() {
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Mobile Safari/537.36');
    data = fetch();
    url((data + '').match(/file:"(.*?m3u8.*?)"/)[1]);
    headers('Referer', 'https://streamruby.com/');
    parser();
}
function streamtheworld() {
    var _0x578532 = fetch().split('<transports>');
    var _0x108458 = '';
    for (let _0x179d52 = 0; _0x179d52 < _0x578532.length; _0x179d52++) {
        if (_0x578532[_0x179d52].includes('AAC')) {
            _0x108458 = _0x578532[_0x179d52];
            break;
        }
    }
    var _0x5b4a94 = _0x108458.match(/<ip>(.*?)<\/ip>/)[1];
    var _0xdd9b92 = _0x108458.match(/<mount>(.*?)<\/mount>/)[1];
    g.setMediaType('mp5');
    url('https://' + _0x5b4a94 + '/' + _0xdd9b92 + '.aac');
    headers('Connection', 'keep-alive');
    parser();
}
function streamoupload() {
    var _0x1238d6 = fetch();
    _0x1238d6 = _0x1238d6.match(/<script\s*type=".*?text\/javascript">\s*(eval[\s\S]*?)\s*<\/script>/)[1];
    _0x1238d6 = unPack(_0x1238d6);
    url(_0x1238d6.match(/file\s*:\s*"(.*?streamoupload.*?)"/)[1].replace(/\\\\\//g, '/'));
    g.deleteHeader('Referer');
    g.deleteHeader('Base');
    parser();
}
function streamtape() {
    url(fixUrl(url()));
    headers('Referer', 'https://streamtape.com/');
    var _0x267b8f = fetch();
    _0x267b8f = matchAll(_0x267b8f, /ById\('.+?=\s*(["']\/\/[^;<]+)/g);
    _0x267b8f = _0x267b8f[_0x267b8f.length - 1][1];
    var _0x3ca728 = _0x267b8f.split('+');
    var _0x17b41f = '';
    for (let _0x543b1e = 0; _0x543b1e < _0x3ca728.length; _0x543b1e++) {
        var _0x2906f2 = _0x3ca728[_0x543b1e].split("'").join("\"");
        try {
            var _0x699def = _0x2906f2.match(/"([^"]*)/)[1];
            var _0x558932 = 0;
            if (_0x2906f2.includes('substring')) {
                var _0x309825 = matchAll(_0x2906f2, /substring\((\d+)/g);
                for (let _0x5dc67b = 0; _0x5dc67b < _0x309825.length; _0x5dc67b++) {
                    _0x558932 = _0x558932 + parseInt(_0x309825[_0x5dc67b][1]);
                }
            }
            _0x17b41f = _0x17b41f + _0x699def.substring(_0x558932);
        } catch (_0x11067a) { }
    }
    _0x17b41f = _0x17b41f + '&stream=1';
    url(fixUrl(_0x17b41f));
    parser();
}
function superfilmgeldi() {
    var _0x3fcf83 = url();
    var _0x48a753 = fetch(url().replace('?l=1', '').replace('?l=0', ''));
    _0x48a753 = _0x48a753.match(/iframe\s*src="(.*?)"/)[1];
    if (_0x48a753.startsWith('/')) {
        _0x48a753 = 'https:' + _0x48a753;
    }
    if (_0x48a753.includes('vidmoly') || _0x48a753.includes('ok.ru') || _0x48a753.includes('odnoklas')) {
        url(_0x48a753);
        parser();
    } else {
        if (_0x48a753.includes('mixlion')) {
            _0x48a753 = fetch(_0x48a753);
            var _0x430b57 = _0x48a753.match(/"videoServer":"(.*?)"/)[1];
            var _0x3356a7 = 'https://mixlion.com' + _0x48a753.match(/"videoUrl":"(.*?)"/)[1].replace(/\\/g, '');
            _0x3356a7 = _0x3356a7 + '?s=' + _0x430b57 + '&d=';
            headers('Referer', _0x3356a7);
            headers('Accept', '*/*');
            _0x48a753 = fetch(_0x3356a7);
            url(_0x48a753.match(/(htt.*?)$/)[1]);
        } else {
            if (_0x48a753.includes('mixtiger')) {
                headers('content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
                headers('x-requested-with', 'XMLHttpRequest');
                var _0xdbf030 = _0x48a753.split('/')[5];
                fetch(_0x48a753);
                _0x48a753 = fetchPost('hash=' + _0xdbf030 + '&r=' + baseUrl(_0x3fcf83) + '&s=', _0x48a753 + '?do=getVideo');
                var _0x47896b = JSON.parse(_0x48a753);
                if (_0x47896b.hasOwnProperty('videoSrc')) {
                    _0x48a753 = _0x47896b.videoSrc;
                } else {
                    _0x48a753 = _0x47896b.videoSources[0].file;
                }
                url(_0x48a753);
            } else {
                if (_0x48a753.includes('mixeagle')) {
                    headers('Referer', baseUrl(_0x48a753));
                    headers('Accept', '*/*');
                    var _0x453d2c = baseUrl(_0x48a753);
                    var _0x48a753 = fetch(_0x48a753);
                    var _0x43a32b = _0x48a753.match(/"videoServer":"(.*?)"/)[1];
                    _0x48a753 = _0x453d2c + _0x48a753.match(/videoUrl":"(.*?)"/)[1].split("\\").join('') + '?s=' + _0x43a32b + '&d=';
                    url(_0x48a753);
                }
            }
        }
    }
    parser();
}
function supervideo() {
    url(fixUrl(url()));
    var _0x2b28fb = fetch();
    try {
        _0x2b28fb = unPack(_0x2b28fb.match(/(eval\(function.*?)<\/script>/)[1]);
        try {
            url(_0x2b28fb.match(/file:"(.*?)"/)[1]);
        } catch (_0x7e403a) {
            url(_0x2b28fb.match(/src:"(.*?)"/)[1]);
        }
    } catch (_0x49dabb) {
        url(_0x2b28fb.match(/\[\{file:"(.*?)"/)[1]);
    }
    parser();
}
function tele1() {
    url(fetch().match(/iframe.*?src="(https:\/\/www.youtube.*?)"/)[1].replace('https://www', 'https://m').replace('/embed/', '/watch?v='));
    parser(url(), 1, '', '', false);
}
function teve2() {
    if (url(['canli-yayin'], 0)) {
        var _0x300ec0 = fetch();
        try {
            url(_0x300ec0.match(/file:'(.*?)'/)[1]);
        } catch (_0xcd2356) {
            url(_0x300ec0.match(/contentUrl"\s*:\s*"(.*?)"/)[1]);
        }
        parser();
    } else {
        var _0x300ec0 = fetch().match(/itemprop="embedURL" href="(.*?)"/)[1].replace('video', 'action/media');
        _0x300ec0 = fetch(_0x300ec0);
        var _0x1bc2da = JSON.parse(_0x300ec0);
        url(_0x1bc2da.Media.Link.ServiceUrl + '/' + _0x1bc2da.Media.Link.SecurePath);
        parser();
    }
}
function thehun() {
    url(fetch().match(/source src="(.*?)"/)[1]);
    parser();
}
function tjk() {
    url(fetch().match(/hls\s*:\s*'(.*?)'/)[1]);
    parser();
}
function tlc() {
    if (url(['canli-izle'], 0)) {
        var _0x438c74 = fetch();
        url(_0x438c74.match(/(?:daionUrl|liveUrl)\s*(?:=|\:)\s*(?:\'|")(.*?)(?:\'|")/)[1]);
        parser();
    } else {
        var _0x1c8484 = '';
        var _0xe6491d = '20';
        if (url(['dmax'], 1)) {
            _0xe6491d = '27';
        }
        _0x1c8484 = fetch().match(/referenceId\s*:\s*'(.*?)'/)[1];
        url('https://dygvideo.dygdigital.com/api/redirect?PublisherId=' + _0xe6491d + '&ReferenceId=' + _0x1c8484 + '&SecretKey=NtvApiSecret2014*');
        parser();
    }
}
function trstx() {
    var _0x3f35f7 = fetch().match(/playerConfigs\s*=\s*(.*?);/)[1];
    var _0x37f9d8 = JSON.parse(_0x3f35f7).href + '' + JSON.parse(_0x3f35f7).file;
    url(fixUrl(_0x37f9d8));
    _0x3f35f7 = matchAll(fetch(), /title":\s*"(.*?)".*?file":\s*"(.*?)"/g);
    var _0x4132a8 = '';
    var _0x1b5ad9 = {};
    for (var _0x4895e0 = 0; _0x4895e0 < _0x3f35f7.length; _0x4895e0++) {
        if (g.getLang() == 1 && _0x3f35f7[_0x4895e0][1].includes('Altyaz')) {
            _0x4132a8 = _0x3f35f7[_0x4895e0][2];
        } else {
            if (g.getLang() == 0 && _0x3f35f7[_0x4895e0][1].includes('Dublaj')) {
                _0x4132a8 = _0x3f35f7[_0x4895e0][2];
            } else {
                _0x1b5ad9[_0x3f35f7[_0x4895e0][1]] = _0x3f35f7[_0x4895e0][1];
                if (g.getLang() == 2) {
                    _0x4132a8 = _0x3f35f7[_0x4895e0][2];
                }
            }
        }
    }
    if (Object.keys(_0x1b5ad9).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x1b5ad9));
    } else {
        var _0x27989a = url().split('playlist');
        url(_0x27989a[0] + 'playlist/' + _0x4132a8 + '!!.txt');
        url(fetch());
        parser();
    }
}
function tv360() {
    var _0x23f980 = fetch();
    url(_0x23f980.match(/source\s*src="(.*?)"/)[1]);
    parser();
}
function tv8() {
    if (url(['canli-yayin'], 0)) {
        var _0x421979 = fetch();
        url(_0x421979.match(/var videoUrl = "(.*?)"/)[1]);
        parser();
    } else {
        url(fetch().match(/hls\s*:\s*"(.*?)"/)[1].replace(/ /g, '').replace(/\"/g, ''));
        parser();
    }
}
function tvem() {
    var _0x542ebc = fetch();
    url(fetch(fixUrl(_0x542ebc.match(/<div class="live-area">\s*.*?<script src="(.*?)"><\/script>/)[1])).match(/yayincomtr4="(.*?)"/)[1]);
    parser();
}
function tvnet() {
    url(fetch().match(/live-image"\s*href="(.*?)"/)[1]);
    parser();
}
function ucankus() {
    url(fetch().match(/<source\s*src="(.*?)"/)[1]);
    parser();
}
function ugurfilm() {
    var _0x23d64b = url();
    var _0x30b1ed = fetch();
    var _0x3940a5 = _0x30b1ed;
    var _0x53e9fb = {
        Mailru: _0x1082ee,
        Fembed: _0x1082ee,
        Youtube: _0x1082ee,
        Vidmoly: _0x1082ee,
        Mailru: _0x1082ee,
        Fembed: _0x1082ee,
        Youtube: _0x1082ee,
        Vidmoly: _0x1082ee,
        Youtube: _0x30b1ed,
        Dublaj: 'https:' + fetch(_0x350e44).match(/"hls":"(.*?)"/)[1].replace(/\//g, '')
    };
    if (!url(['#'], 1)) {
        try {
            _0x30b1ed = _0x30b1ed.match("<iframe.*?src=\"https://" + url().split('/')[2] + "/player(.*?)\"")[1];
            url('https://' + url().split('/')[2] + '/player' + _0x30b1ed);
            if (url([url().split('/')[2] + '/player/play.php'], 1)) {
                var _0x128706 = url().replace('https://' + url().split('/')[2] + '/player/play.php?vid=', '');
                _0x30b1ed = matchAll(fetch(), /class="c-dropdown__item"\s*data-dropdown-value="(.*?)" data-order-value="(\d+)"/g);
                var _0x3bdb0f = 'https://' + url().split('/')[2] + '/player/ajax_sources.php';
                for (var _0x36d74c = 0; _0x36d74c < _0x30b1ed.length; _0x36d74c++) {
                    var _0xdce363 = 'vid=' + _0x128706 + '&alternative=' + _0x30b1ed[_0x36d74c][1] + '&ord=' + _0x30b1ed[_0x36d74c][2];
                    try {
                        headers('X-Requested-With', 'XMLHttpRequest');
                        var _0x1082ee = JSON.parse(fetchPost(_0xdce363, _0x3bdb0f)).iframe + '';
                        _0x1082ee = fixUrl(_0x1082ee) + '';
                        if (_0x1082ee.includes('mail')) {
                            ;
                        } else {
                            if (_0x1082ee.includes('fembed')) {
                                ;
                            } else {
                                if (_0x1082ee.includes('ok.ru')) {
                                    _0x53e9fb['Ok.ru'] = _0x1082ee;
                                } else {
                                    if (_0x1082ee.includes('odnoklassniki')) {
                                        _0x53e9fb['Ok.ru'] = _0x1082ee;
                                    } else {
                                        if (_0x1082ee.includes('youtube')) {
                                            ;
                                        } else {
                                            if (_0x1082ee.includes('vidmoly')) {
                                                ;
                                            } else {
                                                if (_0x1082ee.includes('1x')) {
                                                    _0x53e9fb['1x'] = _0x1082ee;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (_0x54d740) { }
                }
            } else {
                try {
                    _0x30b1ed = _0x3940a5.match(/parttab tab-aktif(.*?)<\/iframe>/)[1].match(/href="(.*?)"/)[1];
                    var _0x1082ee = fetch(_0x30b1ed).match(/iframe.*?src=(?:'|")(.*?)(?:'|")/)[1];
                    if (_0x1082ee.includes('mail')) {
                        ;
                    } else {
                        if (_0x1082ee.includes('fembed')) {
                            ;
                        } else {
                            if (_0x1082ee.includes('ok.ru')) {
                                _0x53e9fb['Ok.ru'] = _0x1082ee;
                            } else {
                                if (_0x1082ee.includes('odnoklassniki')) {
                                    _0x53e9fb['Ok.ru'] = _0x1082ee;
                                } else {
                                    if (_0x1082ee.includes('youtube')) {
                                        ;
                                    } else {
                                        if (_0x1082ee.includes('vidmoly')) {
                                            ;
                                        } else {
                                            if (_0x1082ee.includes('1x')) {
                                                _0x53e9fb['1x'] = _0x1082ee;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (_0x423deb) {
                    try {
                        _0x30b1ed = _0x3940a5.match(/iframe.*?src="(.*?)"/)[1];
                        ;
                    } catch (_0x3e2506) { }
                }
            }
        } catch (_0x5762bd) {
            var _0x350e44 = '';
            try {
                _0x350e44 = _0x30b1ed.match(/<iframe.*?src="(.*?)"/)[2];
            } catch (_0x42e3c0) {
                _0x350e44 = _0x30b1ed.match(/<iframe.*?src="(.*?)"/)[1];
            }
            var _0x167e47 = _0x30b1ed.match(/movie\/(.*?)\/iframe/)[1];
            if (_0x350e44.includes('trstx') || _0x350e44.includes('sobreatsesuyp')) {
                url(_0x350e44);
            } else {
                _0x53e9fb["AltyazÄ±lÄ±"] = 'https:' + fetch(_0x350e44 + '?t=' + _0x167e47).match(/"hls":"(.*?)"/)[1].replace(/\//g, '');
                ;
            }
        }
    }
    if (!_0x23d64b.includes('#') && Object.keys(_0x53e9fb).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x53e9fb));
    } else {
        if (url(['#'], 1)) {
            url(url().split('#')[1].replace(/\\/g, ''));
            url(fixUrl(url()));
        } else {
            url(_0x53e9fb[Object.keys(_0x53e9fb)[0]]);
        }
        parser();
    }
}
function unutulmaz() {
    var _0x4d2018 = url();
    var _0x3ee614 = fetch();
    var _0x49255d = _0x3ee614.match(/<iframe.*?src=(.*?)\s/)[1];
    _0x49255d = _0x49255d.replace(/"/g, '');
    if (!_0x49255d.startsWith('http')) {
        _0x49255d = 'https:' + _0x49255d;
    }
    var _0x105199 = {
        _0x233da6: _0x49255d
    };
    try {
        var _0x38160f = matchAll(_0x3ee614, /<li class="part">\s*<a href="(.*?)"/g);
        for (let _0x54c763 = 0; _0x54c763 < _0x38160f.length; _0x54c763++) {
            var _0xfe46d = fetch(_0x38160f[_0x54c763][1]);
            var _0x2ec3d0 = _0xfe46d.match(/<iframe.*?src=(.*?)\s/)[1];
            if (!_0x2ec3d0.startsWith('http')) {
                _0x2ec3d0 = 'https:' + _0x2ec3d0;
            }
            var _0x4a7cd9 = _0x2ec3d0.match(/(?:\/\/www|\/\/)(.*?)\./);
            _0x105199[_0x4a7cd9] = _0x2ec3d0;
        }
    } catch (_0x21e460) {
        error(_0x21e460.message);
    }
    if (!_0x4d2018.includes('#') && JSON.stringify(_0x105199) != '{}' && Object.keys(_0x105199).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x105199));
    } else {
        if (_0x4d2018.includes('#')) {
            url(_0x4d2018.split('#')[1]);
        }
        if (Object.keys(_0x105199).length == 1) {
            url(_0x49255d);
        }
        if (g.getLang() == 1) {
            Core.setLang(0);
        } else {
            Core.setLang(1);
        }
        headers('Referer', _0x4d2018);
        parser();
    }
}
function vectorx() {
    headers('Referer', baseUrl(url()));
    var _0x4d206a = fetch();
    var _0x1e6275 = _0x4d206a.match(/'({.*?})'/)[1];
    _0x4d206a = getAes('1FHuaQhhcsKgpTRB', _0x1e6275);
    url(JSON.parse(_0x4d206a.match(/sources:(.*?]),/)[1])[0].file);
    try {
        subs = JSON.parse(_0x4d206a.match(/tracks:(.*?]),/)[1]);
        for (let _0x32bee1 = 0; _0x32bee1 < subs.length; _0x32bee1++) {
            if (subs[_0x32bee1].label.toLowerCase().includes('tur') && !subs[_0x32bee1].default) {
                sub(subs[_0x32bee1].file.replace('.srt', '.vtt'));
                break;
            }
        }
    } catch (_0x595cc8) { }
    parser();
}
function videoseyred() {
    url(fixUrl(url()));
    headers('Referer', baseUrl(url()));
    url('https://videoseyred.in' + fetch().match(/playlistUrl='(.*?)'/)[1]);
    var _0x2b79c1 = JSON.parse(fetch());
    url(_0x2b79c1[0].sources[0].file);
    var _0x486fc1 = _0x2b79c1[0].tracks;
    for (let _0x18efae = 0; _0x18efae < _0x486fc1.length; _0x18efae++) {
        if (_0x486fc1[_0x18efae].hasOwnProperty('label')) {
            if (_0x486fc1[_0x18efae].label.includes('T')) {
                sub(_0x486fc1[_0x18efae].file);
                break;
            }
        }
    }
    parser();
}
function vidload() {
    url(fixUrl(url()));
    var _0x63e5ad = url();
    var _0x2a9d43 = new Date().getTime();
    url(baseUrl(url()) + '/video.js?' + _0x2a9d43);
    var _0x32f105 = fetch();
    var _0x328a8c = _0x32f105.match(/window,["\'](.*?)["\'],["\'].*?["\']/)[1];
    var _0x4598b1 = _0x32f105.match(/window,["\'].*?["\'],["\'](.*?)["\']/)[1];
    headers(_0x328a8c, _0x4598b1);
    headers('Referer', _0x63e5ad);
    var _0x17ecd7 = _0x63e5ad.split('iframe/');
    var _0x5f082f = _0x17ecd7[_0x17ecd7.length - 1];
    url(baseUrl(_0x63e5ad) + '/ajax/' + _0x5f082f + '?' + _0x2a9d43);
    _0x32f105 = fetch();
    var _0x499f80 = JSON.parse(_0x32f105);
    url(_0x499f80.file + '?' + _0x499f80.hash);
    parser();
}
function vidlop() {
    var _0x417621 = url().split('/');
    _0x417621 = _0x417621[_0x417621.length - 1];
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('x-requested-with', 'XMLHttpRequest');
    var _0x361ae8 = fetchPost('hash=' + _0x417621 + '&r=' + baseUrl(url()), 'https://vidlop.com/player/index.php?data=' + _0x417621 + '&do=getVideo');
    _0x361ae8 = JSON.parse(_0x361ae8);
    url(_0x361ae8.securedLink);
    var _0x4219aa = fetch();
    try {
        var _0x4773fb = matchAll(_0x4219aa, /URI="(.*?\.vtt)"/g);
        for (let _0x50a291 = 0; _0x50a291 < _0x4773fb.length; _0x50a291++) {
            if (_0x4773fb[_0x50a291][1].includes('tur') && _0x4773fb[_0x50a291][1].includes('.vtt')) {
                sub(_0x4773fb[_0x50a291][1]);
            }
        }
    } catch (_0x149c63) { }
    parser();
}
function vidmoly() {
    url(fixUrl(url()));
    url('.to', '.me');
    url.fasturl;
    url['document has moved'];
    headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    headers('Accept', 'text/html');
    headers('charset', 'utf-8');
    headers('sec-fetch-dest', 'iframe');
    headers('Referer', baseUrl(url()));
    g.logHeader();
    var _0xe71ade = fetch();
    url(_0xe71ade.match(/sources:\s*.*?(https.*?m3u8).*?\],/)[1]);
    try {
        sub(_0xe71ade.match(/file\\s*:\\s*"(\/srt.*?)"/)[1]);
    } catch (_0x8eb36b) { }
    parser();
}
function vidmoxy() {
    ;
    try {
        ;
        for (let _0x1ef8bb = 0; _0x1ef8bb < _0x8eb154.length; _0x1ef8bb++) {
            if (_0x8eb154[_0x1ef8bb][1].includes('Tur')) {
                sub(_0x8eb154[_0x1ef8bb][1].split("\\").join(''));
            }
        }
    } catch (_0x1120ad) { }
    try {
        _0x8b7aa0 = _0x8b7aa0.match(/EE\.dd\("(.*?)"\)/)[1];
        eval(function (_0x52e22b, _0x4d4382, _0x53b6d5, _0x4c2d77, _0x248c84, _0x2cd003) {
            _0x248c84 = function (_0x475c33) {
                return _0x475c33.toString(_0x4d4382);
            };
            if (!''.replace(/^/, String)) {
                while (_0x53b6d5--) {
                    _0x2cd003[_0x248c84(_0x53b6d5)] = _0x4c2d77[_0x53b6d5] || _0x248c84(_0x53b6d5);
                }
                _0x4c2d77 = [function (_0x2a2bbb) {
                    return _0x2cd003[_0x2a2bbb];
                }];
                _0x248c84 = function () {
                    return "\\w+";
                };
                _0x53b6d5 = 1;
            }
            ;
            while (_0x53b6d5--) {
                if (_0x4c2d77[_0x53b6d5]) {
                    _0x52e22b = _0x52e22b.replace(new RegExp("\\b" + _0x248c84(_0x53b6d5) + "\\b", 'g'), _0x4c2d77[_0x53b6d5]);
                }
            }
            return _0x52e22b;
        }("1 e={7:3(s){5 s.f('').h().i('')},8:3(s){5 s.2(/[a-j-9]/g,3(c){5 k.l((c<='9'?m:n)>=(c=c.o(0)+p)?c:c-q)})},t:3(s){1 r=6.7(s);1 a=6.8(r);1 b=u(a);5 b.2(/\\+/g,'-').2(/\\//g,'d').2(/=+$/,'')},v:3(s){s=s.2(/-/g,'+').2(/d/g,'/');w(s.x%4!==0){s+='='}1 a=y(s);1 b=6.8(a);5 6.7(b)}};", 35, 35, '|var|replace|function||return|this|rs|rr|Z||||_|EE|split||reverse|join|zA|String|fromCharCode|90|122|charCodeAt|13|26|||ee|btoa|dd|while|length|atob'.split('|'), 0, {}));
        url(EE.dd(_0x8b7aa0));
    } catch (_0x17ac4a) {
        try {
            url(_0x8b7aa0.match(/{file: "(.*?)", type: ".*?"}/)[1]);
        } catch (_0x22430e) {
            _0x8b7aa0 = _0x8b7aa0.match(/(eval\(function\(p,a,c,k,e,d.*?\}\)\);)/)[1];
            _0x8b7aa0 = unPack(_0x8b7aa0);
            ;
            ;
            url(moxyUnpacker(_0x34178f, _0x1944cf));
        }
    }
    parser();
}
function vimeo() {
    var _0x4ca1e1 = fetch();
    var _0x43b394 = _0x4ca1e1.match(/playerConfig\s*=\s*(\{.*?\})</)[1];
    try {
        _0x43b394 = JSON.parse(_0x43b394);
        url(_0x43b394.request.files.hls.cdns.akfire_interconnect_quic.url);
        parser();
    } catch (_0x4a79e5) {
        error(_0x4a79e5.message);
    }
}
function vkcom() {
    url(fixUrl(url()));
    headers('X-requested-with', 'XMLHttpRequest');
    var _0x4e5fc2 = fetchPost('act=show&al=1&claim=&dmcah=&hd=&list=&module=direct&playlist_id=' + url().split('video')[1].split('_')[0] + '_-2&show_original=&t=&video=' + url().split('video')[1], 'https://vk.com/al_video.php?act=show');
    url(JSON.parse(_0x4e5fc2).payload[1][4].player.params[0].hls);
    parser();
}
function voe() {
    url('https://voe.sx/', 'https://jessicaglassauthor.com/');
    url(atob(fetch().match(/["']hls["']\s*:\s*["'](.*?)["'],/)[1]));
    parser();
}
function vudeo() {
    var _0x2fbafa = fetch();
    url(_0x2fbafa.match(/sources\s*:\s*\["(.*?)"/)[1]);
    headers('Referer', baseUrl(url()) + '/');
    parser();
}
function vumoox() {
    error("Vumoox isimli kaynak site kapalÄ± olduÄŸundan oynatÄ±lamÄ±yor.");
    if (!g.isWebView()) {
        error('No Webview');
    }
    var _0x528a45 = url().split('#')[0];
    headers('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36');
    if (!url(['#'], 1)) {
        getWebViewOwnContentJS(_0x528a45, 'm3u8', 'ajax/episode/subtitle', 'server active', '', 0, false, g.getHeadersJSON(), 2);
    } else {
        var _0x51e878 = '';
        var _0x77887a = '';
        var _0xb39539 = '';
        var _0x27e788 = '';
        var _0x579a9 = '';
        if (url(['episode/subtitle'], 1)) {
            _0x51e878 = url().split('#')[2];
            data = fetch(_0x51e878);
            var _0x463dd0 = JSON.parse(data);
            for (var _0x140909 = 0; _0x140909 < _0x463dd0.length; _0x140909++) {
                if (_0x463dd0[_0x140909].label == 'Turkish') {
                    _0x77887a = _0x463dd0[_0x140909].file;
                } else {
                    if (_0x463dd0[_0x140909].label == 'English') {
                        _0xb39539 = _0x463dd0[_0x140909].file;
                    } else {
                        if (_0x463dd0[_0x140909].label == 'French') {
                            _0x27e788 = _0x463dd0[_0x140909].file;
                        } else {
                            if (_0x463dd0[_0x140909].label == 'German') {
                                _0x579a9 = _0x463dd0[_0x140909].file;
                            }
                        }
                    }
                }
            }
        }
        url(url().split('#')[1]);
        var _0x1807ee = [];
        if (_0x77887a == '') {
            var _0x53f6f8 = '';
            if (_0xb39539 != '') {
                _0x53f6f8 = _0xb39539;
            } else {
                if (_0x27e788 != '') {
                    _0x53f6f8 = _0x27e788;
                } else {
                    if (_0x579a9 != '') {
                        _0x53f6f8 = _0x579a9;
                    }
                }
            }
            if (_0x53f6f8 != '') {
                headers.forHelper = 'c2V5L3RyYW5zbGF0ZS8';
                headers.forHelper2 = 'Q2V2aXJpQUkuVFIucGhwP3VybD0';
                sub(subHelp(_0x53f6f8, 'vumoo'));
                if (_0xb39539 != '') {
                    var _0x3fbc8a = {
                        'lang': 'en',
                        url: _0xb39539
                    };
                    _0x1807ee = JSON.parse(sub());
                    _0x1807ee.push(_0x3fbc8a);
                    sub(JSON.stringify(_0x1807ee));
                }
            }
        } else {
            sub(_0x77887a);
        }
        if (url(['http'], 1)) {
            parser();
        }
    }
}
function watchomovies() {
    var _0x494d5a = fetch();
    url(_0x494d5a.match(/<iframe src="(.*?)"/i)[1]);
    parser();
}
function webteizle() {
    var _0x18ffb3 = url();
    var _0x1f637f = fetch();
    _0x1f637f = _0x1f637f.match(/data-id="(.*?)"/)[1];
    var _0x824adc = 'filmid=' + _0x1f637f + '&dil=' + (_0x18ffb3.includes('altyazi') ? '1' : '0');
    if (_0x18ffb3.includes('sezon')) {
        var _0x74275f = _0x1f637f.match(/(\d*)-sezon-(\d*)-/);
        _0x824adc += '&s=' + _0x74275f[1] + '&b=' + _0x74275f[2];
    }
    _0x824adc += '&bot=0';
    var _0x111a6e = '';
    var _0x488b73 = '';
    headers('X-Requested-With', 'XMLHttpRequest');
    var _0xb85c9 = fetch(baseUrl(_0x18ffb3) + '/js/site.min.js');
    _0x488b73 = _0xb85c9.match(/#embed'\)\.addClass\('loading'\);\$\.post\("\/(.*?)"/)[1];
    _0x111a6e = _0xb85c9.match(/s,b\)\{\$.post\('\/(.*?)'/)[1];
    url(baseUrl(url()) + '/' + _0x111a6e);
    _0x1f637f = fetchPost(_0x824adc);
    var _0x81ca45 = JSON.parse(_0x1f637f).data;
    var _0x476d6c = {};
    for (let _0x3cd26e = 0; _0x3cd26e < _0x81ca45.length; _0x3cd26e++) {
        if (_0x81ca45[_0x3cd26e].baslik != 'Netu' && _0x81ca45[_0x3cd26e].baslik != 'PLUS') {
            _0x476d6c[_0x81ca45[_0x3cd26e].baslik] = '' + _0x81ca45[_0x3cd26e].id;
        }
    }
    if (!_0x18ffb3.includes('#') && Object.keys(_0x476d6c).length > 1) {
        Core.showAlternatesJS(JSON.stringify(_0x476d6c));
    } else {
        headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        var _0x2ae687 = _0x18ffb3.split('#')[2];
        _0x824adc = 'id=' + _0x18ffb3.split('#')[1];
        url(baseUrl(_0x18ffb3.split('#')[0]) + '/' + _0x488b73);
        _0x1f637f = fetchPost(_0x824adc);
        try {
            url(_0x1f637f.match(/<script>(.*?)\('(.*?)',.*?\);<\/script>/)[2]);
        } catch (_0x3f2563) {
            url(_0x1f637f.match(/\/player\/video.asp\?v=(.*?)"/)[1]);
        }
        switch (_0x2ae687.toLowerCase()) {
            case 'uptobox':
                url('https://uptostream.com/iframe/' + url());
                break;
            case 'sper':
                url('https://supervideo.tv/e/' + url());
                break;
            case 'fembed':
                url('https://www.fembed.net/v/' + url());
                break;
            case 'vidmoly':
                url('https://vidmoly.me/embed-' + url() + '.html');
                break;
            case 'mailru':
                var _0x5e8744 = url().split('/');
                url('https://my.mail.ru/' + _0x5e8744[0] + '/' + _0x5e8744[1] + '/video/embed/' + _0x5e8744[2] + '/' + _0x5e8744[3]);
                break;
            case 'ok.ru':
                url('https://odnoklassniki.ru/videoembed/' + url());
                break;
            case 'streamlare':
                url('https://streamlare.com/e/' + url());
                break;
            case 'streamsb':
                url('https://streamsb.net/e/' + url());
                break;
            case 'filemoon':
                url('https://filemoon.sx/e/' + url());
                break;
        }
        parser();
    }
}
function wfilmizle() {
    var _0x5d8f61 = url();
    var _0x5081cb = fetch();
    _0x5081cb = _0x5081cb.match('keremiya_part(.*?)wide-button')[1];
    var _0x3f5d60 = {
        WHDPlayer: url()
    };
    try {
        var _0x53515d = matchAll(_0x5081cb, /<a href="(.*?)" class="post-page-numbers"><span>(.*?)<\/span>/g);
        for (let _0x536383 = 0; _0x536383 < _0x53515d.length; _0x536383++) {
            if (_0x53515d[_0x536383][2].toLowerCase() != 'fragman') {
                if (_0x3f5d60.hasOwnProperty(_0x53515d[_0x536383][2])) {
                    _0x3f5d60[_0x53515d[_0x536383][2] + '' + _0x536383] = _0x53515d[_0x536383][1];
                } else {
                    _0x3f5d60[_0x53515d[_0x536383][2]] = _0x53515d[_0x536383][1];
                }
            }
        }
    } catch (_0x4d2de6) { }
    if (!_0x5d8f61.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x3f5d60));
    } else {
        url(_0x5d8f61.split('#')[1]);
        _0x5081cb = fetch();
        try {
            _0x5081cb = _0x5081cb.match(/<iframe loading="lazy".*?src="(.*?)"/)[1];
            url(_0x5081cb);
        } catch (_0x6fd9b1) {
            _0x5081cb = _0x5081cb.match(/<iframe(?![^>]*\bid\b)[^>]*?src=["'](.*?)["']/)[1];
            if (_0x5081cb.startsWith('//')) {
                _0x5081cb = 'https:' + _0x5081cb;
            }
            if (_0x5081cb.includes('/v/')) {
                var _0x3e1571 = _0x5081cb.split('/');
                _0x3e1571[2] = 'fembed.com';
                url('');
                for (var _0x208aca = 0; _0x208aca < _0x3e1571.length; _0x208aca++) {
                    if (_0x208aca != 0) {
                        url(url() + '/');
                    }
                    url(url(_0x3e1571[_0x208aca]));
                }
            }
            if (_0x5081cb.includes('hdplayersystem.live')) {
                var _0x238a51 = '';
                if (_0x5081cb.includes('/video/')) {
                    _0x238a51 = _0x5081cb.replace('https://hdplayersystem.live/video/', '');
                } else {
                    _0x238a51 = _0x5081cb.split('data=')[1];
                }
                url('https://hdplayersystem.live/player/index.php?data=' + _0x238a51 + '&do=getVideo');
                headers('X-Requested-with', 'XMLHttpRequest');
                headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                _0x5081cb = fetchPost("{hash:\"" + _0x238a51 + "\", r:\"" + url() + "\"}");
                try {
                    var _0x4cba4e = JSON.parse(_0x5081cb);
                    url(_0x4cba4e.videoSource);
                } catch (_0x3fe1d2) {
                    error(_0x3fe1d2.message);
                }
            }
            g.deleteHeader('Content-Type');
            g.deleteHeader('X-Requested-with');
            headers('Accept', '*/*');
        }
        parser();
    }
}
function wikiflix() {
    var _0x3a173c = url();
    var _0x56c268 = _0x3a173c.split('#');
    var _0x4cd287 = url().split('?')[1];
    var _0x4dfebd = _0x4cd287.split('&');
    var _0x346832 = {};
    if (_0x4dfebd.length > 0) {
        for (var _0x1ae6bb = 0; _0x1ae6bb < _0x4dfebd.length; _0x1ae6bb++) {
            _0x346832['Kaynak ' + (_0x1ae6bb + 1)] = _0x4dfebd[_0x1ae6bb];
        }
    } else {
        error('Wikiflix 1957');
    }
    if (!_0x3a173c.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(_0x346832));
    } else {
        var _0x26a87e = url().split('#')[_0x56c268.length - 2] + '';
        if (_0x26a87e.startsWith('m_')) {
            _0x26a87e = url().split('#')[_0x56c268.length - 2] + '';
        }
        if (_0x26a87e.length == 11) {
            url('https://www.youtube.com/watch?v=' + _0x26a87e);
        } else {
            url('https://commons.m.wikimedia.org/wiki/File:' + _0x26a87e + '?embedplayer=yes');
            data = fetch() + '';
            data = data + '';
            var _0x3025f7 = (data + '').match(/(https[^"]+vp9.webm)/)[1];
            url(_0x3025f7);
            try {
                var _0x8f8806 = matchAll(data + '', /\/w\/[^"]+vtt/g);
                for (let _0x5f450a = 0; _0x5f450a < _0x8f8806.length; _0x5f450a++) {
                    if (_0x8f8806[_0x5f450a][1].includes('lang=en')) {
                        sub(_0x8f8806[_0x5f450a][1]);
                        break;
                    }
                }
            } catch (_0x287c9d) {
                error(_0x287c9d.message);
            }
        }
        parser();
    }
}
function womantv() {
    url(JSON.parse(fetch('https://appie.vidpanel.com/wmtv/video/live')).video);
    parser();
}
function xcine() {
    var _0x47eb60 = url();
    var _0x282a22 = '';
    try {
        _0x282a22 = url().split('html-')[1].split('x')[1];
    } catch (_0x460490) { }
    url(url().split('html-')[0]) + 'html';
    var _0x127c15 = matchAll(fetch(), /<span\s*data-link="(.*?)"><i><\/i>\s*(.*?)\s*<\/span/g);
    if (_0x127c15.length == 0) {
        _0x127c15 = matchAll(fetch(), new RegExp("as*href=\"#\"s*id=\".*?1_" + _0x282a22 + "\"s*data-link=\"(.*?)\">s*(.*?)s*</a>", 'g'));
    }
    streamUrls = {};
    for (let _0x3d67cb = 0; _0x3d67cb < _0x127c15.length; _0x3d67cb++) {
        if (_0x127c15[_0x3d67cb][2] != 'Trailer') {
            streamUrls[_0x127c15[_0x3d67cb][2]] = _0x127c15[_0x3d67cb][1];
        }
    }
    if (Object.keys(streamUrls).length > 0 && !_0x47eb60.includes('#')) {
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    } else {
        url(fixUrl(url().split('#')[1]));
        parser();
    }
}
function xhamster() {
    var _0x1e2b3f = baseUrl(url());
    var _0x212fa7 = url().split('-');
    _0x212fa7 = _0x212fa7[_0x212fa7.length - 1];
    url(_0x1e2b3f + '/embed/' + _0x212fa7);
    var _0x359d07 = JSON.parse(fetch().match(/"sources":(.*?\}\}\}),/)[1]);
    for (let _0x4e8e68 = 0; _0x4e8e68 < _0x359d07.standard.h264.length; _0x4e8e68++) {
        if (_0x359d07.standard.h264[_0x4e8e68].quality == 'auto') {
            url(_0x359d07.standard.h264[_0x4e8e68].url);
            break;
        }
    }
    parser();
}
function xnxx() {
    url(fetch().match(/setVideoHLS\('(.*?)'/)[1]);
    parser();
}
function xvideos() {
    url(fetch().match(/setVideoHLS\('(.*?)'/)[1]);
    parser();
}
function yabancidizi() {
    var _0x411f1e = url();
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    var _0x58e2a2 = {
        _0x450ef4: _0x3395b9
    };
    if (!_0x411f1e.includes('#')) {
        var _0x2a5d02 = fetch();
        _0x2a5d02 = _0x2a5d02.match(/series-tabs(.*?)mofycon/)[1];
        var _0xaf92ab;
        if (_0xaf92ab === undefined) {
            _0xaf92ab = 'udys=123;';
        }
        var _0x2a65bc = 1;
        if (g.getLang() == 0) {
            _0x2a65bc = 2;
        }
        try {
            var _0x31a5c2 = matchAll(_0x2a5d02, /data-eid="(.*?)"\s*data-type="(.*?)"/g);
            for (let _0x5992ea = 0; _0x5992ea < _0x31a5c2.length; _0x5992ea++) {
                if (_0x31a5c2[_0x5992ea][2] == '' + _0x2a65bc) {
                    url(_0x31a5c2[_0x5992ea][1]);
                }
            }
        } catch (_0x2f68ec) {
            error(_0x2f68ec.message);
        }
        if (url() == _0x411f1e) {
            error('Yabanci Dizi - 2029');
        } else {
            var _0x5a5e88 = encodeURIComponent(url());
            var _0x118af2 = 'lang=' + _0x2a65bc + '&episode=' + _0x5a5e88 + '&type=langTab';
            url(baseUrl(_0x411f1e) + '/ajax/service');
            headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            headers('X-Requested-With', 'XMLHttpRequest');
            headers('Cookie', _0xaf92ab);
            _0x2a5d02 = fetchPost(_0x118af2);
            try {
                var _0x31a5c2 = matchAll(_0x2a5d02, /data-hash=\\"(.*?)\\"\s*data-link=\\"(.*?)\\"/g);
                for (let _0x4da100 = 0; _0x4da100 < _0x31a5c2.length; _0x4da100++) {
                    _0x118af2 = 'hash' + _0x31a5c2[_0x4da100][1].replace(/\\/g, '') + '&link=' + encodeURIComponent(_0x31a5c2[_0x4da100][2].replace(/\\/g, '')) + '&querytype=alternate&type=videoGet';
                    var _0x3395b9 = fetchPost(_0x118af2);
                    var _0x3395b9 = _0x3395b9.match(/"api_iframe":\s*"(.*?)"/)[1].replace(/\\/g, '');
                    if (_0x3395b9 != '') {
                        var _0x450ef4 = _0x3395b9.match(/\/api\/(.*?)\//)[1];
                        ;
                    }
                }
            } catch (_0x32cee6) {
                error(_0x32cee6.message);
            }
            Core.showAlternatesJS(JSON.stringify(_0x58e2a2));
        }
    } else {
        url(_0x411f1e.split('#')[1]);
        var _0x474b78 = _0x411f1e.split('#')[2];
        g.deleteHeader('Cookie');
        g.deleteHeader('X-Requested-With');
        g.deleteHeader('Content-Type');
        if (_0x474b78.toLowerCase().includes('drive')) {
            if (!g.isWebView()) {
                error('No Webview');
            }
            getWebViewOwnContentJS(url(), 'molystream', 'I AM NOT LOOKING', 'playSheilaBtn', '', 0, false, g.getHeadersJSON, 1);
        } else {
            headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            var _0x2a5d02 = fetch();
            try {
                var _0x31a5c2 = matchAll(_0x2a5d02, /<iframe(.*?)<\/iframe/g);
                for (let _0x1ddc48 = 0; _0x1ddc48 < _0x31a5c2.length; _0x1ddc48++) {
                    if (!_0x31a5c2[_0x1ddc48][1].includes('display:')) {
                        url(_0x31a5c2[_0x1ddc48][1]);
                        break;
                    }
                }
                url(url().match(/src=['"](.*?)['"]/)[1]);
            } catch (_0x441d9f) {
                error(_0x441d9f.message);
            }
            g.deleteHeader('Cookie');
            g.deleteHeader('Content-Type');
            parser();
        }
    }
}
function yabanci_dizi() {
    url('yabanci-dizi', 'yabancidizi');
    var _0xd9b6df = 'bolum-menu';
    if (url(['/film/'], 1)) {
        _0xd9b6df = 'active';
    }
    headers('User-Agent', 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36');
    url('/turkce', '');
    var _0x16508a = fetch();
    url(_0x16508a.match(/<iframe.*?allowfullscreen.*?src="(.*?)"/)[1]);
    url(fixUrl(url()));
    parser();
}
function yabantv() {
    url(fetch(fixUrl(fetch().match(/<iframe.*?src="(.*?)"/)[1])).match(/file\s*:\s*"(.*?)"/)[1]);
    parser();
}
function yesmovies() {
    if (!g.isWebView()) {
        error('No Webview');
    }
    var _0xa808c8 = url().split('#')[0];
    var _0x1edae1 = '';
    var _0x44d223 = '';
    if (url(['sezon'], 1)) {
        _0x1edae1 = url().match(/sezon-(.*?)\//)[1];
        _0x44d223 = url().match(/bolum-(.*?)?/)[1];
        _0xa808c8 = url().split('.html')[0] + '.html';
    }
    headers('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36');
    if (!url(['#'], 1)) {
        var _0x185d81 = 'bwac-btn';
        var _0x2c35f3 = 0;
        var _0x3bf240 = '';
        if (_0x44d223 != '') {
            _0x2c35f3 = -1;
            _0x3bf240 = 'ep-' + _0x44d223 + "').getElementsByTagName('a')[0];//";
            _0x185d81 = "btnp').getElementsByTagName('a')[0];//";
        }
        getWebViewOwnContentJS(_0xa808c8, 'master.m3u8', 'en.vtt', _0x185d81, _0x3bf240, _0x2c35f3, false, g.getHeadersJSON, 2);
    } else {
        if (url(['.vtt'], 1)) {
            var _0x2838fc = url().split('#')[2];
            try {
                fetch(_0x2838fc);
            } catch (_0x5834b5) {
                _0x2838fc = '';
            }
            if (_0x2838fc != '') {
                headers('forHelper', 'c2V5L3RyYW5zbGF0ZS8');
                headers('forHelper2', 'Q2V2aXJpQUkuVFIucGhwP3VybD0');
                var _0x54a9bf = 'm_';
                if (_0x44d223 != '') {
                    _0x54a9bf = 't_';
                }
                sub(subHelp(_0x2838fc, _0x54a9bf + 'ym'));
            }
        }
        url(url().split('#')[1]);
        if (url(['http'], 1)) {
            parser();
        }
    }
}
function yirmidort() {
    url(fetch().match(/source\s*src="(.*?)"/)[1]);
    parser();
}
function yoltv() {
    url(fetch().match(/data-item.*?(https:.*?\.m3u8)/)[1].replace(/\\/, ''));
    parser();
}
function youporn() {
    var _0x3f2edc = fetch().match(/playervars:\s*(.*?)\}\s*page_params/)[1];
    var _0x3967dd = {
        Alone: _0xcd88b3[_0x3baf9e].videoUrl,
        Alone: _0xcd88b3[_0x165c4a].videoUrl
    };
    var _0xcd88b3 = JSON.parse(_0x3f2edc).mediaDefinitions;
    for (let _0x3baf9e = 0; _0x3baf9e < _0xcd88b3.length; _0x3baf9e++) {
        try {
            _0x3967dd[_0xcd88b3[_0x3baf9e].format] = _0xcd88b3[_0x3baf9e].videoUrl;
        } catch (_0x24043a) {
            ;
        }
    }
    if (Object.keys(_0x3967dd).indexOf('hls') !== -1) {
        var _0xcd88b3 = JSON.parse(fetch(_0x3967dd.hls));
        _0x3967dd = {};
        for (let _0x165c4a = 0; _0x165c4a < _0xcd88b3.length; _0x165c4a++) {
            try {
                _0x3967dd[_0xcd88b3[_0x165c4a].quality] = _0xcd88b3[_0x165c4a].videoUrl;
            } catch (_0x1d18e9) {
                ;
            }
        }
        var _0x1107df = ['240', '360', '480', '720', '1080', 'Alone'];
        var _0x56ff64 = -1;
        var _0x9c4d26 = -1;
        for (let _0x2423fb = 0; _0x2423fb < Object.keys(_0x3967dd).length; _0x2423fb++) {
            try {
                var _0x97b1f = Object.keys(_0x3967dd)[_0x2423fb];
                var _0x453dd9 = _0x1107df.indexOf(_0x97b1f);
                if (_0x56ff64 < _0x453dd9) {
                    _0x56ff64 = _0x453dd9;
                    _0x9c4d26 = _0x97b1f;
                }
            } catch (_0x3f156a) { }
        }
        if (_0x56ff64 != -1) {
            url(_0x3967dd[_0x9c4d26].replace(/\\/g, ''));
        }
    } else {
        if (Object.keys(_0x3967dd).indexOf('Alone') !== -1) {
            url(_0x3967dd.Alone);
        } else {
            url(_0x3967dd.mp4);
        }
    }
    url(fixUrl(url()));
    parser();
}
function youtube() {
    if (url(['youtubeiptvs'], 0)) {
        var _0x495ea8 = 0;
        if (url(['#'], 0)) {
            _0x495ea8 = parseInt(url().split('#')[1]);
        }
        url(url().replace('youtubeiptvs', 'https://www.youtube.com'));
        var _0x4436c8 = fetch();
        _0x4436c8 = matchAll(_0x4436c8, /LIVE.*?"addedVideoId":"(.*?)","/g);
        for (let _0xd3bdc1 = 0; _0xd3bdc1 < _0x4436c8.length; _0xd3bdc1++) {
            if (_0xd3bdc1 == _0x495ea8) {
                url(_0x4436c8[_0xd3bdc1][1]);
            }
        }
        url('https://m.youtube.com/watch?v=' + url());
    }
    _0x4436c8 = fetch();
    if (_0x4436c8.includes('hlsManifestUrl')) {
        url(_0x4436c8.match(/hlsManifestUrl":"(.*?)"/)[1]);
        headers('Referer', 'https://youtube.com');
    } else {
        var _0x41935d = url().match(/(?:v=|embed\/)(.*?)(?:&|$)/)[1];
        try {
            _0x41935d = _0x41935d.split('?rel')[0];
        } catch (_0x2f207e) { }
        try {
            var _0x165ae3 = fetch('https://ab.cococococ.com/ajax/download.php?copyright=0&format=1080&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3D' + _0x41935d + '&api=dfcb6d76f2f6a9894gjkege8a4ab232222');
            _0x165ae3 = fetch('https://p.oceansaver.in/ajax/progress.php?id=' + JSON.parse(_0x165ae3).id);
            var _0x59a42a = JSON.parse(_0x165ae3).download_url;
            if (_0x59a42a == null || _0x59a42a == 'null') {
                var _0x165ae3 = fetch('https://ab.cococococ.com/ajax/download.php?copyright=0&format=720&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3D' + _0x41935d + '&api=dfcb6d76f2f6a9894gjkege8a4ab232222');
                _0x165ae3 = fetch('https://p.oceansaver.in/ajax/progress.php?id=' + JSON.parse(_0x165ae3).id);
                var _0x59a42a = JSON.parse(_0x165ae3).download_url;
                if (_0x59a42a == null || _0x59a42a == 'null') {
                    throw new Error('m');
                }
            }
            url(_0x59a42a);
            print(_0x59a42a);
            g.setMediaType('mp4');
        } catch (_0x48cb12) {
            headers('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
            headers('Referer', 'https://www.y2mate.com/');
            _0x4436c8 = fetchPost('k_query=' + url() + '&k_page=home&kl=en&q_auto=', 'https://www.y2mate.com/mates/analyzeV2/ajax');
            var _0x3775aa = JSON.parse(_0x4436c8);
            k = _0x3775aa.links.mp4.auto.k;
            _0x4436c8 = fetchPost('vid=' + _0x41935d + '&k=' + encodeURIComponent(k), 'https://www.y2mate.com/mates/convertV2/index');
            _0x3775aa = JSON.parse(_0x4436c8);
            url(_0x3775aa.dlink + '#.mp4');
        }
    }
    parser();
}
function url(_0x200ded, _0x384825) {
    var _0x4f18a7 = g.getUrl() + '';
    if (arguments.length === 0) {
        return _0x4f18a7;
    } else {
        if (arguments.length === 1) {
            return g.setUrl(_0x200ded);
        } else {
            if (arguments.length === 2) {
                if (typeof _0x384825 === 'string') {
                    url(_0x4f18a7.replace(_0x200ded, _0x384825));
                    return url();
                } else {
                    var _0x20162a;
                    for (let _0x6d384f = 0; _0x6d384f < _0x200ded.length; _0x6d384f++) {
                        if (!_0x4f18a7.includes(_0x200ded[_0x6d384f]) && _0x384825 == 0 || _0x4f18a7.includes(_0x200ded[_0x6d384f]) && _0x384825 == 1 || _0x4f18a7.includes(_0x200ded[_0x6d384f]) && _0x384825 == 2) {
                            _0x20162a = _0x384825 == 1;
                            break;
                        }
                    }
                    return ((_0x20162a == null ? _0x384825 == 0 : _0x20162a) || _0x384825 == 2) && !((_0x20162a == null ? _0x384825 == 0 : _0x20162a) && _0x384825 == 2);
                }
            }
        }
    }
}
function sub(_0x55cad6) {
    if (arguments.length === 0) {
        return g.getSub() + '';
    } else {
        if (arguments.length === 1) {
            if (typeof _0x55cad6 === 'object') {
                _0x55cad6 = JSON.stringify(_0x55cad6);
            }
            g.setSub(_0x55cad6);
        }
    }
}
function isPreview(_0x24baed) {
    try {
        if (arguments.length === 0) {
            return g.isPreview();
        } else {
            if (arguments.length === 1) {
                if (typeof _0x24baed === 'object') {
                    _0x24baed = JSON.stringify(_0x24baed);
                }
                g.setPreview(_0x24baed);
            }
        }
    } catch (_0x216881) {
        return false;
    }
}
function headers(_0x20026, _0x5a81f5) {
    if (arguments.length === 0) {
        return g.getHeaderSize();
    } else {
        if (arguments.length === 1) {
            return g.getHeader(_0x20026) + '';
        } else {
            if (arguments.length === 2) {
                g.setHeader(_0x20026, _0x5a81f5);
            }
        }
    }
}
function lang() {
    return g.getLang();
}
function isWebView() {
    return g.isWebView();
}
function consolelog(_0x5037f8, _0x31073d) {
    if (typeof _0x5037f8 === 'object' && _0x5037f8 !== null) {
        _0x5037f8 = JSON.stringify(_0x5037f8);
    }
    if (typeof _0x31073d === 'object' && _0x31073d !== null) {
        _0x31073d = JSON.stringify(_0x31073d);
    }
    Core.consolelog(_0x5037f8 + ': ' + _0x31073d);
}
function fetch(_0x381d59) {
    var _0x1e578c = url();
    if (arguments.length === 1) {
        url(_0x381d59);
    }
    consolelog(url());
    var _0x2969a9 = Core.fetch();
    if (arguments.length === 1) {
        url(_0x1e578c);
    }
    if (_0x2969a9 == 'Error') {
        error('Fetch Error: ' + url() + ' / ' + _0x381d59);
    } else {
        return _0x2969a9 + '';
    }
}
function fetchResponseHeader(_0x3c379a, _0x48db6d, _0x197a9c) {
    var _0x136b32 = Core.fetchResponseHeader(_0x3c379a, _0x48db6d, _0x197a9c);
    if (_0x136b32 == 'Error') {
        error('Fetch Response Error:');
    } else {
        return _0x136b32;
    }
}
function fetchPost(_0x49feef, _0x423efe) {
    var _0x35265b = url();
    if (arguments.length === 2) {
        url(_0x423efe);
    }
    consolelog('Fetch Post: ', url());
    consolelog(_0x49feef, _0x423efe);
    var _0x35a121 = Core.fetchPost(_0x49feef) + '';
    if (arguments.length === 2) {
        url(_0x35265b);
    }
    if (_0x35a121 == 'Error') {
        error('FetchPost Error:' + url() + ' / ' + _0x423efe);
    } else {
        consolelog(5086, _0x35a121 + '');
        return _0x35a121;
    }
}
function getWebViewOwnContentJS(_0x35fe25, _0x5d407c, _0x557bc2, _0x2b3b0a, _0x550bf1, _0x14ebbc, _0x294e0b, _0x16e14d, _0x1c7d30) {
    Core.getWebViewOwnContentJS(_0x35fe25, _0x5d407c, _0x557bc2, _0x2b3b0a, _0x550bf1, _0x14ebbc, _0x294e0b, _0x16e14d, _0x1c7d30);
}
function error(_0x19540e) {
    throw new Error(_0x19540e);
}
function moxyUnpacker(_0x4be5f4, _0x267339) {
    _0x4be5f4 = _0x4be5f4.split('|');
    a = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    var _0x35a09b = '';
    _0x267339 = _0x267339.split("\\\\");
    for (var _0x5b938f = 1; _0x5b938f < _0x267339.length; _0x5b938f++) {
        _0x35a09b += "\\\\" + _0x4be5f4[a.indexOf(_0x267339[_0x5b938f])];
    }
    return hexToString(_0x35a09b);
}
function matchAll(_0x26e00c, _0x337f36) {
    var _0x58bbf9 = [];
    var _0x1b6bc2;
    while ((_0x1b6bc2 = _0x337f36.exec(_0x26e00c)) !== null) {
        _0x58bbf9.push(_0x1b6bc2);
    }
    return _0x58bbf9;
}
function urlChanger(_0x366c8b) {
    consolelog('--------------------Url Changer Start--------------------');
    consolelog(18, url());
    if (url().includes('#')) {
        var _0x35b8ef = url().split('#');
        _0x35b8ef[0] = _0x366c8b;
        url(_0x35b8ef.join('#'));
    } else {
        url(_0x366c8b);
    }
    consolelog(18, url());
    consolelog('--------------------Url Changer Start--------------------');
}
function logicalXor(_0x3d6744, _0x4442f9) {
    return (_0x3d6744 || _0x4442f9) && !(_0x3d6744 && _0x4442f9);
}
function trim(_0x1f61e2) {
    return _0x1f61e2.split(' ').join('').split("\n").join('').split("\t").join('');
}
function getAes(_0xa12cec, _0x1387fd) {
    var _0x2a8e17 = fetch(atob(headers('base') + '==')) + '';
    _0x2a8e17 = atob(reverseString(_0x2a8e17)) + '';
    var _0x5b4afd = fetchPost('v1=' + encodeURIComponent(_0xa12cec) + '&v2=' + encodeURIComponent(_0x1387fd), _0x2a8e17 + 'sey/back/v2/parser/aes.php') + '';
    return _0x5b4afd;
}
function hdmomplayer(_0x1ad351, _0x2d505d) {
    var _0x31227f = getAes(_0x1ad351, _0x2d505d);
    var _0x287cfd = _0x31227f.match(/video_location.*?(https.*?) /)[1];
    var _0x5dc8d0 = '';
    try {
        var _0x3d1458 = matchAll(_0x31227f, /file.*?(\/upload.*?.vtt)/g);
        for (let _0x56734d = 0; _0x56734d < _0x3d1458.length; _0x56734d++) {
            if (_0x3d1458[_0x56734d][1].includes('tur')) {
                _0x5dc8d0 = baseUrl(_0x287cfd) + _0x3d1458[_0x56734d][1];
                break;
            }
        }
    } catch (_0x4396f5) { }
    return [_0x287cfd, _0x5dc8d0];
}
function hex2a(_0x5efccd) {
    var _0x595f51 = _0x5efccd.toString();
    var _0x1a91e6 = '';
    for (var _0x5aedea = 0; _0x5aedea < _0x595f51.length; _0x5aedea += 2) {
        _0x1a91e6 += String.fromCharCode(parseInt(_0x595f51.substr(_0x5aedea, 2), 16));
    }
    return _0x1a91e6;
}
function hexToString(_0x14a49d) {
    var _0x12380e = _0x14a49d.split("\\x");
    var _0x381fd5 = '';
    for (var _0x1f3997 = 1; _0x1f3997 < _0x12380e.length; _0x1f3997++) {
        var _0x45c1db = parseInt(_0x12380e[_0x1f3997], 16);
        _0x381fd5 += String.fromCharCode(_0x45c1db);
    }
    return _0x381fd5;
}
function subHelp(_0x43e60e, _0x32a008) {
    data = fetch(atob(headers('base') + '=='));
    data = atob(reverseString(data + ''));
    h1 = atob(headers('forHelper') + '=');
    h2 = atob(headers('forHelper2') + '=');
    if (_0x32a008 == 'vumoo') {
        id = _0x43e60e.match(/subtitle\/(.*?).vtt/)[1];
    } else {
        if (_0x32a008 == 't_hd' || _0x32a008 == 'm_hd' || _0x32a008 == 't_ym' || _0x32a008 == 'm_ym') {
            id = _0x43e60e.split('/')[4];
        } else {
            if (_0x32a008.includes('123chill')) {
                raw = _0x32a008.split('-');
                id = raw[0];
                _0x32a008 = raw[1];
            }
        }
    }
    var _0x47f971 = fetch(data + '' + h1 + h2 + _0x43e60e + '&id=' + _0x32a008 + '_' + id);
    _0x47f971 = [];
    var _0x42cd87 = {
        'lang': "AI Ã‡eviri(TR)",
        'url': data + h1 + _0x32a008 + '_' + id + '.vtt'
    };
    _0x47f971.push(_0x42cd87);
    return JSON.stringify(_0x47f971);
}
function reverseString(_0x481c74) {
    var _0x5ea30e = '';
    for (var _0x15da45 = _0x481c74.length - 1; _0x15da45 >= 0; _0x15da45--) {
        _0x5ea30e += _0x481c74[_0x15da45];
    }
    return _0x5ea30e;
}
function atob(_0x15c561) {
    var _0x2ccc0a = String(_0x15c561).replace(/=+$/, '');
    var _0x278ab6 = '';
    if (_0x2ccc0a.length % 4 == 1) {
        throw new Error("'atob' failed: The string to be decoded is not correctly encoded.");
    }
    var _0x21f522 = 0;
    var _0x1d486d;
    var _0x27a0c8;
    for (var _0x4393d8 = 0; _0x27a0c8 = _0x2ccc0a.charAt(_0x4393d8++); ~_0x27a0c8 && (_0x1d486d = _0x21f522 % 4 ? _0x1d486d * 64 + _0x27a0c8 : _0x27a0c8, _0x21f522++ % 4) ? _0x278ab6 += String.fromCharCode(255 & _0x1d486d >> (-2 * _0x21f522 & 6)) : 0) {
        _0x27a0c8 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='.indexOf(_0x27a0c8);
    }
    return _0x278ab6;
}
function baseUrl(_0x10ad4d, _0x4046ba) {
    _0x10ad4d = _0x10ad4d.replace('boncuk44', '');
    _0x10ad4d = _0x10ad4d.replace('boncuk45', '');
    _0x10ad4d = _0x10ad4d.replace('syrtrk', '');
    var _0x386cc9 = _0x10ad4d.split('/');
    var _0x1c7c69 = _0x386cc9[0];
    var _0x472aa5 = _0x386cc9[2];
    var _0x10ad4d = _0x1c7c69 + '//' + _0x472aa5;
    return _0x4046ba ? _0x472aa5 : _0x10ad4d;
}
function fixUrl(_0x4b1d34) {
    if (!_0x4b1d34.startsWith('http')) {
        var _0x122ca0 = 'https';
        if (!_0x4b1d34.startsWith(':')) {
            _0x122ca0 = _0x122ca0 + ':';
            if (!_0x4b1d34.startsWith('//')) {
                _0x122ca0 = _0x122ca0 + '//';
            }
        }
        _0x4b1d34 = _0x122ca0 + _0x4b1d34;
    }
    return _0x4b1d34;
}
function getKeyByValue(_0x1f33ae, _0x132791) {
    return Object.keys(_0x1f33ae).find(_0x8844e0 => _0x1f33ae[_0x8844e0] === _0x132791);
}
function unPack(_0x1dc9ae) {
    function _0x1b4744(_0xb4f4c5) {
        try {
            var _0x1383c4 = 0;
            var _0x191338 = -1;
            var _0x255f6e = '';
            for (var _0x3c4072 = 0; _0x3c4072 < _0xb4f4c5.length; _0x3c4072++) {
                if (_0xb4f4c5[_0x3c4072].indexOf('{') != -1) {
                    _0x1383c4++;
                }
                if (_0xb4f4c5[_0x3c4072].indexOf('}') != -1) {
                    _0x1383c4--;
                }
                if (_0x191338 != _0x1383c4) {
                    _0x191338 = _0x1383c4;
                    _0x255f6e = '';
                    while (_0x191338 > 0) {
                        _0x255f6e += "\t";
                        _0x191338--;
                    }
                    _0x191338 = _0x1383c4;
                }
                _0xb4f4c5[_0x3c4072] = _0x255f6e + _0xb4f4c5[_0x3c4072];
            }
        } finally {
            _0x1383c4 = null;
            _0x191338 = null;
            _0x255f6e = null;
        }
        return _0xb4f4c5;
    }
    eval('with(env) {' + _0x1dc9ae + '}');
    _0x1dc9ae = (_0x1dc9ae + '').replace(/;/g, ";\n").replace(/{/g, "\n{\n").replace(/}/g, "\n}\n").replace(/\n;\n/g, ";\n").replace(/\n\n/g, "\n");
    _0x1dc9ae = _0x1dc9ae.split("\n");
    _0x1dc9ae = _0x1b4744(_0x1dc9ae);
    _0x1dc9ae = _0x1dc9ae.join("\n");
    return _0x1dc9ae;
}
function findRealChar(_0x587600) {
    if (/[a-zA-Z]/.test(_0x587600.charAt(0))) {
        if (_0x587600.toLowerCase().charAt(0) < 'n' || _0x587600.toLowerCase().charAt(0) === "Ä±") {
            var _0x38726c = _0x587600.charCodeAt(0);
            _0x38726c += 13;
            return String.fromCharCode(_0x38726c);
        } else {
            var _0x38726c = _0x587600.charCodeAt(0);
            _0x38726c -= 13;
            return String.fromCharCode(_0x38726c);
        }
    } else {
        return _0x587600;
    }
}
function replaceCustomChars(_0x572c08) {
    var _0x111d53 = '';
    for (var _0x4d90d2 = 0; _0x4d90d2 < _0x572c08.length; _0x4d90d2++) {
        var _0x572d4a = _0x572c08[_0x4d90d2];
        var _0xc23b62 = 'xyzabcdefghijklmnopqrstuvw'.indexOf(_0x572d4a);
        if (_0xc23b62 !== -1) {
            _0x111d53 += 'abcdefghijklmnopqrstuvwxyz '[_0xc23b62];
        } else {
            _0x111d53 += _0x572d4a;
        }
    }
    return _0x111d53;
}
function findSource(_0x55374a) {
    var _0x3926ae = fetch(atob(headers('base') + '==')) + '';
    _0x3926ae = atob(reverseString(_0x3926ae)) + '';
    var _0x2a09b4 = fetch(_0x3926ae + '' + 'sey/back/sourceViaLink.php?link=' + encodeURIComponent(_0x55374a));
    if (_0x2a09b4.includes('http')) {
        url(_0x2a09b4);
    } else {
        url('');
    }
}
