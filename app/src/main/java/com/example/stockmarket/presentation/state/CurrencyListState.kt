package com.example.stockmarket.presentation.state

import com.example.stockmarket.data.remote.pojo.Currency

data class CurrencyListState(
    var currencies: List<Currency> = emptyList(),
    var isLoading: Boolean = true,
    var error: String? = null
)
