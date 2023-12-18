package ru.job4j.safe.jdbc

interface Repository<T> {
    fun insert(value: String): Boolean

    fun update(oldValue: String, newValue: String): Boolean

    fun delete(value: String): Boolean

    fun find(value: String): T?

    fun findAll(): List<T>
}
