package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.Tracker
import ru.job4j.oop.tracker.io.Input
import ru.job4j.oop.tracker.io.Output

class ShowAll : Action {

    override fun getName(): String {
        return "Показать все заявки"
    }

    override fun execute(tracker: Tracker, input: Input, out: Output): Boolean {
        out.show(tracker.findAll().toString())
        return true
    }
}