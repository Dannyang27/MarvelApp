package com.marvel.ledannyyang.retrofit

import android.content.Context
import android.util.Log
import com.marvel.ledannyyang.BuildConfig
import com.marvel.ledannyyang.activity.SplashActivity
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.model.comic.ComicJSON
import com.marvel.ledannyyang.room.MyRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

object RetrofitClient {

    val TAG = "COMIC"
    private val apikey = BuildConfig.MARVEL_PUBLIC_API_KEY
    private val hash = "60953bc79ff43f53ddc1af21467b6cea"
    private val baseUrl = "https://gateway.marvel.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(GithubService::class.java)

    fun getComicPreview(context: Context){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val range = "$year-01-01,${dateFormat.format(Date())}"

        val call = service.getLatestComicPreview(apikey, "1", hash, "-onsaleDate", "comic", range, "thisMonth")
        call.enqueue(object: Callback<ComicJSON> {

            override fun onResponse(call: Call<ComicJSON>, response: Response<ComicJSON>) {
                val comic = response.body()?.copy()
                val list = comic?.data?.results
                    ?.filterNot { it.thumbnail?.path!!.contains("image_not_available") }
                    ?.sortedBy { it.title }

                val roomDatabase = MyRoomDatabase.getMyRoomDatabase(context)

                list?.forEach {
                    val price = it.prices?.get(0)?.price
                    val date = it.dates?.filter { it.type == "onsaleDate" }?.get(0)?.date
                    val creatorList = it.creators?.items

                    var creators = mutableListOf<String>()
                    creatorList?.forEach {
                        creators.add(it.name)
                    }

                    val comic = Comic(it.id, it.title, it.upc, price, it.thumbnail?.path, it.thumbnail?.extension,
                        it.diamondCode, date, it.pageCount, it.description, creators.joinToString())

                    roomDatabase?.addComicPreview(comic)
                }

                SplashActivity.launchMainActivity(context)
            }

            override fun onFailure(call: Call<ComicJSON>, t: Throwable) {
                Log.d(TAG, "Error while getting latest comic")
            }
        })
    }
}