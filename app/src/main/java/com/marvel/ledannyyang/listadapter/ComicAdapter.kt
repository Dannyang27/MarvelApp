package com.marvel.ledannyyang.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.viewholder.ComicPosterViewholder
import com.marvel.ledannyyang.viewholder.ComicViewholder

class ComicAdapter(private val gridLayoutManager: GridLayoutManager? = null,
                   private val comics: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

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
        //TODO
    }

    override fun getItemCount() = comics.size
}