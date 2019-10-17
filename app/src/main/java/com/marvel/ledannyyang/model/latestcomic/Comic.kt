package com.marvel.ledannyyang.model.latestcomic

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("format")
    val format: String = "",
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("prices")
    val prices: List<Price>? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("creators")
    val creators: Creators? = null,
    @SerializedName("pageCount")
    val pageCount: Int = -1,
    @SerializedName("upc")
    val upc: String = "",
    @SerializedName("issueNumber")
    val issueNumber: Double = -1.0
)