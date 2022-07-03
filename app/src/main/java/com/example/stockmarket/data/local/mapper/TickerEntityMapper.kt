package com.example.stockmarket.data.local.mapper

import com.example.stockmarket.data.local.entity.TickerEntity
import com.example.stockmarket.domain.model.Ticker

fun TickerEntity.toTicker() = Ticker(
    name = name,
    symbol = symbol,
    stockExchange = stockExchange.toStockExchange()
)

fun Ticker.toEntity() = TickerEntity(
    name = name,
    symbol = symbol,
    stockExchange = stockExchange.toEntity()
)