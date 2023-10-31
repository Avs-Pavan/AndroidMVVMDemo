package com.kevin.androidmvvmdemo.model.repo.api

import com.kevin.androidmvvmdemo.model.data.AnimeQuotation
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiService {


    @GET("api/random")
    suspend fun getRandomQuotation():Response<AnimeQuotation>
}