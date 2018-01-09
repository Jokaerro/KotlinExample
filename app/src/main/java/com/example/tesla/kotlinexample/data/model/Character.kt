package com.example.tesla.kotlinexample.data.model

import java.util.*

/**
 * Created by TESLA on 10.01.2018.
 */
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceUrl: String,
    val urls: List<Url>,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Events,
    val series: Series
)
//    "id": "int",
//    "name": "string",
//    "description": "string",
//    "modified": "Date",
//    "resourceURI": "string",
//    "urls": [

//    ],
//    "thumbnail": {

//    },
//    "comics": {

//    },
//    "stories": {

//    },
//    "events": {

//    "series": {

//}
