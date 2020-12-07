package com.esaldivia.melichallenge.di.searchitem

import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchItemModule {

    companion object {
        @Provides
        fun provideSearchItemApi(retrofit: Retrofit): SearchItemApi {
            return retrofit.create(SearchItemApi::class.java)
        }
    }
}