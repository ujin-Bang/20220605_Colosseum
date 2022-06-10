package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.adapters.ReReplyAdapter
import com.start.a20220605_colosseum.databinding.ActivityViewReplyDetailBinding
import com.start.a20220605_colosseum.datas.ReplyData
import com.start.a20220605_colosseum.utils.ServerUtil
import org.json.JSONObject

class ViewReplyDetailActivity : BaseActivity() {

    lateinit var bindiing: ActivityViewReplyDetailBinding

    val mReReplyList = ArrayList<ReplyData>()
    lateinit var mReReplyAdapter : ReReplyAdapter

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

        getReplyDetailFromServer()

        mReReplyAdapter = ReReplyAdapter(mContext, R.layout.rereply_list_item, mReReplyList)
        bindiing.reReplyListView.adapter = mReReplyAdapter

    }

    fun getReplyDetailFromServer(){

        ServerUtil.getRequestReplyDetail(mContext, mReplyData.id, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val replyObj = dataObj.getJSONObject("reply")
                val repliesArr = replyObj.getJSONArray("replies")

                mReReplyList.clear()

                for(i in 0 until  repliesArr.length()){
//                    [] => {JSONObject}추출 -> ReplyData로 변환 -> 대댓글 목록에 추가(최종목표)
                    mReReplyList.add( ReplyData.getReplyDataFromJson( repliesArr.getJSONObject(i)) )
                }

                runOnUiThread {

                    mReReplyAdapter.notifyDataSetChanged()
                }
            }

        })
    }

}