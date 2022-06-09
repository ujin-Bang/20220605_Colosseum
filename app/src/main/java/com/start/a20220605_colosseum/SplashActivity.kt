package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

    }

}