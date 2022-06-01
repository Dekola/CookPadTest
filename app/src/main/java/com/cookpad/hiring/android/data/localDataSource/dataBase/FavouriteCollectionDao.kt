package com.cookpad.hiring.android.data.localDataSource.dataBase

import androidx.room.*

@Dao
interface FavouriteCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCollection(favouriteEntity: FavouriteCollectionEntity)

    @Delete
    suspend fun deleteFavouriteCollection(favouriteEntity: FavouriteCollectionEntity)

    @Query("SELECT Id FROM favourite_collection_table")
    suspend fun readFavouriteCollectionIDS(): List<Int>

    @Query("SELECT * FROM favourite_collection_table ORDER BY Id ASC")
    suspend fun readFavouriteCollection(): List<FavouriteCollectionEntity>
}