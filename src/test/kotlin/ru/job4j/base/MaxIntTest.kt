package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MaxIntTest {
    @Test
    fun whenMaxFirst() {
        assertThat(max(2, 1)).isEqualTo(2)
    }

    @Test
    fun whenMaxSecond() {
        assertThat(max(1, 2)).isEqualTo(2)
    }

    @Test
    fun whenMaxThird() {
        assertThat(max(2, 1, 3)).isEqualTo(3)
    }

    @Test
    fun whenFirstOfThree() {
        assertThat(max(2, 1, -3)).isEqualTo(2)
    }
}
