package com.example.bookmystay.domain.cache

class CacheRepository(val cacheDataSource: CacheDataSource) {

    fun saveSharedPref(key: String, value: String) {
        cacheDataSource.saveToSharedPref(key, value)
    }

    fun getCachedHotelDetail(key: String) = cacheDataSource.getCachedHotelDetail(key)

    fun getCachedCommentList(key: String) = cacheDataSource.getCachedCommentList(key)
}