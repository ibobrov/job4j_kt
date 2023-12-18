package ru.job4j.oop.list

interface ListIterable<T> : Iterable<T> {
    fun listIterator(): ListIterator<T>
}
