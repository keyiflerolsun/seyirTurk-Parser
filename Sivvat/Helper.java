package com.swenauk.mainmenu.Sivvat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.UserDictionary;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import com.swenauk.mainmenu.Statics;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.sourceforge.htmlunit.xpath.compiler.Keywords;
import org.apache.commons.text.StringEscapeUtils;
import org.htmlunit.org.apache.http.client.utils.URLEncodedUtils;

/* loaded from: classes3.dex */
public class Helper {
    public static LinkedList<String> pregMatchAll(String str, String str2) {
        LinkedList<String> linkedList = new LinkedList<>();
        if (str2 != null) {
            Matcher matcher = Pattern.compile(str, 32).matcher(str2);
            while (matcher.find()) {
                linkedList.add(matcher.group(1));
            }
        }
        if (linkedList.size() == 0) {
            linkedList.add("");
        }
        return linkedList;
    }

    public static LinkedHashMap<String, String> pregMatchAll(String str, String str2, boolean z, boolean z2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        Pattern compile = Pattern.compile(str, 32);
        if (!z2) {
            compile = Pattern.compile(str);
        }
        Matcher matcher = compile.matcher(str2);
        int r8 = 0;
        while (matcher.find()) {
            String str3 = "m3u8";
            if (z) {
                String group = matcher.group(1);
                if (group != null && !group.equals("null")) {
                    str3 = group;
                }
                String replace = str3.replace("&nbsp;", "").replace("&nbsp", "");
                if (linkedHashMap.containsKey(replace)) {
                    linkedHashMap.put(replace + "" + r8, matcher.group(2));
                    r8++;
                } else {
                    linkedHashMap.put(replace, matcher.group(2));
                }
            } else {
                String group2 = matcher.group(2);
                if (group2 != null && !group2.equals("null")) {
                    str3 = group2;
                }
                String replace2 = str3.replace("&nbsp;", "").replace("&nbsp", "");
                if (linkedHashMap.containsKey(replace2)) {
                    linkedHashMap.put(replace2 + "" + r8, matcher.group(1));
                    r8++;
                } else {
                    linkedHashMap.put(replace2, matcher.group(1));
                }
            }
        }
        if (linkedHashMap.size() == 0) {
            linkedHashMap.put("", "");
        }
        return linkedHashMap;
    }

    public static LinkedHashMap<String, String> pregMatchFilterList(String str, String str2, boolean z, boolean z2, List<String> list) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        Pattern compile = Pattern.compile(str, 32);
        if (!z2) {
            compile = Pattern.compile(str);
        }
        Matcher matcher = compile.matcher(str2);
        while (matcher.find()) {
            String str3 = "m3u8";
            if (z) {
                String group = matcher.group(1);
                if (group != null && !group.equals("null")) {
                    str3 = group;
                }
                String lowerCase = str3.toLowerCase(Locale.ROOT);
                if (list.contains(lowerCase)) {
                    linkedHashMap.put(lowerCase, matcher.group(2));
                }
            } else {
                String group2 = matcher.group(2);
                if (group2 != null && !group2.equals("null")) {
                    str3 = group2;
                }
                String lowerCase2 = str3.toLowerCase(Locale.ROOT);
                if (list.contains(lowerCase2)) {
                    linkedHashMap.put(lowerCase2, matcher.group(1));
                }
            }
        }
        if (linkedHashMap.size() == 0) {
            linkedHashMap.put("", "");
        }
        return linkedHashMap;
    }

    public static LinkedHashMap<String, String> pregMatchFilterListWithLang(String str, String str2, boolean z, boolean z2, List<String> list, String str3) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        Pattern compile = Pattern.compile(str, 32);
        if (!z2) {
            compile = Pattern.compile(str);
        }
        Matcher matcher = compile.matcher(str2);
        while (matcher.find()) {
            String str4 = "m3u8";
            if (z) {
                String group = matcher.group(1);
                String group2 = matcher.group(3);
                if (group != null && !group.equals("null")) {
                    str4 = group;
                }
                String lowerCase = str4.toLowerCase(Locale.ROOT);
                if (list.contains(lowerCase) && group2.contains(str3)) {
                    linkedHashMap.put(lowerCase, matcher.group(2));
                }
            } else {
                String group3 = matcher.group(2);
                String group4 = matcher.group(3);
                if (group3 != null && !group3.equals("null")) {
                    str4 = group3;
                }
                String lowerCase2 = str4.toLowerCase(Locale.ROOT);
                if (list.contains(lowerCase2) && group4.contains(str3)) {
                    linkedHashMap.put(lowerCase2, matcher.group(1));
                }
            }
        }
        if (linkedHashMap.size() == 0) {
            linkedHashMap.put("", "");
        }
        return linkedHashMap;
    }

    public static String pregMatchFilter(String str, String str2, String str3, boolean z) {
        LinkedList<String> pregMatchAll = pregMatchAll(str, str2);
        for (int r2 = 0; r2 < pregMatchAll.size(); r2++) {
            if (z && !pregMatchAll.get(r2).contains(str3)) {
                return pregMatchAll.get(r2);
            }
            if (!z && pregMatchAll.get(r2).contains(str3)) {
                return pregMatchAll.get(r2);
            }
        }
        return "";
    }

    public static String decodeBase64(String str) {
        try {
            return new String(Base64.decode(str, 0));
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean containsAll(String... strArr) {
        for (int r1 = 1; r1 < strArr.length; r1++) {
            if (!strArr[0].contains(strArr[r1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAny(String... strArr) {
        for (int r1 = 1; r1 < strArr.length; r1++) {
            if (strArr[0].contains(strArr[r1])) {
                return true;
            }
        }
        return false;
    }

    public static String decodeUnicode(String str) {
        return Build.VERSION.SDK_INT >= 24 ? StringEscapeUtils.unescapeJava(str) : str;
    }

    public static char selectAChar(String str) {
        return str.charAt(new Random().nextInt(str.length()));
    }

    public static int wordCountInString(String str, String str2) {
        Matcher matcher = Pattern.compile(str).matcher(str2);
        int r2 = 0;
        while (matcher.find()) {
            r2++;
        }
        return r2;
    }

    public static void unzip(File file, File file2) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        File file3 = new File(file2, nextEntry.getName());
                        if (!nextEntry.isDirectory()) {
                            file3.getParentFile();
                        }
                        if (!nextEntry.isDirectory()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file3);
                            while (true) {
                                try {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    } else {
                                        fileOutputStream.write(bArr, 0, read);
                                    }
                                } catch (Throwable th) {
                                    fileOutputStream.close();
                                    throw th;
                                }
                            }
                            fileOutputStream.close();
                        }
                    } else {
                        zipInputStream.close();
                        return;
                    }
                }
            } catch (Throwable th2) {
                zipInputStream.close();
                throw th2;
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00da, code lost:
    
        r12 = r3.getName();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String unzipWZIS(java.util.zip.ZipInputStream r11, java.io.File r12, java.lang.String r13) {
        /*
            java.lang.String r0 = ""
            r1 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r1]     // Catch: java.lang.Throwable -> Lee
            boolean r3 = r12.exists()     // Catch: java.lang.Throwable -> Lee
            if (r3 == 0) goto L12
            boolean r3 = r12.isDirectory()     // Catch: java.lang.Throwable -> Lee
            if (r3 != 0) goto L15
        L12:
            r12.mkdir()     // Catch: java.lang.Throwable -> Lee
        L15:
            java.util.zip.ZipEntry r3 = r11.getNextEntry()     // Catch: java.lang.Throwable -> Lee
            if (r3 == 0) goto Le9
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> Lee
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            r4.<init>(r12, r5)     // Catch: java.lang.Throwable -> Lee
            org.mozilla.universalchardet.UniversalDetector r5 = new org.mozilla.universalchardet.UniversalDetector     // Catch: java.lang.Throwable -> Lee
            r6 = 0
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Lee
            boolean r6 = r3.isDirectory()     // Catch: java.lang.Throwable -> Lee
            if (r6 == 0) goto L31
            goto L15
        L31:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> Lee
            r6.<init>(r4)     // Catch: java.lang.Throwable -> Lee
        L36:
            r7 = 0
            int r8 = r11.read(r2)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r9 = -1
            if (r8 == r9) goto L7b
            r5.handleData(r2, r7, r1)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r9 = r5.getDetectedCharset()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            if (r9 == 0) goto L6a
            java.lang.String r9 = r5.getDetectedCharset()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r9 = r9.toLowerCase()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r10 = "windows"
            boolean r9 = r9.contains(r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            if (r9 != 0) goto L6a
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r10 = r5.getDetectedCharset()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r9.<init>(r2, r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.nio.charset.Charset r10 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            byte[] r9 = r9.getBytes(r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r6.write(r9, r7, r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            goto L36
        L6a:
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r10 = "Windows-1254"
            r9.<init>(r2, r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.nio.charset.Charset r10 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            byte[] r9 = r9.getBytes(r10)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            r6.write(r9, r7, r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            goto L36
        L7b:
            r5.dataEnd()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8a
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            com.swenauk.mainmenu.Statics.downloadedSubtitle = r5     // Catch: java.lang.Throwable -> Lee
        L84:
            r6.close()     // Catch: java.lang.Throwable -> Lee
            goto L97
        L88:
            r12 = move-exception
            goto Ldf
        L8a:
            r5 = move-exception
            java.io.PrintStream r8 = java.lang.System.out     // Catch: java.lang.Throwable -> L88
            r8.println(r5)     // Catch: java.lang.Throwable -> L88
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            com.swenauk.mainmenu.Statics.downloadedSubtitle = r5     // Catch: java.lang.Throwable -> Lee
            goto L84
        L97:
            r5 = 3
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> Lee
            java.lang.String r6 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            r5[r7] = r6     // Catch: java.lang.Throwable -> Lee
            r6 = 1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lee
            r7.<init>()     // Catch: java.lang.Throwable -> Lee
            java.lang.String r8 = "E0"
            r7.append(r8)     // Catch: java.lang.Throwable -> Lee
            r7.append(r13)     // Catch: java.lang.Throwable -> Lee
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lee
            r5[r6] = r7     // Catch: java.lang.Throwable -> Lee
            r6 = 2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lee
            r7.<init>()     // Catch: java.lang.Throwable -> Lee
            java.lang.String r8 = "E"
            r7.append(r8)     // Catch: java.lang.Throwable -> Lee
            r7.append(r13)     // Catch: java.lang.Throwable -> Lee
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> Lee
            r5[r6] = r7     // Catch: java.lang.Throwable -> Lee
            boolean r5 = containsAny(r5)     // Catch: java.lang.Throwable -> Lee
            if (r5 != 0) goto Lda
            boolean r5 = r13.isEmpty()     // Catch: java.lang.Throwable -> Lee
            if (r5 == 0) goto Ld5
            goto Lda
        Ld5:
            r4.delete()     // Catch: java.lang.Throwable -> Lee
            goto L15
        Lda:
            java.lang.String r12 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            goto Lea
        Ldf:
            java.lang.String r13 = r3.getName()     // Catch: java.lang.Throwable -> Lee
            com.swenauk.mainmenu.Statics.downloadedSubtitle = r13     // Catch: java.lang.Throwable -> Lee
            r6.close()     // Catch: java.lang.Throwable -> Lee
            throw r12     // Catch: java.lang.Throwable -> Lee
        Le9:
            r12 = r0
        Lea:
            r11.close()     // Catch: java.lang.Exception -> Lf3
            return r12
        Lee:
            r12 = move-exception
            r11.close()     // Catch: java.lang.Exception -> Lf3
            throw r12     // Catch: java.lang.Exception -> Lf3
        Lf3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.Sivvat.Helper.unzipWZIS(java.util.zip.ZipInputStream, java.io.File, java.lang.String):java.lang.String");
    }

    public static String getPostRequestSubtitleContent(Map<String, String> map, String str, String str2, String str3, String str4) {
        try {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            Log.d("Core: ", "postData: " + str + "\n url:" + str2);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                return str3 + unzipWZIS(new ZipInputStream(httpURLConnection.getInputStream()), new File(str3), str4);
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getPostCookieContent(Map<String, String> map, String str, String str2) {
        try {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            Log.d("Core: ", "postData: " + str + "\n url:" + str2);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                httpURLConnection.getInputStream();
                return httpURLConnection.getHeaderField("Set-Cookie");
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getRedirectUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36.");
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.connect();
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getGetRequestContent(Map<String, String> map, String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (!str.contains("webteizle")) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setInstanceFollowRedirects(false);
            }
            httpURLConnection.setRequestMethod("GET");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                    if (str.contains(Keywords.FUNC_TRANSLATE_STRING)) {
                        stringBuffer.append("\n");
                    }
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getUrlContentPost(Map<String, String> map, String str, String str2) {
        try {
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(length));
            if (str.contains(Statics.SERVER)) {
                httpURLConnection.setRequestProperty("Content-Type", URLEncodedUtils.CONTENT_TYPE);
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            try {
                dataOutputStream.write(bytes);
                dataOutputStream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String str3 = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return str3;
                    }
                    str3 = str3 + readLine;
                }
            } finally {
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean shouldShowMovie(boolean z, boolean z2, boolean z3, boolean z4, Context context) {
        boolean z5 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("prefName", 0);
        boolean z6 = sharedPreferences.getBoolean("showAnime", true);
        boolean z7 = sharedPreferences.getBoolean("showKorean", true);
        if (!z3 && !z4) {
            return true;
        }
        if (z && (z6 || !z4)) {
            z5 = true;
        }
        if (z2 && (z7 || !z3)) {
            z5 = true;
        }
        if (!z2 && !z) {
            if (z4 && z6 && (z7 || !z3)) {
                z5 = true;
            }
            if (z3 && z7 && (z6 || !z4)) {
                return true;
            }
        }
        return z5;
    }

    public static void addToSearchHistory(Context context) {
        try {
            UserDictionary.Words.addWord(context, "MadeUpWord", 10, "Mad", Locale.getDefault());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int dpToPx(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static String redirectedUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection.getHeaderField("Location");
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isForeign(String str) {
        return str.toLowerCase().contains("xcine") || str.toLowerCase().contains("aniworld") || str.toLowerCase().contains("themoviearchive") || str.toLowerCase().contains("hdtoday") || str.toLowerCase().contains("123movies") || str.toLowerCase().contains("watch-free") || str.equalsIgnoreCase("sto") || str.toLowerCase().contains("cinemathek") || str.toLowerCase().contains("movie4k");
    }

    public static void uploadFile(final String str) {
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.Sivvat.Helper.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    File file = new File(str);
                    if (!file.isFile()) {
                        return;
                    }
                    try {
                        String str2 = Statics.SERVER + "sey/translate/upload.php?";
                        FileInputStream fileInputStream = new FileInputStream(file);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                        httpURLConnection.setRequestProperty("ENCTYPE", "multipart/form-data");
                        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
                        httpURLConnection.setRequestProperty("uploaded_file", "test.srt");
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.writeBytes("--*****\r\n");
                        StringBuilder sb = new StringBuilder();
                        sb.append("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"test.srt\"");
                        sb.append("\r\n");
                        dataOutputStream.writeBytes(sb.toString());
                        dataOutputStream.writeBytes("\r\n");
                        int min = Math.min(fileInputStream.available(), 1048576);
                        byte[] bArr = new byte[min];
                        int read = fileInputStream.read(bArr, 0, min);
                        while (read > 0) {
                            dataOutputStream.write(bArr, 0, min);
                            min = Math.min(fileInputStream.available(), 1048576);
                            read = fileInputStream.read(bArr, 0, min);
                        }
                        dataOutputStream.writeBytes("\r\n");
                        dataOutputStream.writeBytes("--*****--\r\n");
                        int responseCode = httpURLConnection.getResponseCode();
                        Log.i("uploadFile", "HTTP Response is : " + httpURLConnection.getResponseMessage() + ": " + responseCode);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                stringBuffer.append(readLine);
                            } else {
                                bufferedReader.close();
                                Log.i("uploadFile", stringBuffer.toString());
                                fileInputStream.close();
                                dataOutputStream.flush();
                                dataOutputStream.close();
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
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

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
