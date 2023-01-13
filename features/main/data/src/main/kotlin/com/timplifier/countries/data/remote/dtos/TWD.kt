package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class TWD(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)