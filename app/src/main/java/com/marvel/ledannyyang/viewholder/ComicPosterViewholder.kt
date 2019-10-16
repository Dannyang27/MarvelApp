package com.marvel.ledannyyang.viewholder

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.activity.ComicInfoActivity
import com.marvel.ledannyyang.model.pojo.ComicPojo
import com.marvel.ledannyyang.retrofit.RetrofitClient
import com.squareup.picasso.Picasso

class ComicPosterViewholder(view: View): RecyclerView.ViewHolder(view){
    var id = -1
    val poster: ImageView? = view.findViewById(R.id.comic_grid_poster)
    val title: TextView? = view.findViewById(R.id.comic_grid_title)

    init {
        view.setOnClickListener {
            val intent = Intent(it.context, ComicInfoActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    fun setViewholder(comic: ComicPojo){
        this.id = comic.id
        this.title?.text = comic.title

        this.poster?.let{
            val url = "${comic.thumbnail}/portrait_uncanny.${comic.thumbnailExtension}"
                .replace("http","https")
            Picasso.get()
                .load(url)
                .into(it)
        }
    }
}