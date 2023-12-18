package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalcTest {
    @Test
    fun when2plus2() {
        assertThat(add(2, 2)).isEqualTo(4)
    }

    @Test
    fun when2minus2() {
        assertThat(minus(2, 2)).isEqualTo(0)
    }

    @Test
    fun when2multiply2() {
        assertThat(multiply(2, 2)).isEqualTo(4)
    }

    @Test
    fun when2divide2() {
        assertThat(divide(2, 2)).isEqualTo(1)
    }
}
