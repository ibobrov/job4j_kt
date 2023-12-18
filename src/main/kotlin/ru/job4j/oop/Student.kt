package ru.job4j.oop

class Student(var name: String = "", var surname: String = "", var phone: String = "", var email: String = "") {
    constructor(name: String, surname: String) : this(name) {
        this.surname = surname
    }

    constructor(name: String, surname: String, phone: String) : this(name, surname) {
        this.phone = phone
    }

    override fun toString(): String = "name = $name, surname = $surname, phone = $phone, email = $email"
}
