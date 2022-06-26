package com.example.stockmarket.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.presentation.state.CurrencyListState
import com.example.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    var currenciesState by mutableStateOf(CurrencyListState())

    init {
        loadCurrencies()
    }

    fun loadCurrencies() = viewModelScope.launch {
        currenciesState = currenciesState.copy(isLoading = true)
        currenciesState = when (
            val currencies = withContext(Dispatchers.IO) { stockRepository.getCurrencies() }
        ) {
            is Resource.Success -> {
                currenciesState.copy(
                    currencies = currencies.data,
                    isLoading = false
                )
            }
            is Resource.Error -> {
                currenciesState.copy(
                    error = currencies.message,
                    isLoading = false
                )
            }
        }
    }
}