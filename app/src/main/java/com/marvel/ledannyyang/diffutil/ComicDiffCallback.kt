package com.marvel.ledannyyang.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.marvel.ledannyyang.model.Comic

class ComicDiffCallback(private val oldComics: MutableList<Comic>, private val newComics: MutableList<Comic>): DiffUtil.Callback(){
    override fun getOldListSize() = oldComics.size
    override fun getNewListSize() = newComics.size
    override fun areItemsTheSame(oldPos: Int, newPos: Int) = oldComics[oldPos].id == newComics[newPos].id
    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldComics[oldPos] == newComics[newPos]
}