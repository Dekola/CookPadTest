package com.cookpad.hiring.android.data.localDataSource

import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDao
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionEntity
import javax.inject.Inject

class CollectionLocalDataSource @Inject constructor(
    private val favouriteDao: FavouriteCollectionDao,
) {
    suspend fun readFavouriteCollectionIDS(): List<Int> {
        return favouriteDao.readFavouriteCollectionIDS()
    }

    suspend fun favoriteSelection(favouriteEntity: FavouriteCollectionEntity) {
        if (favouriteEntity.isFavorite) {
            deleteFavouriteCollection(favouriteEntity)
        } else {
            insertFavouriteCollection(favouriteEntity)
        }
    }

    private suspend fun insertFavouriteCollection(favouriteEntity: FavouriteCollectionEntity) {
        favouriteDao.insertFavouriteCollection(favouriteEntity)
    }

    private suspend fun deleteFavouriteCollection(favouriteEntity: FavouriteCollectionEntity) {
        favouriteDao.deleteFavouriteCollection(favouriteEntity)
    }

    suspend fun readFavouriteCollection(): List<FavouriteCollectionEntity> {
        return favouriteDao.readFavouriteCollection()
    }
}