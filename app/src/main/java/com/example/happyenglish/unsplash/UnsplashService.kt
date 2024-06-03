package com.example.happyenglish.unsplash

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("client_id") clientId: String
    ): Call<SearchResponse>
}

data class SearchResponse(
    val results: List<Photo>
)

data class Photo(
    val urls: Urls
)

data class Urls(
    val regular: String
)
