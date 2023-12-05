package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmailServiceTest {
    private val service = EmailService()

    @Test
    fun whenMessageFoIvan() {
        val rsl = service.emailTo(Message("Ivan", "ivan@test.ru"))
        assertThat(rsl).isEqualTo("Subject : ivan@test.ru Body : Hello, ivan@test.ru You win!")
    }
}