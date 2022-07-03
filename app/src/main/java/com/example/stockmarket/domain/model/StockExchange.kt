package com.example.stockmarket.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StockExchange(
    val name: String,
    val country: String? = null,
    val countryCode: String? = null,
    val city: String? = null,
    val website: String? = null,
) : Parcelable
