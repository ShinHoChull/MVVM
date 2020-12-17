package com.m2comm.test.ListViewTraing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.m2comm.test.R;
import com.m2comm.test.main.MainConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListViewExamActivity extends AppCompatActivity implements View.OnClickListener {
    List<Integer> data;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_exam);

        if ( getIntent().getSerializableExtra("data") != null ) {
            data = (List<Integer>) getIntent().getSerializableExtra("data");
        } else {
            data = new ArrayList<>();
            for (int i = 1 , j = 100 ; i <= j ; i++) {
                data.add(i);
            }
        }

        mAdapter = new MyAdapter(data);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        //데이터 뒤집기
        Collections.reverse(data);
        mAdapter.notifyDataSetChanged();
    }

    private static class MyAdapter extends BaseAdapter {

        private final List<Integer> mData;

        public MyAdapter(List<Integer> data ) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoder viewHoder;

            if ( convertView == null ) {
                viewHoder = new ViewHoder();
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_text , parent , false);

                viewHoder.textView = convertView.findViewById(R.id.fragTextview);
                convertView.setTag(viewHoder);

            } else {
                viewHoder = (ViewHoder) convertView.getTag();
            }
            int data = mData.get(position);
            viewHoder.textView.setText(String.valueOf(data));

            return convertView;
        }

    }

    private static class ViewHoder  {
        TextView textView;
    }



}