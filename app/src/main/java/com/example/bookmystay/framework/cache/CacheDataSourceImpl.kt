package com.example.bookmystay.framework.cache

import android.content.Context
import android.content.SharedPreferences
import com.example.bookmystay.data.hoteldetail.CommentModel
import com.example.bookmystay.data.hoteldetail.HotelDetailModel
import com.example.bookmystay.domain.cache.CacheDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CacheDataSourceImpl(
    val context: Context
): CacheDataSource {

    var sharedPreferences: SharedPreferences? = null
    fun getSharedPref(): SharedPreferences {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(
                "BookMyStay",
                Context.MODE_PRIVATE
            )
        }
        return sharedPreferences!!
    }

    override fun saveToSharedPref(key: String, value: String) {
        val editor = getSharedPref().edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun getCachedHotelDetail(key: String): HotelDetailModel? {
        val cachedHotelDetailStr = getSharedPref().getString(key, null)
        return Gson().fromJson<HotelDetailModel>(cachedHotelDetailStr, HotelDetailModel::class.java)
    }

    override fun getCachedCommentList(key: String): List<CommentModel>? {
        val cachedCommentList = getSharedPref().getString(key, null)
        return Gson().fromJson<List<CommentModel>>(cachedCommentList,
            object : TypeToken<List<CommentModel>>() {}.type)
    }
}