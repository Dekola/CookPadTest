package com.cookpad.hiring.android.di

import android.content.Context
import androidx.room.Room
import com.cookpad.hiring.android.common.DATABASE_NAME
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context, FavouriteCollectionDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: FavouriteCollectionDatabase) = database.favouriteCollectionDao()
}