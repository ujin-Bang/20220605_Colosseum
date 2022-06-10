package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityViewReplyDetailBinding
import com.start.a20220605_colosseum.datas.ReplyData

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var bindiing: ActivityViewReplyDetailBinding

    lateinit var mReplyData : ReplyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindiing = DataBindingUtil.setContentView(this,R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mReplyData = intent.getSerializableExtra("reply") as ReplyData

        bindiing.txtSelectedSide.text = "(${mReplyData.selectedSide.title})"
        bindiing.txtWriterNickname.text = mReplyData.writer.nickname
        bindiing.txtContent.text = mReplyData.content

    }

}