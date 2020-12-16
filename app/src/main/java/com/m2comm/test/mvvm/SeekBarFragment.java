package com.m2comm.test.mvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.m2comm.test.R;
import com.m2comm.test.databinding.FragmentSeekBarBinding;


public class SeekBarFragment extends Fragment {
    private String TAG = SeekBarFragment.class.getSimpleName();

    public SeekBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seek_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final VMShareViewModel viewModel = new ViewModelProvider(requireActivity()).get(VMShareViewModel.class);
        final FragmentSeekBarBinding binding = DataBindingUtil.bind( view );
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.progress.setValue(progress);
                Log.d(TAG , progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}