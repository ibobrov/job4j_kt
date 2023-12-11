package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource

class DataSourceWrapper {

    companion object Builder {
        private val dataSource = BasicDataSource()

        fun driverClassName(className: String) = apply { dataSource.driverClassName = className }

        fun url(url: String) = apply { dataSource.url = url }

        fun username(username: String) = apply { dataSource.username = username }

        fun password(password: String) = apply { dataSource.password = password }

        fun minIdle(minIdle: Int) = apply { dataSource.minIdle = minIdle }

        fun maxIdle(maxIdle: Int) = apply { dataSource.maxIdle = maxIdle }

        fun maxOpenPreparedStatements(maxStatements: Int) = apply {
            dataSource.maxOpenPreparedStatements = maxStatements
        }

        fun build() = dataSource
    }
}

fun main() {
    val pool = DataSourceWrapper
        .driverClassName("org.postgres.Driver")
        .url("localhost")
        .username("postgres")
        .password("password")
        .minIdle(5)
        .maxIdle(10)
        .maxOpenPreparedStatements(100)
        .build()
}