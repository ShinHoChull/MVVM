package com.m2comm.test.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.m2comm.test.main.MainViewHolder;

public abstract class BaseAdapter<
        A extends ViewDataBinding,
        B extends RecyclerView.ViewHolder
        >
        extends RecyclerView.Adapter<B> {


    protected abstract @LayoutRes
    int getLayout();


    protected abstract B getViewHolder(A binding);


    @NonNull
    @Override
    public B onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        A binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext())
                , getLayout(), parent
                , false);

        return getViewHolder(binding);
    }
}
