package com.example.stockmarket.presentation.ticker_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.usecase.LoadHistoricalDataUseCase
import com.example.stockmarket.domain.usecase.LoadTickersUseCase
import com.example.stockmarket.presentation.LoadState
import com.example.stockmarket.presentation.LoadStateProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerListViewModel @Inject constructor(
    private val loadTickersUseCase: LoadTickersUseCase,
    private val loadHistoricalDataUseCase: LoadHistoricalDataUseCase
) : ViewModel() {

    val tickersProvider = LoadStateProvider<List<Ticker>>()
    val historyDataProvider = LoadStateProvider<List<HistoricalData>>()

    init {
        loadTickers()
    }

    fun loadTickers() = viewModelScope.launch {
        tickersProvider.loadData { loadTickersUseCase.loadTickers() }
    }

    fun refreshTickers() = viewModelScope.launch {
        tickersProvider.loadData(
            baseState = LoadState.Refreshing()
        ) { loadTickersUseCase.loadTickers() }
    }

    fun loadHistoricalData(symbol: String) = viewModelScope.launch {
        historyDataProvider.loadData { loadHistoricalDataUseCase.getHistoricalData(symbol) }
    }
}