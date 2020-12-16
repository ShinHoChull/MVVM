package com.m2comm.test.mvvm;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculaterViewModel extends ViewModel {

    public MutableLiveData<Integer> counter = new MutableLiveData<>();

    public CalculaterViewModel() {
        counter.setValue(0);
    }

    public void increase() {
        counter.setValue(counter.getValue() + 1);
    }

    public void decrease() {
        counter.setValue(counter.getValue() - 1);
    }








}
