package com.kevin.androidmvvmdemo.model.repo

import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import retrofit2.Response

interface IQuoteRepository {
    suspend fun getRandomQuotation(): Response<AnimeQuotation>
}