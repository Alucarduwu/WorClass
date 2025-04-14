package com.example.worclass.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.worclass.data.model.dbo.AccountDao
import com.example.worclass.data.model.model.AccountEntity


// ESTO ES LA BD
@Database(entities = [AccountEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {


    abstract fun accountDao(): AccountDao
}