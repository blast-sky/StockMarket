package com.example.stockmarket.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class HistoricalData(
    val date: LocalDateTime,
    val symbol: String,
    val exchange: String,
    val high: Double,
    val low: Double,
) : Parcelable

