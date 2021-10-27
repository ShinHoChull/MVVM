package com.m2comm.test.car_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.m2comm.test.R;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.setUpInit();
        this.setUpBroadCastReciver();
        this.appVersionCheck("20210831_1.0.1");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ActivityLifeCycle", "=========> onResume()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);

        Intent intent = new Intent("com.m2comm.test.car_service.MyService");
        intent.setPackage("com.m2comm.test");
        stopService(intent);

        Log.e("ActivityLifeCycle", "=========> onDestroy()");
    }

    private void setUpBroadCastReciver() {
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("kr.co.ctms.carzzang.SIMPLE");
        registerReceiver(mBroadcastReceiver, filter);
    }

    private void setUpInit() {

        findViewById(R.id.button).setOnClickListener(v->{
            Intent intent = new Intent("com.m2comm.test.car_service.MyService");
            intent.setPackage("com.m2comm.test");
            startService(intent);
        });

        findViewById(R.id.memory_button).setOnClickListener(v->{
            ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            Log.d("Main", "메모리->"+manager.getMemoryClass());
        });

    }

    private void appVersionCheck(String version) {
        String pickVersion = "20210831_1.0.2";
        Log.e("Main", " -> 현재 APP 버전 : " + pickVersion + " ==== 수신 버전 : " + version);

        if (version.contains(pickVersion)) {
            Log.e("Main", " -> including Version " + pickVersion);
        } else {
            if (version.contains("A")) {
                Log.e("Main", " -> UPDATE GO ");
            } else {
                Log.e("Main", " -> B including Version " + pickVersion);
            }
        }
    }




}