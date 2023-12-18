package ru.job4j.oop.list

interface SimpleList<T> : ListIterable<T> {
    fun add(element: T): Boolean

    fun get(index: Int): T

    fun contains(element: T): Boolean

    fun removeAt(index: Int)

    fun isEmpty(): Boolean
}
