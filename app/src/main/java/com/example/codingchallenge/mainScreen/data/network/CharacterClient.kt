package com.example.codingchallenge.mainScreen.data.network

import com.example.codingchallenge.mainScreen.data.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterClient {

    @GET("/quotes")
    suspend fun getCharacters(@Query("count") count: Int): Response<CharacterResponse>

    @GET("/quotes")
    suspend fun getCharacter(@Query("character") character: String): Response<CharacterResponse>
}