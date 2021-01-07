package com.m2comm.test.music;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;

import com.m2comm.test.music.dtos.MusicUiController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {

    final public static String ACTION_PLAY = "play";
    final public static String ACTION_PAUSE = "pause";
    private static final String TAG = MusicService.class.getSimpleName();

    public MediaPlayer mMediaPlayer;
    private MusicUiController mPlayingMusicObj;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MusicUiController event = intent.getParcelableExtra("obj");

        switch (intent.getAction()) {

            case ACTION_PLAY:
                if ( event != null ) {
                    this.playMusic(event);
                }
                break;

            case ACTION_PAUSE:
                this.clickPlayButton();
                break;
        }

        return START_STICKY;
    }

    public void playMusic(final MusicUiController event) {

        try {
            if ( this.mMediaPlayer == null ) {
                this.mMediaPlayer = new MediaPlayer();
            } else {
                this.mMediaPlayer.stop();
                this.mMediaPlayer.release();
                this.mMediaPlayer = new MediaPlayer();
            }

            this.mMediaPlayer.setAudioStreamType( AudioManager.STREAM_MUSIC );
            this.mMediaPlayer.setDataSource(getApplicationContext() , event.getUri());

            mPlayingMusicObj = event;

            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if ( mMediaPlayer.isPlaying() ) {
                        /**
                         * {@link com.m2comm.test.music.fragment.MusicControllerFragment#updateButton(Boolean)}
                         * */
                        EventBus.getDefault().post(true);
                    }
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mMediaPlayer.prepareAsync();
    }


    public MusicUiController getCurrentMusicObj() {
        return mPlayingMusicObj;
    }


    public void clickPlayButton () {
        if ( mMediaPlayer.isPlaying() ) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }
        /**
         * {@link com.m2comm.test.music.fragment.MusicControllerFragment#updateButton(Boolean)}}
         * */
        EventBus.getDefault().post(mMediaPlayer.isPlaying());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IBinder mBinder = new MusicService.MyBinder();

    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }



}
