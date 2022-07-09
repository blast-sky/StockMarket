package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.Resource

interface DataLoader {

    suspend fun <T> getData(
        casher: suspend () -> Resource<T>,
        saver: suspend (T) -> Unit,
        remoter: suspend () -> Resource<T>
    ): Resource<T> {
        val cashed = casher.invoke()
        if (cashed is Resource.Success) return cashed

        val remote = remoter.invoke()
        if (remote is Resource.Success) {
            saver.invoke(remote.data)
            return remote
        }

        return Resource.Error(
            "Local: ${(cashed as Resource.Error).message}\n" +
                    "Remote: ${(remote as Resource.Error).message}"
        )
    }
}