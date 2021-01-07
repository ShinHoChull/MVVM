package com.m2comm.test.music;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
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

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {

    final public static String ACTION_PLAY = "play";
    final public static String ACTION_PAUSE = "pause";
    private static final String TAG = MusicService.class.getSimpleName();


    private MediaPlayer mMediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MusicUiController event = intent.getParcelableExtra("obj");

        switch (intent.getAction()) {

            case ACTION_PLAY:
                if ( event != null )playMusic(event);
                break;

            case ACTION_PAUSE:

                break;
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Subscribe
    public void playMusic(final MusicUiController event) {

        Log.d(TAG , "event = "+event.getUri().toString());
        try {
            mMediaPlayer.setDataSource(getApplicationContext() , event.uri);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    if ( mMediaPlayer.isPlaying() ) {
                        /**
                         * {@link com.m2comm.test.music.fragment.MusicControllerFragment#updateUI(MusicUiController)}
                         * */
                        EventBus.getDefault().post(event);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void clickPlayButton ( View v ) {
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

    public boolean isPlaying() {
        boolean isPlay = false;
        if ( mMediaPlayer != null )
            isPlay = mMediaPlayer.isPlaying();

        return isPlay;
    }



}
