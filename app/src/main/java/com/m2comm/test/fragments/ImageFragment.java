package com.m2comm.test.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m2comm.test.R;

public class ImageFragment extends Fragment {

    private OnImageTouchListener mListener;
    private ImageView mImageView;

    interface OnImageTouchListener {
        void onImageTouch(ImageView view , String message);
    }

    public void setOnImageTouchLister( OnImageTouchListener listener ) {
        this.mListener = listener;
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mListener != null )
                mListener.onImageTouch(mImageView , "hello");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test , container , false);
        mImageView = view.findViewById(R.id.testFragmentImage);

        return view;
    }
}
