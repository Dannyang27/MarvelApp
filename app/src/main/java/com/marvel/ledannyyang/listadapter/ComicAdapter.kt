package com.marvel.ledannyyang.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.diffutil.ComicDiffCallback
import com.marvel.ledannyyang.model.pojo.ComicPojo
import com.marvel.ledannyyang.viewholder.ComicPosterViewholder
import com.marvel.ledannyyang.viewholder.ComicViewholder

class ComicAdapter(private val gridLayoutManager: GridLayoutManager? = null,
                   private val comics: MutableList<ComicPojo>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    enum class ViewType {
        LIST, GRID
    }

    override fun getItemViewType(position: Int): Int {
        return if(gridLayoutManager?.spanCount == 1){
            ViewType.LIST.ordinal
        }else{
            ViewType.GRID.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            ViewType.LIST.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_comic, parent, false)

                return ComicViewholder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_comic_poster, parent, false)

                return ComicPosterViewholder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comic = comics[position]

        when(holder){
            is ComicViewholder -> holder.setViewholder(comic)
            is ComicPosterViewholder -> holder.setViewholder(comic)
        }
    }

    override fun getItemCount() = comics.size

    fun updateList( newComics: MutableList<ComicPojo>){
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(ComicDiffCallback(comics, newComics))
        comics.clear()
        comics.addAll(newComics)
        diffResult.dispatchUpdatesTo(this)
    }
}