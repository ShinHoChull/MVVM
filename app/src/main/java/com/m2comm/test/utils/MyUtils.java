package com.m2comm.test.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;

public class MyUtils {

    public static int sum(int a , int b) {
        return a + b;
    }

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

    public static void showToast (Context context, String message , int duration) {
        Toast result = new Toast(context);

        View v = LayoutInflater.from(context).inflate(R.layout.custom_toast , null);
        TextView customToast = v.findViewById(R.id.custom_toast_text);
        customToast.setText(message);

        result.setView(v);
        result.setDuration(duration);
        result.setGravity(Gravity.CENTER_HORIZONTAL , 0 , -300);
        result.show();
    }

}
