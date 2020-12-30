package com.m2comm.test.music.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m2comm.test.R;
import com.m2comm.test.music.MyIntentService;
import com.m2comm.test.music.MyService;

public class MusicControllerFragment extends Fragment {

    MyService mService;
    boolean mBound = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_controller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button before_song_bt = view.findViewById(R.id.before_song);
        before_song_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyIntentService.class);
                getActivity().startService(intent);
            }
        });

        view.findViewById(R.id.next_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyService.class);
                getActivity().bindService(intent, mConnection, getActivity().BIND_AUTO_CREATE);
            }
        });

        view.findViewById(R.id.play_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNumber(v);
            }
        });
    }

    public void getNumber( View view ) {
        if ( mBound ) {
            Toast.makeText(getContext() , "" + mService.getTime(), Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Bind가 성공하면 call
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //강제 종료시 호출됨.
            mBound = false;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mBound) {
            getActivity().unbindService(mConnection);
            this.mBound = false;
        }
    }
}
