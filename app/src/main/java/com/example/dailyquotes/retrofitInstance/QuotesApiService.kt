package com.example.dailyquotes.retrofitInstance

import com.example.dailyquotes.dataclass.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApiService {
    @GET("quotes")
    suspend fun getRandomQuotes(): Response<List<Quote>>
}
