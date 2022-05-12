package com.cookpad.hiring.android.data.mapper

import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionEntity

internal fun Collection.toEntity(): FavouriteCollectionEntity =
    FavouriteCollectionEntity(
        id = id,
        title = title,
        description = description,
        recipeCount = recipeCount,
        previewImageUrls = previewImageUrls,
        isFavorite = isFavorite
    )