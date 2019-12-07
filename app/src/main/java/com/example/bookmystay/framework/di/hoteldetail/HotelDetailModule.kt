package com.example.bookmystay.framework.di.hoteldetail

import com.example.bookmystay.domain.cache.CacheRepository
import com.example.bookmystay.domain.comment.CommentRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.framework.comment.CommentRepositoryImpl
import com.example.bookmystay.framework.di.main.MainScope
import com.example.bookmystay.framework.hoteldetail.HotelDetailApiService
import com.example.bookmystay.framework.hoteldetail.HotelDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HotelDetailModule {

    @MainScope
    @Provides
    fun provideHotelDetailApiService(retrofit: Retrofit): HotelDetailApiService {
        return retrofit.create(HotelDetailApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideHotelDetailRepository(apiService: HotelDetailApiService, cacheRepository: CacheRepository): HotelDetailRepository {
        return HotelDetailRepositoryImpl(apiService, cacheRepository)
    }

    @MainScope
    @Provides
    fun provideCommentRepository(): CommentRepository {
        return CommentRepositoryImpl()
    }
}