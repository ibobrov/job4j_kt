package ru.job4j.oop.tracker

import ru.job4j.oop.tracker.action.Action
import ru.job4j.oop.tracker.action.Add
import ru.job4j.oop.tracker.action.Exit
import ru.job4j.oop.tracker.action.ShowAll
import ru.job4j.oop.tracker.io.ConsoleInput
import ru.job4j.oop.tracker.io.ConsoleOutput

object StartUI {
    private val tracker = Tracker()
    private val input = ConsoleInput()
    private val out = ConsoleOutput()

    fun init() {
        val actions : List<Action> = listOf(Add(), ShowAll(), Exit())
        do {
            for (i in actions.indices) {
                out.show("${i + 1}. ${actions[i].getName()}")
            }
        } while (actions[input.askInt() - 1].execute(tracker, input, out))
    }
}

fun main() {
    StartUI.init()
}