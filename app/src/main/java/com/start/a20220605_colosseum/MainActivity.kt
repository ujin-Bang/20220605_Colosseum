package com.start.a20220605_colosseum

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.adapters.TopicAdapter
import com.start.a20220605_colosseum.databinding.ActivityMainBinding
import com.start.a20220605_colosseum.datas.TopicData
import com.start.a20220605_colosseum.utils.ServerUtil
import okhttp3.internal.notify
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mTopicList = ArrayList<TopicData>()

    lateinit var mTopicAdapter : TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        binding.topicListView.setOnItemClickListener { parent, view, position, id ->

            val clickedTopicData = mTopicList[position]

            val myIntent = Intent(mContext, ViewTopicDetailActivity::class.java)
            myIntent.putExtra("topic",clickedTopicData)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
//        연습 - 내 정보 API 호출 -> 닉네임 추출 /UI 반영
//        getMyInfoFromServer()

//        실제 - 메인 화면의 데이터 받아오기 -> 토론 주제 목록 ->리스트뷰로 표기
        getTopicListFromSever()

//        어댑터 객체화 / 리스트뷰의 어댑터로 연결
        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)

        binding.topicListView.adapter = mTopicAdapter
    }

    fun getTopicListFromSever(){

        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

                for(i in 0 until topicsArr.length()){
//                    하나의 토론 주제를 표한하는 { } 추출.

                    val topicObj = topicsArr.getJSONObject(i)

//                    목록에 뿌려줄 TopicData 형태로 변환
                    val topicData = TopicData()
                    topicData.id = topicObj.getInt("id")
                    topicData.title = topicObj.getString("title")
                    topicData.imageURL = topicObj.getString("img_url")

//                    완성된 topicData => mTopicList에 추가.
                    mTopicList.add(topicData)
                }

//                어댑터 세팅보다, 서버의 데이터 수신이 더 늦을수도 있다.
//                만약, 서버의 데이터 수신이 더 늦었다면 -> 어댑터 세팅 이후에 목록 추가
//                리스트뷰를 구성하는 ArrayList에 내용물에 변화 발생.

//                새로고침 처리 -> ListView UI 접근
                runOnUiThread {
                    mTopicAdapter.notifyDataSetChanged()
                }

            }

        })
    }


//    fun getMyInfoFromServer(){
//
//        ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler{
//            override fun onResponse(jsonObj: JSONObject) {
//
//                val dataObj = jsonObj.getJSONObject("data")
//                val userObj = dataObj.getJSONObject("user")
//                val nickname = userObj.getString("nick_name")
//
//                runOnUiThread {
//                    binding.txtUserNickname.text = nickname
//                }
//
//            }
//
//        })
//
//    }

}