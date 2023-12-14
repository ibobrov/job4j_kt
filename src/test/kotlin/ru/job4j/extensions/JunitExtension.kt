package ru.job4j.extensions

import org.assertj.core.api.AbstractBooleanAssert
import org.assertj.core.api.Assertions

infix fun Any.eq(expected: Any): AbstractBooleanAssert<*> = Assertions.assertThat(this == expected).isTrue()

infix fun Any.notEq(expected: Any): AbstractBooleanAssert<*> = Assertions.assertThat(this != expected).isTrue()

infix fun <T> List<T>.contains(values: T): AbstractBooleanAssert<*> =
    Assertions.assertThat(this.contains(values)).isTrue()

infix fun <T> List<T>.notContains(values: T): AbstractBooleanAssert<*> =
    Assertions.assertThat(this.contains(values)).isFalse()