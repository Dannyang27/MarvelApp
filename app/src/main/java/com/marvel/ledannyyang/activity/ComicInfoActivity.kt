package com.marvel.ledannyyang.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.getDate
import com.marvel.ledannyyang.getImageUrl
import com.marvel.ledannyyang.intoImage
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.room.MyRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicInfoActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var descriptionLayout: LinearLayout
    private lateinit var description: TextView
    private lateinit var creditLayout: LinearLayout
    private lateinit var poster: ImageView
    private lateinit var diamondCode: TextView
    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var pages: TextView
    private lateinit var releaseDate: TextView
    private lateinit var upcCode: TextView
    private lateinit var credits: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_info)

        toolbar = findViewById(R.id.comic_info_toolbar)
        toolbar.title = getString(R.string.comic_info_title)
        toolbar.setTitleTextColor(getColor(R.color.white))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        descriptionLayout = findViewById(R.id.description_layout)
        creditLayout = findViewById(R.id.credits_layout)
        description = findViewById(R.id.comic_info_overview)
        poster = findViewById(R.id.comic_info_poster)
        diamondCode = findViewById(R.id.comic_info_diamondcode)
        title = findViewById(R.id.comic_info_title)
        pages = findViewById(R.id.comic_info_pages)
        releaseDate = findViewById(R.id.comic_info_releasedate)
        upcCode = findViewById(R.id.comic_info_upc)
        price = findViewById(R.id.comic_info_price)
        credits = findViewById(R.id.comic_info_credits)

        val comicDiamondCode = intent.getStringExtra("diamondCode")
        populateInfo(comicDiamondCode)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun populateInfo(diamondCode: String){
        CoroutineScope(Dispatchers.IO).launch {
            val comic = MyRoomDatabase.getMyRoomDatabase(this@ComicInfoActivity)?.getComicByDiamondCode(diamondCode)
            withContext(Dispatchers.Main){
                setInfo(comic)
            }
        }
    }

    private fun setInfo(comic: Comic?){
        diamondCode.text = comic?.diamondCode
        title.text = comic?.title
        pages.text = comic?.pages.toString()
        releaseDate.text = comic?.onsaleDate?.getDate()
        upcCode.text = comic?.upcCode
        price.text = "$" + comic?.price.toString()

        val url = comic?.imagePath?.getImageUrl(comic.imageExt.toString())
        url?.intoImage(poster)

        comic?.description?.let{
            description.text = comic.description
            descriptionLayout.visibility = View.VISIBLE
        }

        comic?.credits?.let{
            credits.text = comic.credits
            creditLayout.visibility = View.VISIBLE
        }
    }
}
