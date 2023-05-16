package com.example.mymvvmnuntium.modul

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)