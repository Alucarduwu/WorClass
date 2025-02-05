package com.example.worclass.`class`

//class variable {
//}

fun main(){
    //Variable numeric
val age:Int =20
val long_number:Long= 31416987738474837
val temperature:Float= 28.9f
    val weigth:Double = 3.1416989898989898



//String varible
val gender:Char ='M'
val name:String =":)"

//Stoll variable
val isGreater:Boolean = false

//Colection variables
val names = arrayOf(
    "Anahi","Hola",":)"
)

println(age)
    println("Welcome $name, to your first Kootlin pryect")

    println(add())
    add()
    println(product(x=5,y=8))
    printArray(names)

}

 fun add():Int{
     val x =10
     val y=5
     return(x+y)
 }


fun product(x:Int,y:Int):Int{
    return(x*y)

}

fun printArray(names:Array<String>){
    println(names)
    for (name in names){
        println("Hello $name")

    }

}