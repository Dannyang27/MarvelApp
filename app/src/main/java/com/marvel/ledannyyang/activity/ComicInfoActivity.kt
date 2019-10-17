package com.marvel.ledannyyang.activity

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.listadapter.PreviewAdapter
import com.marvel.ledannyyang.model.pojo.ComicPojo
import com.marvel.ledannyyang.retrofit.RetrofitClient
import com.marvel.ledannyyang.room.MyRoomDatabase
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext

class ComicInfoActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = Dispatchers.IO + job

    private lateinit var toolbar: Toolbar
    private lateinit var poster: ImageView
    private lateinit var diamondCode: TextView
    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var pages: TextView
    private lateinit var releaseDate: TextView
    private lateinit var upcCode: TextView
    private lateinit var overview: TextView
    private lateinit var writers: TextView
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

        diamondCode = findViewById(R.id.comic_info_diamondcode)
        title = findViewById(R.id.comic_info_title)
        pages = findViewById(R.id.comic_info_pages)
        releaseDate = findViewById(R.id.comic_info_releasedate)
        upcCode = findViewById(R.id.comic_info_upc)
        price = findViewById(R.id.comic_info_price)
        overview = findViewById(R.id.comic_info_overview)
        writers = findViewById(R.id.comic_info_writers)
        artist = findViewById(R.id.comic_info_artists)

        val comicId = intent.getStringExtra("comicId")
        toast(comicId)

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        previewAdapter = PreviewAdapter(mutableListOf("1", "1", "2", "1", "1", "2"))

        previewImagesList = findViewById<RecyclerView>(R.id.comic_info_preview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter =previewAdapter
        }

        launch {
            val comic = MyRoomDatabase.getMyRoomDatabase(this@ComicInfoActivity)?.getComicById(Integer.parseInt(comicId))
            setInfo(comic)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId){
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setInfo(comic: ComicPojo?){
        diamondCode.text = comic?.diamondCode
        title.text = comic?.title
        overview.text = comic?.description
        pages.text = comic?.pages.toString()
        releaseDate.text = comic?.date
        upcCode.text = comic?.upc
        price.text = "$" + comic?.price.toString()
        writers.text = comic?.writer
        artist.text = comic?.artBy
    }
}
