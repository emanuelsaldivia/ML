package com.esaldivia.melichallenge.di

import android.app.Application
import com.esaldivia.melichallenge.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}