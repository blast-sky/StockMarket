package com.example.stockmarket.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticker(
    val name: String,
    val symbol: String,
    val stockExchange: StockExchange
) : Parcelable
