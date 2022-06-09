package com.start.a20220605_colosseum

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    //    커스텀 액션바를 달아주는 함수.
    fun setCustomActionBar() {

//        기본 액션바 가져오기
        val defActionBar = supportActionBar!!

//        이 액션바를 커스텀 모드로 변경
        defActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//        실제 커스텀뷰를 어떤 xml인지 설정
        defActionBar.setCustomView(R.layout.my_custom_action_bar)

//        좌우 여백 제거 : ToolBar 소환 -> 여백값 세팅.
        val toolBar = defActionBar.customView.parent as Toolbar
        toolBar.setContentInsetsAbsolute(0,0)


    }
}