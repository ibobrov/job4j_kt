package ru.job4j.dsl

import ru.job4j.oop.tracker.Item
import ru.job4j.oop.tracker.Tracker

private val tracker = Tracker()

fun Item.save(): Item {
    return tracker.add(this)
}

fun Item.replace(item: Item): Boolean {
    return tracker.replace(this.id, item)
}

fun Item.delete(): Boolean {
    return tracker.delete(this.id)
}


fun main() {
    val item0 = Item("test").save()
    val item1 = Item("test2").save()

    println(item0)
    println(item1)

    item1.replace(Item("updated"))
    item0.delete()

    println(tracker.findAll())
}
