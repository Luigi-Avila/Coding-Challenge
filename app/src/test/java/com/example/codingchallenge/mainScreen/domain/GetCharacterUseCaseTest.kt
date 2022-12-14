package com.example.codingchallenge.mainScreen.domain

import com.example.codingchallenge.mainScreen.data.CharacterRepository
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: CharacterRepository

    lateinit var getCharacterUseCase: GetCharacterUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCharacterUseCase = GetCharacterUseCase(repository)
    }

    @Test
    fun `when the api return character then get character from api`() = runBlocking {
        //Given
        val myList = listOf(
            CharacterResponseItem(
                character = "marge",
                characterDirection = "right",
                image = "http://image.com",
                quote = "the life is life"
            )
        )
        coEvery { repository.getCharacter(20, "marge") } returns myList

        //When
        val result = getCharacterUseCase("marge")

        //Then
        assert(myList == result)

    }
}