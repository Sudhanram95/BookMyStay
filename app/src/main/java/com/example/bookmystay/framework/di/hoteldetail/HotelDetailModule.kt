package com.example.bookmystay.framework.di.hoteldetail

import com.example.bookmystay.domain.hoteldetail.HotelDetailDataSource
import com.example.bookmystay.domain.hoteldetail.HotelDetailRepository
import com.example.bookmystay.framework.hoteldetail.HotelDetailApiService
import com.example.bookmystay.framework.hoteldetail.HotelDetailDataSourceImpl
import com.example.bookmystay.usecase.GetHotelDetailResponse
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HotelDetailModule {

    @HotelDetailScope
    @Provides
    fun provideHotelDetailApiService(retrofit: Retrofit): HotelDetailApiService {
        return retrofit.create(HotelDetailApiService::class.java)
    }

    @HotelDetailScope
    @Provides
    fun provideGetHotelDetailResponse(repository: HotelDetailRepository): GetHotelDetailResponse {
        return GetHotelDetailResponse(repository)
    }

    @HotelDetailScope
    @Provides
    fun provideHotelDetailRepository(dataSource: HotelDetailDataSource): HotelDetailRepository {
        return HotelDetailRepository(dataSource)
    }

    @HotelDetailScope
    @Provides
    fun provideHotelDetailDataSource(apiService: HotelDetailApiService): HotelDetailDataSource {
        return HotelDetailDataSourceImpl(apiService)
    }
}