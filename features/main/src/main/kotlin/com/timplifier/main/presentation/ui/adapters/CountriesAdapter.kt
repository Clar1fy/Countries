package com.timplifier.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.timplifier.countries.core.base.BaseDiffUtil
import com.timplifier.countries.core.extensions.loadImageWithGlide
import com.timplifier.main.databinding.ItemCountryBinding
import com.timplifier.main.presentation.models.CountryUI

class CountriesAdapter(private val onItemClick: (country: CountryUI) -> Unit) :
    PagingDataAdapter<CountryUI, CountriesAdapter.CountriesViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountriesViewHolder(
        ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CountriesViewHolder(private val binding: ItemCountryBinding) :
        ViewHolder(binding.root) {
        fun onBind(country: CountryUI) {
            binding.imFlag.loadImageWithGlide(country.flags.svg)
            binding.tvCountrySymbol.text = country.symbol
            binding.tvCountryName.text = country.name?.official
        }

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let(onItemClick)
            }
        }
    }
}