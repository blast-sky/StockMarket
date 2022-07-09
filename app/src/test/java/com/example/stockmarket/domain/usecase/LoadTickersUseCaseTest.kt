package com.example.stockmarket.domain.usecase

import com.example.stockmarket.data.repository.TestLocalStockRepository
import com.example.stockmarket.data.repository.TestRemoteStockRepository
import com.example.stockmarket.domain.Resource
import com.example.stockmarket.domain.model.StockExchange
import com.example.stockmarket.domain.model.Ticker
import com.example.stockmarket.domain.repository.LocalStockRepository
import com.example.stockmarket.domain.repository.RemoteStockRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoadTickersUseCaseTest {

    private val error = Resource.Error<List<Ticker>>("test message")
    private val bothRepoError = Resource.Error<List<Ticker>>(
        "Local: ${error.message}\nRemote: ${error.message}"
    )
    private val cashedTickers = mutableListOf<Ticker>()
    private val remoteTickers = listOf(
        Ticker("123", "12", StockExchange("123")),
        Ticker("asd", "as", StockExchange("asd")),
        Ticker("qwerty", "ty", StockExchange("qwerty")),
    )

    private val errorLocalStockRepository =
        object : LocalStockRepository by TestLocalStockRepository() {
            override suspend fun getTickers(): Resource<List<Ticker>> {
                return error
            }

            override suspend fun insertTickers(tickers: List<Ticker>) {
                cashedTickers.addAll(tickers)
            }
        }

    private val successLocalStockRepository =
        object : LocalStockRepository by errorLocalStockRepository {
            override suspend fun getTickers(): Resource<List<Ticker>> {
                return Resource.Success(cashedTickers)
            }
        }

    private val errorRemoteStockRepository =
        object : RemoteStockRepository by TestRemoteStockRepository() {
            override suspend fun getTickers(): Resource<List<Ticker>> {
                return error
            }
        }

    private val successRemoteStockRepository =
        object : RemoteStockRepository by TestRemoteStockRepository() {
            override suspend fun getTickers(): Resource<List<Ticker>> {
                return Resource.Success(remoteTickers)
            }
        }

    @Before
    fun clear() = cashedTickers.clear()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `no cash and remote data return error and empty cash`() = runTest {
        val loadTickersUseCase = LoadTickersUseCase(
            errorRemoteStockRepository,
            errorLocalStockRepository,
        )

        assertEquals(bothRepoError, loadTickersUseCase.loadTickers())
        assertTrue(cashedTickers.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `remote without cash return success`() = runTest {
        val loadTickersUseCase = LoadTickersUseCase(
            successRemoteStockRepository,
            errorLocalStockRepository,
        )

        assertEquals(Resource.Success(remoteTickers), loadTickersUseCase.loadTickers())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `no cash with remote return success and save remote to cash`() = runTest {
        val loadTickersUseCase = LoadTickersUseCase(
            successRemoteStockRepository,
            errorLocalStockRepository,
        )

        val successResult = Resource.Success(remoteTickers)
        assertEquals(successResult, loadTickersUseCase.loadTickers())
        assertEquals(remoteTickers, cashedTickers)
    }
}