package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefragmentationTest {

    @Test
    fun whenAnArrayHasNullAtBetween() {
        val array = arrayOf("first", null, "second", null, "third")
        defragment(array)
        val expected = arrayOf("first", "second", "third", null, null)
        assertThat(array).isEqualTo(expected)
    }

    @Test
    fun whenAnArrayHasNullAtBeginning() {
        val array = arrayOf(null, null, "first", null, "second")
        defragment(array)
        val expected = arrayOf("first", "second", null, null, null)
        assertThat(array).isEqualTo(expected)
    }

    @Test
    fun whenAnArrayHasNullAtEnd() {
        val array = arrayOf("second", "third", null, null, null)
        defragment(array)
        val expected = arrayOf("second", "third", null, null, null)
        assertThat(array).isEqualTo(expected)
    }
}