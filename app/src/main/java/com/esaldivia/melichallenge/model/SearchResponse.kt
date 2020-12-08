package com.esaldivia.melichallenge.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
        @SerializedName("results") val itemList: ArrayList<Item>)