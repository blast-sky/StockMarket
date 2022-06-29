package com.example.stockmarket.presentation.currency_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.repository.StockRepository
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

    var currenciesState: ListState<List<Currency>> by mutableStateOf(ListState.Empty())

    init {
        loadCurrencies()
    }

    fun loadCurrencies() = viewModelScope.launch {
        currenciesState = ListState.Loading()
        currenciesState = getListState()
    }

    fun refresh() = viewModelScope.launch {
        currenciesState = ListState.Refreshing()
        currenciesState = getListState()
    }

    private suspend fun getListState(): ListState<List<Currency>> {
        return when (
            val result = withContext(Dispatchers.IO) {
                stockRepository.getCurrencies()
            }) {
            is Resource.Success -> ListState.Success(result.data)
            is Resource.Error -> ListState.Error(result.message)
        }
    }
}