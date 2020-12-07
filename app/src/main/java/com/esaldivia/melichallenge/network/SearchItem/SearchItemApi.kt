package com.esaldivia.melichallenge.network.SearchItem

import com.esaldivia.melichallenge.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchItemApi {

    @GET("/sites/{siteID}/search?q={name}")
    suspend fun searchItemByName(
        @Path("siteID") siteId: String,
        @Path("name") name: String): List<Item> // todo revisar return type / networkResponse?
}