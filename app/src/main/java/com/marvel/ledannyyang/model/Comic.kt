package com.marvel.ledannyyang.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comic")
class Comic(@PrimaryKey
            val id: Int = -1,
            val title: String? = null,
            val upcCode: String? = null,
            val price: Double? = null,
            val imagePath: String? = null,
            val imageExt: String? = null,
            val diamondCode: String? = null,
            val onsaleDate: String? = null,
            val pages: Int? = null,
            var description: String? = null,
            var credits: String? = null)

