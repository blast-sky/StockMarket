package com.example.stockmarket.di

import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.data.repository.TestStockRepository
import com.example.stockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class RemoteModule {

    @Provides
    fun provideStockMarketService(): StockMarketService {
        return Retrofit
            .Builder()
            .baseUrl(StockMarketService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockMarketService::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindRemoteModule {

//    @Binds
//    abstract fun bindStockRepository(repositoryImpl: StockRepositoryImpl): StockRepository

    @Binds
    abstract fun bindStockRepository(repository: TestStockRepository): StockRepository
}