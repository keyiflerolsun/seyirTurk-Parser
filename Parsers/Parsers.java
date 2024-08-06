package com.swenauk.mainmenu.Parsers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AlertDialog;
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
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.swenauk.mainmenu.VideoView;
import com.swenauk.seyirturk.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class Parsers {
    protected AlertDialog alert;
    public AsyncTask<String, String, String> backGround;
    public BandwidthMeter bandwidthMeter;
    public Context calledContext;
    protected DataSource.Factory dataSourceFactory;
    protected int height;
    protected Boolean isFirst;
    protected Boolean isFragman;
    public String mainUrlForReferer;
    protected MediaSource mediaSource;
    protected ExoPlayer player;
    public String streamUrl;
    public Map<String, String> streamUrls;
    public DefaultTrackSelector trackSelector;
    protected Uri videoUri;

    protected void parse(String str) {
    }

    protected void showVideo() {
    }

    public Parsers(String str, Context context, ExoPlayer exoPlayer) {
        this.streamUrls = new HashMap();
        this.mainUrlForReferer = "";
        this.isFragman = false;
        this.calledContext = context;
        this.player = exoPlayer;
        this.isFirst = true;
        this.player.addListener(new Player.Listener() { // from class: com.swenauk.mainmenu.Parsers.Parsers.1
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
            public void onPlaybackStateChanged(int r3) {
                if (r3 == 3 && Parsers.this.isFirst.booleanValue()) {
                    Parsers.this.isFirst = false;
                    if ((Parsers.this.calledContext instanceof VideoView) && !((VideoView) Parsers.this.calledContext).isDestroyed() && !((VideoView) Parsers.this.calledContext).isFinishing() && ((VideoView) Parsers.this.calledContext).buffer.isShowing()) {
                        ((VideoView) Parsers.this.calledContext).buffer.cancel();
                        ((VideoView) Parsers.this.calledContext).showSubAndVoice();
                    }
                }
                if (r3 != 4 || !(Parsers.this.calledContext instanceof VideoView) || ((VideoView) Parsers.this.calledContext).isDestroyed() || ((VideoView) Parsers.this.calledContext).isFinishing() || Parsers.this.player.isCurrentMediaItemLive()) {
                    return;
                }
                ((VideoView) Parsers.this.calledContext).goBack();
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

            @Override // com.google.android.exoplayer2.Player.Listener
            public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                if (!(Parsers.this.calledContext instanceof VideoView) || ((VideoView) Parsers.this.calledContext).isDestroyed() || ((VideoView) Parsers.this.calledContext).isFinishing() || mediaMetadata.title == null) {
                    return;
                }
                ((VideoView) Parsers.this.calledContext).setMetadata(mediaMetadata.title.toString());
            }
        });
        parse(str);
    }

    public Parsers(String str, Context context, ExoPlayer exoPlayer, String str2) {
        this.streamUrls = new HashMap();
        this.mainUrlForReferer = "";
        this.isFragman = false;
        this.calledContext = context;
        this.player = exoPlayer;
        this.isFirst = true;
        if (!str.contains("context")) {
            this.mainUrlForReferer = str2;
        }
        this.player.addListener(new Player.Listener() { // from class: com.swenauk.mainmenu.Parsers.Parsers.2
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
            public void onPlaybackStateChanged(int r3) {
                if (r3 == 3 && Parsers.this.isFirst.booleanValue()) {
                    Parsers.this.isFirst = false;
                    if ((Parsers.this.calledContext instanceof VideoView) && !((VideoView) Parsers.this.calledContext).isDestroyed() && !((VideoView) Parsers.this.calledContext).isFinishing() && ((VideoView) Parsers.this.calledContext).buffer.isShowing()) {
                        ((VideoView) Parsers.this.calledContext).buffer.cancel();
                        ((VideoView) Parsers.this.calledContext).showSubAndVoice();
                    }
                }
                if (r3 != 4 || !(Parsers.this.calledContext instanceof VideoView) || ((VideoView) Parsers.this.calledContext).isDestroyed() || ((VideoView) Parsers.this.calledContext).isFinishing() || Parsers.this.player.isCurrentMediaItemLive()) {
                    return;
                }
                ((VideoView) Parsers.this.calledContext).goBack();
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
        parse(str);
    }

    public void cancelBackground() {
        this.backGround.cancel(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showAlert() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Parsers.3
            @Override // java.lang.Runnable
            public void run() {
                if (!(Parsers.this.calledContext instanceof VideoView) || ((VideoView) Parsers.this.calledContext).isFinishing() || ((VideoView) Parsers.this.calledContext).isDestroyed()) {
                    return;
                }
                ((VideoView) Parsers.this.calledContext).showAlert();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showBuffer() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Parsers.4
            @Override // java.lang.Runnable
            public void run() {
                if (!(Parsers.this.calledContext instanceof VideoView) || ((VideoView) Parsers.this.calledContext).isDestroyed() || ((VideoView) Parsers.this.calledContext).isFinishing()) {
                    return;
                }
                ((VideoView) Parsers.this.calledContext).bufferAlertToggle();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void prepareVideo() {
        try {
            if (this.streamUrl.startsWith("http")) {
                this.videoUri = Uri.parse(this.streamUrl);
                Context context = this.calledContext;
                this.dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "iFrame"));
                Context context2 = this.calledContext;
                if ((context2 instanceof VideoView) && ((Activity) context2).isFinishing()) {
                    return;
                }
                showVideo();
                return;
            }
            showBuffer();
            showAlert();
        } catch (Exception unused) {
            showBuffer();
            showAlert();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void playVideo() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swenauk.mainmenu.Parsers.Parsers.5
            @Override // java.lang.Runnable
            public void run() {
                Parsers.this.player.prepare(Parsers.this.mediaSource, false, true);
                if (Parsers.this.calledContext instanceof VideoView) {
                    if (Parsers.this.mediaSource instanceof HlsMediaSource) {
                        ((VideoView) Parsers.this.calledContext).isHLS = true;
                    }
                    ((VideoView) Parsers.this.calledContext).setVideoUri(Parsers.this.videoUri);
                    try {
                        if (!Parsers.this.isFragman.booleanValue()) {
                            System.out.println("Deneme");
                            final long parseInt = Integer.parseInt(((VideoView) Parsers.this.calledContext).mili);
                            System.out.println("Mili is " + parseInt);
                            if (parseInt > 0) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Parsers.this.calledContext, R.style.AlertDialog);
                                builder.setTitle("Video Nerden Başlasın");
                                builder.setNegativeButton("Baştan", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Parsers.5.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r2) {
                                        Parsers.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.setPositiveButton("Kaldığım Yerden", new DialogInterface.OnClickListener() { // from class: com.swenauk.mainmenu.Parsers.Parsers.5.2
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int r4) {
                                        Parsers.this.player.seekTo(parseInt);
                                        Parsers.this.player.setPlayWhenReady(true);
                                    }
                                });
                                builder.create().show();
                            } else {
                                Parsers.this.player.setPlayWhenReady(true);
                            }
                        } else {
                            Parsers.this.player.setPlayWhenReady(true);
                        }
                    } catch (Exception unused) {
                        Parsers.this.player.setPlayWhenReady(true);
                    }
                }
            }
        });
    }
}
