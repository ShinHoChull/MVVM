package com.m2comm.test.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.m2comm.test.R;

public class ImageFragmentActivity extends AppCompatActivity implements ImageFragment.OnImageTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fragment);

        ImageFragment fragment = (ImageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.imageFrag);

        fragment.setOnImageTouchLister(this);
    }

    @Override
    public void onImageTouch(ImageView view, String message) {
        Toast.makeText(this , "Hello?",Toast.LENGTH_SHORT).show();
    }
}