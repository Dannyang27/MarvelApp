package com.marvel.ledannyyang.viewholder

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.activity.ComicInfoActivity
import com.marvel.ledannyyang.model.Comic
import com.squareup.picasso.Picasso

class ComicViewholder(view: View): RecyclerView.ViewHolder(view){
    var diamondCode = ""
    val poster: ImageView? = view.findViewById(R.id.comic_default_poster)
    val title: TextView? = view.findViewById(R.id.comic_default_title)
    val upcCode: TextView? = view.findViewById(R.id.comic_default_upc_code)
    val price: TextView? = view.findViewById(R.id.comic_default_price)

    init {
        view.setOnClickListener {
            val intent = Intent(it.context, ComicInfoActivity::class.java)
            intent.putExtra("diamondCode", diamondCode)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                it.context as Activity, poster as View, it.context.getString(R.string.image_transition))
            it.context.startActivity(intent, options.toBundle())
        }
    }

    fun setViewholder(comic: Comic){
        this.diamondCode = comic.diamondCode.toString()
        this.title?.text = comic.title
        this.upcCode?.text = comic.upcCode
        this.price?.text = "\$${comic.price}"
        this.poster?.let {
            val url = "${comic.imagePath}/portrait_uncanny.${comic.imageExt}"
                .replace("http","https")

            Picasso.get()
                .load(url)
                .into(it)
        }
    }
}