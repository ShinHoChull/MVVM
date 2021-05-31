package com.m2comm.test.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m2comm.test.R;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

public class MySection extends Section {


    private final String title;
    private final List<TestVO> list;
    private final ClickListener clickListener;

    private boolean expanded = true;

    public MySection(@NonNull final String title, @NonNull final List<TestVO> list,
                     @NonNull final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.section_ex4_item)
                .headerResourceId(R.layout.section_header)
                .build());

        this.title = title;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return expanded ? list.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;

        final TestVO contact = list.get(position);

        itemHolder.tvItem.setText(String.valueOf(contact.getCount()));
        itemHolder.imgItem.setImageResource(R.drawable.picture);

        itemHolder.rootView.setOnClickListener(v ->
                clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition())
        );
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
        headerHolder.imgArrow.setImageResource(
                expanded ? R.drawable.ic_arrow_drop_up : R.drawable.ic_arrow_drop_down
        );

        headerHolder.rootView.setOnClickListener(v -> clickListener.onHeaderRootViewClicked(this));
    }

    boolean isExpanded() {
        return expanded;
    }

    void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }

    interface ClickListener {
        void onHeaderRootViewClicked(@NonNull final MySection section);
        void onItemRootViewClicked(@NonNull final MySection section, final int itemAdapterPosition);
    }
}
