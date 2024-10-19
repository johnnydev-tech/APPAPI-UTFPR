package com.example.minhaprimeiraapi.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var instance: AppDatabase? = null


    fun getInstance(context: Context? = null): AppDatabase {

        return instance ?: synchronized(this) {
            if (context == null) {
                throw Exception("DatabaseBuilder must be initialized with a context")

            }
            val newInstance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()

            instance = newInstance
            newInstance
        }
    }

}