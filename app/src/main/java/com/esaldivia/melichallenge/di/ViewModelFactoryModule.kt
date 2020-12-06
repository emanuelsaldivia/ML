package com.esaldivia.melichallenge.di

import androidx.lifecycle.ViewModelProvider
import com.esaldivia.melichallenge.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory)
            : ViewModelProvider.Factory
}