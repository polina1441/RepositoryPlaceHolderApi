package com.polich.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Repository(applicationContext: Context) {
    private val reference : WebService by lazy {
        Retrofit.retrofit.create(WebService::class.java)
    }
    private var cache : Todos? = null

    suspend fun getTodos(): Todos?{
        if (cache == null){
        cache = reference.getTodos()
        }
        return cache
    }
    /*fun getUser(): MutableLiveData<Resource<String>> {
        val id = MutableLiveData<Resource<String>>(Resource.Loading())
        MainScope().launch {
            for (i in 0..20) {
                id.postValue(Resource.Success("dvffv"))
                delay(300)
            }
        }
        return id
    }*/
}