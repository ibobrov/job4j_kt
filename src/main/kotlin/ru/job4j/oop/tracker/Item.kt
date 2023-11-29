package ru.job4j.oop.tracker

import java.time.LocalDateTime

data class Item(var name: String,
                var id : Int = 0,
                val created : LocalDateTime = LocalDateTime.now())