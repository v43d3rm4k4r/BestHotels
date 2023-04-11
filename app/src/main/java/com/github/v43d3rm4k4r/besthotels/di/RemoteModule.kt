package com.github.v43d3rm4k4r.besthotels.di

import android.content.Context
import com.github.v43d3rm4k4r.androidutils.network.ConnectivityObserver
import com.github.v43d3rm4k4r.androidutils.network.NetworkConnectivityObserver
import com.github.v43d3rm4k4r.besthotels.domain.contracts.HotelsFetcher
import com.github.v43d3rm4k4r.besthotels.data.HotelsFetcherImpl
import com.github.v43d3rm4k4r.besthotels.data.hotelsapi.HotelsAPI
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Qualifier
annotation class HotelsFetcherDispatcher

@Module(includes = [RemoteModule.Declarations::class])
object RemoteModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/iMofas/ios-android-test/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    fun provideHotelsAPI(retrofit: Retrofit): HotelsAPI = retrofit.create()

    @Provides
    @HotelsFetcherDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideNetworkConnectivityObserver(context: Context): ConnectivityObserver = NetworkConnectivityObserver(context)

    @Module
    interface Declarations {

        @Binds
        fun bindHotelsFetcher(impl: HotelsFetcherImpl): HotelsFetcher
    }
}