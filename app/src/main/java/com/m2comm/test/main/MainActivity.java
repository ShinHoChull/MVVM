package com.m2comm.test.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.m2comm.test.R;
import com.m2comm.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainConstants.View , View.OnClickListener {

    private ActivityMainBinding binding;
    MainConstants.Presenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.mainPresenter = new MainPresenter(this);
        this.init();
    }

    private void objReg() {
        this.binding.bt0.setOnClickListener(this);
        this.binding.bt1.setOnClickListener(this);
        this.binding.bt2.setOnClickListener(this);
        this.binding.bt3.setOnClickListener(this);
        this.binding.bt4.setOnClickListener(this);
        this.binding.bt5.setOnClickListener(this);
        this.binding.bt6.setOnClickListener(this);
        this.binding.bt7.setOnClickListener(this);
        this.binding.bt8.setOnClickListener(this);
        this.binding.bt9.setOnClickListener(this);

        this.binding.plusBt.setOnClickListener(this);
        this.binding.minusBt.setOnClickListener(this);


    }

    private void init() {
        this.objReg();

    }


    @Override
    public void showResult(int result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.plusBt:

                //this.mainPresenter.plus();
                break;

            case R.id.bt0:
                break;
            case R.id.bt1:
                break;
            case R.id.bt2:
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
            case R.id.bt5:
                break;
            case R.id.bt6:
                break;
            case R.id.bt7:
                break;
            case R.id.bt8:
                break;
            case R.id.bt9:
                break;
        }
    }
}