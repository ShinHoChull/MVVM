package com.m2comm.test.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public class MyUtils {

    public static String getRealPath (Context context , Uri uri ) {
        String strDocId = DocumentsContract.getDocumentId(uri);
        String[] strSplittedDocId = strDocId.split(":");
        String strId = strSplittedDocId[strSplittedDocId.length - 1];

        Cursor crsCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI ,
                new String[] {MediaStore.MediaColumns.DATA},
                "_id=?",
                new String[] {strId},
                null
        );
        crsCursor.moveToFirst();

        return crsCursor.getString(0);
    }

}
