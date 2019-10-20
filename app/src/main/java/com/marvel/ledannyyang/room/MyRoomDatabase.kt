package com.marvel.ledannyyang.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marvel.ledannyyang.model.Comic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Database(entities = [Comic::class], version = 1, exportSchema = false)
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

    fun addComicPreview(comic: Comic){
        launch{
            comicDao().insert(comic)
        }
    }

    fun updateComicPreview(comic: Comic){
        launch {
            comicDao().update(comic)
        }
    }

    fun clearFavourites(){
        launch{
            comicDao().clearFavourites()
        }
    }

    fun getFavouriteComics(): MutableList<Comic>{
        return comicDao().getFavouriteComics(true)
    }

    fun getComicByDiamondCode(diamondCode: String): Comic{
        return comicDao().getComicByDiamondCode(diamondCode)
    }

    fun getAllComicPreview(): MutableList<Comic>{
        return comicDao().getComics()
    }
}
