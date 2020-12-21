package com.m2comm.test.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        data = new ArrayList<>();
        for ( int i = 1 , j = 100; i <= j ; i++) {
            data.add(String.valueOf(i));
        }

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

    }


    /**
     * EventBus 에서 보내는 이벤트 수신하는 콜백 메서드
     * @param event
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemClick(MyRecyclerAdapter.ItemClickEvent event) {
        Toast.makeText(this , ""+ data.get(event.position) , Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //EventBus에 구독자로 현재 액티비트 추가
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //EventBus에 구독자를 뺌.
        EventBus.getDefault().unregister(this);
    }
}