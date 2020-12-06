package com.esaldivia.melichallenge.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.esaldivia.melichallenge.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    // todo app lvl instances, ej Retrofit, glide, room

    companion object {
        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions.placeholderOf(R.drawable.ic_launcher_background) // todo ic
            .error(R.drawable.ic_launcher_foreground) // todo ic
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application,
                                 requestOptions: RequestOptions): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        // todo Retrofit instance

    }

}