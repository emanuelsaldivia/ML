package com.esaldivia.melichallenge.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_baseline_broken_image)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application,
                                 requestOptions: RequestOptions): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}