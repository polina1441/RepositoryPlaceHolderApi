package com.polich.repository.web

import com.polich.repository.character.Responce
import retrofit2.http.GET

interface WebService {
    /*@GET("/todos")
    suspend fun getTodos() : Todos*/

    @GET("/api/character")
    suspend fun getCharacter(): Responce


}