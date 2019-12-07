package com.example.bookmystay.presenter.hoteldetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.comment.CommentRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.domain.network.NetworkCallback
import com.example.bookmystay.domain.network.ViewState
import javax.inject.Inject


class HotelDetailViewModel @Inject constructor(
    val hotelDetailRepository: HotelDetailRepository,
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
                hotelDetailLiveData.value = ViewState.Success(hotelDetailModel)
            }

            override fun onError(error: Throwable) {
                hotelDetailLiveData.value = ViewState.Error(Throwable("Something went wrong"))
            }
        })
    }

    fun callGetAllCommentsApi() {
        commentsListLiveData.value = ViewState.Loading()
        hotelDetailRepository.getAllComments(object : NetworkCallback {
            override fun onSuccess(response: Any) {
                val commentList = response as List<CommentModel>
                commentsListLiveData.value = ViewState.Success(commentList)
            }

            override fun onError(error: Throwable) {
                commentsListLiveData.value = ViewState.Error(Throwable("Something went wrong"))
            }
        })
    }

    fun saveComment(user: String, comment: String) {
        if (user.isNullOrEmpty() && comment.isNullOrEmpty()) {
            submitCommentLiveData.value = ViewState.Error(Throwable("Enter all fields"))
        } else {
            val commentModel = commentRepository.submitComment(user, comment)
            submitCommentLiveData.value = ViewState.Success(commentModel)
        }
    }

}