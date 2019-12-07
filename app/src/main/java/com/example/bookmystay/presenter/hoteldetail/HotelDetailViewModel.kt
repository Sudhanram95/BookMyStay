package com.example.bookmystay.presenter.hoteldetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.domain.comment.CommentRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.domain.network.NetworkCallback
import com.example.bookmystay.domain.network.ViewState
import com.google.gson.Gson
import javax.inject.Inject


class HotelDetailViewModel @Inject constructor(
    val hotelDetailRepository: HotelDetailRepository,
    val cacheRepository: CacheRepository,
    val commentRepository: CommentRepository
): ViewModel() {

    private val hotelDetailLiveData = MutableLiveData<ViewState<HotelDetailModel>>()
    private val commentsListLiveData = MutableLiveData<ViewState<List<CommentModel>>>()
    private val submitCommentLiveData = MutableLiveData<ViewState<CommentModel>>()

    fun getHotelDetailResponse() = hotelDetailLiveData
    fun getAllCommentsResponse() = commentsListLiveData
    fun getCommentModel() = submitCommentLiveData

    fun callGetHotelApi() {
        hotelDetailLiveData.value = ViewState.Loading()
        hotelDetailRepository.getHotelDetail(object : NetworkCallback {
            override fun onSuccess(response: Any) {
                val hotelDetailModel = response as HotelDetailModel
                cacheRepository.saveSharedPref("HotelDetail", Gson().toJson(hotelDetailModel))
                hotelDetailLiveData.value = ViewState.Success(hotelDetailModel)
            }

            override fun onError(error: Throwable) {
                val cachedHotelDetail = cacheRepository.getCachedHotelDetail("HotelDetail")
                if (cachedHotelDetail != null) {
                    hotelDetailLiveData.value = ViewState.Success(cachedHotelDetail)
                } else {
                    hotelDetailLiveData.value = ViewState.Error(Throwable("Something went wrong"))
                }
            }
        })
    }

    fun callGetAllCommentsApi() {
        commentsListLiveData.value = ViewState.Loading()
        hotelDetailRepository.getAllCommentList(object : NetworkCallback {
            override fun onSuccess(response: Any) {
                val commentList = response as List<CommentModel>
                cacheRepository.saveSharedPref("Comments", Gson().toJson(commentList))
                commentsListLiveData.value = ViewState.Success(commentList)
            }

            override fun onError(error: Throwable) {
                val cachedCommentList = cacheRepository.getCachedCommentList("Comments")
                if (cachedCommentList != null) {
                    commentsListLiveData.value = ViewState.Success(cachedCommentList)
                } else {
                    commentsListLiveData.value = ViewState.Error(Throwable("Something went wrong"))
                }
            }
        })
    }

    fun saveComment(user: String, comment: String) {
        val commentModel = commentRepository.saveComment(user, comment)

        if (commentModel != null) {
            submitCommentLiveData.value = ViewState.Success(commentModel)
        } else {
            submitCommentLiveData.value = ViewState.Error(Throwable("Enter all fields"))
        }
    }

}