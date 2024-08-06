package com.swenauk.mainmenu.GetsPack;

import android.os.AsyncTask;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.swenauk.mainmenu.Parsers.SezonlukDizi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GetSezonlukDizi extends AsyncTask<String, String, String> {
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    SezonlukDizi afaki;
    Map<String, String> alternates = new HashMap();
    String res;

    public GetSezonlukDizi(SezonlukDizi sezonlukDizi) {
        this.afaki = sezonlukDizi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) {
        try {
            URL url = new URL(this.afaki.startUrl);
            if (this.afaki.isAlt) {
                this.res = getUrlContent(strArr[0].replace("?l=1", "").replace("?l=0", ""));
                boolean contains = strArr[0].contains("?l=0");
                Matcher matcher = Pattern.compile("<div\\s*bid=\"(\\d*)\"\\s*did=", 32).matcher(this.res);
                if (!matcher.find()) {
                    return null;
                }
                String urlContentPost = getUrlContentPost("https://" + url.getHost() + "/ajax/dataAlternatif2.asp", "bid=" + matcher.group(1) + "&dil=" + (contains ? 1 : 0));
                this.res = urlContentPost;
                if (urlContentPost.contains("eklenmedi")) {
                    return null;
                }
                System.out.println(this.res);
                JSONObject jSONObject = new JSONObject(this.res);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int r3 = 0; r3 < jSONObject.getJSONArray("data").length(); r3++) {
                    if (!jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik").toLowerCase().contains("netu") && !jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik").toLowerCase().contains("upto") && !jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik").toLowerCase().equals("multi") && !jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik").toLowerCase().equals("upstream") && !jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik").toLowerCase().equals("videoseyred")) {
                        arrayList.add(String.valueOf(jSONObject.getJSONArray("data").getJSONObject(r3).getInt(TtmlNode.ATTR_ID)));
                        arrayList2.add(jSONObject.getJSONArray("data").getJSONObject(r3).getString("baslik"));
                    }
                }
                if (arrayList.size() == arrayList2.size()) {
                    for (int r12 = 0; r12 < arrayList.size(); r12++) {
                        this.alternates.put(arrayList2.get(r12), arrayList.get(r12));
                    }
                }
                if (arrayList.size() != 0) {
                    return null;
                }
                this.alternates.put("Dizigom", matcher.group(1));
                return null;
            }
            this.res = getUrlContentPost("https://" + url.getHost() + "/ajax/dataEmbed.asp", "id=" + strArr[0]);
            Matcher matcher2 = Pattern.compile("(?:SRC|src)=\"(.*?)\"").matcher(this.res);
            if (!matcher2.find()) {
                return null;
            }
            this.afaki.mainUrl = matcher2.group(1);
            if (this.afaki.mainUrl.startsWith("//")) {
                this.afaki.mainUrl = "https:" + this.afaki.mainUrl;
            }
            if (this.afaki.mainUrl.contains("/player/fembed.asp")) {
                String[] split = this.afaki.mainUrl.split("\\?v=");
                this.afaki.mainUrl = "https://dutrag.com/v/" + split[1];
                return null;
            }
            if (!this.afaki.mainUrl.contains("sbembed")) {
                return null;
            }
            URL url2 = new URL(this.afaki.mainUrl);
            this.res = getUrlContent("https://" + url2.getHost() + "/sourcesx38/" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + this.afaki.mainUrl.split("/")[4].replace(".html", "") + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8") + "/" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + URLDecoder.decode(bytesToHex((makeID(12) + "||" + makeID(12) + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8") + "||" + makeID(12) + "||streamsb").getBytes(StandardCharsets.UTF_8)), "UTF-8"));
            JSONObject jSONObject2 = new JSONObject(this.res);
            try {
                try {
                    this.afaki.streamUrl = jSONObject2.getJSONObject("stream_data").getString("file");
                    return null;
                } catch (Exception unused) {
                    this.afaki.startUrl = "";
                    return null;
                }
            } catch (Exception unused2) {
                this.afaki.streamUrl = jSONObject2.getJSONObject("stream_data").getString("backup");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String makeID(int r6) {
        Random random = new Random();
        String str = "";
        for (int r2 = 0; r2 < r6; r2++) {
            str = str + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62));
        }
        return str;
    }

    public static String bytesToHex(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            int r2 = bArr[r1] & 255;
            int r3 = r1 * 2;
            byte[] bArr3 = HEX_ARRAY;
            bArr2[r3] = bArr3[r2 >>> 4];
            bArr2[r3 + 1] = bArr3[r2 & 15];
        }
        return new String(bArr2, StandardCharsets.UTF_8);
    }

    private String getUrlContent(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            System.out.println(url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            if (str.contains("sbembed")) {
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
                httpURLConnection.setRequestProperty("Referer", "https://sbembed2.com");
                httpURLConnection.setRequestProperty("watchsb", "streamsb");
            } else {
                httpURLConnection.setRequestProperty("Referer", url.getProtocol() + "://" + getBaseDomain(url.getHost()));
            }
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

    private String getUrlContentPost(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla");
            httpURLConnection.setRequestProperty(HttpHeaders.X_REQUESTED_WITH, "XMLHttpRequest");
            httpURLConnection.setRequestProperty("Referer", str);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                return new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
            } finally {
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        super.onPostExecute((GetSezonlukDizi) str);
        if (this.afaki.isAlt) {
            this.afaki.showAlternates(this.alternates);
        } else {
            this.afaki.resumeParse();
        }
    }

    public static String getBaseDomain(String str) {
        int indexOf = str.indexOf(46);
        int lastIndexOf = str.lastIndexOf(46);
        int r3 = 0;
        while (indexOf < lastIndexOf) {
            r3 = indexOf + 1;
            indexOf = str.indexOf(46, r3);
        }
        return r3 > 0 ? str.substring(r3) : str;
    }
}
