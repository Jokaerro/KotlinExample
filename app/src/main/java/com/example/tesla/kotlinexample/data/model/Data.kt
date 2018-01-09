package com.example.tesla.kotlinexample.data.model

/**
 * Created by TESLA on 10.01.2018.
 */
data class Data(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        val results: List<Character>
)

//    "offset": "int",
//    "limit": "int",
//    "total": "int",
//    "count": "int",
//    "results": [