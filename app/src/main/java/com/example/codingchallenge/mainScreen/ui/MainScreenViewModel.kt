package com.example.codingchallenge.mainScreen.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.mainScreen.data.models.CharacterResponse
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.mainScreen.domain.GetCharacterUseCase
import com.example.codingchallenge.mainScreen.domain.GetCharactersUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {

    val getCharactersUseCase = GetCharactersUseCase()
    val getCharacterUseCase = GetCharacterUseCase()

    private val _character = MutableLiveData<String>()
    val character: LiveData<String> = _character

    private val _characterList = mutableStateListOf<CharacterResponseItem>()
    val characterList: List<CharacterResponseItem> = _characterList

    fun onCharacterChange(character: String){
        _character.value = character
    }

    fun getCharacters(){
        viewModelScope.launch {
            val result = getCharactersUseCase()
            _characterList.addAll(result)
            Log.i("Result", "$result")
        }
    }

    fun getCharacter(){
        viewModelScope.launch {
            val result = getCharacterUseCase(_character.value.toString())
            _characterList.clear()
            Log.i("Result", "Clear list")
            _characterList.addAll(result)
            Log.i("Result", "$result")
        }
    }
}