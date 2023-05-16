package com.example.mymvvmnuntium.databasemodul

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    val author: String,
    val urlToImage: String
)
