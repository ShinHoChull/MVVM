package com.m2comm.test.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final List<String> dataList;

    public MyRecyclerAdapter(List<String> dataList) {
        this.dataList = dataList;
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
                .inflate(android.R.layout.simple_list_item_1 , parent , false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textView.setText(dataList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(android.R.id.text1);
        }
    }

}
