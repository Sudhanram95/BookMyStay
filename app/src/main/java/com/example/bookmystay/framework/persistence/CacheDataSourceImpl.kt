package com.example.bookmystay.framework.persistence

import android.content.Context
import android.content.SharedPreferences
import com.example.bookmystay.domain.cache.CacheDataSource

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

    override fun getSharedPrefValue(key: String): String? {
        return getSharedPref().getString(key, "")
    }
}