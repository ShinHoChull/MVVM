package com.m2comm.test.car_service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.m2comm.test.R;
import com.m2comm.test.main.MainActivity;

import java.text.DateFormat;
import java.util.Date;

public class MyService extends Service {

    public static boolean isRunning;
    private static final String CHANNEL_ID = "channel_loc";

    private LocationManager locationManager;

    private double latitude;
    private double longitude;

    private Handler handler;

    @Override
    public void onCreate() {

        Log.e("=======>Service_Call", "OnCrate");

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        handler = new Handler(getApplicationContext().getMainLooper());

        if (ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 100, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 100, locationListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "gps", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(serviceChannel);
        }

        startForeground(99, getNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("=======>Service_Call", "onStartCommand()");
        handler.postDelayed(runnable, 5000);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final Runnable runnable = () -> {
        Log.e("=======>Service_Call", "Runnable");
        callData();
    };

    public void callData() {

        double dValue = Math.random();

        int iValue = (int)(dValue * 10);
        Log.e("=======>Service", "뽑기 번호->"+iValue);

        if (iValue == 2 || iValue == 3 ) {
            Intent intent = new Intent("kr.co.ctms.carzzang.SIMPLE");
            sendBroadcast(intent);
            stopSelf(99);
        } else {
            handler.postDelayed(runnable, 5000);
        }
    }


    private Notification getNotification() {

        Intent start = new Intent(this, MainActivity.class);
        start.setAction(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_LAUNCHER);
        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0,
                start, PendingIntent.FLAG_UPDATE_CURRENT);

        String time = DateFormat.getDateTimeInstance().format(new Date());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                //.addAction(R.mipmap.ic_launcher, "앱으로 이동", activityPendingIntent)
                .setContentIntent(activityPendingIntent)
                .setContentTitle("실시간 위치 등록 중")
                .setContentText("시작 : " + time)
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(getString(R.string.app_name))
                .setWhen(System.currentTimeMillis());

        return builder.build();
    }


    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String provider = location.getProvider();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.e("=======>Service_Call", " -> LocationUpdateService : " + provider + " : " + latitude + " / " + longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
            if (provider.equals("gps")) {

                latitude = 0;
                longitude = 0;

                Toast toast = Toast.makeText(getApplicationContext(), "[ 실시간 위치 등록 ]\n위치 정보를 가져올 수 없습니다. 위치 서비스를 켜주세요.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    };
}