package com.m2comm.test.provider.fragment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m2comm.test.R;

import java.io.IOException;
import java.io.InputStream;

public class GalleryFragment  extends Fragment {

    private static final String TAG = GalleryFragment.class.getSimpleName();
    private GridView mGridView;

    public GalleryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gallery , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //뷰
        this.mGridView = view.findViewById(R.id.gallery_gridview);
        //사진 정보

        //미디어 (사진 , 동영상 , 음악 ) media db

        //provider로 media db 정보를 가져와야 됨.
        Cursor cursor = getActivity().getContentResolver()
                .query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

        //사진을 뿌릴 어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(getContext() , cursor , true);
        this.mGridView.setAdapter(adapter);
    }

    private static class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            View convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_gallery, parent , false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.frag_item_img_view);
            convertView.setTag(viewHolder);

            return convertView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            Uri photoUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ,cursor.getString(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));

            //TODO : 이미지가 변경이 안되네....
            viewHolder.imageView.setImageURI(photoUri);
        }

    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
