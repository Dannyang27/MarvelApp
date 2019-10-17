package com.marvel.ledannyyang.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import org.jetbrains.anko.toast

class ComicPreviewViewholder (view: View): RecyclerView.ViewHolder(view){
    val poster: ImageView = view.findViewById(R.id.comic_preview)

    init {
        view.setOnClickListener {
            it.context.toast("Preview...")
        }
    }
}