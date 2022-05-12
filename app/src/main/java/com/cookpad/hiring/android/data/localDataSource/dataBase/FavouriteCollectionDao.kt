package com.cookpad.hiring.android.data.localDataSource.dataBase

import androidx.room.*

@Dao
interface FavouriteCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCollection(favouriteEntity: FavouriteCollectionEntity)

    @Delete
    suspend fun deleteFavouriteCollection(favouriteEntity: FavouriteCollectionEntity)

}