package com.marvel.ledannyyang.viewholder

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.activity.ComicInfoActivity
import com.marvel.ledannyyang.interfaces.IComicViewholder
import com.marvel.ledannyyang.intoImage
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.room.MyRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.toast

class ComicViewholder(view: View): RecyclerView.ViewHolder(view), IComicViewholder{
    private var diamondCode = ""
    private val poster: ImageView? = view.findViewById(R.id.comic_default_poster)
    private val title: TextView? = view.findViewById(R.id.comic_default_title)
    private val upcCode: TextView? = view.findViewById(R.id.comic_default_upc_code)
    private val price: TextView? = view.findViewById(R.id.comic_default_price)

    init {
        view.setOnClickListener {
            showComicInfo(it.context)
        }

        view.setOnLongClickListener {
            saveComic(it.context)
            true
        }
    }

    override fun setViewholder(comic: Comic){
        this.diamondCode = comic.diamondCode.toString()
        this.title?.text = comic.title
        this.upcCode?.text = comic.upcCode
        this.price?.text = "\$${comic.price}"
        this.poster?.let {
            val url = "${comic.imagePath}/portrait_uncanny.${comic.imageExt}"
                .replace("http","https")

            url.intoImage(it)
        }
    }

    override fun showComicInfo(context: Context) {
        val intent = Intent(context, ComicInfoActivity::class.java)
        intent.putExtra("diamondCode", diamondCode)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            context as Activity, poster as View, context.getString(R.string.image_transition))
        context.startActivity(intent, options.toBundle())
    }

    override fun saveComic(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val roomDatabase = MyRoomDatabase.getMyRoomDatabase(context)
            val comic = roomDatabase?.getComicByDiamondCode(diamondCode)
            comic?.isFavourite = true
            roomDatabase?.updateComicPreview(comic!!)
            withContext(Dispatchers.Main){
                context.toast("Comic added to favourites")
            }
        }
    }
}