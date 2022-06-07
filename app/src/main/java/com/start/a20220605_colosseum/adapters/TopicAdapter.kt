package com.start.a20220605_colosseum.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.start.a20220605_colosseum.datas.TopicData

class TopicAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<TopicData>) : ArrayAdapter<TopicData>(mContext, resId, mList) {
}