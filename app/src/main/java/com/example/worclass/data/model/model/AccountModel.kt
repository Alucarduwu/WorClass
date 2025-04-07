package com.example.worclass.data.model.model

data class AccountModel(
    var id: Int=0,
     var name: String ="",
    var username: String="",
    var password: String="",
    var description: String="",
    var imageURL: String?=null
)