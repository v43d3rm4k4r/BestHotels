package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.recyclerviewutils

import android.graphics.Bitmap
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.v43d3rm4k4r.besthotels.R
import com.github.v43d3rm4k4r.besthotels.R.*
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.ListItemHotelBinding
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent
import com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.HotelsEvent.HotelClicked

class HotelsAdapter(
    private val obtainEvent: (HotelsEvent) -> Unit,
) : ListAdapter<HotelDetailed, HotelsAdapter.HotelHolder>(ItemCallback),
    View.OnClickListener {

    override fun onClick(view: View) {
        val hotel = view.tag as HotelDetailed
        obtainEvent(HotelClicked(hotel))
    }

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes viewType: Int): HotelHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemHotelBinding.inflate(inflater, parent, false).apply {
            root.setOnClickListener(this@HotelsAdapter)
        }
        return HotelHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelHolder, position: Int) =
        holder.bind(getItem(position))

    class HotelHolder(
        private val binding: ListItemHotelBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hotel: HotelDetailed) =
            with(binding) {
                root.tag = hotel
                hotelNameTextView.text = hotel.name
                if (hotel.imageBitmap == null)
                    hotelPhotoImageView.setImageDrawable(AppCompatResources.getDrawable(root.context, drawable.placeholder))
                else {
                    val resizedBmp = Bitmap.createBitmap(
                        hotel.imageBitmap!!, 1, 1, hotel.imageBitmap!!.width-2,
                        hotel.imageBitmap!!.height-2
                    )
                    hotelPhotoImageView.setImageBitmap(resizedBmp)
                }
                distanceFromCenterTextView.text = root.context.getString(string.distance_from_center, hotel.distance)
                resolveStarsVisibility(hotel)
                val roomsAvailable = hotel.suitesAvailability.split(':').size
                roomsAvailableTextView.text = root.context.resources.getQuantityString(plurals.rooms_plurals, roomsAvailable, roomsAvailable)
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

    object ItemCallback : DiffUtil.ItemCallback<HotelDetailed>() {
        override fun areItemsTheSame(oldItem: HotelDetailed, newItem: HotelDetailed): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HotelDetailed, newItem: HotelDetailed): Boolean =
            oldItem == newItem
    }
}