package com.m2comm.test.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.m2comm.test.R;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),0);
        this.mViewPager = findViewById(R.id.viewPager_pager);
        this.mViewPager.setAdapter(adapter);

        mTabLayout = findViewById(R.id.viewPager_tabLayout);
        mTabLayout.setupWithViewPager(this.mViewPager);

    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ColorFragment.newInstance(Color.RED);
                case 1:
                    return ColorFragment.newInstance(Color.BLACK);
                case 2:
                    return ColorFragment.newInstance(Color.BLUE);
                case 3:
                    return ColorFragment.newInstance(Color.CYAN);
                case 4:
                    return ColorFragment.newInstance(Color.MAGENTA);
                default:
                    return ColorFragment.newInstance(Color.GREEN);
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "빨강";
                case 1:
                    return "검정";
                case 2:
                    return "파랑";
                case 3:
                    return "민트";
                case 4:
                    return "분홍";
                default:
                    return "???";
            }
        }


    }
}