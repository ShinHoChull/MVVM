package com.m2comm.test.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B mBinding;

    protected abstract @LayoutRes
    int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpInit();
    }

    protected void setUpInit() {
        mBinding = DataBindingUtil.setContentView(this, getLayout());
    }


}
