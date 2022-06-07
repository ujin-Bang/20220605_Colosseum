package com.start.a20220605_colosseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityMainBinding
import com.start.a20220605_colosseum.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogIn.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()

//            서버에서 이메일/ 비번이 맞는 계정인지? 로그인 요청
            ServerUtil.postRequestLogIn(inputEmail, inputPw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

//                    로그인 API를 호출하고 돌아온 상황.
//                    결과로 jsonObj 하나를 받아서 돌아온 상황
                    Log.d("화면에서의 jsonObj", jsonObj.toString())

                    val code = jsonObj.getInt("code")

//                    code : 200 -> 로그인 성공 토스트
//                    그 외 -> 로그인 실패 토스트

                    runOnUiThread {
                        if(code == 200){
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()

                        }
                        else{
//                            message String으로 실패 사유를 알려준다.
//                            JSON 파싱으로 추출해서 -> "로그인 실패" 대신 서버가 알려준 실패 사유를 띄우자.
                            val message = jsonObj.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }
                    }


                }


            })
        }

        binding.btnMoveSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {


    }



}