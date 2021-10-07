package com.m2comm.test.main;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.m2comm.test.R;
import com.m2comm.test.base.BaseAdapter;
import com.m2comm.test.base.BaseViewHolder;
import com.m2comm.test.databinding.ItemTextBinding;

import java.util.ArrayList;

public class MainAdapter
        extends BaseAdapter<
        ItemTextBinding
        , MainViewHolder
        > {

    private final ArrayList<String> mArr;

    public MainAdapter(ArrayList<String> mArr) {
        this.mArr = mArr;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_text;
    }

    @Override
    protected MainViewHolder getViewHolder(ItemTextBinding binding) {
        return new MainViewHolder(binding);
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
