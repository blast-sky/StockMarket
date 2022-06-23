package com.example.stockmarket.di

import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.data.repository.StockRepository
import com.example.stockmarket.data.repository.StockRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class RemoteModule {

    @Provides
    fun provideStockMarketService(): StockMarketService {
        return Retrofit
            .Builder()
            .baseUrl(StockMarketService.BASE_URL)
            .build()
            .create(StockMarketService::class.java)
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class BindRemoteModule {

    @Binds
    abstract fun bindStockRepository(repositoryImpl: StockRepositoryImpl): StockRepository
}