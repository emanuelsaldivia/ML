package com.esaldivia.melichallenge.network.SearchItem

import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchItemApi {

    @GET("/sites/{siteID}/search")
    suspend fun searchItemByName(
        @Path("siteID") siteId: String,
        @Query("q") name: String): SearchResponse
}