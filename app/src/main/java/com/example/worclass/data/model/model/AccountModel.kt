package com.example.worclass.data.model.model

import android.accounts.Account
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// "cuenta"
data class AccountModel(
    var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var password: String = "",
    var description: String = "",
    var imageURL: String? = null
)


// Room crear y manipular la tabla
@Entity
data class AccountEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageURL") val imageURL: String?
)


// YA PASAMOS LA "CUENTA" A LA BD OSEA YA ES REAL
fun AccountModel.toAccountEntity(): AccountEntity {
    return AccountEntity(
        id = this.id,
        name = this.name,
        username = this.username,
        password = this.password,
        description = this.description,
        imageURL = this.imageURL
    )
}