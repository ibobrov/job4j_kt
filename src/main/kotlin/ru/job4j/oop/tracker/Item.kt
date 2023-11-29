package ru.job4j.oop.tracker

import java.time.LocalDateTime

class Item(var name: String) {
    var id : Int = 0
    private val created : LocalDateTime = LocalDateTime.now()
}