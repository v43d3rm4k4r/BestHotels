package com.github.v43d3rm4k4r.besthotels.data.hotelsapi

import com.github.v43d3rm4k4r.besthotels.data.models.Hotel
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HotelsAPI {

    @GET("0777.json")
    suspend fun fetchHotelsList(): Response<List<Hotel>>

    @GET("{hotel_id}.json")
    suspend fun fetchHotelDetails(@Path(value = "hotel_id", encoded = true) hotelId: String): Response<HotelDetailed>

    // downloads image TODO: use Picasso? (see https://medium.com/@pypriyank/consuming-rest-api-in-android-using-retrofit-and-gson-20268aadf0eb)
    @GET("{image_name}")
    suspend fun fetchHotelImageBytes(@Path(value = "image_name", encoded = true) imageName: String): Response<ResponseBody>
}