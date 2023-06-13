package com.example.mymvvmnuntium.modul.newss

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymvvmnuntium.databse.ArticleDB


data class Article(
    val id: Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
):java.io.Serializable {
    fun getArticleDb(): ArticleDB =
        ArticleDB(
            this.id,
            this.author,
            this.content,
            this.description,
            this.publishedAt,
            this.title,
            this.url,
            this.urlToImage
        )
}