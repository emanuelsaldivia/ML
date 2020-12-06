package com.esaldivia.melichallenge.di

import com.esaldivia.melichallenge.SearchItemsActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchItemsActivity(): SearchItemsActivity

}