package com.marvel.ledannyyang.retrofit

import com.marvel.ledannyyang.model.latestcomic.ComicJSON
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/v1/public/comics")
    fun getLatestComic(@Query("apikey") api: String,
                       @Query("ts") ts: String,
                       @Query("hash") hash: String,
                       @Query("orderBy") orderBy: String) : Call<ComicJSON>
}