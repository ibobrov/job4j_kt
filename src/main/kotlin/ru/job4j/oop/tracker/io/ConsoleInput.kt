package ru.job4j.oop.tracker.io

class ConsoleInput : Input {
    override fun askInt(): Int = readln().toInt()

    override fun askStr(): String = readln()
}
