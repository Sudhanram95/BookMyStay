package com.example.bookmystay.framework.di.main

import com.example.bookmystay.framework.di.hoteldetail.HotelDetailModule
import com.example.bookmystay.framework.di.hoteldetail.HotelDetailScope
import com.example.bookmystay.framework.di.hoteldetail.HotelDetailViewModelModule
import com.example.bookmystay.presenter.comment.CommentFragment
import com.example.bookmystay.presenter.hoteldetail.HotelDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [HotelDetailModule::class, HotelDetailViewModelModule::class])
    abstract fun contributeHotelDetailFragment(): HotelDetailFragment

    @MainScope
    @ContributesAndroidInjector(modules = [HotelDetailModule::class, HotelDetailViewModelModule::class])
    abstract fun contributeCommentFragment(): CommentFragment
}