package com.esaldivia.melichallenge.di

import com.esaldivia.melichallenge.di.searchitem.SearchItemModule
import com.esaldivia.melichallenge.di.searchitem.SearchItemsViewModelModule
import com.esaldivia.melichallenge.ui.searchitems.SearchItemsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [
        SearchItemsViewModelModule::class,
        SearchItemModule::class
    ])
    abstract fun contributeSearchItemsActivity(): SearchItemsActivity

}