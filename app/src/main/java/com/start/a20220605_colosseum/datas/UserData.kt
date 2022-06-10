package com.start.a20220605_colosseum.datas

import org.json.JSONObject
import java.io.Serializable

class UserData : Serializable{

    var id = 0
    var email = ""
    var nickname = ""

    companion object {

        fun getUserDataFromJson(jsonObj : JSONObject) : UserData {

            val userData = UserData()

            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickname = jsonObj.getString("nick_name")

            return userData

        }

    }
}