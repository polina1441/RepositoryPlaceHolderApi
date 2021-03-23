package com.polich.repository

import android.content.Context

class Repository(applicationContext: Context) {
    private val reference : WebService by lazy {
        Retrofit.retrofit.create(WebService::class.java)
    }

    private var cache : responce? = null
    suspend fun getCharacter() : responce? {
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