package com.polich.repository

import retrofit2.http.GET

interface WebService {
    @GET("/todos")
    suspend fun getTodos() : Todos

}