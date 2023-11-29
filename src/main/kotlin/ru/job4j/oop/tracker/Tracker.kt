package ru.job4j.oop.tracker

class Tracker : Store {
    private val items = ArrayList<Item>()

    override fun add(item: Item): Item {
        item.id = items.size
        items.add(item.id, item)
        return item
    }

    override fun replace(id: Int, item: Item): Boolean {
        val contains = items.indices.contains(id)
        if (contains) {
            items[id] = item
        }
        return contains
    }

    override fun delete(id: Int): Boolean {
        val isDeleted = items.remove(findById(id))
        if (isDeleted) correctIds(id)
        return isDeleted
    }

    override fun findAll(): List<Item> {
        return ArrayList(items)
    }

    override fun findByName(key: String): List<Item> {
        val rsl = ArrayList<Item>()
        for (item in items) {
            if (key == item.name) {
                rsl.add(item)
            }
        }
        return rsl
    }

    override fun findById(id: Int): Item? {
        return items.getOrNull(id)
    }

    private fun correctIds(offset: Int) {
        for (i in offset until items.size) {
            items[i].id = i
        }
    }
}