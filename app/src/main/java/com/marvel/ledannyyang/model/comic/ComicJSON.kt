package com.marvel.ledannyyang.model.comic


import com.google.gson.annotations.SerializedName

data class ComicJSON(
    @SerializedName("data")
    val data: ComicItem? = null
)