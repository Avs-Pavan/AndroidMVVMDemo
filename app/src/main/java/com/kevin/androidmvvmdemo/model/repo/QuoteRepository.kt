package com.kevin.androidmvvmdemo.model.repo

import com.kevin.androidmvvmdemo.model.repo.api.QuoteApiService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteApiService: QuoteApiService) :
    IQuoteRepository {
    override suspend fun getRandomQuotation() = quoteApiService.getRandomQuotation()
}