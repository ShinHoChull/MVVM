package com.m2comm.test.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.m2comm.test.R;
import com.m2comm.test.databinding.ActivityMainBinding;
import com.m2comm.test.databinding.ActivityRoomMainBinding;

import java.util.List;

public class RoomMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_main);

        ActivityRoomMainBinding binding = DataBindingUtil.
                setContentView(this , R.layout.activity_room_main);

        //LiveData도 관찰하면서 xml반영이 되게 만들수 있다.
        binding.setLifecycleOwner(this);

        RoomMainViewModel viewModel = new ViewModelProvider(this)
                .get(RoomMainViewModel.class);

        binding.setViewModel(viewModel);

    }




}