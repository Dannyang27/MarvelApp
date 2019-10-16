package com.marvel.ledannyyang.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.marvel.ledannyyang.model.pojo.ComicPojo

class ComicDiffCallback(private val oldComics: MutableList<ComicPojo>, private val newComics: MutableList<ComicPojo>): DiffUtil.Callback(){
    override fun getOldListSize() = oldComics.size
    override fun getNewListSize() = newComics.size
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldComics[oldPos].id == newComics[newPos].id
    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldComics[oldPos] == newComics[newPos]
}