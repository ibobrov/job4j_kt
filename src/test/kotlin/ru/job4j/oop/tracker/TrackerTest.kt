package ru.job4j.oop.tracker

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class TrackerTest {
    private val tracker = Tracker()

    @Test
    fun whenTestFindById() {
        val bug = Item("Bug")
        val item: Item = tracker.add(bug)
        val result: Item? = tracker.findById(item.id)
        assertThat(result?.name).isEqualTo(item.name)
    }

    @Test
    fun whenTestFindAll() {
        val first = Item("First")
        val second = Item("Second")
        tracker.add(first)
        tracker.add(second)
        val result: Item = tracker.findAll()[0]
        assertThat(result.name).isEqualTo(first.name)
    }

    @Test
    fun whenTestFindByNameCheckArrayLength() {
        val first = Item("First")
        val second = Item("Second")
        tracker.add(first)
        tracker.add(second)
        tracker.add(Item("First"))
        tracker.add(Item("Second"))
        tracker.add(Item("First"))
        val result: List<Item> = tracker.findByName(first.name)
        assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun whenTestFindByNameCheckSecondItemName() {
        val first = Item("First")
        val second = Item("Second")
        tracker.add(first)
        tracker.add(second)
        tracker.add(Item("First"))
        tracker.add(Item("Second"))
        tracker.add(Item("First"))
        val result: List<Item> = tracker.findByName(second.name)
        assertThat(result[1].name).isEqualTo(second.name)
    }

    @Test
    fun whenReplaceItemIsSuccessful() {
        val item = Item("Bug")
        tracker.add(item)
        val updateItem = Item("Bug with description")
        tracker.replace(item.id, updateItem)
        assertThat(tracker.findById(updateItem.id)?.name).isEqualTo("Bug with description")
    }

    @Test
    fun whenReplaceItemIsNotSuccessful() {
        val item = Item("Bug")
        tracker.add(item)
        val updateItem = Item("Bug with description")
        val result: Boolean = tracker.replace(1000, updateItem)
        assertThat(tracker.findById(item.id)?.name).isEqualTo("Bug")
        assertThat(result).isFalse()
    }

    @Test
    fun whenDeleteItemIsSuccessful() {
        val item = Item("Bug")
        tracker.add(item)
        val id: Int = item.id
        tracker.delete(id)
        assertThat(tracker.findById(id)).isNull()
    }

    @Test
    fun whenDeleteItemIsNotSuccessful() {
        val item = Item("Bug")
        tracker.add(item)
        val result: Boolean = tracker.delete(1000)
        assertThat(tracker.findById(item.id)?.name).isEqualTo("Bug")
        assertThat(result).isFalse()
    }
}
