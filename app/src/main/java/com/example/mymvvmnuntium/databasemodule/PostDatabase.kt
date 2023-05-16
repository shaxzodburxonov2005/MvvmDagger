package com.example.mymvvmnuntium.databasemodule

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymvvmnuntium.databasemodul.Post
import com.example.mymvvmnuntium.databse.PostDao

@Database(entities = [Post::class] , version = 1)
abstract class PostDatabase:RoomDatabase() {
    abstract fun getPostDao():PostDao
}