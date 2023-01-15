package com.timplifier.countries.core.base

import androidx.recyclerview.widget.DiffUtil

interface BaseDiffModel<T> {
    val symbol: T?
    override fun equals(other: Any?): Boolean
}

class BaseDiffUtil<T : BaseDiffModel<S>, S> :
    DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.symbol== newItem.symbol
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}