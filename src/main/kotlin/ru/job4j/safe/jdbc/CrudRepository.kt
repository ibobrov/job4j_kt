package ru.job4j.safe.jdbc

import java.sql.Connection

class CrudRepository : Repository<String> {
    private lateinit var connection: Connection

    fun init(connection: Connection) {
        this.connection = connection
        execute()
    }

    private fun execute() {
        val statement = connection.createStatement()
        statement.executeUpdate("create table one_line(path varchar not null unique)")
    }

    override fun insert(value: String): Boolean {
        val statement = connection.prepareStatement("insert into one_line(path) values (?)")
        statement.setString(1, value)
        return statement.executeUpdate() > 0
    }

    override fun update(oldValue: String, newValue: String): Boolean {
        val statement = connection.prepareStatement("update one_line set path = ? where path = ?")
        statement.setString(1, newValue)
        statement.setString(2, oldValue)
        return statement.executeUpdate() > 0
    }

    override fun delete(value: String): Boolean {
        val statement = connection.prepareStatement("delete from one_line where path = ?")
        statement.setString(1, value)
        return statement.executeUpdate() > 0
    }

    override fun find(value: String): String? {
        val statement = connection.prepareStatement("select path from one_line where path = ?")
        statement.setString(1, value)
        val rs = statement.executeQuery()
        return if (rs.next()) rs.getString(1) else null
    }

    override fun findAll(): List<String> {
        val statement = connection.createStatement()
        val rs = statement.executeQuery("select path from one_line")
        val rsl = ArrayList<String>()
        while (rs.next()) {
            rsl.add(rs.getString(1))
        }
        return rsl
    }
}