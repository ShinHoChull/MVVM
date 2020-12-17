package com.m2comm.test.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m2comm.test.R;

public class ColorFragment extends Fragment {

    private static final String TAG = ColorFragment.class.getSimpleName();
    private int mColor = Color.RED;
    private String mText = "";
    //fragment 생성시 필수
    public ColorFragment() {
    }

    public static ColorFragment newInstance(int color) {

        Bundle args = new Bundle();

        ColorFragment fragment = new ColorFragment();
        fragment.setColor(color);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_color , container, false);
        view.setBackgroundColor(mColor);
        TextView tv = view.findViewById(R.id.fragText);
        tv.setText(mText);

        return view;
    }

    public void setText (String text) {
        this.mText = text;
    }

    public void setColor(int color) {
        mColor = color;
    }

}
