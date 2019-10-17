package com.marvel.ledannyyang.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.listadapter.PreviewAdapter
import org.jetbrains.anko.toast

class ComicInfoActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var poster: ImageView
    private lateinit var diamondCode: TextView
    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var releaseDate: TextView
    private lateinit var upcCode: TextView
    private lateinit var isbn: TextView
    private lateinit var overview: TextView
    private lateinit var writers: TextView
    private lateinit var covers: TextView
    private lateinit var artist: TextView
    private lateinit var previewImagesList: RecyclerView

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var previewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_info)

        toolbar = findViewById(R.id.comic_info_toolbar)
        toolbar.title = getString(R.string.comic_info_title)
        toolbar.setTitleTextColor(getColor(R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val comicId = intent.getStringExtra("comicId")
        toast(comicId)

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        previewAdapter = PreviewAdapter(mutableListOf("1", "1", "2", "1", "1", "2"))

        previewImagesList = findViewById<RecyclerView>(R.id.comic_info_preview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter =previewAdapter
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId){
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
