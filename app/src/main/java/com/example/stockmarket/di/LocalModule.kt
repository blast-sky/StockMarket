package com.example.stockmarket.di

import android.content.Context
import androidx.room.Room
import com.example.stockmarket.data.local.ObsoleteRule
import com.example.stockmarket.data.local.StockDataBase
import com.example.stockmarket.data.repository.LocalRemoteStockRepositoryImpl
import com.example.stockmarket.domain.repository.LocalStockRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class LocaleModule {

    @Provides
    fun provideStockDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        StockDataBase::class.java,
        "stock_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideStockMarketDao(database: StockDataBase) = database.stockMarketDao()

    @Provides
    fun provideObsoleteRule(): ObsoleteRule = ObsoleteRule.Base
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindLocaleModule {

    @Binds
    abstract fun bindLocaleStockRepository(repository: LocalRemoteStockRepositoryImpl): LocalStockRepository
}