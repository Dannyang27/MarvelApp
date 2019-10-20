package com.marvel.ledannyyang

import com.marvel.ledannyyang.model.Comic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class ComicUnitTest {
    private lateinit var comic: Comic

    @Before
    fun createComic(){
        comic = Comic(1, "Deadpool", "6873218", 3.99, "deadpool",
            "jpg", "AUG12932", "2019-10-20", 39,
            "Deadpool is a blablabla", "Le Danny Yang", false)
    }
    @Test
    fun checkComicAttributes() {
        assertEquals(comic.title ,"Deadpool")
        assertEquals(comic.id ,1)
        assertEquals(comic.diamondCode ,"AUG12932")
        assertEquals(comic.pages ,39)
        assertFalse(comic.isFavourite)
    }
}
