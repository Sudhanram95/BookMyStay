package com.example.bookmystay.framework.hoteldetail

import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.domain.network.NetworkCallback
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HotelDetailRepositoryImpl @Inject constructor(
    val apiService: HotelDetailApiService,
    val cacheRepository: CacheRepository
): HotelDetailRepository {

    override fun getHotelDetail(networkCallback: NetworkCallback) {
        apiService.getHotelDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<HotelDetailModel>() {
                override fun onSuccess(response: HotelDetailModel) {
                    cacheRepository.saveToSharedPref("HotelDetail", Gson().toJson(response))
                    networkCallback.onSuccess(response)
                }

                override fun onError(error: Throwable) {
                    val cachedHotelDetail = cacheRepository.getCachedHotelDetail("HotelDetail")
                    if (cachedHotelDetail != null) {
                        networkCallback.onSuccess(cachedHotelDetail)
                    } else {
                        networkCallback.onError(error)
                    }
                }
            })
    }

    override fun getAllComments(networkCallback: NetworkCallback) {
        apiService.getAllComments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CommentModel>>() {
                override fun onSuccess(response: List<CommentModel>) {
                    cacheRepository.saveToSharedPref("Comments", Gson().toJson(response))
                    networkCallback.onSuccess(response)
                }

                override fun onError(error: Throwable) {
                    val cachedCommentList = cacheRepository.getCachedCommentList("Comments")
                    if (cachedCommentList != null) {
                        networkCallback.onSuccess(cachedCommentList)
                    } else {
                        networkCallback.onError(error)
                    }
                }
            })
    }
}