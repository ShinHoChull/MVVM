package com.m2comm.test.navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.m2comm.test.R;

import java.util.Objects;

public class NavigationMainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    AppBarConfiguration mAppBarConfiguration;
    NavController mNavController;
    BottomNavigationView mBottom;
    View mView;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        mView = findViewById(R.id.rootView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mBottom = findViewById(R.id.bottom_nav);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph()).build();

        //NavigationUI.setupActionBarWithNavController(this , mNavController , mAppBarConfiguration);


        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mBottom, mNavController);
        mNavController.addOnDestinationChangedListener(this);


        mBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mainFragment1) {
                    mNavController.navigate(R.id.include_nav1);
                } else if (item.getItemId() == R.id.newLineFragment1) {
                    mNavController.navigate(R.id.include_nav2);
                } else if (item.getItemId() == R.id.depth3Fragment) {
                    mNavController.navigate(R.id.include_nav3);
                }
                return true;
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination,
                                     @Nullable Bundle arguments) {

        Log.d("onDestination", "destinatioinName=>" + destination.getLabel());


    }
}