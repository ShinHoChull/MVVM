package com.m2comm.test.mvvm;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.m2comm.test.R;
import com.m2comm.test.databinding.ActivityCalculaterMainBinding;

public class CalculaterMainActivity extends AppCompatActivity {

    ActivityCalculaterMainBinding mBinding;
    CalculaterViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = DataBindingUtil.setContentView(this , R.layout.activity_calculater_main );
        this.mBinding.setLifecycleOwner(this);

        final CalculaterViewModel viewModel = new ViewModelProvider( this ).get(CalculaterViewModel.class);
        mBinding.setViewmodel(viewModel);

    }





}