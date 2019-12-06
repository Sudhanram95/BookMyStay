package com.example.bookmystay.presenter.application

import android.content.Context
import com.example.bookmystay.framework.di.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BookMyStayApplication: DaggerApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: BookMyStayApplication? = null
        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }
}