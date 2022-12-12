package com.example.codingchallenge.mainScreen.data.network

import android.util.Log
import com.example.codingchallenge.core.network.RetrofitHelper
import com.example.codingchallenge.mainScreen.data.models.CharacterResponse
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharacterService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharacters(count: Int): List<CharacterResponseItem>{
        return withContext(Dispatchers.IO){
           val response = retrofit.create(CharacterClient::class.java).getCharacters(count)
            Log.i("response", "${response.body()}")
            response.body() ?: emptyList()
        }
    }

    suspend fun getCharacter(character: String): List<CharacterResponseItem>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CharacterClient::class.java).getCharacter(character)
            Log.i("response", "${response.body()}")
            response.body() ?: emptyList()
        }
    }
}