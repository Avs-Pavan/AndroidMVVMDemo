package com.kevin.androidmvvmdemo.di.modules

import com.kevin.androidmvvmdemo.model.repo.IQuoteRepository
import com.kevin.androidmvvmdemo.model.repo.QuoteRepository
import com.kevin.androidmvvmdemo.model.repo.api.QuoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(quoteApiService: QuoteApiService): IQuoteRepository {
        return QuoteRepository(quoteApiService)
    }
}