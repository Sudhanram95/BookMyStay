package com.example.bookmystay.framework.di.hoteldetail

import com.example.bookmystay.domain.comment.CommentDataSource
import com.example.bookmystay.domain.comment.CommentRepository
import com.example.bookmystay.domain.hoteldetail.HotelDetailDataSource
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.framework.comment.CommentDataSourceImpl
import com.example.bookmystay.framework.di.main.MainScope
import com.example.bookmystay.framework.hoteldetail.HotelDetailApiService
import com.example.bookmystay.framework.hoteldetail.HotelDetailDataSourceImpl
import com.example.bookmystay.usecase.GetHotelDetailResponse
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
    fun provideGetHotelDetailResponse(repository: HotelDetailRepository): GetHotelDetailResponse {
        return GetHotelDetailResponse(repository)
    }

    @MainScope
    @Provides
    fun provideHotelDetailRepository(dataSource: HotelDetailDataSource): HotelDetailRepository {
        return HotelDetailRepository(dataSource)
    }

    @MainScope
    @Provides
    fun provideHotelDetailDataSource(apiService: HotelDetailApiService): HotelDetailDataSource {
        return HotelDetailDataSourceImpl(apiService)
    }

    @MainScope
    @Provides
    fun provideCommentRepository(dataSource: CommentDataSource): CommentRepository = CommentRepository(dataSource)

    @MainScope
    @Provides
    fun provideCommentDataSource(): CommentDataSource {
        return CommentDataSourceImpl()
    }
}