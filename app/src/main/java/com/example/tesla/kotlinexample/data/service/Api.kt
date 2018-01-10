package com.example.tesla.kotlinexample.data.service

import com.example.tesla.kotlinexample.data.model.CharacterDataWrapper

/**
 * Created by TESLA on 10.01.2018.
 */
public interface Api {
    abstract fun getCharacters(): Task<CharacterDataWrapper?>
}