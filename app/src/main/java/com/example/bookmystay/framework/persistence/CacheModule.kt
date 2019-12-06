package com.example.bookmystay.framework.persistence

import android.content.Context
import android.content.SharedPreferences
import com.example.bookmystay.domain.cache.CacheDataSource
import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.presenter.application.BookMyStayApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    fun provideCacheRepository(cacheDataSource: CacheDataSource): CacheRepository {
        return CacheRepository(cacheDataSource)
    }

    @Provides
    fun provideCacheDataSource(): CacheDataSource {
        return CacheDataSourceImpl(BookMyStayApplication.getApplicationContext())
    }
}