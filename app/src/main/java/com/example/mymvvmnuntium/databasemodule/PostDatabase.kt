package com.example.mymvvmnuntium.databasemodule

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymvvmnuntium.databasemodul.Post
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.databse.PostDao
import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.modul.newss.Article

@Database(entities = [ArticleDB::class, ItemDB::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getPostDao(): PostDao
}