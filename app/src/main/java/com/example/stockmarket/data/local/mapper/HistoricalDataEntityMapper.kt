package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.local.entity.HistoricalDataEntity
import com.example.stockmarket.domain.model.HistoricalData

fun HistoricalDataEntity.toHistoricalData() = HistoricalData(
    date = date,
    symbol = symbol,
    exchange = exchange,
    high = high,
    low = low
)

fun HistoricalData.toEntity() = HistoricalDataEntity(
    date = date,
    symbol = symbol,
    exchange = exchange,
    high = high,
    low = low
)