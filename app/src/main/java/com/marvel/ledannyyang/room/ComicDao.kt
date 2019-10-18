package com.marvel.ledannyyang.room

import androidx.room.*
import com.marvel.ledannyyang.model.Comic

@Dao
interface ComicDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comic: Comic)

    @Update
    fun update(comic: Comic)

    @Query("UPDATE Comic SET isFavourite = 'false'")
    fun clearFavourites()

    @Delete
    fun delete(comic: Comic)

    @Query("SELECT * FROM Comic")
    fun getComics() : MutableList<Comic>

    @Query("SELECT * FROM Comic LIMIT :offset, :limit")
    fun getComicsWithinRange(limit: Int, offset: Int) : MutableList<Comic>

    @Query("SELECT COUNT(*) FROM Comic")
    fun getTableSize() : Int

    @Query("SELECT * FROM Comic WHERE isFavourite =:isFavourite")
    fun getFavouriteComics(isFavourite: Boolean): MutableList<Comic>

    @Query("SELECT * FROM Comic WHERE id =:id")
    fun getComicById( id: Int) : Comic

    @Query("SELECT * FROM Comic WHERE diamondCode =:diamondCode")
    fun getComicByDiamondCode( diamondCode: String) : Comic

    @Query("DELETE FROM Comic")
    fun deleteComics()
}