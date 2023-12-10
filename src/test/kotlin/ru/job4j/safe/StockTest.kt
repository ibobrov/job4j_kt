package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StockTest {

    @Test
    fun whenEqualsWithNull() {
        val o1 = Stock("str", "cur", null)
        val o2 = Stock("str", "cur", null)
        assertThat(o1 == null).isFalse()
    }

    @Test
    fun whenEqualsHaveReflexivity() {
        val o1 = Stock("str", "cur", null)
        assertThat(o1 == o1).isTrue()
    }

    @Test
    fun whenEqualsHaveSymmetry() {
        val o1 = Stock("str", "cur", null)
        val o2 = Stock("str", "cur", null)
        assertThat(o1 == o2).isTrue()
        assertThat(o2 == o1).isTrue()
    }

    @Test
    fun whenEqualsHaveTransitivity() {
        val o1 = Stock("str", "cur", null)
        val o2 = Stock("str", "cur", null)
        val o3 = Stock("str", "cur", null)
        assertThat(o1 == o3).isTrue()
        assertThat(o2 == o3).isTrue()
        assertThat(o1 == o2).isTrue()
    }
}