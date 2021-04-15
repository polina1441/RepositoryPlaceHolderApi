package com.polich.repository.character

data class Responce(
    val info: Info = Info(),
    val results: List<Result> = listOf()
)