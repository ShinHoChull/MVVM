package com.m2comm.test.car_service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.m2comm.test.R
import com.m2comm.test.base.BaseActivity
import com.m2comm.test.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {


    override fun getLayout(): Int = R.layout.activity_second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}