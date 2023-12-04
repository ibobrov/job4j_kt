package ru.job4j.oop.inheritance

open class Profession(private val name : String) {

    override fun toString(): String {
        return name
    }
}

class Doctor : Profession("Doctor")

class Engineer : Profession("Engineer")

class Teacher : Profession("Teacher")

fun main() {
    val professions : List<Profession> = listOf(Doctor(), Teacher(), Engineer())
    println(professions)
}