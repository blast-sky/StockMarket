package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.local.entity.CurrencyEntity
import com.example.stockmarket.domain.model.Currency

fun CurrencyEntity.toCurrency() = Currency(
    code = code,
    name = name,
    symbol = symbol,
    symbolNative = symbolNative
)

fun Currency.toEntity() = CurrencyEntity(
    code = code,
    name = name,
    symbol = symbol,
    symbolNative = symbolNative
)