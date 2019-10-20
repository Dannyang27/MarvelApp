package com.marvel.ledannyyang.model.marvelComic


import com.google.gson.annotations.SerializedName

data class ComicInfo(
    @SerializedName("creators")
    val creators: Creators? = null,
    @SerializedName("dates")
    val dates: List<Date>? = null,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("diamondCode")
    val diamondCode: String = "",
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("pageCount")
    val pageCount: Int = -1,
    @SerializedName("prices")
    val prices: List<Price>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("upc")
    val upc: String = ""
)