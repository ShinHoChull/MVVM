package com.m2comm.test.main;

import android.view.View;

import com.m2comm.test.base.BaseViewHolder;
import com.m2comm.test.databinding.ItemTextBinding;


public class MainViewHolder
        extends BaseViewHolder<ItemTextBinding> {

    private final  MainViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void itemClick(int position);
    }

    public MainViewHolder(ItemTextBinding itemTextBinding, MainViewHolder.ClickListener mClickListener) {
        super(itemTextBinding);
        this.mClickListener = mClickListener;
    }


    public void bind(Study row) {
        mBinding.parent.setOnClickListener(v->mClickListener.itemClick(getAdapterPosition()));
        mBinding.fragTextview.setText(row.getName());
        mBinding.memoContent.setVisibility(View.GONE);
    }
}
