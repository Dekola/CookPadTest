package com.cookpad.hiring.android.data.remoteDataSource

import com.cookpad.hiring.android.data.api.CookpadHiringService
import com.cookpad.hiring.android.data.dtos.CollectionDTO
import javax.inject.Inject

class CollectionRemoteDataSource @Inject constructor(private val cookpadService: CookpadHiringService) {

    suspend fun getCollectionList(): List<CollectionDTO> {
        return cookpadService.getCollections()
    }
}