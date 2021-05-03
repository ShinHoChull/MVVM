package com.m2comm.test.clone_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.m2comm.test.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private A a;
    private A copy1;
    private A copy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        a = new A();
        a.setAge("17");
        a.setName("신용철");
        ArrayList<A.Data> arr = new ArrayList<>();
        arr.add(new A.Data("abcd"));
        arr.add(new A.Data("ddde"));
        a.setData(arr);

        copy1 = a;

        try {
            copy2 = (A)a.clone();

            copy2.setData((ArrayList<A.Data>)a.getData().clone() );
            for ( int i =0,j= a.getData().size(); i <j ; i++ ) {
                copy2.getData().set(i,(A.Data)a.getData().get(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        copy2.getData().get(0).address = "";

        copy2.setName("허나영");

        Log.d("MainActivity1",copy1.toString());
        Log.d("MainActivity1",copy1.getData().toString());
        Log.d("MainActivity2",copy2.toString());
        Log.d("MainActivity2",copy2.getData().toString());


    }
}