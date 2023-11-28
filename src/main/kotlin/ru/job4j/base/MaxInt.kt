package ru.job4j.base

fun max(first: Int, second: Int) : Int {
    return if (first > second) first else second
}

fun max(first: Int, second: Int, third: Int) : Int {
    val greatest = max(first, second)
    return if (greatest > third) greatest else third
}