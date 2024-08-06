package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.gargoylesoftware.htmlunit.util.MimeType;
import com.swenauk.mainmenu.Parsers.OkRu;
import com.swenauk.mainmenu.Sivvat.Helper;
import com.swenauk.mainmenu.VideoView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.eclipse.jetty.util.StringUtil;

/* loaded from: classes3.dex */
public class GetOkRu extends AsyncTask<String, String, String> {
    OkRu afaki;
    String line;
    String res;

    public GetOkRu(OkRu okRu) {
        this.afaki = okRu;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            String urlContentPost = getUrlContentPost("http://www.ok.ru/dk", Helper.pregMatchAll("https?://(?:www.)?(?:odnoklassniki|ok).ru/(?:videoembed/|dk\\?cmd=videoPlayerMetadata&mid=)(\\d+)", "https:" + strArr[0].replace("https:", "").replace("http:", "")).get(0));
            this.res = urlContentPost;
            this.afaki.parsed = urlContentPost;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            CookieHandler.setDefault(new CookieManager());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", MimeType.TEXT_HTML);
            httpURLConnection.setRequestProperty("charset", StringUtil.__UTF8);
            httpURLConnection.setRequestProperty("Referer", "https://filmmodu.org");
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
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String getUrlContentPost(final String str, final String str2) {
        try {
            this.line = "";
            Thread thread = new Thread(new Runnable() { // from class: com.swenauk.mainmenu.GetsPack.GetOkRu.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        byte[] bytes = ("cmd=videoPlayerMetadata&mid=" + str2).getBytes(StandardCharsets.UTF_8);
                        int length = bytes.length;
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            dataOutputStream.write(bytes);
                            dataOutputStream.close();
                            GetOkRu.this.line = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
                        } finally {
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
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetOkRu) str);
        this.afaki.resumeParse();
    }
}
