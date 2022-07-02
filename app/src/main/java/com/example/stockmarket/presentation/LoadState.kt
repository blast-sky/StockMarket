package com.example.stockmarket.presentation

sealed class LoadState<T> {

    class Success<T>(val data: T) : LoadState<T>()

    class Error<T>(val message: String) : LoadState<T>()

    class Loading<T> : LoadState<T>()

    class Refreshing<T> : LoadState<T>()

    class Empty<T> : LoadState<T>()
}
