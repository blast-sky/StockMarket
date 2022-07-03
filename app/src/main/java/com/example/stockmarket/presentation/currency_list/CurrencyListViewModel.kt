package com.example.stockmarket.presentation.currency_list

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.usecase.LoadCurrenciesUseCase
import com.example.stockmarket.presentation.StockViewModel
import com.example.stockmarket.presentation.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val useCase: LoadCurrenciesUseCase
) : StockViewModel<List<Currency>>() {

    init { loadCurrencies() }

    fun loadCurrencies() = loadData { useCase.getCurrencies() }

    fun refresh() = loadData(baseState = LoadState.Refreshing()) { useCase.getCurrencies() }
}