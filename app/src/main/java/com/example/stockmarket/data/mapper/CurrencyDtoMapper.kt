package com.example.stockmarket.data.mapper

import com.example.stockmarket.data.remote.pojo.CurrencyDto
import com.example.stockmarket.domain.model.Currency

fun CurrencyDto.toCurrency() : Currency {
    return Currency(
        code = this.code,
        name = this.name,
        symbol = this.symbol,
        symbolNative = this.symbolNative
    )
}