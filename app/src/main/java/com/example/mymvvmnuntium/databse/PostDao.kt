package com.example.mymvvmnuntium.databse

import androidx.room.*
import com.example.mymvvmnuntium.modul.ItemDB
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {
    @Query("SELECT COUNT() FROM ArticleDB Where title = :id")
    fun findArticleById(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(article: ArticleDB)

    @Query("DELETE FROM ArticleDB WHERE title = :articleDB")
    suspend fun deletedPost(articleDB: String)

    @Query("SELECT * FROM ArticleDB")
    fun getAllPost(): Flow<List<ArticleDB>>

    @Query("SELECT title FROM ArticleDB")
    fun getAllTitles(): Flow<List<String>>

    @Query("SELECT * FROM ItemDB")
    fun getAllSD(): Flow<List<ItemDB>>


    @Query("SELECT name FROM ItemDB")
    fun getAllSelected(): List<String>

    @Query("SELECT COUNT() FROM ItemDB Where name = :id")
    fun findSelectedId(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSt(itemDB: ItemDB)

    @Query("DELETE FROM ItemDB WHERE name = :itemDB")
    suspend fun deletedSelected(itemDB: String)
}