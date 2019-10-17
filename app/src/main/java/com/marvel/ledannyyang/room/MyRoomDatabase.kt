package com.marvel.ledannyyang.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marvel.ledannyyang.fragment.ComicFragment
import com.marvel.ledannyyang.model.pojo.ComicPojo
import com.marvel.ledannyyang.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Database(entities = [ComicPojo::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase: RoomDatabase(), CoroutineScope{
    private val job = Job()
    override val coroutineContext = Dispatchers.IO + job

    abstract fun comicDao(): ComicDao

    companion object{
        var INSTANCE: MyRoomDatabase? = null

        fun getMyRoomDatabase(context: Context): MyRoomDatabase?{
            if(INSTANCE == null){
                synchronized(MyRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MyRoomDatabase::class.java, "myDatabase").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }

    fun addComic(comic: ComicPojo){
        launch {
            comicDao().insert(comic)
        }
    }

    fun update(comic: ComicPojo){
        launch {
            comicDao().update(comic)
        }
    }

    fun delete(comic: ComicPojo){
        launch {
            comicDao().delete(comic)
        }
    }

    fun getComicById(id: Int): ComicPojo{
        return comicDao().getComicById(id)
    }

    fun updateComics(){
        launch {
            val comics = comicDao().getComics()
            Log.d(RetrofitClient.TAG, "Comic table size: ${comics.size}")
            ComicFragment.updateList(comics)
        }
    }
}
