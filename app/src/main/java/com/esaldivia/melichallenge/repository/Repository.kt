package com.esaldivia.melichallenge.repository

import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.utils.Constants
import java.lang.Exception
import javax.inject.Inject

// todo extender interfaz?
class Repository @Inject constructor(val itemApi: SearchItemApi){

    suspend fun searchItemByName(siteId: String = Constants.SITE_ID_ARG, name: String): List<Item> {
        return itemApi.searchItemByName(siteId, name) // todo necesito try catch?
    }

    fun getItems(): List<Item> {
        val itemList = mutableListOf<Item>()
        itemList.add(Item("id", "name1"))
        itemList.add(Item("id", "name2"))
        itemList.add(Item("id", "name3"))
        itemList.add(Item("id", "name4"))
        itemList.add(Item("id", "name5"))
        itemList.add(Item("id", "name6"))
        itemList.add(Item("id", "name7"))
        itemList.add(Item("id", "name8"))
        itemList.add(Item("id", "name9"))
        itemList.add(Item("id", "name0"))
        itemList.add(Item("id", "name"))

        return itemList
    }
}