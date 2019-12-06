package com.example.bookmystay.domain.cache

interface CacheDataSource {
    fun saveToSharedPref(key: String, value: String)
    fun getSharedPrefValue(key: String): String?
}