package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.model.HistoricalData
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.domain.repository.RemoteStockRepository
import com.example.stockmarket.util.Resource
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val remoteRepository: RemoteStockRepository,
    private val localeRepository: LocalStockRepository
) : LocalStockRepository {

    override suspend fun insertCurrencies(currencies: List<Currency>) =
        localeRepository.insertCurrencies(currencies)

    override suspend fun insertHistoricalData(data: List<HistoricalData>) =
        localeRepository.insertHistoricalData(data)

    override suspend fun getCurrencies(): Resource<List<Currency>> =
        getData(
            casher = { localeRepository.getCurrencies() },
            saver = { localeRepository.insertCurrencies(it) },
            remoter = { remoteRepository.getCurrencies() }
        )

    override suspend fun getHistoricalData(symbol: String): Resource<List<HistoricalData>> =
        getData(
            casher = { localeRepository.getHistoricalData(symbol) },
            saver = { localeRepository.insertHistoricalData(it) },
            remoter = { remoteRepository.getHistoricalData(symbol) }
        )

    private suspend fun <T> getData(
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