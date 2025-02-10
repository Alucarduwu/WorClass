package com.example.worclass.myclass

fun main() {
    // Variables numéricas
    val age: Int = 20
    val longNumber: Long = 31416987738474837
    val temperature: Float = 28.9f
    val weight: Double = 3.1416989898989898

    // Variables de caracteres y cadenas
    val gender: Char = 'M'
    val name: String = ":)"

    // Variable booleana
    val isGreater: Boolean = false

    // Colección de nombres
    val names = arrayOf("Anahi", "Hola", ":)")

    // Impresión de valores
    println(age)
    println("Welcome $name, to your first Kotlin project")

    // Llamadas a funciones
    println(getDay(1))
    println("Sum: ${add()}")
    println("Product: ${product(5, 8)}")

    printArray(names)

    // Creación e impresión de un objeto Person
    val person = Person("Ana", 28)
    person.displayInformation()
    println(person.name)
    println(person.age)
}

// Función para sumar dos números
fun add(): Int {
    val x = 10
    val y = 5
    return x + y
}

// Función para multiplicar dos números
fun product(x: Int, y: Int): Int {
    return x * y
}

// Función para imprimir un array
fun printArray(names: Array<String>) {
    println(names.joinToString(", "))
    for (name in names) {
        println("Hello $name")
    }
}

// Función para obtener el nombre del día de la semana
fun getDay(day: Int): String {
    val name = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Incorrect value"
    }
    return name
}

// Clase Persona
class Person(val name: String, val age: Int) {
    fun displayInformation() {
        println("Name: $name, Age: $age")
    }
}
