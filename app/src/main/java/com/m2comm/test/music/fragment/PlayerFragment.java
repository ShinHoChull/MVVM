package com.m2comm.test.music.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.m2comm.test.R;
import com.m2comm.test.music.MusicService;
import com.m2comm.test.music.dtos.MusicUiController;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerFragment extends Fragment {

    private static final String TAG = PlayerFragment.class.getSimpleName();
    private MusicService mMusicService;
    private boolean mBound = false;
    private ImageView mImageView;
    private TextView mStartTime;
    private TextView mEndTime;
    private SeekBar mSeekBar;

    private Timer mTimer;
    private TimerTask mTimerTask;

    public static PlayerFragment newInstance() {

        Bundle args = new Bundle();

        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void changeUpdateUI(MusicUiController event) {
        if (event == null) return;

        mTimer = new Timer();
        Glide.with(getContext()).load(event.getBitmap()).into(mImageView);
        final int longDuration = Integer.parseInt(event.getDuration());
        int min = longDuration / 1000 / 60;
        int sec = longDuration / 1000 % 60;

        //초로 변경하여서 MAX 값 변경해주기.
        mSeekBar.setMax(longDuration);

        mEndTime.setText(String.format(Locale.KOREA, "%d:%02d", min, sec));

        mTimerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                int currentPosition = mMusicService.mMediaPlayer.getCurrentPosition();
                if ( longDuration <= currentPosition ) mTimer.cancel();
                mSeekBar.setProgress(currentPosition);

                final int min = currentPosition / 1000 / 60;
                final int sec = currentPosition / 1000 % 60;
                mStartTime.post(new Runnable() {
                    @Override
                    public void run() {
                        mStartTime.setText(String.format(Locale.KOREA, "%d:%02d", min, sec));
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask,0 ,1000);
    }


    public void callFragment() {
        if (mBound) {
            this.changeUpdateUI(mMusicService.getCurrentMusicObj());
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_player_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = view.findViewById(R.id.fragment1_img);
        mStartTime = view.findViewById(R.id.music_startTime);
        mEndTime = view.findViewById(R.id.music_endTime);
        mSeekBar = view.findViewById(R.id.music_seekbar);
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
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
        }

        if ( mTimer != null ) mTimer.cancel();
    }
}