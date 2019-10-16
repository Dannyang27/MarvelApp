package com.marvel.ledannyyang.model.latestcomic


import com.google.gson.annotations.SerializedName

data class ComicJSON(
    @SerializedName("data")
    val data: ComicItem? = null
)