package com.polich.repository

data class responce(
    val info: Info = Info(),
    val results: List<Result> = listOf()
)