package com.example.dailyquotes.retrofitinstance

import com.example.dailyquotes.dataclass.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApiService {
    @GET("quotes")
    suspend fun getRandomQuotes(): Response<List<Quote>>
}
