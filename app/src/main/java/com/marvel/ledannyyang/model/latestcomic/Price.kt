package com.marvel.ledannyyang.model.latestcomic


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Double = 1.0,
    @SerializedName("type")
    val type: String = ""
)