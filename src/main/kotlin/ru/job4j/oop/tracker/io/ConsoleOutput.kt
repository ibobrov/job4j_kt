package ru.job4j.oop.tracker.io

class ConsoleOutput : Output {

    override fun show(str: String) {
        println(str)
    }
}