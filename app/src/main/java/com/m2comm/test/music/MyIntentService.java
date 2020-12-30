package com.m2comm.test.music;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //순차적으로 실행 된다
        //worker 스레드 실행된다 .

       for ( int i = 0 ; i < 10; i++ ) {
           try {
               Thread.sleep(1000);
               Log.d(TAG , "onHandleIntent : "+i);
           } catch (Exception e) {
                e.printStackTrace();
           }

       }
    }

}