package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.domain.repository.RemoteStockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class LoadTickersUseCase @Inject constructor(
    private val remoteRepository: RemoteStockRepository,
    private val localRepository: LocalStockRepository
)  : DataLoader {

    suspend fun loadTickers(): Resource<List<Ticker>> =
        getData(
            casher = { localRepository.getTickers() },
            saver = { localRepository.insertTickers(it) },
            remoter = { remoteRepository.getTickers() }
        )
}