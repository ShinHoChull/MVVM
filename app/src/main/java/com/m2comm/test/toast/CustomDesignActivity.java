package com.m2comm.test.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.utils.MyUtils;

public class CustomDesignActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_design);


    }

    @Override
    public void onClick(View v) {
        MyUtils.showToast(this, "안녕하세요?", Toast.LENGTH_SHORT);
    }
}