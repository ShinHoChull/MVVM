package com.m2comm.test.memo;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.memo.db.MemoContract;
import com.m2comm.test.memo.db.MemoDbHelper;
import com.m2comm.test.memo.db.MemoFacade;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MemoActivity.class.getSimpleName();
    public static int MEMO_WRITE_CODE = 1;
    public static int MEMO_MODIFY_CODE = 2;
    int selectionItem = -1;
    private RecyclerView mListview;
    private MemoRecyclerAdapter memoRecyclerAdapter;
    private List<MemoDTO> mdatas;
    private MemoFacade mMemoFacade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //화면 전환 기능 켜기.
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionSet set = new TransitionSet();
            set.addTransition(new ChangeImageTransform());
            getWindow().setExitTransition(set);
            getWindow().setEnterTransition(set);
        }

        this.mdatas = new ArrayList<>();
        this.mMemoFacade = new MemoFacade(this);

        mListview = findViewById(R.id.memo_listview);
        //DB에서 읽어오기
        this.mdatas = this.mMemoFacade.getMemoAllList();

        memoRecyclerAdapter = new MemoRecyclerAdapter(this.mdatas, this);
        mListview.setAdapter(memoRecyclerAdapter);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(500);
        mListview.setItemAnimator(animator);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemLongClick(final MemoRecyclerAdapter.ItemLongClickEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MemoActivity.this);
        // Add the buttons
        builder.setMessage(R.string.memo_dialog_message)
                .setTitle(R.string.memo_dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MemoDTO memoDTO = mdatas.get(event.position);
                //Where문 > ? 값을 selectionArgs 의 순서대로 참조하여 Cursor에 입력이 됨.
                if ( mMemoFacade.itemDelete(memoDTO.getId()) > 0) {
                    mdatas = mMemoFacade.getMemoAllList();

                    // TODO notifyDataSetChanged 리스트 갱신이 안되네 ...
                    memoRecyclerAdapter.swap(mdatas);
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemClick(MemoRecyclerAdapter.ItemClickEvent event) {
        Intent intent = new Intent(this, MemoDetailActivity.class);
        intent.putExtra("data", mdatas.get(event.position));
        this.selectionItem = event.position;
        //startActivityForResult(intent, MEMO_MODIFY_CODE);
        startActivityForResult(intent, MEMO_MODIFY_CODE ,
                ActivityOptions.makeSceneTransitionAnimation(this , event.view , "image").toBundle());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.memo_writeBt:
                Intent intent = new Intent(this, MemoDetailActivity.class);
                startActivityForResult(intent, MEMO_WRITE_CODE);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        this.mMemoFacade.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MEMO_WRITE_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    MemoDTO row = (MemoDTO) data.getSerializableExtra("data");
                    long newRowId = this.mMemoFacade.insert(row.getTitle(), row.getCotent() , row.ImageUri);
                    if (newRowId == -1) {
                        Toast.makeText(this, "sqlite Error", Toast.LENGTH_SHORT).show();
                    } else {
                        mdatas = this.mMemoFacade.getMemoAllList();
                        memoRecyclerAdapter.insert(mdatas);
                    }
                }
            }
        } else if (requestCode == MEMO_MODIFY_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    MemoDTO row = (MemoDTO) data.getSerializableExtra("data");
                    if ( mMemoFacade.itemUpdate(row.getId() , row.getTitle() , row.getCotent() , row.ImageUri) > 0 ) {
                        mdatas = this.mMemoFacade.getMemoAllList();
                        memoRecyclerAdapter.update(mdatas , this.selectionItem);
                    }
                }
            }
        }

    }


    public static class MemoDTO implements Serializable {
        String id;
        String title;
        String cotent;
        String ImageUri;


        public MemoDTO(String id, String title, String cotent, String imageUri) {
            this.id = id;
            this.title = title;
            this.cotent = cotent;
            ImageUri = imageUri;
        }

        public void setImageUri(String imageUri) {
            ImageUri = imageUri;
        }

        public String getImageUri() {
            return ImageUri;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
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