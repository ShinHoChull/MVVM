package com.m2comm.test.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.base.BaseActivity;
import com.m2comm.test.coordinatorlayout.ScrollingActivity;
import com.m2comm.test.databinding.ActivityMainBinding;
import com.m2comm.test.databinding.ActivityMainListBinding;
import com.m2comm.test.fragments.ViewPagerActivity;
import com.m2comm.test.joystick.JoystickMain;
import com.m2comm.test.memo.MemoActivity;
import com.m2comm.test.ml_kit.MlkitActivity;
import com.m2comm.test.music.MusicPlayerActivity;
import com.m2comm.test.navigation.NavigationMainActivity;
import com.m2comm.test.toast.CustomDesignActivity;

import java.util.ArrayList;


public class MainActivity extends BaseActivity<ActivityMainListBinding> implements MainConstants.View ,
        View.OnClickListener , MainViewHolder.ClickListener {

    MainConstants.Presenter mainPresenter;
    MainAdapter mMainAdapter;
    private int saveNum = -1;
    ArrayList<Study> arr;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("onSave1=>", "save1");
        outState.putInt("saveNum",1);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("onSave2=>", "save2");
        this.saveNum = savedInstanceState.getInt("saveNum");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            this.saveNum = savedInstanceState.getInt("saveNum");
        }  else {
            Log.e("onSave3=>", "savedInstanceState Null");
        }

        this.mainPresenter = new MainPresenter(this);
        this.setUpData();
        this.setUpList();
    }

    private void setUpData() {
        arr = new ArrayList<>();

        arr.add(new Study("carService", com.m2comm.test.car_service.MainActivity.class));
        arr.add(new Study("LottoProgram", MlkitActivity.class));
        arr.add(new Study("CustomToast", CustomDesignActivity.class));
        arr.add(new Study("memo",MemoActivity.class));
        arr.add(new Study("music",MusicPlayerActivity.class) );
        arr.add(new Study("navigation",NavigationMainActivity.class) );
        arr.add(new Study("joystick",JoystickMain.class) );
        arr.add(new Study("fragments",ViewPagerActivity.class) );
        arr.add(new Study("coordinator_layout",ScrollingActivity.class) );

    }

    private void setUpList() {
        mMainAdapter = new MainAdapter(arr , this);
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

    @Override
    public void itemClick(int position) {

        Study row = arr.get(position);
        startActivity(new Intent(this ,row.getCls()));

    }
}

class Study {
    private String name;
    private Class cls;

    public Study(String name, Class cls) {
        this.name = name;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}