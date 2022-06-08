package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.start.a20220605_colosseum.databinding.ActivityViewTopicDetailBinding
import com.start.a20220605_colosseum.datas.TopicData
import com.start.a20220605_colosseum.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding

    lateinit var mTopicData : TopicData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {

        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.txtTopicTitle.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopic)

//        현재 진행상황 조회 API 호출해보자. -> 토론진영 목록/ 몇표 획득
        getTopicDetailFromSever()
    }
    fun getTopicDetailFromSever(){

        ServerUtil.getRequestTopicDetail(mContext, mTopicData.id, object :ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                topicObj -> 토론 주제에 대한 정보가 담긴 JSONObject ->TopicData변환 함수로 재료로 사용.
                mTopicData = TopicData.getTopicDataFromJson(topicObj)

                runOnUiThread {
                    refreshUI()
                }
            }

        })
    }

    fun refreshUI(){
//        mTopicData가 변경되었으면 -> 새로 반영해달라.
        binding.txtTopicTitle.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.imgTopic)

    }

}