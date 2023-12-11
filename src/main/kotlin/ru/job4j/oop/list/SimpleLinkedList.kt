package ru.job4j.oop.list

class SimpleLinkedList<T> : SimpleList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    class Node<K>(val value: K, var next: Node<K>? = null, var prev: Node<K>? = null)

    override fun add(element: T): Boolean {
        val node = Node(element)
        if (head == null) {
            head = node
            tail = node
        } else {
            node.prev = tail
            tail?.next = node
            tail = node
        }
        return true
    }

    override fun get(index: Int): T {
        return findByIndex(index).value
    }

    override fun contains(element: T): Boolean {
        for (e in this) {
            if (e == element) {
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
        return listIterator()
    }

    override fun listIterator(): ListIterator<T> {
        return LinkedListIt()
    }

    private fun findByIndex(index: Int): Node<T> {
        if (index < 0) throw IndexOutOfBoundsException()
        var node = head
        for (i in 0 until index) {
            node = node?.next ?: throw IndexOutOfBoundsException()
        }
        return node ?: throw IndexOutOfBoundsException()
    }

    inner class LinkedListIt : ListIterator<T> {
        private var prevNode: Node<T>? = null
        private var currNode: Node<T>? = null
        private var nextNode = head
        private var index = -1

        override fun hasNext(): Boolean {
            return nextNode != null
        }

        override fun hasPrevious(): Boolean {
            return prevNode != null
        }

        override fun next(): T {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            prevNode = currNode
            currNode = nextNode
            nextNode = currNode?.next
            index++
            return currNode?.value!!
        }

        override fun nextIndex(): Int {
            return if (nextNode == null) -1 else index + 1
        }

        override fun previous(): T {
            if (!hasPrevious()) {
                throw NoSuchElementException()
            }
            nextNode = currNode
            currNode = prevNode
            prevNode = currNode?.prev
            index--
            return currNode?.value!!
        }

        override fun previousIndex(): Int {
            return if (prevNode == null) -1 else index - 1
        }
    }
}