package ru.job4j.oop.tracker

/**
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
 * ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
 * laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
 * voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
 * non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
 *
 * @author ibobrov
 * @since 14.12.2023
 */
class Tracker : Store {
    private val items = ArrayList<Item>()

    /**
     * Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
     * ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
     * laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
     * voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
     * non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
     *
     * @param item заявка
     * @return сохраннёную заявку
     */
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

    override fun findById(id: Int): Item? = items.getOrNull(id)

    private fun correctIds(offset: Int) {
        for (i in offset until items.size) {
            items[i].id = i
        }
    }
}
