package com.marvel.ledannyyang.room

import androidx.room.*
import com.marvel.ledannyyang.model.pojo.ComicPojo

@Dao
interface ComicDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: ComicPojo)

    @Update
    fun update(comic: ComicPojo)

    @Delete
    fun delete(comic: ComicPojo)

    @Query("SELECT * FROM Comic")
    fun getComics() : MutableList<ComicPojo>

    @Query("SELECT * FROM Comic WHERE isFavourite = 'true' ")
    fun getSavedComics() : MutableList<ComicPojo>

    @Query("DELETE FROM Comic WHERE isFavourite = 'false' ")
    fun deleteComics()
}