package com.github.v43d3rm4k4r.besthotels.presentation.screens.hotels.recyclerviewutils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.v43d3rm4k4r.besthotels.data.models.HotelDetailed
import com.github.v43d3rm4k4r.besthotels.databinding.ListItemHotelBinding
import java.text.SimpleDateFormat
import java.util.*

class HotelsAdapter(
    private val onItemClicked: (hotel: HotelDetailed) -> Unit,
) : ListAdapter<HotelDetailed, HotelsAdapter.HotelHolder>(ItemCallback),
    View.OnClickListener {

    override fun onClick(view: View) {
        val hotel = view.tag as HotelDetailed
        onItemClicked(hotel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes viewType: Int): HotelHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)

        view.setOnClickListener(this)

        val binding = ListItemHotelBinding.bind(view)
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
//                hotelTextView.text = hotel.text
//                hotelDateTextView.text = hotel.date.toFormattedString()
//                importantImageView.isVisible = hotel.isImportant
            }

        @SuppressLint("SimpleDateFormat")
        private fun Date.toFormattedString(): String {
            val dateFormat = SimpleDateFormat("d MMMM yyyy, HH:mm")
            val calendar   = Calendar.getInstance().apply { time = this@toFormattedString }
            return dateFormat.format(calendar.time)
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<HotelDetailed>() {
        override fun areItemsTheSame(oldItem: HotelDetailed, newItem: HotelDetailed): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HotelDetailed, newItem: HotelDetailed): Boolean =
            oldItem == newItem
    }
}