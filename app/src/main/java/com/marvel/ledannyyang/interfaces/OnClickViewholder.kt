package com.marvel.ledannyyang.interfaces

import android.content.Context
import com.marvel.ledannyyang.model.Comic

interface IComicViewholder {
    fun showComicInfo(context: Context)
    fun saveComic(context: Context)
    fun setViewholder(comic: Comic)
}