package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityViewReplyDetailBinding

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var bindiing: ActivityViewReplyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindiing = DataBindingUtil.setContentView(this,R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }

}