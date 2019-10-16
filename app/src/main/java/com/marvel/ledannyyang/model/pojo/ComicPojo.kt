package com.marvel.ledannyyang.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comic")
class ComicPojo(@PrimaryKey
                val id: Int = -1,
                val title: String? = "",
                val description: String? = "",
                val format: String? = "",
                val upc: String? = "",
                val issueNumber: Double? = -1.0,
                val price: Double? = -1.0,
                val thumbnail: String? = "",
                val thumbnailExtension: String? = "",
                val isFavourite: Boolean? = false)