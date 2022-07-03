package com.example.stockmarket.data.local.mapper

import com.example.stockmarket.data.local.entity.StockExchangeEntity
import com.example.stockmarket.domain.model.StockExchange

fun StockExchangeEntity.toStockExchange() = StockExchange(
    name = name,
    country = country,
    countryCode = countryCode,
    city = city,
    website = website
)

fun StockExchange.toEntity() = StockExchangeEntity(
    name = name,
    country = country,
    countryCode = countryCode,
    city = city,
    website = website
)