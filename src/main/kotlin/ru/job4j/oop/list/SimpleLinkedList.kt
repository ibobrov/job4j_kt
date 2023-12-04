package ru.job4j.oop.list

class SimpleLinkedList<T> : SimpleList<T> {
    private var head : Node<T>? = null
    private var tail : Node<T>? = null

    class Node<K>(val value: K, var next: Node<K>? = null)

    override fun add(element: T): Boolean {
        val node = Node(element)
        if (head == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            tail = node
        }
        return true
    }

    override fun get(index: Int): T {
        return findByIndex(index).value
    }

    override fun contains(element: T): Boolean {
        val iterator = iterator()
        for (e in iterator) {
            if (e?.equals(element) == true) {
                return true
            }
        }
        return false
    }

    override fun removeAt(index: Int) {
        if (index == 0) {
            head = head?.next
            return
        }
        val nodeBefore = findByIndex(index - 1)
        val node = nodeBefore.next
        val nodeAfter = node?.next
        nodeBefore.next = nodeAfter
    }

    override fun isEmpty(): Boolean {
        return head == null
    }

    override fun iterator(): Iterator<T> {
        return LinkedIt()
    }

    private fun findByIndex(index : Int): Node<T> {
        if (index < 0) throw IndexOutOfBoundsException()
        var node = head
        for (i in 0 until index) {
            node = node?.next ?: throw IndexOutOfBoundsException()
        }
        return node ?: throw IndexOutOfBoundsException()
    }

    inner class LinkedIt : Iterator<T> {
        private var currNode = head

        override fun hasNext(): Boolean {
            return currNode != null
        }

        override fun next(): T {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val rsl = currNode
            currNode = currNode?.next
            return rsl!!.value
        }
    }
}