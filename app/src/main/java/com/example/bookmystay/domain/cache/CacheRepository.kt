package com.example.bookmystay.domain.cache

class CacheRepository(val cacheDataSource: CacheDataSource) {
    fun saveSharedPref(key: String, value: String) {
        cacheDataSource.saveToSharedPref(key, value)
    }

    fun getSharedPref(key: String) = cacheDataSource.getSharedPrefValue(key)
}