package com.m2comm.test.memo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.m2comm.test.memo.MemoActivity;

import java.util.ArrayList;
import java.util.List;

public class MemoFacade {
    private MemoDbHelper mDbHelper;
    private Context context;

    public MemoFacade(Context context) {
        this.context = context;
        this.mDbHelper = new MemoDbHelper(context);
    }

    /**
     * 메모를 추가한다
     *
     * @param title   제목
     * @param content 내용
     */
    public long insert(String title, String content) {
        //gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
        values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENT, content);

        return db.insert(MemoContract.MemoEntry.TABLE_NAME, null, values);
    }

    /**
     * 전체 메모 리스트
     *
     * @return 전체 메모
     * */
    public List<MemoActivity.MemoDTO> getMemoAllList () {

        List<MemoActivity.MemoDTO> memoDTOS = new ArrayList<>();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                MemoContract.MemoEntry._ID,
                MemoContract.MemoEntry.COLUMN_NAME_TITLE,
                MemoContract.MemoEntry.COLUMN_NAME_CONTENT
        };

        //Where문 > ? 값을 selectionArgs 의 순서대로 참조하여 Cursor에 입력이 됨.
        String selection = MemoContract.MemoEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                MemoContract.MemoEntry._ID + " DESC";

        Cursor cursor = db.query(
                MemoContract.MemoEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        if ( cursor != null ) {

            while(cursor.moveToNext()) {
                String title = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                MemoContract.MemoEntry.COLUMN_NAME_TITLE));

                String cotent = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                MemoContract.MemoEntry.COLUMN_NAME_CONTENT));

                MemoActivity.MemoDTO row = new MemoActivity.MemoDTO(title , cotent);

                memoDTOS.add(row);
            }
            cursor.close();
        }

        return memoDTOS;
    }

}
