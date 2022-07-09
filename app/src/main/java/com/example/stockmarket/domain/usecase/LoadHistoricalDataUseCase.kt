package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.domain.repository.RemoteStockRepository
import com.example.stockmarket.domain.Resource
import javax.inject.Inject

class LoadHistoricalDataUseCase @Inject constructor(
    private val remoteRepository: RemoteStockRepository,
    private val localRepository: LocalStockRepository
) : DataLoader {

    suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>> =
        getData(
            casher = { localRepository.getHistoricalData(symbol) },
            saver = { localRepository.insertHistoricalData(it) },
            remoter = { remoteRepository.getHistoricalData(symbol) }
        )
}