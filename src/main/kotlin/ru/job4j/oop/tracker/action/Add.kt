package ru.job4j.oop.tracker.action

import ru.job4j.oop.tracker.Item
import ru.job4j.oop.tracker.Tracker
import ru.job4j.oop.tracker.io.Input
import ru.job4j.oop.tracker.io.Output

class Add : Action {

    override fun getName(): String {
        return "Добавить заявку"
    }

    override fun execute(tracker: Tracker, input: Input, out: Output): Boolean {
        out.show("Введите название заявки: ")
        val item = input.askStr()
        tracker.add(Item(item))
        return true
    }
}