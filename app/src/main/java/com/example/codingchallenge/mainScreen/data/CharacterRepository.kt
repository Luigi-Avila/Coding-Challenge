package com.example.codingchallenge.mainScreen.data

import com.example.codingchallenge.mainScreen.data.models.CharacterResponse
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.mainScreen.data.network.CharacterService

class CharacterRepository {
    private val api = CharacterService()

    suspend fun getCharacters(count: Int): List<CharacterResponseItem> {
        return api.getCharacters(count)
    }

    suspend fun getCharacter(character: String): List<CharacterResponseItem> {
        return api.getCharacter(character)
    }
}