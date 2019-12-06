package com.example.bookmystay.data.hoteldetail

data class HotelDetailModel(
    val name: String,
    val description: String,
    val location: String,
    val rating: Double,
    val numberOfReviews: Int,
    val cost: Double
)