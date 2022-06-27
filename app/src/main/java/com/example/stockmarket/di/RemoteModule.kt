package com.example.stockmarket.di

import com.example.stockmarket.data.remote.StockMarketService
import com.example.stockmarket.data.repository.TestStockRepository
import com.example.stockmarket.domain.repository.StockRepository
import com.google.gson.GsonBuilder
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
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'00:00:00+0000")
            .create()

        return Retrofit
            .Builder()
            .baseUrl(StockMarketService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
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