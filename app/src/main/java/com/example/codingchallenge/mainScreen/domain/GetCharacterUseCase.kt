package com.example.codingchallenge.mainScreen.domain

import com.example.codingchallenge.mainScreen.data.CharacterRepository
import com.example.codingchallenge.mainScreen.data.models.CharacterResponse
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem

class GetCharacterUseCase {
    private val repository = CharacterRepository()

    suspend operator fun invoke(character: String): List<CharacterResponseItem> {
        return repository.getCharacter(character)
    }

}