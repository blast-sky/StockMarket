package com.example.stockmarket.domain.model

data class HistoricalData(
    val date: String,
    val symbol: String,
    val exchange: String,
    val high: Double,
    val low: Double,
)

