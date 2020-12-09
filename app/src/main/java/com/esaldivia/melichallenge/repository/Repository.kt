package com.esaldivia.melichallenge.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.liveData
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.utils.Constants
import com.esaldivia.melichallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

open class Repository @Inject constructor(val itemApi: SearchItemApi){

    suspend fun searchItemByName(siteId: String = Constants.SITE_ID_ARG, name: String): List<Item> = runBlocking {

        val result = itemApi.searchItemByName(siteId, name)
        result.itemList
    }

    suspend fun search(siteId: String = Constants.SITE_ID_ARG, name: String)
            = itemApi.searchItemByName(siteId, name)

    open fun renombrar(name: String , context: Context)  = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.succces(data = search(name = name)))
        } catch (exception: Exception) {
            val context = context.applicationContext
            emit(Resource.error(null, exception.message ?: context.getString(R.string.unexpected_error)))
        }
    }
}