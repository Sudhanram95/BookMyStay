package com.example.bookmystay.framework.di.application

import com.example.bookmystay.framework.di.main.MainFragmentBuilderModule
import com.example.bookmystay.framework.di.main.MainScope
import com.example.bookmystay.presenter.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}