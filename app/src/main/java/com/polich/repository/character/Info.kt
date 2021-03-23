package com.polich.repository.character

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
){
    constructor():this(0,"", 0, 0)
}
val info : Info = Info(1, "1", 1, 1)