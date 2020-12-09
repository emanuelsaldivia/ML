package com.esaldivia.melichallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Item(
    @SerializedName("id") val id: String,
    @SerializedName("title") val name: String,
    @SerializedName("currency_id") val currency: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("permalink") val url: String)