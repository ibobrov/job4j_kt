package ru.job4j.oop.list

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SimpleLinkedListTest {
    private val list: SimpleLinkedList<String> = SimpleLinkedList()

    private fun add3elem() {
        list.add("root")
        list.add("second")
        list.add("third")
    }

    @Test
    fun whenAddElementAndGetBefore() {
        assertThat(list.add("root")).isTrue()
        assertThat(list.add("second")).isTrue()
        assertThat(list.get(0)).isEqualTo("root")
        assertThat(list.get(1)).isEqualTo("second")
    }

    @Test
    fun whenThrowIndexOutOfBoundsException() {
        add3elem()
        assertThat(list.get(0)).isEqualTo("root")
        assertThat(list.get(1)).isEqualTo("second")
        assertThrows<IndexOutOfBoundsException> {
            list.get(-1)
        }
        assertThrows<IndexOutOfBoundsException> {
            list.get(3)
        }
    }

    @Test
    fun whenContainsCorrect() {
        add3elem()
        assertThat(list.contains("root")).isTrue()
        assertThat(list.contains("")).isFalse()
        assertThat(list.contains("mock")).isFalse()
    }

    @Test
    fun whenRemoveHead() {
        add3elem()
        list.removeAt(0)
        assertThat(list.get(0)).isEqualTo("second")
        assertThat(list.get(1)).isEqualTo("third")
    }

    @Test
    fun whenRemoveMiddle() {
        add3elem()
        list.removeAt(1)
        assertThat(list.get(0)).isEqualTo("root")
        assertThat(list.get(1)).isEqualTo("third")
    }

    @Test
    fun whenRemoveTail() {
        add3elem()
        list.removeAt(2)
        assertThat(list.get(0)).isEqualTo("root")
        assertThat(list.get(1)).isEqualTo("second")
        assertThrows<IndexOutOfBoundsException> {
            list.get(2)
        }
    }

    @Test
    fun whenIsEmptyCorrect() {
        assertThat(list.isEmpty()).isTrue()
        list.add("test")
        assertThat(list.isEmpty()).isFalse()
    }

    @Test
    fun whenIteratorHasNextAndNext() {
        add3elem()
        val iterator = list.iterator()
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isEqualTo("root")
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isEqualTo("second")
        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isEqualTo("third")
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun whenCallNextOrPrevIndexListIterator() {
        add3elem()
        val iterator = list.listIterator()

        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.nextIndex()).isEqualTo(0)
        assertThat(iterator.previousIndex()).isEqualTo(-1)
        assertThat(iterator.next()).isEqualTo("root")

        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.next()).isEqualTo("second")

        assertThat(iterator.hasNext()).isTrue()
        assertThat(iterator.nextIndex()).isEqualTo(2)
        assertThat(iterator.previousIndex()).isEqualTo(0)
        assertThat(iterator.next()).isEqualTo("third")
        assertThat(iterator.nextIndex()).isEqualTo(-1)
        assertThat(iterator.hasNext()).isFalse()
    }

    @Test
    fun whenCallNextAndPrevToHeadListIterator() {
        add3elem()
        val iterator = list.listIterator()

        assertThat(iterator.next()).isEqualTo("root")
        assertThat(iterator.next()).isEqualTo("second")
        assertThat(iterator.next()).isEqualTo("third")
        assertThat(iterator.hasNext()).isFalse()

        assertThat(iterator.hasPrevious()).isTrue()
        assertThat(iterator.previous()).isEqualTo("second")
        assertThat(iterator.hasPrevious()).isTrue()
        assertThat(iterator.previousIndex()).isEqualTo(0)
        assertThat(iterator.previous()).isEqualTo("root")
        assertThat(iterator.previousIndex()).isEqualTo(-1)
        assertThat(iterator.hasPrevious()).isFalse()
    }
}