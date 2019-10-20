package com.marvel.ledannyyang.model.marvelComic


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("type")
    val type: String = ""
)