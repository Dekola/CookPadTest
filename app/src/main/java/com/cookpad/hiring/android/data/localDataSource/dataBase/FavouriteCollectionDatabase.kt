package com.cookpad.hiring.android.data.localDataSource.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FavouriteCollectionEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(FavouriteCollectionTypeConverter::class)
abstract class FavouriteCollectionDatabase : RoomDatabase() {
    abstract fun favouriteCollectionDao(): FavouriteCollectionDao
}