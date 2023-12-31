package ru.job4j.safe.purchase

import java.util.Date

class Purchase(val name: String, val created: Date, val address: Address?)

class Address(val street: String?, val home: String, private val zip: String) {
    override fun toString(): String = "$zip, $street, $home"
}

fun getHtmlTable(list: List<Purchase>): String = with(StringBuilder()) {
    append("<table>")
    for (purchase in list) {
        append("<tr>")
        append("<th>${purchase.name}</th>")
        append("<th>${purchase.created}</th>")
        append("<th>${purchase.address?.street ?: ""}</th>")
        append("</tr>")
    }
    append("</table>")
}.toString()

fun main() {
    val list =
        listOf(
            Purchase("Игрушки", Date(), Address("Central st", "1", "325734")),
            Purchase("Полотенца", Date(), Address("Green st", "35", "325634")),
            Purchase("Полотенца", Date(), Address(null, "35", "325634")),
            Purchase("Футболки", Date(), null),
        )
    println(getHtmlTable(list))
}
