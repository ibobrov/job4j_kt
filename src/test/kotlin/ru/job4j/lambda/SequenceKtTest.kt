package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SequenceKtTest {

    @Test
    fun whenSimpleSeq() {
        val list = ArrayList<Int>()
        list.addAll(listOf(1, 2, 3))
        assertThat(count(list)).isEqualTo(6)
    }

    @Test
    fun whenMoreDifficultSeq() {
        val list = ArrayList<Int>()
        list.addAll(listOf(1, 2, 3, 4, 5, 9))
        assertThat(count(list)).isEqualTo(22)
    }

    @Test
    fun whenOnlyEvenNumsInSeq() {
        val list = ArrayList<Int>()
        list.addAll(listOf(2, 4, 6))
        assertThat(count(list)).isEqualTo(0)
    }

    @Test
    fun whenNegativeNumInSeq() {
        val list = ArrayList<Int>()
        list.addAll(listOf(-3, 4, 5))
        assertThat(count(list)).isEqualTo(4)
    }
}