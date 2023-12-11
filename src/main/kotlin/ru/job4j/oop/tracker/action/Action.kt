package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.Tracker
import ru.job4j.oop.tracker.io.Input
import ru.job4j.oop.tracker.io.Output

interface Action {

    fun getName(): String

    fun execute(tracker: Tracker, input: Input, out: Output): Boolean
}