package com.cookpad.hiring.android.data.mapper

import com.cookpad.hiring.android.data.dtos.CollectionDTO
import com.cookpad.hiring.android.data.entities.Collection

internal fun CollectionDTO.toPresentation(isFavorite: Boolean): Collection {
    return Collection(
        id = id,
        title = title,
        description = description,
        recipeCount = recipeCount,
        previewImageUrls = previewImageUrls,
        isFavorite = isFavorite
    )
}