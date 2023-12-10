package ru.job4j.safe.purchase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class PurchaseTest {

    @Test
    fun whenGetTableWith3Elms() {
        val date = Date()
        val list = listOf(
            Purchase("Игрушки", date, Address("Central st", "1", "325734")),
            Purchase("Полотенца", date, Address("Green st", "35", "325634")),
            Purchase("Футболки", date, Address("High st", "105", "325631"))
        )
        assertThat(getHtmlTable(list)).isEqualTo(
            "<table>" +
                    "<tr><th>Игрушки</th><th>$date</th><th>Central st</th></tr>" +
                    "<tr><th>Полотенца</th><th>$date</th><th>Green st</th></tr>" +
                    "<tr><th>Футболки</th><th>$date</th><th>High st</th></tr>" +
                    "</table>"
        )
    }

    @Test
    fun whenGetTableWithElmHasNullInAddress() {
        val date = Date()
        val list = listOf(Purchase("Футболки", date, null))
        assertThat(getHtmlTable(list)).isEqualTo(
            "<table><tr><th>Футболки</th><th>$date</th><th></th></tr></table>"
        )
    }

    @Test
    fun whenGetTableWithElmWithAddressButStreetIsNull() {
        val date = Date()
        val list = listOf(
            Purchase("Футболки", date, Address(null, "105", "325631"))
        )
        assertThat(getHtmlTable(list)).isEqualTo(
            "<table>" +
                    "<tr><th>Футболки</th><th>$date</th><th></th></tr>" +
                    "</table>"
        )
    }
}