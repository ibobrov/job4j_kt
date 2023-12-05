package ru.job4j.lambda

data class Message(val username: String, val email: String)

class EmailService {

    fun emailTo(message: Message): String {
        return with(StringBuilder()) {
            append("Subject : ").append(message.email).append(" ")
            append("Body : ").append("Hello, ").append(message.email).append(" ")
            append("You win!")
        }.toString()
    }
}