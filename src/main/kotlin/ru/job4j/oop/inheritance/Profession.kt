package ru.job4j.oop.inheritance

open class Profession(private val name: String) {

    open fun getName(): String = name

    open fun action() {
        println("$name work")
    }

    override fun toString(): String {
        return name
    }
}

class Doctor(private val specialization: String) : Profession("Doctor") {

    override fun getName(): String {
        return specialization
    }

    override fun action() {
        println("$specialization work")
    }
}

class Engineer : Profession("Engineer") {

    override fun getName(): String {
        return "Soft ${super.getName()}"
    }

    override fun action() {
        println("Write code")
    }
}

class Teacher : Profession("Teacher") {

    override fun action() {
        println("Teacher checks homework")
    }
}

fun main() {
    val professions: List<Profession> = listOf(Doctor("Aculist"), Teacher(), Engineer())
    professions.forEach {
        println(it.getName())
        it.action()
        println("")
    }
}