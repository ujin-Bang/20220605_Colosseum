package com.start.a20220605_colosseum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.start.a20220605_colosseum.R
import com.start.a20220605_colosseum.ViewTopicDetailActivity
import com.start.a20220605_colosseum.datas.ReplyData
import com.start.a20220605_colosseum.datas.TopicData
import com.start.a20220605_colosseum.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ReplyAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<ReplyData>) : ArrayAdapter<ReplyData>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if( tempRow == null){

            tempRow = mInflater.inflate(R.layout.reply_list_item, null)

        }

        val row = tempRow!!

        val data = mList[position]

        val txtReplyContent = row.findViewById<TextView>(R.id.txtReplyContent)
        val txtWriterNickname = row.findViewById<TextView>(R.id.txtWriterNickname)
        val txtSelectedSide = row.findViewById<TextView>(R.id.txtSelectedSide)
        val txtCreatedAt = row.findViewById<TextView>(R.id.txtCreatedAt)
        val txtReplyCount = row.findViewById<TextView>(R.id.txtReplyCount)
        val txtLikeCount = row.findViewById<TextView>(R.id.txtLikeCount)
        val txtDislikeCount = row.findViewById<TextView>(R.id.txtDislikeCount)

        txtReplyContent.text = data.content
        txtWriterNickname.text = data.writer.nickname
        txtSelectedSide.text = "(${data.selectedSide.title})"

        txtCreatedAt.text = data.getFormattedCreatedAt()

        txtReplyCount.text = "답글 : ${data.replyCount}개"
        txtLikeCount.text = "좋아요 : ${data.likeCount}개"
        txtDislikeCount.text = "싫어요 : ${data.dislikeCount}개"

//        내 좋아요 여부에 따른 테두리 색 / 글씨 색 변경.
        if(data.myLike){
//            red_border_box로 txtLikeCount의 배경을 변경
            txtLikeCount.setBackgroundResource(R.drawable.red_border_box)

            txtLikeCount.setTextColor(mContext.resources.getColor(R.color.red))

        }
        else {

            txtLikeCount.setBackgroundResource(R.drawable.gray_border_box)
            txtLikeCount.setTextColor(mContext.resources.getColor(R.color.gray))

        }

        if(data.myDislike){
            txtDislikeCount.setBackgroundResource(R.drawable.blue_border_box)
            txtDislikeCount.setTextColor(mContext.resources.getColor(R.color.blue))

        }
        else {
            txtDislikeCount.setBackgroundResource(R.drawable.gray_border_box)
            txtDislikeCount.setTextColor(mContext.resources.getColor(R.color.gray))
        }


//        각 줄의 좋아요 갯수에 이벤트 처리
         txtLikeCount.setOnClickListener{
//             이 댓글에 좋아요를 남겼다고 -> 서버 API 호출
             ServerUtil.postRequestReplyLikeOrDislike(mContext, data.id, true, object : ServerUtil.JsonResponseHandler{
                 override fun onResponse(jsonObj: JSONObject) {

//                     토론 상세 현황 화면의 기능 활용
//                     => 토론 주제 상세 다시 가져오기.(댓글도 가져오게 됨)
                     (mContext as ViewTopicDetailActivity).getTopicDetailFromSever()

                 }

             })
         }

        txtDislikeCount.setOnClickListener {
//            이 댓글에 싫어요를 남겼다고 -> 서버 API 호출
            ServerUtil.postRequestReplyLikeOrDislike(mContext, data.id, false, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    (mContext as ViewTopicDetailActivity).getTopicDetailFromSever()

                }

            })
        }

        return row
    }
}