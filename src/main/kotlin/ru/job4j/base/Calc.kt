package ru.job4j.base

fun add(first : Int, second : Int) : Int {
    return first + second
}

fun minus(first : Int, second : Int) : Int {
    return first - second
}

fun multiply(first : Int, second : Int) : Int {
    return first * second
}

fun divide(first : Int, second : Int) : Int {
    return first / second
}

fun main() {
    println("2 + 2 = ${add(2, 2)}")
    println("2 - 2 = ${minus(2, 2)}")
    println("2 * 2 = ${multiply(2, 2)}")
    println("2 / 2 = ${divide(2, 2)}")
}