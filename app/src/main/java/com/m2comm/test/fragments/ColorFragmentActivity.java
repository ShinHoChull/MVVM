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

    }

    @Override
    public void onClick(View v) {

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int mColor = Color.argb (255,red,green,blue);

        ColorFragment newColorFragment = new ColorFragment();

        switch (v.getId()) {
            case R.id.fragBt1:
                this.addFragment( R.id.container1 , newColorFragment,"No.1" ,mColor );
                break;

            case R.id.fragBt2:
                this.addFragment( R.id.container2 , newColorFragment ,"No.2" ,mColor);
                break;

            case R.id.fragBt3:
                this.addFragment( R.id.container3 , newColorFragment ,"No.3" ,mColor);
                break;
        }

    }

    private void addFragment (int id , ColorFragment cf , String str , int color) {
        getSupportFragmentManager().beginTransaction().
                replace( id , cf).commit();
        cf.setText(str);
        cf.setColor(color );
    }
}