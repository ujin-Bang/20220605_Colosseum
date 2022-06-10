package com.start.a20220605_colosseum

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.start.a20220605_colosseum.databinding.ActivityMyProfileBinding

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

//                실제 로그아웃 처리

            })
            elert.setNegativeButton("취소", null)

            .show()
        }

    }

    override fun setValues() {

    }


}