package ru.job4j.lambda

import java.util.Date

data class Company(val name: String, val address: Address, val created: Date)

data class Address(val city: String, val street: String, val house: String)

fun toStringAll(companies: List<Company>): List<String> = companies.map {
    "${it.name} company," +
        " ${"${it.address.city} ${it.address.street} ${it.address.house}"}" +
        " founded ${it.created}"
}

fun main() {
    val companies: List<Company> =
        listOf(
            Company(
                "Intel",
                Address("California", "Grow street", "1"),
                Date(),
            ),
            Company(
                "Microsoft",
                Address("New York", "Central street", "1A"),
                Date(),
            ),
        )

    toStringAll(companies).forEach {
        println(it)
    }
}
