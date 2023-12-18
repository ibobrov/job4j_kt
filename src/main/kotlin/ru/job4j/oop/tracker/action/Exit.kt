package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.Tracker
import ru.job4j.oop.tracker.io.Input
import ru.job4j.oop.tracker.io.Output

class Exit : Action {
    override fun getName(): String = "Закрыть приложение"

    override fun execute(tracker: Tracker, input: Input, out: Output): Boolean = false
}
