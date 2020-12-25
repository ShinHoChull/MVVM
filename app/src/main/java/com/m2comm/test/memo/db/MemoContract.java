package com.m2comm.test.memo.db;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;

public final class MemoContract {

    /*
    * CREATE TABLE memo
    * (
    *   _id Integer primary key autoincrement,
    *   title text,
    *   content text
    *   imageUri text
    * );
    * */
    public static final String SQL_CREATE_MEMO_TABLE =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s TEXT);",
                    MemoEntry.TABLE_NAME,
                    MemoEntry._ID,
                    MemoEntry.COLUMN_NAME_TITLE,
                    MemoEntry.COLUMN_NAME_CONTENT,
                    MemoEntry.COLUMN_NAME_IMAGE_URI);


    private  MemoContract() {
    }

    public static class MemoEntry implements BaseColumns {

        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_IMAGE_URI = "image_uri";

    }
}
