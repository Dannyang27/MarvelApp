package com.marvel.ledannyyang

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun String.getDate() = this.substring(0, 10)
fun Int.getFirstDateFromYear(): String = "$this-01-01"
fun String.intoImage(imageView: ImageView){
    Picasso.get()
        .load(this)
        .into(imageView)
}