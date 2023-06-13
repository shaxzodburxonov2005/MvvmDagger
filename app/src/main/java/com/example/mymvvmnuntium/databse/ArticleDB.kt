package com.example.mymvvmnuntium.databse

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymvvmnuntium.modul.newss.Article


@Entity
data class ArticleDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
):java.io.Serializable