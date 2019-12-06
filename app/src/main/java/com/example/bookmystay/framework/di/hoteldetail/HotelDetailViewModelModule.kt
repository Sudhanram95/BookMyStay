package com.example.bookmystay.framework.di.hoteldetail

import androidx.lifecycle.ViewModel
import com.example.bookmystay.framework.di.viewmodel.ViewModelKey
import com.example.bookmystay.presenter.hoteldetail.HotelDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HotelDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelDetailViewModel::class)
    protected abstract fun bindHotelDetailViewModel(hotelDetailViewModel: HotelDetailViewModel): ViewModel
}