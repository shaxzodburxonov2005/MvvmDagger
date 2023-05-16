package com.example.mymvvmnuntium.utils


import com.example.mymvvmnuntium.modul.Article
import com.example.mymvvmnuntium.modul.News

sealed class Resource {

    object Loading : Resource()
    data class Success(val list: List<News>) : Resource()
    data class Error(val message: String) : Resource()
}