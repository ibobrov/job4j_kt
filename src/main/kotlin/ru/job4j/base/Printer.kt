package ru.job4j.base

fun draw(size: Int) {
    if (size <= 0 || size % 2 == 0) {
        println("size некорректен")
        return
    }

    for (x in 0 until size) {
        for (y in 0 until size) {
            val onDiagonal = x == y || x + y == size - 1
            print(if (onDiagonal) "x" else " ")
        }
        println()
    }
}

fun main() {
    draw(2)
    println()
    draw(1)
    println()
    draw(3)
    println()
    draw(7)
}
