package com.example.mymvvmnuntium.databse

import androidx.room.*
import com.example.mymvvmnuntium.databasemodul.Post
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Delete
    suspend fun deletedPost(post: Post)

    @Query("SELECT * FROM post")
    fun getAllPost(): Flow<List<Post>>


}