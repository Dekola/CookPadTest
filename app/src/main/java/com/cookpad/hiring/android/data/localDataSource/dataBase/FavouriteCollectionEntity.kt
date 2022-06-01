package com.cookpad.hiring.android.data.localDataSource.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.hiring.android.common.FAVOURITE_COLLECTION_TABLE

@Entity(tableName = FAVOURITE_COLLECTION_TABLE)
class FavouriteCollectionEntity(
    @ColumnInfo(name = "Id")
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val recipeCount: Int,
    val isFavorite: Boolean,
    val previewImageUrls: List<String> = emptyList()
)