package com.cookpad.hiring.android.data

import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.data.localDataSource.CollectionLocalDataSource
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDao
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionEntity
import com.cookpad.hiring.android.data.mapper.toEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class CollectionListLocalDataSourceTest {

    private lateinit var collectionListLocalDataSource: CollectionLocalDataSource

    @Mock
    lateinit var favouriteDao: FavouriteCollectionDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        collectionListLocalDataSource = CollectionLocalDataSource(favouriteDao)
    }

    @Test
    fun `verify favorite is deleted when selected collection is already favorite`() = runTest {
        val favouriteEntity = getFavouriteEntity(true)
        collectionListLocalDataSource.favoriteSelection(favouriteEntity)
        verify(favouriteDao).deleteFavouriteCollection(any())
    }

    @Test
    fun `verify favorite is added when selected collection is not favorite`() = runTest {
        val favouriteEntity = getFavouriteEntity(false)
        collectionListLocalDataSource.favoriteSelection(favouriteEntity)
        verify(favouriteDao).insertFavouriteCollection(any())
    }

    private fun getFavouriteEntity(isFavorite: Boolean): FavouriteCollectionEntity = Collection(
        id = 1,
        title = "title",
        description = "disc",
        recipeCount = 3,
        previewImageUrls = listOf("urls"),
        isFavorite = isFavorite
    ).toEntity()
}