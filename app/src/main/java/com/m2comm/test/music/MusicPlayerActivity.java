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
import android.view.View;

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