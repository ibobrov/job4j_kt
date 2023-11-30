package ru.job4j.oop.tracker

import ru.job4j.oop.tracker.action.Action
import ru.job4j.oop.tracker.action.Add
import ru.job4j.oop.tracker.action.Exit
import ru.job4j.oop.tracker.action.ShowAll
import ru.job4j.oop.tracker.io.ConsoleInput
import ru.job4j.oop.tracker.io.ConsoleOutput
import ru.job4j.oop.tracker.io.Input
import ru.job4j.oop.tracker.io.Output

object StartUI {
    private val tracker : Tracker = Tracker()
    private val input : Input = ConsoleInput()
    private val out : Output = ConsoleOutput()

    fun init() {
        val actions : List<Action> = listOf(Add(), ShowAll(), Exit())
        do {
            for (i in actions.indices) {
                out.show("${i + 1}. " + actions[i].getName())
            }
        } while (actions[input.askInt() - 1].execute(tracker, input, out))
    }
}

fun main() {
    StartUI.init()
}