package com.github.v43d3rm4k4r.besthotels.di

import android.content.Context
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hoteldetails.HotelDetailsFragment
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Scope
annotation class AppScope

@Singleton
@AppScope
@Component(modules = [RemoteModule::class])
interface AppComponent {

    @Component.Factory
    fun interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(to: HotelsFragment)
}