package com.github.v43d3rm4k4r.besthotels

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.github.v43d3rm4k4r.besthotels.di.AppComponent
import com.github.v43d3rm4k4r.besthotels.di.DaggerAppComponent

class BestHotelsApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(context = this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is BestHotelsApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

val Fragment.appComponent: AppComponent
    get() = requireActivity().appComponent