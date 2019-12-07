package com.example.bookmystay.framework.comment

import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.domain.comment.CommentRepository

class CommentRepositoryImpl: CommentRepository {

    override fun submitComment(user: String, comment: String): CommentModel {
        val commentModel = CommentModel(user, comment)
        return commentModel
    }
}