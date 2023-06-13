package com.example.mymvvmnuntium.utils

import com.example.mymvvmnuntium.modul.newss.Article


sealed class Resource {

    object Loading : Resource()
    data class Success(val list: List<Article>) : Resource()
    data class Error(val message: String) : Resource()
}