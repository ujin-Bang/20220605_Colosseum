package com.start.a20220605_colosseum

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityMyProfileBinding
import com.start.a20220605_colosseum.utils.ContextUtil

class MyProfileActivity : BaseActivity() {

    lateinit var binding : ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogout.setOnClickListener {

//            정말 로그아웃 할건지? 확인.

            val elert = AlertDialog.Builder(mContext)
            elert.setTitle("로그아웃 확인")
            elert.setMessage("정말 로그아웃 하시겠습니까?")
            elert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

//                실제 로그아웃 처리 => 저장된 토큰을 ""으로 돌려주자.(내 폰에 저장된 토큰 삭제)

                ContextUtil.setToken(mContext,"")

//                화면 종료 -> (열려있는 모든 화면을 전부 닫고) SplashActivity로 이동.
                val myIntent = Intent(mContext, SplashActivity::class.java)
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(myIntent)

            })
            elert.setNegativeButton("취소", null)

            .show()
        }

    }

    override fun setValues() {

    }


}