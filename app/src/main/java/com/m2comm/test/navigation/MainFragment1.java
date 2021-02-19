package com.m2comm.test.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

        MainFragment1Directions.ActionMainFragment1ToMainFragment2 action =
                MainFragment1Directions.actionMainFragment1ToMainFragment2("Hello");

        Button bt = view.findViewById(R.id.frag_button1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(action);
            }
        });


    }
}