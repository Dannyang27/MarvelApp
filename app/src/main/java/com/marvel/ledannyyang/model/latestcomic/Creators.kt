package com.marvel.ledannyyang.model.latestcomic


import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("items")
    val items: List<Creator>
)