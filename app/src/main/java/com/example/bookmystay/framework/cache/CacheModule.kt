package com.example.bookmystay.framework.cache

import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.presenter.application.BookMyStayApplication
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideCacheRepository(): CacheRepository {
        return CacheRepositoryImpl(BookMyStayApplication.getApplicationContext())
    }
}