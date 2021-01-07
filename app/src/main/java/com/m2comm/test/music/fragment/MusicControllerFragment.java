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
import com.m2comm.test.music.MyIntentService;
import com.m2comm.test.music.MyService;
import com.m2comm.test.music.dtos.MusicUiController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MusicControllerFragment extends Fragment implements View.OnClickListener {

    MyService mService;
    boolean mBound = false;
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mSingerName;
    private Button mPlayButton;
    private boolean mIsPlaying = false;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void updateUI ( MusicUiController event ) {
        mTitle.setText(event.getTitle());
        mSingerName.setText(event.getSingerName());

        //오디오 Thumbnail Image

        if ( event.getBitmap() != null ) {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage ,0 , albumImage.length);
            Glide.with(this).load(event.getBitmap()).into(mImageView);
        }
        this.updateButton(true);
    }

    /**
     * @param isPlaying 하단 버튼 플레이버튼 onOff
     * */
    @Subscribe
    public void updateButton(Boolean isPlaying) {
        this.mIsPlaying = isPlaying;
        if ( isPlaying ) {
            mPlayButton.setText("정지");
        } else {
            mPlayButton.setText("재생");
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

    public void getNumber( View view ) {
        if ( mBound ) {
            Toast.makeText(getContext() , "" + mService.getTime(), Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Bind가 성공하면 call
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
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
        if (this.mBound) {
            getActivity().unbindService(mConnection);
            this.mBound = false;
        }
    }

    @Override
    public void onClick(View v) {
        /**
         * {@link com.m2comm.test.music.MusicService#clickPlayButton(View)}
         * */
        EventBus.getDefault().post(v);
    }
}
