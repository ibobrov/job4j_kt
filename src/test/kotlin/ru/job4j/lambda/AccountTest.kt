package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AccountTest {
    private val list =
        listOf(
            Account("Ivan", 1.2),
            Account("Aleksandr", -1.0),
            Account("Daria", 5.8),
            Account("Daria", 0.4),
        )

    @Test
    fun whenFilterByNameIsIvanAndBalanceNonPositive() {
        assertThat(list.filter { predicate(it, "Ivan", false) }).isEmpty()
    }

    @Test
    fun whenFilterByNameIsAndBalancePositive() {
        assertThat(list.filter { predicate(it, "Ivan", true) }).containsOnly(list[0])
    }

    @Test
    fun whenFilterByNameIsAleksandrAndBalancePositive() {
        assertThat(list.filter { predicate(it, "Aleksandr", true) }).isEmpty()
    }

    @Test
    fun whenFilterByNameIsDariaAndBalancePositive() {
        assertThat(list.filter { predicate(it, "Daria", true) }).containsAll(listOf(list[2], list[3]))
    }
}
