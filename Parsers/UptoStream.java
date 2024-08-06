package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Base64;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.swenauk.mainmenu.GetsPack.GetUpTo;
import com.swenauk.seyirturk.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UptoStream extends Parsers {
    public String parsed;

    public UptoStream(String str, Context context, ExoPlayer exoPlayer) {
        super(str, context, exoPlayer);
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void parse(String str) {
        new GetUpTo(this).execute(str);
    }

    public void resumeParse() {
        try {
            for (String str : this.parsed.split("\n")) {
                if (str.contains("window.sources")) {
                    Matcher matcher = Pattern.compile("window.sources(.*)").matcher(str);
                    if (matcher.find()) {
                        try {
                            JSONArray jSONArray = new JSONArray(new String(Base64.decode(matcher.group(1).replace(StringUtils.SPACE, "").replace("JSON.parse", "").replace("=", "").replace("(", "").replace(")", "").replace(";", "").replace("'", "").replace("atob", ""), 0)));
                            for (int r6 = 0; r6 < jSONArray.length(); r6++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(r6);
                                this.streamUrls.put(jSONObject.getString(HtmlLabel.TAG_NAME), jSONObject.getString(DomElement.SRC_ATTRIBUTE));
                            }
                        } catch (Exception unused) {
                            showBuffer();
                            showAlert();
                        }
                    }
                }
            }
            if (this.streamUrls.isEmpty()) {
                showBuffer();
                showAlert();
            } else {
                createAlertWItems("Çözünürlük Seçiniz");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.UptoStream.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r4) {
                UptoStream.this.showBuffer();
                UptoStream uptoStream = UptoStream.this;
                uptoStream.videoUri = Uri.parse(uptoStream.streamUrls.get(charSequenceArr[r4]));
                DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(UptoStream.this.calledContext, Util.getUserAgent(UptoStream.this.calledContext, "iFrame"));
                UptoStream.this.mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(MediaItem.fromUri(UptoStream.this.videoUri));
                UptoStream.this.playVideo();
            }
        });
        this.alert = builder.create();
        this.alert.show();
    }
}
