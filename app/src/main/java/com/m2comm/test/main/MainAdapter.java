package com.m2comm.test.main;

import android.content.Context;

import androidx.annotation.NonNull;

import com.m2comm.test.R;
import com.m2comm.test.base.BaseAdapter;
import com.m2comm.test.databinding.ItemTextBinding;

import java.util.ArrayList;

public class MainAdapter
        extends BaseAdapter<
        ItemTextBinding
        , MainViewHolder
        > {

    private final  MainViewHolder.ClickListener mClickListener;


    private final ArrayList<Study> mArr;

    public MainAdapter(
            ArrayList<Study> mArr
            , MainViewHolder.ClickListener mClickListener) {

        this.mClickListener = mClickListener;
        this.mArr = mArr;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_text;
    }

    @Override
    protected MainViewHolder bindViewHolder(ItemTextBinding binding) {
        return new MainViewHolder(binding, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.bind(mArr.get(position));
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }
}
