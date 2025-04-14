package com.example.worclass.data.model.dbo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worclass.data.model.model.AccountEntity



@Dao
interface AccountDao {

    // CUENTAS
    // LISTA RESULT
    @Query("SELECT * FROM AccountEntity")
    fun getAll(): List<AccountEntity>

    // Esta función inserta una nueva cuenta en la base de datos.
    // Si ya existe una cuenta con el mismo identificador, se reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: AccountEntity)

    // Esta función elimina una cuenta de la base de datos.
    // El parámetro es la cuenta que se eliminará.
    @Delete()
    fun delete(account: AccountEntity)
}