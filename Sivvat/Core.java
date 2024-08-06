package com.swenauk.mainmenu.Sivvat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AlertDialog;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.net.HttpHeaders;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.swenauk.mainmenu.Parsers.Adult;
import com.swenauk.mainmenu.Parsers.CloseLoad;
import com.swenauk.mainmenu.Parsers.Contentx;
import com.swenauk.mainmenu.Parsers.DailyMotion;
import com.swenauk.mainmenu.Parsers.Fembed;
import com.swenauk.mainmenu.Parsers.FileRU;
import com.swenauk.mainmenu.Parsers.MailRU;
import com.swenauk.mainmenu.Parsers.OkRu;
import com.swenauk.mainmenu.Parsers.Parsers;
import com.swenauk.mainmenu.Parsers.SuperVideo;
import com.swenauk.mainmenu.Parsers.UptoStream;
import com.swenauk.mainmenu.Parsers.VidMoly;
import com.swenauk.mainmenu.Parsers.YoutubeWGetter;
import com.swenauk.mainmenu.Statics;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.sourceforge.htmlunit.xpath.compiler.Keywords;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.htmlunit.org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class Core extends Parsers {
    public boolean bypass;
    public boolean checkMedia;
    public int clickType;
    public int completeCount;
    String cookie;
    String e;
    public Map<String, String> headers;
    public String initialUrl;
    public boolean isDoubleClick;
    public boolean isDoubleClickComplete;
    public boolean isJSDone;
    public boolean isTv;
    public String jsToAdd;
    public Language language;
    public String lookingFor;
    public String lookingForEmbed;
    private MergingMediaSource mergedSource;
    public int nextCount;
    public String pageContent;
    public ParserType parserType;
    public String postData;
    public int reloadCount;
    public int reloadFor;
    public Result result;
    public String root;
    String s;
    public String selectedAlternate;
    public StepType stepType;
    public String subtitle;
    public HashMap<String, String> subtitles;
    public String toClick;
    public UAType uaType;
    public String url;
    public int waitBeforeReload;
    public WebView webView;
    public static StatusType statusType = StatusType.None;
    public static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    /* loaded from: classes3.dex */
    public enum Language {
        TR,
        EN,
        GE,
        None
    }

    /* loaded from: classes3.dex */
    public enum ParserType {
        WebView,
        WebClient,
        GetRequest,
        PostRequest,
        WebViewAutoClick,
        OnlineParser,
        None
    }

    /* loaded from: classes3.dex */
    public enum Result {
        Playable,
        Error,
        None
    }

    /* loaded from: classes3.dex */
    public enum StatusType {
        Working,
        Idle,
        Error,
        None
    }

    /* loaded from: classes3.dex */
    public enum StepType {
        Javascript,
        GetUrlContent,
        JavaParse,
        Play,
        None
    }

    /* loaded from: classes3.dex */
    public enum UAType {
        Nondroid,
        Classic,
        Custom,
        None
    }

    public void translateSubFromFile() {
    }

    public Core(String str, Context context, ExoPlayer exoPlayer, WebView webView) {
        super(str, context, exoPlayer);
        this.parserType = ParserType.None;
        this.stepType = StepType.None;
        this.result = Result.None;
        this.language = Language.None;
        this.uaType = UAType.None;
        this.webView = null;
        this.pageContent = "";
        this.url = "";
        this.lookingFor = "";
        this.lookingForEmbed = "";
        this.root = "";
        this.postData = "";
        this.selectedAlternate = "";
        this.initialUrl = "";
        this.subtitle = "";
        this.bypass = false;
        this.subtitles = new HashMap<>();
        this.nextCount = 0;
        this.reloadCount = 0;
        this.reloadFor = 0;
        this.waitBeforeReload = 0;
        this.completeCount = 0;
        this.jsToAdd = "";
        this.toClick = "";
        this.clickType = 0;
        this.isTv = false;
        this.e = "";
        this.s = "";
        this.isDoubleClick = false;
        this.isDoubleClickComplete = false;
        this.cookie = "";
        this.checkMedia = false;
        this.isJSDone = false;
        this.initialUrl = str;
        setRoot(str);
        this.webView = webView;
        HashMap hashMap = new HashMap();
        this.headers = hashMap;
        hashMap.put("User-Agent", System.getProperty("http.agent"));
    }

    public void start() {
        if (this.stepType == StepType.GetUrlContent) {
            getUrlContent();
        } else if (this.stepType == StepType.JavaParse) {
            next();
        }
    }

    public void next() {
        this.nextCount++;
    }

    public void end() {
        statusType = StatusType.Idle;
        this.nextCount = 10000;
    }

    public void setUserAgent(String str, boolean z) {
        this.uaType = UAType.Custom;
        Statics.userAgent = str;
        if (z) {
            headersClear();
        } else {
            this.headers.remove("User-Agent");
            this.headers.put("User-Agent", Statics.userAgent);
        }
    }

    public void setRoot(String str) {
        try {
            URL url = new URL(str);
            this.root = url.getProtocol() + "://" + url.getHost();
        } catch (Exception unused) {
            this.root = "";
        }
    }

    public void getUrlContent() {
        if (this.url.equals("")) {
            return;
        }
        if (this.parserType == ParserType.WebView) {
            if (this.webView != null) {
                getWebViewContent();
                next();
                return;
            } else {
                showBuffer();
                showAlert();
                return;
            }
        }
        if (this.parserType == ParserType.GetRequest) {
            getGetRequestContent("", false);
            next();
            return;
        }
        if (this.parserType == ParserType.PostRequest) {
            getPostRequestContent();
            next();
        } else if (this.parserType == ParserType.WebViewAutoClick) {
            getWebViewOwnContent();
            next();
        } else if (this.parserType == ParserType.OnlineParser) {
            onlineParserWebView();
        }
    }

    public void onlineParserWebView() {
        int r0 = 0;
        try {
            if (this.language == Language.TR) {
                r0 = 1;
            } else if (this.language == Language.GE) {
                r0 = 2;
            }
            final String str = Statics.SERVER + "/sey/back/v2/parser/parser.php?url=" + URLEncoder.encode(this.url, "UTF-8") + "&lang=" + r0;
            ((Activity) this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.1
                @Override // java.lang.Runnable
                public void run() {
                    Core.this.webView.getSettings().setJavaScriptEnabled(true);
                    Core.this.webView.addJavascriptInterface(Core.this, "Core");
                    Core.this.setUserAgent();
                    Core.this.webView.loadUrl(str);
                }
            });
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public void showResult(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
        builder.setTitle("Debug");
        builder.setMessage(str);
        this.alert = builder.create();
        this.alert.show();
    }

    @JavascriptInterface
    public void showAlternatesJS(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (jSONObject.get(next) instanceof String) {
                    this.streamUrls.put(next, jSONObject.getString(next));
                }
            }
        } catch (Exception unused) {
        }
        if (this.streamUrls.size() > 1) {
            final CharSequence[] charSequenceArr = (CharSequence[]) this.streamUrls.keySet().toArray(new CharSequence[this.streamUrls.keySet().size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.calledContext, R.style.AlertDialog);
            builder.setTitle("Kaynak Seçiniz:");
            builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Sivvat.Core.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, final int r4) {
                    Core core = Core.this;
                    core.selectedAlternate = core.streamUrls.get(charSequenceArr[r4].toString());
                    ((Activity) Core.this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int r1 = 0;
                            try {
                                if (Core.this.language == Language.TR) {
                                    r1 = 1;
                                } else if (Core.this.language == Language.GE) {
                                    r1 = 2;
                                }
                                WebView webView = Core.this.webView;
                                StringBuilder sb = new StringBuilder();
                                sb.append(Statics.SERVER);
                                sb.append("/sey/back/v2/parser/parser.php?url=");
                                sb.append(URLEncoder.encode(Core.this.url + "#" + Core.this.selectedAlternate + "#" + ((Object) charSequenceArr[r4]), "UTF-8"));
                                sb.append("&lang=");
                                sb.append(r1);
                                webView.loadUrl(sb.toString());
                            } catch (Exception unused2) {
                            }
                        }
                    });
                }
            });
            this.alert = builder.create();
            this.alert.show();
            return;
        }
        ((Activity) this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.3
            @Override // java.lang.Runnable
            public void run() {
                int r1;
                try {
                    if (Core.this.language == Language.TR) {
                        r1 = 1;
                    } else {
                        r1 = Core.this.language == Language.GE ? 2 : 0;
                    }
                    Core core = Core.this;
                    core.selectedAlternate = core.streamUrls.get(Core.this.streamUrls.keySet().toArray()[0]);
                    WebView webView = Core.this.webView;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Statics.SERVER);
                    sb.append("/sey/back/v2/parser/parser.php?url=");
                    sb.append(URLEncoder.encode(Core.this.url + "#" + Core.this.selectedAlternate + "#" + Core.this.streamUrls.keySet().toArray()[0], "UTF-8"));
                    sb.append("&lang=");
                    sb.append(r1);
                    webView.loadUrl(sb.toString());
                } catch (Exception unused2) {
                }
            }
        });
    }

    @JavascriptInterface
    public void playVideoJS(String str, String str2, String str3, boolean z) {
        this.bypass = z;
        if (!str.contains("trstx") && !str.contains("sobreatsesuyp")) {
            this.stepType = StepType.Play;
        }
        this.url = str;
        if (str2.startsWith("{") || str2.startsWith("[")) {
            try {
                JSONArray jSONArray = new JSONArray(str2);
                for (int r5 = 0; r5 < jSONArray.length(); r5++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(r5);
                    this.subtitles.put(jSONObject.getString(Keywords.FUNC_LANG_STRING), jSONObject.getString("url"));
                }
            } catch (Exception unused) {
            }
        } else {
            this.subtitle = str2;
        }
        headersClear();
        try {
            JSONObject jSONObject2 = new JSONObject(str3);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (jSONObject2.get(next) instanceof String) {
                    this.headers.put(next, jSONObject2.getString(next));
                }
            }
        } catch (Exception unused2) {
        }
        oldParserIntegration();
    }

    @JavascriptInterface
    public void showOnError() {
        showBuffer();
        showAlert();
    }

    @JavascriptInterface
    public static String unpack(String str) {
        ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("js");
        try {
            engineByName.eval("function unPack (code) {\n\tfunction indent (code) {\n\t\ttry {\n\t\tvar tabs = 0, old=-1, add='';\n\t\tfor(var i=0;i<code.length;i++) {\n\t\t\tif(code[i].indexOf(\"{\") != -1) tabs++;\n\t\t\tif(code[i].indexOf(\"}\") != -1) tabs--;\n\t\t\t\n\t\t\tif(old != tabs) {\n\t\t\t\told = tabs;\n\t\t\t\tadd = \"\";\n\t\t\t\twhile (old > 0) {\n\t\t\t\t\tadd += \"\\t\";\n\t\t\t\t\told--;\n\t\t\t\t}\n\t\t\t\told = tabs;\n\t\t\t}\n\t\t\t\n\t\t\tcode[i] = add + code[i];\n\t\t}\n\t\t} finally {\n\t\t\ttabs = null;\n\t\t\told = null;\n\t\t\tadd = null;\n\t\t}\n\t\treturn code;\n\t}\n    \n    var env = {\n        eval: function (c) {\n            code = c;\n        },\n        window: {},\n        document: {}\n    };\n    \n    eval(\"with(env) {\" + code + \"}\");\n\t\n\tcode = (code+\"\").replace(/;/g, \";\\n\").replace(/{/g, \"\\n{\\n\").replace(/}/g, \"\\n}\\n\").replace(/\\n;\\n/g, \";\\n\").replace(/\\n\\n/g, \"\\n\");\n\t\n    code = code.split(\"\\n\");\n    code = indent(code);\n    \n    code = code.join(\"\\n\");\n    return code;\n} ");
            return (String) ((Invocable) engineByName).invokeFunction("unPack", str);
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
            return "";
        }
    }

    @JavascriptInterface
    public String fetch(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (jSONObject.get(next) instanceof String) {
                    httpURLConnection.setRequestProperty(next, jSONObject.getString(next));
                }
            }
            if (Statics.cookieManager.getCookie(this.root) != null && !this.headers.containsKey("Cookie")) {
                String cookie = Statics.cookieManager.getCookie(this.root);
                if (this.root.contains("dizilab")) {
                    cookie = cookie + " __cf_bm";
                }
                httpURLConnection.setRequestProperty("Cookie", cookie);
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception unused) {
            return "Error";
        }
    }

    @JavascriptInterface
    public String fetchCFF(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (jSONObject.get(next) instanceof String) {
                    httpURLConnection.setRequestProperty(next, jSONObject.getString(next));
                }
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            bufferedReader.close();
            String stringBuffer2 = stringBuffer.toString();
            return stringBuffer2.contains(str3) ? httpURLConnection.getHeaderField("Set-Cookie") : stringBuffer2;
        } catch (Exception unused) {
            return "Error";
        }
    }

    @JavascriptInterface
    public String fetchPost(String str, String str2, String str3) {
        String str4;
        try {
            byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            JSONObject jSONObject = new JSONObject(str2);
            Iterator<String> keys = jSONObject.keys();
            while (true) {
                str4 = "";
                if (!keys.hasNext()) {
                    break;
                }
                String next = keys.next();
                if (jSONObject.get(next) instanceof String) {
                    if (next.equals("Content-Length")) {
                        httpURLConnection.setRequestProperty(next, "" + length);
                    } else {
                        httpURLConnection.setRequestProperty(next, jSONObject.getString(next));
                    }
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return str4;
                    }
                    str4 = str4 + readLine;
                }
            } finally {
            }
        } catch (Exception unused) {
            return "Error";
        }
    }

    @JavascriptInterface
    public String getRedirectUrlJS(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.connect();
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }

    @JavascriptInterface
    public String makeID(int r6) {
        Random random = new Random();
        String str = "";
        for (int r2 = 0; r2 < r6; r2++) {
            str = str + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62));
        }
        return str;
    }

    @JavascriptInterface
    public String bytesToHex(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[bytes.length * 2];
        for (int r1 = 0; r1 < bytes.length; r1++) {
            int r2 = bytes[r1] & 255;
            int r3 = r1 * 2;
            byte[] bArr2 = HEX_ARRAY;
            bArr[r3] = bArr2[r2 >>> 4];
            bArr[r3 + 1] = bArr2[r2 & 15];
        }
        return new String(bArr, StandardCharsets.UTF_8);
    }

    @JavascriptInterface
    public String saveCookie(String str, String str2, String str3) {
        this.lookingFor = str3;
        this.url = str;
        this.reloadFor = 3;
        this.headers.put("Referer", "https://dizilab.com");
        getWebViewContent();
        for (int r3 = 0; r3 < 20; r3++) {
            try {
                if (Statics.cookieManager.getCookie(this.root) == null) {
                    break;
                }
                Thread.sleep(100L);
            } catch (Exception unused) {
            }
        }
        saveCookieToServer(str2);
        this.url = this.initialUrl;
        return Statics.cookieManager.getCookie(this.root);
    }

    public void headersClear() {
        this.headers.clear();
        if (Statics.userAgent.equals("")) {
            this.headers.put("User-Agent", System.getProperty("http.agent"));
        } else {
            this.headers.put("User-Agent", Statics.userAgent);
        }
    }

    public String stripToParse(String str, boolean z) {
        if (str.contains("?l=1")) {
            if (z) {
                this.language = Language.EN;
            } else {
                this.language = Language.TR;
            }
        } else if (str.contains("?l=0")) {
            if (z) {
                this.language = Language.TR;
            } else {
                this.language = Language.EN;
            }
        } else if (str.contains("turkce-dublaj")) {
            this.language = Language.TR;
        } else if (str.contains("turkce-altyazi")) {
            this.language = Language.EN;
        } else if (str.contains("altyazi")) {
            this.language = Language.EN;
        } else if (str.contains("dublaj")) {
            this.language = Language.TR;
        } else {
            this.language = Language.GE;
        }
        return str.replace("?l=1", "").replace("?l=0", "").replace("?l=3", "");
    }

    public void reloadPage() {
        ((Activity) this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((VideoView) Core.this.calledContext).webView.reload();
                } catch (Exception unused) {
                }
            }
        });
    }

    public void killWebView() {
        ((Activity) this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((VideoView) Core.this.calledContext).webView.destroy();
                } catch (Exception unused) {
                }
            }
        });
    }

    public void waitForCookie() {
        while (Statics.cookieManager.getCookie(this.root) == null) {
            try {
                Thread.sleep(250L);
            } catch (Exception unused) {
            }
        }
    }

    public void waitForReloadAndCookie() {
        waitWhileWorking();
        this.reloadCount = 0;
        this.reloadFor = 3;
        reloadPage();
        waitForCookie();
    }

    public void saveCookieToServer(String str) {
        try {
            String cookie = Statics.cookieManager.getCookie(this.root);
            if (this.url.contains("dizilab")) {
                cookie = cookie + " __cf_bm=";
            }
            getGetRequestContent(Statics.SERVER + "/sey/cfcache.php?type=" + str + "&cookie=" + URLEncoder.encode(cookie, "UTF-8") + "&ua=" + Statics.userAgent, true);
        } catch (Exception unused) {
        }
    }

    public void saveToServer(String str, String str2) {
        try {
            getGetRequestContent(Statics.SERVER + "/sey/cfcache.php?type=" + str + "&cookie=" + URLEncoder.encode(str2, "UTF-8"), true);
        } catch (Exception unused) {
        }
    }

    public void waitWhileWorking() {
        while (statusType == StatusType.Working) {
            try {
                Thread.sleep(250L);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: com.swenauk.mainmenu.Sivvat.Core$6, reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((Activity) Core.this.calledContext).isDestroyed()) {
                return;
            }
            if (Core.this.streamUrls.size() > 1) {
                final CharSequence[] charSequenceArr = (CharSequence[]) Core.this.streamUrls.keySet().toArray(new CharSequence[Core.this.streamUrls.keySet().size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(Core.this.calledContext, R.style.AlertDialog);
                builder.setTitle("Kaynak Seçiniz:");
                builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Sivvat.Core.6.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int r4) {
                        Core.this.selectedAlternate = Core.this.streamUrls.get(charSequenceArr[r4].toString());
                        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.6.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Core.this.next();
                            }
                        }).start();
                    }
                });
                Core.this.alert = builder.create();
                Core.this.alert.show();
                return;
            }
            if (Core.this.streamUrls.size() != 1) {
                Core.this.showBuffer();
                Core.this.showAlert();
            } else {
                CharSequence[] charSequenceArr2 = (CharSequence[]) Core.this.streamUrls.keySet().toArray(new CharSequence[Core.this.streamUrls.keySet().size()]);
                Core core = Core.this;
                core.selectedAlternate = core.streamUrls.get(charSequenceArr2[0].toString());
                new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.6.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Core.this.next();
                    }
                }).start();
            }
        }
    }

    public void showAlternates() {
        ((Activity) this.calledContext).runOnUiThread(new AnonymousClass6());
    }

    public void oldParserIntegration() {
        if (!this.url.contains("dood") || this.initialUrl.contains("dood")) {
            killWebView();
        }
        try {
            String str = this.url;
            if (this.bypass) {
                if (this.stepType != StepType.Play && !Helper.containsAny(str, "m3u8", Statics.sheila, "googlevideo", "jetfim", "dood", "jtfi", "hls", ".txt", "mp4", "video-delivery")) {
                    showBuffer();
                    showAlert();
                    return;
                }
                this.streamUrl = str;
                prepareVideo();
                return;
            }
            if (str.contains("mail.ru")) {
                new MailRU(str, this.calledContext, this.player);
                return;
            }
            if (!str.contains("filemoon") && !str.contains("mixdrop") && !str.contains("voe") && !str.contains("figeterpiazine") && !str.contains("streamtape") && !str.contains("videobin") && !str.contains("gounlimited") && !str.contains("pornhub") && !str.contains("ashemaletube") && !str.contains("xvideos") && !str.contains("clipwatching") && !str.contains("7dak.com") && !str.contains("xnxx.com") && !str.contains("unlockxh1.com") && !str.contains("xhamster.com")) {
                if (str.contains("uptostream")) {
                    new UptoStream(str, this.calledContext, this.player);
                    return;
                }
                if (str.contains("closeload") && !str.contains("hls.closeload") && !str.contains(".txt")) {
                    new CloseLoad(str, this.calledContext, this.player);
                    return;
                }
                if (!str.contains("vidmoly") && !str.contains("flmplayer")) {
                    if (!str.contains("ok.ru") && !str.contains("odnok")) {
                        if (!str.contains("fileru.net") && !str.contains("file.ru")) {
                            if (!str.contains("contentx") && !str.contains("playru") && !str.contains("filese") && !str.contains("hotlinger") && !str.contains("//four") && !str.contains("pichive")) {
                                if (!str.contains("youtubeiptv") && (!str.contains("youtube") || str.contains("videoplayback"))) {
                                    if (str.contains("dailymotion")) {
                                        new DailyMotion(str, this.calledContext, this.player);
                                        return;
                                    }
                                    if (str.contains("vk.com")) {
                                        new VKcom(str, this.calledContext, this.player, this.webView);
                                        return;
                                    }
                                    if (str.contains("videoseyred")) {
                                        new Videoseyred(str, this.calledContext, this.player, this.webView);
                                        return;
                                    }
                                    if (str.contains("streamlare")) {
                                        new Streamlare(str, this.calledContext, this.player, this.webView).subtitle = this.subtitle;
                                        return;
                                    }
                                    if (str.contains("dood") && !str.contains(SchemaSymbols.ATTVAL_TOKEN)) {
                                        Dood dood = new Dood(str, this.calledContext, this.player, this.webView);
                                        dood.subtitles = this.subtitles;
                                        dood.subtitle = this.subtitle;
                                        return;
                                    }
                                    if (Helper.containsAny(str, "supervideo", "dropload", "upstream", "streamhub", "filelions", "streamwish")) {
                                        final SuperVideo superVideo = new SuperVideo(str, this.calledContext, this.player, true);
                                        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.8
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                while (superVideo.streamUrl == null) {
                                                    try {
                                                        Thread.sleep(250L);
                                                        if (((Activity) Core.this.calledContext).isDestroyed()) {
                                                            break;
                                                        }
                                                    } catch (InterruptedException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                                if (((Activity) Core.this.calledContext).isDestroyed()) {
                                                    return;
                                                }
                                                ((Activity) Core.this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.8.1
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        Core.this.streamUrl = superVideo.streamUrl;
                                                        Core.this.prepareVideo();
                                                    }
                                                });
                                            }
                                        }).start();
                                        return;
                                    }
                                    if (!str.contains("femax") && !str.contains("feurl.com") && !str.contains("fembed.net") && !str.contains("vcdn.io") && !str.contains("fplay.cf") && !str.contains("fembed.com") && !str.contains("dutrag.com") && !str.contains("suhiaza") && (!str.contains("/v/") || this.stepType == StepType.Play)) {
                                        if (!str.contains("trstx") && (!str.contains("sobreatsesuyp") || this.stepType == StepType.Play)) {
                                            if (this.stepType != StepType.Play && !Helper.containsAny(str, "m3u8", Statics.sheila, "googlevideo", "jetfim", "dood", "jtfi", "hls", ".txt", "mp4", "video-delivery")) {
                                                showBuffer();
                                                showAlert();
                                                return;
                                            }
                                            this.streamUrl = str;
                                            prepareVideo();
                                            return;
                                        }
                                        new Trstx(str, this.calledContext, this.player, null, this.language);
                                        return;
                                    }
                                    new Fembed(str, this.calledContext, this.player);
                                    return;
                                }
                                new YoutubeWGetter(str.replace("youtubeiptv", "youtube"), this.calledContext, this.player);
                                return;
                            }
                            if (this.language == Language.TR) {
                                str = str + "?dub";
                            }
                            new Contentx(str, this.calledContext, this.player, this.initialUrl);
                            return;
                        }
                        new FileRU(str, this.calledContext, this.player);
                        return;
                    }
                    new OkRu(str, this.calledContext, this.player);
                    return;
                }
                new VidMoly(str, this.calledContext, this.player);
                return;
            }
            final Adult adult = new Adult(str, this.calledContext, this.player, true);
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.7
                @Override // java.lang.Runnable
                public void run() {
                    while (adult.streamUrl == null) {
                        try {
                            Thread.sleep(250L);
                            if (((Activity) Core.this.calledContext).isDestroyed()) {
                                break;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (((Activity) Core.this.calledContext).isDestroyed()) {
                        return;
                    }
                    ((Activity) Core.this.calledContext).runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Core.this.streamUrl = adult.streamUrl;
                            Core.this.prepareVideo();
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.swenauk.mainmenu.Parsers.Parsers
    protected void showVideo() {
        if (Helper.containsAny(this.streamUrl, "storage.yandex", ".mp4", "googlevideo", "jetfim", "dood", "streamtape", "video-delivery", "goodstream", ".webm") && !this.streamUrl.contains(".m3u8") && !this.streamUrl.contains("hls.closeload") && !this.streamUrl.contains(".txt")) {
            if (this.streamUrl.contains("yandex")) {
                this.mediaSource = new ProgressiveMediaSource.Factory(this.dataSourceFactory).createMediaSource(MediaItem.fromUri(this.videoUri));
            } else {
                this.mediaSource = new ProgressiveMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Sivvat.Core.9
                    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                    public DataSource createDataSource() {
                        DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(Core.this.headers.get("User-Agent"));
                        for (Map.Entry<String, String> entry : Core.this.headers.entrySet()) {
                            if (entry.getValue() != null) {
                                defaultHttpDataSource.setRequestProperty(entry.getKey(), entry.getValue());
                            }
                        }
                        return defaultHttpDataSource;
                    }
                }).createMediaSource(MediaItem.fromUri(this.videoUri));
            }
        } else {
            this.mediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.Sivvat.Core.10
                @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(Core.this.headers.get("User-Agent"));
                    for (Map.Entry<String, String> entry : Core.this.headers.entrySet()) {
                        if (entry.getValue() != null) {
                            defaultHttpDataSource.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                    return defaultHttpDataSource;
                }
            }).createMediaSource(MediaItem.fromUri(this.videoUri));
        }
        if (!this.subtitle.equals("") || this.subtitles.size() > 0) {
            DefaultDataSource.Factory factory = new DefaultDataSource.Factory(this.calledContext, new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true).setUserAgent(System.getProperty("http.agent")).setDefaultRequestProperties(this.headers).setConnectTimeoutMs(8000).setConnectTimeoutMs(8000));
            if (!this.subtitle.equals("")) {
                SingleSampleMediaSource createMediaSource = new SingleSampleMediaSource.Factory(factory).createMediaSource(new MediaItem.SubtitleConfiguration.Builder(Uri.parse(this.subtitle)).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(HtmlTableRow.TAG_NAME).build(), C.TIME_UNSET);
                MergingMediaSource mergingMediaSource = this.mergedSource;
                if (mergingMediaSource != null) {
                    this.mergedSource = new MergingMediaSource(mergingMediaSource, createMediaSource);
                } else {
                    this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource);
                }
            } else {
                Iterator<Map.Entry<String, String>> it = this.subtitles.entrySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().getKey();
                    String str = this.subtitles.get(key);
                    Uri parse = Uri.parse(str);
                    MediaItem.SubtitleConfiguration build = new MediaItem.SubtitleConfiguration.Builder(parse).setMimeType(MimeTypes.TEXT_VTT).setSelectionFlags(-1).setLanguage(key).build();
                    if (str.contains("srt") && !str.contains(".vtt")) {
                        build = new MediaItem.SubtitleConfiguration.Builder(parse).setMimeType(MimeTypes.APPLICATION_SUBRIP).setSelectionFlags(-1).setLanguage(key).build();
                    }
                    SingleSampleMediaSource createMediaSource2 = new SingleSampleMediaSource.Factory(factory).createMediaSource(build, C.TIME_UNSET);
                    MergingMediaSource mergingMediaSource2 = this.mergedSource;
                    if (mergingMediaSource2 != null) {
                        this.mergedSource = new MergingMediaSource(mergingMediaSource2, createMediaSource2);
                    } else {
                        this.mergedSource = new MergingMediaSource(this.mediaSource, createMediaSource2);
                    }
                }
            }
            playVideoSub();
            return;
        }
        playVideo();
    }

    public void setUserAgent() {
        if (this.uaType == UAType.Nondroid) {
            Statics.userAgent = this.webView.getSettings().getUserAgentString().replace(" Mobile", "").replace("mobile", "").replace(" Version/4.0", "").replace("version", "");
            Statics.userAgent = Statics.userAgent.substring(0, Statics.userAgent.indexOf("(") + 1) + "Windows NT 10.0; Win64; x64" + Statics.userAgent.substring(Statics.userAgent.indexOf(")"));
            this.webView.getSettings().setUserAgentString(Statics.userAgent);
            return;
        }
        if (this.uaType == UAType.Custom) {
            Statics.userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        } else {
            Statics.userAgent = this.webView.getSettings().getUserAgentString();
        }
    }

    public void getWebViewContent() {
        statusType = StatusType.Working;
        ((Activity) this.calledContext).runOnUiThread(new AnonymousClass11(this.url));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.swenauk.mainmenu.Sivvat.Core$11, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ String val$urlToGet;

        AnonymousClass11(String str) {
            this.val$urlToGet = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Core.this.webView.getSettings().setJavaScriptEnabled(true);
            Core.this.webView.setWebViewClient(new WebViewClient() { // from class: com.swenauk.mainmenu.Sivvat.Core.11.1
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str) {
                    webView.evaluateJavascript("(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();", new ValueCallback<String>() { // from class: com.swenauk.mainmenu.Sivvat.Core.11.1.1
                        @Override // android.webkit.ValueCallback
                        public void onReceiveValue(String str2) {
                            if (str2.contains(Core.this.lookingFor)) {
                                Core.this.pageContent = Helper.decodeUnicode(str2);
                                Core.statusType = StatusType.Idle;
                            }
                            if (Core.this.reloadCount == Core.this.reloadFor) {
                                Core.statusType = StatusType.Idle;
                            }
                            Core.this.reloadCount++;
                        }
                    });
                }

                @Override // android.webkit.WebViewClient
                public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                    return super.shouldInterceptRequest(webView, webResourceRequest);
                }

                @Override // android.webkit.WebViewClient
                public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                    super.onReceivedError(webView, webResourceRequest, webResourceError);
                }
            });
            Core.this.setUserAgent();
            CookieManager.getInstance().setAcceptCookie(true);
            if (Core.this.url.contains("dood")) {
                Core.this.webView.loadUrl(this.val$urlToGet, Core.this.headers);
            } else {
                Core.this.webView.loadUrl(this.val$urlToGet);
            }
        }
    }

    public void getWebViewOwnContent() {
        String str;
        String str2;
        String str3;
        String str4;
        statusType = StatusType.Working;
        String str5 = this.toClick;
        if (this.isDoubleClick) {
            str = str5.split("\\|")[1];
            str5 = str5.split("\\|")[0];
        } else {
            str = "";
        }
        int r1 = this.clickType;
        String str6 = "(function() { ";
        if (r1 == -1) {
            str3 = ("(function() { var link = document.getElementById('" + str5 + "');\n") + "link.click();\n";
            str4 = ("(function() { var link = document.getElementById('" + str + "');\n") + "link.click();\n";
        } else if (r1 >= 0) {
            str3 = ("(function() { var link = document.getElementsByClassName('" + str5 + "');\n") + "link[" + this.clickType + "].click();\n";
            str4 = ("(function() { var link = document.getElementsByClassName('" + str + "');\n") + "link[" + this.clickType + "].click();\n";
        } else {
            if (r1 == -2) {
                str6 = "(function() { buttons = document.getElementsByClassName(\"dropdown-menu dropdown-menu-left\")[0].getElementsByTagName(\"button\");\nvar found = -1;\nfor (var i = 0; i < buttons.length; i++) {\n   if (buttons[i].textContent == 'Season " + this.s + "') {\n    found = buttons[i].dataset.id;\n    break;\n  }\n}\n\nif(found != -1){\n    epButs = document.getElementById('season_' + found).getElementsByTagName(\"button\");\n    epButs[" + this.e + "].click();\n}";
                str2 = "(function() { ";
            } else {
                str2 = "(function() { ";
            }
            ((Activity) this.calledContext).runOnUiThread(new AnonymousClass12(str6 + "})();", str2 + "})();"));
        }
        str2 = str4;
        str6 = str3;
        ((Activity) this.calledContext).runOnUiThread(new AnonymousClass12(str6 + "})();", str2 + "})();"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.swenauk.mainmenu.Sivvat.Core$12, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass12 implements Runnable {
        final /* synthetic */ String val$clickFinal;
        final /* synthetic */ String val$secondClickFinal;

        AnonymousClass12(String str, String str2) {
            this.val$clickFinal = str;
            this.val$secondClickFinal = str2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.swenauk.mainmenu.Sivvat.Core$12$1, reason: invalid class name */
        /* loaded from: classes3.dex */
        public class AnonymousClass1 extends WebViewClient {
            AnonymousClass1() {
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(final WebView webView, String str) {
                new Handler().postDelayed(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.12.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((VideoView) Core.this.calledContext).isFinishing()) {
                            return;
                        }
                        webView.evaluateJavascript(AnonymousClass12.this.val$clickFinal, new ValueCallback<String>() { // from class: com.swenauk.mainmenu.Sivvat.Core.12.1.1.1
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str2) {
                                Core.this.isJSDone = true;
                            }
                        });
                    }
                }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                if (Core.this.isDoubleClick) {
                    new Handler().postDelayed(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.12.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (((VideoView) Core.this.calledContext).isFinishing()) {
                                return;
                            }
                            webView.evaluateJavascript(AnonymousClass12.this.val$secondClickFinal, new ValueCallback<String>() { // from class: com.swenauk.mainmenu.Sivvat.Core.12.1.2.1
                                @Override // android.webkit.ValueCallback
                                public void onReceiveValue(String str2) {
                                }
                            });
                            Core.this.isDoubleClickComplete = true;
                        }
                    }, 5000L);
                }
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                int r2 = 0;
                if (Core.this.checkMedia) {
                    if (webResourceRequest.getUrl().getHost().contains(Core.this.lookingFor)) {
                        try {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(String.valueOf(webResourceRequest.getUrl())).openConnection()));
                            for (Map.Entry<String, String> entry : webResourceRequest.getRequestHeaders().entrySet()) {
                                if (!Statics.userAgent.equals("")) {
                                    if (!entry.getKey().equals("User-Agent")) {
                                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                                    } else {
                                        httpURLConnection.setRequestProperty("User-Agent", Statics.userAgent);
                                    }
                                } else {
                                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                                }
                            }
                            httpURLConnection.setReadTimeout(10000);
                            httpURLConnection.setConnectTimeout(10000);
                            httpURLConnection.connect();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            }
                            bufferedReader.close();
                            String stringBuffer2 = stringBuffer.toString();
                            if (stringBuffer2.startsWith("#EXTM3U")) {
                                String[] split = String.valueOf(webResourceRequest.getUrl()).split("/");
                                int length = split.length - 1;
                                int length2 = split.length;
                                int r6 = 0;
                                while (r2 < length2) {
                                    if (stringBuffer2.contains(split[r2])) {
                                        r6++;
                                    }
                                    r2++;
                                }
                                if (length <= r6) {
                                    Core.this.url = webResourceRequest.getUrl().toString();
                                    Core.this.headers.clear();
                                    Core.this.headers = webResourceRequest.getRequestHeaders();
                                    Core.statusType = StatusType.Idle;
                                }
                            }
                            System.out.println("Burası Muştur: " + webResourceRequest.getUrl() + " : " + httpURLConnection.getHeaderFields());
                        } catch (Exception unused) {
                        }
                    }
                } else {
                    System.out.println("Coming From Own: " + webResourceRequest.getUrl());
                    System.out.println("Coming From Own: " + webResourceRequest.getRequestHeaders());
                    if (Core.this.lookingFor.contains("|")) {
                        String[] split2 = Core.this.lookingFor.split("\\|");
                        int r1 = 0;
                        while (r2 < split2.length) {
                            String str = split2[r2];
                            if (webResourceRequest.getUrl().getPath().contains(str) || webResourceRequest.getUrl().getHost().contains(str)) {
                                r1 = 1;
                            }
                            r2++;
                        }
                        r2 = r1;
                    }
                    if (webResourceRequest.getUrl().getPath().contains(Core.this.lookingForEmbed) && !Core.this.lookingForEmbed.contains("http")) {
                        Core.this.lookingForEmbed = webResourceRequest.getUrl().toString();
                        if (Core.this.url.contains(Core.this.lookingFor)) {
                            Core.statusType = StatusType.Idle;
                        }
                        return super.shouldInterceptRequest(webView, webResourceRequest);
                    }
                    if (webResourceRequest.getUrl().getPath().contains(Core.this.lookingFor) || webResourceRequest.getUrl().getHost().contains(Core.this.lookingFor) || r2 != 0) {
                        if (Core.this.isDoubleClick && !Core.this.isDoubleClickComplete) {
                            return super.shouldInterceptRequest(webView, webResourceRequest);
                        }
                        if (Core.this.clickType == -2 && !Core.this.isJSDone) {
                            return super.shouldInterceptRequest(webView, webResourceRequest);
                        }
                        Core.this.url = webResourceRequest.getUrl().toString();
                        Core.this.headers.clear();
                        Core.this.headers = webResourceRequest.getRequestHeaders();
                        Core.statusType = StatusType.Idle;
                        return super.shouldInterceptRequest(webView, webResourceRequest);
                    }
                }
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Core.this.webView.getSettings().setJavaScriptEnabled(true);
            Core.this.webView.setWebViewClient(new AnonymousClass1());
            Core.this.setUserAgent();
            CookieManager.getInstance().setAcceptCookie(true);
            Core.this.webView.getSettings().setDomStorageEnabled(true);
            Core.this.webView.loadUrl(Core.this.url, Core.this.headers);
        }
    }

    @JavascriptInterface
    public void getWebViewOwnContentJS(String str, String str2, String str3, String str4, String str5, int r23, boolean z, String str6, int r26) {
        String str7;
        String str8;
        String str9;
        try {
            try {
                JSONObject jSONObject = new JSONObject(str6);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (jSONObject.get(next) instanceof String) {
                        this.headers.put(next, jSONObject.getString(next));
                    }
                }
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
        }
        String str10 = "";
        if ((!str5.equals("") && !this.s.equals("0")) || !this.e.equals("0")) {
            str10 = str5.replace("#e", this.e).replace("#s", this.s);
            this.isDoubleClick = true;
        }
        String str11 = "(function() { ";
        if (r23 == -1) {
            str8 = ("(function() { var link = document.getElementById('" + str4 + "');\n") + "link.click();\n";
            str9 = ("(function() { var link = document.getElementById('" + str10 + "');\n") + "link.click();\n";
        } else if (r23 >= 0) {
            str8 = ("(function() { var link = document.getElementsByClassName('" + str4 + "');\n") + "link[" + r23 + "].click();\n";
            str9 = ("(function() { var link = document.getElementsByClassName('" + str10 + "');\n") + "link[" + r23 + "].click();\n";
        } else {
            if (r23 == -2) {
                str11 = "(function() { buttons = document.getElementsByClassName(\"dropdown-menu dropdown-menu-left\")[0].getElementsByTagName(\"button\");\nvar found = -1;\nfor (var i = 0; i < buttons.length; i++) {\n   if (buttons[i].textContent == 'Season " + this.s + "') {\n    found = buttons[i].dataset.id;\n    break;\n  }\n}\n\nif(found != -1){\n    epButs = document.getElementById('season_' + found).getElementsByTagName(\"button\");\n    epButs[" + this.e + "].click();\n}";
                str7 = "(function() { ";
            } else {
                str7 = "(function() { ";
            }
            ((Activity) this.calledContext).runOnUiThread(new AnonymousClass13(str11 + "})();", str5, str7 + "})();", z, str2, str3, str, r23, r26, str4, str6));
        }
        str7 = str9;
        str11 = str8;
        ((Activity) this.calledContext).runOnUiThread(new AnonymousClass13(str11 + "})();", str5, str7 + "})();", z, str2, str3, str, r23, r26, str4, str6));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.swenauk.mainmenu.Sivvat.Core$13, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass13 implements Runnable {
        final /* synthetic */ boolean val$checkMedia;
        final /* synthetic */ String val$clickFinal;
        final /* synthetic */ int val$clickType;
        final /* synthetic */ String val$headersJson;
        final /* synthetic */ String val$lookingFor;
        final /* synthetic */ String val$lookingForEmbed;
        final /* synthetic */ String val$secondClickFinal;
        final /* synthetic */ String val$toClick;
        final /* synthetic */ String val$toClick2;
        final /* synthetic */ int val$type;
        final /* synthetic */ String val$url;

        AnonymousClass13(String str, String str2, String str3, boolean z, String str4, String str5, String str6, int r9, int r10, String str7, String str8) {
            this.val$clickFinal = str;
            this.val$toClick2 = str2;
            this.val$secondClickFinal = str3;
            this.val$checkMedia = z;
            this.val$lookingFor = str4;
            this.val$lookingForEmbed = str5;
            this.val$url = str6;
            this.val$clickType = r9;
            this.val$type = r10;
            this.val$toClick = str7;
            this.val$headersJson = str8;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.swenauk.mainmenu.Sivvat.Core$13$1, reason: invalid class name */
        /* loaded from: classes3.dex */
        public class AnonymousClass1 extends WebViewClient {
            AnonymousClass1() {
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(final WebView webView, String str) {
                new Handler().postDelayed(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.13.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((VideoView) Core.this.calledContext).isFinishing()) {
                            return;
                        }
                        webView.evaluateJavascript(AnonymousClass13.this.val$clickFinal, new ValueCallback<String>() { // from class: com.swenauk.mainmenu.Sivvat.Core.13.1.1.1
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str2) {
                                Core.this.isJSDone = true;
                            }
                        });
                    }
                }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                if (AnonymousClass13.this.val$toClick2.equals("")) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.13.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((VideoView) Core.this.calledContext).isFinishing()) {
                            return;
                        }
                        webView.evaluateJavascript(AnonymousClass13.this.val$secondClickFinal, new ValueCallback<String>() { // from class: com.swenauk.mainmenu.Sivvat.Core.13.1.2.1
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str2) {
                            }
                        });
                        Core.this.isDoubleClickComplete = true;
                    }
                }, 5000L);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                int r3 = 0;
                if (AnonymousClass13.this.val$checkMedia) {
                    if (webResourceRequest.getUrl().getHost().contains(AnonymousClass13.this.val$lookingFor)) {
                        try {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(String.valueOf(webResourceRequest.getUrl())).openConnection()));
                            for (Map.Entry<String, String> entry : webResourceRequest.getRequestHeaders().entrySet()) {
                                if (!Statics.userAgent.equals("")) {
                                    if (!entry.getKey().equals("User-Agent")) {
                                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                                    } else {
                                        httpURLConnection.setRequestProperty("User-Agent", Statics.userAgent);
                                    }
                                } else {
                                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                                }
                            }
                            httpURLConnection.setReadTimeout(10000);
                            httpURLConnection.setConnectTimeout(10000);
                            httpURLConnection.connect();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            }
                            bufferedReader.close();
                            String stringBuffer2 = stringBuffer.toString();
                            if (stringBuffer2.startsWith("#EXTM3U")) {
                                String[] split = String.valueOf(webResourceRequest.getUrl()).split("/");
                                int length = split.length - 1;
                                int length2 = split.length;
                                int r5 = 0;
                                while (r3 < length2) {
                                    if (stringBuffer2.contains(split[r3])) {
                                        r5++;
                                    }
                                    r3++;
                                }
                                if (length <= r5) {
                                    Core.this.url = webResourceRequest.getUrl().toString();
                                    Core.this.headers.clear();
                                    Core.this.headers = webResourceRequest.getRequestHeaders();
                                    Core.statusType = StatusType.Idle;
                                    Core.this.stepType = StepType.Play;
                                    Core.this.oldParserIntegration();
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                } else {
                    System.out.println("Coming From Own: " + webResourceRequest.getUrl());
                    System.out.println("Coming From Own: " + webResourceRequest.getRequestHeaders());
                    if (AnonymousClass13.this.val$lookingFor.contains("|")) {
                        String[] split2 = AnonymousClass13.this.val$lookingFor.split("\\|");
                        int r1 = 0;
                        while (r3 < split2.length) {
                            String str = split2[r3];
                            if (webResourceRequest.getUrl().getPath().contains(str) || webResourceRequest.getUrl().getHost().contains(str)) {
                                r1 = 1;
                            }
                            r3++;
                        }
                        r3 = r1;
                    }
                    if (webResourceRequest.getUrl().getPath().contains(AnonymousClass13.this.val$lookingForEmbed) && !AnonymousClass13.this.val$lookingForEmbed.contains("http")) {
                        Core.this.lookingForEmbed = webResourceRequest.getUrl().toString();
                        if (AnonymousClass13.this.val$url.contains(Core.this.lookingForEmbed)) {
                            Core.statusType = StatusType.Idle;
                        }
                        return super.shouldInterceptRequest(webView, webResourceRequest);
                    }
                    if (webResourceRequest.getUrl().getPath().contains(AnonymousClass13.this.val$lookingFor) || webResourceRequest.getUrl().getHost().contains(AnonymousClass13.this.val$lookingFor) || r3 != 0) {
                        if (!AnonymousClass13.this.val$toClick2.equals("") && !Core.this.isDoubleClickComplete) {
                            return super.shouldInterceptRequest(webView, webResourceRequest);
                        }
                        if (AnonymousClass13.this.val$clickType == -2 && !Core.this.isJSDone) {
                            return super.shouldInterceptRequest(webView, webResourceRequest);
                        }
                        Core.this.url = webResourceRequest.getUrl().toString();
                        Core.this.headers.clear();
                        Core.this.headers = webResourceRequest.getRequestHeaders();
                        Core.statusType = StatusType.Idle;
                        if (AnonymousClass13.this.val$type == 2) {
                            Core.this.url = Core.this.initialUrl + "#" + Core.this.url + "#" + Core.this.lookingForEmbed;
                            Core.this.onlineParserWebView();
                        } else if (AnonymousClass13.this.val$type == 1) {
                            Core.this.getWebViewOwnContentJS(Core.this.url, AnonymousClass13.this.val$lookingFor, AnonymousClass13.this.val$lookingForEmbed, AnonymousClass13.this.val$toClick, AnonymousClass13.this.val$toClick2, AnonymousClass13.this.val$clickType, true, "", 0);
                        } else if (AnonymousClass13.this.val$type == 0) {
                            Core.this.oldParserIntegration();
                        }
                        return super.shouldInterceptRequest(webView, webResourceRequest);
                    }
                }
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Core.this.webView.getSettings().setJavaScriptEnabled(true);
            Core.this.webView.setWebViewClient(new AnonymousClass1());
            Core.this.setUserAgent();
            if (this.val$headersJson.contains("User-Agent")) {
                try {
                    Core.this.webView.getSettings().setUserAgentString(new JSONObject(this.val$headersJson).getString("User-Agent"));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            CookieManager.getInstance().setAcceptCookie(true);
            Core.this.webView.getSettings().setDomStorageEnabled(true);
            Core.this.webView.loadUrl(this.val$url, Core.this.headers);
        }
    }

    public void getGetRequestContent(String str, boolean z) {
        try {
            URL url = new URL(this.url);
            if (z) {
                url = new URL(str);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                if (!Statics.userAgent.equals("")) {
                    if (!entry.getKey().equals("User-Agent")) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    } else if (!this.url.contains("gdplayer")) {
                        httpURLConnection.setRequestProperty("User-Agent", Statics.userAgent);
                    } else {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                } else {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (Statics.cookieManager.getCookie(this.root) != null && !this.headers.containsKey("Cookie")) {
                String cookie = Statics.cookieManager.getCookie(this.root);
                if (this.root.contains("dizilab")) {
                    cookie = cookie + " __cf_bm";
                }
                httpURLConnection.setRequestProperty("Cookie", cookie);
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else {
                    stringBuffer.append(readLine);
                }
            }
            bufferedReader.close();
            String stringBuffer2 = stringBuffer.toString();
            if (z) {
                return;
            }
            this.pageContent = stringBuffer2;
        } catch (Exception e) {
            this.pageContent = e.getMessage();
        }
    }

    public String getReturnGetRequestContent(String str, boolean z) {
        try {
            URL url = new URL(this.url);
            if (z) {
                url = new URL(str);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                if (!Statics.userAgent.equals("")) {
                    if (!entry.getKey().equals("User-Agent")) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    } else {
                        httpURLConnection.setRequestProperty("User-Agent", Statics.userAgent);
                    }
                } else {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            bufferedReader.close();
            String stringBuffer2 = stringBuffer.toString();
            if (!z) {
                this.pageContent = stringBuffer2;
            }
            return stringBuffer2;
        } catch (Exception e) {
            this.pageContent = e.getMessage();
            return "";
        }
    }

    public void getPostRequestContent() {
        try {
            byte[] bytes = this.postData.getBytes(StandardCharsets.UTF_8);
            Log.d("Core: ", "postData: " + this.postData + "\n url:" + this.url);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(this.url).openConnection()));
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String str = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        this.pageContent = str;
                        return;
                    }
                    str = str + readLine;
                }
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.pageContent = e.getMessage();
        }
    }

    protected void playVideoSub() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Core.this.player.prepare(Core.this.mergedSource, false, false);
                } catch (Exception unused) {
                    Core.this.player.prepare(Core.this.mediaSource, false, false);
                }
                if (Core.this.calledContext instanceof VideoView) {
                    ((VideoView) Core.this.calledContext).setVideoUri(Core.this.videoUri);
                    try {
                        if (Core.this.isFragman.booleanValue()) {
                            Core.this.player.setPlayWhenReady(true);
                        } else {
                            final long parseInt = Integer.parseInt(((VideoView) Core.this.calledContext).mili);
                            if (parseInt <= 0) {
                                Core.this.player.setPlayWhenReady(true);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Core.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Sivvat.Core.14.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Core.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Sivvat.Core.14.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Core.this.player.seekTo(parseInt);
                                        Core.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            }
                        }
                    } catch (Exception unused2) {
                        Core.this.player.setPlayWhenReady(true);
                    }
                }
                Core.this.player.setPlayWhenReady(true);
            }
        });
    }

    public void trSubLogin() {
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.15
            @Override // java.lang.Runnable
            public void run() {
                Core core = Core.this;
                core.cookie = Helper.getPostCookieContent(core.headers, "username=malsiniz&password=1234malsiniz&autologin=on&redirect=&login=Giriş", "https://turkcealtyazi.org/login.php");
            }
        }).start();
    }

    public void loadSubtitlesOnline() {
        int size;
        List<String> subList;
        statusType = StatusType.Working;
        if (this.calledContext instanceof VideoView) {
            String str = ((VideoView) this.calledContext).imdb;
            final HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", URLEncodedUtils.CONTENT_TYPE);
            final String str2 = Helper.pregMatchAll("sid=(.*?);", this.cookie).get(0);
            hashMap.put("Cookie", this.cookie);
            this.headers.put("Cookie", this.cookie);
            String str3 = Helper.pregMatchAll("id=\"altyazilar\">(.*?)<div class=\"altyazi-list-info\"", getReturnGetRequestContent("https://www.turkcealtyazi.org/mov/" + str + "/guardians-of-the-galaxy-vol-3.html", true)).get(0);
            LinkedList linkedList = new LinkedList();
            if (!this.isTv) {
                size = Helper.wordCountInString("flagtr", str3);
                subList = Helper.pregMatchAll("<a.*?href=\"(\\/sub.*?)\".*?>", str3).subList(0, Math.min(size, 3));
            } else {
                List asList = Arrays.asList(str3.split("<a itemprop=\"url\""));
                for (int r4 = 0; r4 < asList.size(); r4++) {
                    if (Helper.containsAny((String) asList.get(r4), "S<b>0" + this.s, "S<b>" + this.s)) {
                        if (Helper.containsAny((String) asList.get(r4), "E<b>0" + this.e, "E<b>" + this.e, "<b>Paket") && ((String) asList.get(r4)).contains("flagtr")) {
                            try {
                                linkedList.add(Helper.pregMatchAll("href=\"(\\/sub.*?)\".*?>", (String) asList.get(r4)).get(0));
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                size = linkedList.size();
                subList = linkedList.subList(0, Math.min(size, 3));
            }
            final int r11 = size;
            final LinkedList linkedList2 = new LinkedList(subList);
            for (int r13 = 0; r13 < Math.min(r11, 3); r13++) {
                final int r42 = r13;
                new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.16
                    @Override // java.lang.Runnable
                    public void run() {
                        String returnGetRequestContent = Core.this.getReturnGetRequestContent("https://www.turkcealtyazi.org" + ((String) linkedList2.get(r42)), true);
                        String str4 = Helper.pregMatchAll("<input type=\"hidden\" name=\"idid\" value=\"(.*?)\" />", returnGetRequestContent).get(0);
                        String str5 = Helper.pregMatchAll("<input type=\"hidden\" name=\"altid\" value=\"(.*?)\" />", returnGetRequestContent).get(0);
                        String str6 = Helper.pregMatchAll("<input type=\"hidden\" name=\"sidid\" value=\"(.*?)\" />", returnGetRequestContent).get(0);
                        String postRequestSubtitleContent = Helper.getPostRequestSubtitleContent(hashMap, "idid=" + str4 + "&altid=" + str5 + "&sidid=" + str6 + "", "https://www.turkcealtyazi.org/ind", Environment.getExternalStorageDirectory() + "/Download/seyirTURK/", Core.this.e);
                        Core.this.subtitles.put("tr-" + (r42 + 1), postRequestSubtitleContent);
                        Core core = Core.this;
                        core.completeCount = core.completeCount + 1;
                        if (Core.this.completeCount == Math.min(r11, 3)) {
                            Core.statusType = StatusType.Idle;
                            Helper.getGetRequestContent(hashMap, "https://turkcealtyazi.org/login.php?logout=true&sid=" + str2);
                        }
                    }
                }).start();
            }
            if (subList.size() == 0) {
                statusType = StatusType.Idle;
            }
        }
    }

    public void translateSubFromUrl(final String str, final String str2) {
        statusType = StatusType.Working;
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Core.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String getRequestContent = Helper.getGetRequestContent(Core.this.headers, Statics.SERVER + "sey/translate/CeviriAI.TR.php?url=" + URLEncoder.encode(str, "UTF-8") + "&id=" + str2);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/Download/seyirTURK/translated.vtt"));
                        fileOutputStream.write(getRequestContent.getBytes());
                        fileOutputStream.close();
                        Core.this.subtitles.put("AI Çeviri(TR)", Environment.getExternalStorageDirectory() + "/Download/seyirTURK/translated.vtt");
                    } catch (IOException e) {
                        Log.e("Exception", "File write failed: " + e.toString());
                    }
                } catch (Exception e2) {
                    System.out.println(e2);
                }
                Core.statusType = StatusType.Idle;
            }
        }).start();
    }

    public String fixUrl(String str) {
        String str2;
        if (str.startsWith("http")) {
            return str;
        }
        if (str.startsWith(":")) {
            str2 = "https";
        } else {
            str2 = "https:";
            if (!str.startsWith("//")) {
                str2 = "https://";
            }
        }
        return str2 + str;
    }

    public String getRedirectUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
            httpURLConnection.setRequestProperty("User-Agent", Statics.userAgent);
            httpURLConnection.setRequestProperty("Referer", this.root);
            httpURLConnection.setRequestProperty("Cookie", Statics.cookieManager.getCookie(this.root));
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.connect();
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }
}
