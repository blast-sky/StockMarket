package com.example.stockmarket.presentation.currency_list

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.usecase.LoadDataUseCase
import com.example.stockmarket.presentation.StockViewModel
import com.example.stockmarket.presentation.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: LoadDataUseCase
) : StockViewModel<List<Currency>>() {

    init { loadCurrencies() }

    fun loadCurrencies() = loadData { useCase.getCurrencies() }

    fun refresh() = loadData(LoadState.Refreshing()) { useCase.getCurrencies() }
}