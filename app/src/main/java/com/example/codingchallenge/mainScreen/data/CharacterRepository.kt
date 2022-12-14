package com.example.codingchallenge.mainScreen.data

import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.mainScreen.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor( private val api: CharacterService) {

    suspend fun getCharacters(count: Int): List<CharacterResponseItem> {
        return api.getCharacters(count)
    }

    suspend fun getCharacter(count: Int, character: String): List<CharacterResponseItem> {
        return api.getCharacter(count, character)
    }
}