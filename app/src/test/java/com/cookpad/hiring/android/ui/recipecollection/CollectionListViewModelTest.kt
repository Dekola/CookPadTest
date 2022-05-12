package com.cookpad.hiring.android.ui.recipecollection

import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.data.repository.CollectionListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class CollectionListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var collectionListViewModel: CollectionListViewModel
    private val mockCollectionListRepository: CollectionListRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given collections are loaded When data source is success Then emit success view state`() {
        val expectedCollections = getCollections()
        runTest {
            whenever(mockCollectionListRepository.getCollectionList()).thenReturn(
                expectedCollections)
            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            val stateFlow = collectionListViewModel.viewState.first()

            assertEquals(stateFlow, CollectionListViewState.Success(expectedCollections))
        }
    }


    @Test
    fun `Given collections are loaded When data source is error Then emit error view state`() {
        runTest {
            whenever(mockCollectionListRepository.getCollectionList()).thenThrow(RuntimeException(""))
            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            val stateFlow = collectionListViewModel.viewState.first()

            assertEquals(stateFlow, CollectionListViewState.Error)
        }
    }


    @Test
    fun `test viewModel can dispatch call to repository for adding collection to favorite`() {
        runTest {
            initGetCollectionList()

            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            collectionListViewModel.favoriteSelection(getCollection())

            verify(mockCollectionListRepository).favoriteSelection(any())
        }
    }

    @Test
    fun `verify adding collection to favorite refreshes favorite list while viewing favorite`() {
        runTest {
            initGetCollectionList()

            whenever(mockCollectionListRepository.loadFavoriteCollections()).thenReturn(
                getCollections())

            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            // switch to viewing only favorite recipes
            collectionListViewModel.toggleCollectionOption(true)

            collectionListViewModel.favoriteSelection(getCollection())

            verify(mockCollectionListRepository).favoriteSelection(any())
        }
    }

    @Test
    fun `verify toggle selection to show all collections loads all collections`() {
        runTest {
            initGetCollectionList()

            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            collectionListViewModel.toggleCollectionOption(false)

            verify(mockCollectionListRepository, times(2)).getCollectionList()
        }
    }

    @Test
    fun `verify toggle selection to show only favorite collections loads all favorite collections`() {
        runTest {
            initGetCollectionList()

            whenever(mockCollectionListRepository.loadFavoriteCollections()).thenReturn(
                getCollections())

            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            collectionListViewModel.toggleCollectionOption(true)

            verify(mockCollectionListRepository).loadFavoriteCollections()
        }
    }

    @Test
    fun `Given favorite collections are loaded Then emit success view state`() {
        runTest {
            initGetCollectionList()

            val favoriteCollections = getFavoriteCollection()
            whenever(mockCollectionListRepository.loadFavoriteCollections()).thenReturn(
                favoriteCollections)

            collectionListViewModel = CollectionListViewModel(mockCollectionListRepository)

            collectionListViewModel.toggleCollectionOption(true)

            val stateFlow = collectionListViewModel.viewState.first()

            assertEquals(stateFlow, CollectionListViewState.Success(favoriteCollections))

        }
    }

    // This was set to avoid code duplication
    private fun initGetCollectionList() = runTest {
        val expectedCollections = getCollections()
        whenever(mockCollectionListRepository.getCollectionList()).thenReturn(
            expectedCollections)
    }


    private fun getCollections(): List<Collection> {
        return listOf(getCollection())
    }

    private fun getCollection() = Collection(
        id = 1,
        title = "title",
        description = "disc",
        recipeCount = 3,
        previewImageUrls = listOf("urls"),
        isFavorite = false
    )

    private fun getFavoriteCollection(): List<Collection>{
        val collection = Collection(
            id = 1,
            title = "title",
            description = "disc",
            recipeCount = 3,
            previewImageUrls = listOf("urls"),
            isFavorite = true
        )
        return listOf(collection)

    }
}