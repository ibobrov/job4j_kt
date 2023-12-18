package ru.job4j.safe.jdbc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import java.sql.DriverManager
import java.util.function.Consumer

class CrudRepositoryTest {
    private val postgres = PostgreSQLContainer(
        DockerImageName.parse("postgres:13.3"),
    ).apply {
        withDatabaseName("testDb")
        withUsername("root")
        withPassword("123456")
    }

    @BeforeEach
    fun startContainer() = postgres.start()

    @AfterEach
    fun stopContainer() = postgres.stop()

    private fun openConn(test: Consumer<CrudRepository>) {
        DriverManager.getConnection(postgres.jdbcUrl, postgres.username, postgres.password).use {
            with(CrudRepository()) {
                init(it)
                test.accept(this)
            }
        }
    }

    @Test
    fun whenAddAndFind() {
        openConn { crudRepository ->
            with(crudRepository) {
                assertThat(insert("src/main/java")).isTrue()
                assertThat(find("src/main/java")).isEqualTo("src/main/java")
                assertThat(find("src/main/kotlin")).isNull()
                assertThat(insert("src/main/kotlin")).isTrue()
                assertThat(find("src/main/kotlin")).isEqualTo("src/main/kotlin")
            }
        }
    }

    @Test
    fun whenAddAndFindAll() {
        openConn { crudRepository ->
            with(crudRepository) {
                assertThat(insert("src/main/java")).isTrue()
                assertThat(insert("src/main/kotlin")).isTrue()
                assertThat(findAll()).isEqualTo(listOf("src/main/java", "src/main/kotlin"))
            }
        }
    }

    @Test
    fun whenAddAndUpdate() {
        openConn { crudRepository ->
            with(crudRepository) {
                assertThat(insert("src/main/java")).isTrue()
                assertThat(insert("src/main/kotlin")).isTrue()
                assertThat(update("src/main/java", "updated")).isTrue()
                assertThat(findAll()).isEqualTo(listOf("src/main/kotlin", "updated"))
            }
        }
    }

    @Test
    fun whenAddAndDelete() {
        openConn { crudRepository ->
            with(crudRepository) {
                assertThat(insert("src/main/java")).isTrue()
                assertThat(insert("src/main/kotlin")).isTrue()
                assertThat(findAll()).isEqualTo(listOf("src/main/java", "src/main/kotlin"))
                assertThat(delete("src/main/kotlin")).isTrue()
                assertThat(findAll()).isEqualTo(listOf("src/main/java"))
            }
        }
    }
}
