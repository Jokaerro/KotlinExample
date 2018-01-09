package com.example.tesla.kotlinexample.data.service

import com.example.tesla.kotlinexample.data.model.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by TESLA on 10.01.2018.
 */
public interface Network {
    @GET("public/characters")
    fun getCharacters(): Call<CharacterDataWrapper>

    @GET("public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: String): Call<CharacterDataWrapper>
}