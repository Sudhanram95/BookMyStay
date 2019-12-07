package com.example.bookmystay.domain.comment

import com.example.bookmystay.data.hoteldetail.CommentModel

class CommentRepository(val commentDataSource: CommentDataSource) {

    fun saveComment(user: String, comment: String) = commentDataSource.submitComment(user, comment)

}