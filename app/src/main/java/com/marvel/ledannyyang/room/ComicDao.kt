package com.marvel.ledannyyang.room

import androidx.room.*
import com.marvel.ledannyyang.model.Comic

@Dao
interface ComicDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: Comic)

    @Update
    fun update(comic: Comic)

    @Delete
    fun delete(comic: Comic)

    @Query("SELECT * FROM Comic")
    fun getComics() : MutableList<Comic>

    @Query("SELECT * FROM Comic WHERE id =:id")
    fun getComicById( id: Int) : Comic

    @Query("SELECT * FROM Comic WHERE diamondCode =:diamondCode")
    fun getComicByDiamondCode( diamondCode: String) : Comic

    @Query("DELETE FROM Comic")
    fun deleteComics()
}