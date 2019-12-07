package com.example.bookmystay.domain.comment

import com.example.bookmystay.data.hoteldetail.CommentModel

interface CommentDataSource {
    fun submitComment(user: String?, comment: String?): CommentModel?
}