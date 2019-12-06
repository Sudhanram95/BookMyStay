package com.example.bookmystay.usecase

import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.domain.network.NetworkCallback

class GetHotelDetailResponse(val hotelDetailRepository: HotelDetailRepository) {

    operator fun invoke(networkCallback: NetworkCallback) = hotelDetailRepository.getHotelDetail(networkCallback)
}