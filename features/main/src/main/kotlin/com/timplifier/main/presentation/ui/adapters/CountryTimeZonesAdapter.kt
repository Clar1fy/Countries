package com.timplifier.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.timplifier.main.databinding.ItemCountryTimeZoneBinding

class CountryTimeZonesAdapter :
    ListAdapter<String, CountryTimeZonesAdapter.CountryTimeZonesViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryTimeZonesViewHolder(
        ItemCountryTimeZoneBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CountryTimeZonesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CountryTimeZonesViewHolder(private val binding: ItemCountryTimeZoneBinding) :
        ViewHolder(binding.root) {
        fun onBind(timeZone: String) {
            binding.tvCountryTimezone.text = timeZone
        }
    }

    companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}