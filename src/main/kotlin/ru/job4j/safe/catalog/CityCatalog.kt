package ru.job4j.safe.catalog

class CityCatalog {
    val cities by lazy { fetch() }

    private fun fetch() = listOf("Москва", "Санкт-Петербург", "Новосибирск")
}