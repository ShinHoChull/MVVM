package com.m2comm.test.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.m2comm.test.R;

public class NavigationMainActivity extends AppCompatActivity {

    AppBarConfiguration mAppBarConfiguration;
    NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        mNavController = Navigation.findNavController(this , R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this , mNavController , mAppBarConfiguration);

    }


    @Override
    public boolean onSupportNavigateUp() {
        //mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}