package com.polich.repository.web
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    val RICK_MORTY_URL = "https://rickandmortyapi.com/"
    //"https://jsonplaceholder.typicode.com/"
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RICK_MORTY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}