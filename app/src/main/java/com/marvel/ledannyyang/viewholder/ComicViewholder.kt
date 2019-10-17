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

class ComicViewholder(view: View): RecyclerView.ViewHolder(view){
    var id = -1
    val poster: ImageView? = view.findViewById(R.id.comic_default_poster)
    val title: TextView? = view.findViewById(R.id.comic_default_title)
    val upcCode: TextView? = view.findViewById(R.id.comic_default_upc_code)
    val price: TextView? = view.findViewById(R.id.comic_default_price)

    init {
        view.setOnClickListener {
            val intent = Intent(it.context, ComicInfoActivity::class.java)
            intent.putExtra("comicId", this.id.toString())
            it.context.startActivity(intent)
        }
    }

    fun setViewholder(comic: ComicPojo){
        this.id = comic.id
        this.title?.text = comic.title
        this.upcCode?.text = comic.upc
        this.price?.text = "\$${comic.price}"
        this.poster?.let {
            val url = "${comic.thumbnail}/portrait_uncanny.${comic.thumbnailExtension}"
                .replace("http","https")

            Picasso.get()
                .load(url)
                .into(it)
        }
    }
}