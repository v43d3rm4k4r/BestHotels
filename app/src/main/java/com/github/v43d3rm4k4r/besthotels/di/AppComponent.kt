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

    // TODO: Component for each fragment?
    //fun activityComponent(): ActivityComponent

    //fun inject(to: BestHotelsApplication) // no need for this

    // TODO: MB split into different subcomponents
    fun inject(to: HotelsFragment)
    fun inject(to: HotelDetailsFragment)
}