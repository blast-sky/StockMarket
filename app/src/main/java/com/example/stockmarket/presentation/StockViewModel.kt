package com.example.stockmarket.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class StockViewModel<T> : ViewModel() {

    private val provider = LoadStateProvider<T>()
    var state = provider.state

    fun loadData(
        baseState: LoadState<T> = LoadState.Loading(),
        dataLoader: suspend () -> Resource<T>
    ) = viewModelScope.launch {
        provider.loadData(
            baseState = baseState,
            dataLoader = dataLoader
        )
    }

}