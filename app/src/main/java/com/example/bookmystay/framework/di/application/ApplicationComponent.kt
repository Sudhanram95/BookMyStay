package com.example.bookmystay.framework.di.application

import android.app.Application
import com.example.bookmystay.framework.network.NetworkModule
import com.example.bookmystay.framework.cache.CacheModule
import com.example.bookmystay.presenter.application.BookMyStayApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                        ActivityBuilderModule::class,
                        NetworkModule::class,
                        CacheModule::class])
interface ApplicationComponent: AndroidInjector<BookMyStayApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}