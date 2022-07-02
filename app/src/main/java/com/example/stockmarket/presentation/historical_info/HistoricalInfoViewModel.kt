package com.example.stockmarket.presentation.historical_info

import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.usecase.LoadDataUseCase
import com.example.stockmarket.presentation.StockViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoricalInfoViewModel @Inject constructor(
    private val useCase: LoadDataUseCase
) : StockViewModel<List<HistoricalData>>() {

    fun loadHistoricalData(symbol: String) = loadData {
        useCase.getHistoricalData(symbol)
    }
}