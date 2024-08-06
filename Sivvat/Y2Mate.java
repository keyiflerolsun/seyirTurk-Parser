package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.webkit.WebView;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;
import com.google.android.exoplayer2.ExoPlayer;
import com.swenauk.mainmenu.Sivvat.Core;
import java.net.URLEncoder;
import java.util.List;

/* loaded from: classes3.dex */
public class Y2Mate extends Core {
    public int maxP;
    public String v_id;

    public Y2Mate(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer, webView);
        this.v_id = "";
        this.maxP = 0;
        this.stepType = Core.StepType.GetUrlContent;
        this.parserType = Core.ParserType.PostRequest;
        this.uaType = Core.UAType.Nondroid;
        this.v_id = Helper.pregMatchAll("(?:v=|embed\\/)(.*?)(?:&|$)", str).get(0);
        this.url = "https://www.y2mate.com/mates/analyzeV2/ajax";
        try {
            this.postData = "k_query=" + URLEncoder.encode(str, "UTF-8") + "&k_page=home&kl=en&q_auto=0";
            headersClear();
            this.headers.put(HttpHeader.CONTENT_TYPE_LC, "application/x-www-form-urlencoded; charset=UTF-8");
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Y2Mate.1
                @Override // java.lang.Runnable
                public void run() {
                    Y2Mate.this.start();
                }
            }).start();
        } catch (Exception unused) {
            this.url = "";
            oldParserIntegration();
        }
    }

    @Override // com.swenauk.mainmenu.Sivvat.Core
    public void next() {
        super.next();
        if (this.nextCount == 1) {
            VideoInfo data = new YoutubeDownloader().getVideoInfo(new RequestVideoInfo(this.v_id)).data();
            if (data != null) {
                List<VideoFormat> videoFormats = data.videoFormats();
                for (int r1 = 0; r1 < videoFormats.size(); r1++) {
                    if ((videoFormats.get(r1) instanceof VideoWithAudioFormat) && this.height < videoFormats.get(r1).height().intValue()) {
                        this.url = videoFormats.get(r1).url();
                    }
                }
            }
            oldParserIntegration();
        }
    }
}
