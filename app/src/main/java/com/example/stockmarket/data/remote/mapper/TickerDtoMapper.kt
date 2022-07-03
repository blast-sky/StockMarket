package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.remote.dto.TickerDto
import com.example.stockmarket.domain.model.Ticker

fun TickerDto.toTicker() = Ticker(
    name = name,
    symbol = symbol,
    stockExchange = stockExchange.toStockExchange()
)