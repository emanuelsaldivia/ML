package com.esaldivia.melichallenge.di.searchitem

import androidx.lifecycle.ViewModel
import com.esaldivia.melichallenge.di.ViewModelKey
import com.esaldivia.melichallenge.ui.searchitems.SearchItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchItemsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchItemViewModel::class)
    abstract fun bindSearchItemViewModel( searchItemViewModel: SearchItemViewModel): ViewModel
}