package com.m2comm.test.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.m2comm.test.R;

public class AnimationActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView = findViewById(R.id.anim_img);
    }

    public void transition(View view) {
        TransitionDrawable transitionDrawable = (TransitionDrawable) imageView.getDrawable();
        transitionDrawable.startTransition(500);
    }

    public void animation1(View view) {
        Animation animation = AnimationUtils.loadAnimation(this , R.anim.transition);
        imageView.startAnimation(animation);
    }


}