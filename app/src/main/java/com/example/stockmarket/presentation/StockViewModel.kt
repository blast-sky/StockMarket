package com.example.stockmarket.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.domain.Resource
import kotlinx.coroutines.launch

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