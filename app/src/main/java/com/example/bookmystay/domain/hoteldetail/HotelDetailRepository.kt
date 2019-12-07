package com.example.bookmystay.domain.hoteldetail

import com.example.bookmystay.domain.network.NetworkCallback

interface HotelDetailRepository {
    fun getHotelDetail(networkCallback: NetworkCallback)
    fun getAllComments(networkCallback: NetworkCallback)
}