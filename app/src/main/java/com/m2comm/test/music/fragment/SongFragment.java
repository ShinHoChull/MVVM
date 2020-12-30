package com.m2comm.test.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.m2comm.test.R;
import com.m2comm.test.recyclerview.MyRecyclerAdapter;

import java.util.ArrayList;

public class SongFragment extends Fragment {

    private ArrayList<String> mSongList;

    public static SongFragment newInstance(ArrayList<String> songlist) {

        Bundle args = new Bundle();

        SongFragment fragment = new SongFragment();
        args.putStringArrayList("songNames",songlist);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.song_fragment , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.song_recyclerview);
        if ( getArguments() != null ) {
            this.mSongList = getArguments().getStringArrayList("songNames");
        } else {
            this.mSongList = new ArrayList<>();
        }

        SongAdapter adapter = new SongAdapter();
        recyclerView.setAdapter(adapter);

    }

    private class SongAdapter extends RecyclerView.Adapter<ViewHodler> {

        @NonNull
        @Override
        public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_singer_name , parent , false);

            return new ViewHodler(convertView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
            String songName = mSongList.get(position);
            holder.textView.setText(songName);
        }

        @Override
        public int getItemCount() {
            return mSongList.size();
        }
    }


    public static class ViewHodler extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.singer_name_text);
        }
    }


}
