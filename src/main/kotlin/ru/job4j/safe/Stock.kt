package ru.job4j.safe

import java.util.Date

class Stock(val name: String, val currency: String, val date: Date?) {

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (this === other) {
            return true
        }
        if (javaClass != other.javaClass) {
            return false
        }
        other as Stock
        if (name != other.name) return false
        if (currency != other.currency) return false
        if (date != other.date) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}