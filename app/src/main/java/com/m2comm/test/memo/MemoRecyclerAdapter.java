package com.m2comm.test.memo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.m2comm.test.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MemoRecyclerAdapter extends  RecyclerView.Adapter<MemoRecyclerAdapter.ViewHolder>{

    private static final String TAG = MemoRecyclerAdapter.class.getSimpleName();
    private List<MemoActivity.MemoDTO> mdatas;
    private Context mContext;

    public MemoRecyclerAdapter(List<MemoActivity.MemoDTO> mdatas , Context context) {
        this.mdatas = mdatas;
        this.mContext = context;
    }

    public void swap (List<MemoActivity.MemoDTO> newMemoList) {
        mdatas = newMemoList;
        notifyDataSetChanged();
    }

    public void insert (List<MemoActivity.MemoDTO> newMemoList ) {
        mdatas = newMemoList;
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View converView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text , parent , false);
        return new ViewHolder(converView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        MemoActivity.MemoDTO row = this.mdatas.get(position);
        holder.title.setText(row.getTitle());
        holder.content.setText(row.getCotent());

        if ( row.getImageUri() != null && !row.getImageUri().equals("") ) {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(this.mContext)
                    .load(Uri.parse(row.getImageUri()))
                    .thumbnail(0.3f)
                    .into(holder.imageView);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemClickEvent event = new ItemClickEvent(holder.itemView , position);
                EventBus.getDefault().post(event);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ItemLongClickEvent event = new ItemLongClickEvent(holder.itemView , position);
                EventBus.getDefault().post(event);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mdatas.size();
    }

    public static class ItemClickEvent {
        View view;
        int position;


        public ItemClickEvent(View view, int position) {
            this.view = view;
            this.position = position;
        }
    }


    public static class ItemLongClickEvent {
        View view;
        int position;

        public ItemLongClickEvent(View view, int position) {
            this.view = view;
            this.position = position;
        }
    }

    public void update(List<MemoActivity.MemoDTO> memoDTOList , int position) {
        mdatas = memoDTOList;
        notifyItemChanged(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        ImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.fragTextview);
            this.content = itemView.findViewById(R.id.memo_content);
            this.imageView = itemView.findViewById(R.id.fragImageView);
        }
    }

}
