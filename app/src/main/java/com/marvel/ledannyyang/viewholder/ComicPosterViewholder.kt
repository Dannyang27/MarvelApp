package com.marvel.ledannyyang.viewholder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.activity.ComicInfoActivity
import com.marvel.ledannyyang.model.Comic
import com.squareup.picasso.Picasso

class ComicPosterViewholder(view: View): RecyclerView.ViewHolder(view){
    var id = -1
    val poster: ImageView? = view.findViewById(R.id.comic_grid_poster)
    val title: TextView? = view.findViewById(R.id.comic_grid_title)

    init {
        view.setOnClickListener {
            val intent = Intent(it.context, ComicInfoActivity::class.java)
            intent.putExtra("comicId", this.id.toString())
            it.context.startActivity(intent)
        }
    }

    fun setViewholder(comic: Comic){
        this.id = comic.id
        this.title?.text = comic.title

        this.poster?.let{
            val url = "${comic.imagePath}/portrait_uncanny.${comic.imageExt}"
                .replace("http","https")
            Picasso.get()
                .load(url)
                .into(it)
        }
    }
}