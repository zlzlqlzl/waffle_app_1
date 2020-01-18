package com.example.waffleappHW1

import com.example.waffleappHW1.di.DaggerAppComponent
import com.example.waffleappHW1.di.AppComponent
import com.example.waffleappHW1.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule("https://jsonplaceholder.typicode.com"))
            .build()

}