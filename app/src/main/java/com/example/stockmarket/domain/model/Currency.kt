package com.example.stockmarket.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val code: String,
    val name: String,
    val symbol: String,
    val symbolNative: String?
) : Parcelable
