package com.cookpad.hiring.android.data.repository

import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.data.localDataSource.CollectionLocalDataSource
import com.cookpad.hiring.android.data.remoteDataSource.CollectionRemoteDataSource
import javax.inject.Inject


class CollectionListRepository @Inject constructor(
    private val collectionListRemoteDataSource: CollectionRemoteDataSource,
    private val collectionListLocalDataSource: CollectionLocalDataSource,
) {

    suspend fun getCollectionList(): List<Collection> =
        collectionListRemoteDataSource.getCollectionList().map { collectionDTO ->
            with(collectionDTO) {
                Collection(
                    id = id,
                    title = title,
                    description = description,
                    recipeCount = recipeCount,
                    previewImageUrls = previewImageUrls
                )
            }
        }

    fun onCleared() {
        collectionListRemoteDataSource.onCleared()
    }
}