package com.m2comm.test.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;

import java.io.Serializable;
import java.util.List;

public class MemoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private MemoActivity.MemoDTO mdata;
    TextView title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        title = findViewById(R.id.memo_title);
        content = findViewById(R.id.memo_content);

        Intent intent = getIntent();
        if ( intent.getSerializableExtra("data") != null ) {
            mdata = (MemoActivity.MemoDTO) intent.getSerializableExtra("data");
            title.setText(mdata.getTitle());
            content.setText(mdata.getCotent());
        }

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

                mdata = new MemoActivity.MemoDTO(title.getText().toString(),
                        content.getText().toString());

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