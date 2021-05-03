package com.m2comm.test.roomtest.addr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.m2comm.test.R;
import com.m2comm.test.roomtest.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddrActivity extends AppCompatActivity {

    private ADDRAppDatabase mDb;
    private List<ADDR_VZZ> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr);

        mDb = Room.databaseBuilder(getApplicationContext(),
                ADDRAppDatabase.class, "ADDR.DB")
                .createFromAsset("ADDR.DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Log.e("AddrActivity=", "AddrArraySize=" + mDb.addrDao().getAll().size());
        //

//        mDb = Room.databaseBuilder(getApplicationContext(), ADDRAppDatabase.class,
//                "addr.db")
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build();



    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}