package ru.job4j.lambda

class Main

val decrLambda = { i: Int -> i - 1 }
val powLambda = { i: Int -> i * i }

fun main() {
    println(decrLambda(4))
    println(powLambda(4))
}