package com.anatolykravchenko.waveaccesstest.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import com.anatolykravchenko.waveaccesstest.data.model.UserItem

interface ApiService {
    @GET("/breweries")
    suspend fun getListBreweries(
        @Query("page") page: Int
    ): List<UserItem>
}