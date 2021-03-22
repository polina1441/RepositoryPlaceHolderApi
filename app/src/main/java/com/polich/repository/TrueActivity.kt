package com.polich.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.polich.repository.ui.main.TrueFragment

class TrueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.true_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrueFragment.newInstance())
                .commitNow()
        }
    }
}