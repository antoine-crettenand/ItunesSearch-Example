package com.antoinecrettenand.itunesstoresearch.data.repository

import com.antoinecrettenand.itunesstoresearch.data.model.ItunesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handling api calls using retrofit library
 */
const val BASE_URL = "https://itunes.apple.com/"

interface ItunesNetwork {

    @GET("search?")
    fun getMedia(
        @Query("term") term: String,
        @Query("media") media: String,
        @Query("country") country: String
    ): Call<ItunesResult>
}