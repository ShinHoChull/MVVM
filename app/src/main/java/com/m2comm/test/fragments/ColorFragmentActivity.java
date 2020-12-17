package com.m2comm.test.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.m2comm.test.R;

import java.util.Random;

public class ColorFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorFragment colorFrag;
    private ColorFragment colorFrag2;
    private String TAG = ColorFragmentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_fragment);

        //첫번째 프래그먼트 가져와서 캐스팅 후 백그라운드 조작
        colorFrag =  (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.color_frag);
        //colorFrag.setColor(Color.BLUE);


        //두번째. 동적 추가.
        colorFrag2 =  new ColorFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container , colorFrag2 ).commit();

    }

    @Override
    public void onClick(View v) {

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int mColor = Color.argb (255,red,green,blue);

        ColorFragment newColorFragment = new ColorFragment();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.container , newColorFragment).commit();

        newColorFragment.setColor(mColor);

    }
}