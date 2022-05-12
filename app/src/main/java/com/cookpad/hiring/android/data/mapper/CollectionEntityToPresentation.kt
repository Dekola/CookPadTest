package com.cookpad.hiring.android.data.mapper

import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionEntity
import com.cookpad.hiring.android.data.entities.Collection

internal fun FavouriteCollectionEntity.toPresentation(): Collection = Collection(id = id,
    title = title,
    description = description,
    recipeCount = recipeCount,
    previewImageUrls = previewImageUrls,
    isFavorite = true)