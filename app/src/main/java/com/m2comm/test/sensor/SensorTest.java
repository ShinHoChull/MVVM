package com.m2comm.test.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.m2comm.test.R;
import com.m2comm.test.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorTest extends AppCompatActivity implements SensorEventListener {


    private static final String TAG = SensorTest.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mLightSensor;

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(SensorTest.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(SensorTest.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        if ( mLightSensor != null ) {
            TedPermission.with(this)
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("이기능은 외부 저장소에 접근 하지않으면 안됨.")
                    .setDeniedMessage("설정 메뉴에서 권한은 변경할 수 있음.")
                    .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .check();

        } else {
            Toast.makeText(this , "이기기는 조도센서가 없습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener( this , mLightSensor , SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        getSupportActionBar().setTitle("조도="+lux);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}