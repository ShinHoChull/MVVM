package com.m2comm.test.music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.m2comm.test.R;
import com.m2comm.test.music.dtos.MusicUiController;
import com.m2comm.test.music.fragment.PlayerFragment;
import com.m2comm.test.music.fragment.SingerListViewFragment;
import com.m2comm.test.music.fragment.SongFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity {

    private String TAG = MusicPlayerActivity.class.getSimpleName();

    private PlayerFragment mPlayerFragment;
    private SingerListViewFragment mSingerFragment;
    private SongFragment mSongFragment;

    ArrayList<String> singerNameArr;

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        TabLayout tabLayout = findViewById(R.id.music_tabLayout);
        ViewPager viewPager = findViewById(R.id.music_viewPager);

        this.singerNameArr = new ArrayList<>();
        this.singerNameArr.add("신용철");
        this.singerNameArr.add("허나영");
        this.singerNameArr.add("홍서준");
        this.mSingerFragment = SingerListViewFragment.newInstance(this.singerNameArr);

        this.mPlayerFragment = new PlayerFragment();
        this.mSongFragment = SongFragment.newInstance();

        tabLayout.setupWithViewPager(viewPager);

        MusicPlayerPagerAdapter adapter = new MusicPlayerPagerAdapter(getSupportFragmentManager(),0);
        viewPager.setAdapter(adapter);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void playMusic(final MusicUiController event) {

        try {
            mMediaPlayer.setDataSource(getApplicationContext() , event.uri);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    if ( mMediaPlayer.isPlaying() ) {
                        /**
                         * {@link com.m2comm.test.music.fragment.MusicControllerFragment#updateUI(MediaMetadataRetriever)}
                         * */
                        EventBus.getDefault().post(event.retriever);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isPlaying() {
        boolean isPlay = false;
        if ( mMediaPlayer != null )
        isPlay = mMediaPlayer.isPlaying();

        return isPlay;
    }

    private class MusicPlayerPagerAdapter extends FragmentPagerAdapter {

        public MusicPlayerPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d(TAG , "tabPosition="+position);
            switch (position) {
                case 0:
                    return mPlayerFragment;
                case 1:
                    return mSingerFragment;
                case 2:
                    return mSongFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "플레이어";
                case 1:
                    return "아티스트";
                case 2:
                    return "노래";
            }
            return null;
        }
    }

}