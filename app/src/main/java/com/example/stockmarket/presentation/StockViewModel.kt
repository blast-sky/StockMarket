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

    var state: LoadState<T> by mutableStateOf(LoadState.Empty())

    protected fun loadData(
        baseState: LoadState<T> = LoadState.Loading(),
        dataLoader: suspend () -> Resource<T>
    ) = viewModelScope.launch {
        state = baseState
        state = getListState(dataLoader)
    }

    private suspend fun getListState(dataLoader: suspend () -> Resource<T>): LoadState<T> {
        return when (
            val result = withContext(Dispatchers.IO) { dataLoader.invoke() }
        ) {
            is Resource.Success -> LoadState.Success(result.data)
            is Resource.Error -> LoadState.Error(result.message)
        }
    }
}