package com.marvel.ledannyyang.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R

class ComicPosterViewholder(view: View): RecyclerView.ViewHolder(view){
    var id = -1
    val poster: ImageView? = view.findViewById(R.id.comic_portrait)
    val title: TextView? = view.findViewById(R.id.comic_title_portrait)
}