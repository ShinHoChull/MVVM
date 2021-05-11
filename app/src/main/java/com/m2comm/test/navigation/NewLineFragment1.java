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
import android.widget.Toast;

import com.m2comm.test.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewLineFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewLineFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewLineFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewLineFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static NewLineFragment1 newInstance(String param1, String param2) {
        NewLineFragment1 fragment = new NewLineFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("DDDDDDDDDD","newfragment1->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("DDDDDDDDDD","newfragment1->onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("DDDDDDDDDD","newfragment1->onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("DDDDDDDDDD","newfragment1->onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_line1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bt = view.findViewById(R.id.new_button_1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_newLineFragment1_to_newLineFragment2);
            }
        });
    }
}