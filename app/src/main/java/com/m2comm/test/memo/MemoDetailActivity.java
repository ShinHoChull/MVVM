package com.m2comm.test.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.m2comm.test.R;
import com.m2comm.test.utils.MyUtils;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

public class MemoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private MemoActivity.MemoDTO mdata;
    TextView title, content;

    public static int IMAGE_CHOICE = 999;
    private ImageView mImageView;
    private String imageUri = "";
    private String TAG = MemoDetailActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.memo_title);
        content = findViewById(R.id.memo_content);

        mImageView = findViewById(R.id.scrollingImage);

        //title 없애기
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        if ( intent.getSerializableExtra("data") != null ) {
            mdata = (MemoActivity.MemoDTO) intent.getSerializableExtra("data");
            title.setText(mdata.getTitle());
            content.setText(mdata.getCotent());
            if( mdata.getImageUri() != null ) {
                this.imageUri = mdata.getImageUri();
                Glide.with(this)
                        .load(Uri.parse(mdata.getImageUri()))
                        .into(mImageView);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CHOICE && resultCode == RESULT_OK) {
            if (data != null) {

                Uri imageUri = data.getData();

//                final int takeFlags = getIntent().getFlags()
//                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                getContentResolver().takePersistableUriPermission(imageUri, takeFlags);

                this.imageUri = MyUtils.getRealPath(this , imageUri);
                Glide.with(this).load(imageUri).thumbnail(0.2f).into(mImageView);
            }
        }
    }



    public void onImageClick(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_CHOICE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.memo_saveButton:
                if (title.getText().toString().equals("") ||
                        content.getText().toString().equals("")) {
                    Toast.makeText(this, "공백을 체워주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                mdata = new MemoActivity.MemoDTO (
                        mdata == null ? "" : mdata.getId(),
                        title.getText().toString(),
                        content.getText().toString(),
                        this.imageUri);

                Intent intent = getIntent();
                intent.putExtra("data", mdata);
                setResult(RESULT_OK , intent);
                finish();
                break;

            case R.id.memo_cancelButton:
                finish();
                break;
        }
    }
}