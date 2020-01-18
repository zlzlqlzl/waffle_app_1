package com.example.waffleappHW1.di

import android.app.Application
import com.example.waffleappHW1.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: App)
}