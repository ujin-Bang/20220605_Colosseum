package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityWriteReplyBinding

class WriteReplyActivity : BaseActivity() {

    lateinit var binding : ActivityWriteReplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_write_reply)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

    }

    override fun setValues() {

    }

}