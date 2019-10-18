package com.marvel.ledannyyang.model


import com.google.gson.annotations.SerializedName

data class ComicDescription(
    @SerializedName("comics")
    val comics: List<ComicDescriptionItem>? = null
)