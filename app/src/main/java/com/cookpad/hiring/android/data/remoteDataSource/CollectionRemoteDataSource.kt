package com.cookpad.hiring.android.data.remoteDataSource

import com.cookpad.hiring.android.data.api.CookpadHiringService
import com.cookpad.hiring.android.data.dtos.CollectionDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionRemoteDataSource @Inject constructor(
    private val cookpadService: CookpadHiringService,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getCollectionList(): List<CollectionDTO> {
        return withContext(dispatcher) { cookpadService.getCollections() }
    }

    fun onCleared(){
        dispatcher.cancel()
    }
}