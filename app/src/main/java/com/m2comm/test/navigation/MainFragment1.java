package com.m2comm.test.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.m2comm.test.R;

public class MainFragment1 extends Fragment {

    public MainFragment1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainFragment1 newInstance() {
        MainFragment1 fragment = new MainFragment1();
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
        return inflater.inflate(R.layout.fragment_main1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bt = view.findViewById(R.id.frag_button1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_mainFragment12_to_mainFragment22);
            }
        });

        Button bt2 = view.findViewById(R.id.frag_button2);
        bt2.setOnClickListener(v->{
            //Navigation.findNavController(v).navigate(R.id.action_mainFragment1_to_include_nav12);
        });
    }

    private void init() {

    }

    public int numberPlus(int num1 , int num2) {
        return num1 + num2;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("DDDDDDDDDD","fragment1->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("DDDDDDDDDD","fragment1->onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("DDDDDDDDDD","fragment1->onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("DDDDDDDDDD","fragment1->onDestroy");
    }
}