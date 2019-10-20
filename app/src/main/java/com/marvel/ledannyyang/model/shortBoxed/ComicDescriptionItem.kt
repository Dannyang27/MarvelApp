package com.marvel.ledannyyang.model.shortBoxed


import com.google.gson.annotations.SerializedName

data class ComicDescriptionItem(
    @SerializedName("creators")
    val creators: String = "",
    @SerializedName("description")
    val description: String =""
)