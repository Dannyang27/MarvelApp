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
import com.marvel.ledannyyang.room.MyRoomDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext

class ComicPosterViewholder(view: View): RecyclerView.ViewHolder(view), CoroutineScope{
    override val coroutineContext = Dispatchers.IO + Job()
    var diamondCode = ""
    val poster: ImageView? = view.findViewById(R.id.comic_grid_poster)
    val title: TextView? = view.findViewById(R.id.comic_grid_title)

    init {
        view.setOnClickListener {
            val intent = Intent(it.context, ComicInfoActivity::class.java)
            intent.putExtra("diamondCode", diamondCode)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                it.context as Activity, poster as View, it.context.getString(R.string.image_transition))
            it.context.startActivity(intent, options.toBundle())
        }

        view.setOnLongClickListener {
            launch {
                val roomDatabase = MyRoomDatabase.getMyRoomDatabase(it.context)
                val comic = roomDatabase?.getComicByDiamondCode(diamondCode)
                comic?.isFavourite = true
                roomDatabase?.updateComicPreview(comic!!)

                withContext(Dispatchers.Main){
                    it.context.toast("Comic added to favourites")
                }
            }

            true
        }
    }

    fun setViewholder(comic: Comic){
        this.diamondCode = comic.diamondCode.toString()
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