package com.marvel.ledannyyang.model.comic


import com.google.gson.annotations.SerializedName

data class ComicItem(
    @SerializedName("results")
    val results: List<ComicInfo>? = null
)