package com.timplifier.countries.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String,
    @SerializedName("nativeName")
    val nativeName: NativeName?
)