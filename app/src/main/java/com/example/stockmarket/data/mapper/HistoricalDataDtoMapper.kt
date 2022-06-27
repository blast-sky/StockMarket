package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.remote.pojo.HistoricalDataDto
import com.example.stockmarket.domain.model.HistoricalData

fun HistoricalDataDto.toHistoricalData() : HistoricalData {
    return HistoricalData(
        date = this.date,
        symbol = this.symbol,
        high = this.high,
        low = this.low,
        exchange = this.exchange
    )
}