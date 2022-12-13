package com.example.codingchallenge.mainScreen.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.mainScreen.domain.GetCharacterUseCase
import com.example.codingchallenge.mainScreen.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _character = MutableLiveData<String>()
    val character: LiveData<String> = _character

    private val _characterList = mutableStateListOf<CharacterResponseItem>()
    val characterList: List<CharacterResponseItem> = _characterList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showButtons = MutableLiveData<Boolean>()
    val showButtons: LiveData<Boolean> = _showButtons

    fun onCharacterChange(character: String) {
        _character.value = character
        _showButtons.value = _character.value.toString().isNotEmpty()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getCharactersUseCase()
            _characterList.clear()
            _characterList.addAll(result)
            Log.i("Result", "$result")
            _isLoading.value = false
        }
    }

    fun getCharacter() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getCharacterUseCase(_character.value.toString())
            _characterList.clear()
            Log.i("Result", "Clear list")
            _characterList.addAll(result)
            Log.i("Result", "$result")
            _isLoading.value = false
        }
    }
}