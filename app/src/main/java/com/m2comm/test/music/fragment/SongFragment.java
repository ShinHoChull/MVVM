package com.m2comm.test.music.fragment;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.m2comm.test.R;
import com.m2comm.test.music.CursorRecyclerViewAdapter;
import com.m2comm.test.music.MusicService;
import com.m2comm.test.music.MyService;
import com.m2comm.test.music.dtos.MusicUiController;

import org.greenrobot.eventbus.EventBus;

public class SongFragment extends Fragment {

    public static SongFragment newInstance() {

        Bundle args = new Bundle();

        SongFragment fragment = new SongFragment();
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

        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ,
                null ,
                null ,
                null ,
                null
        );

        SongAdapter adapter = new SongAdapter(getContext() , cursor);
        recyclerView.setAdapter(adapter);
    }

    private class SongAdapter extends CursorRecyclerViewAdapter<ViewHodler> {

        public SongAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @NonNull
        @Override
        public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_song_name , parent , false);

            return new ViewHodler(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHodler viewHolder, Cursor cursor) {
            Uri uri = null;
            uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ,
                    cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID)));

            final MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(getContext() , uri);
            String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String singerName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            byte[] albumImage = retriever.getEmbeddedPicture();

            viewHolder.title.setText(title);
            viewHolder.singerName.setText(singerName);
            //오디오 Thumbnail Image
            Bitmap bitmap = null;
            if ( albumImage != null ) {
                bitmap = BitmapFactory.decodeByteArray(albumImage ,0 , albumImage.length);
                bitmap = Bitmap.createScaledBitmap(bitmap , 100 , 100 , true);
                viewHolder.imageView.setImageBitmap(bitmap);
            }
            final MusicUiController event = new MusicUiController(uri , title , singerName , bitmap);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //EventBus.getDefault().post(event);
                    Intent intent = new Intent(getContext() , MusicService.class);
                    intent.setAction(MusicService.ACTION_PLAY);
                    intent.putExtra("obj",event);
                    getActivity().startService(intent);
                }
            });


        }
    }


    public static class ViewHodler extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView singerName;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.song_thumnail);
            title = itemView.findViewById(R.id.song_title);
            singerName = itemView.findViewById(R.id.song_singerName);
        }
    }


}
