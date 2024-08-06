package com.swenauk.mainmenu;

import android.app.UiModeManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.TracksInfo;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.CaptionStyleCompat;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.material.timepicker.TimeModel;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.swenauk.mainmenu.ChannelWork.ChannelClass;
import com.swenauk.mainmenu.ChannelWork.ProgramClass;
import com.swenauk.mainmenu.Classes.EpisodeClass;
import com.swenauk.mainmenu.Classes.IPTvClass;
import com.swenauk.mainmenu.Classes.PosterClass;
import com.swenauk.mainmenu.GetsPack.UserSystem;
import com.swenauk.mainmenu.Parsers.ATV;
import com.swenauk.mainmenu.Parsers.Adult;
import com.swenauk.mainmenu.Parsers.CanliTvLive;
import com.swenauk.mainmenu.Parsers.CloseLoad;
import com.swenauk.mainmenu.Parsers.Contentx;
import com.swenauk.mainmenu.Parsers.DailyMotion;
import com.swenauk.mainmenu.Parsers.DiziPlus;
import com.swenauk.mainmenu.Parsers.Dizidimi;
import com.swenauk.mainmenu.Parsers.Dizigom;
import com.swenauk.mainmenu.Parsers.DizipubPlus;
import com.swenauk.mainmenu.Parsers.Diziroll;
import com.swenauk.mainmenu.Parsers.Diziyo;
import com.swenauk.mainmenu.Parsers.Fembed;
import com.swenauk.mainmenu.Parsers.FileRU;
import com.swenauk.mainmenu.Parsers.FilmModu;
import com.swenauk.mainmenu.Parsers.FoxPlay;
import com.swenauk.mainmenu.Parsers.Fullhdfilmizlesene;
import com.swenauk.mainmenu.Parsers.Hdfilmcehennemi;
import com.swenauk.mainmenu.Parsers.HdfilmcehennemiSyrtrk;
import com.swenauk.mainmenu.Parsers.IMDB;
import com.swenauk.mainmenu.Parsers.KanalD;
import com.swenauk.mainmenu.Parsers.KarnavalRadyo;
import com.swenauk.mainmenu.Parsers.Koreanturk;
import com.swenauk.mainmenu.Parsers.MailRU;
import com.swenauk.mainmenu.Parsers.OkRu;
import com.swenauk.mainmenu.Parsers.Parsers;
import com.swenauk.mainmenu.Parsers.S1cdn;
import com.swenauk.mainmenu.Parsers.SezonlukDizi;
import com.swenauk.mainmenu.Parsers.ShowTV;
import com.swenauk.mainmenu.Parsers.StarTV;
import com.swenauk.mainmenu.Parsers.SuperVideo;
import com.swenauk.mainmenu.Parsers.TLC;
import com.swenauk.mainmenu.Parsers.TrIpTv;
import com.swenauk.mainmenu.Parsers.Tv8;
import com.swenauk.mainmenu.Parsers.Ugurfilm;
import com.swenauk.mainmenu.Parsers.Unutulmaz;
import com.swenauk.mainmenu.Parsers.UptoStream;
import com.swenauk.mainmenu.Parsers.VidMoly;
import com.swenauk.mainmenu.Parsers.WFilmizle;
import com.swenauk.mainmenu.Parsers.YabanciDizi;
import com.swenauk.mainmenu.Parsers.Yesilcam;
import com.swenauk.mainmenu.Parsers.Yjco;
import com.swenauk.mainmenu.Parsers.YoutubeWGetter;
import com.swenauk.mainmenu.Sivvat.Canlitv;
import com.swenauk.mainmenu.Sivvat.Cinemathek;
import com.swenauk.mainmenu.Sivvat.Core;
import com.swenauk.mainmenu.Sivvat.Dizibox;
import com.swenauk.mainmenu.Sivvat.Dizilab;
import com.swenauk.mainmenu.Sivvat.Dizilla;
import com.swenauk.mainmenu.Sivvat.Dizimia;
import com.swenauk.mainmenu.Sivvat.Dizimom;
import com.swenauk.mainmenu.Sivvat.Dizipal;
import com.swenauk.mainmenu.Sivvat.Dizipub;
import com.swenauk.mainmenu.Sivvat.Dizirix;
import com.swenauk.mainmenu.Sivvat.Dizitime;
import com.swenauk.mainmenu.Sivvat.Diziyou;
import com.swenauk.mainmenu.Sivvat.Filmatek;
import com.swenauk.mainmenu.Sivvat.Filmekseni;
import com.swenauk.mainmenu.Sivvat.Filmizlesene;
import com.swenauk.mainmenu.Sivvat.Filmkovasi;
import com.swenauk.mainmenu.Sivvat.Filmmakinesi;
import com.swenauk.mainmenu.Sivvat.Filmon;
import com.swenauk.mainmenu.Sivvat.Hdtoday;
import com.swenauk.mainmenu.Sivvat.Helper;
import com.swenauk.mainmenu.Sivvat.Izle720P;
import com.swenauk.mainmenu.Sivvat.Jetfilmizle;
import com.swenauk.mainmenu.Sivvat.Kultfilmler;
import com.swenauk.mainmenu.Sivvat.Movie4k;
import com.swenauk.mainmenu.Sivvat.Movies123;
import com.swenauk.mainmenu.Sivvat.OnlineDizi;
import com.swenauk.mainmenu.Sivvat.OnlineParserTest;
import com.swenauk.mainmenu.Sivvat.Sinefy;
import com.swenauk.mainmenu.Sivvat.Sinemafilmizle;
import com.swenauk.mainmenu.Sivvat.Siyahfilmizle;
import com.swenauk.mainmenu.Sivvat.Sto;
import com.swenauk.mainmenu.Sivvat.TheMovieArchive;
import com.swenauk.mainmenu.Sivvat.Upcloud;
import com.swenauk.mainmenu.Sivvat.VKcom;
import com.swenauk.mainmenu.Sivvat.Vimeo;
import com.swenauk.mainmenu.Sivvat.Watch_Free;
import com.swenauk.mainmenu.Sivvat.Webteizle;
import com.swenauk.mainmenu.Sivvat.Wikiflix;
import com.swenauk.mainmenu.Sivvat.XCine;
import com.swenauk.mainmenu.Sivvat.Y2Mate;
import com.swenauk.mainmenu.Sivvat.Yabanci_Dizi;
import com.swenauk.mainmenu.Sivvat.Yabancidizi;
import com.swenauk.mainmenu.ViewAdapters.ItemShow1ViewAdapter;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.net.nntp.NNTPReply;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class VideoView extends AppCompatActivity implements MetadataOutput {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static boolean isDestroyed = false;
    private ItemShow1ViewAdapter adapter;
    private AlertDialog alert;
    public ArrayList<PosterClass> allChannels;
    public Map<String, Integer> audioSelection;
    public HashMap<String, Boolean> audios;
    private int backwardsSeek;
    private BandwidthMeter bandwidthMeter;
    private ImageView bs;
    public AlertDialog buffer;
    private String channelName;
    public boolean continueSearching;
    private Core core;
    private int currentChannel;
    public long duration;
    public String epgString;
    private int forwardSeek;
    private ImageView fs;
    private Handler handler;
    public int id;
    private Boolean isControllerBack;
    public Integer isDone;
    public Boolean isHLS;
    private Boolean isIPTV;
    public Boolean isRadio;
    public Boolean isTV;
    public Boolean isVisible;
    private boolean justShown;
    private long latestBS;
    private long latestFS;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ScaleGestureDetector mScaleDetector;
    public String m_id;
    private String metadata;
    public String mili;
    public AlertDialog notfound;
    public String p_id;
    public Parsers parser;
    private ExoPlayer player;
    private StyledPlayerView playerView;
    private SharedPreferences preferences;
    private boolean prevWhenReady;
    private ImageView ps;
    private RecyclerView recyclerView;
    private Runnable runnable;
    public boolean subFromParser;
    public HashMap<String, Boolean> subtitles;
    public Boolean timeShow;
    public DefaultTrackSelector trackSelector;
    public String u_id;
    private UiModeManager uiModeManager;
    private int uiOptions;
    private Uri videoUri;
    private Boolean wasPaused;
    public WebView webView;
    public String content = "";
    public String imdb = "";
    public boolean isLast = false;
    public boolean isTvSeries = false;
    public boolean is4K = false;
    public boolean isFragman = false;
    public boolean beenBacked = false;
    public boolean hasWebView = true;
    private String mainUrl = "";
    private boolean isFirst = true;
    private int lastMotion = -1;
    public long lastTriggerTimePS = -1;
    public boolean isSavedMili = false;
    public int isTurkish = 0;
    public boolean isDialogDismissed = false;

    @Override // com.google.android.exoplayer2.metadata.MetadataOutput
    public void onMetadata(Metadata metadata) {
        if (metadata.toString().contains("ICY:")) {
            Matcher matcher = Pattern.compile("title=\"(.*?)\"").matcher(metadata.toString());
            if (matcher.find()) {
                this.metadata = matcher.group(1);
                showMetadata();
            }
        }
    }

    private void showMetadata() {
        Toast makeText = Toast.makeText(this, this.metadata, 0);
        makeText.setGravity(49, 0, 150);
        makeText.show();
    }

    public void setMetadata(String str) {
        this.metadata = str;
        if (!this.isRadio.booleanValue() || str == null || str.length() <= 5) {
            return;
        }
        showMetadata();
    }

    public void setVideoUri(Uri uri) {
        this.videoUri = uri;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        this.uiModeManager = (UiModeManager) getSystemService("uimode");
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        WebView webView = this.webView;
        if (webView != null) {
            webView.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        int r14;
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (!Fresco.hasBeenInitialized()) {
            Fresco.initialize(this);
        }
        View decorView = getWindow().getDecorView();
        this.uiOptions = 6;
        decorView.setSystemUiVisibility(6);
        this.continueSearching = true;
        this.isVisible = true;
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        this.isDone = 0;
        this.wasPaused = false;
        this.isTV = false;
        this.isRadio = false;
        this.isHLS = false;
        this.justShown = false;
        this.prevWhenReady = true;
        this.allChannels = new ArrayList<>();
        this.audios = new HashMap<>();
        HashMap<String, Boolean> hashMap = new HashMap<>();
        this.subtitles = hashMap;
        hashMap.put("Altyazısız", false);
        this.subFromParser = false;
        this.timeShow = false;
        this.audioSelection = new HashMap();
        this.p_id = "";
        this.preferences = getSharedPreferences("prefName", 0);
        this.mScaleDetector = new ScaleGestureDetector(this, new MyOnScaleGestureListener());
        this.mainUrl = getIntent().getStringExtra("STREAM_URL");
        this.isFragman = getIntent().hasExtra("hasTrailer");
        getWindow().setFlags(1024, 1024);
        Bundle bundle2 = new Bundle();
        try {
            setContentView(com.swenauk.seyirturk.R.layout.activity_video_view);
            str = "parserCheck";
        } catch (Exception unused) {
            this.hasWebView = false;
            setContentView(com.swenauk.seyirturk.R.layout.activity_video_view_no_webview);
            str = "oldParserCheck";
        }
        bundle2.putString("hasWebView", "" + this.hasWebView);
        this.mFirebaseAnalytics.logEvent(str, bundle2);
        this.fs = (ImageView) findViewById(com.swenauk.seyirturk.R.id.forwardSeekImage);
        this.bs = (ImageView) findViewById(com.swenauk.seyirturk.R.id.backwardsSeekImage);
        this.ps = (ImageView) findViewById(com.swenauk.seyirturk.R.id.playButtonImage);
        this.isControllerBack = false;
        HashMap hashMap2 = new HashMap();
        if (getIntent().getData() != null) {
            UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
            this.uiModeManager = uiModeManager;
            if (uiModeManager.getCurrentModeType() == 4 && Build.VERSION.SDK_INT >= 26) {
                new ProgramClass(this, new ChannelClass(this)).getFromProgram("IPTV Favoriler");
            }
            this.isIPTV = true;
            String replace = String.valueOf(getIntent().getData().getEncodedAuthority()).replace("seyirturk:", "");
            for (int r10 = 0; r10 < this.allChannels.size(); r10++) {
                try {
                    if (this.allChannels.get(r10).getId() == Integer.parseInt(replace)) {
                        this.channelName = this.allChannels.get(r10).getName();
                        this.currentChannel = r10;
                        this.mainUrl = this.allChannels.get(r10).getLink();
                    }
                } catch (Exception unused2) {
                }
            }
            if (this.mainUrl == null) {
                this.mainUrl = "";
            }
        } else {
            this.isIPTV = false;
        }
        if (this.mainUrl.contains("fileru")) {
            hashMap2.put("Referer", "https://dizilla.com");
        } else {
            hashMap2.put("Referer", "http://filmakinesi.net");
        }
        hashMap2.put("betty", "jughead");
        if (getIntent().hasExtra("isRadio")) {
            this.isRadio = true;
            this.isIPTV = true;
        }
        if (getIntent().getStringExtra("isIpTV") != null) {
            this.isIPTV = true;
        }
        if (getIntent().getStringExtra("imdbNo") != null) {
            this.imdb = getIntent().getStringExtra("imdbNo");
        }
        this.is4K = getIntent().getBooleanExtra("is4K", false);
        StyledPlayerView styledPlayerView = (StyledPlayerView) findViewById(com.swenauk.seyirturk.R.id.exoViewCustom);
        this.playerView = styledPlayerView;
        styledPlayerView.setVisibility(0);
        StyledPlayerView styledPlayerView2 = (StyledPlayerView) findViewById(com.swenauk.seyirturk.R.id.exoView);
        if (this.isIPTV.booleanValue()) {
            ((LinearLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_bottom_bar)).setVisibility(4);
            this.ps.setVisibility(4);
        }
        styledPlayerView2.setVisibility(4);
        ImageButton imageButton = (ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice);
        View findViewById = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_progress);
        findViewById.setFocusable(false);
        findViewById.setClickable(false);
        imageButton.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    view.setAlpha(0.6f);
                } else {
                    view.setAlpha(1.0f);
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                System.out.println("Subtitle Butonu");
            }
        });
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(this);
        this.trackSelector = defaultTrackSelector;
        if (this.is4K) {
            defaultTrackSelector.setParameters(defaultTrackSelector.getParameters().buildUpon().setMinVideoSize(3000, 0));
        }
        new Handler();
        this.bandwidthMeter = new DefaultBandwidthMeter.Builder(this).build();
        this.player = new ExoPlayer.Builder(this).setTrackSelector(this.trackSelector).setBandwidthMeter(this.bandwidthMeter).setLoadControl(new DefaultLoadControl.Builder().setBufferDurationsMs(50000, 240000, 5000, 10000).build()).build();
        this.uiModeManager = (UiModeManager) getSystemService("uimode");
        SharedPreferences sharedPreferences = getSharedPreferences("prefName", 0);
        this.playerView.setPlayer(this.player);
        ((ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice)).setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    VideoView.this.newShowTrackSelections();
                } catch (Exception unused3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VideoView.this, com.swenauk.seyirturk.R.style.AlertDialog);
                    builder.setTitle("Altyazı ve Ses seçenekleri getirilirken hata oluştu.");
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.3.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int r2) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        });
        UiModeManager uiModeManager2 = (UiModeManager) getSystemService("uimode");
        this.uiModeManager = uiModeManager2;
        if (uiModeManager2.getCurrentModeType() != 4 && !sharedPreferences.getBoolean("tvMod", false)) {
            ((ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_back)).setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VideoView.this.goBack();
                }
            });
        }
        if (this.isRadio.booleanValue()) {
            int r0 = sharedPreferences.getInt("radioPhoto", 0);
            if (r0 == 0) {
                this.playerView.setDefaultArtwork(getDrawable(com.swenauk.seyirturk.R.drawable.radio));
            } else if (r0 == 1) {
                this.playerView.setDefaultArtwork(getDrawable(com.swenauk.seyirturk.R.drawable.r1));
            } else if (r0 == 2) {
                this.playerView.setDefaultArtwork(getDrawable(com.swenauk.seyirturk.R.drawable.r2));
            } else if (r0 == 3) {
                this.playerView.setDefaultArtwork(getDrawable(com.swenauk.seyirturk.R.drawable.r3));
            }
        }
        Boolean valueOf = Boolean.valueOf(sharedPreferences.getBoolean("timeShow", false));
        this.timeShow = valueOf;
        if (valueOf.booleanValue()) {
            this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_time_remaining).setVisibility(0);
            this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_duration).setVisibility(8);
        }
        this.epgString = sharedPreferences.getString("EPG", "");
        this.playerView.setControllerVisibilityListener(new StyledPlayerControlView.VisibilityListener() { // from class: com.swenauk.mainmenu.VideoView.5
            @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.VisibilityListener
            public void onVisibilityChange(int r2) {
                if (r2 == 0 && VideoView.this.isIPTV.booleanValue()) {
                    VideoView videoView = VideoView.this;
                    videoView.uiModeManager = (UiModeManager) videoView.getSystemService("uimode");
                    if (VideoView.this.uiModeManager.getCurrentModeType() != 4 || Build.VERSION.SDK_INT < 26) {
                        VideoView.this.showChannels();
                    }
                }
            }
        });
        this.playerView.setOnKeyListener(new View.OnKeyListener() { // from class: com.swenauk.mainmenu.VideoView.6
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int r2, KeyEvent keyEvent) {
                return VideoView.super.onKeyDown(r2, keyEvent);
            }
        });
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.swenauk.mainmenu.VideoView.7
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                VideoView.this.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                if (!VideoView.this.isIPTV.booleanValue()) {
                    if (motionEvent.getX() > VideoView.this.playerView.getWidth() / 2.0d) {
                        VideoView.this.seek(true);
                    } else {
                        VideoView.this.seek(false);
                    }
                }
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (motionEvent.getX() >= VideoView.this.playerView.getWidth() * 0.6d || motionEvent.getX() <= VideoView.this.playerView.getWidth() * 0.4d || !VideoView.this.playerView.isControllerFullyVisible() || VideoView.this.justShown) {
                    if (VideoView.this.playerView.isControllerFullyVisible() && !VideoView.this.justShown) {
                        VideoView.this.playerView.hideController();
                    }
                } else {
                    VideoView.this.player.setPlayWhenReady(!VideoView.this.player.getPlayWhenReady());
                    VideoView.this.showPermanent(true);
                }
                VideoView.this.justShown = false;
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent.getY() > motionEvent2.getY() && f2 < -6000.0f) {
                    VideoView.this.player.setPlaybackParameters(new PlaybackParameters(VideoView.this.player.getPlaybackParameters().speed + 0.25f));
                    TextView textView = (TextView) VideoView.this.playerView.findViewById(com.swenauk.seyirturk.R.id.speedBut);
                    System.out.println(f2);
                    textView.setText("x" + String.valueOf(VideoView.this.player.getPlaybackParameters().speed));
                } else if (f2 > 3000.0f && VideoView.this.player.getPlaybackParameters().speed > 0.3d) {
                    VideoView.this.player.setPlaybackParameters(new PlaybackParameters(VideoView.this.player.getPlaybackParameters().speed - 0.25f));
                    TextView textView2 = (TextView) VideoView.this.playerView.findViewById(com.swenauk.seyirturk.R.id.speedBut);
                    System.out.println(f2);
                    textView2.setText("x" + String.valueOf(VideoView.this.player.getPlaybackParameters().speed));
                }
                if (VideoView.this.playerView.isControllerFullyVisible() && !VideoView.this.justShown) {
                    VideoView.this.playerView.hideController();
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (!VideoView.this.playerView.isControllerFullyVisible()) {
                    VideoView.this.justShown = true;
                }
                return super.onDown(motionEvent);
            }
        });
        this.playerView.setControllerVisibilityListener(new AnonymousClass8());
        this.playerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.swenauk.mainmenu.VideoView.9
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        try {
            SubtitleView subtitleView = (SubtitleView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_subtitles);
            subtitleView.setApplyEmbeddedStyles(false);
            int parseColor = Color.parseColor("#77000000");
            try {
                if (Statics.preferences.getBoolean("subtitleBG", false)) {
                    parseColor = 0;
                }
            } catch (Exception unused3) {
            }
            int r15 = parseColor;
            try {
                r14 = Statics.preferences.getBoolean("subtitleColor", false) ? InputDeviceCompat.SOURCE_ANY : -1;
            } catch (Exception e) {
                System.out.println(e);
                r14 = -1;
            }
            try {
                subtitleView.setFixedTextSize(2, Integer.parseInt(Statics.preferences.getString("subtitleSize", "28")));
            } catch (Exception unused4) {
            }
            subtitleView.setStyle(new CaptionStyleCompat(r14, r15, 0, 1, ViewCompat.MEASURED_STATE_MASK, null));
        } catch (Exception unused5) {
        }
        this.playerView.setControllerHideOnTouch(false);
        this.recyclerView = (RecyclerView) findViewById(com.swenauk.seyirturk.R.id.recyclerView);
        if (getIntent().getStringExtra("isIpTV") != null) {
            this.isIPTV = true;
            ArrayList<String> stringArrayList = getIntent().getBundleExtra("channels").getStringArrayList("channels");
            this.allChannels = new ArrayList<>();
            for (int r102 = 0; r102 < stringArrayList.size(); r102++) {
                try {
                    IPTvClass iPTvClass = new IPTvClass(new JSONObject(stringArrayList.get(r102)));
                    this.allChannels.add(iPTvClass);
                    if (iPTvClass.getMyJson().getString(HttpHeaders.LINK).equals(this.mainUrl)) {
                        this.currentChannel = r102;
                        this.channelName = iPTvClass.getName();
                    }
                } catch (Exception e2) {
                    System.out.println(e2);
                }
            }
            this.recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.swenauk.mainmenu.VideoView.10
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!VideoView.this.isIPTV.booleanValue()) {
                        return false;
                    }
                    VideoView.this.showChannels();
                    return false;
                }
            });
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
            ItemShow1ViewAdapter itemShow1ViewAdapter = new ItemShow1ViewAdapter(this, this.allChannels);
            this.adapter = itemShow1ViewAdapter;
            this.recyclerView.setAdapter(itemShow1ViewAdapter);
            this.recyclerView.scrollToPosition(this.currentChannel);
        }
        createBufferAlert();
        bufferAlertToggle();
        if (!this.isIPTV.booleanValue()) {
            this.mili = getIntent().getStringExtra("MILI");
            this.u_id = getIntent().getStringExtra("U_ID");
            this.m_id = getIntent().getStringExtra("M_ID");
            this.p_id = getIntent().getStringExtra("P_ID");
            this.isLast = getIntent().getBooleanExtra("isLast", false);
            this.isTvSeries = getIntent().getBooleanExtra("isTvSeries", false);
            String str2 = (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) ? ("  S" + getIntent().getStringExtra("Season")) + " - B" + getIntent().getStringExtra("Episode") : "";
            if (getIntent().hasExtra("langType")) {
                this.isTurkish = getIntent().getIntExtra("langType", 0);
            }
            if (getIntent().hasExtra("m_name")) {
                updateCurrentlyPlaying(getIntent().getStringExtra("m_name") + str2);
            }
            Statics.isLast = this.isLast;
        }
        if (getIntent().getStringExtra("isTv") != null) {
            this.isTV = true;
        }
        if (this.isIPTV.booleanValue()) {
            this.playerView.setShowBuffering(0);
            UiModeManager uiModeManager3 = (UiModeManager) getSystemService("uimode");
            this.uiModeManager = uiModeManager3;
            if (uiModeManager3.getCurrentModeType() == 4) {
                int r02 = Build.VERSION.SDK_INT;
            }
        }
        this.forwardSeek = Integer.parseInt(sharedPreferences.getString("forwardSeek", "10 Saniye").replace(" Saniye", "")) * 1000;
        this.backwardsSeek = Integer.parseInt(sharedPreferences.getString("backwardsSeek", "10 Saniye").replace(" Saniye", "")) * 1000;
        if (this.mainUrl.contains("/plus/")) {
            this.mainUrl = this.mainUrl.replace("https:", "");
            this.mainUrl = "https://dizipub.net" + this.mainUrl;
        }
        if (this.mainUrl.contains("youtube.com/embed")) {
            this.mainUrl = this.mainUrl.replace("embed/", "watch?v=");
        }
        try {
            this.webView = (WebView) findViewById(com.swenauk.seyirturk.R.id.webViewTest);
        } catch (Exception unused6) {
        }
        isDestroyed = false;
        try {
            if (this.isIPTV.booleanValue()) {
                if (this.mainUrl.contains("canlitvlive.io")) {
                    loadCanliTvLive();
                } else if (this.mainUrl.contains("canlitv.center")) {
                    loadCanliTvCenter();
                } else if (this.mainUrl.contains("dailymotion")) {
                    loadDailyMotion();
                } else {
                    if (!this.mainUrl.contains("pokitv") && !this.mainUrl.contains("istanbuluseyret") && !this.mainUrl.contains("teleontv") && !this.mainUrl.contains("meteorolojitv") && !this.mainUrl.contains("canli-yayin") && !this.mainUrl.contains("CanliYayin") && !this.mainUrl.contains("canliyayin") && !this.mainUrl.contains("dhayayin") && !this.mainUrl.contains("canli-izle") && !this.mainUrl.contains("personamedia") && !this.mainUrl.contains("brtk.net") && !this.mainUrl.contains("kralmuzik") && !this.mainUrl.contains("m.star.com") && !this.mainUrl.contains("dreamturk") && !this.mainUrl.contains("bloomberght") && !this.mainUrl.contains("tjk.org") && !this.mainUrl.contains("womantv") && !this.mainUrl.contains("yabantv") && !this.mainUrl.contains("volotv.com") && !this.mainUrl.contains("chaturbate") && !this.mainUrl.contains("sabantv.com") && !this.mainUrl.contains("milyontv.com") && !this.mainUrl.contains("tele1") && !this.mainUrl.contains("ulketv") && !this.mainUrl.contains("tvnet")) {
                        if (!this.mainUrl.contains("streamtheworld") && !this.mainUrl.contains("canliradyolar.org") && !this.mainUrl.contains("radyohome") && !this.mainUrl.contains("onlineradiobox") && !this.mainUrl.contains("radyodelisi")) {
                            if (this.mainUrl.contains("youtube")) {
                                loadYoutube();
                            } else if (this.mainUrl.contains("7dak")) {
                                loadAdult();
                            } else if (this.mainUrl.contains("filmon")) {
                                loadFilmon();
                            } else {
                                System.out.println("IP TV URL -> " + this.mainUrl);
                                this.videoUri = Uri.parse(this.mainUrl);
                                this.player.addListener(new Player.Listener() { // from class: com.swenauk.mainmenu.VideoView.11
                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                                        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onAudioSessionIdChanged(int r1) {
                                        Player.Listener.CC.$default$onAudioSessionIdChanged(this, r1);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                                        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onCues(List list) {
                                        Player.Listener.CC.$default$onCues(this, list);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                                        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onDeviceVolumeChanged(int r1, boolean z) {
                                        Player.Listener.CC.$default$onDeviceVolumeChanged(this, r1, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onEvents(Player player, Player.Events events) {
                                        Player.Listener.CC.$default$onEvents(this, player, events);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onIsLoadingChanged(boolean z) {
                                        Player.Listener.CC.$default$onIsLoadingChanged(this, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onIsPlayingChanged(boolean z) {
                                        Player.Listener.CC.$default$onIsPlayingChanged(this, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onLoadingChanged(boolean z) {
                                        Player.Listener.CC.$default$onLoadingChanged(this, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                                        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int r2) {
                                        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, r2);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onMetadata(Metadata metadata) {
                                        Player.Listener.CC.$default$onMetadata(this, metadata);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int r2) {
                                        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, r2);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                                        Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int r1) {
                                        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, r1);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerError(this, playbackException);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlayerStateChanged(boolean z, int r2) {
                                        Player.Listener.CC.$default$onPlayerStateChanged(this, z, r2);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(int r1) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, r1);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int r3) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, r3);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onRenderedFirstFrame() {
                                        Player.Listener.CC.$default$onRenderedFirstFrame(this);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onRepeatModeChanged(int r1) {
                                        Player.Listener.CC.$default$onRepeatModeChanged(this, r1);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                                        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                                        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onSeekProcessed() {
                                        Player.Listener.CC.$default$onSeekProcessed(this);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                                        Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                                        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onSurfaceSizeChanged(int r1, int r2) {
                                        Player.Listener.CC.$default$onSurfaceSizeChanged(this, r1, r2);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onTimelineChanged(Timeline timeline, int r2) {
                                        Player.Listener.CC.$default$onTimelineChanged(this, timeline, r2);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                                        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                                        Player.Listener.CC.$default$onTracksChanged(this, trackGroupArray, trackSelectionArray);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                                        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public /* synthetic */ void onVolumeChanged(float f) {
                                        Player.Listener.CC.$default$onVolumeChanged(this, f);
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public void onPlaybackStateChanged(int r2) {
                                        if (r2 == 3 && VideoView.this.isFirst) {
                                            VideoView.this.isFirst = false;
                                            VideoView.this.showSubAndVoice();
                                        }
                                    }

                                    @Override // com.google.android.exoplayer2.Player.Listener
                                    public void onTracksInfoChanged(TracksInfo tracksInfo) {
                                        for (int r1 = 0; r1 < tracksInfo.getTrackGroupInfos().size(); r1++) {
                                            TrackGroup trackGroup = tracksInfo.getTrackGroupInfos().get(r1).getTrackGroup();
                                            for (int r3 = 0; r3 < trackGroup.length; r3++) {
                                                Metadata metadata = trackGroup.getFormat(r3).metadata;
                                                if (metadata != null) {
                                                    System.out.println(metadata);
                                                }
                                            }
                                        }
                                    }
                                });
                                if (this.mainUrl.contains("m3u8")) {
                                    HlsMediaSource createMediaSource = new HlsMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri));
                                    if (this.mainUrl.contains("yilmaztv")) {
                                        createMediaSource = new HlsMediaSource.Factory(new DataSource.Factory() { // from class: com.swenauk.mainmenu.VideoView.12
                                            @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
                                            public DataSource createDataSource() {
                                                DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
                                                defaultHttpDataSource.setRequestProperty("Referer", VideoView.this.mainUrl);
                                                return defaultHttpDataSource;
                                            }
                                        }).createMediaSource(MediaItem.fromUri(this.videoUri));
                                    }
                                    this.player.prepare(createMediaSource);
                                    this.player.setPlayWhenReady(true);
                                    bufferAlertToggle();
                                    this.isHLS = true;
                                } else if (this.mainUrl.contains("mp3")) {
                                    this.player.prepare(new HlsMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri)));
                                    this.player.setPlayWhenReady(true);
                                    bufferAlertToggle();
                                    this.isHLS = true;
                                } else if (!this.mainUrl.contains("m3u")) {
                                    new DefaultDataSource.Factory(this);
                                    MediaSource createMediaSource2 = new DefaultMediaSourceFactory(this).createMediaSource(MediaItem.fromUri(this.videoUri));
                                    if (!this.mainUrl.contains(".mp4") && !this.mainUrl.contains(".mkv") && !this.mainUrl.contains(".avi")) {
                                        this.isHLS = true;
                                    }
                                    this.player.prepare(createMediaSource2);
                                    this.player.setPlayWhenReady(true);
                                    bufferAlertToggle();
                                }
                            }
                        }
                        loadKarnaval();
                    }
                    loadTrIpTV();
                }
            } else if (this.mainUrl.contains("s1cdn.vg")) {
                loadS1cdn();
            } else if (this.mainUrl.contains("imdb.com")) {
                loadImdb();
            } else if (this.mainUrl.contains("mail.ru")) {
                loadMailRU();
            } else if (this.mainUrl.contains("tabii")) {
                this.videoUri = Uri.parse(this.mainUrl);
                if (this.mainUrl.contains("m3u8")) {
                    this.player.prepare(new HlsMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri)));
                    final long parseInt = Integer.parseInt(this.mili);
                    System.out.println("Mili is " + parseInt);
                    if (parseInt > 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.swenauk.seyirturk.R.style.AlertDialog);
                        builder.setTitle("Video Nerden Başlasın");
                        builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.13
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int r2) {
                                VideoView.this.player.setPlayWhenReady(true);
                            }
                        });
                        builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.14
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int r4) {
                                VideoView.this.player.seekTo(parseInt);
                                VideoView.this.player.setPlayWhenReady(true);
                            }
                        });
                        builder.create().show();
                    } else {
                        this.player.setPlayWhenReady(true);
                    }
                    bufferAlertToggle();
                } else {
                    this.player.prepare(new ProgressiveMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri)));
                    final long parseInt2 = Integer.parseInt(this.mili);
                    System.out.println("Mili is " + parseInt2);
                    if (parseInt2 > 0) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(this, com.swenauk.seyirturk.R.style.AlertDialog);
                        builder2.setTitle("Video Nerden Başlasın");
                        builder2.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.15
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int r2) {
                                VideoView.this.player.setPlayWhenReady(true);
                            }
                        });
                        builder2.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.16
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int r4) {
                                VideoView.this.player.seekTo(parseInt2);
                                VideoView.this.player.setPlayWhenReady(true);
                            }
                        });
                        builder2.create().show();
                    } else {
                        this.player.setPlayWhenReady(true);
                    }
                    bufferAlertToggle();
                }
            } else {
                if (!this.mainUrl.contains("startv") && !this.mainUrl.contains("trt1")) {
                    if (this.mainUrl.contains("kanald")) {
                        loadKanalD();
                    } else if (this.mainUrl.contains("showtv")) {
                        loadShowTV();
                    } else if (this.mainUrl.contains("atv")) {
                        if (this.hasWebView) {
                            loadOnlineParserTest();
                        } else {
                            loadATV();
                        }
                    } else if (this.mainUrl.contains("/plus/")) {
                        loadDizipubPlus();
                    } else if (this.mainUrl.contains("dailymotion")) {
                        loadDailyMotion();
                    } else {
                        if (!this.mainUrl.contains("upstream") && !this.mainUrl.contains("vtube") && !this.mainUrl.contains("filemoon") && !this.mainUrl.contains("mixdrop") && !this.mainUrl.contains("dood") && !this.mainUrl.contains("voe.") && !this.mainUrl.contains("streamz") && !this.mainUrl.contains("freeomovie") && !this.mainUrl.contains("pandamovie") && !this.mainUrl.contains("streamtape") && !this.mainUrl.contains("protonvideo") && !this.mainUrl.contains("videobin") && !this.mainUrl.contains("gounlimited") && !this.mainUrl.contains("pornhub") && !this.mainUrl.contains("ashemaletube") && !this.mainUrl.contains("xvideos") && !this.mainUrl.contains("clipwatching") && !this.mainUrl.contains("7dak.com") && !this.mainUrl.contains("xnxx.com") && !this.mainUrl.contains("unlockxh1.com") && !this.mainUrl.contains("xhamster.com") && !this.mainUrl.contains("streamhub") && !this.mainUrl.contains("luluvdo") && !this.mainUrl.contains("youporn") && !this.mainUrl.contains("thehun")) {
                            if (this.mainUrl.contains("chefkoch24")) {
                                loadYesilcam();
                            } else {
                                if (!this.mainUrl.contains("puhutv.com") && !this.mainUrl.contains("teve2") && !this.mainUrl.contains("streamsb")) {
                                    if (this.mainUrl.contains("supervideo")) {
                                        loadSuper();
                                    } else if (this.mainUrl.contains("filmmodu")) {
                                        if (this.hasWebView) {
                                            loadOnlineParserTest();
                                        } else {
                                            loadFilmModu();
                                        }
                                    } else if (this.mainUrl.contains("uptostream")) {
                                        loadUpTo();
                                    } else if (this.mainUrl.contains("closeload")) {
                                        loadCloseLoad();
                                    } else {
                                        if (!this.mainUrl.contains("vidmoly") && !this.mainUrl.contains("flmplayer")) {
                                            if (!this.mainUrl.contains("ok.ru") && !this.mainUrl.contains("odnok")) {
                                                if (this.mainUrl.contains("youtube")) {
                                                    loadY2Mate();
                                                } else if (this.mainUrl.contains("nowtv.com.tr")) {
                                                    loadFoxPlay();
                                                } else {
                                                    if (!this.mainUrl.contains("feurl.com") && !this.mainUrl.contains("fembed.net") && !this.mainUrl.contains("vcdn.io") && !this.mainUrl.contains("fembed.com") && !this.mainUrl.contains("fplay.cf")) {
                                                        if (this.mainUrl.contains("yjco.xyz")) {
                                                            loadYjco();
                                                        } else {
                                                            if (!this.mainUrl.contains("fileru.net") && !this.mainUrl.contains("file.ru")) {
                                                                if (this.mainUrl.contains("diziplus")) {
                                                                    loadDiziplus();
                                                                } else if (this.mainUrl.contains("tv8.com.tr")) {
                                                                    loadTv8();
                                                                } else if (this.mainUrl.contains("kanal7")) {
                                                                    loadDailyMotion();
                                                                } else if (this.mainUrl.contains("k2s")) {
                                                                    loadAdult();
                                                                } else if (this.mainUrl.contains("umutdeneme")) {
                                                                    loadY2Mate();
                                                                } else if (this.mainUrl.contains("dizilla")) {
                                                                    if (this.hasWebView) {
                                                                        loadOnlineParserTest();
                                                                    } else {
                                                                        loadDizilla();
                                                                    }
                                                                } else if (this.mainUrl.contains("dizigom")) {
                                                                    loadDizigom();
                                                                } else {
                                                                    if (!this.mainUrl.contains("contentx") && !this.mainUrl.contains("filese") && !this.mainUrl.contains("playru")) {
                                                                        if (!this.mainUrl.contains("fullhdfilmizlesene") && !this.mainUrl.contains("4kfilmizle")) {
                                                                            if (this.mainUrl.contains("koreanturk")) {
                                                                                loadKoreanturk();
                                                                            } else if (this.mainUrl.contains("unutulmazfilmler")) {
                                                                                loadUnutulmaz();
                                                                            } else if (this.mainUrl.contains("filmmakinesi")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadFilmakinesi();
                                                                                }
                                                                            } else if (this.mainUrl.contains("ugurfilm")) {
                                                                                loadUgurfilm();
                                                                            } else if (this.mainUrl.contains("hdfilmcehennemi")) {
                                                                                if (this.mainUrl.contains("syrtrk")) {
                                                                                    if (this.hasWebView) {
                                                                                        loadOnlineParserTest();
                                                                                    } else {
                                                                                        loadHdFilmcehennemiSyrtrk();
                                                                                    }
                                                                                } else {
                                                                                    loadHdfilmcehennemi();
                                                                                }
                                                                            } else if (this.mainUrl.contains("webteizle")) {
                                                                                loadWebteizle();
                                                                            } else if (this.mainUrl.contains("720pizle")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadIzle720p();
                                                                                }
                                                                            } else if (this.mainUrl.contains("jetfilm")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadJetfilm();
                                                                                }
                                                                            } else if (this.mainUrl.contains("sezonlukdizi")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadSezonlukdizi();
                                                                                }
                                                                            } else if (this.mainUrl.contains("diziroll")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadDiziroll();
                                                                                }
                                                                            } else if (this.mainUrl.contains("filmizlesene")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadFilmizlesene();
                                                                                }
                                                                            } else if (this.mainUrl.contains("sinefy")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadSinefy();
                                                                                }
                                                                            } else if (this.mainUrl.contains("diziyo") && !this.mainUrl.contains("diziyou")) {
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadDiziyo();
                                                                                }
                                                                            } else {
                                                                                if (!this.mainUrl.contains("yabanci-dizi") && !this.mainUrl.contains("roketdizi")) {
                                                                                    if (this.mainUrl.contains("wfilmizle")) {
                                                                                        if (this.hasWebView) {
                                                                                            loadOnlineParserTest();
                                                                                        } else {
                                                                                            loadWFilmizle();
                                                                                        }
                                                                                    } else {
                                                                                        if (!this.mainUrl.contains("canli-izle") && !this.mainUrl.contains("dmax") && !this.mainUrl.contains("tlctv")) {
                                                                                            if (this.mainUrl.contains("onlinedizi")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadOnlinedizi();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("dizirix")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDizirix();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("dizipal")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDizipal();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("dizipub")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDizipub();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("yabancidizi")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadYabancidizi();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("dizibox")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDiziBox();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("dizitime")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDizitime();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("sinemafilmizle")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadSinemafilmizle();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("vk.com")) {
                                                                                                loadVKcom();
                                                                                            } else if (this.mainUrl.contains("dizimom")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadDizimom();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("hdtoday")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadHdtoday();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("wikiflix")) {
                                                                                                this.mainUrl += "#" + ("m_" + this.m_id);
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    loadWikiflix();
                                                                                                }
                                                                                            } else if (this.mainUrl.contains("123moviesfree")) {
                                                                                                if (this.hasWebView) {
                                                                                                    loadOnlineParserTest();
                                                                                                } else {
                                                                                                    load123Movies();
                                                                                                }
                                                                                            } else {
                                                                                                if (!this.mainUrl.contains("upcloud") && !this.mainUrl.contains("dokicloud")) {
                                                                                                    if (this.mainUrl.contains("dizilab")) {
                                                                                                        if (this.hasWebView) {
                                                                                                            loadOnlineParserTest();
                                                                                                        } else {
                                                                                                            loadDizilab();
                                                                                                        }
                                                                                                    } else if (this.mainUrl.contains("siyahfilmizle")) {
                                                                                                        if (this.hasWebView) {
                                                                                                            loadOnlineParserTest();
                                                                                                        } else {
                                                                                                            loadSiyahfilmizle();
                                                                                                        }
                                                                                                    } else {
                                                                                                        if (!this.mainUrl.contains("/s.to") && !this.mainUrl.contains("aniworld.to")) {
                                                                                                            if (this.mainUrl.contains("movie4k")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadMovie4k();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("cinemathek")) {
                                                                                                                loadCinemathek();
                                                                                                            } else if (this.mainUrl.contains("vimeo")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadVimeo();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("kultfilmler")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadKultfilmler();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("dizimia")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadDizimia();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("diziyou")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadDiziyou();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("filmatek")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadFilmatek();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("filmkovasi")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadFilmkovasi();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("filmekseni")) {
                                                                                                                if (this.hasWebView) {
                                                                                                                    loadOnlineParserTest();
                                                                                                                } else {
                                                                                                                    loadFilmekseni();
                                                                                                                }
                                                                                                            } else if (this.mainUrl.contains("setfilmizle")) {
                                                                                                                loadOnlineParserTest();
                                                                                                            } else if (this.mainUrl.equals("OnlineParserTest")) {
                                                                                                                loadOnlineParserTest();
                                                                                                            } else {
                                                                                                                this.videoUri = Uri.parse(this.mainUrl);
                                                                                                                this.player.addListener(new Player.Listener() { // from class: com.swenauk.mainmenu.VideoView.17
                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                                                                                                                        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onAudioSessionIdChanged(int r1) {
                                                                                                                        Player.Listener.CC.$default$onAudioSessionIdChanged(this, r1);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                                                                                                                        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onCues(List list) {
                                                                                                                        Player.Listener.CC.$default$onCues(this, list);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                                                                                                                        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onDeviceVolumeChanged(int r1, boolean z) {
                                                                                                                        Player.Listener.CC.$default$onDeviceVolumeChanged(this, r1, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onEvents(Player player, Player.Events events) {
                                                                                                                        Player.Listener.CC.$default$onEvents(this, player, events);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onIsLoadingChanged(boolean z) {
                                                                                                                        Player.Listener.CC.$default$onIsLoadingChanged(this, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onIsPlayingChanged(boolean z) {
                                                                                                                        Player.Listener.CC.$default$onIsPlayingChanged(this, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onLoadingChanged(boolean z) {
                                                                                                                        Player.Listener.CC.$default$onLoadingChanged(this, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                                                                                                                        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int r2) {
                                                                                                                        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, r2);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                                                                                                                        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onMetadata(Metadata metadata) {
                                                                                                                        Player.Listener.CC.$default$onMetadata(this, metadata);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int r2) {
                                                                                                                        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, r2);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                                                                                                                        Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int r1) {
                                                                                                                        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, r1);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                                                                                                                        Player.Listener.CC.$default$onPlayerError(this, playbackException);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                                                                                                                        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlayerStateChanged(boolean z, int r2) {
                                                                                                                        Player.Listener.CC.$default$onPlayerStateChanged(this, z, r2);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                                                                                                                        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPositionDiscontinuity(int r1) {
                                                                                                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, r1);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int r3) {
                                                                                                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, r3);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onRenderedFirstFrame() {
                                                                                                                        Player.Listener.CC.$default$onRenderedFirstFrame(this);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onRepeatModeChanged(int r1) {
                                                                                                                        Player.Listener.CC.$default$onRepeatModeChanged(this, r1);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                                                                                                                        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                                                                                                                        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onSeekProcessed() {
                                                                                                                        Player.Listener.CC.$default$onSeekProcessed(this);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                                                                                                                        Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                                                                                                                        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onSurfaceSizeChanged(int r1, int r2) {
                                                                                                                        Player.Listener.CC.$default$onSurfaceSizeChanged(this, r1, r2);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onTimelineChanged(Timeline timeline, int r2) {
                                                                                                                        Player.Listener.CC.$default$onTimelineChanged(this, timeline, r2);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                                                                                                                        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                                                                                                                        Player.Listener.CC.$default$onTracksChanged(this, trackGroupArray, trackSelectionArray);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                                                                                                                        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public /* synthetic */ void onVolumeChanged(float f) {
                                                                                                                        Player.Listener.CC.$default$onVolumeChanged(this, f);
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public void onPlaybackStateChanged(int r2) {
                                                                                                                        if (r2 == 3 && VideoView.this.isFirst) {
                                                                                                                            VideoView.this.isFirst = false;
                                                                                                                            VideoView.this.showSubAndVoice();
                                                                                                                        }
                                                                                                                    }

                                                                                                                    @Override // com.google.android.exoplayer2.Player.Listener
                                                                                                                    public void onTracksInfoChanged(TracksInfo tracksInfo) {
                                                                                                                        for (int r1 = 0; r1 < tracksInfo.getTrackGroupInfos().size(); r1++) {
                                                                                                                            TrackGroup trackGroup = tracksInfo.getTrackGroupInfos().get(r1).getTrackGroup();
                                                                                                                            for (int r3 = 0; r3 < trackGroup.length; r3++) {
                                                                                                                                Metadata metadata = trackGroup.getFormat(r3).metadata;
                                                                                                                                if (metadata != null) {
                                                                                                                                    System.out.println(metadata);
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                                if (this.mainUrl.contains("m3u8")) {
                                                                                                                    this.player.prepare(new HlsMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri)));
                                                                                                                    this.player.setPlayWhenReady(true);
                                                                                                                    bufferAlertToggle();
                                                                                                                } else if (this.mainUrl.contains("mp3")) {
                                                                                                                    this.player.prepare(new HlsMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(this.videoUri)));
                                                                                                                    this.player.setPlayWhenReady(true);
                                                                                                                    bufferAlertToggle();
                                                                                                                } else if (!this.mainUrl.contains("m3u")) {
                                                                                                                    new DefaultDataSource.Factory(this);
                                                                                                                    this.player.prepare(new DefaultMediaSourceFactory(this).createMediaSource(MediaItem.fromUri(this.videoUri)));
                                                                                                                    this.player.setPlayWhenReady(true);
                                                                                                                    bufferAlertToggle();
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                        if (this.hasWebView) {
                                                                                                            loadOnlineParserTest();
                                                                                                        } else {
                                                                                                            loadSto();
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                loadUpcloud();
                                                                                            }
                                                                                        }
                                                                                        loadTLC();
                                                                                    }
                                                                                }
                                                                                if (this.hasWebView) {
                                                                                    loadOnlineParserTest();
                                                                                } else {
                                                                                    loadYabanci_Dizi();
                                                                                }
                                                                            }
                                                                        }
                                                                        loadFullhdfilmizlesene();
                                                                    }
                                                                    loadContentx();
                                                                }
                                                            }
                                                            loadFileRU();
                                                        }
                                                    }
                                                    loadFembed();
                                                }
                                            }
                                            loadOkRu();
                                        }
                                        loadVidMoly();
                                    }
                                }
                                loadYabanci();
                            }
                        }
                        loadAdult();
                    }
                }
                loadStarTV();
            }
        } catch (Exception unused7) {
        }
        if (getIntent().getIntExtra("scaleType", -1) != -1) {
            this.playerView.setResizeMode(getIntent().getIntExtra("scaleType", 0));
        }
    }

    /* renamed from: com.swenauk.mainmenu.VideoView$8, reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass8 implements StyledPlayerControlView.VisibilityListener {
        AnonymousClass8() {
        }

        @Override // com.google.android.exoplayer2.ui.StyledPlayerControlView.VisibilityListener
        public void onVisibilityChange(int r5) {
            VideoView.this.showPermanent(r5 == 0);
            if (r5 == 0) {
                VideoView.this.updateAllTopInfo();
                if (VideoView.this.timeShow.booleanValue()) {
                    VideoView videoView = VideoView.this;
                    videoView.duration = videoView.player.getDuration();
                    if (VideoView.this.duration > 0) {
                        final Handler handler = new Handler();
                        handler.post(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.8.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (VideoView.this.playerView.isControllerFullyVisible()) {
                                    long currentPosition = (VideoView.this.duration - VideoView.this.player.getCurrentPosition()) / 1000;
                                    int r3 = (int) (currentPosition / 3600);
                                    int r2 = ((int) (currentPosition / 60)) - (r3 * 60);
                                    final String format = String.format(Locale.forLanguageTag(HtmlTableRow.TAG_NAME), "%02d:%02d:%02d", Integer.valueOf(r3), Integer.valueOf(r2), Integer.valueOf((((int) currentPosition) - (r3 * 3600)) - (r2 * 60)));
                                    VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.8.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            ((TextView) VideoView.this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_time_remaining)).setText(format);
                                        }
                                    });
                                    handler.postDelayed(this, 100L);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            VideoView.this.resetPlayerViewVisibility();
        }
    }

    public void loadFilmon() {
        new Filmon(this.mainUrl, this, this.player, this.webView);
    }

    public void loadUpcloud() {
        new Upcloud(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizilab() {
        new Dizilab(this.mainUrl, this, this.player, this.webView);
    }

    public void loadSiyahfilmizle() {
        new Siyahfilmizle(this.mainUrl, this, this.player, this.webView);
    }

    public void loadSto() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        new Sto(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadKultfilmler() {
        new Kultfilmler(this.mainUrl, this, this.player, this.webView);
    }

    public void loadMovie4k() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        new Movie4k(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadCinemathek() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        new Cinemathek(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadXcine() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        new XCine(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadVimeo() {
        new Vimeo(this.mainUrl, this, this.player, this.webView);
    }

    public void loadHdtoday() {
        String str;
        String str2;
        String str3;
        String str4 = "m_" + this.m_id;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str2 = getIntent().getStringExtra("Season");
            str = "t_" + this.m_id;
            str3 = stringExtra;
        } else {
            str = str4;
            str2 = "0";
            str3 = str2;
        }
        this.core = new Hdtoday(this.mainUrl, this, this.player, this.webView, str2, str3, str);
    }

    public void loadWikiflix() {
        this.core = new Wikiflix(this.mainUrl, this, this.player, this.webView, "m_" + this.m_id);
    }

    public void loadTheMovieArchive() {
        this.core = new TheMovieArchive(this.mainUrl, this, this.player, this.webView, "m_" + this.m_id);
    }

    public void loadDizimia() {
        new Dizimia(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDiziyou() {
        new Diziyou(this.mainUrl, this, this.player, this.webView);
    }

    public void loadFilmatek() {
        new Filmatek(this.mainUrl, this, this.player, this.webView);
    }

    public void loadFilmkovasi() {
        new Filmkovasi(this.mainUrl, this, this.player, this.webView);
    }

    public void loadFilmekseni() {
        new Filmekseni(this.mainUrl, this, this.player, this.webView);
    }

    public void loadOnlineParserTest() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        new OnlineParserTest(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadWatch_Free() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        this.core = new Watch_Free(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void load123Movies() {
        String str;
        String str2;
        if (getIntent().hasExtra("Season") && getIntent().hasExtra("Episode")) {
            String stringExtra = getIntent().getStringExtra("Episode");
            str = getIntent().getStringExtra("Season");
            str2 = stringExtra;
        } else {
            str = "0";
            str2 = str;
        }
        this.core = new Movies123(this.mainUrl, this, this.player, this.webView, str, str2);
    }

    public void loadDizimom() {
        new Dizimom(this.mainUrl, this, this.player, this.webView);
    }

    public void loadVKcom() {
        new VKcom(this.mainUrl, this, this.player, this.webView);
    }

    public void loadSinemafilmizle() {
        new Sinemafilmizle(this.mainUrl, this, this.player, this.webView);
    }

    public void loadYabancidizi() {
        new Yabancidizi(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizipub() {
        new Dizipub(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizirix() {
        new Dizirix(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizipal() {
        new Dizipal(this.mainUrl, this, this.player, this.webView);
    }

    public void loadHdFilmcehennemiSyrtrk() {
        new HdfilmcehennemiSyrtrk(this.mainUrl, this, this.player);
    }

    public void loadOnlinedizi() {
        new OnlineDizi(this.mainUrl, this, this.player, this.webView);
    }

    public void loadTLC() {
        new TLC(this.mainUrl, this, this.player);
    }

    public void loadWFilmizle() {
        new WFilmizle(this.mainUrl, this, this.player);
    }

    public void loadDiziyo() {
        new Diziyo(this.mainUrl, this, this.player);
    }

    public void loadYabanci_Dizi() {
        String replace = this.mainUrl.replace("yabanci-dizi", "yabancidizi");
        this.mainUrl = replace;
        new Yabanci_Dizi(replace, this, this.player, this.webView);
    }

    public void loadSinefy() {
        new Sinefy(this.mainUrl, this, this.player, this.webView);
    }

    public void loadFilmizlesene() {
        new Filmizlesene(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDiziroll() {
        new Diziroll(this.mainUrl, this, this.player);
    }

    public void loadSezonlukdizi() {
        new SezonlukDizi(this.mainUrl, this, this.player);
    }

    public void loadDizitime() {
        new Dizitime(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizidimi() {
        new Dizidimi(this.mainUrl, this, this.player, this.trackSelector);
    }

    public void loadJetfilm() {
        new Jetfilmizle(this.mainUrl, this, this.player, this.webView);
    }

    public void loadIzle720p() {
        new Izle720P(this.mainUrl, this, this.player, this.webView);
    }

    public void loadContentx() {
        new Contentx(this.mainUrl, this, this.player);
    }

    public void loadWebteizle() {
        new Webteizle(this.mainUrl, this, this.player, this.webView);
    }

    public void loadHdfilmcehennemi() {
        new Hdfilmcehennemi(this.mainUrl, this, this.player);
    }

    public void loadUgurfilm() {
        new Ugurfilm(this.mainUrl, this, this.player);
    }

    public void loadFilmakinesi() {
        new Filmmakinesi(this.mainUrl, this, this.player, this.webView);
    }

    public void loadUnutulmaz() {
        new Unutulmaz(this.mainUrl, this, this.player);
    }

    public void loadKoreanturk() {
        new Koreanturk(this.mainUrl, this, this.player);
    }

    public void loadFullhdfilmizlesene() {
        new Fullhdfilmizlesene(this.mainUrl, this, this.player, this.trackSelector, this.bandwidthMeter);
    }

    public void loadDizigom() {
        new Dizigom(this.mainUrl, this, this.player);
    }

    public void loadTv8() {
        new Tv8(this.mainUrl, this, this.player);
    }

    public void loadY2Mate() {
        new Y2Mate(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDiziplus() {
        new DiziPlus(this.mainUrl, this, this.player);
    }

    public void loadFileRU() {
        new FileRU(this.mainUrl, this, this.player);
    }

    public void loadS1cdn() {
        new S1cdn(this.mainUrl, this, this.player);
    }

    public void loadCanliTvLive() {
        new CanliTvLive(this.mainUrl, this, this.player);
    }

    public void loadCanliTvCenter() {
        new Canlitv(this.mainUrl, this, this.player, this.webView);
    }

    public void loadFoxPlay() {
        new FoxPlay(this.mainUrl, this, this.player);
    }

    public void loadYjco() {
        new Yjco(this.mainUrl, this, this.player);
    }

    public void loadFembed() {
        new Fembed(this.mainUrl, this, this.player);
    }

    public void loadKarnaval() {
        new KarnavalRadyo(this.mainUrl, this, this.player);
    }

    public void loadYabanci() {
        new YabanciDizi(this.mainUrl, this, this.player);
    }

    public void loadSuper() {
        new SuperVideo(this.mainUrl, this, this.player);
    }

    public void loadUpTo() {
        new UptoStream(this.mainUrl, this, this.player);
    }

    public void loadOkRu() {
        new OkRu(this.mainUrl, this, this.player);
    }

    public void loadYoutube() {
        new YoutubeWGetter(this.mainUrl, this, this.player);
    }

    public void loadVidMoly() {
        new VidMoly(this.mainUrl, this, this.player);
    }

    public void loadCloseLoad() {
        new CloseLoad(this.mainUrl, this, this.player);
    }

    public void loadFilmModu() {
        new FilmModu(this.mainUrl, this, this.player);
    }

    public void loadAdult() {
        new Adult(this.mainUrl, this, this.player, false);
    }

    public void loadYesilcam() {
        new Yesilcam(this.mainUrl, this, this.player);
    }

    public void loadTrIpTV() {
        new TrIpTv(this.mainUrl, this, this.player);
    }

    public void loadImdb() {
        new IMDB(this.mainUrl, this, this.player);
    }

    public void loadDiziBox() {
        new Dizibox(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDizilla() {
        new Dizilla(this.mainUrl, this, this.player, this.webView);
    }

    public void loadDailyMotion() {
        new DailyMotion(this.mainUrl, this, this.player);
    }

    public void loadDizipubPlus() {
        new DizipubPlus(this.mainUrl, this, this.player);
    }

    public void loadMailRU() {
        new MailRU(this.mainUrl, this, this.player);
    }

    public void loadATV() {
        new ATV(this.mainUrl, this, this.player);
    }

    public void loadShowTV() {
        new ShowTV(this.mainUrl, this, this.player);
    }

    public void loadKanalD() {
        new KanalD(this.mainUrl, this, this.player);
    }

    public void loadStarTV() {
        new StarTV(this.mainUrl, this, this.player);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (!this.isIPTV.booleanValue() && !this.isFragman) {
            if (this.player.getDuration() * 0.85d <= this.player.getCurrentPosition() && this.player.getDuration() > 0) {
                this.isDone = 1;
            }
            if (!this.isSavedMili) {
                UserSystem userSystem = new UserSystem(this);
                this.mili = String.valueOf(this.player.getCurrentPosition());
                userSystem.execute(4);
            }
        }
        if (this.isRadio.booleanValue()) {
            return;
        }
        this.player.setPlayWhenReady(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.isIPTV.booleanValue()) {
            this.buffer.setCancelable(true);
            this.buffer.cancel();
        }
        if (this.isControllerBack.booleanValue() || !this.playerView.isControllerFullyVisible()) {
            if (!this.isIPTV.booleanValue() && !this.isFragman) {
                if (this.player.getDuration() * 0.85d <= this.player.getCurrentPosition() && this.player.getDuration() > 0) {
                    this.isDone = 1;
                    Statics.isDone = true;
                    Statics.increase = true;
                    this.id = this.preferences.getInt(TtmlNode.ATTR_ID, 0);
                    Set<String> stringSet = this.preferences.getStringSet(this.id + "NewMarked", new HashSet());
                    if (!stringSet.contains("" + this.m_id)) {
                        stringSet.remove("-" + this.m_id);
                        stringSet.add("" + this.m_id);
                    }
                    this.preferences.edit().remove(this.id + "NewMarked").apply();
                    this.preferences.edit().putStringSet(this.id + "NewMarked", stringSet).apply();
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putInt("user_id", this.id);
                        bundle.putString("andro_id", Settings.Secure.getString(getBaseContext().getContentResolver(), "android_id"));
                        bundle.putString("movie_id", this.m_id);
                        this.mFirebaseAnalytics.logEvent("watched", bundle);
                    } catch (Exception unused) {
                    }
                } else {
                    Statics.increase = false;
                }
            }
            this.player.setPlayWhenReady(false);
            this.continueSearching = false;
            boolean z = this.preferences.getBoolean("autoEpisode", true);
            if (this.isDone.intValue() == 1 && z && !this.isLast && this.id > 0 && this.isTvSeries && !this.isDialogDismissed && this.preferences.getBoolean("isVipUser", false)) {
                this.mili = String.valueOf(this.player.getCurrentPosition());
                new UserSystem(this).execute(8);
                bufferAlertToggle();
            } else {
                if (this.isLast && this.isDone.intValue() == 1 && this.isTvSeries) {
                    Statics.increase = false;
                    Toast.makeText(this, "Sistemdeki son bölümü izlediniz!", 1).show();
                }
                try {
                    this.player.release();
                    this.parser.cancelBackground();
                } catch (Exception unused2) {
                }
                finish();
                super.onBackPressed();
            }
            isDestroyed = true;
            return;
        }
        this.playerView.hideController();
    }

    public void playNextEpisode(EpisodeClass episodeClass) {
        String str;
        try {
            this.isSavedMili = true;
            final Intent intent = new Intent(this, (Class<?>) VideoView.class);
            ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            URL url = new URL(this.mainUrl);
            String str2 = "";
            for (int r8 = 0; r8 < episodeClass.getStreams().size(); r8++) {
                if (episodeClass.getStreams().get(r8).getLink().contains(url.getHost()) && episodeClass.getStreams().get(r8).getTurkish() == this.isTurkish) {
                    str2 = episodeClass.getStreams().get(r8).getLink();
                }
                if (episodeClass.getStreams().get(r8).getTurkish() == 1) {
                    str = episodeClass.getStreams().get(r8).getProvider() + " - Türkçe Dublaj";
                } else if (episodeClass.getStreams().get(r8).getTurkish() == 0) {
                    str = episodeClass.getStreams().get(r8).getProvider() + " - Türkçe Altyazılı";
                } else if (episodeClass.getStreams().get(r8).getTurkish() == 2 && !episodeClass.getStreams().get(r8).getProvider().contains("IMDB")) {
                    str = episodeClass.getStreams().get(r8).getProvider() + " - Türkçe Dublaj & Altyazılı";
                } else if (episodeClass.getStreams().get(r8).getTurkish() == 3) {
                    str = episodeClass.getStreams().get(r8).getProvider() + " - Almanca Dublaj";
                } else {
                    str = "" + episodeClass.getStreams().get(r8).getProvider();
                }
                arrayList.add(str);
                arrayList2.add(episodeClass.getStreams().get(r8).getLink());
            }
            intent.putExtra("MILI", "0");
            intent.putExtra("U_ID", String.valueOf(this.id));
            intent.putExtra("imdbNo", this.imdb);
            intent.putExtra("isTv", "1");
            intent.putExtra("M_ID", String.valueOf(episodeClass.getEp_id()));
            intent.putExtra("P_ID", String.valueOf(episodeClass.getId()));
            intent.putExtra("Episode", episodeClass.getEpisode());
            intent.putExtra("Season", episodeClass.getSeason());
            intent.putExtra("isLast", episodeClass.isLast());
            intent.putExtra("m_name", getIntent().getStringExtra("m_name"));
            intent.putExtra("isTvSeries", true);
            intent.putExtra("langType", this.isTurkish);
            Statics.latestSeason = Integer.parseInt(episodeClass.getSeason());
            Statics.latestEpisode = Integer.parseInt(episodeClass.getEpisode());
            final String season = episodeClass.getSeason();
            final String episode = episodeClass.getEpisode();
            if (str2.equals("")) {
                if (episodeClass.getStreams().size() > 1) {
                    CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new CharSequence[0]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, com.swenauk.seyirturk.R.style.AlertDialog);
                    builder.setTitle("Kaynak Seçiniz:");
                    builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.18
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int r4) {
                            intent.putExtra("STREAM_URL", (String) arrayList2.get(r4));
                            Toast.makeText(VideoView.this, "Otomatik olarak " + season + ". Sezon " + episode + ". Bölüme geçilmiştir!", 1).show();
                            VideoView.this.startActivity(intent);
                            try {
                                VideoView.this.player.release();
                            } catch (Exception e) {
                                System.out.println("Hata 4: " + e);
                            }
                            VideoView.this.finish();
                        }
                    });
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.swenauk.mainmenu.VideoView.19
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            VideoView.this.isDialogDismissed = true;
                        }
                    });
                    AlertDialog create = builder.create();
                    this.alert = create;
                    create.show();
                    return;
                }
                if (episodeClass.getStreams().size() == 1) {
                    intent.putExtra("STREAM_URL", episodeClass.getStreams().get(0).getLink());
                    startActivity(intent);
                    try {
                        this.player.release();
                    } catch (Exception e) {
                        System.out.println("Hata 3: " + e);
                    }
                    Toast.makeText(this, "Otomatik olarak " + season + ". Sezon " + episode + ". Bölüme geçilmiştir!", 1).show();
                    finish();
                    return;
                }
                return;
            }
            intent.putExtra("STREAM_URL", str2);
            startActivity(intent);
            try {
                this.player.release();
            } catch (Exception e2) {
                System.out.println("Hata 2: " + e2);
            }
            Toast.makeText(this, "Otomatik olarak " + season + ". Sezon " + episode + ". Bölüme geçilmiştir!", 1).show();
            finish();
            return;
        } catch (Exception e3) {
            System.out.println("Hata 1: " + e3);
        }
        System.out.println("Hata 1: " + e3);
    }

    public void goBack() {
        try {
            isDestroyed = true;
            this.isControllerBack = true;
            Core core = this.core;
            if (core != null) {
                core.end();
            }
            if (this.beenBacked) {
                return;
            }
            onBackPressed();
            this.beenBacked = true;
        } catch (Exception unused) {
            this.player.release();
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r18, KeyEvent keyEvent) {
        if (r18 == 20) {
            if (!this.isIPTV.booleanValue()) {
                if (this.player.isPlaying()) {
                    ((LinearLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_bottom_bar)).setVisibility(4);
                    if (this.player.getPlaybackParameters().speed > 0.3d) {
                        ExoPlayer exoPlayer = this.player;
                        exoPlayer.setPlaybackParameters(new PlaybackParameters(exoPlayer.getPlaybackParameters().speed - 0.25f));
                        ((TextView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.speedBut)).setText("x" + String.valueOf(this.player.getPlaybackParameters().speed));
                    }
                    this.playerView.showController();
                } else if (this.playerView.isControllerFullyVisible()) {
                    this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice).requestFocus();
                }
            } else if (this.recyclerView.getVisibility() == 0) {
                showChannels();
            } else {
                changeChannel(this.currentChannel - 1);
            }
        } else if (r18 == 19) {
            if (!this.isIPTV.booleanValue()) {
                ((LinearLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_bottom_bar)).setVisibility(4);
                if (this.player.isPlaying()) {
                    ExoPlayer exoPlayer2 = this.player;
                    exoPlayer2.setPlaybackParameters(new PlaybackParameters(exoPlayer2.getPlaybackParameters().speed + 0.25f));
                    ((TextView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.speedBut)).setText("x" + String.valueOf(this.player.getPlaybackParameters().speed));
                    this.playerView.showController();
                }
            } else if (this.recyclerView.getVisibility() == 0) {
                showChannels();
            } else {
                changeChannel(this.currentChannel + 1);
            }
        } else if (r18 == 23) {
            if (!((ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice)).hasFocus()) {
                this.player.setPlayWhenReady(!r7.isPlaying());
                showPermanent(true);
                if (this.playerView.isControllerFullyVisible()) {
                    resetPlayerViewVisibility();
                }
            }
        } else if (r18 == 22) {
            if (this.isIPTV.booleanValue()) {
                if (this.recyclerView.getVisibility() == 0) {
                    showChannels();
                } else {
                    try {
                        if (!this.isHLS.booleanValue()) {
                            seek(true);
                            this.playerView.showController();
                        } else {
                            showChannels();
                        }
                    } catch (Exception unused) {
                        showChannels();
                    }
                }
            } else if (this.player.isLoading() || this.player.isPlaying()) {
                seek(true);
            }
        } else if (r18 == 21) {
            if (this.isIPTV.booleanValue()) {
                if (this.recyclerView.getVisibility() == 0) {
                    showChannels();
                } else {
                    try {
                        if (!this.isHLS.booleanValue()) {
                            seek(false);
                            this.playerView.showController();
                        } else {
                            showChannels();
                        }
                    } catch (Exception unused2) {
                        showChannels();
                    }
                }
            } else if (this.player.isLoading() || this.player.isPlaying()) {
                seek(false);
            }
        }
        Calendar calendar = Calendar.getInstance();
        int r8 = calendar.get(11);
        String str = String.valueOf(r8) + ":" + String.format(Locale.forLanguageTag(HtmlTableRow.TAG_NAME), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(12)));
        if (this.isIPTV.booleanValue()) {
            try {
                JSONArray jSONArray = new JSONArray(this.epgString);
                for (int r13 = 0; r13 < jSONArray.length(); r13++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(r13);
                    if (jSONObject.getJSONObject("0").getString(TvContractCompat.PARAM_CHANNEL).toLowerCase().replace(" hd", "").replace(" tv", "").equals(this.channelName.toLowerCase().replace(" hd", "").replace(" tv", ""))) {
                        str = (str + " | " + jSONObject.getJSONObject("0").getString("name")) + " | Sonraki: " + jSONObject.getJSONObject("1").getString("name");
                    }
                }
            } catch (Exception unused3) {
            }
        }
        return super.onKeyDown(r18, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seek(boolean z) {
        this.playerView.showController();
        RelativeLayout relativeLayout = (RelativeLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_top_bar);
        ImageButton imageButton = (ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_back);
        ImageButton imageButton2 = (ImageButton) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice);
        if (this.player.isPlaying()) {
            relativeLayout.setVisibility(4);
            imageButton.setVisibility(4);
            imageButton2.setVisibility(4);
            this.ps.setVisibility(4);
        }
        if (z) {
            ExoPlayer exoPlayer = this.player;
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() + this.forwardSeek);
            if (this.fs.getAlpha() < 0.7f) {
                this.fs.animate().alpha(0.7f);
            }
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.20
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long time = new Date().getTime();
                        VideoView.this.latestFS = time;
                        Thread.sleep(1000L);
                        if (VideoView.this.latestFS == time) {
                            VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.20.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (VideoView.this.isFinishing()) {
                                        return;
                                    }
                                    VideoView.this.fs.animate().alpha(0.0f);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return;
        }
        ExoPlayer exoPlayer2 = this.player;
        exoPlayer2.seekTo(exoPlayer2.getCurrentPosition() - this.backwardsSeek);
        this.latestBS = System.currentTimeMillis();
        if (this.bs.getAlpha() < 0.7f) {
            this.bs.animate().alpha(0.7f);
        }
        new Thread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.21
            @Override // java.lang.Runnable
            public void run() {
                try {
                    long time = new Date().getTime();
                    VideoView.this.latestBS = time;
                    Thread.sleep(1000L);
                    if (VideoView.this.latestBS == time) {
                        VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.21.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (VideoView.this.isFinishing()) {
                                    return;
                                }
                                VideoView.this.bs.animate().alpha(0.0f);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showPermanent(boolean z) {
        if (!this.player.isPlaying()) {
            this.ps.setImageResource(com.swenauk.seyirturk.R.drawable.play);
        } else {
            this.ps.setImageResource(com.swenauk.seyirturk.R.drawable.pause);
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.22
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long time = new Date().getTime();
                        VideoView.this.lastTriggerTimePS = time;
                        if (!VideoView.this.playerView.isControllerFullyVisible()) {
                            Thread.sleep(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                        } else {
                            Thread.sleep(4000L);
                        }
                        if (VideoView.this.lastTriggerTimePS == time) {
                            VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.22.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (!VideoView.this.playerView.isControllerFullyVisible()) {
                                        VideoView.this.ps.setVisibility(4);
                                    } else if (VideoView.this.player.isPlaying()) {
                                        VideoView.this.playerView.hideController();
                                        VideoView.this.ps.setVisibility(4);
                                    }
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        if (z && !this.isIPTV.booleanValue() && this.fs.getAlpha() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.bs.getAlpha() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.ps.setVisibility(0);
        } else {
            this.ps.setVisibility(4);
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mScaleDetector.onTouchEvent(motionEvent);
        if (this.isIPTV.booleanValue()) {
            showChannels();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void comingFromChannels() {
        this.recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.swenauk.mainmenu.VideoView.23
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!VideoView.this.isIPTV.booleanValue()) {
                    return false;
                }
                VideoView.this.showChannels();
                return false;
            }
        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        ItemShow1ViewAdapter itemShow1ViewAdapter = new ItemShow1ViewAdapter(this, this.allChannels);
        this.adapter = itemShow1ViewAdapter;
        this.recyclerView.setAdapter(itemShow1ViewAdapter);
        this.recyclerView.scrollToPosition(this.currentChannel);
    }

    public void showChannels() {
        if (this.recyclerView.getVisibility() == 4) {
            this.recyclerView.setVisibility(0);
            String str = this.metadata;
            if (str != null && str != "") {
                showMetadata();
            }
        }
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
        this.handler = new Handler();
        Runnable runnable = new Runnable() { // from class: com.swenauk.mainmenu.VideoView.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VideoView.this.recyclerView.setVisibility(4);
                    VideoView.this.recyclerView.scrollToPosition(VideoView.this.currentChannel);
                    VideoView.this.adapter.notifyDataSetChanged();
                } catch (Exception unused) {
                }
            }
        };
        this.runnable = runnable;
        this.handler.postDelayed(runnable, 4000L);
    }

    public void changeChannel(int r7) {
        try {
            Intent intent = new Intent(this, (Class<?>) VideoView.class);
            intent.putExtra("STREAM_URL", this.allChannels.get(r7).getMyJson().getString(HttpHeaders.LINK));
            intent.putExtra("isIpTV", BooleanUtils.YES);
            intent.putExtra("scaleType", this.playerView.getResizeMode());
            intent.putExtra("EXTRA_SESSION_ID", this.allChannels.get(r7).getMyJson().toString());
            Bundle bundle = new Bundle();
            ArrayList<String> arrayList = new ArrayList<>();
            for (int r4 = 0; r4 < this.allChannels.size(); r4++) {
                arrayList.add(this.allChannels.get(r4).getMyJson().toString());
            }
            if (this.isRadio.booleanValue()) {
                intent.putExtra("isRadio", BooleanUtils.YES);
            }
            bundle.putStringArrayList("channels", arrayList);
            intent.putExtra("channels", bundle);
            startActivity(intent);
            try {
                this.player.release();
            } catch (Exception unused) {
            }
            finish();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAlert() {
        if (!this.isIPTV.booleanValue()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, com.swenauk.seyirturk.R.style.AlertDialog);
            builder.setCancelable(false);
            builder.setMessage("Medya bulunamadı!");
            builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.25
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int r2) {
                    if (VideoView.this.isIPTV.booleanValue()) {
                        return;
                    }
                    VideoView.this.goBack();
                }
            });
            AlertDialog create = builder.create();
            this.notfound = create;
            create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.swenauk.mainmenu.VideoView.26
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    Button button = VideoView.this.notfound.getButton(-2);
                    button.setFocusable(true);
                    button.setFocusableInTouchMode(true);
                    if (VideoView.this.uiModeManager.getCurrentModeType() == 4) {
                        button.requestFocus();
                    }
                }
            });
            this.notfound.show();
            return;
        }
        Toast makeText = Toast.makeText(this, "Kanal Bulunamadı", 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private void createBufferAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, com.swenauk.seyirturk.R.style.AlertDialog);
        builder.setCancelable(false);
        if (this.isIPTV.booleanValue()) {
            builder.setTitle(this.channelName + " Açılıyor...");
        } else {
            builder.setTitle("Seçtiğiniz siteden veri alınıyor...");
        }
        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.27
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r2) {
                if (VideoView.this.isIPTV.booleanValue()) {
                    return;
                }
                VideoView.this.goBack();
            }
        });
        AlertDialog create = builder.create();
        this.buffer = create;
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.swenauk.mainmenu.VideoView.28
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                VideoView.this.getWindow().getDecorView().setSystemUiVisibility(VideoView.this.uiOptions);
                Button button = VideoView.this.buffer.getButton(-2);
                button.setFocusable(true);
                button.setFocusableInTouchMode(true);
                if (VideoView.this.uiModeManager.getCurrentModeType() == 4) {
                    button.requestFocus();
                } else {
                    try {
                        button.requestFocus();
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    public void bufferAlertToggle() {
        if (!this.isIPTV.booleanValue()) {
            this.recyclerView.setVisibility(8);
        } else {
            new Thread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.29
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.29.1
                            @Override // java.lang.Runnable
                            public void run() {
                                VideoView.this.playerView.hideController();
                            }
                        });
                    } catch (Exception unused) {
                    }
                }
            }).start();
        }
        if (this.buffer.isShowing()) {
            this.buffer.setCancelable(true);
            this.buffer.cancel();
        } else {
            if ((this.isIPTV.booleanValue() && this.player.isPlaying()) || this.isIPTV.booleanValue()) {
                return;
            }
            this.buffer.setCancelable(false);
            this.buffer.show();
        }
    }

    /* loaded from: classes3.dex */
    public class MyOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public MyOnScaleGestureListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            System.out.println(scaleFactor);
            if (scaleFactor >= 1.0d) {
                VideoView.this.playerView.setResizeMode(4);
                return true;
            }
            VideoView.this.playerView.setResizeMode(0);
            return true;
        }
    }

    public void goBack(View view) {
        goBack();
    }

    public void updateResolutionText(String str) {
        ((TextView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_resolution)).setText(str);
    }

    public void updateTimeText(String str) {
        ((TextView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_current_time)).setText(str);
    }

    public void updateCurrentlyPlaying(String str) {
        ((TextView) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_currently_playing)).setText(str);
    }

    public void updateAllTopInfo() {
        try {
            int r4 = this.player.getVideoFormat().height;
            System.out.println("Exo Player Size: " + this.player.getVideoFormat());
            updateResolutionText(r4 > 1080 ? "4K" : r4 > 720 ? "1080p" : r4 > 480 ? "720p" : r4 > 360 ? "480p" : "360p");
        } catch (Exception unused) {
        }
        final Handler handler = new Handler();
        handler.post(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.30
            @Override // java.lang.Runnable
            public void run() {
                if (VideoView.this.playerView.isControllerFullyVisible()) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        final int r1 = calendar.get(11);
                        final String format = String.format(Locale.forLanguageTag(HtmlTableRow.TAG_NAME), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(calendar.get(12)));
                        VideoView.this.runOnUiThread(new Runnable() { // from class: com.swenauk.mainmenu.VideoView.30.1
                            @Override // java.lang.Runnable
                            public void run() {
                                VideoView.this.updateTimeText(String.valueOf(r1) + ":" + format);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.postDelayed(this, 1000L);
                }
            }
        });
        if (this.isIPTV.booleanValue()) {
            try {
                String str = this.channelName;
                JSONArray jSONArray = new JSONArray(this.epgString);
                for (int r6 = 0; r6 < jSONArray.length(); r6++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(r6);
                    if (jSONObject.getJSONObject("0").getString(TvContractCompat.PARAM_CHANNEL).toLowerCase().replace(" hd", "").replace(" tv", "").equals(this.channelName.toLowerCase().replace(" hd", "").replace(" tv", ""))) {
                        str = str + "      -      " + jSONObject.getJSONObject("0").getString("name");
                    }
                }
                updateCurrentlyPlaying(str);
            } catch (Exception unused2) {
            }
        }
    }

    public void resetPlayerViewVisibility() {
        LinearLayout linearLayout = (LinearLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_bottom_bar);
        RelativeLayout relativeLayout = (RelativeLayout) this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_top_bar);
        View findViewById = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_resolution);
        View findViewById2 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_current_time);
        View findViewById3 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_currently_playing);
        View findViewById4 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.speedBut);
        View findViewById5 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_duration);
        View findViewById6 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_time_remaining);
        View findViewById7 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_position);
        View findViewById8 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_progress);
        View findViewById9 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_back);
        View findViewById10 = this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice);
        linearLayout.setVisibility(0);
        relativeLayout.setVisibility(0);
        findViewById.setVisibility(0);
        findViewById2.setVisibility(0);
        findViewById3.setVisibility(0);
        findViewById4.setVisibility(0);
        if (!this.timeShow.booleanValue()) {
            findViewById5.setVisibility(0);
        } else {
            findViewById6.setVisibility(0);
        }
        findViewById7.setVisibility(0);
        findViewById8.setVisibility(0);
        findViewById9.setVisibility(0);
        findViewById10.setVisibility(0);
        if (this.subtitles.size() > 1 || this.audios.size() > 1) {
            return;
        }
        findViewById10.setVisibility(4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r5v4 */
    public void showSubAndVoice() {
        TrackGroup trackGroup;
        boolean z;
        Iterator<Map.Entry<String, Boolean>> it;
        Iterator<Map.Entry<String, Boolean>> it2;
        boolean z2;
        this.player.getCurrentTracksInfo().getTrackGroupInfos();
        TrackGroupArray currentTrackGroups = this.player.getCurrentTrackGroups();
        TrackSelectionArray currentTrackSelections = this.player.getCurrentTrackSelections();
        if (!this.player.isPlaying()) {
            this.ps.setImageResource(com.swenauk.seyirturk.R.drawable.play);
        } else {
            this.ps.setImageResource(com.swenauk.seyirturk.R.drawable.pause);
        }
        ?? r5 = 0;
        int r6 = 0;
        while (r6 < currentTrackGroups.length) {
            try {
                trackGroup = currentTrackGroups.get(r6);
                for (int r9 = 0; r9 < trackGroup.length; r9++) {
                    System.out.println("TGA " + r6 + ", " + r9);
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("TS Groups: ");
                    sb.append(trackGroup.getFormat(r9).language);
                    printStream.println(sb.toString());
                    System.out.println("TS Groups: " + trackGroup.getFormat(r9).sampleMimeType);
                    System.out.println("TS Groups: " + trackGroup.getFormat(r9).metadata);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if (trackGroup.length > 0) {
                String[] strArr = new String[3];
                strArr[r5] = trackGroup.getFormat(r5).sampleMimeType;
                strArr[1] = "vtt";
                strArr[2] = "subrip";
                if (Helper.containsAny(strArr) && !this.subFromParser) {
                    int r10 = 0;
                    while (true) {
                        if (r10 >= currentTrackSelections.length - 1) {
                            z2 = false;
                            break;
                        } else {
                            if (trackGroup.getFormat(r5) == currentTrackSelections.get(r10).getFormat(r5)) {
                                z2 = true;
                                break;
                            }
                            r10++;
                        }
                    }
                    if (!trackGroup.getFormat(r5).language.equals("forced")) {
                        System.out.println("Umut: " + trackGroup.getFormat(r5).language);
                        String[] strArr2 = new String[4];
                        strArr2[r5] = trackGroup.getFormat(r5).language;
                        strArr2[1] = "en";
                        strArr2[2] = HtmlTableRow.TAG_NAME;
                        strArr2[3] = "de";
                        if (Helper.containsAny(strArr2)) {
                            this.subtitles.put(trackGroup.getFormat(r5).language, Boolean.valueOf(z2));
                        }
                    }
                    r6++;
                    r5 = 0;
                }
            }
            if (trackGroup.length > 0 && trackGroup.getFormat(r5).sampleMimeType.contains("audio")) {
                int r7 = 0;
                while (true) {
                    if (r7 >= currentTrackSelections.length - 1) {
                        z = false;
                        break;
                    } else {
                        if (currentTrackSelections.get(r7) != null && currentTrackSelections.get(r7).length() > 0 && trackGroup.getFormat(r5) == currentTrackSelections.get(r7).getFormat(r5)) {
                            z = true;
                            break;
                        }
                        r7++;
                    }
                }
                try {
                    String replace = trackGroup.getFormat(r5).label.replace("Audio", "");
                    this.audios.put(replace, Boolean.valueOf(z));
                    this.audioSelection.put(replace, Integer.valueOf(trackGroup.getFormat(r5).selectionFlags));
                    if (this.mainUrl.contains("dizipal")) {
                        for (Map.Entry<String, Boolean> entry : this.audios.entrySet()) {
                            if (entry.getKey().contains("Eng")) {
                                this.audios.put(entry.getKey(), true);
                            } else {
                                this.audios.put(entry.getKey(), Boolean.valueOf((boolean) r5));
                            }
                            changeTracks();
                        }
                    } else if (this.mainUrl.contains("filmmakinesi")) {
                        for (Map.Entry<String, Boolean> entry2 : this.audios.entrySet()) {
                            if (((!entry2.getKey().contains("Eng") && !entry2.getKey().contains("Original")) || Statics.language != Core.Language.EN) && ((!entry2.getKey().contains("Tur") && !entry2.getKey().contains("Tür")) || Statics.language != Core.Language.TR)) {
                                this.audios.put(entry2.getKey(), Boolean.valueOf((boolean) r5));
                                changeTracks();
                            }
                            this.audios.put(entry2.getKey(), true);
                            changeTracks();
                        }
                    } else {
                        if (!this.mainUrl.contains("sinefy") && !this.mainUrl.contains("720pizle")) {
                            if (this.mainUrl.contains("dizilla")) {
                                Iterator<Map.Entry<String, Boolean>> it3 = this.audios.entrySet().iterator();
                                while (it3.hasNext()) {
                                    Map.Entry<String, Boolean> next = it3.next();
                                    if (((!next.getKey().contains("Eng") && !next.getKey().contains("Original") && !next.getKey().contains("Orijinal") && !next.getKey().contains("ngili")) || Statics.language != Core.Language.EN) && ((!next.getKey().contains("Tur") && !next.getKey().contains("Tür")) || Statics.language != Core.Language.TR)) {
                                        it2 = it3;
                                        this.audios.put(next.getKey(), false);
                                        changeTracks();
                                        it3 = it2;
                                    }
                                    it2 = it3;
                                    this.audios.put(next.getKey(), true);
                                    changeTracks();
                                    it3 = it2;
                                }
                            }
                        }
                        Iterator<Map.Entry<String, Boolean>> it4 = this.audios.entrySet().iterator();
                        while (it4.hasNext()) {
                            Map.Entry<String, Boolean> next2 = it4.next();
                            if (((!next2.getKey().contains("Eng") && !next2.getKey().contains("Original") && !next2.getKey().contains("Orijinal") && !next2.getKey().contains("ngili")) || Statics.language != Core.Language.EN) && ((!next2.getKey().contains("Tur") && !next2.getKey().contains("Tür")) || Statics.language != Core.Language.TR)) {
                                it = it4;
                                this.audios.put(next2.getKey(), false);
                                changeTracks();
                                it4 = it;
                            }
                            it = it4;
                            this.audios.put(next2.getKey(), true);
                            changeTracks();
                            it4 = it;
                        }
                    }
                } catch (Exception unused) {
                    this.audios.put("Tek Düblaj", Boolean.valueOf(z));
                }
            }
            r6++;
            r5 = 0;
        }
        if (this.subtitles.size() > 1 || this.audios.size() > 1) {
            return;
        }
        this.playerView.findViewById(com.swenauk.seyirturk.R.id.exo_sub_voice).setVisibility(4);
    }

    public void newShowTrackSelections() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(com.swenauk.seyirturk.R.layout.subtitle_popup_v2, (ViewGroup) null, false);
        inflate.measure(0, 0);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(com.swenauk.seyirturk.R.id.mainDrawer);
        inflate.findViewById(com.swenauk.seyirturk.R.id.restOfView).setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(relativeLayout, 17, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.swenauk.mainmenu.VideoView.32
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                VideoView.this.changeTracks();
                VideoView.this.playerView.requestFocus();
                VideoView.this.playerView.hideController();
                VideoView.this.player.setPlayWhenReady(true);
            }
        });
        inflate.requestFocus();
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.33
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    view.setBackgroundColor(Color.parseColor("#FF8C00"));
                } else {
                    view.setBackgroundColor(VideoView.this.getResources().getColor(android.R.color.white));
                }
            }
        };
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupSubs);
        final TextView textView = (TextView) inflate.findViewById(com.swenauk.seyirturk.R.id.spinSubtitleLanguage);
        final TextView textView2 = (TextView) inflate.findViewById(com.swenauk.seyirturk.R.id.spinDubLanguage);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupAudios);
        if (this.subtitles.size() > 1) {
            linearLayout.setVisibility(0);
            textView.setOnFocusChangeListener(onFocusChangeListener);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, com.swenauk.seyirturk.R.drawable.ic_action_down_arrow, 0);
            for (Map.Entry<String, Boolean> entry : this.subtitles.entrySet()) {
                if (entry.getValue().booleanValue()) {
                    textView.setText(entry.getKey());
                }
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.34
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VideoView.this.showSubtitlePicker(textView);
                }
            });
        } else {
            textView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            textView.setFocusable(false);
        }
        if (this.audios.size() > 1) {
            linearLayout2.setVisibility(0);
            textView2.setOnFocusChangeListener(onFocusChangeListener);
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, com.swenauk.seyirturk.R.drawable.ic_action_down_arrow, 0);
            for (Map.Entry<String, Boolean> entry2 : this.audios.entrySet()) {
                if (entry2.getValue().booleanValue()) {
                    textView2.setText(entry2.getKey());
                }
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.35
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VideoView.this.showDubPicker(textView2);
                }
            });
        } else {
            textView2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            textView2.setFocusable(false);
        }
        Button button = (Button) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupOkay);
        button.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.36
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Button button2 = (Button) view;
                if (z) {
                    button2.setAlpha(0.6f);
                    button2.setTextColor(Color.parseColor("#FF6C00"));
                } else {
                    button2.setAlpha(1.0f);
                    button2.setTextColor(-1);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    public void showDubPicker(final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dublaj Seçenekleri");
        NumberPicker numberPicker = new NumberPicker(this);
        final String[] strArr = (String[]) this.audios.keySet().toArray(new String[0]);
        int r5 = 0;
        for (int r4 = 0; r4 < strArr.length; r4++) {
            if (this.audios.containsKey(strArr[r4]) && this.audios.get(strArr[r4]).booleanValue()) {
                r5 = r4;
            }
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(strArr.length - 1);
        numberPicker.setDisplayedValues(strArr);
        numberPicker.setDescendantFocusability(393216);
        numberPicker.setFocusable(true);
        numberPicker.setValue(r5);
        numberPicker.setBackgroundColor(Color.parseColor("#FFEDD6"));
        numberPicker.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        numberPicker.setOnTouchListener(new View.OnTouchListener() { // from class: com.swenauk.mainmenu.VideoView.38
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (VideoView.this.lastMotion != -1) {
                    if (VideoView.this.lastMotion != 0 || motionEvent.getAction() != 1) {
                        VideoView.this.lastMotion = motionEvent.getAction();
                    } else {
                        if (view instanceof NumberPicker) {
                            int value = ((NumberPicker) view).getValue();
                            textView.setText(strArr[value]);
                            for (int r8 = 0; r8 < VideoView.this.audios.size(); r8++) {
                                if (VideoView.this.audios.keySet().toArray()[r8].equals(strArr[value])) {
                                    VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r8], true);
                                } else {
                                    VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r8], false);
                                }
                            }
                            VideoView.this.alert.cancel();
                        }
                        VideoView.this.lastMotion = -1;
                    }
                } else {
                    VideoView.this.lastMotion = motionEvent.getAction();
                }
                return false;
            }
        });
        numberPicker.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view instanceof NumberPicker) {
                    int value = ((NumberPicker) view).getValue();
                    textView.setText(strArr[value]);
                    for (int r1 = 0; r1 < VideoView.this.audios.size(); r1++) {
                        if (VideoView.this.audios.keySet().toArray()[r1].equals(strArr[value])) {
                            VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r1], true);
                        } else {
                            VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r1], false);
                        }
                    }
                    VideoView.this.alert.cancel();
                }
            }
        });
        builder.setView(numberPicker);
        AlertDialog create = builder.create();
        this.alert = create;
        create.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(this, com.swenauk.seyirturk.R.drawable.alert_dialog_custom_background_white));
        this.alert.show();
        this.alert.getWindow().setLayout(NNTPReply.AUTHENTICATION_REQUIRED, -2);
        if (this.uiModeManager.getCurrentModeType() == 4) {
            numberPicker.requestFocus();
        }
    }

    public void showSubtitlePicker(final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Altyazı Seçenekleri");
        NumberPicker numberPicker = new NumberPicker(this);
        final String[] strArr = (String[]) this.subtitles.keySet().toArray(new String[0]);
        int r5 = 0;
        for (int r4 = 0; r4 < strArr.length; r4++) {
            if (this.subtitles.containsKey(strArr[r4]) && this.subtitles.get(strArr[r4]).booleanValue()) {
                r5 = r4;
            }
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(strArr.length - 1);
        numberPicker.setDisplayedValues(strArr);
        numberPicker.setDescendantFocusability(393216);
        numberPicker.setFocusable(true);
        numberPicker.setValue(r5);
        numberPicker.setBackgroundColor(Color.parseColor("#FFEDD6"));
        numberPicker.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        numberPicker.setOnTouchListener(new View.OnTouchListener() { // from class: com.swenauk.mainmenu.VideoView.40
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (VideoView.this.lastMotion != -1) {
                    if (VideoView.this.lastMotion != 0 || motionEvent.getAction() != 1) {
                        VideoView.this.lastMotion = motionEvent.getAction();
                    } else {
                        if (view instanceof NumberPicker) {
                            int value = ((NumberPicker) view).getValue();
                            textView.setText(strArr[value]);
                            for (int r8 = 0; r8 < VideoView.this.subtitles.size(); r8++) {
                                if (VideoView.this.subtitles.keySet().toArray()[r8].equals(strArr[value])) {
                                    VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r8], true);
                                } else {
                                    VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r8], false);
                                }
                            }
                            VideoView.this.alert.cancel();
                        }
                        VideoView.this.lastMotion = -1;
                    }
                } else {
                    VideoView.this.lastMotion = motionEvent.getAction();
                }
                return false;
            }
        });
        numberPicker.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view instanceof NumberPicker) {
                    int value = ((NumberPicker) view).getValue();
                    textView.setText(strArr[value]);
                    for (int r1 = 0; r1 < VideoView.this.subtitles.size(); r1++) {
                        if (VideoView.this.subtitles.keySet().toArray()[r1].equals(strArr[value])) {
                            VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r1], true);
                        } else {
                            VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r1], false);
                        }
                    }
                    VideoView.this.alert.cancel();
                }
            }
        });
        builder.setView(numberPicker);
        AlertDialog create = builder.create();
        this.alert = create;
        create.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(this, com.swenauk.seyirturk.R.drawable.alert_dialog_custom_background_white));
        this.alert.show();
        this.alert.getWindow().setLayout(NNTPReply.AUTHENTICATION_REQUIRED, -2);
        if (this.uiModeManager.getCurrentModeType() == 4) {
            numberPicker.requestFocus();
        }
    }

    public void showTrackSelections() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(com.swenauk.seyirturk.R.layout.subtitle_popup, (ViewGroup) null, false);
        inflate.measure(0, 0);
        final PopupWindow popupWindow = new PopupWindow(inflate, -1, -1, true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(com.swenauk.seyirturk.R.id.mainDrawer);
        inflate.findViewById(com.swenauk.seyirturk.R.id.restOfView).setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(relativeLayout, 17, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.swenauk.mainmenu.VideoView.43
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                VideoView.this.changeTracks();
                VideoView.this.playerView.requestFocus();
                VideoView.this.playerView.hideController();
                VideoView.this.player.setPlayWhenReady(true);
            }
        });
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-16842910}, new int[]{android.R.attr.state_enabled}}, new int[]{ViewCompat.MEASURED_STATE_MASK, -1});
        RadioGroup radioGroup = (RadioGroup) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupSubsGroup);
        RadioButton[] radioButtonArr = new RadioButton[this.subtitles.size()];
        radioGroup.setOrientation(0);
        radioGroup.setGravity(17);
        int r9 = 0;
        for (int r8 = 0; r8 < this.subtitles.size(); r8++) {
            radioButtonArr[r8] = new RadioButton(this);
            radioButtonArr[r8].setText((String) this.subtitles.keySet().toArray()[r8]);
            radioButtonArr[r8].setTextColor(Color.parseColor("#FFFFFF"));
            radioButtonArr[r8].setButtonTintList(colorStateList);
            radioButtonArr[r8].setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.44
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        ((RadioButton) view).setTextColor(Color.parseColor("#FF6C00"));
                    } else {
                        ((RadioButton) view).setTextColor(-1);
                    }
                }
            });
            radioButtonArr[r8].setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.45
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RadioButton radioButton = (RadioButton) view;
                    for (int r1 = 0; r1 < VideoView.this.subtitles.size(); r1++) {
                        if (VideoView.this.subtitles.keySet().toArray()[r1].equals(radioButton.getText())) {
                            VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r1], true);
                        } else {
                            VideoView.this.subtitles.put((String) VideoView.this.subtitles.keySet().toArray()[r1], false);
                        }
                    }
                }
            });
            HashMap<String, Boolean> hashMap = this.subtitles;
            if (hashMap.get(hashMap.keySet().toArray()[r8]).booleanValue()) {
                r9 = r8;
            }
            radioGroup.addView(radioButtonArr[r8]);
        }
        if (this.audios.size() < 2) {
            ((RelativeLayout) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupAudios)).setVisibility(4);
        } else {
            RadioGroup radioGroup2 = (RadioGroup) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupAudiosGroup);
            RadioButton[] radioButtonArr2 = new RadioButton[this.audios.size()];
            radioGroup2.setOrientation(0);
            radioGroup2.setGravity(17);
            int r10 = 0;
            for (int r5 = 0; r5 < this.audios.size(); r5++) {
                radioButtonArr2[r5] = new RadioButton(this);
                radioButtonArr2[r5].setText((String) this.audios.keySet().toArray()[r5]);
                radioButtonArr2[r5].setTextColor(Color.parseColor("#FFFFFF"));
                radioButtonArr2[r5].setButtonTintList(colorStateList);
                radioButtonArr2[r5].setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.46
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View view, boolean z) {
                        if (z) {
                            ((RadioButton) view).setTextColor(Color.parseColor("#FF6C00"));
                        } else {
                            ((RadioButton) view).setTextColor(-1);
                        }
                    }
                });
                radioButtonArr2[r5].setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.47
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        RadioButton radioButton = (RadioButton) view;
                        for (int r1 = 0; r1 < VideoView.this.audios.size(); r1++) {
                            if (VideoView.this.audios.keySet().toArray()[r1].equals(radioButton.getText())) {
                                VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r1], true);
                            } else {
                                VideoView.this.audios.put((String) VideoView.this.audios.keySet().toArray()[r1], false);
                            }
                        }
                    }
                });
                HashMap<String, Boolean> hashMap2 = this.audios;
                if (hashMap2.get(hashMap2.keySet().toArray()[r5]).booleanValue()) {
                    r10 = r5;
                }
                radioGroup2.addView(radioButtonArr2[r5]);
            }
            radioGroup2.check(radioButtonArr2[r10].getId());
        }
        radioGroup.check(radioButtonArr[r9].getId());
        try {
            radioButtonArr[0].requestFocus();
        } catch (Exception unused) {
            popupWindow.dismiss();
        }
        Button button = (Button) inflate.findViewById(com.swenauk.seyirturk.R.id.subtitlePopupOkay);
        button.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swenauk.mainmenu.VideoView.48
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Button button2 = (Button) view;
                if (z) {
                    button2.setAlpha(0.6f);
                    button2.setTextColor(Color.parseColor("#FF6C00"));
                } else {
                    button2.setAlpha(1.0f);
                    button2.setTextColor(-1);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.swenauk.mainmenu.VideoView.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
    
        r1 = r8.trackSelector;
        r1.setParameters(r1.buildUponParameters().setRendererDisabled(2, true));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void changeTracks() {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swenauk.mainmenu.VideoView.changeTracks():void");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 62 && keyEvent.getAction() == 0) {
            this.player.setPlayWhenReady(!r3.getPlayWhenReady());
            showPermanent(true);
            return true;
        }
        if (keyEvent.getKeyCode() == 67 && keyEvent.getAction() == 0) {
            goBack();
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
