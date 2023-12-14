package ru.job4j.dsl

import org.junit.jupiter.api.Test
import ru.job4j.extensions.contains
import ru.job4j.extensions.eq
import ru.job4j.extensions.notContains
import ru.job4j.extensions.notEq

class TestLikeKotest {

    @Test
    fun whenCheckMyJUnitExtensions() {
        "1" + "2" eq "12"
        1 + 2 notEq 12
        listOf("first", "second") contains "first"
        listOf(1, 2, 3, 4, 5) notContains 0
    }
}