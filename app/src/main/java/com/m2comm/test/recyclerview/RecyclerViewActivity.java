package com.m2comm.test.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.m2comm.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class RecyclerViewActivity extends AppCompatActivity implements MySection.ClickListener {
    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    List<TestVO> data;
    MyRecyclerAdapter adapter;

    Map<String , List<TestVO>> mListMap;
    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        sectionedAdapter = new SectionedRecyclerViewAdapter();


        mListMap = initList();

        for (final Map.Entry<String, List<TestVO>> entry : mListMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                sectionedAdapter.addSection(new MySection(entry.getKey(), entry.getValue(), this));
            }
        }

        recyclerView.setAdapter(sectionedAdapter);

        final boolean isExpanded = false;

        for (int i = 0, j = sectionedAdapter.getSectionCount(); i < j; i++) {
            MySection section = (MySection) sectionedAdapter.getSection(i);
            section.setExpanded(isExpanded);
        }

    }


    @Override
    public void onHeaderRootViewClicked(@NonNull @NotNull MySection section) {
        final SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(section);

        // store info of current section state before changing its state
        final boolean wasExpanded = section.isExpanded();
        final int previousItemsTotal = section.getContentItemsTotal();

        section.setExpanded(!wasExpanded);
        sectionAdapter.notifyHeaderChanged();

        if (wasExpanded) {
            sectionAdapter.notifyItemRangeRemoved(0, previousItemsTotal);
        } else {
            sectionAdapter.notifyAllItemsInserted();
        }
    }

    @Override
    public void onItemRootViewClicked(@NonNull @NotNull MySection section, int itemAdapterPosition) {
        final SectionAdapter inSectionAdapter = sectionedAdapter.getAdapterForSection(section);


        //섹션 인덱스값 구하기.
        Log.d(TAG, "sectionPosition=" + sectionedAdapter.getSectionIndex(section));

        //아이템 인덱스 값구하기.
        Log.d(TAG, "itemPosition=" + inSectionAdapter.getPositionInSection(itemAdapterPosition));

        if (mListMap != null) {
            //Key Name 구하기.
            String key = (new ArrayList<>(mListMap.keySet())).get(sectionedAdapter.getSectionIndex(section));
            int itemIndex = inSectionAdapter.getPositionInSection(itemAdapterPosition);
            TestVO testVO = mListMap.get(key).get(itemIndex);
            Toast.makeText(this, String.valueOf(testVO.getCount()), Toast.LENGTH_SHORT).show();
        }

    }

    private Map<String , List<TestVO>> initList() {

        Map<String, List<TestVO>> map = new LinkedHashMap<>();

        data = new ArrayList<>();
        data.add(new TestVO("",1));
        data.add(new TestVO("",2));
        data.add(new TestVO("",3));
        data.add(new TestVO("",4));
        map.put("A",data);

        data = new ArrayList<>();
        data.add(new TestVO("",41));
        data.add(new TestVO("",22));
        data.add(new TestVO("",33));
        data.add(new TestVO("",41));
        map.put("B",data);

        data = new ArrayList<>();
        data.add(new TestVO("",51));
        data.add(new TestVO("",52));
        data.add(new TestVO("",53));
        data.add(new TestVO("",51));
        map.put("C",data);

        data = new ArrayList<>();
        data.add(new TestVO("",511));
        data.add(new TestVO("",5122));
        data.add(new TestVO("",533));
        data.add(new TestVO("",531));
        map.put("D",data);

        return map;
    }


    /**
     * EventBus 에서 보내는 이벤트 수신하는 콜백 메서드
     * @param event
     * */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemClick(MyRecyclerAdapter.ItemClickEvent event) {

        adapter.notifyDataSetChanged();
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