package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource
import java.sql.Connection
import java.util.function.Consumer
import java.util.function.Function

class JdbcWrapper

fun BasicDataSource.execute(code: Consumer<Connection>) {
    connection.use {
        try {
            it.autoCommit = false
            code.accept(it)
            it.commit()
        } catch (e: Exception) {
            it.rollback()
            println(e)
        }
    }
}

fun <T> BasicDataSource.tx(code: Function<Connection, T>): T {
    connection.use { return code.apply(it) }
}

fun <T> BasicDataSource.txBackList(code: Function<Connection, List<T>>): List<T> {
    connection.use { return code.apply(it) }
}
