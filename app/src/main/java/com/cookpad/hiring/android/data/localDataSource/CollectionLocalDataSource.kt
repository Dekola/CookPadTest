package com.cookpad.hiring.android.data.localDataSource

import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDao
import javax.inject.Inject

class CollectionLocalDataSource @Inject constructor(
    private val favouriteDao: FavouriteCollectionDao,
) {

}