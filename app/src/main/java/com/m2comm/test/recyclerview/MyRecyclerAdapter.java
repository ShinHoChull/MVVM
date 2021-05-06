package com.m2comm.test.recyclerview;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.m2comm.test.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<TestVO> dataList;
    private Context mContext;

    public MyRecyclerAdapter(List<TestVO> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    /**
     * EventBus 에서 발송할 이벤트
     * */
    public static class ItemClickEvent {
        View view;
        int position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_test , parent , false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        TestVO row = dataList.get(position);

        holder.textView.setText(dataList.get(position).getCount()+"");

        byte[] imageAsBytes = Base64.decode(row.getImageURL(), Base64.DEFAULT);

        Glide.with(mContext)
                .load(imageAsBytes)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(
                                Glide.with(mContext).load(R.mipmap.ic_launcher)
                        )
                .into(holder.imageView);

        //holder.imageView.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row.setCount(row.getCount()+1);

                //EventsBus를 통해 이벤트 발송
                ItemClickEvent event = new ItemClickEvent();
                event.view = holder.itemView;
                event.position = position;
                EventBus.getDefault().post(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.item_text);
            this.imageView = itemView.findViewById(R.id.item_image);
        }
    }

}
