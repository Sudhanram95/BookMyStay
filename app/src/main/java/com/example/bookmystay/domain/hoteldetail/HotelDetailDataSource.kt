package com.example.bookmystay.domain.hoteldetail

import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.network.NetworkCallback

interface HotelDetailDataSource {
    fun getHotelDetail(networkCallback: NetworkCallback)
    fun getAllComments(networkCallback: NetworkCallback)
}