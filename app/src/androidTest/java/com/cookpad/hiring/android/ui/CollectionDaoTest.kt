package com.cookpad.hiring.android.ui

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDao
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionDatabase
import com.cookpad.hiring.android.data.localDataSource.dataBase.FavouriteCollectionEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CollectionDaoTest {
    private lateinit var favouriteCollectionDao: FavouriteCollectionDao
    private lateinit var favouriteCollectionDatabase: FavouriteCollectionDatabase

    @Before
    fun createDb() {
        val application = ApplicationProvider.getApplicationContext<Application>()

        favouriteCollectionDatabase = Room.inMemoryDatabaseBuilder(
            application, FavouriteCollectionDatabase::class.java).build()
        favouriteCollectionDao = favouriteCollectionDatabase.favouriteCollectionDao()
    }

    @Test
    fun insertAndRetrieveFavouriteCollection() = runBlocking {
        val favouriteEntity = createCollectionEntity(1)

        favouriteCollectionDao.insertFavouriteCollection(favouriteEntity)
        val favorites = favouriteCollectionDao.readFavouriteCollection()

        Assert.assertEquals(favorites[0].id, favouriteEntity.id)
    }

    @Test
    fun insertAndRetrieveFavouriteIDCollection() = runBlocking {
        val favouriteEntity = createCollectionEntity(1)

        favouriteCollectionDao.insertFavouriteCollection(favouriteEntity)
        val favorites = favouriteCollectionDao.readFavouriteCollectionIDS()

        Assert.assertEquals(favorites[0], favouriteEntity.id)
    }

    @Test
    fun insertAndDeleteFavouriteCollection() = runBlocking {
        val favouriteEntity1 = createCollectionEntity(1)
        val favouriteEntity2 = createCollectionEntity(2)

        favouriteCollectionDao.insertFavouriteCollection(favouriteEntity1)
        favouriteCollectionDao.insertFavouriteCollection(favouriteEntity2)

        Assert.assertEquals(favouriteCollectionDao.readFavouriteCollection().size, 2)

        favouriteCollectionDao.deleteFavouriteCollection(favouriteEntity1)

        Assert.assertEquals(favouriteCollectionDao.readFavouriteCollection().size, 1)
    }

    @After
    fun closeDb() {
        favouriteCollectionDatabase.close()
    }

    private fun createCollectionEntity(id: Int) = FavouriteCollectionEntity(
        id = id,
        title = "title",
        description = "description",
        recipeCount = 2,
        previewImageUrls = listOf("previewImageUrls"),
        isFavorite = true
    )
}