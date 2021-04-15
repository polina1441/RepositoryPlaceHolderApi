package com.polich.repository

import android.content.Context
import com.polich.repository.character.Responce
import com.polich.repository.web.Retrofit
import com.polich.repository.web.WebService

class Repository(applicationContext: Context) {
    private val reference : WebService by lazy {
        Retrofit.retrofit.create(WebService::class.java)
    }

    private var cache : Responce? = null
    suspend fun getCharacter() : Responce? {
        if (cache == null){
            cache = reference.getCharacter()
        }
        return cache
    }
    /*private var cache : Todos? = null

    suspend fun getTodos(): Todos?{
        if (cache == null){
        cache = reference.getTodos()
        }
        return cache
    }*/
}