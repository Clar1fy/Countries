package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("signs")
    val signs: List<String>?,
    @SerializedName("side")
    val side: String
)