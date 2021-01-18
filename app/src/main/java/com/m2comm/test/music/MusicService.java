package com.m2comm.test.music;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.media.MediaBrowserServiceCompat;

import com.m2comm.test.R;
import com.m2comm.test.music.dtos.MusicUiController;
import com.m2comm.test.music.fragment.PlayerFragment;

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

    public void hideNotification() {
        stopForeground(true);
    }
    /**
    * 백그라운드는 강제 킬 대상이다. ( 메모리 부족으로 인한. )
     * 서비스라도 포그라운드로 올려줘야 앱이 죽지 않음.  startForeground
    * */
    RemoteViews mRemoteViews;
    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.CHANNEL_DEFAULT_IMPORTANCE));
       // builder.setContentTitle(mPlayingMusicObj.getTitle());
       // builder.setContentText(mPlayingMusicObj.getSingerName());
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent stopIntent = new Intent(this , MusicService.class );
        stopIntent.setAction(MusicService.ACTION_PAUSE);
        PendingIntent stopPendingIntent = PendingIntent.getService(this ,
                1 , stopIntent , PendingIntent.FLAG_CANCEL_CURRENT );

        //Notification Custom 하기.
        mRemoteViews = new RemoteViews(getPackageName() , R.layout.remote_view);
        mRemoteViews.setOnClickPendingIntent(R.id.noti_stopBt,stopPendingIntent);
        mRemoteViews.setImageViewBitmap(R.id.img,mPlayingMusicObj.getBitmap());
        mRemoteViews.setTextViewText(R.id.noti_stopBt,mMediaPlayer.isPlaying() ? "정지" : "재생");
        //아래와 같은식으로 값을 가져와서 수정함.
        //remoteViews.setTextViewText(R.id.notifyLocation, location);
        //remoteViews.setTextViewText(R.id.notifyWeather, "날씨 : " + curWfKor + "," + curTemp);

        builder.setContent(mRemoteViews);

       // Bitmap bitmap = BitmapFactory.decodeResource (
        //           getResources() , R.mipmap.ic_launcher
       // );
        //builder.setLargeIcon(bitmap);

        //알림을 클릭하면 수행될 인텐트
        Intent resultIntent = new Intent(this , MusicPlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,
                resultIntent , PendingIntent.FLAG_UPDATE_CURRENT );

        builder.setContentIntent(pendingIntent);

        // 노티 클릭하면 지우기.
        builder.setAutoCancel(true);

        // 기본 알림
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(uri);

        //진동
        builder.setVibrate(new long[]{100,200,300});
//
//        Intent stopIntent = new Intent(this , MusicService.class );
//        stopIntent.setAction(MusicService.ACTION_PAUSE);
//        PendingIntent stopPendingIntent = PendingIntent.getService(this ,
//                1 , stopIntent , PendingIntent.FLAG_CANCEL_CURRENT );

        //액션
//        builder.addAction(R.mipmap.ic_launcher , "중지",stopPendingIntent);
//        builder.addAction(R.mipmap.ic_launcher , "다음곡",pendingIntent);
//        builder.addAction(R.mipmap.ic_launcher , "이전곡",pendingIntent);

        //알림표시
        startForeground(1 , builder.build());

    }


    public MusicUiController getCurrentMusicObj() {
        return mPlayingMusicObj;
    }


    public void clickPlayButton () {
        if ( mMediaPlayer.isPlaying() ) {
            mMediaPlayer.pause();
            /**
             * {@link PlayerFragment#stopMediaPlay(PlayerFragment.Player)}
             * */
            EventBus.getDefault().post(new PlayerFragment.Player(PlayerFragment.PlayerEnum.A));
        } else {
            mMediaPlayer.start();
            /**
             * {@link PlayerFragment#stopMediaPlay(PlayerFragment.Player)}
             * */
            EventBus.getDefault().post(new PlayerFragment.Player(PlayerFragment.PlayerEnum.B));
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
