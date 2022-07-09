package com.example.stockmarket.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.stockmarket.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoadStateProvider<T> {

    var state by mutableStateOf<LoadState<T>>(LoadState.Empty())

    suspend fun loadData(
        baseState: LoadState<T> = LoadState.Loading(),
        dataLoader: suspend () -> Resource<T>
    ) {
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