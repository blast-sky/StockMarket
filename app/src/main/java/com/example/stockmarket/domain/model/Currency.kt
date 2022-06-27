package com.example.stockmarket.domain.model

data class Currency(
    val code: String,
    val name: String,
    val symbol: String,
    val symbolNative: String?
)
