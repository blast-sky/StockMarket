package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.remote.dto.StockExchangeDto
import com.example.stockmarket.domain.model.StockExchange

fun StockExchangeDto.toStockExchange() = StockExchange(
    name = name,
    country = country,
    countryCode = countryCode,
    city = city,
    website = website
)