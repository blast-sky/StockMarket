package com.example.stockmarket.domain.usecase

import com.example.stockmarket.domain.model.Currency
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.domain.repository.RemoteStockRepository
import com.example.stockmarket.domain.Resource
import javax.inject.Inject

class LoadCurrenciesUseCase @Inject constructor(
    private val remoteRepository: RemoteStockRepository,
    private val localeRepository: LocalStockRepository
) : DataLoader {

    suspend fun getCurrencies(): Resource<List<Currency>> =
        getData(
            casher = { localeRepository.getCurrencies() },
            saver = { localeRepository.insertCurrencies(it) },
            remoter = { remoteRepository.getCurrencies() }
        )
}