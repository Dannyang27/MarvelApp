package com.marvel.ledannyyang.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comic")
class ComicPojo(@PrimaryKey
                val id: Int = -1,
                val title: String? = "",
                val description: String? = "",
                val upc: String? = "",
                val date: String? = "",
                val diamondCode: String? = "",
                val price: Double? = -1.0,
                val thumbnail: String? = "",
                val thumbnailExtension: String? = "",
                val images: String? = "",
                val pages: Int = -1,
                val writer: String? = "",
                val artBy: String? = "")