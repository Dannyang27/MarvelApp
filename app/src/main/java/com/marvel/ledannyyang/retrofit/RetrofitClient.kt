package com.marvel.ledannyyang.retrofit

import android.content.Context
import android.util.Log
import com.marvel.ledannyyang.BuildConfig
import com.marvel.ledannyyang.model.latestcomic.ComicJSON
import com.marvel.ledannyyang.model.pojo.ComicPojo
import com.marvel.ledannyyang.room.MyRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun getLatestComics(context: Context){
        val call = service.getLatestComic(apikey, "1", hash, "-issueNumber")
        call.enqueue(object: Callback<ComicJSON>{

            override fun onResponse(call: Call<ComicJSON>, response: Response<ComicJSON>) {
                val comic = response.body()?.copy()
                val comicList = comic?.data?.results

                val roomDatabase = MyRoomDatabase.getMyRoomDatabase(context)

                comicList?.forEach {
                    val comic = ComicPojo(it.id, it.title, it.description, it.format, it.upc, it.issueNumber,
                        it.prices?.get(0)?.price!!, it.thumbnail?.path.toString(), it.thumbnail?.extension.toString(), false)

                    roomDatabase?.addComic(comic)
                }

                roomDatabase?.updateComics()
            }

            override fun onFailure(call: Call<ComicJSON>, t: Throwable) {
                Log.d(TAG, "Error while getting latest comic")
            }
        })
    }
}