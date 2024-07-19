package com.example.dailyquotes.dataclass

data class Quote(
    val q: String,
    val a: String,
    val h: String,
    var isFavorite: Boolean = false
)
