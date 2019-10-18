package com.marvel.ledannyyang.model


import com.google.gson.annotations.SerializedName

data class ComicDescriptionItem(
    @SerializedName("creators")
    val creators: String = "",
    @SerializedName("description")
    val description: String =""
)