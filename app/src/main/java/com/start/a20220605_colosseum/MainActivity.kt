package com.start.a20220605_colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityMainBinding
import com.start.a20220605_colosseum.utils.ServerUtil

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
            ServerUtil.postRequestLogIn(inputEmail, inputPw)
        }

    }

    override fun setValues() {


    }



}