package com.marvel.ledannyyang.retrofit

import com.marvel.ledannyyang.model.ComicDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShortBoxedService {
    @GET("/comics/v1/diamond_id/{diamondCode}")
    fun getComicDescription(@Path("diamondCode") diamondCode: String) : Call<ComicDescription>
}