package ru.job4j.lambda

import java.util.stream.Collectors

/**
 * Фильтр убирает чётные числа, map добавляет 1, коллектор складывает элементы и возвращает сумму.
 */
fun count(list: ArrayList<Int>) : Int {
    return list
        .stream()
        .filter { it % 2 != 0 }
        .map { it + 1 }
        .collect(Collectors.summingInt { it })
}