package com.github.v43d3rm4k4r.besthotels.presentation.screens.hoteldetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.FragmentHotelDetailsBinding
import com.github.v43d3rm4k4r.besthotels.presentation.BaseFragment

class HotelDetailsFragment : BaseFragment<FragmentHotelDetailsBinding>(
    FragmentHotelDetailsBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() =
        with(binding) {
            val hotel = navArgs<HotelDetailsFragmentArgs>().value.hotel
            hotelNameTextView.text = hotel.name
            resolveStarsVisibility(hotel)
            if (hotel.imageBitmap == null)
                hotelPhotoImageView.setImageDrawable(AppCompatResources.getDrawable(root.context, R.drawable.placeholder))
            else {
                val resizedBmp = Bitmap.createBitmap(
                    hotel.imageBitmap!!, 1, 1, hotel.imageBitmap!!.width-2,
                    hotel.imageBitmap!!.height-2
                )
                hotelPhotoImageView.setImageBitmap(resizedBmp)
            }
            distanceFromCenterTextView.text = getString(R.string.distance_from_center, hotel.distance)
            val availableRooms = if (!hotel.suitesAvailability.contains(':'))
                    hotel.suitesAvailability
                else
                    hotel.suitesAvailability.split(':').sorted().toString()
            roomsAvailableTextView.text = if (availableRooms.isBlank())
                    getString(R.string.hotel_no_available_rooms)
                else
                    getString(R.string.hotel_available_rooms, availableRooms)
        }

    private fun resolveStarsVisibility(hotel: HotelDetailed) {
        with(binding) {
            star1ImageView.isVisible = hotel.stars >= 1
            star2ImageView.isVisible = hotel.stars >= 2
            star3ImageView.isVisible = hotel.stars >= 3
            star4ImageView.isVisible = hotel.stars >= 4
            star5ImageView.isVisible = hotel.stars == 5
        }
    }
}