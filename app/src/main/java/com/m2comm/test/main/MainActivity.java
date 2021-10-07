package com.m2comm.test.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.base.BaseActivity;
import com.m2comm.test.databinding.ActivityMainBinding;
import com.m2comm.test.databinding.ActivityMainListBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainListBinding> implements MainConstants.View ,
        View.OnClickListener {

    MainConstants.Presenter mainPresenter;
    MainAdapter mMainAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mainPresenter = new MainPresenter(this);
        this.setUpListener();

    }

    private void setUpListener() {

        ArrayList<String> arr = new ArrayList<>();
        arr.add("hello1");
        arr.add("hello2");
        arr.add("hello3");

        mMainAdapter = new MainAdapter(arr);
        mBinding.list.setAdapter(mMainAdapter);
    }


    @Override
    public void showResult(int result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }
}