package com.example.bookmystay.framework.hoteldetail

import android.content.Context
import android.content.SharedPreferences
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.hoteldetail.HotelDetailDataSource
import com.example.bookmystay.domain.network.NetworkCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HotelDetailDataSourceImpl @Inject constructor(
    val apiService: HotelDetailApiService
): HotelDetailDataSource {

    override fun getHotelDetail(networkCallback: NetworkCallback) {
        apiService.getHotelDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<HotelDetailModel>() {
                override fun onSuccess(response: HotelDetailModel) {
                    networkCallback.onSuccess(response)
                }

                override fun onError(error: Throwable) {
                    networkCallback.onError(error)
                }
            })
    }

    override fun getAllComments(networkCallback: NetworkCallback) {
        apiService.getAllComments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CommentModel>>() {
                override fun onSuccess(response: List<CommentModel>) {
                    networkCallback.onSuccess(response)
                }

                override fun onError(error: Throwable) {
                    networkCallback.onError(error)
                }
            })
    }
}