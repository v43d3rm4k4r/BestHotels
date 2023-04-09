package com.github.v43d3rm4k4r.besthotels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.v43d3rm4k4r.besthotels.data.HotelsFetcherImpl
import com.github.v43d3rm4k4r.besthotels.data.hotelsapi.HotelsAPI
import com.github.v43d3rm4k4r.besthotels.domain.contracts.HotelsFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * TODO: Test Repository
 */
@RunWith(AndroidJUnit4::class)
class HotelsFetcherTest {

    private lateinit var hotelsFetcher: HotelsFetcher

    @Before
    fun createHotelsFetcher() {
        val client = OkHttpClient.Builder().build()
        val hotelsAPI = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/iMofas/ios-android-test/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create<HotelsAPI>()

        hotelsFetcher = HotelsFetcherImpl(hotelsAPI, Dispatchers.IO)
    }

    @Test
    @Throws(Exception::class)
    fun getHotelsData() = runBlocking {

        val hotelsList = hotelsFetcher.fetchHotelsList()
        hotelsList ?: error("hotelsList is null")
        assertEquals(true, hotelsList.size == HOTELS_COUNT)

        val detailedHotelsList = hotelsList.map {
            hotelsFetcher.fetchHotelDetails(it.id).apply {
                assertNotEquals("Detailed hotel $it is null", null, it)
            }!!
        }

        // HotelDetailed.image could be blank, so testing the one with this field available:
        val image = hotelsFetcher.fetchHotelImageBytes(detailedHotelsList[0].imageName)
        assertNotEquals("Hotel image is null", null, image)
    }

    private companion object {
        const val HOTELS_COUNT = 7
    }
}