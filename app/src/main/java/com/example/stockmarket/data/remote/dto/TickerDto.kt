package com.example.stockmarket.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TickerDto(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("stock_exchange") val stockExchange: StockExchangeDto
)
