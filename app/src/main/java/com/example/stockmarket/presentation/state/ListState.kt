package com.example.stockmarket.presentation.state

sealed class ListState<T> {

    class Success<T>(val data: T) : ListState<T>()

    class Error<T>(val message: String) : ListState<T>()

    class Loading<T> : ListState<T>()

    class Refreshing<T> : ListState<T>()

    class Empty<T> : ListState<T>()
}
