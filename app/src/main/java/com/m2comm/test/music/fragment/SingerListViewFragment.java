package com.m2comm.test.music.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m2comm.test.R;

import java.util.ArrayList;
import java.util.List;

public class SingerListViewFragment extends Fragment {

    private static final String TAG = SingerListViewFragment.class.getSimpleName();
    private ArrayList<String> mSingerNames;
    public static SingerListViewFragment newInstance(ArrayList<String> singerNames) {

        Bundle args = new Bundle();
        SingerListViewFragment fragment = new SingerListViewFragment();
        args.putStringArrayList("singerNames",singerNames);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.singer_fragment , container , false);
        ListView listView = view.findViewById(R.id.singer_listview);

        if ( getArguments() != null ) {
            this.mSingerNames =  getArguments().getStringArrayList("singerNames");
        } else {
            this.mSingerNames = new ArrayList<>();
        }

        SingerListViewAdapter adapter = new SingerListViewAdapter();
        listView.setAdapter(adapter);

        return view;
    }

    private class SingerListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mSingerNames.size();
        }

        @Override
        public Object getItem(int position) {
            return mSingerNames.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if( convertView == null ) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_singer_name , parent , false);
                viewHolder.textView = convertView.findViewById(R.id.singer_name_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(mSingerNames.get(position));

            return convertView;
        }

        private class ViewHolder {
            TextView textView;
        }

    }



}
