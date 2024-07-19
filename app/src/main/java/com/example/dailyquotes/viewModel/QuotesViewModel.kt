package com.example.dailyquotes.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquotes.dataclass.Quote
import com.example.dailyquotes.retrofitInstance.QuotesApiService
import com.example.dailyquotes.retrofitInstance.RetrofitInstance
import kotlinx.coroutines.launch

class QuotesViewModel : ViewModel() {

    private val quotesApiService: QuotesApiService = RetrofitInstance.quoteApi

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes : LiveData<List<Quote>> = _quotes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _favoriteQuotes = MutableLiveData<List<Quote>>()
    val favoriteQuotes: LiveData<List<Quote>> = _favoriteQuotes


    init {
        fetchQuotes()
    }

    private fun fetchQuotes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = quotesApiService.getRandomQuotes()
                if (response.isSuccessful) {
                    _quotes.value = response.body()
                } else {
                    Log.e("QuotesFragment", "Failed to fetch quotes: ${response.errorBody()}")
                    _quotes.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("QuotesViewModel", "Exception: ${e.message}", e)
                _quotes.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addFavorite(quote: Quote) {
        val currentFavorites = _favoriteQuotes.value?.toMutableList()?: mutableListOf()
        currentFavorites.add(quote)
        _favoriteQuotes.value = currentFavorites
    }
    fun removeFavorite(quote: Quote) {
        val currentFavorites = _favoriteQuotes.value?.toMutableList() ?: mutableListOf()
        currentFavorites.remove(quote)
        _favoriteQuotes.value = currentFavorites
    }
}