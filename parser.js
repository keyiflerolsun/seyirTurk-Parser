// https://beytepe.tk//sey/back/v2/parser/parsers.js

function parser(url, lang = 1, sub = "", headers = {}){
    try{
        if(!headers.hasOwnProperty("User-Agent")){
            headers = {};   
            headers["User-Agent"] = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
            headers["Referer"] = baseUrl(url);
        }

        url = url.replace("?wfilmizle", "");

        if(url.includes("atv.com.tr") && !url.includes("canli-yayin")){
            atv(url, headers);
        }else if(url.includes("filmmakinesi")){
            filmmakinesi(url, lang, headers);
        }else if(url.includes("filmmodu")){
            filmmodu(url, lang, headers);
        }else if(url.includes("setfilmizle")){
            setfilmizle(url, lang, headers);
        }else if(url.includes("dizilla")){
            dizilla(url, lang, headers);
        }else if(url.includes("dizimom")){
            dizimom(url, lang, headers);
        }else if(url.includes("filmekseni")){
            filmekseni(url, lang, headers);
        }else if(url.includes("720pizle")){
            izle720p(url, lang, headers);
        }else if(url.includes("kultfilmler")){
            kultfilmler(url, lang, headers);
        }else if(url.includes("filmkovasi")){
            filmkovasi(url, lang, headers);
        }else if(url.includes("filmatek")){
            filmatek(url, lang, headers);
        }else if(url.includes("dizimia")){
            dizimia(url, lang, headers);
        }else if(url.includes("diziyou") && !url.includes(".m3u8")){
            diziyou(url, lang, headers);
        }else if(url.includes("movie4k")){
            movie4k(url, lang, headers);
        }else if(url.includes("vimeo") && !url.includes(".m3u8")){
            vimeo(url, lang, headers);
        }else if(url.includes("/s.to") || url.includes("aniworld.to")){
            sto(url, lang, headers);
        }else if(url.includes("siyahfilmizle")){
            siyahfilmizle(url, lang, headers);
        }else if(url.includes("sinemafilmizle")){
            sinemafilmizle(url, lang, headers);
        }else if(url.includes("dizilab")){
            dizilab(url, lang, headers);
        }else if(url.includes("123movies")){
            movies123(url, lang, headers);
        }else if(url.includes("wikiflix")){
            wikiflix(url, lang, headers);
        }else if(url.includes("hdtoday")){
            hdtoday(url, lang, headers);
        }else if(url.includes("dizitime")){
            dizitime(url, lang, headers);
        }else if(url.includes("dizibox")){
            dizibox(url, lang, headers);
        }else if(url.includes("yabancidizi")){
            yabancidizi(url, lang, headers);
        }else if(url.includes("dizipub")){
            dizipub(url, lang, headers);
        }else if(url.includes("dizipal")){
            dizipal(url, lang, headers);
        }else if(url.includes("dizirix")){
            dizirix(url, lang, headers);
        }else if(url.includes("onlinedizi")){
            onlinedizi(url, lang, headers);
        }else if(url.includes("wfilmizle")){
            wfilmizle(url, lang, headers);
        }else if(url.includes("yabanci-dizi") || url.includes("roketdizi")){
            yabanci_dizi(url, lang, headers);
        }else if(url.includes("diziyo") && !url.includes("diziyou")){
            diziyo(url, lang, headers);
        }else if(url.includes("sinefy")){
            sinefy(url, lang, headers);
        }else if(url.includes("filmizlesene")){//FHD not working
            filmizlesene(url, lang, headers);
        }else if(url.includes("diziroll")){
            diziroll(url, lang, headers);
        }else if(url.includes("sezonlukdizi")){
            sezonlukdizi(url, lang, headers);
        }else if(url.includes("jetfilm")){
            //jetfilm(url, lang, headers);
        }else if(url.includes("hdfilmcehennemi")){
            if(url.includes("syrtrk")){
                hdfilmcehennemi2(url, lang, headers);
            }else{

            }
        }else if((url.includes("contentx") || url.includes("playru") || url.includes("filese") || url.includes("hotlinger")|| url.includes("//four") || url.includes("pichive")) && !url.includes("m.php")){
            contentx(url, lang, headers);
        }else if(url.includes("diziwatch")){
            diziwatch(url, lang, headers);
        }else if(url.includes("realfilmizle")){
            realfilmizle(url, lang, headers);
        }else if(url.includes("filmmax")){
            filmmax(url, lang, headers);
        }else{
            var bypass = false;

            if((url.includes("contentx") || url.includes("playru") || url.includes("filese") || url.includes("hotlinger")|| url.includes("//four") || url.includes("pichive"))){
                bypass = true;
            }
            
            Core.playVideoJS(url, sub, JSON.stringify(headers), bypass);
        }
    }catch(ignored){
        var error = ignored.message;
        error = encodeURIComponent(error);
        var details = encodeURIComponent(url + ", " + lang);
        fetch("http://beytepe.tk/sey/back/v2/parser/notifier.php?error=" + error + "&details=" + details, headers);
        Core.showOnError();
    }
}

function atv(url, headers){
    headers["Referer"] = "http://www.atv.com.tr/"; 
    var data = fetch(url, headers);

    try{
        var m1 = data.match("url:\s*\"(https://videojs.tmgrup.com.tr/.*?)\"")[1];
        data = fetch(m1, headers);
        var ms = data.match("\"VideoUrl\":\"([^\"]+)\".*?\"VideoSmilUrl\":\"([^\"]+)\"");
        var url1 = encodeURIComponent(ms[1]);
        var url2 = encodeURIComponent(ms[2]);
        var url = "https://securevideotoken.tmgrup.com.tr/webtv/secure?url=" + url1 + "&url2=" + url2;
        m1 = fetch(url, headers);

        json = JSON.parse(m1);
        if(json.hasOwnProperty("Url")){
            url = json["Url"];
        }else{
            if(json.hasOwnProperty("Alone")){
                url = json["Alone"];
            }else if(json.hasOwnProperty("1080p")){
                url = json["1080p"];
            }else if(json.hasOwnProperty("720p")){
                url = json["720p"];
            }else if(json.hasOwnProperty("480p")){
                url = json["480p"];
            }
        }

    }catch(ignored){
        error(ignored.message);
    }
    
    parser(url);
}

function contentx(url, lang, headers){
    if(!url.includes("filese")){

        if(!url.startsWith("http"))
            url = "https:" + url;

        var data = fetch(url, headers);

        var sub = "";

        try{
            var alts = [...data.matchAll(/"file"\s*:\s*"(.*?)"\s*,\s*"label"\s*:\s*"(.*?)"/g)];
            for(let i = 0; i < alts.length; i++){
                if(alts[i][2].toLowerCase().includes("rk") || alts[i][2].toLowerCase().includes("turkish")){
                    sub = alts[i][1].replace(/\\\//g, "/");
                }
            }
        }catch(ignored){
            error(ignored.message);
        }

        data = data.match(/window.openPlayer\((.*?)\)/)[1].split(",")[0].replace(/'/g, "");

        data = fetch(baseUrl(url) + "/source2.php?v=" + data, headers);

        var j = JSON.parse(data).playlist;

        for(var i = 0; i < j.length; i++){
            if(lang == 1 && (j[i].sources[0].title.includes("Türkçe Dublaj") || j[i].sources[0].title.includes("Orijinal"))){
                url = j[i].sources[0].file;
            }else if(lang == 0 && (j[i].sources[0].title.includes("Türkçe Altyazı") || j[i].sources[0].title.includes("Orijinal"))){
                url = j[i].sources[0].file;
            }
        }

        headers["Referer"] = baseUrl(url);
        
        parser(url, lang, sub, headers);
    }
}

function dizibox(url, lang, headers){
    var initialUrl = url;

    var cookie = Core.saveCookie(url, "dbx", "woca-linkpages");

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    data = data.match(/woca-linkpages-dd selectBox(.*?)\/select/)[1];

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/(?:value=["']|href=["'])(.*?)["'].*?>(.*?)</g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][1];
            var prov = alts[i][2];
            if(prov != "" && prov != "King" && prov != "Play")
                streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        if(provider.toLowerCase().includes("pro")){
            var lookingFor = "molystream";
            var lookingForEmbed = "I AM NOT LOOKING";
            var toClick = "playSheilaBtn";
            var clickType = 0;
            var checkMedia = false;
            getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, "", clickType, checkMedia, headers, 1);
        }else{
            url = streamUrls[provider];

            data = fetch(url, headers);

            url = data.match(/frame.*?src="(.*?)".*?webkitallowfullscreen/)[1];

            data = fetch(url, headers);

            if(url.includes("haydi.php")){
                try{
                    var alts = [...data.matchAll(/<iframe(.*?)<\/iframe/g)];
            
                    for(let i = 0; i < alts.length; i++){
                        if(!alts[i][1].includes("display:")){
                                // Core.showResult(alts[i][1]);
                            url = alts[i][1];
                            break;
                        }
                    }
                    url = url.match(/src=['"](.*?)['"]/)[1];
                }catch(ignored){
                    error(ignored.message);
                }
                
                
            }else if(url.includes("moly.php")){
                try{
                    var alts = [...data.matchAll(/unescape\("(.*?)"\)/g)];
            
                    for(let i = 0; i < alts.length; i++){
                        if(!alts[i][1].includes("display:")){
                            url = alts[i][1];
                            break;
                        }
                    }

                    url = decodeURIComponent(url);

                    url = atob(url);

                    try{
                        var alts = [...url.matchAll(/<iframe(.*?)<\/iframe/g)];
                
                        for(let i = 0; i < alts.length; i++){
                            if(!alts[i][1].includes("display:")){
                                url = alts[i][1];
                                break;
                            }
                        }
                    }catch(ignored){
                        error(ignored.message);
                    }

                    url = url.match(/src=['"](.*?)['"]/)[1];
                }catch(ignored){
                    error(ignored.message);
                }
            }
            parser(url, lang, "", headers);
        }
    }
}

function dizilab(url, lang, headers){
    var initialUrl = url;

    var cookie = Core.saveCookie(url, "dz_lb", "span class=\"title\">");

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    if(data.includes("mobilembeds")){
        data = data.match(/mobilembeds(.*?)Sonra/)[1];
    }else{
        data = data.match(/fa fa-angle-down(.*?)Sonra/)[1];
    }

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/loadVideo\('(.*?)'.*?"icon-tr"><\/span>(.*?)\s*<\/a>/g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][1];
            var prov = alts[i][2];
            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        if(provider.toLowerCase().includes("vip")){
            var lookingFor = "molystream";
            var lookingForEmbed = "I AM NOT LOOKING";
            var toClick = "playSheilaBtn";
            var clickType = 0;
            var checkMedia = false;
            getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, "", clickType, checkMedia, headers, 1);
        }else{
            url = streamUrls[provider];

            postData = "vid=" + encodeURIComponent(url) + "&mobil=1&tip=1&type=loadVideo";
            url = baseUrl(initialUrl) + "/ajax";

            headers["Referer"] = initialUrl.split("#")[0];
            headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";
            headers["Content-Length"] = "123";
            headers["X-Requested-With"] = "XMLHttpRequest";

            data = fetchPost(url, headers, postData);

            url = data.match(/src=\\"(.*?)"/)[1].replace(/\\/g, "");

            delete headers["Content-Length"];
            delete headers["X-Requested-With"];
            delete headers["Content-Type"];
            
            data = Core.fetchCFF(url, JSON.stringify(headers), "Lütfen bekleyiniz");

            if(data.includes("cff")){

                headers["Cookie"] = data.split(";")[0] + ";";

                data = fetch(url,headers);
                
                if(data.includes("Lütfen bekleyiniz")){
                    var count = 0;
                    while (count < 10){
                        data = Core.fetchCFF(url, JSON.stringify(headers), "Lütfen bekleyiniz");

                        if(data.includes("cff")){
                            headers["Cookie"] = data.split(";")[0] + ";";

                            data = fetch(url,headers);
                        }

                        if(!data.includes("Lütfen bekleyiniz")){
                            break;
                        }

                        count++;
                    }
                }
            }

            url = data.match(/src="(.*?)"/)[1];

            delete headers["Cookie"];

            parser(url, lang, "", headers);
        }
    }
}

function dizilla(url, lang, headers){
    headers["Referer"] = url;
    headers["Accept"] = "*/*";

    var data = fetch(url, headers);

    var first = data.match(/<iframe.*?src="(.*?)"/)[1];

    if(!first.startsWith("https")){
        first = "https:" + first;
    }

    if(first.includes("dizilla.org/vmplayer")){
        first = "https://vidmoly.to/embed-" + first.split("\\?vid=")[1] + ".html";
    }

    parser(first, lang, "", headers);
}

function dizimia(url, lang, headers){
    var initialUrl = url;

    headers["Referer"] = url;
    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/<a class="focus:outline-none"\s*href="(.*?)"\s*title="(.*?)"/g)];
        var altCount = 1;
        for(let i = 0; i < alts.length; i++){
            streamUrls[alts[i][2]] = alts[i][1];
            altCount++;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];
        
        var data = fetch(url, headers);
        var partial = data.match(/<iframe.*?src="(.*?)"/)[1];

        if(!partial.startsWith("https")){
            partial = "https:" + partial;
        }

        parser(partial, lang, "", headers);
    }
}

function dizimom(url, lang, headers){
    var initialUrl = url;

    headers["Content-Type"] =  "application/x-www-form-urlencoded; charset=UTF-8";
    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Mobile Safari/537.36";
    headers["X-Requested-With"] =  "XMLHttpRequest";
    var data = fetch(url, headers);

    urls = {};
    singleUrl = "";
    
    try{
        var alts = [...data.matchAll(/<iframe.*?src="(.*?(?:player|embed|video|ok.ru|suhiaza).*?)".*?allowfullscreen/g)];

        for(let i = 0; i < alts.length; i++){
            urls[alts[i][1]] = alts[i][1];
            singleUrl = alts[i][1];
        }
    }catch(ignored){

    }

    if(!initialUrl.includes("#") && urls.length > 1){
        Core.showAlternatesJS(JSON.stringify(urls));
    }else{
        if(!initialUrl.includes("#")){
            initialUrl += "#" + singleUrl;
        }

        var selectedAlternate = urls[initialUrl.split("#")[1]].replace("https://hdplayersystem.live/video/", "");

        if(!selectedAlternate.includes("videoseyred.in")){
            if(selectedAlternate.includes("index.php"))
                url = selectedAlternate + "&do=getVideo";
            else
                url = "https://hdplayersystem.live/player/index.php?data=" + selectedAlternate + "&do=getVideo";
            
            var data = fetchPost(url, headers, "hash="+selectedAlternate+"&r="+encodeURIComponent(initialUrl) + ")");

            var j = JSON.parse(data);

            if(j.hasOwnProperty("securedLink")){
                url = j.securedLink.replace(/\\\//g, "/");
            }else{
                url = j.videoSources[0].file;
            }

            parser(url, lang, "", headers);
        }else{
            selectedAlternate = selectedAlternate.replace("https://videoseyred.in/embed/","").replace("?hideTitle=1", "");

            var data = fetch("https://videoseyred.in/playlist/"+selectedAlternate+".json", headers);

            var j = JSON.parse(data);

            url = j[0].sources[0].file;

            var tracks = j[0].tracks;

            var sub = "";

            for(var i = 0; i < tracks.length; i++){
                if(tracks[i].hasOwnProperty("language")){
                    if(tracks[i].language == "tur"){
                        sub = tracks[i].file;
                    }
                }
            }

            headers["Referer"] = "https://videoseyred.in/";

            parser(url, lang, sub, headers);
        }
    }
}

function dizipal(url, lang, headers){
    var initialUrl = url;

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    // Core.showResult(data);


    url = data.match(/data-src="(.*?)"/)[1];

    data = fetch(url, headers);

    url = data.match(/file:"(.*?)"/)[1];

    var sub = "";

    try{
        var alts = [...data.matchAll(/\](http.*?\.vtt)/g)];

        for(let i = 0; i < alts.length; i++){
            if(alts[i][1].includes("Turkce") || alts[i][1].includes("tr") || alts[i][1].includes("_tur"))
                sub = alts[i][1];
        }
    }catch(ignored){
        sub = "";
    }


    parser(url, lang, sub, headers);
}

function dizipub(url, lang, headers){
    var initialUrl = url;

    var cookie = Core.saveCookie(url, "dpub", "application/ld+json");

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    data = data.match(/series-alter-active(.*?) <\/ul>/)[1];

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/<(?:span class=|a.*?href=)"(.*?)"\s*title="(.*?)"/g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][1];
            var prov = alts[i][2];
            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        data = fetch(url, headers);
        
        url = data.match(/<iframe.*?src="(.*?)".*?allowfullscreen.*?<\/iframe>/)[1];

        if(!url.startsWith("https"))
            url = "https:" + url;

        parser(url, lang, "", headers);
    }
}

function dizirix(url, lang, headers){
    var initialUrl = url;

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    if(!initialUrl.includes("#")){
        var data = fetch(url, headers);

        var l = "dublaj";

        if(lang == 0)
            l = "altyazili";

        var bID = data.match(/var\s*bID\s*=\s*"(\d*)"/)[1];

        var postData = "whichOne=" + l + "&bId=" + bID;

        url = baseUrl(url) + "/ajax/request=getLanguage";

        data = fetchPost(url, headers, postData);

        var streamUrls = {};

        try{
            var alts = [...data.matchAll(/data-code=\\\"(.*?)\\\".*?span>(.*?)</g)];

            for(let i = 0; i < alts.length; i++){
                var link = alts[i][1];
                var prov = alts[i][2];
                streamUrls[prov] = link;
            }
        }catch(ignored){
            error(ignored.message);
        }

        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";
        var data = fetchPost(baseUrl(initialUrl) + "/ajax/request=getFrames", headers, "dataCode=" + url);

        data = atob(data);

        url = data.match(/src="(.*?)"/)[1];

        delete headers["Content-Type"];

        var sub = "";

        if(url.includes("prixy.php") || url.includes("source=rix2") || url.includes("source-yg") || url.includes("proxy.php")){
            headers["upgrade-insecure-requests"] = "1";
            data = fetch(url, headers);

            url = "dummy";

            try{
                url = data.match(/file:"(.*?)"/)[1];
                sub = data.match(/"file":.*?"(.*?)"/) || [];
                if(sub.length > 0){
                    sub = sub[1];
                }
            }catch(ignored){
                url = data.match(/Back.*?'(https.*?)'/)[1];
            }

            delete headers["upgrade-insecure-requests"];

            headers["Origin"] = baseUrl(initialUrl);
        }

        parser(url, lang, sub, headers);
    }
}

function diziroll(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    data = data.match(/focus\:outline-none"\s*href="(.*?)"\s*title="(.*?)"\s*data-navigo/)[1];

    data = fetch(data, headers);

    try{
        var alts = [...data.matchAll(/iframe.*?src="(.*?)"/g)];

        for(let i = 0; i < alts.length; i++){
            if(!alts[i][1].includes("finema")){
                url = alts[i][1];
                break;
            }
        }
    }catch(ignored){
        error(ignored.message);
    }

    parser(url, lang, "", headers);
}

function dizitime(url, lang, headers){ //Need a way to fix if started previously from browser somehow
    var initialUrl = url;

    var cookie = Core.saveCookie(url, "dtx", "data-name=>");

    //headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/data-name="(.*?)"\s*data-oid="(.*?)"/g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][2];
            var prov = alts[i][1];
            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        if(provider.toLowerCase().includes("time")){
            var lookingFor = "molystream.org";
            var lookingForEmbed = "I AM NOT LOOKING";
            var toClick = "playSheilaBtn";
            var clickType = 0;
            var checkMedia = false;
            getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, "", clickType, checkMedia, headers, 1);
        }else{
            url = streamUrls[provider];
            headers["Referer"] = baseUrl(initialUrl);

            url = baseUrl(initialUrl) + "/play/" + url;
            data = fetch(url, headers);

            var temp = "";

            try{
                var alts = [...data.matchAll(/document\.write\(atob\(unescape\("(.*?)"\)\)\);/g)];
        
                for(let i = 0; i < alts.length; i++){
                    temp += ""+atob(alts[i][1]);
                }
            }catch(ignored){
                error(ignored.message);
            }

            var pc = temp.match(/<iframe.*?src=[",'](.*?)[",']/)[1].replace(/:/g,"").replace(/&#/g, "").trim().replace(/;/g," ");
            var b = pc.split(" ");
            pc = "";

            for(var i = 0; i < b.length; i++){
                pc += ""+String.fromCharCode(parseInt(b[i]));
            }

            url = pc;
            if(url.includes("getvideo")){
                data = fetch(url, headers);
                pc = data.match(/sources\s*:\s*\[(.*?)\]/)[1];
                streamUrls = {};

                try{
                    var alts = [...data.matchAll(/file:"(.*?)"(?:\}|,label:"(.*?)")/g)];

                    for(let i = 0; i < alts.length; i++){
                        var link = alts[i][1];
                        var prov = alts[i][2];
                        if(link.startsWith("https")){
                            streamUrls[prov] = link;
                            url = link;
                        }
                    }
                }catch(ignored){
                    error(ignored.message);
                }
                
                headers["Referer"] = "https://vidmoly.to";
            }

            parser(url, lang, "", headers);
        }
    }
}

function diziwatch(url, lang, headers){
    var data = fetch(url, headers);

    headers["Referer"] = url;

    var sub = "";

    if(!data.includes("<iframe")){
        data = data.toLowerCase();
        var first = data.match(/url\s*:\s*"(http[^"]+php)".*?\'pid\'\s*:\s*(\d+),/);

        data = fetch(first[1] + "?action=playlist&pid=" + first[2], headers).toLowerCase();
    
        url = data.match(/file"\s*:\s*"(.*?)"/);
        url = url[url.length-1];
    }else{
        var first = data.match(/<iframe\s*src="(.*?)".*?allowfullscreen>/)[1];

        data = fetch(first, headers);

        url = data.match(/'\/playlist\/(.*?).json';/)[1];

        data = fetch("https://videoseyred.in/playlist/"+url+".json", headers);

        var j = JSON.parse(data);

        url = j[0].sources[0].file;

        var tracks = j[0].tracks;

        for(var i = 0; i < tracks.length; i++){
            if(tracks[i].hasOwnProperty("language")){
                if(tracks[i].language == "tur"){
                    sub = tracks[i].file;
                }
            }
        }

        headers["Referer"] = "https://videoseyred.in/";
    } 

    parser(url, lang, sub, headers);
}

function diziyo(url, lang, headers){
    var initialUrl = url;

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    var d1 = data.match(/id="player-option-1"\s*class="(.*?)"\s*data-type=".*?"\s*data-post=".*?"\s*data-nume=".*?"/)[1];
    var d2 = data.match(/id="player-option-1"\s*class=".*?"\s*data-type="(.*?)"\s*data-post=".*?"\s*data-nume=".*?"/)[1];
    var d3 = data.match(/id="player-option-1"\s*class=".*?"\s*data-type=".*?"\s*data-post="(.*?)"\s*data-nume=".*?"/)[1];
    var d4 = data.match(/id="player-option-1"\s*class=".*?"\s*data-type=".*?"\s*data-post=".*?"\s*data-nume="(.*?)"/)[1];

    var postData = "action=doo_player_ajax&post="+d3+"&nume="+d4+"&type="+d2;
    data = fetchPost(baseUrl(url) + "/wp-admin/admin-ajax.php", headers, postData);

    data = data.match(/src='(.*?)'/)[1];
    var arr = data.split("/");

    var hash = arr[arr.length - 1];

    postData = "hash="+hash+"&r="+baseUrl(url)+"/";

    headers["X-Requested-With"] = "XMLHttpRequest";

    var data2 = fetchPost(data + "?do=getVideo", headers, postData);

    headers["Referer"] = data;

    var j = JSON.parse(data2);
    url = j.videoSources[0].file;

    delete headers["X-Requested-With"];
    headers["Accept"] = "*/*";

    parser(url, lang, "", headers);
}

function diziyou(url, lang, headers){
    headers["Referer"] = url;
    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    url = data.match(/<iframe.*?src="(.*?)"/)[1];

    if(lang == 1){
        url = url.replace(".html", "_tr.html");
    }

    var data = fetch(url, headers);
    
    var sub = data.match(/<track\s*src="(.*?)"/)[1];

    url = data.match(/<source.*?src="(.*?)"/)[1];

    parser(url, lang, sub, headers);
}

function filmatek(url, lang, headers){
    var initialUrl = url;

    var part = 1;

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/(\d+)\.\s*Kısım/g)];
        var altCount = 1;
        for(let i = 0; i < alts.length; i++){
            if(!alts[i][1].includes("youtube")){
                if(initialUrl.includes("#")){
                    if(alts[i][1] == initialUrl.split("#")[1]){
                        part = i + 1;
                    }
                }
                streamUrls["Bölüm " + altCount] = alts[i][1];
                altCount++;
            }
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        if(initialUrl.includes("#")){
            part = initialUrl.split("#")[1];
        }
        
        var player_api = data.match(/layer_api":"(.*?)","play_aj/)[1];
        var video_id = data.match(/data-post='(\d+)'/)[1];

        url = player_api.replace(/\\\//g, "/") + video_id + "/movie/" + part;

        var data = fetch(url, headers);

        try{
            var j = JSON.parse(data);

            url = j["embed_url"];

            data = fetch(url, headers);

            url = data.match(/"file"\s*:\s*"(.*?)"/)[1].replace(/\\\//g, "/");
            parser(url, lang, "", headers);
        }catch(ignored){
            error(ignored.message);
        }
    }
}

function filmekseni(url, lang, headers){
    var initialUrl = url;

    headers["Content-Type"] =  "application/x-www-form-urlencoded; charset=UTF-8";
    headers["X-Requested-With"] =  "XMLHttpRequest";
    var data = fetch(url, headers);

    data = data.match("tab-pane active(.*?)</nav>")[1];
    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/href="(.*?)">\s*(.*?)\s*<\/a>/g)];
        for(let i = 0; i < alts.length; i++){
            streamUrls[alts[i][2]] = alts[i][1];
        }
    }catch(ignored){
        
    }

    if(!initialUrl.includes("#")){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var data = fetch(url, headers);

        url = data.match(/iframe.*?data-src="(.*?)"/)[1];

        if(url.includes("vidload.one")){
            var time = new Date();
            time = time.getTime();
            
            var code = url.split("/")[url.split("/").length - 1];

            url = "https://vidload.one/ajax/" + code + "?" + time;

            var header = fetch("https://vidload.one/video.js?"+time, {});
            var tt = header.match("window,'(.*?)','.*?','.*?'")[1];
            var ip = header.match("window,'.*?','(.*?)','.*?'")[1];

            headers[tt] = ip;

            data = fetch(url, headers);

            var json = JSON.parse(data);

            url = json.file + "?" + json.hash;
            url = Core.getRedirectUrlJS(url);

            headers["Referer"] = "https://vidload.one";

            parser(url, lang, "", headers);
        }else{
            headers["Referer"] = initialUrl;

            parser(url, lang, "", headers);
        }
    }
}

function filmizlesene(url, lang, headers){
    var initialUrl = url;

    var cookie = Core.saveCookie(url, "flmizl", "vidcontainer");

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    var data2 = data.match(/(?:inepisode|bolumler)(.*?)vidcontainer/)[1];
    if(data2 == "" && !initialUrl.includes("#")){
        data = data.match(/iframe\s*src="(.*?)"/)[1];
        if(data.includes("/ok/")){
            var id = data.split("?v=")[1];
            id = atob(id);
            url = "https://odnoklassniki.ru/videoembed/" + id;
        }
        parser(url, lang, "", headers);
    }else{
        data = data2;

        if(!initialUrl.includes("#")){
            var streamUrls = {};

            try{
                var alts = [...data.matchAll(/dil="(.*?)">(.*?)<.*?iframe\s*src="(.*?)"/g)];

                for(let i = 0; i < alts.length; i++){
                    if(prov != "opn" && prov != "up"){
                        var link = alts[i][3];
                        var prov = alts[i][2] + ", " + alts[i][1];
                        streamUrls[prov] = link;
                    }
                }

            }catch(ignored){
                error(ignored.message);
            }

            Core.showAlternatesJS(JSON.stringify(streamUrls));
        }else{
            url = initialUrl.split("#")[1];
    
            if(url.includes("vidmoly")){
                url = url.match(/vid=(.*?)$/)[1];
            }else{
                headers["Cookie"] = cookie;
                data = fetch(url, headers);

                data = data.match(/iframe\s*src=(?:\'|")(.*?)(?:\'|")/)[1];

                if(data.includes("hdplayer") || data.includes("vidmo")){
                    data = fetch(url, headers);

                    var isDone = false;

                    data = data.match(/iframe.*?src\s*=\s*"(.*?)"/)[1];

                    if(data.includes("odnoklass")){
                        url = data;
                        isDone = true;
                    }

                    if(!data.includes("hdplayer")){
                        url = data + "/sheila";
                        delete headers["Cookie"];
                        
                        headers["Referer"] = data;
                        isDone = true;
                    }
                }else{
                    url = data;
                }
            }
            parser(url, lang, "", headers);
        }
    }
}

function filmkovasi(url, lang, headers){
    var data = fetch(url, headers);

    var m1 = data.match(/<iframe\s*src="(.*?)"/)[1];
    if(!m1.includes("trstx") && !m1.includes("sobreatsesuyp")){
        data = fetch(m1, headers);
        m1 = data.match(/file:\s*"(.*?)"/)[1];
    }
    parser(m1, lang, "", headers);

}

function filmmakinesi(url, lang, headers){
    var initialUrl = url;

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    var m1 = data.match("php.*?(https://closeload.filmmakinesi.*?embed.*?) ")[1];
    data = fetch(m1, headers);

    var pre = "https://closeload." + baseUrl(initialUrl, false);

    var sub = "";

    try{
        var subs = [...data.matchAll(/<track src="(.*?)"\s*kind="captions"/g)];
        for(let i = 0; i < subs.length; i++){
            if(subs[i][1].includes("tr")){
                sub = pre + "/" + subs[i][1];
                break;
            }
        }
    }catch(ignored){

    }

    m1 = data.match('(eval.*?)</script>')[1];

    m1 = unPack(m1);
    
    var url = m1.match('(aHR0cH.*?)"')[1];

    url = atob(url);

    headers["Referer"] = pre;

    parser(url, lang, sub, headers);
}

function filmmax(url, lang, headers){
    var data = fetch(url, headers);

    var first = data.match(/iframe.*?src="(.*?)"/)[1];

    var sub = "";

    data = fetch(first, headers);

    url = data.match(/'\/playlist\/(.*?).json';/)[1];

    data = fetch("https://videoseyred.in/playlist/"+url+".json", headers);

    var j = JSON.parse(data);

    url = j[0].sources[0].file;

    var tracks = j[0].tracks;

    for(var i = 0; i < tracks.length; i++){
        if(tracks[i].hasOwnProperty("language")){
            if(tracks[i].language == "tur"){
                sub = tracks[i].file;
            }
        }
    }

    headers["Referer"] = "https://videoseyred.in/";

    parser(url, lang, sub, headers);
}

function filmmodu(url, lang, headers){
    var data = fetch(url, headers);

    try{
        var json = JSON.parse(data);
        
        if(json.hasOwnProperty("subtitle")){
            var sub = baseUrl(url) + json["subtitle"];
        }

        var sources = json["sources"];
        sources.sort(function(a, b){
            return a.label.localeCompare(b.label);
        });

        url = sources[0]["src"];
    }catch(ignored){
        error(ignored.message);
    }

    parser(url, lang, sub, headers);
}

function hdfilmcehennemi2(url, lang, headers){
    url = url.replace("syrtrk", "");
    
    var initialUrl = url;

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    data = data.match(/<nav\s*class="video-alternatives">(.*?)player-container/)[1]; //Changed

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/<div\s*class="alternative-links".*?data-lang="(.*?)">.*?data-video="(.*?)">(.*?)<\/button>/g)]; // Changed

        for(let i = 0; i < alts.length; i++){
            //if((lang == 1 && (alts[i][1] == "tr" || alts[i][1] == "dual")) || (lang == 0 && (alts[i][1] == "en" || alts[i][1] == "dual")))
            var prov = alts[i][3];
            var link = alts[i][2];

            if(alts[i][1] == "dual")
                prov = prov + " - Altyazı & Dublaj";
            else if(alts[i][1] == "tr")
                prov = prov + " - Dublaj";
            else
                prov = prov + " - Altyazı";

            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        headers["X-Requested-With"] = "fetch";
        data = fetch(baseUrl(initialUrl) + "/video/" + url + "/", headers);

        var provider = initialUrl.split("#")[2];

        data = data.match(/<iframe.*?data-src=\\"(.*?)\\"/)[1];

        var sub = "";

        if(data.includes("player")){
            data = fetch(data, headers);

            url = "eval(" + data.match(/eval\((.*?)\{\}\)\)/)[1] + "{}))";
            
            data = data.match(/tracks:\s*(.*?\]),/)[1];

            sub = [];

            try{
                var alts = [...data.matchAll(/"file":"(.*?)"/g)];
        
                for(let i = 0; i < alts.length; i++){       
                    var l = ""; 
                    if(alts[i][1].includes("Turkish"))
                        l = "tr";
                    else if(alts[i][1].includes("English"))
                        l = "en";
                    
                    if(l != ""){
                        var s = alts[i][1].replace(/\\\//g, "/") + "/";
                        var t = {"lang": l, "url": baseUrl(initialUrl) + s};
                        sub.push(t);
                    }
                }
            }catch(ignored){

            }
            
            data = unPack(url).replace("var file_link=\"", "").replace("\";", "");
            url = atob(data);
            
            delete headers["X-Requested-With"]
        }
        parser(url, lang, JSON.stringify(sub), headers);
    }
}

function hdtoday(url, lang, headers){
    var initialUrl = url.split("#")[0];

    headers["User-Agent"] = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";

    var tvID = "";
    if(initialUrl.includes("/movie/")){
        initialUrl = initialUrl.replace("/movie/", "/watch-movie/");
    }else if(initialUrl.includes("/tv/")){
        initialUrl = initialUrl.replace("/tv/", "/watch-tv/").replace("-full-", "-hd-").replace(".se", ".tv");
        tvID = initialUrl.split(".")[initialUrl.split(".").length - 1];
        tvID = tvID.split("/")[0];
        var d = fetch(baseUrl(initialUrl) + "/ajax/episode/servers/" + tvID, headers);
        initialUrl = initialUrl.replace(tvID, d.match(/<a data-id="(.*?)"/)[1]).split("/sezon")[0];
    }

    if(!url.includes("#")){
        var lookingFor = "playlist.m3u8";
        var lookingForEmbed = "embed";
        var toClick = "jw-icon jw-icon-display jw-button-color jw-reset";
        var clickType = 0;
        var checkMedia = false;
        
        var toClick2 = "";

        getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, toClick2, clickType, checkMedia, headers, 2);
    }else{
        var temp = url.split("#")[2];

        url = url.split("#")[1];
        if(temp.includes("http")){
            headers["x-requested-with"] = "XMLHttpRequest";
            
            var id = temp.split("/")[temp.split("/").length - 1];
            id = id.split("?")[1];

            var data = fetch(temp, headers);
            var sub = [];

            try{
                var jobj = JSON.parse(data);
                var jarr = jobj["tracks"];

                for(var i = 0; i < jarr.length; i++){
                    var s = jarr[i];
                    if(s.hasOwnProperty("label") && s.hasOwnProperty("file")){
                        if(s["label"].includes("English") || s["label"].includes("German") || s["label"].includes("Turkish")){
                            var l = "Eng";
                            if(s["label"].includes("German"))
                                l = "Ger";
                            else if(s["label"].includes("Turkish"))
                                l = "Tur";
                            var t = {"lang": l, "url": s["file"]};
                            sub.push(t);
                        }
                    }
                }
            }catch(exception){

            }

            //loadSubtitlesOnline
            //AI Translate
            parser(url, lang, JSON.stringify(sub), headers);
        }
    }
}

function izle720p(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/<iframe.*?src="(.*?)".*?<\/iframe>/g)];
        var altCount = 1;
        for(let i = 0; i < alts.length; i++){
            if(!alts[i][1].includes("youtube")){
                streamUrls["Alternatif " + altCount] = alts[i][1];
                altCount++;
            }
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#")){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];
        parser(url, lang, "", headers);
    }
}

// Parser Changed
function kultfilmler(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    data = data.match(/player-control(.*?)button\s*report-button\s*trigger/)[1];

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/part-name">(.*?)<.*?"part-lang">(.*?)<\/div/g)];
        var count = 0;
        for(let i = 0; i < alts.length; i++){
            var tempUrl = url;
            if(count > 0){
                tempUrl = url + (count + 1) + "/"; 
            }

            var lan = "cc";
            if(lang == 1)
                lan = "tr";

            if(alts[i][2].includes(lan) || (lan != 1 && alts[i][2].length < 2)){
                if(!alts[i][1].includes("KULTPlayer")){
                    streamUrls[alts[i][1]] = tempUrl;
                }
            }
            count++;
        }
    }catch(ignored){
        error(ignored.message);
    }


    if(!initialUrl.includes("#") && streamUrls.length > 0){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        if(initialUrl.includes("#"))
            url = initialUrl.split("#")[1];
        else
            url = initialUrl;

        data = fetch(url, headers);


        url = data.match(/new ContentManager\(.*?, "(.*?)", 10\);/)[1]; //Changed
        url = atob(url);
        url = url.match(/iframe.*?src="(.*?)"/)[1];

        if(!url.startsWith("http")){
            url = "https:" + url;
        }

        if(url.includes("yildizkisafilm")){
            var code = url.split("/")[url.split("/").length - 1];
            var postUrl = "https://yildizkisafilm.org/player/index.php?data="+code+"&do=getVideo";
            var json = "hash="+code+"&r=https%3A%2F%2Fkultfilmler.com%2F";
            headers["X-Requested-With"] = "XMLHttpRequest";

            var res = fetchPost(postUrl, headers, json);
            try{
                json = JSON.parse(res);
                url = json.securedLink;
                parser(url, lang, "", headers);
            }catch(ignored){
                error(ignored.message);
            }
        }else{
            parser(url, lang, "", headers);
        }
    }
}

function movies123(url, lang, headers){
    var initialUrl = url.split("#")[0];

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var lookingFor = "master.m3u8";
    var lookingForEmbed = "embed";
    var toClick = "play-now";
    var clickType = -1;
    var checkMedia = false;
    
    var toClick2 = "ep-#e";
    getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, toClick2, clickType, checkMedia, headers, 0);
}

// Server 4K, Dropload, Goodstream, Mixdrop not working.
function movie4k(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/class="tablinks"\s*href="#"\s*data-link="(.*?)">(.*?)</g)];
        var altCount = 1;
        for(let i = 0; i < alts.length; i++){
            if(!alts[i][2].includes("Player HD") && !alts[i][2].includes("Trailer")){
                streamUrls[alts[i][2]] = alts[i][1];
                altCount++;
            }
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];
        url = fixUrl(url);

        if(url.includes("goodstream")){
            //not working
        }else{
            //loadSubtitlesOnline
            parser(url, lang, "", headers);
        }
    }
}

function onlinedizi(url, lang, headers){ // Evil Babam bakıcak
    var initialUrl = url;

    var lookFor = "episode-buttons";

    if(url.includes("/film/")){
        lookFor = "active";
        if(!url.includes("izle")){
            url = url + "/turkce-altyazi-izle";
        }
    }
    var cookie = Core.saveCookie(url, "on_dz", lookFor);

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    try{
        data = data.match(/Alternatif(.*?)episode-buttons/)[1];
    }catch(ignored){

    }

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/href="(.*?)".*?>(.*?)</g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][1];
            var prov = alts[i][2];
            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(Object.keys(streamUrls).length == 0 || Object.keys(streamUrls).length > 5)
        error("Onlinedizi 1371");

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        data = fetch(url, headers);

        url = data.match(/iframe\s*src="(.*?)"/)[1];
        if(!url.startsWith("http"))
            url = baseUrl(initialUrl) + url;

        data = fetch(url, headers);

        var iframe = data.match(/ifsrc = "(.*?)"/)[1];
        if(!iframe.startsWith("http"))
            iframe = "https:" + iframe;

        url = Core.getRedirectUrlJS(iframe);

        if(url == ""){
            error("Onlinedizi 1393");
        }else if(url.includes("gdplayer")){
            data = fetch(url, headers);
            
            url = "https:" + data.match(/(\/\/gdplayer.org\/api\/.*?)"/)[1];

            data = fetch(url, headers);

            try{
                var j = JSON.parse(data);
                url = j.sources[0].file;
            }catch(ignored){
                error(ignored.message);
            }
        }else if(url.includes("fscdn.xyz")){
            var key = url.split("/")[4];
            var res = url + "?do=getVideo";

            var postData = "hash=" + key + "&r=" + baseUrl(initialUrl) + "&s=";
            url = res;
            headers["content-type"] = "application/x-www-form-urlencoded; charset=UTF-8";
            headers["x-requested-with"] = "XMLHttpRequest";

            data = fetchPost(url, headers, postData);

            try{
                var j = JSON.parse(data);
                res = j.videoSources[0].file;
                url = res;

                if(res.includes("fcdn")){
                    data = fetch(url, headers);
                    url = "";

                    try{
                        var alts = [...data.matchAll(/sticon-film"><\/span><b>(.*?)</g)];
                        for(let i = 0; i < alts_raw.length; i++){
                            if(url == ""){
                                url = alts[i][1];
                            }else if(alts[i][1].includes("1080p/playlist")){
                                url = alts[i][1];
                            }else if(alts[i][1].includes("720p/playlist") && !url.includes("1080p/playlist")){
                                url = alts[i][1];
                            }else if(alts[i][1].includes("480p/playlist") && !url.includes("1080p/playlist") && !url.includes("720p/playlist")){
                                url = alts[i][1];
                            }else if(alts[i][1].includes("360p/playlist") && !url.includes("1080p/playlist") && !url.includes("720p/playlist") && !url.includes("480p/playlist")){
                                url = alts[i][1];
                            }
                        }
                    }catch(ignored){

                    }
                }
            }catch(ignored){
                error(ignored.message);
            }
        }else if(url.includes("ondembed.xyz")){
            url = url.replace("ondembed.xyz", "fembed.com");
        }

        parser(url, lang, "", headers);
    }
}

function realfilmizle(url, lang, headers){
    var data = fetch(url, headers).toLowerCase();

    var first = data.match(/iframe.*?src="(https.*?)"/)[1];

    data = fetch(first, headers);

    url = data.match(/{\s*file:"(.*?)"/)[1];

    headers["Referer"] = first;
    headers["Accept"] = "*/*";

    parser(url, lang, "", headers);
}

// dub ve sub parser kontrol
function setfilmizle(url, lang, headers){
    var initialUrl = url;
    if(url.includes("#"))
        url = url.split("#")[0];

    var data = fetch(url, headers);
    var data_id = data.match('data.*?-id="(.*?)"')[1];    
    
    var urls = {};

    try{
        var alts_raw = [...data.matchAll(/sticon-film"><\/span><b>(.*?)</g)];
        for(let i = 0; i < alts_raw.length; i++){
            if(alts_raw[i][1] != "Raca")
                urls[alts_raw[i][1]] = alts_raw[i][1];
        }
    }catch(ignored){

    }
    
    var isSameLength = alts_raw.length == urls.length;

    if(!initialUrl.includes("#")){
        Core.showAlternatesJS(JSON.stringify(urls));
    }else{
        try{
            var key = urls[initialUrl.split("#")[1]];

            var fetchUrl = baseUrl(url) + "/play/play.php?ser=" + data_id + "&name=" + key + "&partKey=";

            if(!isSameLength){
                if(lang == 1){
                    fetchUrl += "turkcedublaj";
                }else{
                    fetchUrl += "turkcealtyazi";
                }
            }

            var tempData = Core.fetch(fetchUrl, JSON.stringify(headers));

            if(tempData == "Error"){
                error("Setfilmizle 1499");
            }

            var tempRef = tempData.match(/src=[\"'](.*?)['\"]/)[1];
            var tempDom = tempRef.split("/")[2];

            tempData = Core.fetch(tempRef, JSON.stringify(headers));

            if(tempData == "Error"){
                error("Setfilmizle 1508");
            }

            var playable = tempData.match(/videoSources.*?"file":"(.*?)"/)[1].replace(/\\\//g, "/");
            var number = playable.split("/")[2];
            playable = playable.replace("/"+number+"/", "/"+tempDom+"/") + "?s=" + number;

            headers["Referer"] = tempRef;
            headers["Accept"] = "*/*";
            parser(playable, lang, "", headers);

        }catch(ignored){
            error(ignored.message);
        }
    }
}

function sezonlukdizi(url, lang, headers){
    if(lang == 1)
        lang = 0;
    else
        lang = 1;
    var initialUrl = url;

    var data = fetch(url, headers);

    data = data.match(/<div\s*bid="(\d*)"\s*did=/)[1];

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    headers["X-Requested-With"] = "XMLHttpRequest";
    headers["Content-Length"] = "123";

    data = fetchPost(baseUrl(url) + "/ajax/dataAlternatif2.asp", headers, "bid=" + data + "&dil=" + lang);

    if(data.includes("eklenmedi")){
        error("Sezonlukdizi 1544");
    }
    var j = JSON.parse(data);

    var streamUrls = {};

    for(var i = 0; i < j.data.length; i++){
        if(!j.data[i].baslik.toLowerCase().includes("netu") && !j.data[i].baslik.toLowerCase().includes("upto") && !j.data[i].baslik.toLowerCase().includes("multi") && !j.data[i].baslik.toLowerCase().includes("upstream") && !j.data[i].baslik.toLowerCase().includes("videoseyred"))
        {
            var k = j.data[i].id+"";
            var v = j.data[i].baslik;
            streamUrls[v] = k;
        }
    }

    if(streamUrls.length == 0)
        error("Sezonlukdizi 1560");

    if(!initialUrl.includes("#")){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        data = fetchPost(baseUrl(initialUrl) + "/ajax/dataEmbed.asp", headers, "id=" + url);

        data = data.match(/(?:SRC|src)="(.*?)"/)[1];

        if(data.startsWith("//"))
            data = "https:" + data;

        if(data.includes("/player/fembed.asp")){
            url = "https://dutrag.com/v/" + data.split("\\?v=")[1];
        }else if(data.includes("sbembed")){
            var media_id = data.split("/")[4].replace(".html", "");

            var x = Core.makeID(12) + "||" + media_id + "||" + Core.makeID(12) + "||streamsb";
            var xHex = Core.bytesToHex(x);
            var c1 = decodeURIComponent(xHex);

            x = Core.makeID(12) + "||" + Core.makeID(12) + "||" + Core.makeID(12) + "||streamsb";
            xHex = Core.bytesToHex(x);
            var c2 = decodeURIComponent(xHex);

            x = Core.makeID(12) + "||" + c2 + "||" + Core.makeID(12) + "||streamsb";
            xHex = Core.bytesToHex(x);
            var c3 = decodeURIComponent(xHex);

            url = baseUrl(url) + "/sourcesx38/" + c1 + "/" + c3;

            data = fetch(url, headers);

            try{
                url = JSON.parse(data).stream_data.file;
            }catch(ignored){
                url = JSON.parse(data).stream_data.backup;
            }
        }else{
            url = data;
        }

        delete headers["X-Requested-With"];
        delete headers["Content-Length"];

        parser(url, lang, "", headers);
    }
}

function sinefy(url, lang, headers){
    var initialUrl = url;

    //var cookie = Core.saveCookie(url, "snfy", "data-querytype");

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/data-querytype=".*?">\s*<a href="(.*?)"\s*data-navigo.*?>(.*?)</g)];

        for(let i = 0; i < alts.length; i++){
            var link = alts[i][1];
            var prov = alts[i][2];
            streamUrls[prov] = link;
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        // Core.showResult(url);
        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        headers["Accept"] = "*/*";

        data = fetch(url, headers);

        url = "";

        if(url.includes("rapid")){
            url = data.match(/file:"(.*?)"/)[1];
        }else{
            try{
                var alts = [...data.matchAll(/iframe.*?src="(.*?)"/g)];
        
                for(let i = 0; i < alts.length; i++){
                    var link = alts[i][1];
                    if(!link.includes("finema")){
                        url = link;
                        break;
                    }
                }

                if(url.startsWith("//"))
                    url = "https:" + url;
            }catch(ignored){
                error(ignored.message);
            }
        }

        parser(url, lang, "", headers);
    }
}

function sinemafilmizle(url, lang, headers){
    var initialUrl = url;

    headers["Referer"] = url;

    var data = fetch(url, headers);

    var langs = [];
    var providers = [];
    var iframes = [];

    var streamUrls = {};

    var toLookFor = "tra";
    if(lang == 1)
        toLookFor = "trd";

    try{
        var alts = [...data.matchAll(/dil="(.*?)">.*?<\/span>/g)];
        for(let i = 0; i < alts.length; i++){
            langs.push(alts[i][1]);
        }

        alts = [...data.matchAll(/dil=".*?">(.*?)<\/span>/g)];
        for(let i = 0; i < alts.length; i++){
            providers.push(alts[i][1]);
        }

        alts = [...data.matchAll(/html\('<iframe.*?src="(.*?)"/g)];
        for(let i = 0; i < alts.length; i++){
            iframes.push(alts[i][1]);
        }
    }catch(ignored){
        error(ignored.message);
    }

    for(var i = 0; i < langs.length; i++){
        if(langs[i] == toLookFor){
            try{
                streamUrls[providers[i]] = iframes[i];
            }catch(ignored){

            }
        }
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        var pos = iframes.indexOf(url);

        var provider = "";

        if(pos != null){
            provider = providers[pos];
        }

        if(provider.toLowerCase().includes("sine")){
            //WebAutoClicker
            var lookingFor = "popcornvakti";
            var lookingForEmbed = "I AM NOT LOOKING";
            var toClick = "playSheilaBtn";
            var clickType = 0;
            var checkMedia = false;
            getWebViewOwnContentJS(url, lookingFor, lookingForEmbed, toClick, "", clickType, checkMedia, headers, 0);
        }else{
            if(url.includes("my.mail.ru") || url.includes("vidmoly")){
                if(url.includes("vidmoly"))
                    url = url.split("vid=")[1];
            }else{
                headers["Referer"] = initialUrl;
                data = fetch(url, headers);

                url = data.match(/player"><iframe.*?src=["\'](.*?)["\']/)[1];
                                
                if(!url.includes("odno") && !url.includes("ok.ru")){
                    data = fetch(url, headers);
                    url = data.match(/<iframe.*?src="(.*?)"/)[1];
                }
            }
            parser(url, lang, "", headers);
        }
    }
}

function siyahfilmizle(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/<li><span.*?href="(.*?)".*?i class="(.*?)"><\/i>(.*?)</g)];

        for(let i = 0; i < alts.length; i++){
            var lang2 = alts[i][2];
            var link = alts[i][1];
            var prov = alts[i][3];
            if(lang2 != null){
                if((lang2.includes("en") && lang == 0) || (lang2.includes("tr") && lang == 1)){
                    streamUrls[prov] = link;
                }
            }
        }
    }catch(ignored){
        error(ignored.message);
    }


    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        if(initialUrl.includes("#"))
            url = initialUrl.split("#")[1];

        data = fetch(url, headers);

        url = data.match(/iframe.*?data-src=["\'](.*?)["\']/)[1].replace("#038;", "");

        url = fixUrl(url);

        parser(url, lang, "", headers);
    }
}

// Voe, Doodstream not working.
function sto(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url, headers);

    var root = baseUrl(url);

    var streamUrls = {};

    try{
        var alts = [...data.matchAll(/data-lang-key="(\d)"\s*data-link-id="\d+"\s*data-link-target="(.*?)".*?Hoster\s(.*?)"/g)];
        var altCount = 1;
        for(let i = 0; i < alts.length; i++){
            var lang2 = alts[i][1];
            var link = alts[i][2];
            var prov = alts[i][3];
            if(lang2 != null){
                if((lang2 == "2" && lang == 0) || (lang2 == "1" && lang == 2)){
                    streamUrls[prov] = root + link;
                }
            }
        }
    }catch(ignored){
        error(ignored.message);
    }

    if(!initialUrl.includes("#") && JSON.stringify(streamUrls) != "{}"){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];
        url = Core.getRedirectUrlJS(url);

        if(url.includes("vidoza") || url.includes("videzz")){
            data = fetch(url, headers);
            url = data.match(/source\s*src="(.*?)"/)[1];
        }
        //loadSubtitlesOnline
        parser(url, lang, "", headers);
    }
}

function vimeo(url, lang, headers){
    var data = fetch(url, headers);
    var j = data.match(/playerConfig\s*=\s*(\{.*?\})</)[1];  
    
    try{
        j = JSON.parse(j);
        
        url = j.request.files.hls.cdns.akfire_interconnect_quic.url;

        parser(url, lang, "", headers);
    }catch(ignored){
        error(ignored.message);
    }
}

function wfilmizle(url, lang, headers){
    var initialUrl = url;

    var data = fetch(url.replace("?l=1", "").replace("?l=0", ""), headers);

    data = data.match("keremiya_part(.*?)wide-button")[1];
    var streamUrls = {};
    streamUrls["WHDPlayer"] = url;

    try{
        var alts = [...data.matchAll(/<a href="(.*?)" class="post-page-numbers"><span>(.*?)<\/span>/g)];
        for(let i = 0; i < alts.length; i++){
            if(alts[i][2].toLowerCase() != "fragman")
                streamUrls[alts[i][2]] = alts[i][1];
        }
    }catch(ignored){
        
    }

    if(!initialUrl.includes("#")){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        url = initialUrl.split("#")[1];

        data = fetch(url, headers);

        try{
            data = data.match(/<iframe loading="lazy".*?src="(.*?)"/)[1];
        }catch(ignored){
            data = data.match(/<iframe.*?src=["'](.*?)["']/)[1];

            if(data.startsWith("//"))
                data = "https:" + data;

            if(data.includes("/v/")){
                var arr = data.split("/");
                arr[2] = "fembed.com";
                url = "";
                for(var i = 0; i < arr.length; i++){
                    if(i != 0)
                        url += "/";
                    url += arr[i];
                }
            }

            if(data.includes("hdplayersystem.live")){
                var data_u = "";
                if(data.includes("/video/"))
                    data_u = data.replace("https://hdplayersystem.live/video/", "");
                else
                    data_u = data.split("data=")[1];

                url = "https://hdplayersystem.live/player/index.php?data=" + data_u + "&do=getVideo";

                headers["X-Requested-with"] = "XMLHttpRequest";
                headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";

                data = fetchPost(url, headers, "{hash:\"" + data_u + "\", r:\""+url+"\"}");

                try{
                    var j = JSON.parse(data);
                    url = j.videoSource;
                }catch(ignored){
                    error(ignored.message);
                }
            }

            delete headers["Content-Type"];
            delete headers["X-Requested-with"];
            headers["Accept"] = "*/*";

            parser(url, lang, "", headers);
        }
    }
}

// youtube 403
function wikiflix(url, lang, headers){
    var initialUrl = url;

    var splits = initialUrl.split("#");
    var id = splits[splits.length - 1];

    var showAlts = true;
    
    if(!id.startsWith("m_")){
        showAlts = false;
        id = splits[splits.length - 2];
    }

    var urlPartsQm = url.split("?")[1];
    var parts = urlPartsQm.split("&");

    var streamUrls = {};

    if(parts.length > 0){
        for(var i = 0; i < parts.length; i++){
            streamUrls["Kaynak " + (i+1)] = parts[i];
        }
    }else{
        error("Wikiflix 1957");
    }

    if(showAlts){
        Core.showAlternatesJS(JSON.stringify(streamUrls));
    }else{
        var selectedAlternate = url.split("#")[splits.length-3];
        if(selectedAlternate.startsWith("m_")){
            selectedAlternate = url.split("#")[splits.length-2];
        }
        var sub = "";

        if(selectedAlternate.length == 11){
            url = "https://www.youtube.com/watch?v=" + selectedAlternate;
        }else{
            url = "https://commons.m.wikimedia.org/wiki/File:" + selectedAlternate + "?embedplayer=yes";

            data = fetch(url, headers);

            url = data.match(/(https[^"]+vp9.webm)/)[1];

            try{
                var alts = [...data.matchAll(/\/w\/[^"]+vtt/g)];
                var altCount = 1;
                for(let i = 0; i < alts.length; i++){
                    if(alts[i][1].includes("lang=en")){
                        // ToDo: Add translate...
                        sub = alts[i][1];
                        break;
                    }
                }
            }catch(ignored){
                error(ignored.message);
            }
        }

        parser(url, lang, sub, headers);
    }
}

function yabancidizi(url, lang, headers){
    var initialUrl = url;
  
    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    var streamUrls = {};

    if(!initialUrl.includes("#")){
        var data = fetch(url, headers);

        data = data.match(/series-tabs(.*?)mofycon/)[1];

        var cookie = Core.saveCookie(url, "dbx", "series-tabs");

        var x = 0;

        while(x < 10){
            if(cookie === undefined){
                cookie = Core.saveCookie(url, "dbx", "series-tabs");
            }else{
                break;
            }
            x++;
        }

        if(cookie === undefined){
            cookie = "udys=123;";
        }

        var l = 1;

        if(lang == 0)
            l = 2;

        try{
            var alts = [...data.matchAll(/data-eid="(.*?)"\s*data-type="(.*?)"/g)];

            for(let i = 0; i < alts.length; i++){
                if(alts[i][2] == ""+l){
                    url = alts[i][1];
                }
            }
        }catch(ignored){
            error(ignored.message);
        }

        if(url == initialUrl)
            error("Yabanci Dizi - 2029");
        else{
            var episode = encodeURIComponent(url);
            var postData = "lang=" + l + "&episode=" + episode + "&type=langTab";
            url = baseUrl(initialUrl) + "/ajax/service";
            headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";
            headers["X-Requested-With"] = "XMLHttpRequest";
            headers["Cookie"] = cookie;
            
            data = fetchPost(url, headers, postData);

            try{
                var alts = [...data.matchAll(/data-hash=\\"(.*?)\\"\s*data-link=\\"(.*?)\\"/g)];
        
                for(let i = 0; i < alts.length; i++){
                    postData = "hash" + alts[i][1].replace(/\\/g, "") + "&link=" + encodeURIComponent(alts[i][2].replace(/\\/g, "")) + "&querytype=alternate&type=videoGet";
                    var temp = fetchPost(url, headers, postData);
                    var temp = temp.match(/"api_iframe":\s*"(.*?)"/)[1].replace(/\\/g, "");
                    if(temp != ""){
                        var prov = temp.match(/\/api\/(.*?)\//)[1];
                        streamUrls[prov] = temp;
                    }
                }
            }catch(ignored){
                error(ignored.message);
            }

            Core.showAlternatesJS(JSON.stringify(streamUrls));
        }
    }else{
        var base = initialUrl.split("#")[0];

        url = initialUrl.split("#")[1];

        var provider = initialUrl.split("#")[2];

        delete headers["Cookie"];
        delete headers["X-Requested-With"];
        delete headers["Content-Type"];
            
        if(provider.toLowerCase().includes("drive")){
            var lookingFor = "molystream";
            var lookingForEmbed = "I AM NOT LOOKING";
            var toClick = "playSheilaBtn";
            var clickType = 0;
            var checkMedia = false;
            getWebViewOwnContentJS(url, lookingFor, lookingForEmbed, toClick, "", clickType, checkMedia, headers, 1);
        }else{
            var cookie = Core.saveCookie(base, "dbx", "series-tabs");

            headers["Cookie"] = cookie;
            headers["Content-Type"] = "application/x-www-form-urlencoded; charset=UTF-8";

            var data = fetch(url, headers);
            
            try{
                var alts = [...data.matchAll(/<iframe(.*?)<\/iframe/g)];
        
                for(let i = 0; i < alts.length; i++){
                    if(!alts[i][1].includes("display:")){
                        url = alts[i][1];
                        break;
                    }
                }
                url = url.match(/src=['"](.*?)['"]/)[1];
            }catch(ignored){
                error(ignored.message);
            }

            delete headers["Cookie"];
            delete headers["Content-Type"];

            parser(url, lang, "", headers)
        }
    }
}

function yabanci_dizi(url, lang, headers){
    url = url.replace("yabanci-dizi", "yabancidizi");
    var initialUrl = url;

    var lookFor = "bolum-menu";

    if(url.includes("/film/"))
        lookFor = "active";

    var cookie = Core.saveCookie(url, "yb_dz", lookFor);

    headers["User-Agent"] = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36";

    url = url.replace("/turkce", "");
    var data = fetch(url, headers);

    url = data.match(/<iframe.*?src="(.*?)"/)[1];
    if(url.startsWith("//"))
        url = "https:" + url;

    parser(url, lang, "", headers);
}

// Java Callers
function fetch(url, headers){
    var data = Core.fetch(url, JSON.stringify(headers));

    if(data == "Error"){
        error("Fetch Error");
    }else{
        return data;
    }
}

function fetchPost(url, headers, postData){
    var data = Core.fetchPost(url, JSON.stringify(headers), postData);

    if(data == "Error"){
        error("FetchPost Error");
    }else{
        return data;
    }
}

// Type 0->Plays after click, 1->for checkmedia, 2->returns initialurl + # + playableurl + # + lookingforembed_url
function getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, toClick2, clickType, checkMedia, headers, type){
    Core.getWebViewOwnContentJS(initialUrl, lookingFor, lookingForEmbed, toClick, toClick2, clickType, checkMedia, JSON.stringify(headers), type);
}

function error(m){
    throw new Error(m);
}

// Helpers
function baseUrl(url, includeStart = true){
    var pathArray = url.split("/");
    var protocol = pathArray[0];
    var host = pathArray[2];
    var url = protocol + "//" + host;

    if(!includeStart){
        return host;
    }else{
        return url;
    }
}

function fixUrl(url){
    if(!url.startsWith("http")){
        var toAdd = "https";
        if(!url.startsWith(":")){
            toAdd = toAdd + ":";
            if(!url.startsWith("//"))
                toAdd = toAdd + "//";
        }

        url = toAdd + url;
    }
    return url;
}

function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
}

function unPack(code) {
    function indent(code) {
        try {
            var tabs = 0
              , old = -1
              , add = '';
            for (var i = 0; i < code.length; i++) {
                if (code[i].indexOf("{") != -1)
                    tabs++;
                if (code[i].indexOf("}") != -1)
                    tabs--;
                if (old != tabs) {
                    old = tabs;
                    add = "";
                    while (old > 0) {
                        add += "\t";
                        old--;
                    }
                    old = tabs;
                }
                code[i] = add + code[i];
            }
        } finally {
            tabs = null;
            old = null;
            add = null;
        }
        return code;
    }
    var env = {
        eval: function(c) {
            code = c;
        },
        window: {},
        document: {}
    };
    eval("with(env) {" + code + "}");
    code = (code + "").replace(/;/g, ";\n").replace(/{/g, "\n{\n").replace(/}/g, "\n}\n").replace(/\n;\n/g, ";\n").replace(/\n\n/g, "\n");
    code = code.split("\n");
    code = indent(code);
    code = code.join("\n");
    return code;
}
