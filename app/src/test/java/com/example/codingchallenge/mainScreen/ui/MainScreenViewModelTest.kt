package com.example.codingchallenge.mainScreen.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.mainScreen.domain.GetCharacterUseCase
import com.example.codingchallenge.mainScreen.domain.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainScreenViewModelTest {
    @RelaxedMockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @RelaxedMockK
    private lateinit var getCharacterUseCase: GetCharacterUseCase

    lateinit var mainScreenViewModel: MainScreenViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainScreenViewModel = MainScreenViewModel(getCharactersUseCase, getCharacterUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all characters`() = runTest {
        //Given
        val myList = listOf(
            CharacterResponseItem(
                character = "homer",
                characterDirection = "left",
                image = "http://image.com",
                quote = "the life is good"
            ), CharacterResponseItem(
                character = "Bart",
                characterDirection = "right",
                image = "http://image.com",
                quote = "I want this"
            )
        )
        coEvery { getCharactersUseCase() } returns myList

        //When
        mainScreenViewModel.getCharacters()

        //Then
        assert(mainScreenViewModel.characterList.value == myList)
    }

    @Test
    fun `when viewmodel finds a character and set character list`() = runTest {
        //Given
        val myList = listOf(
            CharacterResponseItem(
                character = "Homer",
                characterDirection = "left",
                image = "http://image.com",
                quote = "the life is good"
            ), CharacterResponseItem(
                character = "Homer",
                characterDirection = "left",
                image = "http://image.com",
                quote = "I want this"
            )
        )
        coEvery { getCharacterUseCase("Homer")} returns myList
        //When
        mainScreenViewModel.getCharacter("Homer")

        //Then
        assert(mainScreenViewModel.characterList.value == myList)
    }

    @Test
    fun `when viewmodel doesn't return a character`() = runTest {
        //Given
        val myList = listOf(
            CharacterResponseItem(
                character = "homer",
                characterDirection = "left",
                image = "http://image.com",
                quote = "the life is good"
            ), CharacterResponseItem(
                character = "Bart",
                characterDirection = "right",
                image = "http://image.com",
                quote = "I want this"
            )
        )
        //mainScreenViewModel.characterList.value = myList
        coEvery { getCharacterUseCase("Pepe") } returns emptyList()

        //When
        mainScreenViewModel.getCharacter("Pepe")

        //Then
        assert(mainScreenViewModel.characterList.value == myList)
    }


}