package com.start.a20220605_colosseum.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    companion object {

//        여기에 적는 변수 / 함수는 => JAVA의 static에 대응됨
//        클래스이름.기능() 로 활용 가능.

//        모든 함수(기능) 가 공유할 서버 컴퓨터 주소.
        val HOST_URL = "http://54.180.52.26"

//        로그인 함수 - POST
        fun postRequestLogIn( email: String, pw: String){

//            1. 어디로 가야? URL
            val urlString = "${ HOST_URL}/user"
//            2. 어떤 데이터를 들고가는가?(파라미터)
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

//            3. 어떤 메쏘드 +1,2 데이터 결합
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

//              4. 완성된 Request를 실제로 호출 => 클라이언트 역할.
            val client = OkHttpClient()

            client.newCall(request).enqueue( object : Callback{
                override fun onFailure(call: Call, e: IOException) {

//                    실패: 물리적 접속 실패.
//                    보통 토스트 띄우는 것으로 대체함.
                }

                override fun onResponse(call: Call, response: Response) {

//                    결과가 무엇이든 응답은 돌아온 상황.
//                    응답의 본문(body)에 어떤내용? => 본문만 String으로 변환.
                    val bodyString = response.body!!.string()

//                    bodyString은 JSON양식으로 가공됨 => 한글도 임시 변환된 상태(encoding)

//                    일반 String - JSONObject로 변환(한글도 원상복구)
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답", jsonObj.toString())
                }

            })
        }

    }
}