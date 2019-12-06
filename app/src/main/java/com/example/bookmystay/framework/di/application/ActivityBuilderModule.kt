package com.example.bookmystay.framework.di.application

import com.example.bookmystay.framework.di.hoteldetail.HotelDetailModule
import com.example.bookmystay.framework.di.hoteldetail.HotelDetailScope
import com.example.bookmystay.framework.di.hoteldetail.HotelDetailViewModelModule
import com.example.bookmystay.presenter.hoteldetail.HotelDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @HotelDetailScope
    @ContributesAndroidInjector(modules = [HotelDetailModule::class, HotelDetailViewModelModule::class])
    abstract fun contributeHotelDetailActivity(): HotelDetailActivity
}