package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class HNL(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)