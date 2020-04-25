package com.caiozed.videogamequiz

import com.caiozed.videogamequiz.models.Cover
import com.caiozed.videogamequiz.models.Game
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result;

class IGDBService {
    var userKey = "API_KEY"
    var games = "https://api-v3.igdb.com/games/"
    var covers = "https://api-v3.igdb.com/covers/"

    suspend fun getRandomGames(query: String): Array<Game>{
        val(_, _, result ) = Fuel.post(games)
            .header("user-key", userKey)
            .body(query)
            .responseObject<Array<Game>>()

        return when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
                emptyArray()
            }
            is Result.Success -> {
                val data = result.component1()
                data!!
            }
        }
    }

    suspend fun getCovers(coverIds: List<Int>?): Array<Cover>{
        val(_, _, result ) = Fuel.post(covers)
            .header("user-key", userKey)
            .body("fields *; where id = (${coverIds?.joinToString(",")});"
        )
            .responseObject<Array<Cover>>()

        return when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
                emptyArray()
            }
            is Result.Success -> {
                val data = result.component1()
                data!!
            }
        }
    }
}
