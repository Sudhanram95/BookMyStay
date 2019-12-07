package com.example.bookmystay.domain.comment

import com.example.bookmystay.data.hoteldetail.CommentModel

interface CommentRepository {
    fun submitComment(user: String, comment: String): CommentModel
}