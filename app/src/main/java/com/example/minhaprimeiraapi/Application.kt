package com.example.minhaprimeiraapi

import android.app.Application
import com.example.minhaprimeiraapi.database.DatabaseBuilder

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }


    private fun init() {
        DatabaseBuilder.getInstance(this)
    }
}