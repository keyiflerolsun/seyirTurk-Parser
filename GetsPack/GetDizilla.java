package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.swenauk.mainmenu.Parsers.Dizilla;
import com.swenauk.mainmenu.VideoView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetDizilla extends AsyncTask<String, String, String> {
    Dizilla afaki;
    String res;
    String ref = "";
    String line = "";
    String comingUrl = "";
    boolean isThreadRunning = false;
    Map<String, String> alternates = new HashMap();

    public GetDizilla(Dizilla dizilla) {
        this.afaki = dizilla;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(final String... strArr) {
        try {
            this.comingUrl = strArr[0];
            this.res = getUrlContent(strArr[0]);
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetDizilla.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        new URL(strArr[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!GetDizilla.this.afaki.isAlt) {
                        Matcher matcher = Pattern.compile("<iframe\\s*src=\"(.*?)\"", 32).matcher(GetDizilla.this.res);
                        if (matcher.find()) {
                            GetDizilla.this.res = matcher.group(1);
                            if (matcher.find()) {
                                String group = matcher.group(1);
                                if (group.startsWith("//")) {
                                    group = "https:" + group;
                                }
                                GetDizilla getDizilla = GetDizilla.this;
                                getDizilla.res = getDizilla.getUrlContent(group);
                                GetDizilla.this.ref = group;
                            }
                        } else {
                            matcher = Pattern.compile("<iframe.*?src=\"(.*?)\".*?</iframe>", 32).matcher(GetDizilla.this.res);
                            if (matcher.find()) {
                                GetDizilla getDizilla2 = GetDizilla.this;
                                getDizilla2.res = getDizilla2.getUrlContent("https:" + matcher.group(1));
                                GetDizilla.this.ref = "https:" + matcher.group(1);
                            }
                        }
                        try {
                            if (!GetDizilla.this.ref.contains("contentx") && !GetDizilla.this.ref.contains("playru")) {
                                if (GetDizilla.this.ref.contains("filese")) {
                                    Matcher matcher2 = Pattern.compile("getJSON\\('(.*?)'", 32).matcher(GetDizilla.this.res);
                                    if (matcher2.find()) {
                                        matcher2.group(1).replace("'", "");
                                        GetDizilla getDizilla3 = GetDizilla.this;
                                        getDizilla3.res = getDizilla3.getUrlContent("https://filese.me" + matcher2.group(1).replace("'", ""));
                                        GetDizilla.this.afaki.parsed = GetDizilla.this.res;
                                    }
                                } else if (matcher.group(1).contains("vmplayer")) {
                                    GetDizilla.this.afaki.parsed = "https://vidmoly.to/embed-" + GetDizilla.this.comingUrl.split("\\?vid=")[1] + ".html";
                                } else {
                                    GetDizilla.this.afaki.parsed = GetDizilla.this.ref;
                                }
                            }
                            Matcher matcher3 = Pattern.compile("window.openPlayer\\((.*?)\\)", 32).matcher(GetDizilla.this.res);
                            if (matcher3.find()) {
                                String host = new URL(strArr[0]).getHost();
                                String replace = matcher3.group(1).split(",")[0].replace("'", "");
                                GetDizilla getDizilla4 = GetDizilla.this;
                                getDizilla4.res = getDizilla4.getUrlContent("https://" + host + "/source.php?v=" + replace);
                                GetDizilla.this.afaki.parsed = new JSONObject(GetDizilla.this.res).getJSONObject("playlist").getJSONArray("sources").getJSONObject(0).getString("file");
                            }
                        } catch (Exception unused) {
                        }
                    }
                    GetDizilla.this.isThreadRunning = false;
                }
            }).start();
            this.isThreadRunning = true;
            while (this.isThreadRunning) {
                Thread.sleep(100L);
                if (VideoView.isDestroyed) {
                    break;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUrlContent(final String str) {
        try {
            this.line = "";
            Thread thread = new Thread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetDizilla.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        URL url = new URL(str);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                        httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + url.getHost());
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setConnectTimeout(5000);
                        httpURLConnection.connect();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                stringBuffer.append(readLine);
                            } else {
                                bufferedReader.close();
                                GetDizilla.this.line = stringBuffer.toString();
                                return;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            thread.start();
            for (int r5 = 0; this.line.equals("") && r5 < 100; r5++) {
                Thread.sleep(100L);
                if (VideoView.isDestroyed) {
                    return "";
                }
            }
            if (thread.isAlive()) {
                thread.interrupt();
            }
            return this.line;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetDizilla) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }
}
