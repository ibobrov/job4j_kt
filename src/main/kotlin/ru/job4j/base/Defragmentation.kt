package ru.job4j.base

fun defragment(array: Array<String?>) {
    for (x in array.indices) {
        if (array[x] == null) {
            for (y in (x + 1) until array.size) {
                if (array[y] != null) {
                    array[x] = array[y]
                    array[y] = null
                    break
                }
            }
        }
    }
}