package com.example.stockmarket.data.remote.pojo

import com.google.gson.annotations.SerializedName


data class CurrencyDto(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("symbolNative") val symbolNative: String?
)
