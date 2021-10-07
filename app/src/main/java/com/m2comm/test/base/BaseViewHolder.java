package com.m2comm.test.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

abstract public class BaseViewHolder<
        A extends ViewDataBinding
        >
        extends RecyclerView.ViewHolder {

    protected A mBinding;

    public BaseViewHolder(A a) {
        super(a.getRoot());
        mBinding = a;
    }

}
