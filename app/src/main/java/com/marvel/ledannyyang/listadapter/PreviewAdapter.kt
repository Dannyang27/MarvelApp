package com.marvel.ledannyyang.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.viewholder.ComicPreviewViewholder

class PreviewAdapter (private val images: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_comic_preview, parent, false)
        return ComicPreviewViewholder(view)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}