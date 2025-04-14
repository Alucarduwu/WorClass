package com.example.worclass.data.model.database


import android.content.Context
import androidx.room.Room


object DatabaseProvider {

    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            // IF NOT EXIST LA CREA
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app-db"
            ).build()

            // LO REGRESA A LA BD
            instance = db

            // Y YA LO REGRESA
            db
        }
    }
}