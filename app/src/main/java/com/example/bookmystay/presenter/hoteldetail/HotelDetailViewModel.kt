package com.example.bookmystay.presenter.hoteldetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.domain.network.NetworkCallback
import com.example.bookmystay.domain.network.ViewState
import javax.inject.Inject

class HotelDetailViewModel @Inject constructor(
    val hotelDetailRepository: HotelDetailRepository,
    val cacheRepository: CacheRepository
): ViewModel() {

    private val hotelDetailLiveData = MutableLiveData<ViewState<HotelDetailModel>>()
    private val commentsListLiveData = MutableLiveData<ViewState<List<CommentModel>>>()

    fun getHotelDetailResponse() = hotelDetailLiveData
    fun getAllCommentsResponse() = commentsListLiveData

    fun callGetHotelApi() {
        hotelDetailLiveData.value = ViewState.Loading()
        hotelDetailRepository.getHotelDetail(object : NetworkCallback {
            override fun onSuccess(response: Any) {
//                cacheRepository.saveSharedPref("HotelDetail", response as HotelDetailModel)
                hotelDetailLiveData.value = ViewState.Success(response as HotelDetailModel)
            }

            override fun onError(error: Throwable) {
                hotelDetailLiveData.value = ViewState.Error(Throwable("Something went wrong"))
            }
        })
    }

    fun callGetAllCommentsApi() {
        commentsListLiveData.value = ViewState.Loading()
        hotelDetailRepository.getAllCommentList(object : NetworkCallback {
            override fun onSuccess(response: Any) {
                commentsListLiveData.value = ViewState.Success(response as List<CommentModel>)
            }

            override fun onError(error: Throwable) {
                commentsListLiveData.value = ViewState.Error(Throwable("Something went wrong"))
            }
        })
    }

}