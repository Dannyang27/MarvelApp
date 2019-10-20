package com.marvel.ledannyyang.model.shortBoxed


import com.google.gson.annotations.SerializedName

data class ComicDescription(
    @SerializedName("comics")
    val comics: List<ComicDescriptionItem>? = null
)