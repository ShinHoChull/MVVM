package com.m2comm.test.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m2comm.test.R;


public class LifeCycleFragment extends Fragment {

    //빈 생성자가 필요함 , 파라미터를 가질 수 없다 ( 액티비의 라이프사이클에 맞춰 동작하기 위해! )
    public LifeCycleFragment() {
        // Required empty public constructor
    }

    //액티비티에 붙을 때
    @Override
    public void onAttach(@NonNull Context context) {
        //context : 액티비티
        super.onAttach(context);
    }

    //프래그먼트가 생성될 때
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // savedInstanceState 로 복원 처리 가능
        super.onCreate(savedInstanceState);
    }

    //액티비티의 onCreate()에 해당 : 레이아웃 완성
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //onAttach & onCreate 에서 들어오는 매개변수 context = inflater / savedInstanceState
        //가 모두 있어서 궂이 사용하지 않는다.

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    //액티비티가 실제로 생선된 직후 호출
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //뷰 소멸
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //프래그먼트 소멸
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //액티비티에서 떨어짐.
    @Override
    public void onDetach() {
        super.onDetach();
    }
}