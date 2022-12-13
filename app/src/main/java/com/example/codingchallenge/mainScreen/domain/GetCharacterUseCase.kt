package com.example.codingchallenge.mainScreen.domain

import com.example.codingchallenge.mainScreen.data.CharacterRepository
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(character: String): List<CharacterResponseItem> {
        return repository.getCharacter(count = 20,character)
    }

}