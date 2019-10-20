package com.marvel.ledannyyang.retrofit

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.marvel.ledannyyang.BuildConfig
import com.marvel.ledannyyang.getFirstDateFromYear
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.room.MyRoomDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*

object RetrofitClient{

    private const val apikey = BuildConfig.MARVEL_PUBLIC_API_KEY
    private const val hash = "60953bc79ff43f53ddc1af21467b6cea"
    private const val baseUrl = "https://gateway.marvel.com"
    private const val shortBoxedUrl = "https://api.shortboxed.com"
    private const val format = "comic"
    private const val orderBy = "-onsaleDate"
    private const val month = "thisMonth"
    private lateinit var preferenceManager: SharedPreferences

    private var roomDatabase: MyRoomDatabase? = null

    private fun createMarvelService(): MarvelService{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }

    private fun createShortBoxedService(): ShortBoxedService{
        return Retrofit.Builder()
            .baseUrl(shortBoxedUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ShortBoxedService::class.java)
    }

    suspend fun getLatestComicsIfModified(context: Context){
        val retrofitService = createMarvelService()
        val shortBoxedService = createShortBoxedService()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val today = dateFormat.format(Date())
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val range = "${year.getFirstDateFromYear()},$today"

        if(isAlreadyUpdate(context, today)){
            return
        }

        val response = retrofitService.getLatestComicPreview(apikey, "1", hash, orderBy,
            format, range, month, 20)
        try{
            if(response.isSuccessful){
                roomDatabase = MyRoomDatabase.getMyRoomDatabase(context)

                val list = response.body()?.copy()?.data?.results
                val filteredList = list
                    ?.filterNot { it.thumbnail?.path!!.contains("image_not_available") }
                    ?.sortedBy { it.title }

                filteredList?.forEach {
                    val price = it.prices?.get(0)?.price
                    val date = it.dates?.filter { it.type == "onsaleDate" }?.get(0)?.date


                    val shortBoxedResponse = shortBoxedService.getComicDescription(it.diamondCode)

                    if (shortBoxedResponse.isSuccessful) {
                        val comicDescription = shortBoxedResponse.body()?.copy()
                        val comicItem = comicDescription?.comics?.get(0)
                        val description = comicItem?.description
                        val creators = comicItem?.creators

                        val comic = Comic(
                            it.id,
                            it.title,
                            it.upc,
                            price,
                            it.thumbnail?.path,
                            it.thumbnail?.extension,
                            it.diamondCode,
                            date,
                            it.pageCount,
                            description,
                            creators,
                            false
                        )

                        roomDatabase?.addComicPreview(comic)
                    }
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun isAlreadyUpdate(context: Context, today: String): Boolean{
        preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        val lastUpdate = preferenceManager.getString("lastUpdate", "")
        var isUpdated: Boolean

        if(lastUpdate == today){
            isUpdated =  true
        }else{
            val editor = preferenceManager.edit()
            editor.putString("lastUpdate", today)
            editor.apply()

            isUpdated = false
        }

        return isUpdated
    }
}