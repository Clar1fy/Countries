package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class Demonyms(
    @SerializedName("eng")
    val eng: EngX,
    @SerializedName("fra")
    val fra: FraXX?
)