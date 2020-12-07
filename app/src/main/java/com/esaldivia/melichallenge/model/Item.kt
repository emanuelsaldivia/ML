package com.esaldivia.melichallenge.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id: String,
    @SerializedName("title") val name: String)