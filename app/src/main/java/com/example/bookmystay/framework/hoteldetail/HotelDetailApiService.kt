package com.example.bookmystay.framework.hoteldetail

import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import io.reactivex.Single
import retrofit2.http.GET

interface HotelDetailApiService {

    @GET("/api/workshop/hotel")
    fun getHotelDetail(): Single<HotelDetailModel>

    @GET("/api/workshop/comments")
    fun getAllComments(): Single<List<CommentModel>>
}