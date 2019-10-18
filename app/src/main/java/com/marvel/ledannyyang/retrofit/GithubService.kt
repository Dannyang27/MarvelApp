package com.marvel.ledannyyang.retrofit

import com.marvel.ledannyyang.model.comic.ComicJSON
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/v1/public/comics")
    fun getLatestComicPreview( @Query("apikey") api: String,
                               @Query("ts") ts: String,
                               @Query("hash") hash: String,
                               @Query("orderBy") orderBy: String,
                               @Query("format") format: String,
                               @Query("dateRange") dateRange: String,
                               @Query("dateDescriptor") dateDesc: String,
                               @Query("limit") limit: Int) : Call<ComicJSON>
}