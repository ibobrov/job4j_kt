package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import ru.job4j.oop.tracker.Item

class JdbcItemStoreTest {
    private var postgres: PostgreSQLContainer<*> =
        PostgreSQLContainer(
            DockerImageName.parse("postgres:13.3"),
        )
    private var store: JdbcItemStore = JdbcItemStore()

    @BeforeEach
    fun startContainer() {
        postgres.apply {
            withDatabaseName("testDb")
            withUsername("root")
            withPassword("123456")
            start()
        }
        store.init(
            BasicDataSource().apply {
                url = postgres.jdbcUrl
                username = postgres.username
                password = postgres.password
            },
        )
    }

    @AfterEach
    fun stopContainer() {
        postgres.stop()
        store.close()
    }

    @Test
    fun whenTestFindById() {
        val item = store.add(Item("Bug"))
        val result = store.findById(item.id)
        assertThat(result?.name).isEqualTo(item.name)
    }

    @Test
    fun whenTestFindAll() {
        val first = Item("First")
        val second = Item("Second")
        store.add(first)
        store.add(second)
        val result: Item = store.findAll()[0]
        assertThat(result.name).isEqualTo(first.name)
    }

    @Test
    fun whenTestFindByNameCheckArrayLength() {
        val first = store.add(Item("First"))
        store.add(Item("Second"))
        store.add(Item("First"))
        store.add(Item("Second"))
        store.add(Item("First"))
        val result = store.findByName(first.name)
        assertThat(result.size).isEqualTo(3)
    }

    @Test
    fun whenTestFindByNameCheckSecondItemName() {
        store.add(Item("First"))
        val second = store.add(Item("Second"))
        store.add(Item("First"))
        store.add(Item("Second"))
        store.add(Item("First"))
        val result: List<Item> = store.findByName(second.name)
        assertThat(result[1].name).isEqualTo(second.name)
    }

    @Test
    fun whenReplaceItemIsSuccessful() {
        val item = store.add(Item("Bug"))
        store.replace(item.id, Item("Bug with description"))
        assertThat(store.findById(item.id)?.name).isEqualTo("Bug with description")
    }

    @Test
    fun whenReplaceItemIsNotSuccessful() {
        val item = store.add(Item("Bug"))
        val updateItem = Item("Bug with description")
        val result = store.replace(1000, updateItem)
        assertThat(store.findById(item.id)?.name).isEqualTo("Bug")
        assertThat(result).isFalse()
    }

    @Test
    fun whenDeleteItemIsSuccessful() {
        val item = store.add(Item("Bug"))
        store.delete(item.id)
        assertThat(store.findById(item.id)).isNull()
    }

    @Test
    fun whenDeleteItemIsNotSuccessful() {
        val item = store.add(Item("Bug"))
        assertThat(store.findById(item.id)?.name).isEqualTo("Bug")
        assertThat(store.delete(1000)).isFalse()
    }
}
