package com.m2comm.test.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.memo.db.MemoContract;
import com.m2comm.test.memo.db.MemoDbHelper;
import com.m2comm.test.memo.db.MemoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements View.OnClickListener ,
        AdapterView.OnItemClickListener {

    private String TAG = MemoActivity.class.getSimpleName();
    public static int MEMO_WRITE_CODE = 1;
    public static int MEMO_MODIFY_CODE = 2;
    private int selectPosition = -1;
    private ListView mListview;
    private MemoAdapter mAdapter;
    private List<MemoDTO> mdatas;
    private MemoFacade mMemoFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        this.mdatas = new ArrayList<>();
        this.mMemoFacade = new MemoFacade(this);

        //DB에서 읽어오기
        this.mdatas = this.mMemoFacade.getMemoAllList();

        mAdapter = new MemoAdapter(this.mdatas);
        mListview = findViewById(R.id.memo_listview);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent( this , MemoDetailActivity.class );
        selectPosition = position;
        intent.putExtra("data",mdatas.get(position));
        startActivityForResult(intent , MEMO_MODIFY_CODE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.memo_writeBt:
                Intent intent = new Intent( this , MemoDetailActivity.class );
                startActivityForResult(intent , MEMO_WRITE_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == MEMO_WRITE_CODE ) {
            if ( resultCode == RESULT_OK ) {
                if ( data != null ) {
                    MemoDTO row = (MemoDTO)data.getSerializableExtra("data");
                    long newRowId = this.mMemoFacade.insert(row.getTitle() , row.getCotent());
                    if ( newRowId == -1 ) {
                        Toast.makeText(this , "sqlite Error" , Toast.LENGTH_SHORT).show();
                    } else {
                        mdatas = this.mMemoFacade.getMemoAllList();

                    }
                }
            }
        } else if ( requestCode == MEMO_MODIFY_CODE ) {
            if ( resultCode == RESULT_OK ) {
                if ( data != null ) {
                    MemoDTO row = (MemoDTO) data.getSerializableExtra("data");
                    mdatas.get(selectPosition).setTitle(row.getTitle());
                    mdatas.get(selectPosition).setCotent(row.getCotent());
                }
            }
        }
        // TODO notifyDataSetChanged 리스트 갱신이 안되네 ...
        mAdapter = new MemoAdapter(mdatas);
        mAdapter.notifyDataSetChanged();
    }

    public static class MemoAdapter extends BaseAdapter {

        private List<MemoDTO> mdatas;

        public MemoAdapter(List<MemoDTO> mdatas ) {
            this.mdatas = mdatas;
        }

        @Override
        public int getCount() {
            return this.mdatas.size();
        }

        @Override
        public Object getItem(int position) {
            return this.mdatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHodel viewHodel;

            if ( convertView == null ) {
                viewHodel = new ViewHodel();
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_text, parent , false);

                viewHodel.title = convertView.findViewById(R.id.fragTextview);
                viewHodel.content = convertView.findViewById(R.id.memo_content);
                convertView.setTag(viewHodel);
            } else {
                viewHodel = (ViewHodel)convertView.getTag();
            }

            MemoDTO row = mdatas.get(position);
            viewHodel.title.setText(row.getTitle());
            viewHodel.content.setText(row.getCotent());

            return convertView;
        }

        private static class ViewHodel {
            TextView title;
            TextView content;
        }
    }


    public static class MemoDTO implements Serializable {
        String title;
        String cotent;

        public MemoDTO(String title, String cotent) {
            this.title = title;
            this.cotent = cotent;
        }

        public String getTitle() {
            return title;
        }

        public String getCotent() {
            return cotent;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCotent(String cotent) {
            this.cotent = cotent;
        }
    }
}