package com.example.mymvvmnuntium.repository

import com.example.mymvvmnuntium.databasemodul.Post
import com.example.mymvvmnuntium.databse.PostDao
import com.example.mymvvmnuntium.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {
    fun getPost()= flow {
        emit(apiService.getAllNews())
    }

    suspend fun insertPost(post: Post){
        postDao.insertPost(post)
    }

    suspend fun delete (post: Post){
        postDao.deletedPost(post)
    }
    fun getAllPost():Flow<List<Post>> =postDao.getAllPost()
}