package com.esaldivia.melichallenge.repository

import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.utils.Constants
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class Repository @Inject constructor(val itemApi: SearchItemApi){

    suspend fun searchItemByName(siteId: String = Constants.SITE_ID_ARG, name: String): List<Item> = runBlocking {

        val result = itemApi.searchItemByName(siteId, name)
        result.itemList
    }

    suspend fun search(siteId: String = Constants.SITE_ID_ARG, name: String)
            = itemApi.searchItemByName(siteId, name)
}