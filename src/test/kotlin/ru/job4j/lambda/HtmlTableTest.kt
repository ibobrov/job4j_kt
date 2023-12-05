package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HtmlTableTest {
    private val template = HtmlTable()

    @Test
    fun whenTableEmpty() {
        assertThat(template.table(0, 0)).isEqualTo("<table></table>")
        assertThat(template.table(0, 2)).isEqualTo("<table></table>")
    }

    @Test
    fun whenTableWith2Rows2Cells() {
        assertThat(template.table(2, 2))
            .isEqualTo("<table><tr><th> </th><th> </th></tr><tr><th> </th><th> </th></tr></table>")
    }

    @Test
    fun whenTableWith1Rows3Cells() {
        assertThat(template.table(1, 3))
            .isEqualTo("<table><tr><th> </th><th> </th><th> </th></tr></table>")
    }
}