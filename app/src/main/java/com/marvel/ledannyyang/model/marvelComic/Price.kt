package com.marvel.ledannyyang.model.marvelComic


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Double = -1.0
)