package com.example.waffleappHW1.di

import com.example.waffleappHW1.view.ListActivity
import com.example.waffleappHW1.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun main(): MainActivity

    @ContributesAndroidInjector
    abstract fun list(): ListActivity
}