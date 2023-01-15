package com.timplifier.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.timplifier.main.databinding.ItemCountryCurrencyBinding
import com.timplifier.main.presentation.models.CurrencyUI

class CountryCurrenciesAdapter :
    ListAdapter<CurrencyUI, CountryCurrenciesAdapter.CountryCurrenciesViewHolder>(Companion) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryCurrenciesViewHolder(
        ItemCountryCurrencyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CountryCurrenciesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CountryCurrenciesViewHolder(private val binding: ItemCountryCurrencyBinding) :
        ViewHolder(binding.root) {
        fun onBind(currency: CurrencyUI) {
            binding.tvCurrencySymbol.text = currency.symbol
            binding.tvCurrencyCode.text = currency.code
            binding.tvCurrencyName.text = currency.name
        }
    }

    companion object : DiffUtil.ItemCallback<CurrencyUI>() {
        override fun areItemsTheSame(oldItem: CurrencyUI, newItem: CurrencyUI) =
            oldItem.name == newItem.name || oldItem.code == newItem.code || oldItem.symbol == newItem.symbol

        override fun areContentsTheSame(oldItem: CurrencyUI, newItem: CurrencyUI) =
            oldItem == newItem
    }
}