package com.example.bookmystay.domain.cache

import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel

interface CacheDataSource {
    fun saveToSharedPref(key: String, value: String)
    fun getCachedHotelDetail(key: String): HotelDetailModel?
    fun getCachedCommentList(key: String): List<CommentModel>?
}