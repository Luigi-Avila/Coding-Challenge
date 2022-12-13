package com.example.codingchallenge.mainScreen.data.network

import android.util.Log
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val characterClient: CharacterClient) {

    suspend fun getCharacters(count: Int): List<CharacterResponseItem>{
        return withContext(Dispatchers.IO){
           val response = characterClient.getCharacters(count)
            Log.i("response", "${response.body()}")
            response.body() ?: emptyList()
        }
    }

    suspend fun getCharacter(count: Int, character: String): List<CharacterResponseItem>{
        return withContext(Dispatchers.IO){
            val response = characterClient.getCharacter(count, character)
            Log.i("response", "${response.body()}")
            response.body() ?: emptyList()
        }
    }
}