package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityWriteReplyBinding
import com.start.a20220605_colosseum.datas.TopicData
import com.start.a20220605_colosseum.utils.ServerUtil
import org.json.JSONObject

class WriteReplyActivity : BaseActivity() {

    lateinit var binding : ActivityWriteReplyBinding

    lateinit var mTopicData : TopicData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_write_reply)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {
        
        binding.btnOk.setOnClickListener { 
            
//            입력한 문구가 몇글자? -> 10글자 이내면 거부
             val inputContent = binding.edtContent.text.toString()
            
            if (inputContent.length < 10){

                Toast.makeText(mContext, "최소 10글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            검사 통과 -> 의견등록 (서버 호출)

            ServerUtil.postRequestTopicReply(mContext, mTopicData.id, inputContent, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    runOnUiThread {
                        if(code == 200){
                            Toast.makeText(mContext, "의견등록 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else {
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            })

        }


    }

    override fun setValues() {


        mTopicData = intent.getSerializableExtra("topic") as TopicData

        binding.txtTopicTitle.text = mTopicData.title

        binding.txtMySideTitle.text = mTopicData.mySide!!.title
    }

}