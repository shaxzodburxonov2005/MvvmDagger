package com.example.mymvvmnuntium.repository

import android.util.Log
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.databse.PostDao
import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {
    fun getPost(query: String) = flow {
        Log.d("VVV", "getPost: ${apiService.getAllNews(query).body()?.articles}")
        emit(apiService.getAllNews(query).body()?.articles)
    }

    suspend fun insertPost(article: ArticleDB) {
        postDao.insertPost(article)
    }


    suspend fun delete(articleDb: String) {
        postDao.deletedPost(articleDb)
    }

    fun getAllPost(): Flow<List<ArticleDB>> = postDao.getAllPost()
    fun getAllSD(): Flow<List<ItemDB>> = postDao.getAllSD()

    fun getAllTitles()= postDao.getAllTitles()


    fun findById(id: String) = postDao.findArticleById(id)

    fun findBySelected(id: String) = postDao.findSelectedId(id)
    fun getAllSelected() = postDao.getAllSelected()

    suspend fun insertSD(itemDB: ItemDB) {
        postDao.insertSt(itemDB)
    }

    suspend fun deleteSelected(itemDB: String) {
        postDao.deletedSelected(itemDB)
    }
}