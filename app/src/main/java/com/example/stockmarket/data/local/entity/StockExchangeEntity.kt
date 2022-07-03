package com.example.stockmarket.data.local.entity

import androidx.room.ColumnInfo

data class StockExchangeEntity(
    @ColumnInfo(name = "exchange_name") val name: String,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "country_code") val countryCode: String? = null,
    @ColumnInfo(name = "city") val city: String? = null,
    @ColumnInfo(name = "website") val website: String? = null
)
