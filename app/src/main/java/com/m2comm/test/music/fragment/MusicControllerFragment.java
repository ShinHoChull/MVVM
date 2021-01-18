package com.m2comm.test.music.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.m2comm.test.R;
import com.m2comm.test.music.MusicPlayerActivity;
import com.m2comm.test.music.MusicService;
import com.m2comm.test.music.MyIntentService;
import com.m2comm.test.music.MyService;
import com.m2comm.test.music.dtos.MusicUiController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MusicControllerFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MusicControllerFragment.class.getSimpleName();
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mSingerName;
    private Button mPlayButton;

    MusicService mMusicService;
    boolean mBound = false;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity() , MusicService.class);
        getActivity().bindService(intent , mConnection , Context.BIND_AUTO_CREATE);

    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        if (this.mBound) {
            getActivity().unbindService(mConnection);
            this.mBound = false;
        }
        if ( mMusicService != null && mMusicService.mMediaPlayer != null && mMusicService.mMediaPlayer.isPlaying() ) {
            mMusicService.showNotification();
        }
    }

    private void updateUI ( MusicUiController event ) {
        if ( event == null ) return;
        mTitle.setText(event.getTitle());
        mSingerName.setText(event.getSingerName());

        //오디오 Thumbnail Image
        if ( event.getBitmap() != null ) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage ,0 , albumImage.length);
            Glide.with(this).load(event.getBitmap()).into(mImageView);
        }
    }

    @Subscribe
    public void updateButton(Boolean isPlaying) {
        if ( mBound ) {
            mPlayButton.setText(this.mMusicService.mMediaPlayer.isPlaying()?"정지" : "재생");
            updateUI(mMusicService.getCurrentMusicObj());
        }
    }

    public void callFragment() {
        if (mBound) {
            this.updateUI(mMusicService.getCurrentMusicObj());

            //상단 노티 제거
            if ( mMusicService != null && mMusicService.mMediaPlayer != null && mMusicService.mMediaPlayer.isPlaying() ) {
                mMusicService.hideNotification();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_controller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mImageView = view.findViewById(R.id.music_controller_albumImage);
        this.mTitle = view.findViewById(R.id.music_controller_title);
        this.mSingerName = view.findViewById(R.id.music_controller_singer_name);
        this.mPlayButton = view.findViewById(R.id.play_song);
        this.mPlayButton.setOnClickListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Bind가 성공하면 call
            MusicService.MyBinder binder = (MusicService.MyBinder) service;
            mMusicService = binder.getService();
            mBound = true;
            callFragment();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //강제 종료시 호출됨.
            mBound = false;
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG , "onDestroy");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity() , MusicService.class);
        intent.setAction(MusicService.ACTION_PAUSE);
        getActivity().startService(intent);
    }
}
