package com.example.codingchallenge.mainScreen.domain

import com.example.codingchallenge.mainScreen.data.CharacterRepository
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem

class GetCharactersUseCase {

    private val repository = CharacterRepository()

    suspend operator fun invoke(): List<CharacterResponseItem> {
        return repository.getCharacters(count = 20)
    }
}