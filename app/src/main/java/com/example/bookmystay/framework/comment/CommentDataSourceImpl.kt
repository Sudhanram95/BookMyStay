package com.example.bookmystay.framework.comment

import android.util.Log
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.domain.comment.CommentDataSource

class CommentDataSourceImpl: CommentDataSource {

    override fun submitComment(user: String?, comment: String?): CommentModel? {
        Log.e("CommentDataSource", "user: $user")
        if (user.isNullOrEmpty() && comment.isNullOrEmpty()) {
            return null
        }
        val commentModel = CommentModel(user!!, comment!!)
        return commentModel
    }
}