package com.m2comm.test.music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.m2comm.test.R;
import com.m2comm.test.music.fragment.PlayerFragment;
import com.m2comm.test.music.fragment.SingerListViewFragment;
import com.m2comm.test.music.fragment.SongFragment;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity {

    private PlayerFragment mPlayerFragment;
    private SingerListViewFragment mSingerFragment;
    private SongFragment mSongFragment;

    private String TAG = MusicPlayerActivity.class.getSimpleName();
    ArrayList<String> singerNameArr;
    ArrayList<String> songNameArr;

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


        this.songNameArr = new ArrayList<>();
        this.songNameArr.add("쟤가걔야 - 마마무");
        this.songNameArr.add("서시 - 신성우");
        this.songNameArr.add("Y - 프리스타일");
        this.mSongFragment = SongFragment.newInstance(this.songNameArr);

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