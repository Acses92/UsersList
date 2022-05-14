package com.anatolykravchenko.waveaccesstest.data.network

import retrofit2.http.GET
import com.anatolykravchenko.waveaccesstest.data.model.UserItemDto

interface ApiService {
    @GET("/v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    suspend fun getUsersList(): ArrayList<UserItemDto>
}