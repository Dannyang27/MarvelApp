package com.marvel.ledannyyang.model.latestcomic

import com.google.gson.annotations.SerializedName

data class ComicItem(
    @SerializedName("count")
    val count: Int = -1,
    @SerializedName("limit")
    val limit: Int = -1,
    @SerializedName("offset")
    val offset: Int = -1,
    @SerializedName("results")
    val results: List<Comic>? = null,
    @SerializedName("total")
    val total: Int = -1
)