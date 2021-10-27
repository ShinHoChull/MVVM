package com.m2comm.test.car_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("kr.co.ctms.carzzang.SIMPLE".equals(intent.getAction())) {

            String dpJobNo = intent.getStringExtra("dpJobNo");
            Log.e("Receive_POD_JOB_NO", " -> 간편배차 브로드캐스트 수신 dpJobNo : " + dpJobNo);

        }

    }
}
