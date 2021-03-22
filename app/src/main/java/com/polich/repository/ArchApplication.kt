package com.polich.repository

import android.app.Application

class ArchApplication : Application() {
    val repository : Repository by lazy { Repository(applicationContext) }

}