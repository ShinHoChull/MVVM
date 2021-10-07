package com.m2comm.test.main;

import com.m2comm.test.base.BaseViewHolder;
import com.m2comm.test.databinding.ItemTextBinding;


public class MainViewHolder
        extends BaseViewHolder<ItemTextBinding> {



    public MainViewHolder(ItemTextBinding itemTextBinding) {
        super(itemTextBinding);
    }

    public void bind(String row) {
        mBinding.fragTextview.setText(row);
    }
}
