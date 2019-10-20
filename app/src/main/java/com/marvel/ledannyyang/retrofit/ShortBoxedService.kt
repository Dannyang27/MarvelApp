package com.marvel.ledannyyang.retrofit

import com.marvel.ledannyyang.model.shortBoxed.ComicDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShortBoxedService {
    @GET("/comics/v1/diamond_id/{diamondCode}")
    suspend fun getComicDescription(@Path("diamondCode") diamondCode: String) : Response<ComicDescription>
}