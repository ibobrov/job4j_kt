package ru.job4j.base

fun max(first: Int, second: Int) : Int = if (first > second) first else second

fun max(first: Int, second: Int, third: Int) : Int = max(max(first, second), third)