package com.marvel.ledannyyang.model.marvelComic


import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("role")
    val role: String = ""
)