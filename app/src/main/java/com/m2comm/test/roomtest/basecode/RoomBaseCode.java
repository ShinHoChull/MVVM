package com.m2comm.test.roomtest.basecode;

import android.opengl.ETC1;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.m2comm.test.R;
import com.m2comm.test.roomtest.AppDatabase;
import com.m2comm.test.roomtest.Todo;
import com.m2comm.test.roomtest.TodoDao;
import com.m2comm.test.utils.Custom_SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoomBaseCode extends AppCompatActivity {

    private BaseCodeAppDatabase mDB;
    private String mData = "{'data':[{'CODE':'027','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'27','GROUP_ID':'BANK','NAME':'한국씨티은행 ','ETC3':'씨티'},{'CODE':'089','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'89','GROUP_ID':'BANK','NAME':'케이뱅크','ETC3':'케이뱅크'},{'CODE':'090','DISPLAY_INDEX':0,'ETC1':'N','ETC2':null,'GROUP_ID':'BANK','NAME':'카카오뱅크','ETC3':'카카오뱅크'},{'CODE':'071','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'71','GROUP_ID':'BANK','NAME':'우체국 ','ETC3':'우체국'},{'CODE':'020','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'20','GROUP_ID':'BANK','NAME':'우리은행 ','ETC3':'우리'},{'CODE':'048','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'48','GROUP_ID':'BANK','NAME':'신협중앙회','ETC3':'신협'},{'CODE':'088','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'88','GROUP_ID':'BANK','NAME':'신한은행','ETC3':'신한'},{'CODE':'045','DISPLAY_INDEX':0,'ETC1':'N','ETC2':'45','GROUP_ID':'BANK','NAME':'새마을금고중앙회','ETC3':'새마을금고'},{'CODE':'011','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'11','GROUP_ID':'BANK','NAME':'농협은행 ','ETC3':'농협'},{'CODE':'003','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'03','GROUP_ID':'BANK','NAME':'기업은행 ','ETC3':'기업'},{'CODE':'004','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'04','GROUP_ID':'BANK','NAME':'국민은행','ETC3':'국민'},{'CODE':'023','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'23','GROUP_ID':'BANK','NAME':'SC제일은행 ','ETC3':'SC제일'},{'CODE':'005','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'05','GROUP_ID':'BANK','NAME':'KEB하나은행(구, 외환은행)','ETC3':'KEB(구하나)'},{'CODE':'081','DISPLAY_INDEX':0,'ETC1':'Y','ETC2':'81','GROUP_ID':'BANK','NAME':'KEB하나은행 ','ETC3':'KEB하나'},{'CODE':'ERP','DISPLAY_INDEX':0,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'ERP','ETC3':null},{'CODE':'USER','DISPLAY_INDEX':1,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'USER','ETC3':null},{'CODE':'SYSTEM','DISPLAY_INDEX':2,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'SYSTEM','ETC3':null},{'CODE':'GOVERNMENT','DISPLAY_INDEX':3,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'GOVERNMENT','ETC3':null},{'CODE':'B2B','DISPLAY_INDEX':4,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'B2B','ETC3':null},{'CODE':'ISO','DISPLAY_INDEX':5,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'ISO','ETC3':null},{'CODE':'ETC','DISPLAY_INDEX':6,'ETC1':null,'ETC2':null,'GROUP_ID':'CODE_TYPE','NAME':'ETC','ETC3':null}],'updateVersion':'1','resultCode':'1'}";
    private JSONArray mJsonArray;
    private String mVersion = "1";
    private Custom_SharedPreferences mCsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_base_code);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DBAsyncTask(mDB.baseCodeDAO(), "getData").execute();

            }
        });

        init();

        try {
            initJson();
        } catch (JSONException e) {
            Log.e("initJsonError", "initJsonError->>" + e.toString());
        }
        initRoom();
    }

    private void init() {
        mCsp = new Custom_SharedPreferences(this);
    }

    private void initJson() throws JSONException {
        JSONObject json = new JSONObject(mData);
        mJsonArray = json.getJSONArray("data");
    }


    private void initRoom() {
        mDB = Room.databaseBuilder(getApplication(), BaseCodeAppDatabase.class,
                "base_code.db").build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            versionCheck();
        } catch (JSONException e) {
            Log.e("RoomBaseCode",
                    "RoomBaseCode#OnResume->>" + e.toString());
        }
    }

    private void versionCheck() throws JSONException {
        if (!mCsp.getValue("version", "").equals(mVersion)) {
            Log.e("RoomBaseCode",
                    "versionCheck_Inner=" + mJsonArray.length());

            new DBAsyncTask(mDB.baseCodeDAO(), "allDelete").execute();

        }
    }

    private void insertData() throws JSONException {
        Log.e("RoomBaseCode",
                "insertData");
        for (int i = 0, j = mJsonArray.length(); i < j; i++) {
            JSONObject row = (JSONObject) mJsonArray.get(i);
            new DBAsyncTask(mDB.baseCodeDAO(), "insert").execute(
                    new BaseCodeVO(
                            row.getString("CODE"),
                            row.getInt("DISPLAY_INDEX"),
                            row.getString("GROUP_ID"),
                            row.getString("NAME"),
                            row.getString("ETC1"),
                            row.getString("ETC2"),
                            row.getString("ETC3")
                    )
            );
        }
        mCsp.put("version", mVersion);
    }




    private class DBAsyncTask extends AsyncTask<BaseCodeVO, Void, Void> {

        private BaseCodeDAO mBaseCodeDAO;
        private String mStatus;
        private List<BaseCodeVO> itemLists;

        public DBAsyncTask(BaseCodeDAO baseCodeDAO, String status) {
            this.mBaseCodeDAO = baseCodeDAO;
            this.mStatus = status;
        }

        @Override
        protected Void doInBackground(BaseCodeVO... baseCodeVOS) {

            if (mStatus.equals("insert")) {
                mBaseCodeDAO.insert(baseCodeVOS[0]);
            } else if (mStatus.equals("allDelete")){
                mBaseCodeDAO.allDelete();
            } else if (mStatus.equals("getData")) {
                itemLists = mBaseCodeDAO.getCodeList("BANK");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mStatus.equals("allDelete")) {
                try {
                    insertData();
                } catch (JSONException e) {
                    Log.d("allDeleteError==>", "JSONException");
                    e.printStackTrace();
                }
            } else if (mStatus.equals("insert")){
                //Log.d("SelectAsyncTask=>", "bascodeSize=>" + mBaseCodeDAO.getAllList().size());
            } else if (mStatus.equals("getData")) {
                Log.d("SelectAsyncTask=>", "itemListSize=>" + itemLists.size());
            }

        }
    }

}