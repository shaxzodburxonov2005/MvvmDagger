package com.example.mymvvmnuntium.databasemodule

import android.content.Context
import androidx.room.Room
import com.example.mymvvmnuntium.databse.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context):PostDatabase{
        return Room.databaseBuilder(context,PostDatabase::class.java,"post_dp")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(postDatabase: PostDatabase):PostDao{
        return postDatabase.getPostDao()
    }
}