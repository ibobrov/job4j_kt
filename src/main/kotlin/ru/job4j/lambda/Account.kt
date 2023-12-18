package ru.job4j.lambda

data class Account(val name: String, val balance: Double)

val predicate = { acc: Account, name: String, isPositiveBalance: Boolean ->
    acc.name == name && acc.balance > 0 == isPositiveBalance
}
