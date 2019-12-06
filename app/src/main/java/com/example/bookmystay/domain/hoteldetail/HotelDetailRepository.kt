package com.example.bookmystay.domain.hoteldetail

import com.example.bookmystay.domain.hoteldetail.HotelDetailDataSource
import com.example.bookmystay.domain.network.NetworkCallback

class HotelDetailRepository(val hotelDetailDataSource: HotelDetailDataSource) {

    fun getHotelDetail(networkCallback: NetworkCallback) = hotelDetailDataSource.getHotelDetail(networkCallback)

    fun getAllCommentList(networkCallback: NetworkCallback) = hotelDetailDataSource.getAllComments(networkCallback)
}