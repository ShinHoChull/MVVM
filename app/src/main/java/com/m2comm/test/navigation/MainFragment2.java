package com.m2comm.test.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m2comm.test.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment2 extends Fragment {


    public MainFragment2() {
        // Required empty public constructor
    }


    public static MainFragment2 newInstance() {
        MainFragment2 fragment = new MainFragment2();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("DDDDDDDDDD","fragment2->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("DDDDDDDDDD","fragment2->onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("DDDDDDDDDD","fragment2->onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("DDDDDDDDDD","fragment2->onDestroy");
    }
}